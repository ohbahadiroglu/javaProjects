
package question;

public class Customer {
	
	//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
	private int ID;
	public String name;
	private int age;
	private Operator operator;
	private Bill bill;
	public int talkedTime;
	public int messagesSent;
	public double totalSpent;
	public double totalMb;
	
	
	public Customer(int ID,String name,int age,Operator operator,double limitingAmount) {
		talkedTime=0;
		messagesSent=0;
		totalSpent=0;
		totalMb=0;
		this.ID=ID;
		this.name=name;
		this.age=age;
		this.operator=operator;
		this.bill= new Bill(limitingAmount);
	}
	
	public void talk(int minute,Customer other){
		if (this.ID != other.ID){
			double tCost=0;
			tCost = this.operator.calculateTalkingCost(minute, this);
			if (this.bill.check(tCost)) {
				this.bill.add(tCost);
				this.talkedTime+=minute;
				other.talkedTime+=minute;
				this.operator.timeServiced+=minute;
				other.operator.timeServiced+=minute;

				
			}
		}
	}
	public void message(int quantity,Customer other) {
		if (this.ID != other.ID){	
			double mCost=0;
			mCost = this.operator.calculateMessageCost(quantity, this, other);
			if (this.bill.check(mCost)) {
				this.bill.add(mCost);
				this.operator.messageServiced+=quantity;
				this.messagesSent+=quantity;
			}
		}
	}
	public void connection(double amount) {
		double nCost=0;
		nCost = this.operator.calculateNetworkCost(amount);
		if (this.bill.check(nCost)) {
			this.bill.add(nCost);
			this.operator.servicedMegaBytes+=amount;
			this.totalMb+=amount;
		}
		
	}
	
	public int getAge() {
		return age;
	}
	public Bill getBill() {
		return bill;
	}
	public Operator getOperator() {
		return operator;
	}
	
	public void setAge(int age) {
		this.age=age;
	}
	public void setBill(Bill bill) {
		this.bill=bill;
	}
	public void setOperator(Operator operator) {
		this.operator=operator;
	}
	
	


	//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
}

