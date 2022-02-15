/**
 * Represent a timestamp consisting of a date (day/month/year) and time 
 * in hours and minutes (24h format.
 */
public class DateTime  implements Comparable<DateTime> { //For part 4
    
    public int year;
    public int month;
    public int day; 
    public int hours;
    public int minutes;    
    public int seconds;
    public boolean pm; 
    public String dataString;

    
    public DateTime(int year, int day, int month, int h, int m) {        
        this.year = year; 
        this.month = month; 
        this.day = day;     
        this.hours = h; 
        this.minutes = m;
        this.dataString = String.valueOf(year) + String.valueOf(month) + String.valueOf(day) + String.valueOf(h) + String.valueOf(m);
    }
    
    @Override
    public int hashCode() {
        final int prime = 29;
        int result = 1;
        result = prime * result
                + ((dataString == null) ? 0 : dataString.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj){
        
        
        if(this == obj){
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()){
            return false;
        }
        
        DateTime other = (DateTime) obj;
        if(this.compareTo(other) == 0){
            return true;
        }
        else{
            return false;
        }
        
        
    }
    
    
    public int compareTo(DateTime d){
        
        DateTime t1 = new DateTime(this.year, this.day, this.month, this.hours, this.minutes);
        DateTime t2 = d;
        
        int compareMins = Integer.compare(t1.minutes, t2.minutes);
        int compareHrs = Integer.compare(t1.hours, t2.hours);
        int compareDay = Integer.compare(t1.day, t2.day);
        int compareMonth = Integer.compare(t1.month, t2.month);
        int compareYear = Integer.compare(t1.year, t2.year);
     
        if(compareYear == 0){            
            if(compareMonth ==0){
                if(compareDay == 0){
                    if(compareHrs == 0){
                       if(compareMins ==  0){
                           return 0;
                       } 
                        else{return compareMins;}
                    }
                    else{return compareHrs;}
                }
                else{return compareDay;}
            }
            else{return compareMonth;}
        }
        else{ return compareYear;}
        
    }
    
    public DateTime(String datetime) {
        String[] fields = datetime.split(" ");
        String[] dateFields = fields[0].split("/");
        month = Integer.parseInt(dateFields[0]);
        day = Integer.parseInt(dateFields[1]);
        year = Integer.parseInt(dateFields[2]);
        
        String[] timeFields = fields[1].split(":"); 
        hours = Integer.parseInt(timeFields[0]);
        minutes = Integer.parseInt(timeFields[1]);;
    }
    
    public String toString() {
        return Integer.toString(month)+"/"+Integer.toString(day)+"/"+Integer.toString(year)+"  "+
            String.format("%02d" , hours)+":"+String.format("%02d", minutes);
    }   
    
}
