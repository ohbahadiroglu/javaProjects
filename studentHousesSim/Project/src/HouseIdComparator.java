import java.util.Comparator;
/**
 * 
 * Custom HouseIdComparator which implements Comparator interface.
 * @author huzeyf
 *
 */
public class HouseIdComparator implements Comparator<House> {
	@Override
	public int compare(House h1,House h2) {
		if ( h1.id > h2.id ) {
			return 1;
		}
		else if (h1.id < h2.id) {
			return -1;
		}
		return 0;
	}
}
