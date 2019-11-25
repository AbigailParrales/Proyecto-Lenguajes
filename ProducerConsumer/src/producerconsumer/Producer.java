
package producerconsumer;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer extends Thread {
    Buffer buffer;
    int idN;
    
    Producer(Buffer buffer, int n) {
        this.buffer = buffer;
        this.idN = n;
    }
    
    @Override
    public void run() {
        while(true) {
			System.out.println("Running Producer"+Integer.toString(idN)+"...");
			String productNum = "1234567890";
			String productOps = "+-*/";
			//Random r = new Random(System.currentTimeMillis());
			
			String product;
			
			
			//product = "("+productOps.charAt(r.nextInt(productOps.length()))+" "+productNum.charAt(r.nextInt(productNum.length()))+" "+productNum.charAt(r.nextInt(productNum.length()))+")";
			product = "("+productOps.charAt(ThreadLocalRandom.current().nextInt(productOps.length()))+" "+productNum.charAt(ThreadLocalRandom.current().nextInt(productNum.length()))+" "+productNum.charAt(ThreadLocalRandom.current().nextInt(productNum.length()))+")";
			this.buffer.produce(product);
			//System.out.println("Producer produced: " + product);
			Buffer.print("Producer" + Integer.toString(this.idN) + " produced: " + product);
				
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
    }
    
}