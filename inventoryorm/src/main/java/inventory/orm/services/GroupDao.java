package inventory.orm.services;

import inventory.orm.entity.Group;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.NoSuchElementException;

public class GroupDao {

	private static GroupDao groupDao;

	private GroupDao() {
	}

	public static GroupDao getGroupDao() {
		if (groupDao == null) {
			groupDao = new GroupDao();
		}
		return groupDao;
	}

	public void save(Group group) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory(Group.class).openSession();
		Transaction tx1 = session.beginTransaction();
		session.save(group);
		tx1.commit();
		session.close();
	}

	public void update(Group group) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory(Group.class).openSession();
		Transaction tx1 = session.beginTransaction();
		session.update(group);
		tx1.commit();
		session.close();
	}

	public void delete(Group group) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory(Group.class).openSession();
		Transaction tx1 = session.beginTransaction();
		session.delete(group);
		tx1.commit();
		session.close();
	}

	public Group findById(int id) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory(Group.class).openSession();
		Transaction tx1 = session.beginTransaction();
		Group group = session.get(Group.class, id);
		tx1.commit();
		session.close();
		if (group != null) {
			return group;
		}
		throw new NoSuchElementException("Group with such id does not exist");
	}

	public Group findByName(String name) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory(Group.class).openSession();
		Transaction tx1 = session.beginTransaction();
		Criteria criteria = session.createCriteria(Group.class);
		Group group = (Group) criteria.add(Restrictions.eq("name", name)).uniqueResult();
		tx1.commit();
		session.close();
		if (group != null) {
			return group;
		}
		throw new NoSuchElementException("Group with such name does not exist");
	}

	public List<Group> findByNameLike(String name) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory(Group.class).openSession();
		Transaction tx1 = session.beginTransaction();
		Criteria criteria = session.createCriteria(Group.class);
		Criterion c1 = Restrictions.like("name", name, MatchMode.ANYWHERE);
		List<Group> groups = criteria.add(c1).list();
		tx1.commit();
		session.close();
		return groups;
	}

	public List<Group> findAll() {
		Session session = HibernateSessionFactoryUtil.getSessionFactory(Group.class).openSession();
		Transaction tx1 = session.beginTransaction();
		List<Group> res = (List<Group>) session.createQuery("From inventory.orm.entity.Group").list();
		tx1.commit();
		session.close();
		return res;
	}
}