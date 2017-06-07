
import java.util.Iterator;
import java.util.Set;
import java.util.Scanner;
import java.util.Properties;
import java.io.FileInputStream;
import java.util.Set;
import java.util.HashSet;

/**
 * The LuckNumber program implements an application that
 * displays wining number to the standard output.
 *
 * @author  Ratnakar Choudhary
 * @version 1.0
 * @since   2017-06-06
 */
public class LuckyNumber {
    
    /**
     * Set numbersGenerated will be used to check if the number generated is unique.
     */
    
    private Set<Integer> numbersGenerated = new HashSet<Integer>();
    
    
    /*
     * Main method 
     * @parm args - takes the string of array as input
     */
	public static void main(String[] args){
		/*System.out.println("please enter the values with comma seperated e.g.(1245, 549)");*/
        Scanner sc = new Scanner(System.in);
        try{
            // read the string from properties file will throw exception if file is not found
            String values = readStringFromPropertiesFile();//sc.nextLine();
            
            if(values != null) {
                // split the string based on comma (,)
                String[] valuesSplit = values.split("\\,");
            
                // call the api to generate the number and print the number
                new LuckyNumber().generateAndPrintNumbers(valuesSplit);
            }
        
            
            
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
	}
    
    /*
     * @method readStringFromPropertiesFile - This will read the string from properties file
     * it will return the value from the properties file stored at the same location as the executable jar file
     * 
     * @throws exception
     */
    public static String readStringFromPropertiesFile() throws Exception {
        try{
            Properties props = new Properties();
            FileInputStream fis = new FileInputStream("./newspaper.properties");
            props.load(fis);
            return props.getProperty("values");
        } catch(Exception e) {
            throw new Exception("Properties file is missing");
        }
    }
    
    /*
     * @method generateAndPrintNumbers - This method will try to
     * @param - String[] - passed based on the split on comma ,
     */
    public void generateAndPrintNumbers(String[] valuesSplit) {
        for(String value: valuesSplit) {
            value = value.replaceAll("\"","");
            // call generate number that converts the number into array of int
            int[] winingNumbers = this.generateNumber(value.trim());
            // if wining number is generated and is unique print the number
            if(winingNumbers != null && checkWiningNumberIsUnique(winingNumbers)) {
                printWiningNumbers(value, winingNumbers);
            }
        }
    }
    
    /*
     * @method printWiningNumbers - This method prints the string and the corresponding number in format as string -> number
     * @ param value - String  to be printed on the left
     * @ param winingNumber - int[] this is the array that will be printed on right
     */
    public void printWiningNumbers(String value, int[] winingNumbers) {
        int size = 0;
        // print the string passed followed by ->
        System.out.print(value+" -> ");
        // iterate through the int[] to print to the right of ->
        for(int winingNumber : winingNumbers) {
            System.out.print(winingNumber);
            size++;
            // if not last number print the space between number else do not add space
            if(size< winingNumbers.length) {
                System.out.print(" ");
            } else {
                System.out.println(" ");
            }
        }
    }
    
    /*
     * @method generateNumber - This method generate the number for the string passed to it
     * @ param value - String  that needs to be generate the number
     * @ return int[] this is the array that contains the number
     */
    public int[] generateNumber(String value) {
        // if the string is null or its legnth is not more than 7 or less than 14 number generation is not possible.
        if(value == null || value.length()<7 || value.length()>14) {
            return null;
        }
        // else 7 wining number
        int[] winingNumber = new int[7];
        
        // no of 2 digit number possible.
        int noOf2Digit = value.length()-7;
        char c1 ;
        char c2 ;
        int i =0;
        int j =0;
        // loop till all the characters are processed
        while(i<value.length()) {
            c1 = value.charAt(i);
            // check if the char is number or not else return null as number generation is not possible
            if(Character.isDigit(c1)) {
                // generate the number from the character
                int val1 = Character.getNumericValue(c1);
                // if number is less than 6, combining with next char(0-9) will generate 2 digit number of less than 60
                if(val1<6 && noOf2Digit >0 && i<value.length()-1){
                    c2 = value.charAt(i+1);
                    if(Character.isDigit(c2)) {
                        int val2 = Character.getNumericValue(c2);
                        winingNumber[j++] = val1*10+val2;
                        noOf2Digit --;
                        i+=2;
                    } else {
                        return null;
                    }
                // if number is more than 5 then do not generate 2 digit number as number will be more than 60.
                } else {
                    winingNumber[j++] = val1;
                    i++;
                }
            } else {
                return null;
            }
        }
        return winingNumber;
    }
    
    
    /*
     * @method -checkWiningNumberIsUnique - This checks if the number array has the unique number or not as the wining number should be unique
     *
     * @param - int[] - This is the int[] generated from string
     * @ return - boolean - this returns true if all the numbers are unique in given int[] else return false;
     */
    public boolean checkWiningNumberIsUnique(int[] winingNumbers) {
        // clear the HashSet for each generated number
        this.numbersGenerated.clear();
        // iterate and check the number
        for(int winingNumber: winingNumbers) {
            // if set do not contain add to hashset else return false as number is not unique
            if(!this.numbersGenerated.contains(winingNumber)) {
                this.numbersGenerated.add(winingNumber);
            } else{
                return false;
            }
        }
        // after each number is processed return true
        return true;
    }
}
