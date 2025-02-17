package beeplan;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Random rand = new Random();
        int scenario = rand.nextInt(3); // 0,1,2 üç farklı senaryo
        // Test amaçlı isterseniz scenario = 0; sabitleyebilirsiniz.

        Schedule schedule;
        List<Instructor> instructors;

        String scenarioName = "";
        String scenarioDescription = "";

        switch (scenario) {
            case 0:
                scenarioName = "Scenario A";
                scenarioDescription = "Now with more courses and fully filled schedule!";
                Pair pairA = createScenarioA();
                schedule = pairA.schedule;
                instructors = pairA.instructors;
                break;
            case 1:
                scenarioName = "Scenario B";
                scenarioDescription = "2nd and 3rd year courses, more filling approach.";
                Pair pairB = createScenarioB();
                schedule = pairB.schedule;
                instructors = pairB.instructors;
                break;
            default:
                scenarioName = "Scenario C";
                scenarioDescription = "3rd and 4th year advanced courses, trying to fill all slots.";
                Pair pairC = createScenarioC();
                schedule = pairC.schedule;
                instructors = pairC.instructors;
                break;
        }

        System.out.println("==================================================");
        System.out.println("Selected " + scenarioName);
        System.out.println(scenarioDescription);
        System.out.println("==================================================\n");

        // Tek bir eğitmenin haftalık programı (ilk eğitmen)
        if (!instructors.isEmpty()) {
            Instructor selectedInstructor = instructors.get(0);
            schedule.printInstructorWeeklySchedule(selectedInstructor);
            System.out.println();
        } else {
            System.out.println("No instructors found in this scenario.");
        }

        // Öğrenciler oluştur
        List<Student> students = new ArrayList<>();
        Student student1 = new Student("S001", "Ali Veli");
        Student student2 = new Student("S002", "Ayşe Fatma");
        students.add(student1);
        students.add(student2);

        List<Course> allCourses = schedule.getAllCourses();
        // Ali tüm derslere kaydoluyor
        for (Course c : allCourses) {
            student1.enrollCourse(c);
        }
        // Ayşe de tüm derslere kaydoluyor, böylece programı iyice dolu olsun
        for (Course c : allCourses) {
            student2.enrollCourse(c);
        }

        // Öğrencilerin programlarını yazdır (artık çok daha dolu olacak)
        schedule.printStudentWeeklySchedule(student1);
        schedule.printStudentWeeklySchedule(student2);

        printSummary(schedule, instructors);
    }

    static class Pair {
        Schedule schedule;
        List<Instructor> instructors;
        Pair(Schedule s, List<Instructor> i) {
            this.schedule = s;
            this.instructors = i;
        }
    }

    // createScenarioA methodunda değişiklikler yapıyoruz:
    // - Daha fazla ders ekliyoruz.
    // - Daha fazla timeslot ekliyoruz.
    // - courseIndex'i döngüsel hale getiriyoruz.
    // - Oda veya eğitmen bulunmazsa rastgele seçiyoruz, böylece slot boş kalmasın.
    private static Pair createScenarioA() {
        List<Course> courses = Arrays.asList(
            new Course("PHYS131", "Physics I", 5, true),
            new Course("TURK101", "Turkish I", 2, false),
            new Course("ENG121", "Academic English I", 2, false),
            new Course("MATH157", "Calculus", 4, false),
            new Course("BIO101", "Biology", 3, false),
            new Course("SENG101", "Programming I", 5, true),
            new Course("CHEM101", "Chemistry", 4, false),
            new Course("HIST101", "World History", 3, false),
            new Course("SENG102", "Programming II", 5, true),
            new Course("PHYS132", "Physics II", 5, true)
        );

        List<Instructor> instructors = Arrays.asList(
            new Instructor("I001", "Dr. Phys", "phys@university.edu"),
            new Instructor("I002", "Dr. Math", "math@university.edu"),
            new Instructor("I003", "Dr. Eng", "eng@university.edu"),
            new Instructor("I004", "Dr. Bio", "bio@university.edu"),
            new Instructor("I005", "Dr. Turk", "turk@university.edu"),
            new Instructor("I006", "Dr. Soft", "soft@university.edu"),
            new Instructor("I007", "Dr. Chem", "chem@university.edu"),
            new Instructor("I008", "Dr. Hist", "hist@university.edu")
        );

        List<Room> rooms = Arrays.asList(
            new Room("R101", 40, "Main Building", false),
            new Room("R102", 50, "Main Bldg Floor 2", false),
            new Room("LAB101", 25, "Lab Building", true),
            new Room("LAB201", 30, "Advanced Lab", true)
        );

        // Daha fazla zaman dilimi ekleyerek haftayı iyice dolduralım
        List<Timeslot> timeslots = Arrays.asList(
            new Timeslot("TS1", "Monday", "09:00", "10:50"),
            new Timeslot("TS2", "Monday", "11:00", "12:50"),
            new Timeslot("TS3", "Monday", "13:00", "14:50"),
            new Timeslot("TS4", "Tuesday", "09:00", "10:50"),
            new Timeslot("TS5", "Tuesday", "11:00", "12:50"),
            new Timeslot("TS6", "Tuesday", "13:00", "14:50"),
            new Timeslot("TS7", "Wednesday", "09:00", "10:50"),
            new Timeslot("TS8", "Wednesday", "11:00", "12:50"),
            new Timeslot("TS9", "Wednesday", "13:00", "14:50"),
            new Timeslot("TS10","Thursday", "09:00", "10:50"),
            new Timeslot("TS11","Thursday", "11:00", "12:50"),
            new Timeslot("TS12","Thursday", "13:00", "14:50"),
            new Timeslot("TS13","Friday", "09:00", "10:50"),
            new Timeslot("TS14","Friday", "11:00", "12:50"),
            new Timeslot("TS15","Friday", "13:00", "14:50")
        );

        Schedule schedule = new Schedule("ScenarioA", "Engineering Faculty");
        Random rand = new Random();
        int courseIndex = 0;

        for (Timeslot ts : timeslots) {
            // kursu döngüsel seçiyoruz
            Course c = courses.get(courseIndex % courses.size());

            // Oda seçimi (lab gerekli ise lab, değilse normal ama bulamazsa rastgele)
            List<Room> suitableRooms = new ArrayList<>();
            for (Room r : rooms) {
                if (c.isLabRequired() && r.isLab()) suitableRooms.add(r);
                if (!c.isLabRequired() && !r.isLab()) suitableRooms.add(r);
            }
            Room chosenRoom;
            if (suitableRooms.isEmpty()) {
                // Hiç uygun oda yoksa herhangi birini seç
                chosenRoom = rooms.get(rand.nextInt(rooms.size()));
            } else {
                chosenRoom = suitableRooms.get(rand.nextInt(suitableRooms.size()));
            }

            // Eğitmen seçimi: kursu listeye ekler
            Instructor chosenInstructor = instructors.get(rand.nextInt(instructors.size()));
            chosenInstructor.addCourse(c);

            schedule.addClass(ts, c, chosenInstructor, chosenRoom);
            courseIndex++;
        }

        return new Pair(schedule, instructors);
    }

    private static Pair createScenarioB() {
        // Benzer mantıkla senaryoyu iyice doldurun ya da eski halini bırakın.
        // Burada da benzer mantık uygulayabilirsiniz.
        // Kısalık adına basitçe birkaç ders/timeslot ekleyelim.
        List<Course> courses = Arrays.asList(
            new Course("SENG201", "Data Structures", 5, true),
            new Course("SENG203", "Discrete Structures", 3, false),
            new Course("SENG206", "Software Design", 3, false),
            new Course("MATH158", "Calculus II", 4, false),
            new Course("HIST201", "History I", 2, false),
            new Course("PHYS231", "Physics III", 4, true),
            new Course("CHEM201", "Chemistry II", 4, false)
        );

        List<Instructor> instructors = Arrays.asList(
            new Instructor("I101", "Dr. Discrete", "disc@university.edu"),
            new Instructor("I102", "Dr. Hist", "hist@university.edu"),
            new Instructor("I103", "Dr. Math2", "math2@university.edu"),
            new Instructor("I104", "Dr. DS", "ds@university.edu"),
            new Instructor("I105", "Dr. Soft2", "soft2@university.edu"),
            new Instructor("I106", "Dr. Phys3", "phys3@university.edu"),
            new Instructor("I107", "Dr. Chem2", "chem2@university.edu")
        );

        List<Room> rooms = Arrays.asList(
            new Room("R202", 60, "Engineering Block", false),
            new Room("R303", 45, "Old Science Hall", false),
            new Room("LAB301", 20, "Advanced Lab", true),
            new Room("LAB302", 25, "Software Lab", true)
        );

        List<Timeslot> timeslots = Arrays.asList(
            new Timeslot("TS1", "Monday", "09:00", "10:50"),
            new Timeslot("TS2", "Monday", "11:00", "12:50"),
            new Timeslot("TS3", "Monday", "13:00", "14:50"),
            new Timeslot("TS4", "Tuesday", "09:00", "10:50"),
            new Timeslot("TS5", "Tuesday", "11:00", "12:50"),
            new Timeslot("TS6", "Tuesday", "13:00", "14:50"),
            new Timeslot("TS7", "Wednesday", "09:00", "10:50"),
            new Timeslot("TS8", "Wednesday", "11:00", "12:50"),
            new Timeslot("TS9", "Wednesday", "13:00", "14:50"),
            new Timeslot("TS10","Thursday", "09:00", "10:50"),
            new Timeslot("TS11","Thursday", "11:00", "12:50"),
            new Timeslot("TS12","Thursday", "13:00", "14:50"),
            new Timeslot("TS13","Friday", "09:00", "10:50"),
            new Timeslot("TS14","Friday", "11:00", "12:50"),
            new Timeslot("TS15","Friday", "13:00", "14:50")
        );

        Schedule schedule = new Schedule("ScenarioB", "Engineering Faculty");
        Random rand = new Random();
        int courseIndex = 0;

        for (Timeslot ts : timeslots) {
            Course c = courses.get(courseIndex % courses.size());
            List<Room> suitableRooms = new ArrayList<>();
            for (Room r : rooms) {
                if (c.isLabRequired() && r.isLab()) suitableRooms.add(r);
                if (!c.isLabRequired() && !r.isLab()) suitableRooms.add(r);
            }
            Room chosenRoom;
            if (suitableRooms.isEmpty()) {
                chosenRoom = rooms.get(rand.nextInt(rooms.size()));
            } else {
                chosenRoom = suitableRooms.get(rand.nextInt(suitableRooms.size()));
            }

            Instructor chosenInstructor = instructors.get(rand.nextInt(instructors.size()));
            chosenInstructor.addCourse(c);

            schedule.addClass(ts, c, chosenInstructor, chosenRoom);
            courseIndex++;
        }

        return new Pair(schedule, instructors);
    }

    private static Pair createScenarioC() {
        // Aynı mantıkla Scenario C de doldurulabilir.
        // Burada da benzer yaklaşım:
        List<Course> courses = Arrays.asList(
            new Course("SENG301", "Software Project Management", 4, true),
            new Course("SENG315", "Software Testing", 3, false),
            new Course("SENG303", "Concurrent Programming", 3, false),
            new Course("SENG426", "Formal Methods", 4, false),
            new Course("ELEC404", "Technical Elective IV", 3, false),
            new Course("SENG491", "Graduation Project I", 5, true),
            new Course("BIO201", "Advanced Biology", 3, false),
            new Course("PHYS331","Nuclear Physics",4,true)
        );

        List<Instructor> instructors = Arrays.asList(
            new Instructor("I201", "Dr. Concurrent", "conc@university.edu"),
            new Instructor("I202", "Dr. Elective", "elective@university.edu"),
            new Instructor("I203", "Dr. Formal", "formal@university.edu"),
            new Instructor("I204", "Dr. Project", "project@university.edu"),
            new Instructor("I205", "Dr. Testing", "testing@university.edu"),
            new Instructor("I206", "Dr. Bio2", "bio2@university.edu"),
            new Instructor("I207", "Dr. PhysNuc", "physnuc@university.edu")
        );

        List<Room> rooms = Arrays.asList(
            new Room("R401", 30, "High Tech Bldg", false),
            new Room("R402", 40, "High Tech Bldg Floor 2", false),
            new Room("LAB501", 20, "Adv Computing Lab", true),
            new Room("LAB502", 20, "Testing Lab", true)
        );

        List<Timeslot> timeslots = Arrays.asList(
            new Timeslot("TS1", "Monday", "09:00", "10:50"),
            new Timeslot("TS2", "Monday", "11:00", "12:50"),
            new Timeslot("TS3", "Monday", "13:00", "14:50"),
            new Timeslot("TS4", "Tuesday", "09:00", "10:50"),
            new Timeslot("TS5", "Tuesday", "11:00", "12:50"),
            new Timeslot("TS6", "Tuesday", "13:00", "14:50"),
            new Timeslot("TS7", "Wednesday", "09:00", "10:50"),
            new Timeslot("TS8", "Wednesday", "11:00", "12:50"),
            new Timeslot("TS9", "Wednesday", "13:00", "14:50"),
            new Timeslot("TS10","Thursday", "09:00", "10:50"),
            new Timeslot("TS11","Thursday", "11:00", "12:50"),
            new Timeslot("TS12","Thursday", "13:00", "14:50"),
            new Timeslot("TS13","Friday", "09:00", "10:50"),
            new Timeslot("TS14","Friday", "11:00", "12:50"),
            new Timeslot("TS15","Friday", "13:00", "14:50")
        );

        Schedule schedule = new Schedule("ScenarioC", "Engineering Faculty");
        Random rand = new Random();
        int courseIndex = 0;

        for (Timeslot ts : timeslots) {
            Course c = courses.get(courseIndex % courses.size());
            List<Room> suitableRooms = new ArrayList<>();
            for (Room r : rooms) {
                if (c.isLabRequired() && r.isLab()) suitableRooms.add(r);
                if (!c.isLabRequired() && !r.isLab()) suitableRooms.add(r);
            }
            Room chosenRoom;
            if (suitableRooms.isEmpty()) {
                chosenRoom = rooms.get(rand.nextInt(rooms.size()));
            } else {
                chosenRoom = suitableRooms.get(rand.nextInt(suitableRooms.size()));
            }

            Instructor chosenInstructor = instructors.get(rand.nextInt(instructors.size()));
            chosenInstructor.addCourse(c);

            schedule.addClass(ts, c, chosenInstructor, chosenRoom);
            courseIndex++;
        }

        return new Pair(schedule, instructors);
    }

    private static void printSummary(Schedule schedule, List<Instructor> instructors) {
        System.out.println("=== Overall Summary ===");
        List<Schedule.ScheduledClass> allClasses = schedule.getAllScheduledClasses();
        int totalClasses = allClasses.size();
        int labClasses = 0;
        Set<Course> uniqueCourses = new HashSet<>();
        for (Schedule.ScheduledClass sc : allClasses) {
            uniqueCourses.add(sc.course);
            if (sc.course.isLabRequired()) {
                labClasses++;
            }
        }

        int instructorCount = instructors.size();
        int uniqueCourseCount = uniqueCourses.size();

        System.out.println("Total Scheduled Classes: " + totalClasses);
        System.out.println("Total Lab-Required Classes: " + labClasses);
        System.out.println("Number of Unique Courses Scheduled: " + uniqueCourseCount);
        System.out.println("Number of Instructors: " + instructorCount);
        System.out.println("All operations completed successfully.");
    }
}
