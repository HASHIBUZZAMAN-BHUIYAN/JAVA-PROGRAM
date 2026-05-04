// ============================================================
//  Circular Buffer with Java Monitor, Combined Single File for online compiler
// ============================================================

// Buffer interface
interface Buffer {
    void set( int value );
    int  get();
}

// CircularBuffer (Fig. 6.6) 
class CircularBuffer implements Buffer {

    private int buffers[]     = { -1, -1, -1 };
    private int occupiedBuffers = 0;
    private int readLocation    = 0;
    private int writeLocation   = 0;

    public synchronized void set( int value ) {
        String name = Thread.currentThread().getName();

        while ( occupiedBuffers == buffers.length ) {
            try {
                System.out.println( "\nAll buffers full.  " + name + " waits." );
                displayState( name + " waits (buffer full)" );
                wait();
            }
            catch ( InterruptedException e ) { e.printStackTrace(); }
        }

        buffers[ writeLocation ] = value;
        ++occupiedBuffers;
        displayState( name + " writes " + buffers[ writeLocation ] );
        writeLocation = ( writeLocation + 1 ) % buffers.length;
        notify();
    }

    public synchronized int get() {
        String name = Thread.currentThread().getName();

        while ( occupiedBuffers == 0 ) {
            try {
                System.out.println( "\nAll buffers empty.  " + name + " waits." );
                displayState( name + " waits (buffer empty)" );
                wait();
            }
            catch ( InterruptedException e ) { e.printStackTrace(); }
        }

        int readValue = buffers[ readLocation ];
        --occupiedBuffers;
        displayState( name + " reads " + readValue );
        readLocation = ( readLocation + 1 ) % buffers.length;
        notify();
        return readValue;
    }

    public void displayState( String operation ) {
        StringBuffer out = new StringBuffer( operation );
        out.setLength( 40 );
        out.append( "\nbuffers occupied: " + occupiedBuffers );
        out.append( "\nbuffers:  " );
        for ( int i = 0; i < buffers.length; i++ )
            out.append( "  " + buffers[ i ] + "  " );
        out.append( "\n          " );
        for ( int i = 0; i < buffers.length; i++ )
            out.append( " ---- " );
        out.append( "\n          " );
        for ( int i = 0; i < buffers.length; i++ ) {
            if      ( i == writeLocation && writeLocation == readLocation ) out.append( "  WR  " );
            else if ( i == writeLocation )                                  out.append( "  W   " );
            else if ( i == readLocation  )                                  out.append( "  R   " );
            else                                                            out.append( "      " );
        }
        out.append( "\n" );
        System.out.println( out );
    }
}

// Producer
class Producer extends Thread {
    private Buffer sharedLocation;

    public Producer( Buffer shared ) {
        super( "Producer" );
        sharedLocation = shared;
    }

    public void run() {
        for ( int count = 1; count <= 10; count++ ) {
            try { Thread.sleep( (int)( Math.random() * 300 ) ); }
            catch ( InterruptedException e ) { e.printStackTrace(); }
            sharedLocation.set( count );
        }
        System.out.println( "\n" + getName() + " done producing.\nTerminating " + getName() + "." );
    }
}

// Consumer 
class Consumer extends Thread {
    private Buffer sharedLocation;

    public Consumer( Buffer shared ) {
        super( "Consumer" );
        sharedLocation = shared;
    }

    public void run() {
        int sum = 0;
        for ( int count = 1; count <= 10; count++ ) {
            try { Thread.sleep( (int)( Math.random() * 300 ) ); }
            catch ( InterruptedException e ) { e.printStackTrace(); }
            sum += sharedLocation.get();
        }
        System.out.println( "\n" + getName() + " read values totaling: " + sum + ".\nTerminating " + getName() + "." );
    }
}

// CircularBufferTest  (entry point)
public class CircularBufferTest {

    public static void main( String[] args ) {

        CircularBuffer sharedLocation = new CircularBuffer();
        sharedLocation.displayState( "Initial State" );

        Producer producer = new Producer( sharedLocation );
        Consumer consumer = new Consumer( sharedLocation );

        producer.start();
        consumer.start();
    }
}
