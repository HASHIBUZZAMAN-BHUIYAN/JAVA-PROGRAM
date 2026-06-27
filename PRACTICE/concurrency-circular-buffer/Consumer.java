// Consumer.java
public class Consumer extends Thread {
    private Buffer sharedLocation;

    public Consumer( Buffer shared ) {
        super( "Consumer" );
        sharedLocation = shared;
    }

    public void run() {
        int sum = 0;
        for ( int count = 1; count <= 10; count++ ) {
            try {
                Thread.sleep( (int)( Math.random() * 500 ) );
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
            sum += sharedLocation.get();
        }
        System.err.println( "\n" + getName() +
            " read values totaling: " + sum +
            ".\nTerminating " + getName() + "." );
    }
}
