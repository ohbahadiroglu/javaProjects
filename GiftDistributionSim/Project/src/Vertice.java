public abstract class Vertice {
    private String type;
    private int capacity;
    private int level;
    private  int visitedNeighb = 0;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getVisitedNeighb() {
        return visitedNeighb;
    }

    public void setVisitedNeighb(int visitedNeighb) {
        this.visitedNeighb = visitedNeighb;
    }

    public void incrementVisitedNeighb(){
        this.visitedNeighb++;
    }

    public String toString(){
        return this.type;
    }
}
