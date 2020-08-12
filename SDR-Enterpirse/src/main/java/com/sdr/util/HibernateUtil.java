package com.sdr.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;



public class HibernateUtil {

	private static SessionFactory sessionFactory = buildSessionFactory();

	/*
	 * private void b() { Query<Product> query =
	 * session.createQuery("from User u where u.scn=:scn", Product.class);
	 * query.setParameter("scn", scn); Product user = query.uniqueResult();
	 * 
	 * @Autowired private EntityManager entityManager; User user = null; Query query
	 * = entityManager.createQuery("SELECT u FROM User u WHERE u.email=:email");
	 * query.setParameter("email", email); try { user = (User)
	 * query.getSingleResult(); } catch (Exception e) { // Handle exception } return
	 * user;
	 * 
	 * }
	 */	
	   private static SessionFactory buildSessionFactory()
	   {
	      try
	      {
	         if (sessionFactory == null)
	         {
	            Configuration configuration = new Configuration().configure(HibernateUtil.class.getResource("/hibernate.cfg.xml"));
	            StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
	            serviceRegistryBuilder.applySettings(configuration.getProperties());
	            ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
	            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	         }
	         return sessionFactory;
	      } catch (Throwable ex)
	      {
	         System.err.println("Initial SessionFactory creation failed." + ex);
	         throw new ExceptionInInitializerError(ex);
	      }
	   }
	 
	   public static SessionFactory getSessionFactory()
	   {
	      return sessionFactory;
	   }
	 
	   public static void shutdown()
	   {
	      getSessionFactory().close();
	   }
}
