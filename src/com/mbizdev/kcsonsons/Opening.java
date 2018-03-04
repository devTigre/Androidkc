package com.mbizdev.kcsonsons;

public class Opening {

	private String day, open1, close1, open2, close2;
	
	
	//constructor
	
	public Opening(String day, String open1, String close1, String open2, String close2)
	{
	this.day=day;
	this.open1=open1;
	this.close1=close1;
	this.open2=open2;
	this.close2=close2;
	}
	
	public String getDay() {
		return day;
	}


	public String getOpen1() {
		return open1;
	}


	public String getClose1() {
		return close1;
	}


	public String getOpen2() {
		return open2;
	}


	public String getClose2() {
		return close2;
	}
}