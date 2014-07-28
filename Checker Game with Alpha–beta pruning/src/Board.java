/** StudentName: Lu Li
 *  StudentID: lli619
 *  USCID: 4264118672
 */



import java.util.ArrayList;


public class Board {

	/**variables**/
    char[][] state = new char[8][8];
    ArrayList<Board> children = new ArrayList<Board>();
    int[] move = new int[4];
    int value;
    int alpha_value;
    int beta_value;
    boolean isTerminal;
    /*create new board*/
    Board()
    {
    	isTerminal = false;
    }
    Board(Board oldBoard)
    {
    	for(int i=0; i<8; i++)
    	{
    		for(int j=0; j<8; j++)
    		{
    			state[i][j] = oldBoard.state[i][j];
    		}
    	}
    }
    
	void printBoard()
	{
		for(int i=0; i<8; i++)
		{
			for(int j=0; j<8; j++)
			{
				System.out.print(state[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	int getHeuristic()
	{
		int h=0;
		for(int i=0; i<8; i++)
		{
			for(int j=0; j<8; j++)
			{
				if(state[i][j] == 'o') h+=1;
				if(state[i][j] == 'k') h+=2;
				if(state[i][j] == '*') h-=1;
				if(state[i][j] == 'K') h-=2;
			}
		}
		return h;
	}
	
    ArrayList<Board> getChildren(char player)
    {
    	if(!getAllJumps(player))
    	{
    		getAllMoves(player);
    	}
    	return children;
    }
	
    boolean getAllJumps(char player)
    {
    	Board tmpBoard = new Board(this);//tmpBoard for adding children
    	boolean canJump = false;
    	for(int i=0; i<8; i++)
		{
			for(int j=0; j<8; j++)
			{
				if(getForwardLeftJump(tmpBoard, i, j , player))
				{
					children.add(tmpBoard);
					canJump = true;
					tmpBoard = new Board(this);
				}
				if(getForwardRightJump(tmpBoard, i, j , player)) 
				{
					children.add(tmpBoard);
					canJump = true;
					tmpBoard = new Board(this);
				}
				if(getBackwardLeftJump(tmpBoard, i, j , player)) 
				{
					children.add(tmpBoard);
					canJump = true;
					tmpBoard = new Board(this);
				}
				if(getBackwardRightJump(tmpBoard, i, j , player))
				{
					children.add(tmpBoard);
					canJump = true;
					tmpBoard = new Board(this);
				}
				
			}	
		}
    	return canJump;
    }
    boolean getAllMoves(char player)
    {
    	Board tmpBoard = new Board(this);//tmpBoard for adding children
    	boolean canMove = false;
    	for(int i=0; i<8; i++)
		{
			for(int j=0; j<8; j++)
			{
				if(getForwardLeftMove(tmpBoard, i, j , player))
				{
					children.add(tmpBoard);
					canMove = true;
					tmpBoard = new Board(this);
				}
				if(getForwardRightMove(tmpBoard, i, j , player)) 
				{
					children.add(tmpBoard);
					canMove = true;
					tmpBoard = new Board(this);
				}
				if(getBackwardLeftMove(tmpBoard, i, j , player)) 
				{
					children.add(tmpBoard);
					canMove = true;
					tmpBoard = new Board(this);
				}
				if(getBackwardRightMove(tmpBoard, i, j , player))
				{
					children.add(tmpBoard);
					canMove = true;
					tmpBoard = new Board(this);
				}
				
			}	
		}
    	return canMove;
    }

    boolean getForwardLeftMove(Board board,int x, int y , char player)
    {
    	if(player =='A' && (board.state[x][y] == 'o'||board.state[x][y] == 'k') && (x-1)>=0 && (y-1)>=0 && board.state[x-1][y-1]=='.')
    	{
    		board.state[x-1][y-1] = board.state[x][y];
    		if((x-1)==0) board.state[x-1][y-1] = 'k';
    		board.state[x][y] = '.';
    		board.move[0] = x+1;
    		board.move[1] = y+1;
    		board.move[2] = x ;
    		board.move[3] = y ;
    		return true;
    	}
    	else if(player =='B' && (board.state[x][y] == '*'||board.state[x][y] == 'K') && (x+1)<8 && (y-1)>=0 && board.state[x+1][y-1]=='.')
    	{
    		board.state[x+1][y-1] = board.state[x][y];
    		if((x+1)==7) board.state[x+1][y-1] = 'K';
    		board.state[x][y] = '.';
    		board.move[0] = x+1;
    		board.move[1] = y+1;
    		board.move[2] = x+2;
    		board.move[3] = y ;
    		return true;
    	}
    	return false;
    }
    
    boolean getForwardRightMove(Board board,int x, int y , char player)
    {
    	if(player =='A' && (board.state[x][y] == 'o'||board.state[x][y] == 'k') && (x-1)>=0 && (y+1)<8 && board.state[x-1][y+1]=='.')
    	{
    		board.state[x-1][y+1] = board.state[x][y];
    		if((x-1)==0) board.state[x-1][y+1] = 'k';
    		board.state[x][y] = '.';
    		board.move[0] = x+1;
    		board.move[1] = y+1;
    		board.move[2] = x ;
    		board.move[3] = y+2;
    		return true;
    	}
    	else if(player =='B' && (board.state[x][y] == '*'||board.state[x][y] == 'K') && (x+1)<8 && (y+1)<8 && board.state[x+1][y+1]=='.')
    	{
    		board.state[x+1][y+1] = board.state[x][y];
    		if((x+1)==7) board.state[x+1][y+1] = 'K';
    		board.state[x][y] = '.';
    		board.move[0] = x+1;
    		board.move[1] = y+1;
    		board.move[2] = x+2;
    		board.move[3] = y+2;
    		return true;
    	}
    	return false;
    }
    
    boolean getBackwardLeftMove(Board board,int x, int y , char player)
    {
    	if(player=='A' && board.state[x][y]=='k' && (x+1)<8 && (y-1)>=0 && board.state[x+1][y-1]=='.')
    	{
    		board.state[x+1][y-1] = board.state[x][y];
    		board.state[x][y] = '.';
    		board.move[0] = x+1;
    		board.move[1] = y+1;
    		board.move[2] = x+2;
    		board.move[3] = y;
    		return true;
    	}
    	if(player=='B' && board.state[x][y]=='K' && (x-1)>=0 && (y-1)>=0 && board.state[x-1][y-1]=='.')
    	{
    		board.state[x-1][y-1] = board.state[x][y];
    		board.state[x][y] = '.';
    		board.move[0] = x+1;
    		board.move[1] = y+1;
    		board.move[2] = x ;
    		board.move[3] = y ;
    		return true;
    	}
    	return false;
    }
 
    boolean getBackwardRightMove(Board board,int x, int y , char player)
    {
    	if(player=='A' && board.state[x][y]=='k' && (x+1)<8 && (y+1)<8 && board.state[x+1][y+1]=='.')
    	{
    		board.state[x+1][y+1] = board.state[x][y];
    		board.state[x][y] = '.';
    		board.move[0] = x+1;
    		board.move[1] = y+1;
    		board.move[2] = x+2;
    		board.move[3] = y+2;
    		return true;
    	}
    	if(player=='B' && board.state[x][y]=='K' && (x-1)>=0 && (y+1)<8 && board.state[x-1][y+1]=='.')
    	{
    		board.state[x-1][y+1] = board.state[x][y];
    		board.state[x][y] = '.';
    		board.move[0] = x+1;
    		board.move[1] = y+1;
    		board.move[2] = x ;
    		board.move[3] = y+2;
    		return true;
    	}
    	return false;
    }
  
    boolean getForwardLeftJump(Board board,int x, int y , char player)
    {
    	if(player =='A' && (board.state[x][y] == 'o'||board.state[x][y] == 'k') && (x-2)>=0 && (y-2)>=0 && (board.state[x-1][y-1]=='*'||board.state[x-1][y-1]=='K')&&board.state[x-2][y-2]=='.')
    	{
    		board.state[x-2][y-2] = board.state[x][y];
    		if((x-2)==0) board.state[x-2][y-2] = 'k';
    		board.state[x-1][y-1] = '.';
    		board.state[x][y] = '.';
    		board.move[0] = x+1;
    		board.move[1] = y+1;
    		board.move[2] = x-1;
    		board.move[3] = y-1;
    		return true;
    	}
    	else if(player =='B' && (board.state[x][y] == '*'||board.state[x][y] == 'K') && (x+2)<8 && (y-2)>=0 && (board.state[x+1][y-1] == 'o'||board.state[x+1][y-1] == 'k')&& board.state[x+2][y-2]=='.')
    	{
    		board.state[x+2][y-2] = board.state[x][y];
    		if((x+2)==7) board.state[x+2][y-2] = 'K';
    		board.state[x+1][y-1] = '.';
    		board.state[x][y] = '.';
    		board.move[0] = x+1;
    		board.move[1] = y+1;
    		board.move[2] = x+3;
    		board.move[3] = y-1;
    		return true;
    	}
    	return false;
    }

    boolean getForwardRightJump(Board board,int x, int y , char player)
    {
    	if(player =='A' && (board.state[x][y] == 'o'||board.state[x][y] == 'k') && (x-2)>=0 && (y+2)<8 && (board.state[x-1][y+1]=='*'||board.state[x-1][y+1]=='K')&&board.state[x-2][y+2]=='.')
    	{
    		board.state[x-2][y+2] = board.state[x][y];
    		if((x-2)==0) board.state[x-2][y+2] = 'k';
    		board.state[x-1][y+1] = '.';
    		board.state[x][y] = '.';
    		board.move[0] = x+1;
    		board.move[1] = y+1;
    		board.move[2] = x-1;
    		board.move[3] = y+3;
    		return true;
    	}
    	else if(player =='B' && (board.state[x][y] == '*'||board.state[x][y] == 'K') && (x+2)<8 && (y+2)<8 && (board.state[x+1][y+1] == 'o'||board.state[x+1][y+1] == 'k')&& board.state[x+2][y+2]=='.')
    	{
    		board.state[x+2][y+2] = board.state[x][y];
    		if((x+2)==7) board.state[x+2][y+2] = 'K';
    		board.state[x+1][y+1] = '.';
    		board.state[x][y] = '.';
    		board.move[0] = x+1;
    		board.move[1] = y+1;
    		board.move[2] = x+3;
    		board.move[3] = y+3;
    		return true;
    	}
    	return false;
    }
   
    boolean getBackwardLeftJump(Board board,int x, int y , char player)
    {
    	if(player=='A' && board.state[x][y]=='k' && (x+2)<8 && (y-2)>=0 && (board.state[x+1][y-1]=='*'||board.state[x+1][y-1]=='K')&&board.state[x+2][y-2]=='.')
    	{
    		board.state[x+2][y-2] = board.state[x][y];
    		board.state[x+1][y-1]='.';
    		board.state[x][y] = '.';
    		board.move[0] = x+1;
    		board.move[1] = y+1;
    		board.move[2] = x+3;
    		board.move[3] = y-1;
    		return true;
    	}
    	if(player=='B' && board.state[x][y]=='K' && (x-2)>=0 && (y-2)>=0 && (board.state[x-1][y-1]=='o'||board.state[x-1][y-1]=='k')&&board.state[x-2][y-2]=='.')
    	{
    		board.state[x-2][y-2] = board.state[x][y];
    		board.state[x-1][y-1]='.';
    		board.state[x][y] = '.';
    		board.move[0] = x+1;
    		board.move[1] = y+1;
    		board.move[2] = x-1;
    		board.move[3] = y-1;
    		return true;
    	}
    	return false;
    }
    
    boolean getBackwardRightJump(Board board,int x, int y , char player)
    {
    	if(player=='A' && board.state[x][y]=='k' && (x+2)<8 && (y+2)<8 && (board.state[x+1][y+1]=='*'||board.state[x+1][y+1]=='K')&&board.state[x+2][y+2]=='.')
    	{
    		board.state[x+2][y+2] = board.state[x][y];
    		board.state[x+1][y+1]='.';
    		board.state[x][y] = '.';
    		board.move[0] = x+1;
    		board.move[1] = y+1;
    		board.move[2] = x+3;
    		board.move[3] = y+3;
    		return true;
    	}
    	if(player=='B' && board.state[x][y]=='K' && (x-2)>=0 && (y+2)<8 && (board.state[x-1][y+1]=='o'||board.state[x-1][y+1]=='k')&&board.state[x-2][y+2]=='.')
    	{
    		board.state[x-2][y+2] = board.state[x][y];
    		board.state[x-1][y=1]='.';
    		board.state[x][y] = '.';
    		board.move[0] = x+1;
    		board.move[1] = y+1;
    		board.move[2] = x-1;
    		board.move[3] = y+3;
    		return true;
    	}
    	return false;
    }
}

