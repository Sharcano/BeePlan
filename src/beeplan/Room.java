package beeplan;

public class Room {
    private String roomID;   
    private int capacity;    
    private String location; 
    private boolean isLab;   

    public Room(String roomID, int capacity, String location, boolean isLab) {
        this.roomID = roomID;
        this.capacity = capacity;
        this.location = location;
        this.isLab = isLab;
    }

    public boolean checkAvailability(String timeslot) {
        return true; 
    }

    @Override
    public String toString() {
        return "Room ID: " + roomID +
               "\nCapacity: " + capacity +
               "\nLocation: " + location +
               "\nLab: " + (isLab ? "Yes" : "No");
    }

    public String getRoomDetails() {
        return toString();
    }

    public String getRoomID() { return roomID; }
    public void setRoomID(String roomID) { this.roomID = roomID; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public boolean isLab() { return isLab; }
    public void setLab(boolean isLab) { this.isLab = isLab; }
}
