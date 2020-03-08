package com.harambase.hackathon.common.constant;

public enum SystemConst {

    SUCCESS("操作成功", 2001),
    FAIL("操作失败", 2002),
    SYSTEM_ERROR("系统异常", 2003),
    DELETE_BLOCK("不可删除", 2004),
    USER_DISABLED("用户已禁用", 2005),
    ADVISE_DUPLICATE("辅导关系已确立", 2006),
    REPEAT("课程重复", 2007),
    UNMET_PREREQ("预选课程未完成", 2008),
    COURSE_DISABLED("课程禁止选课", 2009),
    MAX_CAPACITY("课程容量达到上限", 2010),
    TIME_CONFLICT("时间冲突", 2011),
    PIN_EXISTS("该时段识别码已生成", 2012),
    WRONG_INFO("选课的时间有问题", 2013),
    WRONG_OLD_PASSWORD("旧密码有问题", 2014),
    FEEDBACK_DUPLICATE("重复评价", 2015),
    NEEDREG("需要注册或者找到账户", 2016);

    public static final String DEFAULT_SALT = "$2a$10$Vgi8tZLxvUdtt9pMj7ZEguBoOYohts.RGeQxcKqvf7PIKoeXQzmea";

    private String msg;
    private int code;

    SystemConst(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

}
