package com.companies.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.companies.services.CompanyService;

import models.Company;

@RestController
@RequestMapping("/company")
@Component
public class CompaniesController {
	
	@Autowired
	private CompanyService companyService;
	
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	@ResponseBody
	public Company getCompany(@PathVariable int id) {
		return companyService.findById(id);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Company> getAllCompanies() {
		return companyService.findAll();
	}

	@RequestMapping(path = "/tree", method = RequestMethod.GET)
	@ResponseBody
	public String getAllCompaniesTree() {
		return companyService.getCompaniesTree();
	}
	
	@RequestMapping(path = "/add/{parentId}", method = RequestMethod.POST)
	public void persist(@RequestBody Company company, @PathVariable int parentId) {
		companyService.persist(company, parentId);
	}
	
	@RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable int id) {
		System.out.println(id);
		companyService.delete(id);
	}

	@RequestMapping(path = "/update", method = RequestMethod.PUT)
	@ResponseBody
	public void update(@RequestBody Company company) {
		companyService.update(company);
	}
}
