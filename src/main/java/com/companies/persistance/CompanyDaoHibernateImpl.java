package com.companies.persistance;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.companies.domain.Company;

public class CompanyDaoHibernateImpl implements CompanyDao {

	private Session currentSession;

	private Transaction currentTransaction;

	public CompanyDaoHibernateImpl() {
	}

	public Session openCurrentSession() {
		return currentSession = getSessionFactory().openSession();
	}

	public Session openCurrentSessionwithTransaction() {
		currentSession = getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}

	public void closeCurrentSession() {
		currentSession.close();
	}

	public void closeCurrentSessionwithTransaction() {
		currentTransaction.commit();
		currentSession.close();
	}

	private static SessionFactory getSessionFactory() {
		return HibernateUtil.getSessionFactory();
	}

	public Session getCurrentSession() {
		return currentSession;
	}

	public void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}

	public Transaction getCurrentTransaction() {
		return currentTransaction;
	}

	public void setCurrentTransaction(Transaction currentTransaction) {
		this.currentTransaction = currentTransaction;
	}

	public void persist(Company entity) {
		getCurrentSession().save(entity);
	}

	public void update(Company entity) {
		getCurrentSession().update(entity);
	}

	public Company findById(int id) {
		Criteria criteria = getCurrentSession().createCriteria(Company.class);
		criteria.add(Restrictions.eq("id", id));
		Company company = (Company) criteria.uniqueResult();
		return company;
	}

	public void delete(Company entity) {
		getCurrentSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<Company> findAll() {
		Criteria criteria = getCurrentSession().createCriteria(Company.class);
		
		criteria.list();
		List<Company> companies = criteria.list();
		return companies;
	}

	public void deleteAll() {
		List<Company> entityList = findAll();
		for (Company entity : entityList) {
			delete(entity);
		}
	}
	
	public List<Company> getParentCompanies() {
		Criteria criteria = getCurrentSession().createCriteria(Company.class);
		criteria.add(Restrictions.isNull("parentCompany"));
		return criteria.list();
	}
	
}
