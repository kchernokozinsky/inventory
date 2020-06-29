package inventory.orm.services;

import inventory.orm.entity.Goods;
import inventory.orm.entity.Group;
import inventory.orm.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
	private static SessionFactory sessionFactoryGoods = null;
	private static SessionFactory sessionFactoryGroup = null;
	private static SessionFactory sessionFactoryUser = null;
	private static Configuration configuration = null;

	public static SessionFactory getSessionFactory(Class klass) {
		if (configuration == null){
			configuration = new Configuration().configure();
			configuration.addAnnotatedClass(Group.class);
			configuration.addAnnotatedClass(User.class);
			configuration.addAnnotatedClass(Goods.class);
		}

		SessionFactory sessionFactory = null;
		if (klass == Goods.class) {
			sessionFactory = sessionFactoryGoods;
		}
		if (klass == Group.class) {
			sessionFactory = sessionFactoryGroup;
		}
		if (klass == User.class) {
			sessionFactory = sessionFactoryUser;
		}

		if (sessionFactory == null) {
			try {
				StandardServiceRegistryBuilder builder =
						new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
				sessionFactory = configuration.buildSessionFactory(builder.build());
				if (klass == Goods.class) {
					sessionFactoryGoods = sessionFactory;
				}
				if (klass == Group.class) {
					sessionFactoryGroup = sessionFactory;
				}
				if (klass == User.class) {
					sessionFactoryUser = sessionFactory;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return sessionFactory;
	}
}