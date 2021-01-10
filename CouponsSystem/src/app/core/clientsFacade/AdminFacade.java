package app.core.clientsFacade;

import java.util.ArrayList;

import app.core.beans.Company;
import app.core.beans.Customer;
import app.core.dao.impel.CompaniesDBDAO;
import app.core.dao.impel.CouponsDBDAO;
import app.core.dao.impel.CustomersDBDAO;
import app.core.exceptions.CouponSystemFacadException;
import app.core.exceptions.DAOException;

public class AdminFacade extends ClientFacade {

	public AdminFacade() throws CouponSystemFacadException {
		try {
			super.companiesDAO = new CompaniesDBDAO();
			super.customersDAO = new CustomersDBDAO();
			super.couponsDAO = new CouponsDBDAO();
		} catch (DAOException e) {
			e.printStackTrace();
			throw new CouponSystemFacadException("AdminFacade Error: initializing AdminFacade failed", e);
		}
	}

	@Override
	public boolean login(String email, String passwoed) {
		if (email.equals("admin@admin.com") && passwoed.equals("admin")) {
			return true;
		} else {
			return false;
		}
	}

	public void addCompany(Company company) throws CouponSystemFacadException {

		try {
			// Check the db if contains a company with the same name, if true throw facade
			// exception
			if (companiesDAO.isCompanyNameExist(company.getName())) {
				throw new CouponSystemFacadException(
						"AdminFacade Error: adding company failed, company name already exists");
			}
			// Check the db if contains a company with the same email, if true throw facade
			// exception
			else if (companiesDAO.isCompanyEmailExist(company.getEmail())) {
				throw new CouponSystemFacadException(
						"AdminFacade Error: adding company failed, company email already exists");
			}
			// if passes then add company to db
			else {
				companiesDAO.addCompany(company);
			}
		} catch (DAOException e) {
			e.printStackTrace();
			throw new CouponSystemFacadException("AdminFacade Error: adding company failed", e);
		}

	}

	public void updateCompany(Company company) throws CouponSystemFacadException {

		try {
			companiesDAO.updateCompany(company);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new CouponSystemFacadException("AdminFacade Error: updating company failed", e);
		}

	}

	public void deleteCompany(Company company) throws CouponSystemFacadException {

		try {
			companiesDAO.deleteCompany(company.getId());
		} catch (DAOException e) {
			e.printStackTrace();
			throw new CouponSystemFacadException("AdminFacade Error: deleting company failed", e);
		}

	}

	public ArrayList<Company> getAllCompanies() throws CouponSystemFacadException {
		try {
			return companiesDAO.getAllCompanies();
		} catch (DAOException e) {
			e.printStackTrace();
			throw new CouponSystemFacadException("AdminFacade Error: getting all companies failed", e);
		}
	}

	public Company getOneCompany(Company company) throws CouponSystemFacadException {
		try {
			return companiesDAO.getOneCompany(company.getId());
		} catch (DAOException e) {
			e.printStackTrace();
			throw new CouponSystemFacadException("AdminFacade Error: getting company failed", e);
		}
	}

	public void addCustomer(Customer customer) throws CouponSystemFacadException {

		try {
			if (!customersDAO.isCustomerEmailExists(customer.getEmail())) {
				customersDAO.addCustomer(customer);
			} else {
				throw new CouponSystemFacadException(
						"AdminFacade Error: adding customer failed, customer email already exists");
			}
		} catch (DAOException e) {
			e.printStackTrace();
			throw new CouponSystemFacadException("AdminFacade Error: adding customer failed", e);
		}

	}

	public void updateCustomer(Customer customer) throws CouponSystemFacadException {

		try {
			customersDAO.updateCustomer(customer);

		} catch (DAOException e) {
			e.printStackTrace();
			throw new CouponSystemFacadException("AdminFacade Error: updating customer failed", e);
		}

	}

	public void deleteCustomer(int customerID) throws CouponSystemFacadException {
		try {
			customersDAO.deleteCustomer(customerID);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new CouponSystemFacadException("AdminFacade Error: delete customer failed", e);
		}
	}

	public ArrayList<Customer> getAllCustomers() throws CouponSystemFacadException {

		try {
			return customersDAO.getAllCustomers();
		} catch (DAOException e) {
			e.printStackTrace();
			throw new CouponSystemFacadException("AdminFacade Error: geting all customer failed", e);
		}

	}

	public Customer getOneCustomer(int customerID) throws CouponSystemFacadException {
		try {
			return customersDAO.getOneCustomer(customerID);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new CouponSystemFacadException("AdminFacade Error: geting customer failed", e);
		}

	}

}
