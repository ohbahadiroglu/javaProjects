
public class Edge {

	private Vertice source;
	private Vertice destination;
	private int distance;
	
	public Edge(Vertice first, Vertice second, int distance) {
		this.source = first;
		this.destination = second;
		this.distance = distance;
	}

	public Vertice getSource() {
		return source;
	}

	public void setSource(Vertice source) {
		this.source = source;
	}

	public Vertice getDestination() {
		return destination;
	}

	public void setDestination(Vertice destination) {
		this.destination = destination;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
	public String toString() {
		return source.getName() +"-->"+ destination.getName()+" distance "+this.distance;
	}
	
	
	
}