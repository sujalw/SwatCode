/*
 * Problem Statement:
 * 
 * -----
 * We want to describe numbers using character strings. The desired program 
 * would read integers from STDIN,  one number per line. These integers would 
 * be converted to their character string representations (described below) by 
 * your program and emitted to STDOUT, also one string per line. 
 * 
 * Limits: Every input integer would be in the inclusive range [0, 2^50 - 1].
 * -------
 * 
 * Motivation for character string representation:
 * 
 * Let us use the characters [a..c] for the examples below. Please use the full 
 * range [a..z] for the final implementation. Also, we impose the restriction 
 * that the character sequences would be non-descending. Which means "abcc" 
 * and "aabc" are valid strings, but "cb" and "bac" are not. 
 * 
 * If we order such strings first by length and then lexicographically, we get 
 * the following ordering:
 * a, b, c, aa, ab, ac, bb, bc, cc, aaa, ...
 * 
 * Note that the list of 3 character strings can be generated from the list of 
 * 2 character strings by inserting appropriate prefixes. Another way of 
 * listing the above set of strings is as follows:
 * 
 * length 1: [a, b, c]
 * length 2: [a + [a, b, c], b + [b, c], c + [c]] --> [aa, ab, ac, bb, bc, cc]
 * length 3: [a + [aa, ab, ac, bb, bc, cc], b + [bb, bc, cc], c + [c]]
 * 
 * We use the ordering scheme defined by the examples above to convert an 
 * integer to its string representation. The i'th number (starting at 0) 
 * corresponds to the i'th string in the above list.
 * 
 * -------
 * Some examples for testing purposes:
 * 0 : a
 * 4 : e
 * 12 : m
 * 25 : z
 * 26 : aa
 * 27 : ab
 * 39 : an
 * 46 : au
 * 51 : az
 * 52 : bb
 * 54 : bd
 * 93 : cs
 * 100 : cz
 * 376 : zz
 * 700 : att
 * 729 : bbc
 * -------
 * 
 * Hint:
 * Let n be the number of characters in the string. Then from i in [1 .. n], 
 * and for j in [0 .. 25] try to construct COUNT[i][j] of number of strings of 
 * length i that start with the j'th character 'a' + j. Then for every input 
 * integer, use the COUNT[i][j] matrix to calculate the first character and the 
 * length of the desired string representation. 
 * 
 * Now, observe the following identities to calculate the rest of the 
 * characters:
 * 
 * convert("dde") - convert("ddd") = convert("de") - convert("dd")
 * convert("pqr") - convert("ppp") = convert("qr") - convert("pp")
 */

/**
 *
 * @author Sujal
 */
public class NumberToCharacterString {
    
    final int noOfAlphabets = 26;
    long[][] count;
    long[] totCountForLength;
    
    final int add = 97; // 65 for capital letters, 97 for small case letters
    /**
     * Constructs the matrix 'count' as per the hint. 
     * @param len Maximum length of string to be computed
     */
    private void constructCount(int len) {
        
        if(len <= 0) {
            return;
        }
                
        count = new long[len][noOfAlphabets];
        totCountForLength = new long[len];
        
        /*
         * initialize row 1. Number of strings of length 1 starting with any
         * character is 1
         */        
        for(int colNo=0 ; colNo<noOfAlphabets ; colNo++) {
            count[0][colNo] = 1;
        }
                
        /*
         * For all subsequent rows, value in column j is the sum of all values
         * in previous row starting from column j. Values are computed in 
         * reverse order for computational efficiency.
         */
        for(int row=1 ; row<len ; row++) {
            int prevRow = row - 1;
            int prevSum = 0;
            for(int column=noOfAlphabets-1 ; column>=0 ; column--) {
                prevSum += count[prevRow][column];
                count[row][column] = prevSum;
            }
            totCountForLength[prevRow] = prevSum;
        }
        
        // compute total count for last row
        int row = len - 1;
        long sum = 0;
        for(int i=0 ; i<noOfAlphabets ; i++) {
            sum += count[row][i];
        }
        totCountForLength[row] = sum;
    }
    
    private int getLengthOfOutputString(long num) {
        int len = 0;
        
        /*
         * Loop through totCountForLength to find the length of desired output
         * string
         */
        int sum = 0;
        for(int i=0 ; i<totCountForLength.length ; i++) {
            sum += totCountForLength[i];
            
            if(num <= sum) {
                len = i+1;
                break;
            }
        }
        
        return len;
    }
    
    /**
     * @param num input number
     * @param len length of desired output string
     * @return First character (in column number) of desired output string
     */
    private byte getFirstChar(long num, int len) {
        byte firstCharColNo = 0;
        
        /*
         * Loop through the (len-1) row of count array to find the first 
         * character
         */
        int row = len - 1;
        long sum = 0;
        
        if(row > 0) {
            for(int i=0 ; i<row ; i++) {
                sum += totCountForLength[i];
            }            
        }
        
        for(byte column=0 ; column<count[row].length ; column++) {
            sum += count[row][column];
            if(num < sum) {
                firstCharColNo = column;
                break;
            }
        }
        
        return firstCharColNo;
    }
    
    private void printCount() {
        for(int i=0 ; i<count.length ; i++) {
            for(int j=0 ; j<count[0].length ; j++) {
                System.out.print(count[i][j] + ", ");
            }
            System.out.println("");
        }
    }
    
    private void printCountOfLengths() {
        for(int i=0 ; i<totCountForLength.length ; i++) {
            System.out.println(totCountForLength[i]);
        }
    }
    
    public NumberToCharacterString() {
        StringBuilder output = new StringBuilder();
        
        constructCount(7);
        //printCount();
        //printCountOfLengths();
        
        long num = 445786;
        //System.out.println("num = " + num);
        
        int len = getLengthOfOutputString(num);
        //System.out.println("len = " + len);
        
        
        /*
         * Logic:
         * If len=3 and first char='b' then by the given identities, 
         * 
         *      b** - bbb = ** - bb
         * =>   b** - ** = bbb - bb
         * =>   ** = b** - bbb + bb
         * 
         *      now solve for **
         */
        byte firstCharNo = getFirstChar(num, len);
        output.append((char)(firstCharNo + add));
        while(len > 1) {
            
            /*
             * get number represented by string where all chars=firstChar of 
             * length len and len-1
             */
            long num1 = getNumber(firstCharNo, len);
            long num2 = getNumber(firstCharNo, len-1);
            num = num - num1 + num2;
            len = getLengthOfOutputString(num);            
            firstCharNo = getFirstChar(num, len);
            
            output.append((char)(firstCharNo + add));
        }
        
        System.out.println("Output = " + output.toString());
        
        //System.out.println("First char = " + firstCharNo);
    }
    
    private long getNumber(byte firstCharNo, int len) {
        long num = 0;
        
        // add counts from all strings with length<len
        for(int i=0 ; i<len-1 ; i++) {
            num += totCountForLength[i];
        }
        
        /*
         * add the count of strings of length 'len' where first character is 
         * any of the characters prior to given firstChar
         */
        for(int i=0 ; i<firstCharNo ; i++) {
            num += count[len-1][i];
        }
        
        return num;
    }
        
    public static void main(String[] args) {
        new NumberToCharacterString();
    }
}
