package beeplan;

import java.util.ArrayList;
import java.util.List;

public class Curriculum {
    private String curriculumID; 
    private int year; 
    private String department; 
    private List<Course> courseList;

    public Curriculum(String curriculumID, int year, String department) {
        this.curriculumID = curriculumID;
        this.year = year;
        this.department = department;
        this.courseList = new ArrayList<>();
    }

    public boolean validateCourseSchedule() {
        System.out.println("Validating course schedule for curriculum: " + curriculumID + " (Dummy Validation)");
        return true; 
    }

    public void addCourse(Course course) {
        courseList.add(course);
    }

    public void removeCourse(String courseID) {
        courseList.removeIf(course -> course.getCourseID().equals(courseID));
    }

    @Override
    public String toString() {
        StringBuilder details = new StringBuilder();
        details.append("Curriculum ID: ").append(curriculumID)
               .append("\nYear: ").append(year)
               .append("\nDepartment: ").append(department)
               .append("\nCourses:");
        for (Course course : courseList) {
            details.append("\n- ").append(course.getCourseName());
        }
        return details.toString();
    }

    public String getCurriculumDetails() {
        return toString();
    }

    public String getCurriculumID() { return curriculumID; }
    public void setCurriculumID(String curriculumID) { this.curriculumID = curriculumID; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public List<Course> getCourseList() { return courseList; }
}
