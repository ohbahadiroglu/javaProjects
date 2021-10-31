
package question;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {


	public static void main(String args[]) {

		Customer[] customers;
		Operator[] operators;

		int C, O, N;

		File inFile = new File(args[0]);  // args[0] is the input file
		File outFile = new File(args[1]);  // args[1] is the output file
		try {
			PrintStream outstream = new PrintStream(outFile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		Scanner reader;
		try {
			reader = new Scanner(inFile);
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find input file");
			return;
		}

		C = reader.nextInt();
		O = reader.nextInt();
		N = reader.nextInt();

		customers = new Customer[C];
		operators = new Operator[O];

		//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
		
		PrintStream outstream1;
		try {
		        outstream1 = new PrintStream(outFile);
		}catch(FileNotFoundException e2) {
		        e2.printStackTrace();
		        return;
		}
		
//		for (int i = 0 ; i != customers.length ; i++) {
//		    customers[i] = new Customer();
//		}
//		for (int i = 0 ; i != operators.length ; i++) {
//		    operators[i] = new Operator();
//		}
		
		int idCustomerCounter=0;
		int idOperatorCounter=0;
		
		for (int i = 0 ; i <= N ; i ++) {
			String line = reader.nextLine();
			String[] linetokens= line.split(" ");
			
			
			if (linetokens[0].equals("1")) {
				String name=linetokens[1];
				int age=Integer.parseInt(linetokens[2]);
				int operatorID=Integer.parseInt(linetokens[3]);
				double limAmount=Double.parseDouble(linetokens[4]);
				Operator operator=operators[operatorID];
				customers[idCustomerCounter]=new Customer(idCustomerCounter,name,age,operator,limAmount);
				idCustomerCounter += 1 ;
				
									
			}
			else if (linetokens[0].equals("2")) {
				double tCharge=Double.parseDouble(linetokens[1]);
				double mCost=Double.parseDouble(linetokens[2]);
				double nCharge=Double.parseDouble(linetokens[3]);
				int disRate=Integer.parseInt(linetokens[4]);
				operators[idOperatorCounter]=new Operator(idOperatorCounter,tCharge,mCost,nCharge,disRate);
				idOperatorCounter+=1;
								
			}
			else if (linetokens[0].equals("3")) {
				int firstCust=Integer.parseInt(linetokens[1]);
				int secondCust=Integer.parseInt(linetokens[2]);
				int time=Integer.parseInt(linetokens[3]);
				customers[firstCust].talk(time,customers[secondCust]);
				

			}
			else if (linetokens[0].equals("4")) {
				int firstCustID=Integer.parseInt(linetokens[1]);
				int secondCustID=Integer.parseInt(linetokens[2]);
				int quantity=Integer.parseInt(linetokens[3]);
				customers[firstCustID].message(quantity,customers[secondCustID]);
			}
			else if (linetokens[0].equals("5")) {
				int custID=Integer.parseInt(linetokens[1]);
				double megaBytes=Double.parseDouble(linetokens[2]);
				customers[custID].connection(megaBytes);
				
			}
			else if (linetokens[0].equals("6")) {
				int custID=Integer.parseInt(linetokens[1]);
				double pAmount=Double.parseDouble(linetokens[2]);
				customers[custID].getBill().pay(pAmount);
			}
			else if (linetokens[0].equals("7")) {
				int custID=Integer.parseInt(linetokens[1]);
				int opID=Integer.parseInt(linetokens[2]);
				Operator newOpt=operators[opID];
				customers[custID].setOperator(newOpt);
								
			}
			else if(linetokens[0].equals("8")) {
				int custID=Integer.parseInt(linetokens[1]);
				double newLimitingAmount=Double.parseDouble(linetokens[2]);
				customers[custID].getBill().changeTheLimit(newLimitingAmount);
			}
			else {
				continue;
			}
						
		}
		for (int i = 0; i<O ; i++) {
			double opServicedMb=operators[i].servicedMegaBytes;
			int opServicedMessage=operators[i].messageServiced;
			int opServicedTime=operators[i].timeServiced;		
			outstream1.printf("Operator "+i+" : "+opServicedTime+" "+opServicedMessage+" "+"%.2f"+"\n",opServicedMb);
					
		}
		int mostTalkedTime=0;
		int mostSentMessages=0;
		double mostTotalMb=0;
		String mostTalkedCustomer=customers[0].name;
		String mostMessagedCustomer=customers[0].name;
		String mostTotalMbUser=customers[0].name;
		for (int i = 0; i<C ; i++) {
			double totalPaid=customers[i].getBill().paidBill;
			double currentDebt=customers[i].getBill().getCurrentDebt();
			outstream1.printf("Customer "+i+" : "+"%.2f"+" "+"%.2f"+"\n",totalPaid,currentDebt);
			if (customers[i].talkedTime>mostTalkedTime) {
				mostTalkedTime=customers[i].talkedTime;
				mostTalkedCustomer=customers[i].name;
			}
			if (customers[i].messagesSent>mostSentMessages) {
				mostMessagedCustomer=customers[i].name;
				mostSentMessages=customers[i].messagesSent;
			}
			if (customers[i].totalMb>mostTotalMb) {
				mostTotalMbUser=customers[i].name;
				mostTotalMb=customers[i].totalMb;
			}
		}
		outstream1.println(mostTalkedCustomer+" : "+mostTalkedTime);
		outstream1.println(mostMessagedCustomer+" : "+mostSentMessages);
		outstream1.printf(mostTotalMbUser+" : "+"%.2f",mostTotalMb);

		reader.close();
		outstream1.close();
		
		
		
		//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
	} 
}

