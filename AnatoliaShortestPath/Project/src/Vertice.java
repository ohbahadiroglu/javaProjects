
public class Vertice {
	
	private String name ;
	private int shortestDistance = (int) Double.POSITIVE_INFINITY;
	private Vertice preV;
	private Vertice parent;
	private boolean inTree=false;  
	private int primDistance = (int) Double.POSITIVE_INFINITY;

	
	public Vertice (String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getShortestDistance() {
		return shortestDistance;
	}

	public void setShortestDistance(int shortestDistance) {
		this.shortestDistance = shortestDistance;
	}

	public Vertice getPreV() {
		return preV;
	}

	public void setPreV(Vertice preV) {
		this.preV = preV;
	}
	public String toString() {
		return this.name;
	}

	public boolean isInTree() {
		return inTree;
	}

	public void setInTree(boolean inTree) {
		this.inTree = inTree;
	}

	public int getPrimDistance() {
		return primDistance;
	}

	public void setPrimDistance(int primDistance) {
		this.primDistance = primDistance;
	}

	public Vertice getParent() {
		return parent;
	}

	public void setParent(Vertice parent) {
		this.parent = parent;
	}
}
