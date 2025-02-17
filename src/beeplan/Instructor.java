package beeplan;

import java.util.ArrayList;
import java.util.List;

public class Instructor {
    private String instructorID;  
    private String name;          
    private String email;         
    private List<Course> courseList;

    public Instructor(String instructorID, String name, String email) {
        this.instructorID = instructorID;
        this.name = name;
        this.email = email;
        this.courseList = new ArrayList<>();
    }

    public void viewSchedule() {
        System.out.println("=== Instructor Schedule: " + name + " ===");
        if (courseList.isEmpty()) {
            System.out.println("No courses assigned.");
        } else {
            for (Course course : courseList) {
                System.out.println(course);
                System.out.println("--------------------------");
            }
        }
    }

    public void requestChange(String timeslotID) {
        System.out.println(name + " requested a schedule change for timeslot: " + timeslotID);
    }

    @Override
    public String toString() {
        return "Instructor ID: " + instructorID +
               "\nName: " + name +
               "\nEmail: " + email;
    }

    public String getInstructorID() { return instructorID; }
    public void setInstructorID(String instructorID) { this.instructorID = instructorID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<Course> getCourseList() { return courseList; }
    public void addCourse(Course course) { this.courseList.add(course); }
}
