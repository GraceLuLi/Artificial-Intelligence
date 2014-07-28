import java.util.ArrayList;
import java.util.Scanner;


public class tmp {

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
	    if(sc.hasNextInt()) d = sc.nextInt();
		
	    
	   // root.printBoard();
	    //System.out.println(d);
	     root.value = max_value(root, 0, -1000,1000,d);
	     System.out.println("Answer: Player A moves the piece at ("+root.move[0]+","+root.move[1]+") to ("+root.move[2]+","+root.move[3]+").");
		
		}	


    static int max_value(Board board, int depth, int alpha, int beta, int d){


        if (depth == d){
        	board.value = board.getHeuristic();
            return board.value;
        }

        //board.value = -99999;
        int oldValue = board.value;
        depth++;
        ArrayList<Board> children = board.getChildren('A');
        for (Board child:children ){

            board.value = Math.max(board.value , min_value(child,depth, alpha, beta,d));
            if(oldValue!=board.value) board.move = child.move;
            if (board.value >= beta){

                return board.value;                
            }
            alpha = Math.max(alpha, board.value);
        }
        return board.value;
    }

    static int min_value(Board board, int depth, int alpha, int beta, int d){


    	 if (depth == d){
         	board.value = board.getHeuristic();
             return board.value;
         }

        //board.value = 99999;
    	  int oldValue = board.value;
          depth++;
          ArrayList<Board> children = board.getChildren('A');
        for (Board child: children){

            board.value = Math.min(board.value, max_value(child,depth, alpha, beta,d));
            if(oldValue!=board.value) board.move = child.move;
            if (board.value >= beta){
                return board.value;
            }
            beta = Math.min(beta, board.value);
        }
        return board.value;
    }
}
