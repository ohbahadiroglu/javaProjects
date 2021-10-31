
package question;

public class Bill {

	//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
	private double limitingAmount;
	private double currentDebt;
	public double paidBill;
	
	
	
	public Bill(double limitingAmount) {
		currentDebt=0;
		paidBill=0;
		this.limitingAmount=limitingAmount;
	}
	
	public boolean check(double amount) {
		if ((this.currentDebt+amount)>= limitingAmount) {
			return false;		
		}
		return true;		
	}
	
	public void add(double amount) {
		this.currentDebt += amount;
	}
	public void pay(double amount) {
		if (this.currentDebt>=amount) {
			this.paidBill+=amount;
			this.currentDebt -= amount;
		}
		else {
			this.paidBill+=this.currentDebt;
			this.currentDebt=0;

			
		}
	}
	public void changeTheLimit(double amount) {
		if (this.currentDebt<=amount) {
			this.limitingAmount = amount;
		}
	}
	public double getLimitingAmount(){
		return limitingAmount;		
	}
	public double getCurrentDebt() {
		return currentDebt;
	}
	
	
	
	



	//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
}

