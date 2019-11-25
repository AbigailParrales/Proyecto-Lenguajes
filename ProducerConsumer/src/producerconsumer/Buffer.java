
package producerconsumer;

import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffer {
    
    private Stack<String> buffer;
    private final SchemeSolver solver;
    private int cSize, uSize;
    private boolean isFull;
    
    Buffer() {
        this.buffer = new Stack<>();
        this.solver = new SchemeSolver();
        this.cSize=0;
        this.uSize=5;
        this.isFull = false;
    }

    public void setuSize(int uSize) {
        this.uSize = uSize;
    }
	
	public boolean isFull() {
		if(this.cSize<this.uSize){
            this.isFull = false;
        } else {
			this.isFull = true;
		}
		
		return this.isFull;
	}
    
    synchronized String consume() {
        float result;
        String product;
        
        if(this.buffer.empty()){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        String thisProduct = this.buffer.pop();
        this.solver.setA(thisProduct.charAt(3) - 0x30);
        this.solver.setB(thisProduct.charAt(5) - 0x30);
        this.solver.setOp(thisProduct.charAt(1));
        result = this.solver.solve();
        product = thisProduct + " returns --> " + result;
		cSize--;
		
        notify();
        
        return product;
    }
    
    synchronized void produce(String product) {
        if(this.isFull()) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
		this.buffer.add(product);
        cSize++;
        
        notify();
    }
    
    static int count = 1;
    synchronized static void print(String string) {
        System.out.print(count++ + " ");
        System.out.println(string);
    }
    
}
