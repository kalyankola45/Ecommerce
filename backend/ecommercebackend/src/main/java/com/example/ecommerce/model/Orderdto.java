package com.example.ecommerce.model;

import java.util.Date;
import java.util.List;

public class Orderdto {
	
	  private int orderId;
	    private String paymentType;
	    private String orderStatus;
	    private Date expectedDate;
	    private Adress adress;
	    private List<Orderitem> orderitems;
      private long totalprice;
	    public long getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(long totalprice) {
		this.totalprice = totalprice;
	}

		// Getters and Setters
	    public int getOrderId() {
	        return orderId;
	    }

	    public void setOrderId(int orderId) {
	        this.orderId = orderId;
	    }

	    public String getPaymentType() {
	        return paymentType;
	    }

	    public void setPaymentType(String paymentType) {
	        this.paymentType = paymentType;
	    }

	    public String getOrderStatus() {
	        return orderStatus;
	    }

	    public void setOrderStatus(String orderStatus) {
	        this.orderStatus = orderStatus;
	    }

	    public Date getExpectedDate() {
	        return expectedDate;
	    }

	    public void setExpectedDate(Date expectedDate) {
	        this.expectedDate = expectedDate;
	    }

	    public Adress getAdress() {
	        return adress;
	    }

	    public void setAdress(Adress adress) {
	        this.adress = adress;
	    }

		public List<Orderitem> getOrderitems() {
			return orderitems;
		}

		public void setOrderitems(List<Orderitem> orderitems) {
			this.orderitems = orderitems;
		}

	
}
