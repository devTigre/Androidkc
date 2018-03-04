package com.mbizdev.kcsonsons;

import java.util.ArrayList;

public class KCMenuItemList {
	
public ArrayList<KCMenuItem> MenuItemList = new ArrayList<KCMenuItem>();
	
	private KCMenuItem[] item = new KCMenuItem[MenuItemList.size()];
	
	public KCMenuItemList() {
		
		for (int i=0; i< MenuItemList.size(); i++) {
			
			item[i] = new KCMenuItem(getCategory(i), getItem(i), getFlag(i), getOrderCount(i), getDesc(i),  getCenterRed(i), getCenterBlack(i),getPrice(i), getCenterBig(i), getSmallText(i) );
				
		}	
	}



	public ArrayList<KCMenuItem> getMenuItemList() {
		return MenuItemList;
	}

	public KCMenuItem[] getItem() {
		return item;
	}
	
	public String getItem(int i) {
		KCMenuItem item = MenuItemList.get(i);
		String itemName = item.getItem();
		return itemName;
	}

	public String getFlag(int i) {
		KCMenuItem item = MenuItemList.get(i);
		String flag = item.getFlag();
		return flag;
	}
	
	public String getOrderCount(int i) {
		KCMenuItem item = MenuItemList.get(i);
		String orderCount = item.getOrderCount();
		return orderCount;
	}
	
	public String getCategory(int i) {
		KCMenuItem item = MenuItemList.get(i);
		String category = item.getCategory();
		return category;
	}
	public String getDesc(int i) {
		KCMenuItem item = MenuItemList.get(i);
		String desc = item.getDesc();
		return desc;
	}
	
	public String getCenterRed(int i) {
		KCMenuItem item = MenuItemList.get(i);
		String centerRed = item.getCenterRed();
		return centerRed;
	}
	
	public String getCenterBlack(int i) {
		KCMenuItem item = MenuItemList.get(i);
		String centerBlack = item.getCenterBlack();
		return centerBlack;
	}
	
	public String getPrice(int i) {
		KCMenuItem item = MenuItemList.get(i);
		String price = item.getPrice();
		return price;
	}
	public String getCenterBig(int i) {
		KCMenuItem item = MenuItemList.get(i);
		String centerBig = item.getCenterBig();
		return centerBig;
	}
	public String getSmallText(int i) {
		KCMenuItem item = MenuItemList.get(i);
		String smallText = item.getSmallText();
		return smallText;
	}
}
