package com.itfenbao.gadmins.admin.filters;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
import com.itfenbao.gadmins.admin.entity.DatawayApi;
import com.itfenbao.gadmins.admin.entity.DatawayGroup;
import com.itfenbao.gadmins.admin.service.IApiService;
import com.itfenbao.gadmins.admin.service.IGroupService;
import io.swagger.models.Swagger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.util.UriComponents;
import springfox.documentation.OperationNameGenerator;
import springfox.documentation.builders.OperationBuilder;
import springfox.documentation.service.*;
import springfox.documentation.spring.web.DocumentationCache;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.mappers.ServiceModelToSwagger2Mapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
        OperationNameGenerator nameGenerator = context.getBean(OperationNameGenerator.class);
        ServiceModelToSwagger2Mapper mapper = context.getBean(ServiceModelToSwagger2Mapper.class);
        ObjectMapper objectMapper = context.getBean(ObjectMapper.class);
        Documentation documentation = documentationCache.documentationByGroup(groupName);
        if (documentation == null) {
            return null;
        }
        generateDatawayApi(groupName, context, nameGenerator, documentation);
        Swagger swagger = mapper.mapDocumentation(documentation);
        UriComponents uriComponents = componentsFrom(servletRequest, swagger.getBasePath());
        swagger.basePath(StringUtils.isEmpty(uriComponents.getPath()) ? "/" : uriComponents.getPath());
        if (StringUtils.isEmpty(swagger.getHost())) {
            swagger.host(hostName(uriComponents, hostNameOverride));
        }
        return objectMapper.writeValueAsString(swagger);
    }

    private void generateDatawayApi(String groupName, ApplicationContext context, OperationNameGenerator nameGenerator, Documentation documentation) {
        IGroupService groupService = context.getBean(IGroupService.class);
        IApiService apiService = context.getBean(IApiService.class);
        groupService.list(Wrappers.<DatawayGroup>lambdaQuery().eq(DatawayGroup::getGroupType, groupName)).forEach(it -> {
            String tagStr = "动态接口-" + it.getDesc();
            Tag tag = new Tag(tagStr, it.getDesc());
            documentation.getTags().add(tag);
            List<DatawayApi> apis = apiService.list(
                    Wrappers.<DatawayApi>lambdaQuery()
                            .eq(DatawayApi::getGroupId, it.getId())
                            .eq(DatawayApi::getStatus, 1)
            );
            apis.forEach(api -> {
                Operation operation = new OperationBuilder(nameGenerator)
                        .method(HttpMethod.valueOf(api.getApiMethod()))
                        .uniqueId("test")
                        .summary(api.getApiComment())
                        .tags(Sets.newHashSet(tagStr))
                        .build();
                ApiDescription apiDescription = new ApiDescription(it.getUrlPrefix(), api.getApiPath(), api.getApiComment(), Arrays.asList(operation), false);
                ApiListing apiListing = new ApiListing(
                        "1.0.0", "/", api.getApiPath(), Sets.newHashSet(),
                        Sets.newHashSet(), "", Sets.newHashSet(), CollUtil.newArrayList(),
                        Arrays.asList(apiDescription), CollUtil.newHashMap(), api.getApiComment(), 0, Sets.newHashSet(tag));
                documentation.getApiListings().put(api.getApiPath(), apiListing);
            });
        });
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
