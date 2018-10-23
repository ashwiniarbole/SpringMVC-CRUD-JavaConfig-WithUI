package com.scp.spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scp.spring.model.Person;

@Repository("personDAO")
public class PersonDAOImpl implements PersonDAO {

	
	private static final Logger logger = LoggerFactory.getLogger(PersonDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	public void addPerson(Person p) {
		System.out.println("in dao add----------------------");
		Session session = this.sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		session.persist(p);
		session.flush();
		tr.commit();
		logger.info("Person saved successfully, Person Details="+p);
	}

	public void updatePerson(Person p) {
		Session session = this.sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		session.update(p);
		logger.info("Person updated successfully, Person Details="+p);
		session.flush();
		tr.commit();
		
		
		
	}

	@SuppressWarnings("unchecked")
	public List<Person> listPersons() {
		Session session = this.sessionFactory.openSession();
		List<Person> personsList = session.createQuery("from Person").list();
		for(Person p : personsList){
			logger.info("Person List::"+p);
		}
		return personsList;
	}

	public Person getPersonById(int id) {
		Session session = this.sessionFactory.openSession();		
		Person p = (Person) session.load(Person.class, new Integer(id));
		logger.info("Person loaded successfully, Person details="+p);
		return p;
	}

	public void removePerson(int id) {
		Session session = this.sessionFactory.openSession();
		
		Person p = (Person) session.load(Person.class, new Integer(id));
		if(null != p){
			Transaction tr = session.beginTransaction();
			session.delete(p);
			session.flush();
			tr.commit();
		}
		logger.info("Person deleted successfully, person details="+p);
	}

}
