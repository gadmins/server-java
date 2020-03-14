package com.itfenbao.gadmins.admin.component;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.itfenbao.gadmins.admin.entity.Dict;
import com.itfenbao.gadmins.admin.service.IDictService;
import com.itfenbao.gadmins.core.web.service.IDictManager;
import com.itfenbao.gadmins.core.web.vo.DictVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DictManager implements IDictManager {

    @Autowired
    IDictService dictService;

    @Override
    public List<DictVO> getDictsByCode(String code) {
        Dict dict = dictService.getOne(Wrappers.<Dict>lambdaQuery().eq(Dict::getDCode, code));
        if (dict == null) {
            return new ArrayList<>();
        }
        Integer pid = dict.getId();
        return dictService.list(Wrappers.<Dict>lambdaQuery().eq(Dict::getPId, pid)).stream().map(item -> {
            DictVO dictVO = new DictVO();
            dictVO.setIndexVal(item.getIndexValue());
            dictVO.setStringVal(item.getDValue());
            return dictVO;
        }).collect(Collectors.toList());
    }

}
