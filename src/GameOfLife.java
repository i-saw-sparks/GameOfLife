
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class GameOfLife {
    private int boardsOvTime[][][]; 
    private int currentTick;
    
    public GameOfLife(int[][] board, int ticks){
        currentTick = 0;
        boardsOvTime = new int[ticks][][];
        boardsOvTime[0] = cloneOneBoard(board);
    }
    
    public GameOfLife(String fileName, int ticks) throws IOException{
        currentTick = 0;
        boardsOvTime = new int[ticks][][];
        boardsOvTime[0] = readBoard(fileName);
    }
    
    public int getCurrentTick(){
        return currentTick;
    }
    
    public int getMaxTick(){
        return boardsOvTime.length - 1;
    }
    
    public int newState(int cellCurrentState, int numLiveNeighbours){
        if(cellCurrentState == 1){
            if(numLiveNeighbours == 2 || numLiveNeighbours == 3){
                return 1;
            }else{
                return 0;
            }
        }else{
            if(numLiveNeighbours == 3){
                return 1;
            }else{
                return 0;
            }
        }
    }
    
    public void evolveOnce(){
        if(currentTick < boardsOvTime.length){
            int newBoard[][]= new int[boardsOvTime[0].length][boardsOvTime[0][0].length];
            evolveCorners(boardsOvTime[currentTick], newBoard);
            evolveEdges(boardsOvTime[currentTick], newBoard);
            evolveMiddle(boardsOvTime[currentTick], newBoard);
            currentTick++;
            boardsOvTime[currentTick] = newBoard;
        }
    }
    
    public void evolveCorners( int[][] currentBoard, int[][] newBoard ){
        int maxY = currentBoard[0].length-1;
        int maxX = currentBoard.length-1;
        newBoard[0][0] = newState(currentBoard[0][0], currentBoard[0][1]+currentBoard[1][0]+currentBoard[1][1]);
        newBoard[0][maxY] = newState(currentBoard[0][maxY], currentBoard[1][maxY]+currentBoard[0][maxY-1]+currentBoard[1][maxY-1]);
        newBoard[maxX][0] = newState(currentBoard[maxX][0], currentBoard[maxX][1]+currentBoard[maxX-1][0]+currentBoard[maxX-1][1]);
        newBoard[maxX][maxY]=newState(currentBoard[maxX][maxY], currentBoard[maxX][maxY-1]+currentBoard[maxX-1][maxY]+currentBoard[maxX-1][maxY-1]);
    }
    
    public void evolveEdges( int[][] currentBoard, int[][] newBoard ){
        int maxY = currentBoard[0].length-1;
        int maxX = currentBoard.length-1;
        
        for(int i = 1; i < currentBoard.length-1; i++){
            int adjSum = currentBoard[i-1][0]+currentBoard[i+1][0]+currentBoard[i-1][1]+currentBoard[i][1]+currentBoard[i+1][1];
            newBoard[i][0] = newState(currentBoard[i][0], adjSum);
            
            adjSum = currentBoard[i-1][maxY]+currentBoard[i+1][maxY]+currentBoard[i-1][maxY-1]+currentBoard[i][maxY-1]+currentBoard[i+1][maxY-1];
            newBoard[i][maxY] = newState(currentBoard[i][maxY], adjSum);
        }
        
        for(int i = 1; i < currentBoard[0].length-1; i++){
            int adjSum = currentBoard[0][i-1]+currentBoard[0][i+1]+currentBoard[1][i-1]+currentBoard[1][i]+currentBoard[1][i+1];
            newBoard[0][i] = newState(currentBoard[0][i], adjSum);
            
            adjSum = currentBoard[maxX][i-1]+currentBoard[maxX][i+1]+currentBoard[maxX-1][i-1]+currentBoard[maxX-1][i]+currentBoard[maxX-1][i+1];
            newBoard[maxX][i] = newState(currentBoard[maxX][i], adjSum);
        }
    }
    
    public void evolveMiddle( int[][] currentBoard, int[][] newBoard ){
        for(int i = 1; i<currentBoard.length-2; i++){
            for(int j = 1; j<currentBoard[i].length-2; j++){
                newBoard[i][j] = newState(currentBoard[i][j], getAdjSum(currentBoard, i, j));
            }
        }
    }

    private int getAdjSum(int board[][], int x, int y){
        int sum = 0;
        for(int i = x-1; i <= x+1; i++){
            for(int j = y-1; j <= y+1; j++){
                if(i != x || j != y){
                    sum += board[i][j];
                }
            }
        }
        return sum;
    }
    
    public static int[][] cloneOneBoard(int[][] board){
        if(board == null){
            return null;
        }
        
        int ret[][] = new int[board.length][board[0].length];
        for(int i = 0; i < board.length; i++){
            System.arraycopy(board[i], 0, ret[i], 0, board[i].length);
        }
        return ret;
    }
    
    public int[][] readBoard(String fileName) throws IOException{
        File fileToRead = new File(fileName);
        BufferedReader bufferedFile = new BufferedReader(new FileReader(fileToRead));
        
        String fileLine=bufferedFile.readLine();
        String parts[] = fileLine.split(" ",0);
        int xLen = Integer.parseInt(parts[0]);
        int yLen = Integer.parseInt(parts[1]);
        int ret[][] = new int[xLen][yLen];
        
        for(int i = 0; i < xLen; i++){
            for(int j = 0; j < yLen; j++){
                ret[i][j] = Integer.parseInt(bufferedFile.readLine());
            }
        }
        
        return ret;
    }
    
    public boolean equals(int[][] board){
      if(board.length == boardsOvTime[currentTick].length){
          for(int i = 0; i < board.length; i++){
              if(board[i].length == boardsOvTime[currentTick][i].length){
                  for(int j = 0; j < board[i].length; j++){
                      if(board[i][j] != boardsOvTime[currentTick][i][j]){
                          return false;
                      }
                  }
              }else{
                  return false;
              }
          }
          return true;
      }else{
          return false;
      }
    }
    
    @Override
    public String toString(){
        String ret="";
        for(int i = 0; i< boardsOvTime[currentTick].length; i++){
            for(int j=0; j<boardsOvTime[currentTick][0].length; j++){
                ret += boardsOvTime[currentTick][i][j];
            }
            ret+="\n";
        }
        return ret;
    }
}
