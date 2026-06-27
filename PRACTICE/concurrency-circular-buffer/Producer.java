// Producer.java
public class Producer extends Thread {
    private Buffer sharedLocation;

    public Producer( Buffer shared ) {
        super( "Producer" );
        sharedLocation = shared;
    }

    public void run() {
        for ( int count = 1; count <= 10; count++ ) {
            try {
                Thread.sleep( (int)( Math.random() * 500 ) );
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
            sharedLocation.set( count );
        }
        System.err.println( "\n" + getName() + " done producing.\n" +
            "Terminating " + getName() + "." );
    }
}
