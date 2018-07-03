package com.olympics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OlympicsProjects {
	
	static WebDriver driver;
	String url ="https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table.";

	@BeforeClass // runs once for all tests
	public void setUp() {
		
		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// driver.manage().window().fullscreen();
		driver.get(url);
		
	}
	
	@Test(priority=1)
	public void sortTest() {
		List<WebElement> firstColumn = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[1]"));
		
		ArrayList<Integer> actualList = new ArrayList<>();
		for (int i = 0; i < firstColumn.size()-1; i++) {
			actualList.add(Integer.parseInt(firstColumn.get(i).getText()));
			
		}
		System.out.println(actualList);
		
		SortedSet<Integer>expectedList = new TreeSet<>(actualList);
		System.out.println(expectedList);
		Assert.assertEquals(actualList, expectedList);
		
		driver.findElement(By.xpath("//th[@class='headerSort'][contains(text(),'NOC')]")).click();
		
		List<WebElement> countries = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a"));
		
		ArrayList<String> actualCountryList = new ArrayList<>();
		
		
		for (int i = 0; i < countries.size()-1; i++) {
			actualCountryList.add(countries.get(i).getText());
			//System.out.println(actualCountryList);
		}
		System.out.println(actualCountryList+" Arraylist");
		SortedSet<String> expectedCountryList = new TreeSet<>(actualCountryList);
		System.out.println(expectedCountryList+"Treeset");
		Assert.assertEquals(actualCountryList, expectedCountryList);
		
		List<WebElement> firstColumn1 = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[1]"));
		ArrayList<Integer> actualList1 = new ArrayList<>(); 
		for (int i = 0; i < firstColumn.size()-1; i++) {
			actualList1.add(Integer.parseInt((firstColumn1.get(i).getText())));
		}
		System.out.println("ex"+expectedList);
		System.out.println("ac"+actualList1);
		Assert.assertFalse(expectedList.equals(actualList1));
		
		
	}
	
	@Test(priority=2)
	public void theMost() {
		driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics");
		System.out.println(medalGold());
		System.out.println(medalSilver());
		System.out.println(medalBronze());
		System.out.println(medalTotal());
		Assert.assertTrue(medalGold().equals("46 - United States")); 
		Assert.assertTrue(medalSilver().equals("37 - United States")); 
		Assert.assertTrue(medalBronze().equals("38 - United States")); 
		Assert.assertTrue(medalTotal().equals("121 - United States")); 
		
	}
	
	@Test(priority=3)
	public void countryByMedal() {
		System.out.println(silver18());
		List<String> list1 = new ArrayList<>();
		list1.add("China");
		list1.add("France");
		Assert.assertEquals(silver18(), list1);
	}
	
	@Test(priority=4)
	public void getIndex() {
		rowAndColumn("Japan");
		System.out.println(rowAndColumn("Japan"));
		Assert.assertEquals(rowAndColumn("Japan"), "6,2");
	}
	
	@Test(priority=5)
	public void testCase5(){
		driver.get(url);
		System.out.println(getSum()); 

		List<String> ls=Arrays.asList("Australia", "Italy"); 
		Assert.assertEquals( getSum(),ls); 
	}
	
public static List<String> getSum(){
		
		List<WebElement> medals= driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[4]"));
		List<WebElement> countries = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a"));
		HashMap<String,Integer> mp= new HashMap<>();
		HashMap<String,Integer> hmp= new HashMap<>();
		
		SortedSet<String> st= new TreeSet<>();
		for(int i=0; i<medals.size()-1; i++){
			mp.put(countries.get(i).getText(), Integer.parseInt(medals.get(i).getText()) );
		}
		for(int i=0; i<medals.size()-1; i++){
			hmp.put(countries.get(i).getText(), Integer.parseInt(medals.get(i).getText()) ); 
		}
		for(Entry <String,Integer> each: mp.entrySet()){
			for(Entry <String,Integer> other: hmp.entrySet()){
				
				if(!(each.getKey().equals(other.getKey()))&&(each.getValue()+other.getValue())==18){
					st.add(each.getKey());
					st.add(other.getKey());
				} 
				}
			}
			
		 
		
		List<String> ls= new ArrayList<>(st);
		
		return ls; 
	}
	
	public List<String> silver18() {
		Set<String> sSet = new HashSet<>();
		List<WebElement> silverList = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[3]"));
		List<WebElement> countries = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a"));
		for (int i = 0; i < silverList.size(); i++) {
			
			if(Integer.parseInt(silverList.get(i).getText())==18) {
				sSet.add(countries.get(i).getText());
			}
		}
	     List<String> list = new ArrayList<>(sSet);
	     return list;
	}
	
	public String rowAndColumn(String country) {
		Map<String, Integer> map = new HashMap<>();
		List<WebElement> countryNames = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a"));
		List<WebElement> data = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[1]"));
		
		for (int i = 0; i < data.size()-1; i++) {
			map.put((countryNames.get(i).getText()), i+1);
	
		}	
	
		return map.get(country)+","+2;
	}
	
	
	
	
	public  String medalGold() {
		SortedMap<Integer,String> sm = new TreeMap<>();
		List<WebElement> goldList = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[2]"));
		List<WebElement> countries = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a"));
		for (int i = 0; i < goldList.size()-1; i++) {
			sm.put(Integer.parseInt(goldList.get(i).getText()), countries.get(i).getText());
		}
			return sm.lastKey()+ " - " + sm.get(sm.lastKey());
	}
	
	public  String medalSilver() {
		SortedMap<Integer,String> sm = new TreeMap<>();
		List<WebElement> silverList = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[3]"));
		List<WebElement> countries = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a"));
		for (int i = 0; i < silverList.size()-1; i++) {
			sm.put(Integer.parseInt(silverList.get(i).getText()), countries.get(i).getText());
		}
			return sm.lastKey()+ " - " + sm.get(sm.lastKey());
	}
	
	public  String medalBronze() {
		SortedMap<Integer,String> sm = new TreeMap<>();
		List<WebElement> bronzeList = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[4]"));
		List<WebElement> countries = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a"));
		for (int i = 0; i < bronzeList.size()-1; i++) {
			sm.put(Integer.parseInt(bronzeList.get(i).getText()), countries.get(i).getText());
		}
			return sm.lastKey()+ " - " + sm.get(sm.lastKey());
	}
	
	public  String medalTotal() {
		SortedMap<Integer,String> sm = new TreeMap<>();
		List<WebElement> totalList = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[5]"));
		List<WebElement> countries = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a"));
		for (int i = 0; i < totalList.size()-1; i++) {
			sm.put(Integer.parseInt(totalList.get(i).getText()), countries.get(i).getText());
		}
			return sm.lastKey()+ " - " + sm.get(sm.lastKey());
	}
	
	

}
