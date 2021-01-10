package app.core.clientsFacade;

import app.core.dao.CompaniesDAO;
import app.core.dao.CouponsDAO;
import app.core.dao.CustomersDAO;
import app.core.exceptions.FacadeException;

public abstract class ClientFacade {

	public CompaniesDAO companiesDAO;
	
	public CustomersDAO customersDAO;
	
	public CouponsDAO couponsDAO;
	
	public abstract boolean login(String email, String password) throws FacadeException;
	
}
