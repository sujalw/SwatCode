/*
 * Problem Statement:
 * 
 * Find the edit distance between two given strings. Edit distance is the 
 * minimum number of operations required to modify one string into the second 
 * one or vice versa. The operations allowed are as follows:
 * 
 * 1. Delete a character
 * 2. Replace a character with another one
 * 3. Insert a character
 * 4. Swap by switching two adjacent letters
 * 
 * For example,
 * 
 * editDistance("am", "ma") = 2 (without swap)
 *                          = 1 (with swap)
 * 
 * editDistance("google", "gogol")  = 3 (without swap)
 *                                  = 2 (with swap)
 *  
 */

/**
 *
 * @author Sujal
 */
public class EditDistance {

    /*
     * Cost of operations
     */
    final int DELETE_COST = 1;
    final int INSERT_COST = 1;
    final int REPLACE_COST = 1;
    final int SWAP_COST = 1;

    public static void main(String[] args) {
        new EditDistance();
    }

    public EditDistance() {
        String s1 = "google";
        String s2 = "gogol";
        
        int dist = getEditDistance(s1, s2);

        System.out.println("String 1 = " + s1);
        System.out.println("String 2 = " + s2);
        System.out.println("Edit distance = " + dist);
    }

    /**
     * 
     * @param s1 First string
     * @param s2 Second string
     * @return -1 if either of the input strings is null. Else returns the 
     * edit distance between the two input strings
     */
    private int getEditDistance(String s1, String s2) {
        return getEditDistance(s1.toCharArray(), s2.toCharArray());
    }

    private int getEditDistance(char[] s1, char[] s2) {

        /*
         * Return -1 if either of the input strings is null
         */
        if (s1 == null || s2 == null) {
            return -1;
        }

        /*
         * Each character in the 2nd string requires an insertion operation.
         */
        if (s1.length == 0) {
            return s2.length * INSERT_COST;
        }
        
        /*
         * Each character in the 1st string requires a delete operation.
         */
        if (s2.length == 0) {
            return s1.length * DELETE_COST;
        }

        /*
         * This matrix stores the edit distances between every possible 
         * substrings two input strings.
         */
        int[][] distance = new int[s1.length + 1][s2.length + 1];

        /*
         * Initialize 1st row. Each character in the 2nd string requires an
         * insertion operation.
         */
        for (int i = 0; i < s2.length + 1; i++) {
            distance[0][i] = i * INSERT_COST;
        }

       /*
         * Initialize 1st column. Each character in the 1st string requires a
         * delete operation.
         */
        for (int i = 0; i < s1.length + 1; i++) {
            distance[i][0] = i * DELETE_COST;
        }

        //printDistance(distance);

        // compute the distance matrix
        for (int i = 1; i <= s1.length; i++) {
            for (int j = 1; j <= s2.length; j++) {
                if (s1[i - 1] == s2[j - 1]) {
                    // chars are same
                    distance[i][j] = distance[i - 1][j - 1];
                } else {
                    /*
                     * Characters are not same. Compute the edit distance by
                     * considering all possible operations like 'delete', 
                     * 'insert', 'replace' and 'swap' and take the minumum 
                     * value.
                     */

                    /*
                     * Distance with deletion is equal to the distance of 
                     * strings by ignoring the current character from 2nd 
                     * string and adding the deleting cost.
                     */
                    int delCost = distance[i - 1][j] + DELETE_COST;

                    /*
                     * Distance with insertion is equal to the distance of 
                     * strings by ignoring the current character from 1st 
                     * string and adding the insertion cost.
                     */
                    int insCost = distance[i][j - 1] + INSERT_COST;

                    /*
                     * Distance with replacement. Distance is equal to the 
                     * distance of strings by ignoring the current characters 
                     * from both the strings and adding the replacement cost.
                     */
                    int repCost = distance[i - 1][j - 1] + REPLACE_COST;

                    int swpCost = Integer.MAX_VALUE; // default value

                    /*
                     * Compute swap cost. Swap is possible only if there are
                     * atleast 2 characters in both the strings
                     */
                    if (i >= 2 && j >= 2) {
                        /*
                         * Check if swapping is possible by comparing the 
                         * current and previous characters of both the strings.
                         */
                        if (s1[i - 1] == s2[j - 2] && s1[i - 2] == s2[j - 1]) {
                            /*
                             * Distance is equal to the distance of strings by
                             * ignoring the current and previous characters 
                             * from both the strings and adding the swapping
                             * cost.
                             */
                            swpCost = distance[i - 2][j - 2] + SWAP_COST;
                        }
                    }

                    distance[i][j] = getMin(delCost, insCost, repCost, swpCost);
                }
            }
        }

        //printDistance(distance);

        return distance[distance.length - 1][distance[0].length - 1];
    }

    private void printDistance(int[][] distance) {
        for (int i = 0; i < distance.length; i++) {
            for (int j = 0; j < distance[0].length; j++) {
                System.out.print(distance[i][j] + ", ");
            }
            System.out.println("");
        }
    }

    private int getMin(int c1, int c2, int c3, int c4) {
        return Math.min(Math.min(c1, c2), Math.min(c3, c4));
    }
}