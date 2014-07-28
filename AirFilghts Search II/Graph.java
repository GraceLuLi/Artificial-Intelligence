import java.awt.Point;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

@SuppressWarnings("unchecked")
public class Graph{

	public Node rootNode;
    public ArrayList<Node> nodes=new ArrayList();
    public Point[][] flightMatrix;//Edges will be represented as adjacency Matrix
    int size;
    
    public void setRootNode(Node n)
    {
        this.rootNode=n;
    }
    
    public Node getRootNode()
    {
        return this.rootNode;
    }
    public void addNode(Node n)
    {
        nodes.add(n);
    }
   
    public void connectNode(Node start,Node end,int fare, int dist)
    {
        if(flightMatrix==null)
        {
            size=nodes.size();
            flightMatrix=new Point[size][size];
            for(int i = 0; i<size; i++)
            {
            	for(int j=0; j<size; j++)
            	{
            		flightMatrix[i][j] = new Point(0,0);
            	}
            }
        }
        int startIndex = start.index;
        int endIndex = end.index;
        
        	flightMatrix[startIndex][endIndex].x=fare;
            flightMatrix[endIndex][startIndex].x=fare;
        
        	flightMatrix[startIndex][endIndex].y=dist;
            flightMatrix[endIndex][startIndex].y=dist;
    }

    private Node getUnvisitedChildNode(Node n)
    {
    	int index=n.index;
    	int i=0;
    	while(i<size)
    	{
    		if(flightMatrix[index][i].x!=0 && flightMatrix[index][i].y!=0&& ((Node)nodes.get(i)).visited == false)
    		{
    			Node nodeI = (Node)nodes.get(i);
    			return nodeI;
    		}
    		i++;
    	}
        return null;
    }

    public void search(String method, String cost)
    {

    	int nodesNum = 0;//Number of nodes expanded
        int travelDist = 0;//Traveling distance
        int airFare = 0;//Air Fare
        Queue q=new LinkedList();
        ArrayList<Node> childArray = new ArrayList<Node>();//store those nodes expanded
        //this part is for initialization of A* search
        
        if(cost == "fare")
        {
        	this.rootNode.value = this.rootNode.Efare;
        }
        if(cost == "distance")
        {
        	this.rootNode.value = this.rootNode.Edistance;
        }
        //end for initialization of A* search
        
        q.add(this.rootNode);
        childArray.add(this.rootNode);
        while(!q.isEmpty())
        {
            Node n=(Node)q.remove();// remove means we expand this node
            childArray.remove(n);//keep step with q, help to check if q contains nodes
            nodesNum++; 
            n.visited = true;
            System.out.print(n.label+"  ");
            if(n.label == "New York")
            {
            	System.out.println();
                System.out.println("Number of nodes expanded:  "+ nodesNum);
               
                  while(n.label!="Los Angeles")
                  {
                	airFare += flightMatrix[n.parent.index][n.index].x;
                	travelDist += flightMatrix[n.parent.index][n.index].y;
                	n = n.parent;
                  }
                
                System.out.println("Traveling distance: "+ travelDist + " miles");
                System.out.println("Air Fare: "+ airFare + " dollars");
                System.out.println();
                clearNodes();
           	    return;
            }
            Node child=null; 
            // when getting child nodes, set the value as the estimate fare or dist, and we can sort them by value;
            while((child=getUnvisitedChildNode(n))!=null)
            {
           	 child.visited = true;
           	 child.tmpParent = n;
           	 //just if this child node have already existed in queque q.
           	 if(!childArray.contains(child))
           	 {
               	 q.add(child);
               	 childArray.add(child);
           	 }

            }
          // ascendSortNopen(q, cost);
            if(method == "greedy") greedySortNopen(q,cost);
            if(method == "Astar") AstarSortNopen(q,n,cost);
    }
        System.out.println("can't find the destination New York, please check!");
        clearNodes();
    
    }   
    
    private void greedySortNopen(Queue q, String cost)
    {
       	ArrayList<Node> tmp = new ArrayList<Node>();
       	Node tmpNode;
       	while(!q.isEmpty())
       	{
       		tmp.add((Node)q.remove());
       	}
       	for(int i=0; i<tmp.size();i++)
       	{
       		for(int j=i+1; j<tmp.size();j++)
       		{
       			if(cost == "fare"&& tmp.get(i).Efare > tmp.get(j).Efare)
       			{
       				tmpNode = tmp.get(i);
       				tmp.set(i,tmp.get(j));
       				tmp.set(j,tmpNode);
       				
       			}
       			if(cost == "distance"&& tmp.get(i).Edistance > tmp.get(j).Edistance)
       			{
       				tmpNode = tmp.get(i);
       				tmp.set(i,tmp.get(j));
       				tmp.set(j,tmpNode);
       				
       			}
       		}
       	}
       	for(int i=0; i<tmp.size();i++)
       	{
       		tmp.get(i).visited = false;
       		tmp.get(i).parent = tmp.get(i).tmpParent;
       		q.add(tmp.get(i));
       	}
    }

    private void AstarSortNopen(Queue q, Node n, String cost)
    {
   	ArrayList<Node> tmp = new ArrayList<Node>();
   	Node tmpNode;
   	while(!q.isEmpty())
   	{
   		Node top = (Node)q.remove();
   	//value = g(n)+h(n)
   		if(cost == "fare")
   		{
       		int tmpValue = top.tmpParent.value - top.tmpParent.Efare+flightMatrix[top.tmpParent.index][top.index].x+top.Efare;
       		if(top.value ==0 || tmpValue < top.value) {top.value = tmpValue;top.parent = top.tmpParent;}
   		}
   		if(cost == "distance")
   		{
       		int tmpValue = top.tmpParent.value - top.tmpParent.Edistance+flightMatrix[top.tmpParent.index][top.index].y+top.Edistance;
       		if(top.value ==0 || tmpValue < top.value) {top.value = tmpValue;top.parent = top.tmpParent;}
       		
   		}
   			
   		tmp.add(top);
   	}
   	for(int i=0; i<tmp.size()-1;i++)
   	{
   		for(int j=i+1; j<tmp.size();j++)
   		{
   			if(tmp.get(i).value > tmp.get(j).value)
   			{
   				tmpNode = tmp.get(i);
   				tmp.set(i,tmp.get(j));
   				tmp.set(j,tmpNode);
   				
   			}
   		}
   	}
   	for(int i=0; i<tmp.size();i++)
   	{
   		tmp.get(i).visited = false;
   		q.add(tmp.get(i));
   	}
    }

    private void clearNodes()
    {
        int i=0;
        while(i<size)
        {
            Node n=(Node)nodes.get(i);
            n.visited=false;
            n.value = 0;
            i++;
        }
    }

}
