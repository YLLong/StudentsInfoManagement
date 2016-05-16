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
    @Override
    public List<Students> queryAllStudents() {

        Transaction ts = null;
        List<Students> list = null;
        String hql = "";
        try {
            Session session = MyHibernateSessionFactory.getSessionFaactory().getCurrentSession();
            ts = session.beginTransaction();
            hql = "from Students";
            Query query = session.createQuery(hql);
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
