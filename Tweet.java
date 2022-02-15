/**
 * Represent a tweet, including the content, author's username
 * and time when it was posted. 
 */
public class Tweet {
    
    public String user;
    public DateTime datetime; 
    public String content;
    
    public Tweet(String user, DateTime datetime, String content) {
            this.user = user; 
            this.datetime = datetime;
            this.content = content;       
    }
    
    @Override
    public int hashCode() {
        final int prime = 29;
        int result = 1;
        result = prime * result
                + ((this.content == null) ? 0 : this.content.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj){
        
        
        if(this == obj){
            return true;
        }
        if(obj == null || this.getClass() != obj.getClass()){
            return false;
        }
        
        Tweet other = (Tweet) obj;
        if(other.user.equals(this.user) && other.content.equals(this.content) && other.datetime.equals(this.datetime)){
            return true;
        }
        else{
            
            return false;
        }
    }
    
    public String toString(){
        return "@"+this.user+" ["+datetime.toString()+"]: "+content;
    }
    
}
