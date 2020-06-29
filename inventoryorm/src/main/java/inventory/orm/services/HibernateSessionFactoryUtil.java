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

	public static SessionFactory getSessionFactory(Class klass) {
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

		System.out.println(sessionFactoryGoods);
		System.out.println(sessionFactoryGroup);
		System.out.println(sessionFactoryUser);

		if (sessionFactory == null) {
			try {
				System.out.println("1111");
				Configuration configuration = new Configuration().configure();
				configuration.addAnnotatedClass(klass);
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