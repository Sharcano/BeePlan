package beeplan;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String studentID;
    private String name;
    private List<Course> enrolledCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.enrolledCourses = new ArrayList<>();
    }

    public void enrollCourse(Course course) {
        enrolledCourses.add(course);
    }

    public String getStudentID() { return studentID; }
    public String getName() { return name; }
    public List<Course> getEnrolledCourses() { return enrolledCourses; }
}
