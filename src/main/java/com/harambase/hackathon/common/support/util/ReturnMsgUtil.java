package com.harambase.hackathon.common.support.util;


import com.harambase.hackathon.common.ResultMap;
import com.harambase.hackathon.common.constant.SystemConst;

public class ReturnMsgUtil {

    public static ResultMap success(Object data) {
        ResultMap resultMap = new ResultMap();
        resultMap.setData(data);
        resultMap.setCode(SystemConst.SUCCESS.getCode());
        resultMap.setMsg(SystemConst.SUCCESS.getMsg());
        return resultMap;
    }

    public static ResultMap fail() {
        ResultMap resultMap = new ResultMap();
        resultMap.setCode(SystemConst.FAIL.getCode());
        resultMap.setMsg(SystemConst.FAIL.getMsg());
        return resultMap;
    }

    public static ResultMap systemError() {
        ResultMap resultMap = new ResultMap();
        resultMap.setCode(SystemConst.SYSTEM_ERROR.getCode());
        resultMap.setMsg(SystemConst.SYSTEM_ERROR.getMsg());
        return resultMap;
    }

    public static ResultMap custom(SystemConst systemConst) {
        ResultMap resultMap = new ResultMap();
        resultMap.setCode(systemConst.getCode());
        resultMap.setMsg(systemConst.getMsg());
        return resultMap;
    }
}
