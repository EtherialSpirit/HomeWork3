import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.TreeMap;
import java.util.Map;

import java.net.URL;
 
public class Home {
    
    public static void main(String[] args) {
        try {
        	
        	Home main = new Home();
        	File file = main.getFileFromResources("NewText.txt");
            //File file = new File("c:/NewText.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);          
            String line = reader.readLine();
            
            TreeMap<String, TreeMap<String, Integer>> purchases = new TreeMap<String, TreeMap <String, Integer>>();
            while (line != null) {  
            	
            	String[] linechar = line.split(" ");
            	String people = linechar[0];
                String prod = linechar[1];
                Integer quantity = Integer.parseInt(linechar[2]);
                
                if (!purchases.containsKey(people))
                	purchases.put(people, new TreeMap <String, Integer>());
     
                TreeMap <String, Integer> temp = purchases.get(people);
     
                if (!temp.containsKey(prod))
                    temp.put(prod,0);
     
                Integer tempQuantity = temp.get(prod);
     
                temp.put(prod, tempQuantity + quantity);
                           
                line = reader.readLine();
            }

            for(Map.Entry<String, TreeMap <String, Integer>> entry : purchases.entrySet()) {
            
            	 String key = entry.getKey();
                 TreeMap <String, Integer> value = entry.getValue();
      
                 System.out.println(key + ":");
      
                 for(Map.Entry<String,Integer> product : value.entrySet()) {
                     String keyProduct = product.getKey();
                     Integer valueProduct = product.getValue();
      
                     System.out.println(keyProduct + " " + valueProduct);
                 }
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File getFileFromResources(String fileName) {

    	ClassLoader classLoader = getClass().getClassLoader();

    	URL resource = classLoader.getResource(fileName);
    	if (resource == null) {
    	throw new IllegalArgumentException("file is not found!");
    	} else {
    	return new File(resource.getFile());
    	}
 
}
}


