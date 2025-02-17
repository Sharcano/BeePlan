package beeplan;

import java.util.*;

public class Schedule {
    private String scheduleID;      
    private List<Course> courseList;
    private List<Timeslot> timeslotList;
    private String department;      
    private Map<Timeslot, ScheduledClass> scheduleMap = new HashMap<>();

    // Gün sırasını belirleyen sabit liste
    private static final List<String> DAY_ORDER = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");

    class ScheduledClass {
        Course course;
        Instructor instructor;
        Room room;
        Timeslot timeslot;
        public ScheduledClass(Course c, Instructor i, Room r, Timeslot t) {
            this.course = c;
            this.instructor = i;
            this.room = r;
            this.timeslot = t;
        }
    }

    public Schedule(String scheduleID, String department) {
        this.scheduleID = scheduleID;
        this.department = department;
        this.courseList = new ArrayList<>();
        this.timeslotList = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courseList.add(course);
    }

    public void removeCourse(String courseID) {
        courseList.removeIf(course -> course.getCourseID().equals(courseID));
    }

    public boolean checkConflicts() {
        return false; 
    }

    public void finalizeSchedule() {
        // Dummy finalize
    }

    public void addClass(Timeslot ts, Course c, Instructor i, Room r) {
        scheduleMap.put(ts, new ScheduledClass(c, i, r, ts));
    }

    // Eğitmen haftalık programı
    public void printInstructorWeeklySchedule(Instructor instructor) {
        Map<String, List<Timeslot>> dayMap = new HashMap<>();

        for (Map.Entry<Timeslot, ScheduledClass> entry : scheduleMap.entrySet()) {
            Timeslot ts = entry.getKey();
            ScheduledClass sc = entry.getValue();
            if (sc.instructor.equals(instructor)) {
                dayMap.putIfAbsent(ts.getDay(), new ArrayList<>());
                dayMap.get(ts.getDay()).add(ts);
            }
        }

        if (dayMap.isEmpty()) {
            System.out.println("No classes assigned for " + instructor.getName());
            return;
        }

        // Günleri sıralama
        List<String> sortedDays = new ArrayList<>(dayMap.keySet());
        sortedDays.sort(Comparator.comparingInt(DAY_ORDER::indexOf));

        // Her günün Timeslotlarını saat sırasına göre sırala
        for (List<Timeslot> list : dayMap.values()) {
            list.sort(Comparator.comparing(Timeslot::getStartTime));
        }

        int dayWidth = 10;
        int timeWidth = 12;
        int courseWidth = 25;
        int roomWidth = 8;
        int totalWidth = dayWidth + timeWidth + courseWidth + roomWidth + 10; 

        String instructorName = instructor.getName();

        printLine(totalWidth);
        System.out.println("Weekly Schedule for Instructor: " + instructorName);
        printLine(totalWidth);

        String headerFormat = "%-" + dayWidth + "s  %-" + timeWidth + "s  %-" + courseWidth + "s  %-" + roomWidth + "s%n";
        System.out.printf(headerFormat, "Day", "Time", "Course", "Room");
        printLine(totalWidth);

        for (String day : sortedDays) {
            List<Timeslot> tsList = dayMap.get(day);
            for (Timeslot ts : tsList) {
                ScheduledClass sc = scheduleMap.get(ts);
                String timeRange = ts.getStartTime() + "-" + ts.getEndTime();
                String courseName = sc.course.getCourseName();
                String roomID = sc.room.getRoomID();
                System.out.printf(headerFormat, day, timeRange, courseName, roomID);
                day = ""; // Aynı günde birden çok ders varsa ilk satırda gün ismi yaz, sonra boş bırak
            }
        }
        printLine(totalWidth);
    }

    // Öğrenci haftalık programı (hocayı da gösteriyoruz)
    public void printStudentWeeklySchedule(Student student) {
        System.out.println("\nWeekly Schedule for Student: " + student.getName() + " (" + student.getStudentID() + ")");

        // Tüm Timeslotları gün+startTime sırasına göre sırala
        List<Timeslot> allSlots = new ArrayList<>(scheduleMap.keySet());
        allSlots.sort(Comparator.<Timeslot>comparingInt(ts -> DAY_ORDER.indexOf(ts.getDay()))
                     .thenComparing(Timeslot::getStartTime));

        int dayWidth = 10;
        int timeWidth = 15;
        int courseWidth = 30;
        int instructorWidth = 15; // hocayı da göstermek için ek sütun
        int totalWidth = dayWidth + timeWidth + courseWidth + instructorWidth + 5;

        printLine(totalWidth);
        System.out.format("%-" + dayWidth + "s%-" + timeWidth + "s%-" + courseWidth + "s%-" + instructorWidth + "s%n", 
                          "Day", "Time", "Course", "Instructor");
        printLine(totalWidth);

        for (Timeslot ts : allSlots) {
            ScheduledClass sc = scheduleMap.get(ts);
            String courseName = "Empty";
            String instructorName = "";
            if (sc != null && student.getEnrolledCourses().contains(sc.course)) {
                courseName = sc.course.getCourseName();
                instructorName = sc.instructor.getName();
            }
            System.out.format("%-" + dayWidth + "s%-" + timeWidth + "s%-" + courseWidth + "s%-" + instructorWidth + "s%n",
                              ts.getDay(), ts.getStartTime() + "-" + ts.getEndTime(), courseName, instructorName);
        }
        printLine(totalWidth);
    }

    private void printLine(int width) {
        for (int i = 0; i < width; i++) System.out.print("-");
        System.out.println();
    }

    public List<Course> getAllCourses() {
        Set<Course> unique = new HashSet<>();
        for (ScheduledClass sc : scheduleMap.values()) {
            unique.add(sc.course);
        }
        unique.addAll(courseList);
        return new ArrayList<>(unique);
    }

    public List<Schedule.ScheduledClass> getAllScheduledClasses() {
        return new ArrayList<>(scheduleMap.values());
    }

    public String getScheduleID() { return scheduleID; }
    public List<Course> getCourseList() { return courseList; }
    public List<Timeslot> getTimeslotList() { return timeslotList; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
}
