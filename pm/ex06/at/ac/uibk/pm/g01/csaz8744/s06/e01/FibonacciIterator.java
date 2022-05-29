package at.ac.uibk.pm.g01.csaz8744.s06.e01;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * FibonacciIterator
 */
public class FibonacciIterator implements Iterator<BigInteger>{

    private BigInteger fibCurrent;
    private BigInteger fibLast;

    private final long maxIterations;
    private long iteration;

    public FibonacciIterator(long n) {
        if(n < 1){
            throw new IllegalArgumentException("Only integers bigger then zero");
        }
        this.maxIterations = n;
        this.iteration = 0;
        this.fibCurrent = BigInteger.ONE;
        this.fibLast = BigInteger.ZERO;
    }

    private void fibNext(){
        BigInteger tmp = fibCurrent;
        fibCurrent = fibCurrent.add(fibLast);
        fibLast = tmp;
    }

    @Override
    public BigInteger next() {
        if(!hasNext()){
            throw new NoSuchElementException("Maximum iteration reached");
        }
        fibNext();
        iteration++;
        return fibLast;
    }
    
    @Override
    public boolean hasNext() {
        return iteration < maxIterations;
    }
}