
public class GameOfLife {
    private int boardsOvTime[][][]; 
    private int currentTick;
    
    public GameOfLife(int[][] board, int ticks){
        currentTick = 0;
        boardsOvTime = new int[ticks][][];
        boardsOvTime[0] = cloneOneBoard(board);
    }
    
    public int getCurrentTick(){
        return currentTick;
    }
    
    public int getMaxTick(){
        return boardsOvTime.length - 1;
    }
    
    public int newState(int cellCurrentState, int numLiveNeighbours){
        //TODO
        return 0;
    }
    
    public void evolveOnce(){
        //TODO
    }
    
    public void evolveCorners( int[][] currentBoard, int[][] newBoard ){
        
    }
    
    public void evolveEdges( int[][] currentBoard, int[][] newBoard ){
        
    }
    
    public void evolveMiddle( int[][] currentBoard, int[][] newBoard ){
        
    }
    
    public static int[][] cloneOneBoard(int[][] board){
        if(board == null){
            return null;
        }
        
        int ret[][] = new int[board.length][];
        for(int i = 0; i < board.length; i++){
            System.arraycopy(board[i], 0, ret[i], 0, board[i].length);
        }
        return ret;
    }
    
    public boolean equals(int[][] board){
        
    }
    
    @Override
    public String toString(){
        
    }
}
