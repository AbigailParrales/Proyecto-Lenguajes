
package producerconsumer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffer {
    
    private String buffer;
    private SchemeSolver solver;
    
    Buffer() {
        this.buffer = "";
        this.solver = new SchemeSolver();
    }
    
    synchronized String consume() {
        float result = 0;
        String product = "";
        
        if(this.buffer.equals("")) {
            try {
                wait(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        this.solver.setA(this.buffer.charAt(3) - 0x30);
        this.solver.setB(this.buffer.charAt(5) - 0x30);
        this.solver.setOp(this.buffer.charAt(1));
        result = this.solver.solve();
        product = this.buffer + " returns --> " + result;
        this.buffer = "";
        notify();
        
        return product;
    }
    
    synchronized void produce(String product) {
        if(!(this.buffer.equals(""))) {
            try {
                wait(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.buffer = product;
        
        notify();
    }
    
    static int count = 1;
    synchronized static void print(String string) {
        System.out.print(count++ + " ");
        System.out.println(string);
    }
    
}
