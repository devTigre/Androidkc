package com.mbizdev.kcsonsons;

public class KCMenuItem {
	
	private String category, item, flag, orderCount,desc, centerRed, centerBlack, price, centerBig, smallText;
	

	//constructor
	public KCMenuItem(String category, String item, String flag, String orderCount, String desc, String centerRed, String centerBlack,String price, String centerBig, String smallText)
	{
	this.category=category;
	this.item=item;
	this.flag=flag;
	this.orderCount=orderCount;
	this.desc=desc;
	this.centerRed=centerRed;
	this.centerBlack=centerBlack;
	this.price=price;
	this.centerBig=centerBig;
	this.smallText=smallText;
	}
	
 	
	public String getCategory() {
		return category;
	}

	public  String getItem() {
		return item;
	}

	public String getFlag() {
		return flag;
	}
	
	public  String getOrderCount() {
		return orderCount;
	}
	public String getDesc() {
		return desc;
	}
	
	public String getCenterRed() {
		return centerRed;
	}
	public String getCenterBlack() {
		return centerBlack;
	}
	public String getPrice() {
		return price;
	}
	
	public String getCenterBig() {
		return centerBig;
	}
	public String getSmallText() {
		return smallText;
	}

}
