import java.io.*;

public class TestA2Phase2 {
  
  public static void main( String args[] ) {
    int[][] b0, b1, b2, b3;
    GameOfLife g;
    
    b0 = new int[][]{ { 0, 0, 1 }, { 1, 1, 1 }, { 0, 1, 0 } };
    b1 = new int[][]{ { 0, 0, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
    b2 = new int[][]{ { 0, 1, 0 }, { 1, 0, 1 }, { 1, 0, 1 } };
    b3 = new int[][]{ { 0, 1, 0 }, { 1, 0, 1 }, { 0, 0, 0 } };
    
    System.out.println( "Phase 2 Testing (using file \"testFile.txt\")" );
    
    try {
      g = new GameOfLife( "testFile.txt", 10 );
    } catch ( IOException e ) {
      System.out.println( "Couldn't read a board from testFile.txt. Aborting tests!" );
      System.out.println( e.getMessage() );
      e.printStackTrace();
      return;
    } // end try-catch
    
    System.out.println( "\nAt time tick 0:" );
    System.out.println( g );
    if ( ! g.equals(b0) ) {
      System.out.println( "Either starting board not initialized properly or method equals doesn't work" );
    } // end if
    g.evolveOnce();
    System.out.println( "\nAt time tick 1:" );
    System.out.println( g );
    if ( ! g.equals(b1) ) {
      System.out.println( "Either starting board not evolved properly or method equals doesn't work" );
    } // end if
    g.evolveOnce();
    System.out.println( "\nAt time tick 2:" );
    System.out.println( g );
    if ( ! g.equals(b2) ) {
      System.out.println( "Either time tick 1 board not evolved properly or method equals doesn't work" );
    } // end if
    g.evolveOnce();
    System.out.println( "\nAt time tick 3:" );
    System.out.println( g );
    if ( ! g.equals(b3) ) {
      System.out.println( "Either time tick 2 board not evolved properly or method equals doesn't work" );
    } // end if
  } // end main
  
} // end class TestA2Phase2
