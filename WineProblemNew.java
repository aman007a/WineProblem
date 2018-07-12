package WineProblem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;



public class WineProblemNew {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        HashMap<String,ArrayList<String>> wineWishlist = new HashMap<>();
        Set<String> wineList = new HashSet<>();
        int wineSold = 0;
        HashMap<String,ArrayList<String>> finalList = new HashMap<>();
        
        File file = new File("https://s3.amazonaws.com/br-user/puzzles/person_wine_3.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while((line = br.readLine()) != null){
            String[] nameAndWine = line.split("\\s");
            String name = nameAndWine[0];
            String wine = nameAndWine[1];
            
            if(!wineWishlist.containsKey(wine)){
                wineWishlist.put(wine, new ArrayList<String>());
                
            }
            wineWishlist.get(wine).add(name);
            
            wineList.add(wine);
        }
        br.close();
        
        for(String wine : wineList){
            int maxSize = wineWishlist.get(wine).size();
            int counter = 0;
            while(counter < 10){
                int i = (int)(Math.random() * maxSize);
                String person = wineWishlist.get(wine).get(i); //get the first person on the wishlist
                if(!finalList.containsKey(person)){
                    finalList.put(person, new ArrayList<String>());
                }
                if(finalList.get(person).size() < 3){
                    finalList.get(person).add(wine);
                    wineSold++;
                    break;
                }
                counter++;
           }
        }
        
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("finalOutput.txt")));
        bw.write(String.valueOf(wineSold));
        bw.newLine();
        for(String person: finalList.keySet()){
            for(String wines: finalList.get(person)){
                bw.write(person + " " + wines);
                bw.newLine();
            }
        }
        bw.close();
        
        
    }
}