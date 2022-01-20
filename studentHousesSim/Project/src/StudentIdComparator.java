import java.util.Comparator;
/**
 * 
 * Custom StudentIdComparator which implements Comparator interface.
 * @author huzeyf
 *
 */
public class StudentIdComparator implements Comparator<Student> {
	@Override
	public int compare(Student s1,Student s2) {
		if ( s1.id > s2.id ) {
			return 1;
		}
		else if (s1.id < s2.id) {
			return -1;
		}
		return 0;
	}
}
