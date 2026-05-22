package com.nt.fectory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

import com.nt.comps.Courier;
import com.nt.comps.Flipkart;

public class FlipkarFectory 
{
	private static Properties props;
	static
	{
		
		try
		{
			// load the properties files using stream
			InputStream is = new FileInputStream("src/main/java/com/nt/commons/info.properties");
			
			//create properteis class object
			 props = new Properties();
			props.load(is);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}  //static block
	
//static fectory methode having to the given courier object
	public static Flipkart getIntance()
	{
		//get the dependece class name frome the properties class name 
		String couriesClassName= props.getProperty("courier.name");
		
		Courier courier =null;
		//laod the dependecnt class
	
		try
		{
			Class c = Class.forName(couriesClassName);
//			//create the obje for depencence class
//		courier= (Courier) c.newInstance();	 //  --> deprected methode for (Java9)	
			
			// get all constructore of the loaded class 
			Constructor<Courier> cons[] = c.getDeclaredConstructors();
			
			
			//create the object for dependece class using 0 - param contructore
			courier = (Courier)cons[0].newInstance();
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		//creat target clas object
		
		Flipkart fpkt = new Flipkart();
		
		fpkt.setCourier(courier);
		
		return fpkt;
	}
	
}
