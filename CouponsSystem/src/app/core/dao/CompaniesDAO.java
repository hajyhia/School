package app.core.dao;

import java.util.ArrayList;

import app.core.beans.Company;
import app.core.exceptions.ConnectionPoolException;
import app.core.exceptions.DAOException;

/**
 * @author Nours
 *
 */
public interface CompaniesDAO {


	/**
	 * @param email
	 * @param password
	 * @return
	 * @throws ConnectionPoolException 
	 * @throws DAOException 
	 */
	boolean isCompanyExists(String email, String password) throws DAOException;
	

	public boolean isCompanyEmailExist(String companyEmail) throws DAOException;
	
	
	public boolean isCompanyNameExist(String companyName) throws DAOException;
	
	
	
	public Company getLoginInCompanyID(String email, String password) throws DAOException;
	
	
	/**
	 * @param company
	 * @throws DAOException 
	 */
	void addCompany(Company company) throws DAOException;
	
	
	/**
	 * @param company
	 * @throws DAOException 
	 */
	void updateCompany(Company company) throws DAOException;
	
	
	/**
	 * @param companyID
	 * @throws DAOException 
	 */
	void deleteCompany(int companyID) throws DAOException;
	
	
	/**
	 * @return
	 * @throws DAOException 
	 */
	ArrayList<Company> getAllCompanies() throws DAOException;
	
	
	/**
	 * @param companyID
	 * @return
	 * @throws DAOException 
	 */
	Company getOneCompany(int companyID) throws DAOException;
	
	
}
