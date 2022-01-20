import java.util.Comparator;

public class VerticeComparator implements Comparator<Vertice>{

	@Override
	public int compare(Vertice o1, Vertice o2) {

		return (o1.getShortestDistance() - o2.getShortestDistance());
		
	}
	

}
