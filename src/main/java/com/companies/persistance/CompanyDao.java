package com.companies.persistance;

import java.util.List;

import org.hibernate.Session;

import models.Company;

public interface CompanyDao {

	public void persist(Company company);

	void update(Company company);

	Company findById(int id);

	void delete(Company company);

	List<Company> findAll();

	void deleteAll();

	Session openCurrentSessionwithTransaction();

	void closeCurrentSessionwithTransaction();

	Session openCurrentSession();

	void closeCurrentSession();

	List<Company> getParentCompanies();

}
