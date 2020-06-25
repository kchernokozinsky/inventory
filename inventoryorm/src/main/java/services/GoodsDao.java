package services;

import entity.Goods;
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

    public static GoodsDao getGoodsDao(){
        if (goodsDao == null){
            goodsDao = new GoodsDao();
        }
        return goodsDao;
    }

    private GoodsDao(){}

    public void save(Goods goods) {
        Session session =   HibernateSessionFactoryUtil. getSessionFactory(Goods.class).openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(goods);
        tx1.commit();
        session.close();
    }

    public void update(Goods goods) {
        Session session = HibernateSessionFactoryUtil. getSessionFactory(Goods.class).openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(goods);
        tx1.commit();
        session.close();
    }

    public void delete(Goods goods) {
        Session session =   HibernateSessionFactoryUtil. getSessionFactory(Goods.class).openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(goods);
        tx1.commit();
        session.close();
    }

    public Goods findById(int id) {
        Goods goods = HibernateSessionFactoryUtil. getSessionFactory(Goods.class).openSession().get(Goods.class, id);
        if (goods != null){
            return goods;
        }
        throw new NoSuchElementException("Goods with such id do not exist");
    }

    public Goods findByName(String name) {
        Session session =   HibernateSessionFactoryUtil. getSessionFactory(Goods.class).openSession();
        Criteria criteria = session.createCriteria(Goods.class);
        Goods goods = (Goods) criteria.add(Restrictions.eq("name", name)).uniqueResult();
        if(goods != null) {
            return goods;
        }
        throw new NoSuchElementException("Goods with such name do not exist");
    }

    public List<Goods> findByNameLike(String name) {
        if ("".equals(name))
            return findAll();
        Session session =   HibernateSessionFactoryUtil. getSessionFactory(Goods.class).openSession();
        Criteria criteria = session.createCriteria(Goods.class);
        Criterion c1 = Restrictions.like("name", name, MatchMode.ANYWHERE);
        criteria.add(c1);
        return criteria.list();
    }

    public List<Goods> getListByNumber(int number) {
        Session session =   HibernateSessionFactoryUtil. getSessionFactory(Goods.class).openSession();
        Criteria crit = session.createCriteria(Goods.class);
        crit.add(Restrictions.eq("number", number));
        return crit.list();
    }

    public List<Goods> findAll() {
        return (List<Goods>)   HibernateSessionFactoryUtil. getSessionFactory(Goods.class).openSession().createQuery("From entity.Goods").list();
    }

    public List<Goods> getListByGroupId(int id) {
        Session session = HibernateSessionFactoryUtil. getSessionFactory(Goods.class).openSession();
        Criteria crit = session.createCriteria(Goods.class);
        crit.add(Restrictions.eq("groupId", id));
        return crit.list();
    }
}