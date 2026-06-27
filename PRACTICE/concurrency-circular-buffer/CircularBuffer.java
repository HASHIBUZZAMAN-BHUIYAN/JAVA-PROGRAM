// Fig. 6.6: CircularBuffer.java
// CircularBuffer synchronizes access to a shared circular buffer.

public class CircularBuffer implements Buffer {

    // each array element is a buffer
    private int buffers[] = { -1, -1, -1 };

    // occupiedBuffers maintains count of occupied buffers
    private int occupiedBuffers = 0;

    // variables that maintain read and write buffer locations
    private int readLocation  = 0;
    private int writeLocation = 0;

    // place value into buffer
    public synchronized void set( int value ) {

        // get name of thread that called this method
        String name = Thread.currentThread().getName();

        // while buffer full, place thread in waiting state
        while ( occupiedBuffers == buffers.length ) {
            // output thread and buffer information, then wait
            try {
                System.err.println( "\nAll buffers full. " +
                    name + " waits." );
                displayState( name + " waits (buffer full)" );
                wait(); // wait until space is available
            } // end try
            catch ( InterruptedException exception ) {
                exception.printStackTrace();
            } // end catch
        } // end while

        // place value in writeLocation of buffers
        buffers[ writeLocation ] = value;

        // one more buffer is occupied
        ++occupiedBuffers;

        displayState( name + " writes " + buffers[ writeLocation ] );

        // update writeLocation for future write operation
        writeLocation = ( writeLocation + 1 ) % buffers.length;

        notify(); // return a waiting thread to ready state
    } // end method set

    // return value from buffer
    public synchronized int get() {

        // get name of thread that called this method
        String name = Thread.currentThread().getName();

        // while buffer is empty, place thread in waiting state
        while ( occupiedBuffers == 0 ) {
            // output thread and buffer information, then wait
            try {
                System.err.println( "\nAll buffers empty. " +
                    name + " waits." );
                displayState( name + " waits (buffer empty)" );
                wait(); // wait until buffer contains new values
            } // end try
            catch ( InterruptedException exception ) {
                exception.printStackTrace();
            } // end catch
        } // end while

        // obtain value at current readLocation
        int readValue = buffers[ readLocation ];

        // one fewer buffer is occupied
        --occupiedBuffers;

        // display consumed value
        System.err.println( "\n" + name + " reads " + readValue + " " );
        displayState( name + " reads " + readValue );

        // update readLocation for future read operation
        readLocation = ( readLocation + 1 ) % buffers.length;

        notify(); // return a waiting thread to ready state

        return readValue;
    } // end method get

    // display current operation and buffer state
    public void displayState( String operation ) {
        // build first line of state information
        StringBuffer outputLine = new StringBuffer( operation );
        outputLine.setLength( 40 );
        outputLine.append( "\nbuffers occupied: " + occupiedBuffers );
        outputLine.append( "\nbuffers:  " );

        for ( int i = 0; i < buffers.length; i++ )
            outputLine.append( "  " + buffers[ i ] + "  " );

        outputLine.append( "\n          " );

        for ( int i = 0; i < buffers.length; i++ )
            outputLine.append( " ---- " );

        outputLine.append( "\n          " );

        for ( int i = 0; i < buffers.length; i++ ) {
            if ( i == writeLocation && writeLocation == readLocation )
                outputLine.append( "  WR  " );
            else if ( i == writeLocation )
                outputLine.append( "  W   " );
            else if ( i == readLocation )
                outputLine.append( "  R   " );
            else
                outputLine.append( "      " );
        }

        outputLine.append( "\n" );
        System.err.println( outputLine );
    } // end method displayState

} // end class CircularBuffer
