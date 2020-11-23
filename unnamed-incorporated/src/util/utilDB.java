package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Query;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


import datamodel.Resturant;
import datamodel.Reservation;
import datamodel.User;

public class utilDB {
	static SessionFactory sessionFactory = null;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory != null) {
			return sessionFactory;
		}
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		sessionFactory = configuration.buildSessionFactory(builder.build());
		return sessionFactory;
	}
	
	public static void createRes(int rID, String rname, String desc, String addr, String city, String state, String hours) {
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(new Resturant(rID, rname, desc, addr, city, state, hours));
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	//for when we need to pull multiple restaurants for search results and such
	public static List<Resturant> getResturantList(){
		List<Resturant> resultList = new ArrayList<Resturant>();

	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;  // each process needs transaction and commit the changes in DB.

	      try {
	         tx = session.beginTransaction();
	         List<?> resturants = session.createQuery("FROM Resturant").list();
	         for (Iterator<?> iterator = resturants.iterator(); iterator.hasNext();) {
	            Resturant resturant = (Resturant) iterator.next();
	            resultList.add(resturant);
	         }
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx != null)
	            tx.rollback();
	         e.printStackTrace();
	      } finally {
	         session.close();
	      }
	      return resultList;
	}
	
	public static Resturant getResturant(Integer rID) {
		Resturant r = null;
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("FROM Resturant R where R.rID = " + rID);
			r = (Resturant) q.list().get(0);
		}
		catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return r;
	}
	
	public static void createReservation(int restID, int custID, String reservationName, String date, String time, int numPeople) {
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(new Reservation(restID, custID, reservationName, date, time, numPeople));
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	//for pulling multiple Reservations in the case of the Restaurant Information page
	public static List<Reservation> getReservationRest(Integer rID) {
		List<Reservation> resultList = new ArrayList<Reservation>();
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			List<?> reservations = session.createQuery("FROM RESERVATIONS").list();
			for (Iterator<?> iterator = reservations.iterator(); iterator.hasNext();) {
				Reservation reservation = (Reservation) iterator.next();
				if (reservation.getRestaurantId() == rID) {
					resultList.add(reservation);
				}
			}
			tx.commit();
		}
		catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return resultList;
	}
	
	//for pulling multiple Reservations in the case of the Customer Information page
	public static List<Reservation> getReservationCust(Integer cID) {
		List<Reservation> resultList = new ArrayList<Reservation>();
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
			
		try {
			tx = session.beginTransaction();
			List<?> reservations = session.createQuery("FROM RESERVATIONS").list();
			for (Iterator<?> iterator = reservations.iterator(); iterator.hasNext();) {
				Reservation reservation = (Reservation) iterator.next();
				if (reservation.getCustomerID() == cID) {
					resultList.add(reservation);
				}
			}
			tx.commit();
		}
		catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return resultList;
	}
	
	public static void createUser(String username, String password, boolean identityBit) {
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(new User(username, password, identityBit));
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	//used for pulling user lists when validating sign in
	public static List<User> getRestaurantUserList(){
		List<User> resultList = new ArrayList<User>();

	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;  // each process needs transaction and commit the changes in DB.

	      try {
	         tx = session.beginTransaction();
	         List<?> Users = session.createQuery("FROM User").list();
	         for (Iterator<?> iterator = Users.iterator(); iterator.hasNext();) {
	            User user = (User) iterator.next();
	            if (user.isRestaurant() == true) {
	            	resultList.add(user);
	            }
	         }
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx != null)
	            tx.rollback();
	         e.printStackTrace();
	      } finally {
	         session.close();
	      }
	      return resultList;
	}
	
	//used for pulling user lists when validating sign in
		public static List<User> getCustomerUserList(){
			List<User> resultList = new ArrayList<User>();

		      Session session = getSessionFactory().openSession();
		      Transaction tx = null;  // each process needs transaction and commit the changes in DB.

		      try {
		         tx = session.beginTransaction();
		         List<?> Users = session.createQuery("FROM User").list();
		         for (Iterator<?> iterator = Users.iterator(); iterator.hasNext();) {
		            User user = (User) iterator.next();
		            if (user.isRestaurant() == false) {
		            	resultList.add(user);
		            }
		         }
		         tx.commit();
		      } catch (HibernateException e) {
		         if (tx != null)
		            tx.rollback();
		         e.printStackTrace();
		      } finally {
		         session.close();
		      }
		      return resultList;
		}

}
