package com.scp.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scp.spring.dao.PersonDAO;
import com.scp.spring.model.Person;

@Service("personService")
public class PersonServiceImpl implements PersonService {
	
	private PersonDAO personDAO;

	@Autowired(required=true)
	@Qualifier(value="personDAO")
	public void setPersonDAO(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}


	@Transactional
	public void addPerson(Person p) {
		System.out.println("in service add----------------------");
		this.personDAO.addPerson(p);
	}

	
	@Transactional
	public void updatePerson(Person p) {
		this.personDAO.updatePerson(p);
	}


	@Transactional
	public List<Person> listPersons() {
		return this.personDAO.listPersons();
	}


	@Transactional
	public Person getPersonById(int id) {
		return this.personDAO.getPersonById(id);
	}


	@Transactional
	public void removePerson(int id) {
		this.personDAO.removePerson(id);
	}

}
