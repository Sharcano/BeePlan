package beeplan;

public class Course {
    private String courseID;       
    private String courseName;     
    private int credits;           
    private boolean isLabRequired; 

    public Course(String courseID, String courseName, int credits, boolean isLabRequired) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.credits = credits;
        this.isLabRequired = isLabRequired;
    }

    @Override
    public String toString() {
        return "Course ID: " + this.courseID +
               "\nCourse Name: " + this.courseName +
               "\nCredits: " + this.credits +
               "\nLab Required: " + (this.isLabRequired ? "Yes" : "No");
    }

    public String getCourseID() { return courseID; }
    public void setCourseID(String courseID) { this.courseID = courseID; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }

    public boolean isLabRequired() { return isLabRequired; }
    public void setLabRequired(boolean isLabRequired) { this.isLabRequired = isLabRequired; }
}
