package com.itfenbao.gadmins.core.web.service;

import com.itfenbao.gadmins.core.web.vo.DictVO;

import java.util.List;

public interface IDictManager {
    List<DictVO> getDictsByCode(String code);
}
