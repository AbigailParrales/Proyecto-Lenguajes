
package producerconsumer;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer extends Thread {
    Buffer buffer;
    
    Producer(Buffer buffer) {
        this.buffer = buffer;
    }
    
    @Override
    public void run() {
        System.out.println("Running Producer...");
        String productNum = "1234567890";
        String productOps = "+-*/";
        Random r = new Random(System.currentTimeMillis());
        String product;
        
        for(int i=0 ; i<5 ; i++) {
            product = "("+productOps.charAt(r.nextInt(productOps.length()))+" "+productNum.charAt(r.nextInt(productNum.length()))+" "+productNum.charAt(r.nextInt(productNum.length()))+")";
            this.buffer.produce(product);
            //System.out.println("Producer produced: " + product);
            Buffer.print("Producer produced: " + product);
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}