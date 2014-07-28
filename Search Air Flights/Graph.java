import java.awt.Point;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class Graph {

    public Node rootNode;
    public ArrayList nodes=new ArrayList();
    public int[][] adjMatrix;//Edges will be represented as adjacency Matrix
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
    
    //This method will be called to make connect two nodes
    public void connectNode(Node start,Node end,int fare, int dist,boolean c)
    {
        if(adjMatrix==null)
        {
            size=nodes.size();
            adjMatrix=new int[size][size];
        }
        //int startIndex=nodes.indexOf(start);
        //int endIndex=nodes.indexOf(end);
        int startIndex = start.index;
        int endIndex = end.index;
        if(c == false)
        {
        	adjMatrix[startIndex][endIndex]=fare;
            adjMatrix[endIndex][startIndex]=fare;
        }
        else
        {
        	adjMatrix[startIndex][endIndex]=dist;
            adjMatrix[endIndex][startIndex]=dist;
        }
        
    }
     
    private Node getUnvisitedChildNode(Node n)
    {
        int index=n.index;
        int j=0;
        while(j<size)
        {
            if(adjMatrix[index][j]!=0 && ((Node)nodes.get(j)).visited == false)
            {
            	int newValue = n.value+adjMatrix[index][j];
            	Node nodeJ = (Node)nodes.get(j);
            	if(nodeJ.value==0)
            		nodeJ.value = newValue;
            	else 
            	{
            		nodeJ.value = Math.min(nodeJ.value, newValue);
            		
            	}
            	
                return nodeJ;
            }
            j++;
        }
        return null;
    }
    
    //return the Node in temple nodes Array with minimal value
    private Node minValueNode(ArrayList<Node> tmpArray)
    {
    	Node tmpNode = null;
    	if(tmpArray!=null)
    	{
    		tmpNode = tmpArray.get(0);
    	    for(int i=0; i<tmpArray.size(); i++ )
    	      {
    		    if(tmpNode.value > tmpArray.get(i).value)
    		    {
    		    	tmpNode = tmpArray.get(i);
    		    }
    	      }
    	}
    	return tmpNode;
    	
    }
 
 
     public void search(String method)
     {
    	 ArrayList<Node> childArray = new ArrayList<Node>();//for UCS: store the child nodes, in case add multiple same node in q
         Queue q=new LinkedList();
         q.add(this.rootNode);
         childArray.add(this.rootNode);
         while(!q.isEmpty()&& !childArray.isEmpty())
         {
             Node n=(Node)q.peek();
             //childArray.remove(n);//keep step with q
             n.visited = true;
             printNode(n);
             if(n.label == "New York")
             {
            	 clearNodes();
            	 return;
             }
             if(method == "BFS")
		        {
            	    q = breadthFirstQueuing( q, n);
		        }
             else if(method == "DFS")
		        {
            	    q = depthFirstQueuing(q,childArray,n);
		        }
             else if(method == "UCSF" || method == "UCSD")
		        {
            	    q = uniformCostFareQueuing(q,childArray,n);
		        }
             else 
             {
            	 System.out.println("This Search Algorithm doesn't exist, Please check it !");
            	 return;
             }
         }
        
         clearNodes();
     }  
     //depthFirstQueuing
    private Queue depthFirstQueuing(Queue q, ArrayList childArray,Node n)
     {
    	 Node child = null;
         child=getUnvisitedChildNode(n);
         
         if(child!=null)
         {
             child.visited=true;
             childArray.add(child);     
         }
         else
         {
             childArray.remove(childArray.size()-1);
         }    
     //make a stack with childArray and queue q
        q.clear();
         for(int i=childArray.size()-1; i>0; i--)
         {
             q.add(childArray.get(i));
         }
    	return q; 
     }
     //breadthFirstQueuing
     private Queue breadthFirstQueuing(Queue q, Node n)
     {
    	     q.remove();
             Node child=null;
             while((child=getUnvisitedChildNode(n))!=null)
             {
                 child.visited=true;
                 q.add(child);
             }
    	 return q;
     }
     //uniformCostFareQueuing; append nodes to queque with uniform cost fare search
     private Queue uniformCostFareQueuing(Queue q, ArrayList<Node> childArray, Node n)
     {
    	 q.remove();
	     childArray.remove(n);//keep step with q
    	 Node child=null;    
         while((child=getUnvisitedChildNode(n))!=null)
           {
          	 child.visited = true;
          	 //just if this child node have already existed in queque q.
          	 if(!childArray.contains(child))
          	 {
              	 q.add(child);
              	 childArray.add(child);
          	 }

           }
          ascendSortNopen(q);
          return q;
     }
     
   //sort the Nodes in q with ascending, which is use in UCS
     private void ascendSortNopen(Queue q)
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
     
    //Utility methods for clearing visited property of node
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
    //Utility methods for printing the node's label
    private void printNode(Node n)
    {
        System.out.print(n.label+" ");
    }


}
