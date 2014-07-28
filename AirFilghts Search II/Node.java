
public class Node {
	String label;
	int index;
	int value = 0;
	int Edistance = 0;
	int Efare = 0;
    boolean visited ;
    Node parent;
    Node tmpParent;

    Node(int k,String l)
    {
    	index = k;
    	label = l;
    	visited = false;
    	value = 0;
    }
    Node(int k,String l, int ed, int ef)
    {
    	index = k;
    	label = l;
    	visited = false;
    	Edistance = ed;
    	Efare = ef;
    }
   
    boolean goaltest(String s)
    {
    	if(label == s ) return true;
    	else return false;
    }
    
    void setValue(int v)
    {
    	value = v;
    }

  
}
