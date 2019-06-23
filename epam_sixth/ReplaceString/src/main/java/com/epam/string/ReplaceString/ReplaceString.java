package com.epam.string.ReplaceString;

import org.junit.After;

public class ReplaceString {
	
	public String ReplaceAA(String string) {
		
		String old_string = string.substring(0,1).replace("A","");
		old_string += string.substring(1, 2).replace("A","");
		old_string += string.substring(2);
		return(old_string);
		
	}
}
