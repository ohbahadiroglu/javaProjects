/**
 * Class that represents House objects.
 * @author huzeyf
 *
 */
public class House {
	int id;
	int duration;
	double rating;
	Student student;
	boolean availability;
	
	/**
	 * Constructor of house class.
	 * Takes three parameter : int id , int duration , double rating.
	 * @param id house unique id
	 * @param duration remaining semester that house will be allocable after.
	 * @param rating quality of the house
	 */
	public House(int id, int duration, double rating) {
		this.id=id;
		this.duration=duration;
		this.rating=rating;
		if (duration == 0) {
			this.availability=true;
		}else {
			this.availability=false;
		}
		
		
	}
	/**
	 * Method returns string representation of a house instance.
	 * @return this.id as a string. 
	 */
	public String toString(){
		return String.valueOf(this.id);
	}
}
