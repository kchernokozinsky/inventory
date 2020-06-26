package inventory.orm.services;

import inventory.orm.entity.User;
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

	private UserDao() {
	}

	public static UserDao getUserDao() {
		if (userDao == null) {
			userDao = new UserDao();
		}
		return userDao;
	}

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
		Session session = HibernateSessionFactoryUtil.getSessionFactory(User.class).openSession();
		Transaction tx1 = session.beginTransaction();
		User user = session.get(User.class, id);
		if (user != null) {
			return user;
		}
		tx1.commit();
		session.close();
		throw new NoSuchElementException("User with such id does not exist");
	}

	public User findByLogin(String login) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory(User.class).openSession();
		Transaction tx1 = session.beginTransaction();
		Criteria criteria = session.createCriteria(User.class);
		User user = (User) criteria.add(Restrictions.eq("login", login)).uniqueResult();
		if (user != null) {
			return user;
		}
		tx1.commit();
		session.close();
		System.out.println("11111111111111" + session);
		throw new NoSuchElementException("User with such login does not exist");
	}

	public List<User> findAll() {
		Session session = HibernateSessionFactoryUtil.getSessionFactory(User.class).openSession();
		Transaction tx1 = session.beginTransaction();
		List<User> ret = (List<User>) session.createQuery("From inventory.orm.entity.User").list();
		tx1.commit();
		session.close();
		return ret;
	}

	public User validateUser(String login, String password) {
		User user = findByLogin(login);
		if (BCrypt.checkpw(login + ':' + password, user.getPasswordHash())) {
			return user;
		}
		throw new InputMismatchException("Validation failed");
	}
}
