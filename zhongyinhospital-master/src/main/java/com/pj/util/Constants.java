package com.pj.util;

/**
 * 通用常量信息：定义某个数字对应的单词
 * 
 */
public class Constants
{
    /**
     * 防止实例化
     **/
    private Constants() {
    }


    public static final String COMMON_STATUS_ONE = "1";

    public static final String COMMON_STATUS_ZERO = "0";

    public static final String COMMON_STATUS_MINUS = "-1";

    public static final String IC_READ_FAIIL = "fail";

    public static final String IC_READ_NONE = "none";


    public class URL {
        public static final String ADDRESS_URL = "http://whois.pconline.com.cn/ip.jsp";
    }

    public class USER {

        public static final String USER_NOT_EXIST = "该账户不存在";

        public static final String PASSWORD_ERROR = "密码错误";

        public static final String EMAIL_STATUS_INACTIVE = "账户未激活";

        public static final String ROLE_STATUS_NOTAUDIT = "角色暂未审核,请等待或联系管理员";

        public static final String ROLE_STATUS_NOTPASS = "角色未审核通过，请联系管理员";

        public static final String SUCCESS = "SUCCESS";

        public static final String ACCOUNT_EXIST = "该账户已存在，若要添加角色登录后账户设置";

        public static final String PLAIN_PASSWORD_ERROR = "旧密码错误";

        public static final String OLD_EQUALS_NEW_PASSWORD = "新密码与旧密码一致";

        public static final String CHANGE_OK = "修改成功";

        public static final String FAIL = "FAIL";

        public static final String OLD_NO_NEW = "密码确认输入不一致！";

        public static final String ADD_OK = "添加成功";

        public static final String ANN_EQUALS = "该公告已存在";
    }

    public class REGISTER {

        public static final String NONE = "NONE";

        public static final String COVER = "COVER";         //补办就诊卡

        public static final String ACTIVATED = "ACTIVATED"; //就诊卡已被激活

    }

    public class QUEUE {

        /**
         * 队列状态：稍后处理(待处理)
         **/
        public static final int LATER = -1;

        /**
         * 队列状态：过期
         **/
        public static final int OVERDUE = 0;

        /**
         * 队列状态：正常
         **/
        public static final int NORMAL = 1;
    }


    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * GBK 字符集
     */
    public static final String GBK = "GBK";
    /**
     * 角色管理cache key
     */
    public static final String SYS_ROLE_KEY = "sys-role:";
    /**
     * 菜单管理cache key
     */
    public static final String SYS_MENU_KEY = "sys-menu:";
    /**
     * 个人菜单管理cache name
     */
    public static final String USER_MENU_CACHE = "user-menu";
    /**
     * 菜单管理cache key
     */
    public static final String USER_MENU_KEY = "user-menu:";
    /**
     * 字典类型管理 cache key
     */
    public static final String SYS_DICTTYPE_KEY = "sys_dicttype:";
    /**
     * 字典管理 cache name
     */
    public static final String SYS_DICT_CACHE = "sys-dict";
    /**
     * 字典管理 cache key
     */
    public static final String SYS_DICT_KEY = "sys_dict:";
    /**
     * 科室管理 cache key
     */
    public static final String SYS_DEPARTMENT_KEY = "sys_department:";
    /**
     * 药物管理 cache key
     */
    public static final String SYS_DRUG_KEY = "sys_drug:";

    /**
     * 角色管理cache name
    public static final String SYS_ROLE_CACHE = "sys-role";
     */
    /**
     * 菜单管理cache name
    public static final String SYS_MENU_CACHE = "sys-menu";
     */
    /**
     * 字典类型管理 cache name
    public static final String SYS_DICTTYPE_CACHE = "sys-dicttype";
     */
    /**
     * 药物管理 cache name
    public static final String SYS_DRUG_CACHE = "sys-drug";
     */

    /**
     * 科室管理 cache name
    public static final String SYS_DEPARTMENT_CACHE = "sys-department";
     */


}
