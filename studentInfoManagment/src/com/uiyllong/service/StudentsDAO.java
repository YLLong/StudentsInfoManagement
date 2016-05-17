package com.uiyllong.service;

import com.uiyllong.bean.Students;

import java.util.List;

/**
 * Created by uilong on 2016/5/8.
 */
public interface StudentsDAO {

    //查询所有学生资料
    public List<Students> queryAllStudents(int pageNum, int pageSize);

    //统计学生总人数
    public int getStudentsCount();

    //根据 id 查询学生资料
    public Students queryStudentBySid(String sid);

    //添加学生资料
    public boolean addStudents(Students s);

    //修改学生资料
    public boolean updateStudents(Students s);

    //删除学生资料
    public boolean deleteStudents(String sid);

}
