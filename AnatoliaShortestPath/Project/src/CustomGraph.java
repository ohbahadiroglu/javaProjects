import java.util.Hashtable;
import java.util.LinkedList;

public class CustomGraph {
	private int vertices;
	private Hashtable< Vertice ,LinkedList<Edge> > adjTable;
	
	public CustomGraph() {
		
		this.adjTable = new Hashtable<Vertice,LinkedList<Edge>>();
	}
	
	public void addAdjTable(Vertice v) {
		this.adjTable.put( v, new LinkedList<Edge>() );
		
	}
	
	public void addEdge(Vertice first,Vertice second,int distance) {
		Edge edge = new Edge(first,second,distance);
		LinkedList<Edge> tmp= this.adjTable.get(first);
		tmp.add(edge);
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Vertice v : this.adjTable.keySet() ) {
			sb.append(this.adjTable.get(v)+"\n");
		}
		
		return sb.toString();
	}

	public Hashtable<Vertice, LinkedList<Edge>> getAdjTable() {
		return adjTable;
	}
	
}
