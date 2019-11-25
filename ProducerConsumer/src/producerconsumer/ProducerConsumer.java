
package producerconsumer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ProducerConsumer {

    public static int nProd, nCons;
    public static Producer[] producers;
    public static Consumer[] consumers;
    
    public static void main(String[] args) {
        //Change by hand, 
        nProd = 3;
        nCons = 2;
        
        producers = new Producer[nProd];
        consumers = new Consumer[nCons];
        
        Buffer buffer = new Buffer();
        
        for(int i = 0; i<nProd; i++) {
            producers[i] = new Producer(buffer, i+1);
        }
        
        for(int j = 0; j<nCons; j++){
            consumers[j] = new Consumer(buffer, j+1);
        }
        
        for(int i = 0; i<nProd; i++) {
            producers[i].start();
        }
        
        for(int j = 0; j<nCons; j++){
            consumers[j].start();
        }
    }
    
}
