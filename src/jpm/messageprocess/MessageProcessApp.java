package jpm.messageprocess;
import java.io.File;
import java.util.Scanner;

import jpm.messageprocess.service.SaleProcesser;
/**
 * 
 * @author Sujana Ummani
 * @since 11-12-2017
 * Class Name:MessageProcessApp
 * This class contains the main program to run the application.
 * Requires a test input file containing sales message details.
 *
 */
public class MessageProcessApp {

	public static void main(String args[]) {

		SaleProcesser saleProc = new SaleProcesser();

		try {
			//Assuming input file is available on user desktop
			File desktop = new File(System.getProperty("user.home"), "/Desktop"); 
		    Scanner file = new Scanner(new File(desktop, "textInput.txt.txt"));
		    //Reading line by line from input file and calling appropriate processing methods
		   // on that input message
			while (file.hasNextLine()){
				//This method will process the message
				saleProc.processSale(file.nextLine());
				//This method will generate reports for sales processing logs
				saleProc.processSalesLog();
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
