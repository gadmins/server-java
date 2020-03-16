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
        ret.setCode(JsonReturnCode.SUCCESS.getCode());
        ret.setMsg(JsonReturnCode.SUCCESS.getDesc());
        ret.setData(PageData.get(page));
        return ret;
    }

    public static JsonPageResult http403() {
        JsonPageResult ret = new JsonPageResult();
        ret.setCode(JsonReturnCode.ACCESS_ERROR.getCode());
        ret.setMsg(JsonReturnCode.ACCESS_ERROR.getDesc());
        return ret;
    }

}
