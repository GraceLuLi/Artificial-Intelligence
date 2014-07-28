
public class Node {
	String label;
	int index;
	int value = 0;
    boolean visited ;

    Node(int k,String l)
    {
    	index = k;
    	label = l;
    	visited = false;
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
