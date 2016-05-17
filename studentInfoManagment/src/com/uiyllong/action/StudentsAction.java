package com.uiyllong.action;

import com.uiyllong.bean.Students;
import com.uiyllong.service.StudentsDAO;
import com.uiyllong.service.impl.StudentsDAOImpl;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by uilong on 2016/5/9.
 */
public class StudentsAction extends SuperAction {

    private int pageNum;
    private int pageSize;
    private int totalpage;

    /**
     * 查询全部学生资料
     * @return
     */
    public String query() {
        StudentsDAO sDAO = new StudentsDAOImpl();
        List<Students> list = sDAO.queryAllStudents(pageNum, pageSize);
        session.setAttribute("students_list", list);
        return "query_success";
    }

    /**
     * 删除学生资料
     * @return
     */
    public String delete() {
        StudentsDAO sDAO = new StudentsDAOImpl();
        String sid = request.getParameter("sid");
        sDAO.deleteStudents(sid);
        return "delete_success";
    }

    /**
     * 添加学生资料
     * @return
     * @throws Exception
     */
    public String add() throws Exception {
        StudentsDAO sDAO = new StudentsDAOImpl();
        Students s = new Students();
        s.setSname(request.getParameter("sname"));
        s.setGender(request.getParameter("gender"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        s.setBirthday(sdf.parse(request.getParameter("birthday")));
        s.setAddress(request.getParameter("address"));
        sDAO.addStudents(s);
        return "add_success";
    }

    /**
     * 修改学生资料
     * @return
     */
    public String modify() {
        String sid = request.getParameter("sid");
        StudentsDAO sDAO = new StudentsDAOImpl();
        Students s = sDAO.queryStudentBySid(sid);
        session.setAttribute("modify_students", s);
        return "modify_success";
    }

    /**
     * 保存修改后的学生资料
     * @return
     * @throws Exception
     */
    public String save() throws Exception {
        StudentsDAO sDAO = new StudentsDAOImpl();
        Students s = new Students();
        s.setSid(request.getParameter("sid"));
        s.setSname(request.getParameter("sname"));
        s.setGender(request.getParameter("gender"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        s.setBirthday(sdf.parse(request.getParameter("birthday")));
        s.setAddress(request.getParameter("address"));
        sDAO.updateStudents(s);
        return "sava_success";
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
