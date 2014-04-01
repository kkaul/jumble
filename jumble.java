
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.ArrayList;

//********** jumble - begin
public class jumble 
{

    //********** rearrange - begin
    //Finds different arrangements of the string.
    public static String rearrange(char[] x) 
    {
        int newLowest = 0;            
        int newHighest = x.length - 1;

        while (newLowest < newHighest) 
        {
            int highest = newHighest;
            int lowest = newLowest;
            newLowest = x.length;    
            
            for (int i = lowest; i < highest; i++) 
            {
                
                if (x[i] > x[i + 1]) 
                {
                    char temp = x[i];
                    x[i] = x[i + 1];
                    x[i + 1] = temp;
                    
                    if (i < newLowest) 
                    {
                        newLowest = i - 1;
                        
                        if (newLowest < 0) 
                        {
                            newLowest = 0;
                        }
                    } 
                    
                    else if (i > newHighest) 
                    {
                        newHighest = i + 1;
                    }
                }
            }
        }

        return new String(x);
    }
    //********** rearrange - end



    //********** main - begin
    public static void main(String[] args) 
    {
        
        try 
        {
            //Reading the dictionary as a hashmap for faster navigation
            BufferedReader dictBufferedReader = new BufferedReader(new FileReader("wordlist.txt"));//enter path of dictionary file here

            HashMap<String, ArrayList<String>> dict = new HashMap<String, ArrayList<String>>();
            ArrayList<String> tempArrayList;
            String dictWord, key;
            int count = 0;
            System.out.print("Loading");

            //Check if rearranged word matches dictionnary word
            while ((dictWord = dictBufferedReader.readLine()) != null) 
            {
                dictWord = dictWord.toLowerCase();
                key = rearrange(dictWord.toCharArray());
                
                if (dict.containsKey(key)) 
                {
                    tempArrayList = dict.get(key);
                    
                    if (!tempArrayList.contains(dictWord)) 
                    {
                        dict.get(key).add(dictWord);
                    }
                } 

                else 
                {
                    ArrayList<String> t = new ArrayList<String>();
                    t.add(dictWord);
                    dict.put(key, t);
                }

                if (count % 1000 == 0) 
                {
                    System.out.print(".");
                }

                count++;
            }

            System.out.println();
            
            String result, userinput;
            
            //Enter your word
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter your jumble word:");
            userinput = in.readLine();

            result = userinput.toLowerCase();//convert userinput into lowercase as dictionary is all lowercase.

            String elements = result.substring(0, result.length());
            ArrayList<String> a = new ArrayList<String>();
            ArrayList<String> b = new ArrayList<String>();

            ArrayList<String> newWord = combinations(elements,a);
            String[] newArray = newWord.toArray(new String[newWord.size()]);

            int x = newArray.length;   
            for(String c : newArray)
            {

                key = rearrange(c.toCharArray());
                //Checks if word found already has been found, then does not put it in the list.
                if (dict.containsKey(key)) 
                {
                    
                    for (String s : dict.get(key)) 
                    {

                        if (!b.contains(s))
                        {
                            b.add(s);
                        }
                    }
                } 
            }
            
            //Check if no words found
            if (!b.isEmpty()) 
            {
                System.out.println(b);
            }
            
            else 
            {
                System.out.println("No words found!");
            }
        } 

        //if fails to read text file.
        catch (Exception e) 
        {
            System.err.println("Failed - Cannot read dictionary text file:Check your path.");
            return;
        }
    }
    //********* main - end


    //********* combinations - begin
    //recursively call combinations to find all possible combinations of letters in the string.
    public static ArrayList<String> combinations(String s, ArrayList<String> a) {
        return combinations("", s, a);
    }

    public static ArrayList<String> combinations(String prefix, String s, ArrayList<String> a) {
         
        if (s.length() > 0) {
             
            a.add(prefix + s.charAt(0));
            combinations(prefix + s.charAt(0), s.substring(1),a);
            combinations(prefix,s.substring(1),a);
            
        }
        return a;
        
    }
    //********** combinations - end

}
//********** jumble - end
