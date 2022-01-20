
/**
 * 
 *  Class that represents House objects.
 *  
 * @author huzeyf
 *
 */
public class Student {
	
	int id;
	String name;
	int duration;
	double ratingExpectation;
	boolean isAllocated = false;
	
	/**
	 * Constructor of student class.
	 * Takes four parameters: int id, String name , int duration , double ratingE .
	 * @param id int unique student id.
	 * @param name String unique student name.
	 * @param duration int student s remaining semester.
	 * @param ratingE double rating expectation of student.
	 */
	public Student(int id, String name, int duration, double ratingE) {
		this.id=id;
		this.name=name;
		this.duration=duration;
		this.ratingExpectation=ratingE;
		
	}
	/**
	 * Method returns string represantation of student objects.
	 * @return this.name
	 */
	public String toString() {
		return this.name;
	}
}
