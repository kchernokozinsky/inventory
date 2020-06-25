package services;

import entity.Group;
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

    public static GroupDao getGroupDao(){
        if (groupDao == null){
            groupDao = new GroupDao();
        }
        return groupDao;
    }

    private GroupDao(){}

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
    }

    public void delete(Group group) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory(Group.class).openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(group);
        tx1.commit();
        session.close();
    }

    public Group findById(int id) {
        Group group = HibernateSessionFactoryUtil.getSessionFactory(Group.class).openSession().get(Group.class, id);
        if (group != null){
            return group;
        }
        throw new NoSuchElementException("Group with such id does not exist");
    }

    public Group findByName(String name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory(Group.class).openSession();
        Criteria criteria = session.createCriteria(Group.class);
        Group group =(Group) criteria.add(Restrictions.eq("name", name)).uniqueResult();
        if (group != null){
            return group;
        }
        throw new NoSuchElementException("Group with such name does not exist");
    }

    public List<Group> findByNameLike(String name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory(Group.class).openSession();
        Criteria criteria = session.createCriteria(Group.class);
        Criterion c1 = Restrictions.like("name", name, MatchMode.ANYWHERE);
        return criteria.add(c1).list();
    }

    public List<Group> findAll() {
        return (List<Group>) HibernateSessionFactoryUtil.getSessionFactory(Group.class).openSession().createQuery("From entity.Group").list();
    }
}