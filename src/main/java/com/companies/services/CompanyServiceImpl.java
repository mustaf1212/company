package com.companies.services;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.companies.domain.Company;
import com.companies.persistance.CompanyDao;
import com.companies.util.CompaniesView;

@Component
public class CompanyServiceImpl implements CompanyService{
	
	@Autowired
	private  CompanyDao companyDao;

	public void persist(Company company, int parentId) {
		companyDao.openCurrentSessionwithTransaction();
		company.setParentCompany(companyDao.findById(parentId));
		companyDao.persist(company);
		companyDao.closeCurrentSessionwithTransaction();
	}	

	public void update(Company company) {
		companyDao.openCurrentSessionwithTransaction();
		companyDao.update(company);
		companyDao.closeCurrentSessionwithTransaction();
	}

	public Company findById(int id) {
		try {
			companyDao.openCurrentSession();
			Company company = companyDao.findById(id);
			Hibernate.initialize(company.getSabsidiaryCompanies());
			for (Company sabsidiaryCompany : company.getSabsidiaryCompanies()) {
				Hibernate.initialize(sabsidiaryCompany.getSabsidiaryCompanies());
			}
			return company;
		} finally {
			companyDao.closeCurrentSession();
		}
	}

	public void delete(int id) {
		companyDao.openCurrentSessionwithTransaction();
		Company company = companyDao.findById(id);
		companyDao.delete(company);
		companyDao.closeCurrentSessionwithTransaction();
	}

	public void delete(Company company) {
		companyDao.openCurrentSessionwithTransaction();
		companyDao.delete(company);
		companyDao.closeCurrentSessionwithTransaction();

	}

	public List<Company> findAll() {
		companyDao.openCurrentSession();
		List<Company> companies = companyDao.findAll();
		companyDao.closeCurrentSession();
		return companies;
	}

	public void deleteAll() {
		companyDao.openCurrentSessionwithTransaction();
		companyDao.deleteAll();
		companyDao.closeCurrentSessionwithTransaction();
	}

	public String getCompaniesTree() {
		companyDao.openCurrentSession();
		List<Company> parentCompanies = companyDao.getParentCompanies();
		String result = CompaniesView.printCompanies(parentCompanies);
		companyDao.closeCurrentSession();
		return result;
	}

}