package inventory.orm.services;

import inventory.orm.entity.Goods;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.NoSuchElementException;

public class GoodsDao {

	private static GoodsDao goodsDao;

	private GoodsDao() {
	}

	public static GoodsDao getGoodsDao() {
		if (goodsDao == null) {
			goodsDao = new GoodsDao();
		}
		return goodsDao;
	}

	public void save(Goods goods) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory(Goods.class).openSession();
		Transaction tx1 = session.beginTransaction();
		session.save(goods);
		tx1.commit();
		session.close();
	}

	public void update(Goods goods) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory(Goods.class).openSession();
		Transaction tx1 = session.beginTransaction();
		session.update(goods);
		tx1.commit();
		session.close();
	}

	public void delete(Goods goods) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory(Goods.class).openSession();
		Transaction tx1 = session.beginTransaction();
		session.delete(goods);
		tx1.commit();
		session.close();
	}

	public Goods findById(int id) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory(Goods.class).openSession();
		Transaction tx1 = session.beginTransaction();
		Goods goods = session.get(Goods.class, id);
		tx1.commit();
		session.close();
		if (goods != null) {
			return goods;
		}
		throw new NoSuchElementException("Goods with such id do not exist");
	}

	public Goods findByName(String name) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory(Goods.class).openSession();
		Transaction tx1 = session.beginTransaction();
		Criteria criteria = session.createCriteria(Goods.class);
		Goods goods = (Goods) criteria.add(Restrictions.eq("name", name)).uniqueResult();
		tx1.commit();
		session.close();
		if (goods != null) {
			return goods;
		}

		throw new NoSuchElementException("Goods with such name do not exist");
	}

	public List<Goods> findByNameLike(String name) {
		if ("".equals(name))
			return findAll();
		Session session = HibernateSessionFactoryUtil.getSessionFactory(Goods.class).openSession();
		Transaction tx1 = session.beginTransaction();
		Criteria criteria = session.createCriteria(Goods.class);
		Criterion c1 = Restrictions.like("name", name, MatchMode.ANYWHERE);
		criteria.add(c1);
		List<Goods> res = criteria.list();
		tx1.commit();
		session.close();
		return res;
	}

	public List<Goods> getListByNumber(int number) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory(Goods.class).openSession();
		Transaction tx1 = session.beginTransaction();
		Criteria crit = session.createCriteria(Goods.class);
		crit.add(Restrictions.eq("number", number));
		List<Goods> res = crit.list();
		tx1.commit();
		session.close();
		return res;
	}

	public List<Goods> findAll() {
		Session session = HibernateSessionFactoryUtil.getSessionFactory(Goods.class).openSession();
		Transaction tx1 = session.beginTransaction();
		List<Goods> res = session.createQuery("From inventory.orm.entity.Goods").list();
		tx1.commit();
		session.close();
		return res;
	}

	public List<Goods> getListByGroupId(int id) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory(Goods.class).openSession();
		Transaction tx1 = session.beginTransaction();
		Criteria crit = session.createCriteria(Goods.class);
		crit.add(Restrictions.eq("groupId", id));
		List<Goods> goods = crit.list();
		tx1.commit();
		session.close();
		return goods;
	}
}