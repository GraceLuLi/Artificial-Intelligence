import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		        String method = null;
		        boolean c = false;
                System.out.print("Please choose the Search Algorithm (BFS/DFS/UCSF/UCSD):  ");
                //BufferedReader br = new 
                	     // BufferedReader(new InputStreamReader(System.in)); 
                Scanner sc=new Scanner(System.in);
                
                method=sc.nextLine();
				
				//System.out.println(method);
				
				if(method.equals("UCSD"))
				{
					c = true;
				}

		        Node LA=new Node(0,"Los Angeles");
		        Node SF=new Node(1,"San Francisco");
		        Node SE=new Node(2,"Seattle");
		        Node DA=new Node(3,"Dallas");
		        Node DE=new Node(4,"Denver");
		        Node DC=new Node(5,"DC");
		        Node MI=new Node(6,"Miami");
		        Node CH=new Node(7,"Chicago");
		        Node AT=new Node(8,"Atlanta");
		        Node NO=new Node(9,"New Orleans");
		        Node SLC=new Node(10,"Salt Lake City");
		        Node NY=new Node(11,"New York");
		        Node SL=new Node(12,"St.Louis");
		        Node OM=new Node(13,"Omaha");
		 
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

		        g.connectNode(SE,SF,149,679,c);
		        g.connectNode(SF,LA,199,338,c);
		        g.connectNode(SE,LA,159,954,c);
		        g.connectNode(SF,DE,219,967,c);
		        g.connectNode(LA,DA,239,1235,c);
		        g.connectNode(SLC,DE,129,391,c);
		        g.connectNode(DE,DA,199,641,c);
		        g.connectNode(SE,CH,399,1721,c);
		        g.connectNode(DE,CH,199,888,c);
		        g.connectNode(SF,DC,439,2442,c);
		        g.connectNode(SE,MI,529,2724,c);
		        g.connectNode(DA,NO,199,448,c);
		        g.connectNode(OM,CH,349,416,c);
		        g.connectNode(SL,CH,159,258,c);
		        g.connectNode(DA,AT,249,732,c);
		        g.connectNode(CH,NY,199,740,c);
		        g.connectNode(CH,DC,199,612,c);
		        g.connectNode(AT,MI,229,595,c);
		        g.connectNode(AT,DC,209,547,c);
		        g.connectNode(NY,DC,109,213,c);
		        g.connectNode(NO,MI,199,675,c);
		        g.connectNode(NY,MI,199,1090,c);

		        //Perform the traversal of the graph
                //System.out.println(LA.label);

		        if(method.equals("BFS"))
		        {
		        	System.out.println("BFS Traversal of the flights is --->");
			        g.search("BFS");
		        }
		        else if(method.equals("DFS"))
		        {
		        	 System.out.println("\nDFS Traversal of the flights is --->");
				     g.search("DFS");
		        }
		        else if(method.equals("UCSF"))
		        {
		        	System.out.println("\nUCS with fare  Traversal of the flights is --->");
			        g.search("UCSF");
		        }
		        else if(method.equals("UCSD"))
		        {
		        	System.out.println("\nUCS with distance  Traversal of the flights is --->");
			        g.search("UCSD");
		        }
		        else 
		        {
	            	 System.out.println("This Search Algorithm doesn't exist, Please check it !");
	            	 
	             }

	}

}
