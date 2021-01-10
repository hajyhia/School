package app.core.clientsFacade;

import app.core.beans.Company;
import app.core.beans.Coupon;
import app.core.dao.impel.CompaniesDBDAO;
import app.core.exceptions.FacadeException;
import app.core.exceptions.DAOException;

public class CompanyFacade extends ClientFacade {
	
	private int companyID;
	
	public CompanyFacade() throws FacadeException {
		try {
			super.companiesDAO = new CompaniesDBDAO();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FacadeException("AdminFacade Error: initializing CompanyFacade failed", e);
		}
	}

	@Override
	public boolean login(String email, String password) {
		try {
			Company logedCompany = companiesDAO.getLogedInCompany(email, password);
			companyID = logedCompany.getId();
			return true;
		} catch (DAOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void addCoupon(Coupon coupon) {
		
	}
	
	

}
