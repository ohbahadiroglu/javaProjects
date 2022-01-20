import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Scanner;
/**
 * 
 * Runnable main class of Project1.
 * @author huzeyf
 *
 */
public class project1main {
	
	/**
	 * Main method.
	 * Takes two arguments : input.txt output.txt
	 * Takes the input , manipulates it and writes to an output file.
	 * @param args input.txt output.txt
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		LinkedList<House> houses = new LinkedList<House>();         // Stores houses.
		LinkedList<Student> students = new LinkedList<Student>();	// Stores students.
		
		/*
		 *!!!!!!!! beginning of input file reading,creating and storing custom class objects. !!!!!!!!!!!!!!
		 */
		
		File inputText = new File(args[0]);
		PrintStream outstream = new PrintStream(args[1]);
		Scanner fileReader = new Scanner(inputText);
		
		while (fileReader.hasNextLine()) {
			
			String line = fileReader.nextLine();
			String[] lineElements = line.split(" ");

			
			if ( lineElements[0].equals("h") ) {
				
				int id = Integer.parseInt(lineElements[1]);
				int duration = Integer.parseInt(lineElements[2]);
				double rating = Double.parseDouble(lineElements[3]);
				
				House temp = new House(id,duration,rating);
				houses.add(temp);	
			}
			
			else if ( lineElements[0].equals("s") ) {
				
				int id = Integer.parseInt(lineElements[1]);
				String name = lineElements[2];
				int duration = Integer.parseInt(lineElements[3]);
				double rating = Double.parseDouble(lineElements[4]);
				
				Student temp = new Student(id,name,duration,rating);
				students.add(temp);
				
			}
		}
		
		fileReader.close();
		/*
		 *		!!!!!!!!!!!!!! ending of input file reading,creating and storing custom class objects !!!!!!!!!!!!!!!
		 */
		
		houses.sort(new HouseIdComparator());
		students.sort(new StudentIdComparator());
		
		int totalSemester = 0;
		for (Student student : students) {				// finds total semester amount that will process. 			
			if ( student.duration > totalSemester) {
				totalSemester=student.duration;
			}
		}
		
		for (int i = 0 ; i < totalSemester ; i++) {			
			
			for (House house : houses) {
				
				if ( house.duration == 0 ) {
		
					for (Student student : students) {
						
						if ( ( student.duration > 0 ) && ( student.isAllocated == false ) ){
							
							if ( house.rating >= student.ratingExpectation ) {	
								
								house.duration = student.duration;
								student.isAllocated = true;
								
								break;
							}
						}
					}	
				}
			}
			for (House housee : houses) {					// updates houses s durations to be allocable.
				if (housee.duration >= 1 ) {
					housee.duration -= 1;
				}
			}
			for (Student studentt : students) {				// 	updates students s durations ...														
				if ( studentt.duration >= 1 ) {				//	(remaining	semester) to be graduated.
					studentt.duration -= 1;
				}
			}
				
		}
		
		
		for (Student student : students) {
			if (student.isAllocated == false) {
				outstream.println(student.name);			// writes not allocated students's  ...
			}												//	names to a file  "output.txt"
		}
		
		outstream.close();
	}

}
