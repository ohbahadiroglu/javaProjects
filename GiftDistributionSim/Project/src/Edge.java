public class Edge {
    private Vertice source;
    private Vertice destination;
    private int capacity;
    private int weight = 0 ;

    public Edge(Vertice src, Vertice dst, int capacity){
        this.source = src;
        this.destination = dst;
        this.capacity = capacity;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
