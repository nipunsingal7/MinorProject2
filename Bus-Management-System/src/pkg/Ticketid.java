package pkg;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import pkg.Mailclass;

public class Ticketid {
	
	static Random rm1 =new Random();
	
	
	public static String tnumber(String to,String text1)
	{
		SimpleDateFormat ftm=new SimpleDateFormat("yyddMM");
		Date dt= new Date();
		String date=ftm.format(dt);
		
		int n1=rm1.nextInt(30000)%70000+10000;
		
		String id=n1+date;
		
		String text="Your Ticketno:"+" "+id+"\n\n"+text1;
		
		
		Mailclass.sendmail(to, text);
		
		return(id);
		
	}
	
	

}
