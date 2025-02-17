package beeplan;

import java.util.List;

public class ExternalInstructor {
    private String externalInstructorID; 
    private List<Timeslot> availability; 

    public ExternalInstructor(String externalInstructorID, List<Timeslot> availability) {
        this.externalInstructorID = externalInstructorID;
        this.availability = availability;
    }

    public void viewSchedule() {
        System.out.println("=== External Instructor Availability: " + externalInstructorID + " ===");
        if (availability.isEmpty()) {
            System.out.println("No availability set.");
        } else {
            for (Timeslot timeslot : availability) {
                System.out.println(timeslot.getTimeslotDetails());
                System.out.println("--------------------------");
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("External Instructor ID: ").append(externalInstructorID)
          .append("\nAvailability:");
        for (Timeslot t : availability) {
            sb.append("\n- ").append(t.toString().replace("\n", ", "));
        }
        return sb.toString();
    }

    public String getExternalInstructorID() { return externalInstructorID; }
    public void setExternalInstructorID(String externalInstructorID) { this.externalInstructorID = externalInstructorID; }

    public List<Timeslot> getAvailability() { return availability; }
    public void setAvailability(List<Timeslot> availability) { this.availability = availability; }
}
