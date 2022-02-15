import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap; 
import java.util.TreeMap; 
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.*;
import java.io.File;

public class TweetDB {
   
    
    HashMap<String, List<Tweet>> tweetsPerUser;
    HashMap<String, List<Tweet>> tweetsPerKeyword;  
    TreeMap<DateTime, List<Tweet>> tweetsByTime;
    
    public TweetDB(String pathToFile) throws FileNotFoundException, IOException {
        tweetsPerUser = new HashMap<>();               
        tweetsPerKeyword = new HashMap<>();   
        tweetsByTime = new TreeMap<>();      
        
        File f = new File(pathToFile);
        if(!f.isFile()){
             throw new FileNotFoundException();
        }
        if(!f.canRead()){
            throw new IOException();
        }
        
        BufferedReader br = new BufferedReader(new FileReader(pathToFile));        
        CsvReader csv = new CsvReader(br);         
        
        String[] fields = csv.nextLine();  

        
        while (fields != null) {
            //this method loops through csv and prints tweet database
            
            String user = fields[0];
            String content = fields[1];
            String dtString = fields[2];
            DateTime dt = new DateTime(dtString);

            Tweet t = new Tweet(user, dt, content); 
            
            if(tweetsPerUser.containsKey(t.user)){
                // this means there is already an arraylist there
                tweetsPerUser.get(t.user).add(t);
                              
            }
            else{
                List<Tweet> tweetsByUser = new ArrayList<>();
                tweetsByUser.add(t);
                tweetsPerUser.put(t.user, tweetsByUser);
            }
            
            
            String[] keywords = parseTweet(t.content);
            for(String s: keywords){
                
                if(tweetsPerKeyword.containsKey(s)){
                    tweetsPerKeyword.get(s).add(t);
                }
                else{
                    List<Tweet> keyBucket = new ArrayList<>();
                    keyBucket.add(t);
                    tweetsPerKeyword.put(s, keyBucket);
                }
            }
          
            if(tweetsByTime.containsKey(t.datetime)){
                
                tweetsByTime.get(t.datetime).add(t);
                
            }
            else{
                
                List<Tweet> timeBucket = new ArrayList<>();
                timeBucket.add(t);
                tweetsByTime.put(t.datetime, timeBucket);
                
            }
            
            fields = csv.nextLine();
        }
        

        

    }
    
    public String[] parseTweet(String t){
        
        t.replaceAll("\\p{Punct}", " ");
        t.replaceAll("#", " ");
        
        String[] words = t.split(" ");
        return words;
        
    }
        
        
     
     
    
    public List<Tweet> getTweetsByUser(String user) {
        //return null; // replace this
         List<Tweet> theTweetsbyUser = new ArrayList<>();
        
        if(tweetsPerUser.containsKey(user)){
            theTweetsbyUser = tweetsPerUser.get(user);
        }
        
        
        HashSet<Tweet> tweetSet = new HashSet<>(theTweetsbyUser);
     
        theTweetsbyUser = new ArrayList<>(tweetSet);
        

     
        return theTweetsbyUser;
    }
    
    
    public List<Tweet> getTweetsByKeyword(String kw) {
        //return null; // replace this 
        
        List<Tweet> theTweetsbyKeyword = new ArrayList<>();
        if(tweetsPerKeyword.containsKey(kw)){
             theTweetsbyKeyword = tweetsPerKeyword.get(kw);
        }
        
        
        HashSet<Tweet> tweetSet = new HashSet<>(theTweetsbyKeyword);
        
        theTweetsbyKeyword = new ArrayList<>(tweetSet);
        
        return theTweetsbyKeyword;
    }
    
     public List<Tweet> getTweetsInRange(DateTime start, DateTime end) {
         //return null; // replace this 
         start = tweetsByTime.ceilingKey(start);     
         end = tweetsByTime.floorKey(end);
         
         SortedMap<DateTime, List<Tweet>> subMap = new TreeMap<>();
         subMap = tweetsByTime.subMap(start, end);
         
         Collection<List<Tweet>> collection = subMap.values();
         List<Tweet> theList = new ArrayList<>();
         
         for(List<Tweet> x: collection){
             for(Tweet y : x){
                 theList.add(y);
             }
         }
         
         HashSet<Tweet> tweetSet = new HashSet<>(theList);
         
         
         theList = new ArrayList<>(tweetSet); // an arraylist of all tweets in range
         
         
         
         
         
         return theList;
    }  
    
    public static void main(String[] args) {
          
        try {
            TweetDB tdb = new TweetDB("coachella_tweets.csv");
            //TweetDB tdb = new TweetDB("notAFile.csv");

           //System.out.println("--------------- Part 1: Search by username: @MsHGolightly -----------------");
            for(Tweet t : tdb.getTweetsByUser("MsHGolightly")){
                System.out.println(t);
            }
            
           //System.out.println("---------------- Part 2: Search by keyword: disappointing ----------------------");
           // 
            for(Tweet t : tdb.getTweetsByKeyword("disappointing")){
                System.out.println(t);
            }
            
            //System.out.println("-------------- Part 3: Search by date/time interval: 1/6/15 00:00 - 1/6/15 5:00 -----------------");
            //Part 3: Search by date/time interval          
            for(Tweet t : tdb.getTweetsInRange(new DateTime("1/6/15 00:00"), new DateTime("1/6/15 5:00"))){
                 System.out.println(t);
            }
            
            
        } catch (FileNotFoundException e) {
            System.out.println(".csv File not found.");
        } catch (IOException e) {
            System.out.println("Error reading from .csv file.");
        }
        
        
        
        
    }
    
}
