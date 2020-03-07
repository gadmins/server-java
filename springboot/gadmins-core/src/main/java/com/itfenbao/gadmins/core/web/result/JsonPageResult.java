package com.itfenbao.gadmins.core.web.result;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itfenbao.gadmins.core.web.PageData;

/**
 * json 分页结果
 *
 * @param <T>
 * @author itfenbao
 */
public class JsonPageResult<T> extends JsonResult<PageData<T>> {

    public static <T> JsonPageResult<T> success(IPage<T> page) {
        JsonPageResult<T> ret = new JsonPageResult<>();
        ret.setData(PageData.get(page));
        return ret;
    }

}
