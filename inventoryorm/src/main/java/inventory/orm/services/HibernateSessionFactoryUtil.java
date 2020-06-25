package inventory.orm.services;

import inventory.orm.entity.Goods;
import inventory.orm.entity.Group;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
	private static SessionFactory sessionFactoryGoods;
	private static SessionFactory sessionFactoryGroup;
	private static SessionFactory sessionFactoryUser;

	private HibernateSessionFactoryUtil() {
	}

	public static SessionFactory getSessionFactory(Class klass) {
		SessionFactory sessionFactory = null;
		if (klass == Goods.class) {
			sessionFactory = sessionFactoryGoods;
		}
		if (klass == Group.class) {
			sessionFactory = sessionFactoryGroup;
		}
		if (klass == Group.class) {
			sessionFactory = sessionFactoryUser;
		}

		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration().configure();
				configuration.addAnnotatedClass(klass);
				StandardServiceRegistryBuilder builder =
						new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
				sessionFactory = configuration.buildSessionFactory(builder.build());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}
}