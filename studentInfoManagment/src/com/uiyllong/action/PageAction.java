package com.uiyllong.action;

import com.opensymphony.xwork2.ActionSupport;
import com.uiyllong.service.StudentsDAO;
import com.uiyllong.service.impl.StudentsDAOImpl;

/**
 * 计算当前所在页数，总页数，每页显示的学生个数
 * Created by uilong on 2016/5/17.
 */
public class PageAction extends ActionSupport {

    private int pageNum;        //当前所在页数
    private int pageSize;       //每页显示的个数
    private int totalpage;      //总页数

    @Override
    public String execute() throws Exception {
        StudentsDAO sDAO = new StudentsDAOImpl();
        pageSize = 5;
        int sudentsCount = sDAO.getStudentsCount();
        totalpage = (sudentsCount % pageSize == 0) ? (sudentsCount / pageSize) : (sudentsCount / pageSize + 1);
        if (pageNum <= 0)
            pageNum = 1;
        if (pageNum > totalpage)
            pageNum = totalpage;
        return SUCCESS;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(int totalpage) {
        this.totalpage = totalpage;
    }
}
