package com.uiyllong.test;

import com.uiyllong.bean.Students;
import com.uiyllong.bean.Users;
import com.uiyllong.service.StudentsDAO;
import com.uiyllong.service.UsersDAO;
import com.uiyllong.service.impl.StudentsDAOImpl;
import com.uiyllong.service.impl.UsersDAOImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.EnumSet;
import java.util.List;

/**
 * Created by uilong on 2016/5/8.
 */
public class TestStudents {

    /**
     * 查询所有学生资料
     */
    @Test
    public void testQueryAllStudents() {
        StudentsDAO sDAO = new StudentsDAOImpl();
        List<Students> list = sDAO.queryAllStudents();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    /**
     * 向学生表中插入数据
     */
    @Test
    public void testSaveStudents() {
        //创建配置对象
        Configuration configuration = new Configuration().configure();
        //创建服务注册对象
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
        //创建 sessionFactory
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        //创建 session 对象
        Session session = sessionFactory.getCurrentSession();

        Transaction ts = session.beginTransaction();
        Students s1 = new Students("s00001", "张三", "男", new Date(), "四川");
        Students s2 = new Students("s00002", "王兒", "女", new Date(), "重庆");
        Students s3 = new Students("s00003", "麻子", "男", new Date(), "北京");
        session.save(s1);
        session.save(s2);
        session.save(s3);
        ts.commit();
        sessionFactory.close();
    }

    /**
     * 登录测试 用上了 断言
     */
    @Test
    public void testUsersDAOImple() {
        Users u = new Users(1, "鄢淋珑", "123456");
        UsersDAO uDAO = new UsersDAOImpl();
        Assert.assertEquals(true, uDAO.usersLogin(u));
    }

    /**
     * 生成数据库表结构的方法
     */
    @Test
    public void testSchemaExport() {
        //创建配置对象
        Configuration configuration = new Configuration().configure();
        //创建服务注册对象
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
        //创建 sessionFactory
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        //创建 session 对象
        Session session = sessionFactory.getCurrentSession();
        //创建 ShemaExport 对象
        Metadata metadata = new MetadataSources(serviceRegistry).buildMetadata();
        SchemaExport export = new SchemaExport();
        export.create(EnumSet.of(TargetType.DATABASE), metadata);
    }
}
