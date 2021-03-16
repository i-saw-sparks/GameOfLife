public class TestA2Phase1 {
  
  public static void main( String[] args ) {
    int[][] b0, b1, b2, b3;
    GameOfLife g;
    String[] state = { "dead", "live" };
    
    // Test with a hard-coded starting board (b0)
    
    b0 = new int[][]{ { 0, 0, 1 }, { 1, 1, 1 }, { 0, 1, 0 } };
    b1 = new int[][]{ { 0, 0, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
    b2 = new int[][]{ { 0, 1, 0 }, { 1, 0, 1 }, { 1, 0, 1 } };
    b3 = new int[][]{ { 0, 1, 0 }, { 1, 0, 1 }, { 0, 0, 0 } };
    
    System.out.println( "Phase 1 Testing:" );
    
    g = new GameOfLife( b0, 10 );
    
    System.out.println( "\nTesting the newState method:" );
    for ( int i = 1; i < 5; i++ ) {
      System.out.println( "  A live cell with " + i + " live neighbours will be " + state[g.newState( 1, i )] );
      System.out.println( "  A dead cell with " + i + " live neighbours will be " + state[g.newState( 0, i )] );
    } // end for
    
    // Multiple full transitions
    
    System.out.println( "\nNow trying multiple evolutions of the whole board:" );
    
    g = new GameOfLife( b0, 10 );
    System.out.println( "\nMaximum time tick allowed: " + g.getMaxTick() );
    
    System.out.println( "\nAt time tick 0:" );
    System.out.println( "Current time tick = " + g.getCurrentTick() );
    System.out.println( g );
    if ( ! g.equals(b0) ) {
      System.out.println( "Either starting board not initialized properly or method equals doesn't work" );
    } // end if
    g.evolveOnce();
    System.out.println( "\nAt time tick 1:" );
    System.out.println( "Current time tick = " + g.getCurrentTick() );
    System.out.println( g );
    if ( ! g.equals(b1) ) {
      System.out.println( "Either starting board not evolved properly or method equals doesn't work" );
    } // end if
    g.evolveOnce();
    System.out.println( "\nAt time tick 2:" );
    System.out.println( "Current time tick = " + g.getCurrentTick() );
    System.out.println( g );
    if ( ! g.equals(b2) ) {
      System.out.println( "Either time tick 1 board not evolved properly or method equals doesn't work" );
    } // end if
    g.evolveOnce();
    System.out.println( "\nAt time tick 3:" );
    System.out.println( "Current time tick = " + g.getCurrentTick() );
    System.out.println( g );
    if ( ! g.equals(b3) ) {
      System.out.println( "Either time tick 2 board not evolved properly or method equals doesn't work" );
    } // end if
    
    System.out.println( "Current time tick at very end: " + g.getCurrentTick() );
  } // end main 
  
} // end TestA2Phase1
