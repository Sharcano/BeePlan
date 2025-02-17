package beeplan;

public class Timeslot {
    private String timeslotID; 
    private String day;        
    private String startTime;  
    private String endTime;    

    public Timeslot(String timeslotID, String day, String startTime, String endTime) {
        this.timeslotID = timeslotID;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean isAvailable(String roomID) {
        return true; 
    }

    @Override
    public String toString() {
        return "Timeslot ID: " + timeslotID +
               "\nDay: " + day +
               "\nStart Time: " + startTime +
               "\nEnd Time: " + endTime;
    }

    public String getTimeslotDetails() {
        return toString();
    }

    public String getTimeslotID() { return timeslotID; }
    public void setTimeslotID(String timeslotID) { this.timeslotID = timeslotID; }

    public String getDay() { return day; }
    public void setDay(String day) { this.day = day; }

    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }
}
