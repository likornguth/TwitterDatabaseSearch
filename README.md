# DSA_Assignment3

Problem 2 - Searching a Twitter Dataset (66 pts)
The file coachella_tweets.csv contains 3882 tweets discussing the Coachella 2015 music festival. This dataset was originally created
for the purpose of training and testing sentiment analysis models, identifying if a users view of the festival is positive or negative.
In this assignment, you will not work on sentiment, but instead use various Map and Set implementations in the Java standard library to index and query this database.

In the .csv file, each row contains one tweet. Each row has three fields (columns), separated by commas. The file CsvReader.java
already provides a class that reads in this format (described below), so you do not have to re-implement this. The fields are

The username of the author.
The content of the tweet.
The date and time of the tweet, formatted as DD/MM/YY hh:mm, where the hour format is in a 24 hour format. For example “1/7/15 15:02” (which is 3:02pm).
The goal of this problem is to read the complete data into memory and index it using different maps. Specifically, you will index
the tweets using

A java.util.HashMap in which the keys are usernames (of type String), which allows you to efficiently find all tweets sent by a user.
A java.util.HashMap in which the keys are keywords (of type String) appearing in the tweets, which allows you to efficiently find all tweets mentioning a specific term.
A java.util.TreeMap in which the keys are dates and times (represented as instances of the class DateTime. The TreeMap allows you to find tweets by a specific date and time and also efficiently retrieve tweets in a certain time range. A TreeMap is a balanced Binary Search Tree. (see below)
You may find it useful to look at the API reference for each class.

In all three maps, the values should be Lists containing instances of the class Tweet. The maps will be instance variables of the TweetDB class.

Part 1 - Reading the CSVfile and Indexing by Username. (18 pts)
The class CsvReader contains code to read a CSV file. The constructor takes a BufferedReader as a parameter and opens the file.
The CsvReader instance then provides a String[] nextLine() method, which returns a String array of the individual fields in each row.
The method returns null once the reader has reached the end of the file.

The file Tweet represents a tweet.The constructor takes the following parameters: username, content, and time stamp as a DateTime instance.
DateTime already comes with a constructor that parses the date and time in a string format, as described above. The dates/times can be directly passed to the constructor of the DateTime class to create a corresponding DateTime instance.

TODO: First, in the TweetDB class, complete the constructor. The parameter to the constructor is the pathname of a data file (such as "coachella_tweets.csv").
Using a CsvReader, read through the input file and convert each row into a Tweet instance.
Then, insert each Tweet into the tweetsPerUser hash map, indexed by the username. Recall that the values in the hash map should be Lists of Tweets (a user may have tweeted multiple times).
Finally, write the method. public List<Tweet> getTweetsByUser(String user) that returns all tweets written by a user. You can test the functionality in the main method, but the graders will call your methods from their own tester program.

Part 2 - Indexing by Keyword. (15 pts)
TODO: Update the constructor TweetDB so that each Tweet is additionally indexed by the the words that appear in the tweet content. You should add the tweets into the tweetsPerKeyword hashmap.
You may want to write an additional method for this, which removes punctuation and obtains keywords from the tweet. Do not spend a lot of time preprocessing the tweet text for this assignment. Simply stripping out common punctuation symbols and splitting by whitespace is sufficient.
Then, complete the method public List<Tweet> getTweetsByKeyword(String keyword), which returns all tweets with a given keyword.
Again, you can test this functionality in the main method.

Part 3 - Indexing by Date/Time (18 pts)
TODO:
Step 1) Update the constructor TweetDB so that each Tweet is additionally indexed by its DateTime object. You should add the tweets into the tweetsByTime tree map. To use the DateTime instances as keys, you need to modify that class to implement Comparable.
Step 2) Write the method public List<Tweet> getTweetsInRange(DateTime start, DateTime end) that returns a list of all tweets between a start time (inclusive) and end time (exclusive).
Use the TreeMap.subMap method. This method is similar to what you implemented in Programming part 1, that is it allows you to get all the keys in a certain range on a BST.
Again, you can test this functionality in the main method.

Part 4 - Removing Duplicates (15 pts)
You may have noticed that the output of your various search method contains duplicates. To remove these duplicates, update your getTweetsByUser, getTweetsByKeyword and getTweetsInRange method in the following way. Before returning the results, insert all retrieved tweets into a java.util.HashSet. Then turn the set back into a list.
To make this work properly, you need to modify the Tweet and DateTime classes and override both int hashCode() and boolean equals(Object other). Implement appropriate hash functions.
