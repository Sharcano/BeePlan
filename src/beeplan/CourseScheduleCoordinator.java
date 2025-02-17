package beeplan;

import java.util.ArrayList;
import java.util.List;

public class CourseScheduleCoordinator {
    private String coordinatorID;   
    private String name;            
    private String email;           
    private List<Course> managedCourses;

    public CourseScheduleCoordinator(String coordinatorID, String name, String email) {
        this.coordinatorID = coordinatorID;
        this.name = name;
        this.email = email;
        this.managedCourses = new ArrayList<>();
    }

    public Schedule createSchedule(int year, String department) {
        return new Schedule("SCH" + year + department.replace(" ",""), department);
    }

    public void addManagedCourse(Course course) {
        managedCourses.add(course);
        System.out.println("Managed course added: " + course.getCourseName());
    }

    public void listManagedCourses() {
        System.out.println("=== Managed Courses by " + name + " ===");
        if (managedCourses.isEmpty()) {
            System.out.println("No courses managed.");
        } else {
            for (Course course : managedCourses) {
                System.out.println(course.getCourseDetails());
                System.out.println("--------------------------");
            }
        }
    }

    public void updateSchedule(Schedule schedule) {
        System.out.println("Updating schedule ID: " + schedule.getScheduleID() + " (Dummy Update)");
    }

    public void assignRoom(Schedule schedule, String courseID, String roomID) {
        System.out.println("Assigning Room ID: " + roomID + " to Course ID: " + courseID + " (Dummy)");
    }

    public void viewSchedule(Schedule schedule) {
        System.out.println("=== Viewing Schedule for Department: " + schedule.getDepartment() + " ===");
        System.out.println(schedule.getScheduleDetails());
    }

    public String generateReport(Schedule schedule) {
        System.out.println("Generating report for Schedule ID: " + schedule.getScheduleID() + " (Dummy Report)");
        return "Report for Schedule ID: " + schedule.getScheduleID();
    }

    @Override
    public String toString() {
        return "Coordinator ID: " + coordinatorID +
               "\nName: " + name +
               "\nEmail: " + email;
    }

    public String getCoordinatorID() { return coordinatorID; }
    public void setCoordinatorID(String coordinatorID) { this.coordinatorID = coordinatorID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
