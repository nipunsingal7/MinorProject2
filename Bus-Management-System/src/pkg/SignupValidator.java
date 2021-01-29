package pkg;


public class SignupValidator {
	
	protected static int namevalidate(String name)
	{   int z=0;
		 
	  if(name.length()<6 || name.length()>30)                             //check length
	    {  return(z=1);   }
	  
	  
	//check starting of user name
	  if(!(name.charAt(0)>=65 && name.charAt(0)<=90 || name.charAt(0)>=97 && name.charAt(0)<=122 ) )       
	      {return(z=1);}                                                
	     
		for(int a=1;a<name.length();a++)                                  //check rest user name
			
		{   int q=name.charAt(a);
		    if(!(q>=65 && q<=90 || q>=97 && q<=122 || q>=48 && q<=57 || q==95) )
			{return(z=1);}
		}
		
		return(z);
	
	}
	
	
	protected static int passwdvalidate(String passwd)
	{
		int p=0,flag=0;
		
		if(passwd.length()<8 || passwd.length()>18)                //check length
		{return(p=1);}
		
		if(passwd.contains(" "))                                  //check white spaces
		{return(p=1);}
		
		for(int a=0;a<10;a++)                                    //check the digits
		{
			String numb=Integer.toString(a);
			if(passwd.contains(numb))
			{flag++;
			break;}
		}
		
		if(flag==0)
		{return(p=1);}
		
		flag=0;
		// check special character
	
		if(!(passwd.contains("!") || passwd.contains("@") || passwd.contains("`") || passwd.contains("~")
		   || passwd.contains("#") || passwd.contains("$") || passwd.contains("%") || passwd.contains("^")
		   || passwd.contains("&") || passwd.contains("*") || passwd.contains("(") || passwd.contains(")")
		   || passwd.contains("_") || passwd.contains("-") || passwd.contains("=") || passwd.contains("+")
		   || passwd.contains(":") || passwd.contains("?") || passwd.contains(",") || passwd.contains(".")
		   || passwd.contains("/") || passwd.contains("'") || passwd.contains("]") || passwd.contains("[")))
			
		{return(p=1);}
		   
		
		//checking upper case alphabets
		for(int l=0;l<passwd.length();l++)
		{  
			int value=passwd.charAt(l);
			if((value>=65 && value<=90))
			{
				flag++;
				break;
			}
		}
		
		if(flag==0)
		{return(p=1);}
		
		flag=0;
		
		
		//checking lower case alphabets
		for(int l=0;l<passwd.length();l++)
		{  
			int value=passwd.charAt(l);
			if((value>=97 && value<=122))
			{
				flag++;
				break;
			}
		}
		
		if(flag==0)
		{return(p=1);}
		
		

        return(p);		
		
	}
	
	
	protected static int emailcheck(String mail)
	{ int m=0;
		
	    if (!(mail.endsWith("@gmail.com") || mail.endsWith("@yahoo.com") || mail.endsWith("@hotmail.com") || mail.endsWith("@rediffmail.com")
	    		|| mail.endsWith("@outlook.com")))
	    {return(m=1);}
	
		
	  return(m);	
	}
	
	
	protected static int phonecheck(String phone)
	
	{  int k=0;
		
		if(phone.length()<10 || phone.length()>10)
		{
			return(k=1);
		}
		
		if(phone.charAt(0)=='0' || phone.charAt(0)=='+')
		{return(k=1);}
		
		
		try { Long.parseLong(phone);	}
		
		catch(Exception e)
		{return(k=1); }
		
		return(k);
		
	}

}
