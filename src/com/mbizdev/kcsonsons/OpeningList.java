package com.mbizdev.kcsonsons;

import java.util.ArrayList;

 

public class OpeningList {
	
	public ArrayList<Opening> OpeningList = new ArrayList<Opening>();
	
	private Opening[] day = new Opening[OpeningList.size()];
	
	public OpeningList() {
		
		for (int i=0; i< OpeningList.size(); i++) {
			
			day[i] = new Opening(getDay(i), getOpen1(i), getClose1(i), getOpen2(i), getClose2(i));
				
		}	
	}

	public ArrayList<Opening> getDayList() {
		return OpeningList;
	}

	public Opening[] getDay() {
		return day;
	}

	public String getDay(int i) {
		Opening d = OpeningList.get(i);
		String Day = d.getDay();
		return Day;
	}

	public String getOpen1(int i) {
		Opening d = OpeningList.get(i);
		String Day = d.getDay();
		return Day;
	}
	
	public String getClose1(int i) {
		Opening d = OpeningList.get(i);
		String Day = d.getDay();
		return Day;
	}
	
	public String getOpen2(int i) {
		Opening d = OpeningList.get(i);
		String Day = d.getDay();
		return Day;
	}
	
	public String getClose2(int i) {
		Opening d = OpeningList.get(i);
		String Day = d.getDay();
		return Day;
	}
	
}
