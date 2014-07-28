import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args)  {
		// TODO Auto-generated method stub
			

		        Node LA=new Node(0,"Los Angeles",2475,499);
		        Node SF=new Node(1,"San Francisco",2586,529);
		        Node SE=new Node(2,"Seattle",2421,529);
		        Node DA=new Node(3,"Dallas",1391,319);
		        Node DE=new Node(4,"Denver",1626,359);
		        Node DC=new Node(5,"DC",213,89);
		        Node MI=new Node(6,"Miami",1090,199);
		        Node CH=new Node(7,"Chicago",740,199);
		        Node AT=new Node(8,"Atlanta",760,259);
		        Node NO=new Node(9,"New Orleans",1182,319);
		        Node SLC=new Node(10,"Salt Lake City",1989,459);
		        Node NY=new Node(11,"New York",0,0);
		        Node SL=new Node(12,"St.Louis",892,279);
		        Node OM=new Node(13,"Omaha",1154,329);
		 
		        //Create the graph, add nodes, create edges between nodes
		        Graph g=new Graph();
		        g.addNode(LA);
		        g.addNode(SF);
		        g.addNode(SE);
		        g.addNode(DA);
		        g.addNode(DE);
		        g.addNode(DC);
		        g.addNode(MI);
		        g.addNode(CH);
		        g.addNode(AT);
		        g.addNode(NO);
		        g.addNode(SLC);
		        g.addNode(NY);
		        g.addNode(SL);
		        g.addNode(OM);
		    
		        g.setRootNode(LA);

		        g.connectNode(SF,LA,199,338);
		        g.connectNode(SE,LA,159,954);
		        g.connectNode(SF,DE,219,967);
		        g.connectNode(LA,DA,239,1235);
		        g.connectNode(SLC,DE,129,391);
		        g.connectNode(DE,DA,199,641);
		        g.connectNode(SE,CH,399,1721);
		        g.connectNode(DE,CH,199,888);
		        g.connectNode(SF,DC,439,2442);
		        g.connectNode(SE,MI,529,2724);
		        g.connectNode(DA,NO,199,448);
		        g.connectNode(OM,CH,349,416);
		        g.connectNode(SL,CH,159,258);
		        g.connectNode(DA,AT,249,732);
		        g.connectNode(CH,NY,199,740);
		        g.connectNode(CH,DC,199,612);
		        g.connectNode(AT,DC,209,547);
		        g.connectNode(NY,MI,199,1090);

		        //Perform the traversal of the graph
                    System.out.println("Greedy Search (distance):");
		    	    g.search("greedy","distance");
		    	    System.out.println("A* Search (distance):");
		    	    g.search("Astar","distance");
		    	    System.out.println("Greedy Search (air fare):");
	            	g.search("greedy","fare");
	            	System.out.println("A* Search (air fare):");
	            	g.search("Astar","fare");
	             }

	}

