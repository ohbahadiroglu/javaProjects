import java.util.Comparator;

public class VerticeComparator2 implements Comparator<Vertice>{

	@Override
	public int compare(Vertice o1, Vertice o2) {

		return (o1.getPrimDistance() - o2.getPrimDistance());
		
	}
	

}
