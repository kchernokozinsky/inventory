package services;

import entity.Goods;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class GoodsDao {

    private static GoodsDao goodsDao;

    public static GoodsDao getGroupDao(){
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
        Session session =   HibernateSessionFactoryUtil. getSessionFactory(Goods.class).openSession();
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

    public Goods findGoodsById(int id) {
        return   HibernateSessionFactoryUtil. getSessionFactory(Goods.class).openSession().get(Goods.class, id);
    }

    public Goods findGoodsByName(String name) {
        Session session =   HibernateSessionFactoryUtil. getSessionFactory(Goods.class).openSession();
        Criteria criteria = session.createCriteria(Goods.class);
        return (Goods) criteria.add(Restrictions.eq("name", name)).uniqueResult();
    }

    public List<Goods> listByNumber(int number) {
        Session session =   HibernateSessionFactoryUtil. getSessionFactory(Goods.class).openSession();
        Criteria crit = session.createCriteria(Goods.class);
        crit.add(Restrictions.eq("number", number));
        return crit.list();
    }

    public List<Goods> findAll() {
        return (List<Goods>)   HibernateSessionFactoryUtil. getSessionFactory(Goods.class).openSession().createQuery("From Goods").list();
    }
}