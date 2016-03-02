package com.companies.services;

import java.util.List;

import models.Company;

public interface CompanyService {
	
	void persist(Company company, int parentId);

	void update(Company company);

	Company findById(int id);

	void delete(int id);

	void delete(Company company);

	List<Company> findAll();

	public void deleteAll();

	String getCompaniesTree();

}
