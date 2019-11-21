
package producerconsumer;

public class SchemeSolver {

    public char op;
    private int A,B;
    private float result;
    
    public SchemeSolver() {
        this.op = '+';
        this.result = 0;
        this.A = 0;
        this.B = 0;
    }

    public void setOp(char op) {
        this.op = op;
    }

    public void setA(int A) {
        this.A = A;
    }

    public void setB(int B) {
        this.B = B;
    }
    
    public float solve() {
        switch(this.op) {
            case('+'):
                this.result = this.A + (float)this.B;
                break;
            case('-'):
                this.result = this.A - (float)this.B;
                break;
            case('*'):
                this.result = this.A * (float)this.B;
                break;
            case('/'):
                this.result = this.A / (float)this.B;
                break;
        }
        
        return this.result;
    }
}
