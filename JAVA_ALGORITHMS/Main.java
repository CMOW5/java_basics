import java.io.*;
import java.lang.Math;
import java.util.*;

public class Main {

	public static void main (String[] args) throws IOException {
		
		//File file = new File("file.txt");
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        final String iteratorSeparator = "|";
        final String numbersSeparator = " ";

        while ((line = buffer.readLine()) != null) {
          
        	line = line.trim();
        	String[] parts = line.split("\\" + iteratorSeparator);
        	String[] arrayToArrange = parts[0].trim().split(numbersSeparator);
        	int iterator = Integer.parseInt(parts[1].trim());

            int[] array = toIntArray(arrayToArrange);
            stupidSort(array,iterator);
            
            for( int number : array ){
                System.out.print(number);    
            }
            System.out.println("");
            
            
        	
        } //end while text
        
	}

    public static void stupidSort(int[] arrayToSort, int iterator) {

        boolean hasFinished = false;
        boolean hasChanged = false;
        //iterator--;

        while(!hasFinished){

            for (int i = 0; (iterator > 0) && (i < arrayToSort.length - 1) ; i++, iterator--)
            {

                if(arrayToSort[i+1] < arrayToSort[i])
                {
                    int auxValue = arrayToSort[i];
                    arrayToSort[i] = arrayToSort[i+1];
                    arrayToSort[i+1] = auxValue;
                    hasChanged = true;
                    break;
                }
            }

            if(iterator == 0)
                hasFinished = true;   
            else if (hasChanged)
                hasChanged = false;
            else
                hasFinished = true;
        }
         
    }

    public static int[] toIntArray(String[] toIntArray){
        return  Arrays.asList(toIntArray)
                      .stream()
                      .mapToInt(Integer::parseInt)
                      .toArray();
    } 

    

}

