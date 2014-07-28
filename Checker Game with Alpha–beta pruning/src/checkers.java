/** StudentName: Lu Li
 *  StudentID: lli619
 *  USCID: 4264118672
 */

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class checkers {
	public static void main(String [ ] args){
	
	/** variable declearance part**/
    int d = 0;//d is the search depth of minimax
	Board root = new Board();
	root.value = -1000;
	
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
    if(sc.hasNextInt()) d = sc.nextInt();
    
   // root.printBoard();
    //System.out.println(d);
	minimax(root, 0, -1000,1000,'A',d); 
	System.out.println();
	System.out.println("Expansions Completed !");
	System.out.println("Answer: Player A moves the piece at ("+root.move[0]+","+root.move[1]+") to ("+root.move[2]+","+root.move[3]+").");
	}
	  private static int minimax(Board board, int depth, int alpha, int beta, char player,int d)
	  {
		  ArrayList<Board> children = new ArrayList<Board>();
		  children = board.getChildren(player);  
		  if(depth == d)
		  {
			  printBlank(depth);
			  System.out.print("Depth "+depth+": Heuristic value of the current board = "+board.getHeuristic()+".");
			  
			  //System.out.print(" moves the piece at ("+board.move[0]+","+board.move[1]+") to ("+board.move[2]+","+board.move[3]+").");
			  return board.getHeuristic();
		  }
		  
		 
		  if(player == 'A')
		  {
			  int position = 0;
			  for(Board child : children)
			  {
				  position++;
				  printBlank(depth);
				  System.out.print("Depth "+depth+": Player "+player+" moves the piece at ("+child.move[0]+","+child.move[1]+") to ("+child.move[2]+","+child.move[3]+").");
				  int alphaOld = alpha;
				  alpha = Math.max(alpha, minimax(child, depth+1,alpha, beta, Not(player),d));
				//System.out.println(child.alpha_value);
				 if(alphaOld!=alpha && depth == 0) board.move = child.move;//if the node get update, the parent's move changes
	
				  if(alpha >= beta)
				  {
					  printBlank(depth); 
					 System.out.print("Depth "+depth+": Pruning Player "+player+"'s moves: ("+child.move[0]+","+child.move[1]+") to ("+child.move[2]+","+child.move[3]+")");
					 for(int p= position; p<children.size(); p++ )
					 {
						 child = children.get(p);//this is the line I lost in former version
						 System.out.print(",("+child.move[0]+","+child.move[1]+") to ("+child.move[2]+","+child.move[3]+")");
					 }
					 System.out.print("; Alpha = "+alpha+"; Beta = "+beta+".");
					 break; 
				  }

			  }
			  //System.out.println(alpha);
			  return alpha;
		  }
		  else
		  {
			  int position = 0;
			  for(Board child : children)
			  {
				  position++;
				  printBlank(depth);
				 System.out.print("Depth "+depth+": Player "+player+" moves the piece at ("+child.move[0]+","+child.move[1]+") to ("+child.move[2]+","+child.move[3]+").");
				  int betaOld = beta;
				  //child.alpha_value = alpha;
				  //child.beta_value = beta;
				  beta = Math.min(beta, minimax(child, depth+1,alpha, beta, Not(player),d));
				  if(betaOld!=beta && depth == 0) board.move =child.move ;//if the node get update, the parent's move changes
				  
				  if(alpha >= beta) 
				  {
					  printBlank(depth); 
					  System.out.print("Depth "+depth+": Pruning Player "+player+"'s moves: ("+child.move[0]+","+child.move[1]+") to ("+child.move[2]+","+child.move[3]+")");
						 for(int p= position; p<children.size(); p++ )
						 {
							 child = children.get(p);
							 System.out.print(",("+child.move[0]+","+child.move[1]+") to ("+child.move[2]+","+child.move[3]+")");
						 }
						 System.out.print("; Alpha = "+alpha+"; Beta = "+beta+".");			
						 break;				  
				  }

			  }
			 // System.out.println(beta);
			  return beta;
		  }
	  }
	  
	  static char Not(char player)
	  {
		  if(player == 'A') return 'B';
		  else return 'A';
	  }
	  static void printBlank(int depth)
	  {
		  System.out.println();
		  for(int i=depth; i > 0; i--)
		  {
			  System.out.print(" ");
		  }  
	  }
}
