package com.itfenbao.gadmins.admin.filters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.models.Swagger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.util.UriComponents;
import springfox.documentation.service.Documentation;
import springfox.documentation.spring.web.DocumentationCache;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.mappers.ServiceModelToSwagger2Mapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Optional.ofNullable;
import static springfox.documentation.swagger.common.HostNameProvider.componentsFrom;

@Slf4j
@WebFilter(urlPatterns = "/v2/api-docs", filterName = "SwaggerApiFilter")
public class SwaggerApiFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletRequest.getServletContext());
        ResponseWrapper wrapperResponse = new ResponseWrapper((HttpServletResponse) servletResponse);//转换成代理类
        filterChain.doFilter(servletRequest, wrapperResponse);
        byte[] content = wrapperResponse.getContent();//获取返回值
        //判断是否有值
        if (content.length > 0) {
            String newResponseStr = rewriteResponse(context, (HttpServletRequest) servletRequest);
            ServletOutputStream out = servletResponse.getOutputStream();
            out.write(newResponseStr.getBytes());
            out.flush();
            out.close();
        }
    }

    private String rewriteResponse(ApplicationContext context, HttpServletRequest servletRequest) throws ServletRequestBindingException, JsonProcessingException {
        String groupName = ServletRequestUtils.getStringParameter(servletRequest, "group");
        groupName = ofNullable(groupName).orElse(Docket.DEFAULT_GROUP_NAME);
        Environment environment = context.getBean(Environment.class);
        String hostNameOverride = environment.getProperty("springfox.documentation.swagger.v2.host", "DEFAULT");
        DocumentationCache documentationCache = context.getBean(DocumentationCache.class);
        ServiceModelToSwagger2Mapper mapper = context.getBean(ServiceModelToSwagger2Mapper.class);
        ObjectMapper objectMapper = context.getBean(ObjectMapper.class);
        Documentation documentation = documentationCache.documentationByGroup(groupName);
        if (documentation == null) {
            return null;
        }
        // TODO: 添加动态接口文档
        Swagger swagger = mapper.mapDocumentation(documentation);
        UriComponents uriComponents = componentsFrom(servletRequest, swagger.getBasePath());
        swagger.basePath(StringUtils.isEmpty(uriComponents.getPath()) ? "/" : uriComponents.getPath());
        if (StringUtils.isEmpty(swagger.getHost())) {
            swagger.host(hostName(uriComponents, hostNameOverride));
        }
        return objectMapper.writeValueAsString(swagger);
    }

    private String hostName(UriComponents uriComponents, String hostNameOverride) {
        if ("DEFAULT".equals(hostNameOverride)) {
            String host = uriComponents.getHost();
            int port = uriComponents.getPort();
            if (port > -1) {
                return String.format("%s:%d", host, port);
            }
            return host;
        }
        return hostNameOverride;
    }
}
