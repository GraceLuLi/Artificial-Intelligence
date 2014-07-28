import java.util.NoSuchElementException;
import java.util.Scanner;


public class test {
	public static void main(String [ ] args){
	
	/** variable declearance part**/
    int d = 0;//d is the search depth of minimax
	Board root = new Board();
	
	/** input part**/
	System.out.println("please input the board state and depth:");
	Scanner sc = new Scanner(System.in);
	int i=0;
    while(sc.hasNext() && i<8)
    {
    	String line = sc.nextLine();
    	//Scanner l = new Scanner(line);
    	for(int j=0; j<8;j++)
    	{
    		 root.state[i][j] = line.charAt(j);
    	}
    	i++;
    }
    if(sc.hasNextInt())// throw new NoSuchElementException();
    d = sc.nextInt();
	
    
   // root.printBoard();
   // printB(root);
    int h = root.getHeuristic();
    
    System.out.println(h);
	
	

/*
  private void minimax(Board board, int depth, int alpha, int beta, char player)
  {
	  if(depth == 0||depth == d)
	  {
		  return board.heuristic;
	  }
	  
  }*/
	}	
	static void printB(Board board)
	{
		for(int i=0; i<8; i++)
		{
			for(int j=0; j<8; j++)
			{
				System.out.print(board.state[i][j]+" ");
			}
			System.out.println();
		}
	}
}
