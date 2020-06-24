package services;

import entity.Group;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;


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

    public Group findGroupById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory(Group.class).openSession().get(Group.class, id);
    }

    public Group findGroupByName(String name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory(Group.class).openSession();
        Criteria criteria = session.createCriteria(Group.class);
        return (Group) criteria.add(Restrictions.eq("name", name)).uniqueResult();
    }

    public List<Group> findAll() {
        return (List<Group>) HibernateSessionFactoryUtil.getSessionFactory(Group.class).openSession().createQuery("From Group").list();
    }
}