
package question;

public class Operator {
	//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
	public int ID;
	private double messageCost,talkingCharge,networkCharge;
	private int discountRate;
	public int timeServiced;
	public int messageServiced;
	public double servicedMegaBytes;
	
	
	public Operator(int ID,double talkingCharge,double messageCost,double networkCharge,int discountRate) {
		timeServiced=0;
		messageServiced=0;
		this.ID=ID;
		this.talkingCharge=talkingCharge;
		this.messageCost=messageCost;
		this.networkCharge=networkCharge;
		this.discountRate=discountRate;
	}
	
	public double calculateTalkingCost(int minute,Customer customer) {
		
		double tCost=minute*this.talkingCharge;
		
		if (customer.getAge()<18 || customer.getAge()>65) {
			tCost*=(100-this.discountRate) / (double) 100;
		}
		
		return tCost;
	}
	
	public double calculateMessageCost(int quantity, Customer customer, Customer other) {
		double mCost=quantity*this.messageCost;
		
		if (customer.getOperator() == other.getOperator()) {
			mCost*=(100-this.discountRate)/(double) 100;
		}
		
		return mCost;		
	}
	public  double calculateNetworkCost(double amount) {
		double nCost=amount*this.networkCharge;
		return nCost;
	}
	
	public double getTalkingCharge() {
		return talkingCharge;
	}
	public double getMessageCost() {
		return messageCost;
	}
	public double getNetworkCharge() {
		return networkCharge;
	}
	public int getDiscountRate() {
		return discountRate;
	}
	



	//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
}

