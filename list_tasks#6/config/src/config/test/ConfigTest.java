package config.test;

import config.ConfigProject;

public class ConfigTest {

	public static void main(String[] args) { 	
		System.out.println(ConfigProject.getInstance().getMonthsAncient());		
		System.out.println(ConfigProject.getInstance().getMarkQuery());		
	}	
}
