/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.hasor.dataway.config;

import com.itfenbao.gadmins.core.web.result.JsonReturnCode;
import net.hasor.dataql.QueryResult;
import net.hasor.dataql.domain.DataModel;
import net.hasor.dataql.runtime.ThrowRuntimeException;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 工具。
 *
 * @author 赵永春 (zyc@hasor.net)
 * @version : 2020-03-20
 */
public class DatawayUtils {
    public static String evalCodeValueForSQL(String strCodeValue, Map<String, Object> strRequestBody) {
        StringBuilder paramKeyBuilder = new StringBuilder("");
        StringBuilder callKeyBuilder = new StringBuilder("");
        for (String key : strRequestBody.keySet()) {
            paramKeyBuilder.append("`" + key + "`,");
            callKeyBuilder.append("${" + key + "},");
        }
        if (paramKeyBuilder.length() > 0) {
            paramKeyBuilder.deleteCharAt(paramKeyBuilder.length() - 1);
            callKeyBuilder.deleteCharAt(callKeyBuilder.length() - 1);
        }
        strCodeValue = "var tempCall = @@sql(" + paramKeyBuilder.toString() + ")<%" + strCodeValue + "%>;\n";
        strCodeValue = strCodeValue + "return tempCall(" + callKeyBuilder.toString() + ");";
        return strCodeValue;
    }

    private static final ThreadLocal<Long> localRequestTime = ThreadLocal.withInitial(System::currentTimeMillis);

    public static void resetLocalTime() {
        localRequestTime.remove();
        localRequestTime.set(System.currentTimeMillis());
    }

    public static long currentLostTime() {
        return System.currentTimeMillis() - localRequestTime.get();
    }

    public static Map<String, Object> queryResultToResult(QueryResult queryResult) {
        return queryResultToResultWithSpecialValue(queryResult, queryResult.getData());
    }

    public static Map<String, Object> queryResultToResultWithSpecialValue(QueryResult queryResult, Object specialValue) {
        return new LinkedHashMap<String, Object>() {{
            put("code", JsonReturnCode.SUCCESS.getCode());
            put("message", JsonReturnCode.SUCCESS.getDesc());
            put("lifeCycleTime", currentLostTime());
            put("executionTime", queryResult.executionTime());
            //
            if (specialValue instanceof DataModel) {
                put("data", ((DataModel) specialValue).unwrap());
            } else {
                put("data", specialValue);
            }
        }};
    }

    public static Map<String, Object> exceptionToResult(Exception e) {
        if (e instanceof ThrowRuntimeException) {
            return new LinkedHashMap<String, Object>() {{
                put("code", ((ThrowRuntimeException) e).getThrowCode());
                put("message", e.getMessage());
                put("lifeCycleTime", currentLostTime());
                put("executionTime", ((ThrowRuntimeException) e).getExecutionTime());
                put("data", ((ThrowRuntimeException) e).getResult().unwrap());
            }};
        } else {
            return new LinkedHashMap<String, Object>() {{
                put("code", 500);
                put("message", e.getMessage());
                put("lifeCycleTime", currentLostTime());
                put("executionTime", -1);
                put("data", e.getMessage());
            }};
        }
    }
}