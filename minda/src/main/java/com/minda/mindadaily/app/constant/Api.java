package com.minda.mindadaily.app.constant;

import com.minda.mindadaily.utils.TimeUtil;

/**
 * Created by kun on 2017/1/19.
 */

public class Api {
    public static final String URL_LOGIN = "http://ids.scuec.edu.cn"; // 个人图书馆URL
    public static final String URL_GOTO = "http://my.scuec.edu.cn/index.portal";
    public static final String URL_CHECK = "http://ssfw.scuec.edu.cn/ssfw/j_spring_ids_security_check/"; // URL
    public static final String URL_COURSE = "http://ssfw.scuec.edu.cn/ssfw/pkgl/kcbxx/4/" + TimeUtil.getChangeCouserUrl() + ".do/"; // 课程表URL

}
