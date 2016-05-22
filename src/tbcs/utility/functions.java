package tbcs.utility;

import java.util.Random;

public class functions {
	
	public static String createPassword(int length){
		StringBuilder password = new StringBuilder();
		
		Random random = new Random();
		
		int ctr = 0;
		char c;
		while(ctr < length){
			c = (char) (random.nextInt(26) + 'a');
			password.insert(ctr, c);
			System.out.println("Password: " + password.toString());
			ctr++;
		}
		
		return password.toString();
		
	}

}
