
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import java.util.*;

public class project4main {
    public static void main(String[] args) throws FileNotFoundException {


        ArrayList<Vehicle> greenTrains = new ArrayList<>();
        ArrayList<Vehicle> redTrains = new ArrayList<>();
        ArrayList<Vehicle> greenDeers = new ArrayList<>();
        ArrayList<Vehicle> redDeers = new ArrayList<>();

        int numOfTotalGift = 0;

        File reader = new File(args[0]);
        Scanner scanner = new Scanner(reader);
        CustomGraph graph = new CustomGraph();

        Vehicle superVehicle = new Vehicle("SuperVehicle",Integer.MAX_VALUE);
        GiftBag superBag = new GiftBag("SuperBag",Integer.MAX_VALUE);
        graph.addAdjTable(superVehicle);
        graph.addAdjTable(superBag);

        int numOfGreenTrains = scanner.nextInt();
        for ( int i = 0; i < numOfGreenTrains ; i++ ){
            int capacity = scanner.nextInt();
            Vehicle v = new Vehicle("GreenTrain",capacity);
            graph.addAdjTable(v);
            graph.addEdge(v,superVehicle,capacity);
            greenTrains.add(v);
        }
        int numOfRedTrains = scanner.nextInt();
        for ( int i = 0; i < numOfRedTrains ; i++ ){
            int capacity = scanner.nextInt();
            Vehicle v = new Vehicle("RedTrain",capacity);
            graph.addAdjTable(v);
            graph.addEdge(v,superVehicle,capacity);
            redTrains.add(v);
        }
        int numOfGreenDeers = scanner.nextInt();
        for ( int i = 0; i < numOfGreenDeers ; i++ ){
            int capacity = scanner.nextInt();
            Vehicle v = new Vehicle("GreenDeer",capacity);
            graph.addAdjTable(v);
            graph.addEdge(v,superVehicle,capacity);
            greenDeers.add(v);
        }
        int numOfRedDeers = scanner.nextInt();
        for ( int i = 0; i < numOfRedDeers ; i++ ){
            int capacity = scanner.nextInt();
            Vehicle v = new Vehicle("RedDeer",capacity);
            graph.addAdjTable(v);
            graph.addEdge(v,superVehicle,capacity);
            redDeers.add(v);
        }
        int numOfBags = scanner.nextInt();
        for ( int i = 0; i < numOfBags ; i++ ){
            String type = scanner.next();
            int capacity = scanner.nextInt();
            numOfTotalGift += capacity;
            GiftBag bag = new GiftBag(type,capacity);
            graph.addAdjTable(bag);
            graph.addEdge(superBag,bag,capacity);
            switch (bag.getType()) {
                case "a" -> {
                    createEdgesMethod1(graph, bag, greenTrains);
                    createEdgesMethod1(graph, bag, redTrains);
                    createEdgesMethod1(graph, bag, greenDeers);
                    createEdgesMethod1(graph, bag, redDeers);
                }
                case "b" -> {
                    createEdgesMethod2(graph, bag, greenTrains);
                    createEdgesMethod2(graph, bag, greenDeers);
                }
                case "c" -> {
                    createEdgesMethod2(graph, bag, redTrains);
                    createEdgesMethod2(graph, bag, redDeers);
                }
                case "d" -> {
                    createEdgesMethod2(graph, bag, greenTrains);
                    createEdgesMethod2(graph, bag, redTrains);
                }
                case "e" -> {
                    createEdgesMethod2(graph, bag, greenDeers);
                    createEdgesMethod2(graph, bag, redDeers);
                }
                case "ab" -> {
                    createEdgesMethod1(graph, bag, greenTrains);
                    createEdgesMethod1(graph, bag, greenDeers);
                }
                case "ac" -> {
                    createEdgesMethod1(graph, bag, redTrains);
                    createEdgesMethod1(graph, bag, redDeers);
                }
                case "ad" -> {
                    createEdgesMethod1(graph, bag, greenTrains);
                    createEdgesMethod1(graph, bag, redTrains);
                }
                case "ae" -> {
                    createEdgesMethod1(graph, bag, greenDeers);
                    createEdgesMethod1(graph, bag, redDeers);
                }
                case "abd" -> createEdgesMethod1(graph, bag, greenTrains);
                case "abe" -> createEdgesMethod1(graph, bag, greenDeers);
                case "acd" -> createEdgesMethod1(graph, bag, redTrains);
                case "ace" -> createEdgesMethod1(graph, bag, redDeers);
                case "bd" -> createEdgesMethod2(graph, bag, greenTrains);
                case "be" -> createEdgesMethod2(graph, bag, greenDeers);
                case "cd" -> createEdgesMethod2(graph, bag, redTrains);
                case "ce" -> createEdgesMethod2(graph, bag, redDeers);
                default -> System.out.println("invalid input");
            }

        }

        int maxFlow = dinicAlgo(graph,superBag,superVehicle);

        PrintStream out = new PrintStream(args[1]);
        out.print(numOfTotalGift-maxFlow);
        out.close();
    }

    public static  int dinicAlgo(CustomGraph graph, Vertice source, Vertice sink){
        if (source == sink){
            return -1;
        }
        int maxFlow = 0;


        while (breadthFS(graph, source, sink)){
            for (Vertice vertice : graph.adjTable.keySet()){
                vertice.setVisitedNeighb(0);
            }
            //System.out.println();
            int flow;
            while ( ( flow = sendFlow(graph,source,sink,Integer.MAX_VALUE) ) > 0 ){
                maxFlow += flow;
            }
        }
        return maxFlow;
    }

    private static int sendFlow(CustomGraph graph, Vertice currentVertice, Vertice sink, int flow) {
        if (currentVertice == sink){
            return flow;
        }
        if (currentVertice.getVisitedNeighb() == graph.adjTable.get(currentVertice).size()){
            return 0 ;
        }
        Hashtable<Vertice,Edge> adjTable = graph.adjTable.get(currentVertice);
        for (Edge edge : adjTable.values() ){
            Vertice src = edge.getSource();
            Vertice dst = edge.getDestination();

            if (edge.getCapacity() > 0){
                currentVertice.incrementVisitedNeighb();
                if (dst.getLevel() == currentVertice.getLevel()+1 ){
                    int currentFlow = Math.min(flow , edge.getCapacity());
                    int bottleNeck = sendFlow(graph, dst, sink, currentFlow);
                    if (bottleNeck > 0){
                        Edge backEdge = graph.adjTable.get(dst).get(src);
                        edge.setCapacity(edge.getCapacity()-bottleNeck);
                        backEdge.setCapacity(backEdge.getCapacity()+bottleNeck);
                        return bottleNeck;
                    }
                }
            }
        }
        return 0;
    }

    private static boolean breadthFS(CustomGraph graph, Vertice source, Vertice sink) {
        for (Vertice vertice : graph.adjTable.keySet()){
            vertice.setLevel(-1);
        }
        source.setLevel(0);
        Queue<Vertice> queue = new LinkedList<>();
        queue.add(source);

        while ( !queue.isEmpty() ){
            Vertice currentVertice = queue.poll();
            Hashtable<Vertice,Edge> adjTable = graph.adjTable.get(currentVertice);

            for ( Edge edge : adjTable.values()){
                Vertice destination = edge.getDestination();
                if ( edge.getCapacity() > 0 && destination.getLevel() < 0){
                    destination.setLevel( currentVertice.getLevel() + 1 );
                    queue.add(destination);
                }
            }
        }
        return sink.getLevel() != -1;
    }

    public static void createEdgesMethod1(CustomGraph graph, GiftBag bag, ArrayList<Vehicle> list){
        if (bag.getCapacity() > 0) {
            for (Vehicle vehicle : list) {
                if (vehicle.getCapacity() > 0) {
                    graph.addEdge(bag, vehicle, 1);
                }
            }
        }
    }
    public static void createEdgesMethod2(CustomGraph graph, GiftBag bag, ArrayList<Vehicle> list){
        int edgeCapacity;
        for (Vehicle vehicle : list){
            edgeCapacity = bag.getCapacity();
            if (vehicle.getCapacity() < edgeCapacity ){
                edgeCapacity = vehicle.getCapacity();
            }
            if ( edgeCapacity > 0 ) {
                graph.addEdge(bag, vehicle, edgeCapacity);
            }
        }
    }
}
