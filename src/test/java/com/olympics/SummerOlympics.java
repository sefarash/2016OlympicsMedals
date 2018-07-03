package com.olympics;

public class SummerOlympics {
	
	String country;
	int gold;
	int silver;
	int bronze;
	int total;
	
	public SummerOlympics(String country, int gold, int silver, int bronze, int total) {
		super();
		this.country = country;
		this.gold = gold;
		this.silver = silver;
		this.bronze = bronze;
		this.total = total;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	public int getSilver() {
		return silver;
	}
	public void setSilver(int silver) {
		this.silver = silver;
	}
	public int getBronze() {
		return bronze;
	}
	public void setBronze(int bronze) {
		this.bronze = bronze;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	
	
	

}
