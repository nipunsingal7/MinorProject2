package pkg;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OtpVerification {
	
	
	public static int timevalidation(Date d1)
	{
	    try{
	    	
	    SimpleDateFormat ftm=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	    
	    Date date=new Date();
	    String dt1=ftm.format(date);
	    Date date1=ftm.parse(dt1);
	  
		
	    long diff = date1.getTime() - d1.getTime();
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffDays = diff / (24 * 60 * 60 * 1000);	
        
        if(diffDays>0 || diffHours>0 || diffMinutes>=5)
        {return 1;}
        
	    }
	    catch(Exception e)
		   {System.out.println(e);}
	    
	   
	    
	    
		return 0;
	}
	
	
	
	
	

	
	

}
