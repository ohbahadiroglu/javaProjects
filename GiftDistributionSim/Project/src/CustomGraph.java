import java.util.Hashtable;
import java.util.LinkedList;

public class CustomGraph {
    Hashtable< Vertice , Hashtable<Vertice,Edge> > adjTable;

    public CustomGraph(){
        this.adjTable = new Hashtable< Vertice,Hashtable<Vertice,Edge> >();
    }

    public void addAdjTable(Vertice v){
        this.adjTable.put(v , new Hashtable<Vertice,Edge>());
    }

    public void addEdge(Vertice src, Vertice dst, int capacity){
        Edge edge = new Edge(src, dst, capacity);
        Edge edge2 = new Edge(dst, src, 0);
        Hashtable<Vertice,Edge> tmpTable = this.adjTable.get(src);
        Hashtable<Vertice,Edge> tmpTable2 = this.adjTable.get(dst);

        tmpTable.put(dst,edge);
        tmpTable2.put(src,edge2);

    }
}
