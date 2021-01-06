package app.core.dao;

import java.util.ArrayList;

import app.core.beans.Company;
import app.core.exceptions.CouponSystemConnectionException;
import app.core.exceptions.CouponSystemDAOException;

/**
 * @author Nours
 *
 */
public interface CompaniesDAO {


	/**
	 * @param email
	 * @param password
	 * @return
	 * @throws CouponSystemConnectionException 
	 * @throws CouponSystemDAOException 
	 */
	boolean isCompanyExists(String email, String password) throws CouponSystemDAOException;
	
	
	/**
	 * @param company
	 * @throws CouponSystemDAOException 
	 */
	void addCompany(Company company) throws CouponSystemDAOException;
	
	
	/**
	 * @param company
	 * @throws CouponSystemDAOException 
	 */
	void updateCompany(Company company) throws CouponSystemDAOException;
	
	
	/**
	 * @param companyID
	 * @throws CouponSystemDAOException 
	 */
	void deleteCompany(int companyID) throws CouponSystemDAOException;
	
	
	/**
	 * @return
	 * @throws CouponSystemDAOException 
	 */
	ArrayList<Company> getAllCompanies() throws CouponSystemDAOException;
	
	
	/**
	 * @param companyID
	 * @return
	 * @throws CouponSystemDAOException 
	 */
	Company getOneCompany(int companyID) throws CouponSystemDAOException;
	
	
}
