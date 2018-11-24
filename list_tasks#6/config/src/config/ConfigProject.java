package config;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigProject {

	private static final Logger log = Logger.getLogger(ConfigProject.class.getName()); 	
	private int monthsAncient;
	private boolean markQuery;
	
	private static ConfigProject instance;
	
	private ConfigProject() { 
		init();
	}
	
	public static ConfigProject getInstance() {
		if(instance == null) {
			instance = new ConfigProject();
		}
		return instance;
	}
	
	public int getMonthsAncient() {
		return monthsAncient;
	}
	
	public boolean getMarkQuery() {
		return markQuery;
	}
	
	private void init() {
		Properties pr = new Properties();
		try {
			pr.load(new FileInputStream("F:\\java_course_2018\\list_tasks#6\\config\\config.properties"));
			monthsAncient = Integer.valueOf(pr.getProperty("monthsAncient"));
			markQuery = Boolean.valueOf(pr.getProperty("markQuery"));		
		} catch(Exception e) {
			log.log(Level.INFO, "Error init config", e);
			defaultValues();
		}		
	}	
	
	private void defaultValues() {
		monthsAncient = 6;
		markQuery = true;
	}
}