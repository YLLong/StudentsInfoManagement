<%--
  Created by IntelliJ IDEA.
  User: uilong
  Date: 2016/5/8
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    response.sendRedirect(path+"/users/Users_login.jsp");
%>