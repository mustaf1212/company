package com.companies.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "companies")
public class Company {
	
	public Company() {
		
	}

	public Company(String name, double estimatedErnings, List<Company> sabsidiaryCompanies) {
		super();
		this.name = name;
		this.estimatedErnings = estimatedErnings;
		this.sabsidiaryCompanies = sabsidiaryCompanies;
	}

	@Id
	@GeneratedValue(strategy  = GenerationType.AUTO)
	private int id;
	
	
	@ManyToOne
	@JoinColumn(name = "parent_id")
	private Company parentCompany;
	
	@Column
	private String name;

	@Column(name = "estimated_ernings")
	private double estimatedErnings;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	@JsonIgnore
	private List<Company> sabsidiaryCompanies;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getEstimatedEarnings() {
		return estimatedErnings;
	}

	public void setEstimatedEarnings(double estimatedEarnings) {
		this.estimatedErnings = estimatedEarnings;
	}
	
	public List<Company> getSabsidiaryCompanies() {
		return sabsidiaryCompanies;
	}
	
	
	
	public Company getParentCompany() {
		return parentCompany;
	}

	public void setParentCompany(Company parentCompany) {
		this.parentCompany = parentCompany;
	}

	public void setSabsidiaryCompanies(List<Company> sabsidiaryCompanies) {
		this.sabsidiaryCompanies = sabsidiaryCompanies;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", estimatedErnings="
				+ estimatedErnings + "]";
	}

	

}
