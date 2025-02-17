package beeplan;

import java.util.List;

public class StudentAffairsOffice {
    private String officeID;   
    private String contactInfo;

    public StudentAffairsOffice(String officeID, String contactInfo) {
        this.officeID = officeID;
        this.contactInfo = contactInfo;
    }

    public void manageEnrollments(String courseID, List<String> studentList) {
        System.out.println("Managing enrollments for Course ID: " + courseID);
        System.out.println("Enrolled Students: " + studentList);
    }

    public void distributeSchedule(String departmentID) {
        System.out.println("Distributing schedule to Department ID: " + departmentID);
    }

    @Override
    public String toString() {
        return "Office ID: " + officeID + "\nContact Info: " + contactInfo;
    }

    public String getOfficeDetails() {
        return toString();
    }

    public String getOfficeID() { return officeID; }
    public void setOfficeID(String officeID) { this.officeID = officeID; }

    public String getContactInfo() { return contactInfo; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }
}
