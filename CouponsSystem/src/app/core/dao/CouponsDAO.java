package app.core.dao;

import java.util.ArrayList;

import app.core.beans.Coupon;
import app.core.exceptions.CouponSystemDAOException;


public interface CouponsDAO {



	// boolean isCouponExists(Coupon coupon) throws CouponSystemDAOException;
	
	

	void addCoupon(Coupon coupon) throws CouponSystemDAOException;
	
	

	void updateCoupon(Coupon coupon) throws CouponSystemDAOException;
	
	

	void deleteCoupon(int couponID) throws CouponSystemDAOException;
	
	

	ArrayList<Coupon> getAllCoupons() throws CouponSystemDAOException;
	
	

	Coupon getOneCoupon(int couponID) throws CouponSystemDAOException;
	
	
	
	void addCouponPurchase(int customerID, int couponID);
	
	
	
	void deleteCouponPurchase(int customerID, int couponID);
	
	
	
	
	
	
}
