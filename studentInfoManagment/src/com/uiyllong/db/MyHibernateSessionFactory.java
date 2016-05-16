package com.uiyllong.db;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by uilong on 2016/5/8.
 */
public class MyHibernateSessionFactory {

    private static SessionFactory sessionFactory;      //会话工厂属性

    //构造方法私有化，保证单例模式
    private MyHibernateSessionFactory() {

    }

    /**
     * 公有静态方法，返回一个工厂实例
     * @return
     */
    public static SessionFactory getSessionFaactory() {
        if (sessionFactory == null) {
            //创建配置对象
            Configuration configuration = new Configuration().configure();
            //创建服务注册对象
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
            //创建 sessionFactory
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            return sessionFactory;
        } else {
            return sessionFactory;
        }
    }
}
