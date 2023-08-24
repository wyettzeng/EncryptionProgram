import java.math.*;
import java.util.ArrayList;
import java.util.Scanner;

public class NthRoot {
    public static void main(String[] args)
    {
    	Scanner input = new Scanner(System.in);
    	System.out.println("Please enter the number whose Nth root is to be calculated : ");
    	String BigInt = input.next();

    	System.out.println("Please enter which root of the above number is to be calculated (N in Nth root) : ");
    	String Int = input.next();

    	BigInteger Number = new BigInteger(BigInt);
    	Integer N = new Integer(Int);

    	BigDecimal result;
    	result = NthRootfunc(Number,N);

    	System.out.println("The answer is : " + result);
    }

    /** returns the nth root of a number stored in a BigInteger as a BigDecimal
     * @return */
    public static BigDecimal NthRootfunc(BigInteger num,int n)
    {
        ArrayList<BigDecimal> arr = new ArrayList<BigDecimal>();
       	Integer i = 1;
		Integer j = 0;
        BigDecimal[] tArray = new BigDecimal[5];
        BigDecimal N = new BigDecimal(n);
        BigDecimal A = new BigDecimal(num);
        arr.add(0,A);

        while(true)
        {
		    tArray[0] = (arr.get(i-1).multiply(new BigDecimal(n-1)));
		    tArray[1] = ((arr.get(i-1)).pow(n-1));
		    tArray[2] = A.divide(tArray[1],6,RoundingMode.FLOOR);
		    tArray[3] = tArray[0].add(tArray[2]);
		    tArray[4] = tArray[3].divide(N,6,RoundingMode.FLOOR);

		    arr.add(i,tArray[4]);
	        i = i + 1;

	        if ((arr.get(i-1)).compareTo(arr.get(i-2)) == 0)
		    {
		        break;
	        }
       }

        //return arr.get(i-1);
        Decryption de = new Decryption();
        //de.returnValue((arr.get(i-1)).toBigInteger());
		return null;
    }
}