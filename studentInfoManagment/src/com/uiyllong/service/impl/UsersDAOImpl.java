package com.uiyllong.service.impl;

import com.uiyllong.bean.Users;
import com.uiyllong.db.MyHibernateSessionFactory;
import com.uiyllong.service.UsersDAO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by uilong on 2016/5/8.
 */
public class UsersDAOImpl implements UsersDAO {
    @Override
    public boolean usersLogin(Users u) {

        Transaction ts = null;
        String hql = "";
        try {
            Session session = MyHibernateSessionFactory.getSessionFaactory().getCurrentSession();
            ts = session.beginTransaction();
            hql = "from Users where username = ? and password = ?";
            Query query = session.createQuery(hql);
            query.setParameter(0, u.getUsername());
            query.setParameter(1, u.getPassword());
            List list = query.list();
            ts.commit();
            if (list.size() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (ts != null) {
                ts = null;
            }
        }
    }
}
