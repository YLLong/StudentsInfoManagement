package com.uiyllong.action;

import com.opensymphony.xwork2.ModelDriven;
import com.uiyllong.bean.Users;
import com.uiyllong.service.UsersDAO;
import com.uiyllong.service.impl.UsersDAOImpl;
import org.apache.struts2.interceptor.validation.SkipValidation;

/**
 * Created by uilong on 2016/5/9.
 */
public class UsersAction extends SuperAction implements ModelDriven<Users> {

    private Users user = new Users();

    /**
     * 用户登录处理方式
     * @return
     */
    public String login() {
        UsersDAO uDAO = new UsersDAOImpl();
        if (uDAO.usersLogin(user)) {
            //将登录成功的用户名保存到 session 中
            session.setAttribute("loginUserName", user.getUsername());
            return "login_success";
        } else {
            return "login_failure";
        }
    }

    /**
     * 用户注销的方法
     * @return
     */
    @SkipValidation
    public String logout() {
        if (session.getAttribute("loginUserName") != null) {
            session.removeAttribute("loginUserName");
        }
        return "logout_success";
    }

    /**
     * 用户登录的验证
     */
    @Override
    public void validate() {
        if ("".equals(user.getUsername())) {
            addFieldError("usernameError", "用户名不能为空！");
        }
        if (user.getPassword().length() < 6) {
            addFieldError("passwordError", "密码长度不能小于6位！");
        }
    }

    @Override
    public Users getModel() {
        return this.user;
    }
}
