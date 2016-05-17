package com.uiyllong.service.impl;

import com.uiyllong.bean.Students;
import com.uiyllong.db.MyHibernateSessionFactory;
import com.uiyllong.service.StudentsDAO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by uilong on 2016/5/9.
 */
public class StudentsDAOImpl implements StudentsDAO {

    /**
     * 每页显示的学生信息
     * @param pageNum   所在页数，计算显示的是从哪个数开始，默认是0开始
     * @param pageSize  每页显示的个数
     * @return 返回一个 list 是该页显示的数据
     */
    @Override
    public List<Students> queryAllStudents(int pageNum, int pageSize) {
        Transaction ts = null;
        List<Students> list = null;
        String hql = "";
        try {
            Session session = MyHibernateSessionFactory.getSessionFaactory().getCurrentSession();
            ts = session.beginTransaction();
            hql = "from Students";
            Query query = session.createQuery(hql);
            query.setFirstResult((pageNum - 1) * pageSize);
            query.setMaxResults(pageSize);
            list = query.list();
            ts.commit();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            ts.commit();
            return list;
        } finally {
            if (ts != null)
                ts = null;
        }

    }

    /**
     * 计算学生总人数
     * @return
     */
    @Override
    public int getStudentsCount() {
        int i = 0;
        Transaction ts = null;
        String hql = "";
        try {
            Session session = MyHibernateSessionFactory.getSessionFaactory().getCurrentSession();
            ts = session.beginTransaction();
            hql = "from Students";
            Query query = session.createQuery(hql);
            i = query.list().size();
            ts.commit();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            ts.commit();
            return i;
        } finally {
            if (ts != null) {
                ts = null;
            }
        }
    }

    /**
     * 通过 sid 查找学生信息
     * @param sid
     * @return
     */
    @Override
    public Students queryStudentBySid(String sid) {
        Transaction ts = null;
        Students student = null;
        try {
            Session session = MyHibernateSessionFactory.getSessionFaactory().getCurrentSession();
            ts = session.beginTransaction();
            student = session.get(Students.class, sid);
            ts.commit();
            return student;
        } catch (Exception e) {
            e.printStackTrace();
            ts.commit();
            return student;
        } finally {
            if (ts != null)
                ts = null;
        }
    }

    @Override
    public boolean addStudents(Students s) {

        s.setSid(getNewSId());
        Transaction ts = null;
        try {
            Session session = MyHibernateSessionFactory.getSessionFaactory().getCurrentSession();
            ts = session.beginTransaction();
            session.save(s);
            ts.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            ts.commit();
            return false;
        } finally {
            if (ts != null)
                ts = null;
        }
    }

    @Override
    public boolean updateStudents(Students s) {
        Transaction ts = null;
        try {
            Session session = MyHibernateSessionFactory.getSessionFaactory().getCurrentSession();
            ts = session.beginTransaction();
            session.update(s);
            ts.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            ts.commit();
            return false;
        } finally {
            if (ts != null)
                ts = null;
        }
    }

    @Override
    public boolean deleteStudents(String sid) {

        Transaction ts = null;
        try {
            Session session = MyHibernateSessionFactory.getSessionFaactory().getCurrentSession();
            ts = session.beginTransaction();
            Students s = session.get(Students.class, sid);
            session.delete(s);
            ts.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            ts.commit();
            return false;
        } finally {
            if (ts != null)
                ts = null;
        }
    }

    /**
     * 生成学生 id
     * @return
     */
    private String getNewSId() {
        Transaction ts = null;
        String hql = "";
        String sid = null;
        try {
            Session session = MyHibernateSessionFactory.getSessionFaactory().getCurrentSession();
            ts = session.beginTransaction();
            hql = "select max(sid) from Students";
            Query query = session.createQuery(hql);
            sid = (String) query.uniqueResult();
            if (sid == null || "".equals(sid)) {
                sid = "s00001";
            } else {
                String temp = sid.substring(1);
                int i = Integer.parseInt(temp);
                i++;
                sid = String.valueOf(i);
                int len = sid.length();
                for (int j = 0; j < (temp.length() - len); j++) {
                    sid = "0" + sid;
                }
                sid = "s" + sid;
            }
            ts.commit();
            return sid;
        } catch (Exception e) {
            e.printStackTrace();
            ts.commit();
            return null;
        } finally {
            if (ts != null)
                ts = null;
        }
    }
}
