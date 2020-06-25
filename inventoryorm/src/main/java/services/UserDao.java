package services;

import entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.mindrot.jbcrypt.BCrypt;

import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;

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

    public User findById(int id) {
        User user = HibernateSessionFactoryUtil.getSessionFactory(User.class).openSession().get(User.class, id);
        if (user != null){
            return user;
        }
        throw new NoSuchElementException("User with such id does not exist");
    }

    public User findByLogin(String login) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory(User.class).openSession();
        Criteria criteria = session.createCriteria(User.class);
        User user = (User) criteria.add(Restrictions.eq("login", login)).uniqueResult();
        if (user != null){
            return user;
        }
        throw new NoSuchElementException("User with such login does not exist");
    }

    public List<User> findAll() {
        return (List<User>) HibernateSessionFactoryUtil.getSessionFactory(User.class).openSession().createQuery("From entity.User").list();
    }

    public User validateUser(String login, String password)
    {
        User user = findByLogin(login);
        if (BCrypt.checkpw(login+':'+password, user.getPasswordCache())){
            return user;
        }
        throw new InputMismatchException("Validation failed");
    }
}
