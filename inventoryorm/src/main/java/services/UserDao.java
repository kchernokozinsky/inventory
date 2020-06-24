package services;

import entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class UserDao {

    private static UserDao userDao;

    public static UserDao getUserDao(){
        if (userDao == null){
            userDao = new UserDao();
        }
        return userDao;
    }

    private UserDao(){}

    public void save(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory(User.class).openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    public void update(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory(User.class).openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    public void delete(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory(User.class).openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }

    public User findUserById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory(User.class).openSession().get(User.class, id);
    }

    public User findUserByName(String name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory(User.class).openSession();
        Criteria criteria = session.createCriteria(User.class);
        return (User) criteria.add(Restrictions.eq("name", name)).uniqueResult();
    }

    public List<User> findAll() {
        return (List<User>) HibernateSessionFactoryUtil.getSessionFactory(User.class).openSession().createQuery("From User").list();
    }
}
