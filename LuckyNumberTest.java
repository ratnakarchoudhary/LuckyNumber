import junit.framework.*;

public class LuckyNumberTest {
    
    protected String[] value = new String[9];
    protected int[][] expectedNumbers = new int[9][];
    protected boolean[] expectedIsUnique = new boolean[9];

    LuckyNumber luckyNumber = new LuckyNumber();
    
    protected void setUp(){
        value[0] = null; // when no string is passed
        expectedNumbers[0] = null; // returned string for null
        expectedIsUnique[0] = true;
        
        value[1] = "4"; // when the string length has less than 7 character
        expectedNumbers[1] = null; // returned int[] string length has less than 7 character
        expectedIsUnique[1] = true;
        
        value[2] = "b" ; // when string is not a number
        expectedNumbers[2] = null; // returned int[] string is not a number
        expectedIsUnique = true;
        
        value[3] = "4b"; // when string is combination of string and a number
        expectedNumbers[3] = null; // returned int[] string is not a number
        expectedIsUnique[3] = true;
        
        value[4] = "472844278465445"; // when string lenght is more than 14 character
        expectedNumbers[4] = null; // returned int[] string is not a number
        expectedIsUnique[4] = true;
        
        value[5] = "569815571556"; // when string do not have unique numbers
        expectedNumbers[5] = new int[] {56,9,8,15,57,15,56}; // returned int[] string is not a number
        expectedIsUnique[5] = false;
        
        value[6] = "1234567"; // has 7 unique character of number
        expectedNumbers[6] = new int[] {1,2,3,4,5,6,7}; // returned int[] string is not a number
        expectedIsUnique[6] = true;
        
        value[7] = "4938532894754" ; // more than 7 less than 14 unique character of number
        expectedNumbers[7] = new int[] {49,38,53,28,9,47,54}; // returned int[] string is not a number
        expectedIsUnique[7] = true;
        
        value[8] = "49385328342546" ; // has 14 unique character
        expectedNumbers[8] = new int[] {49,38,53,28,34,25,46}; ; // returned int[] string is not a number
        expectedIsUnique[8] = true;
    }
    
    @Test
    
    public void testGenerateNumber() {
        int i =0;
        int[] generatedNumber;
        while(i<9) {
            generatedNumber = luckyNumber.generateNumber(value[i]);
            assertEquals(expectedNumbers[i], generatedNumber);
            i++;
        }
        
    }
    
    @Test
    
    public void testCheckWiningNumberIsUnique() {
        int i =0;
        boolean[] isUnique = new boolean[9];
        while(i<9) {
            isUnique[i] = luckyNumber.checkWiningNumberIsUnique(expectedNumbers[i]);
            assertEquals(isUnique[i], expectedIsUnique[i]);
            i++;
        }
    }
                
                
}
