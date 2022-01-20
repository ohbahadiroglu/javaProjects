import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
public class project3main {

	public static void main(String[] args) throws FileNotFoundException {
		
		Hashtable<String,Vertice> vertices = new Hashtable<String,Vertice>();
		File file = new File(args[0]);
		Scanner reader = new Scanner(file);
		PrintStream outstream = new PrintStream(args[1]);
		
		CustomGraph anatolia1 = new CustomGraph();
		CustomGraph anatolia2 = new CustomGraph();

		
		int fatherslimit = reader.nextInt();
		int numberOfVertices = reader.nextInt();
		
		String mecnunCityName = reader.next();
		String leylaCityName = reader.next();
		
		reader.nextLine();
		
		int ccities=0;
		int dcities=0;
		for (int i = 0 ; i < numberOfVertices ; i++)  {
			String[] elements = reader.nextLine().split(" ");
			String tmpVerticeName = elements[0];
//			System.out.println(tmpVerticeName);
			Vertice tmpVertice = new Vertice(tmpVerticeName);
			vertices.put(tmpVerticeName, tmpVertice);
			if (tmpVerticeName.equals(leylaCityName) ) {
				anatolia1.addAdjTable(tmpVertice);
				anatolia2.addAdjTable(tmpVertice);
				dcities++;
				ccities++;
			}else if (tmpVerticeName.startsWith("d")) {
				anatolia2.addAdjTable(tmpVertice);
				dcities++;
			}else {
				anatolia1.addAdjTable(tmpVertice);
				ccities++;
			}	
		}
		
		Scanner reader2 = new Scanner(file);
		reader2.nextLine();
		reader2.nextLine();
		reader2.nextLine();
		
		for (int i = 0 ; i < numberOfVertices ; i++) {
			String[] elements = reader2.nextLine().split(" ");
			String fromVerticeName = elements[0];
			Vertice from = vertices.get(fromVerticeName);
			for (int j = 1; j < elements.length ; j+=2 ) {
				String toVerticeName = (elements[j]);
				Vertice to = vertices.get(toVerticeName);
				int distance = Integer.parseInt(elements[j+1]);			
				if (fromVerticeName.equals(leylaCityName) || fromVerticeName.startsWith("d") ) {
					anatolia2.addEdge(from, to, distance);
					anatolia2.addEdge(to, from, distance);
				}else {
					anatolia1.addEdge(from, to, distance);
				}
			}
		}
		
		reader.close();
		reader2.close();
		
		//Initializing unvisited vertice heaps and source vertices in order to use in algorithyms below.
		Vertice mecnuncity = vertices.get(mecnunCityName);
		Vertice leylacity = vertices.get(leylaCityName);
		mecnuncity.setShortestDistance(0);
		leylacity.setPrimDistance(0);
		PriorityQueue<Vertice> unvisitedD = new PriorityQueue<Vertice>(10,new VerticeComparator());
		PriorityQueue<Vertice> unvisitedP = new PriorityQueue<Vertice>(10, new VerticeComparator2());
		
		for (Vertice v : vertices.values()) {
			if (v.getName().startsWith("c") ) {
				unvisitedD.add(v);
			}
			if (v.getName().startsWith("d") || v.getName().equals(leylaCityName)) {
				unvisitedP.add(v);
			}
		}
		
		//Dijkstra Shortest Path Algorithm
		
		while ( unvisitedD.peek() != null ) {
			Vertice currentV = unvisitedD.poll();
			LinkedList<Edge> adjlist = anatolia1.getAdjTable().get(currentV);
			for (Edge edge : adjlist) {
				Vertice destination = edge.getDestination();
				if (unvisitedD.contains(destination)) {
					int tmpDistance = ( currentV.getShortestDistance() + edge.getDistance() );
					if (tmpDistance < destination.getShortestDistance()) {
						destination.setShortestDistance(tmpDistance);
						unvisitedD.remove(destination);
						unvisitedD.add(destination);
						destination.setPreV(currentV);
					}
				}
			}
			
		}
		

		
		//Prim's MST Algorithm

		while ( unvisitedP.peek() != null) {
			Vertice currentV = unvisitedP.poll();
			if ( currentV.isInTree() == false ) {
				
				currentV.setInTree(true);
				LinkedList<Edge> adjlist = anatolia2.getAdjTable().get(currentV);
				
				for (Edge edge : adjlist) {
					Vertice destination = edge.getDestination();
					if ( destination.isInTree() == false ) {
						int tmpPrimDistance = edge.getDistance() ;
						if (tmpPrimDistance <  destination.getPrimDistance()) {
							destination.setPrimDistance(tmpPrimDistance);
							destination.setParent(currentV);
							unvisitedP.remove(destination);
							unvisitedP.add(destination);
						}
					}
				}
			}
		}
		
		// Preparing output line1 and line2
		
		Vertice current = leylacity;
		String output1 = current.getName();
		int output2 = 0;		
		
		if (current.getPreV() == null) {
			output1 = "-1";
			output2 = -1;
		}else {
			while( current.getPreV() != null ) {
				output1 = current.getPreV().getName() + " " + output1;
				current = current.getPreV();
			}
			if ( leylacity.getShortestDistance() <= fatherslimit) {
				int edgeCounter = 0;
				for (Vertice v : vertices.values()) {
					if (v.getName().startsWith("d") || v.getName().equals(leylaCityName)) {
						if( v.getParent() != null) {
							edgeCounter++;
							output2 += ( v.getPrimDistance()*2 );
						}
					}
				}
				if (edgeCounter != (dcities-1)) {
					output2 = -2;
				}
			}else {
				output2=-1;
			}
		}
		
		// printing outputs to the txt file.
		outstream.println(output1);
		outstream.print(output2);

		
		outstream.close();
		
	}
}
