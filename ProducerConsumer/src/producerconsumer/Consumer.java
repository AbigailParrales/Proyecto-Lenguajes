package producerconsumer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer extends Thread {
    Buffer buffer;
    int idN;
    
    Consumer(Buffer buffer, int n) {
        this.buffer = buffer;
        this.idN = n;
    }
    
    @Override
    public void run() {
        while(true){
			System.out.println("Running Consumer"+Integer.toString(idN)+"...");
			String product;
			
			product = this.buffer.consume();
			//System.out.println("Consumer consumed: " + product);
			Buffer.print("Consumer" + Integer.toString(this.idN) + " consumed: " + product);
				
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
    }
}