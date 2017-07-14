package me.alexandreh.fr.mananaheul.utils;

public class IntegerCheck {

	public static boolean IsInteger(String number){
		try{
	    	int num = Integer.parseInt(number);
	    	return true;
		}catch(NumberFormatException e){
	    	return false;
		}
	}
}

