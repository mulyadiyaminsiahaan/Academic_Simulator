package academic.driver;

import java.sql.SQLException;
import java.util.*;
import academic.model.*;

public class Driver1 {

    static final String US = "root";
    static final String PS = "Mulyad1yam1n.";
    
    public static void main(String[] args) {
        CourseService courseService = new CourseService();

        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Course> courses = new ArrayList<>();
        ArrayList<Enrollment> enrollments = new ArrayList<>();
        ArrayList<Lecturer> lecturers = new ArrayList<>();
        ArrayList<CourseOpening<Course>> courseOpenings = new ArrayList<>();

        class SemesterComparator implements Comparator<CourseOpening<Course>> {
            @Override
            public int compare(CourseOpening<Course> co1, CourseOpening<Course> co2) {
                // Ambil angka dari semester (misalnya "odd" menjadi 1, "even" menjadi 2)
                int semester1 = co1.getSemester().equalsIgnoreCase("odd") ? 1 : 2;
                int semester2 = co2.getSemester().equalsIgnoreCase("odd") ? 1 : 2;

                // Bandingkan angka semester untuk mendapatkan urutan yang diinginkan
                return Integer.compare(semester1, semester2);
            }
        }

        // Implementasi Local Class untuk logika pembanding enrollment
        class EnrollmentComparator {
            public boolean isNewer(Enrollment current, Enrollment other) {
                return (current.getYear().compareTo(other.getYear()) < 0) ||
                    (current.getYear().equals(other.getYear()) &&
                        current.getSemester().equals("odd") && other.getSemester().equals("even"));
            }
        }
        
        //javac example\*.java -d bin
        //java -cp "bin;./libs/*" example.Driver
        try {
            //Contact ke database
            ContactDatabase database = new ContactDatabase("jdbc:mysql://localhost:3306/DBAcademic");
            //Masukkan inputan
            Scanner Input = new Scanner(System.in);

            //Percabangan
            while (Input.hasNextLine()){
                String input = Input.nextLine();
                if (input.equals("---")){
                    //PRINT ALL LECTURER
                    database.printAllLecturers();
                    // PRINT ALL COURSE
                    database.printAllCourses();
                    // PRINT ALL STUDENT
                    database.printAllStudents();
                    // PRINT ALL ENROLLMENT
                    database.printAllEnrollment();

                    for (Lecturer lecturer : lecturers){
                        System.out.println(lecturer);
                    }
    
                    for (Course course : courses){
                        System.out.println(course);
                    }
                    
                    for (Student student : students){
                        System.out.println(student);
                    }
    
                    for (Enrollment e : enrollments){
                        System.out.println(e);
                    } 

                    Input.close();
                    break;
                }

                String[] tokens = input.split("#");

                if (tokens[0].equals("course-add")){
                    try {
                        courseService.courseAdd(tokens[1], tokens[2], Integer.parseInt(tokens[3]), tokens[4], database);
                        
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    //CEK APAKAH COURSE SUDAH ADA
                    boolean found = false;
                    for (Course course : courses) {
                        if (course.getCode().equals(tokens[1])) {
                            found = true;
                            break;
                        }
                    }
                    //JIKA BELUM ADA
                    if (!found) {
                        //SPLIT INITIAL
                        //String[] pisah = tokens[5].split(",");
                        //MASUKAN SEMUA INITIAL YANG ADA
                        /*for (int i = 0; i < pisah.length; i++){
                            for (Lecturer lecturer : lecturers){
                                if (lecturer.getInitial().equals(pisah[i])){     
                                    pisah[i] = pisah[i] + " (" + lecturer.getEmail() + ")";                        
                                }
                            }
                        }*/
                        Course course = new Course(tokens[1], tokens[2], Integer.parseInt(tokens[3]), tokens[4]);
                        courses.add(course);
                    }

                }  else if (tokens[0].equals("course-open")){
                    //course-open#12S1101#2020/2021#odd#IUS
                    database.addCourseOpening(tokens[1], tokens[2], tokens[3], tokens[4]);

                    String IE="";

                    //course-open#12S1101#2020/2021#odd#IUS
                    for (Course course : courses){
                        if (course.getCode().equals(tokens[1])){
                            for (Lecturer lecturer : lecturers){
                                if (lecturer.getInitial().equals(tokens[4])){
                                    //System.out.println(course + "|" + tokens[2] + "|" + tokens[3] + "|" + lecturer.getInitial() + " (" + lecturer.getEmail() + ")");
                                    //print lalu tambahkan ke courseOpening
                                    IE =  lecturer.getInitial() + " (" + lecturer.getEmail() + ")";
    
                                    break;
                                }
                            }
                            CourseOpening<Course> courseOpening = new CourseOpening<>(course, tokens[2], tokens[3], IE);
                            courseOpenings.add(courseOpening);
                            //break;
                        }
    
                    }

                } else if (tokens[0].equals("course-history")){
                    //course-history#12S1101
                    database.showCourseHistory(tokens[1]);

                                        // Urutkan ArrayList berdasarkan semester
                    Collections.sort(courseOpenings, new SemesterComparator());

                    
                    //print course opening
                    for (CourseOpening<Course> a : courseOpenings){

                        if (a.getCodeCourse().equals(tokens[1])){
                            System.out.println(a);

                            for (Enrollment e : enrollments){
                                if (e.getCode().equals(a.getCodeCourse()) && e.getYear().equals(a.getYear()) && e.getSemester().equals(a.getSemester())){
                                    System.out.println(e);
                                }
                            }
                            
                        }

                        
                    }
                    
                }else if (tokens[0].equals("student-add")){
                    //memasukkan ke database
                    database.addStudent(tokens[1], tokens[2], Integer.parseInt(tokens[3]), tokens[4]);

                    //CEK APAKAH STUDENT SUDAH ADA
                    boolean founded = false;
                    for (Student student : students) {
                        if (student.getNim().equals(tokens[1])) {
                            founded = true;
                            break;
                        }
                    }
                    //JIKA BELUM ADA masukkan ke arraylist
                    if (!founded) {
                        Student student = new Student(tokens[1], tokens[2], Integer.parseInt(tokens[3]), tokens[4]);
                        students.add(student);
                    }


                } else if (tokens[0].equals("student-details")){
                    //student-details#12S20003
                    database.printStudentDetails(tokens[1]);

                    Double Indeks = 0.00, beIndeks = 0.00;
                    Integer Sks = 0;
                    Double result = 0.00;
                    String idCourse = "";
                    boolean cek = false;
    
                    for (Student student : students){
                    
                        if (student.getNim().equals(tokens[1])){
    
                            for (Enrollment enrollment : enrollments){
    
                                if (enrollment.getNim().equals(student.getNim())){
    
                                    if (enrollment.getGrade().equals("A")){
                                        Indeks = 4.0;
                                    } else if (enrollment.getGrade().equals("AB")){
                                        Indeks = 3.5;
                                    } else if (enrollment.getGrade().equals("B")){
                                        Indeks = 3.0;
                                    }else if (enrollment.getGrade().equals("BC")){
                                        Indeks = 2.5;
                                    }else if (enrollment.getGrade().equals("C")){
                                        Indeks = 2.0;
                                    }else if (enrollment.getGrade().equals("D")){
                                        Indeks = 1.5;
                                    }else if (enrollment.getGrade().equals("E")){
                                        Indeks = 1.0;
                                    }
    
                                    for (Course course : courses){
    
                                        if (enrollment.getCode().equals(course.getCode()) && Indeks > 0.00){
    
                                            if (idCourse.equals(course.getCode())){
                                            
                                                Integer Kredit = course.getCredit();
                                                Sks = Sks - Kredit;
                                                result =  result - (beIndeks * Kredit);
                                            }
    
                                            Integer Kredit = course.getCredit();
                                            Sks = Sks + Kredit;
                                            result =  result + (Indeks * Kredit);
    
                                            idCourse = course.getCode();
                                            beIndeks = Indeks;
    
                                            break;  
                                        } 
                                    }
                                }
                            }
                        }
                    }
                    
                    for (Student student : students) {
                            
                        if (student.getNim().equals(tokens[1])){
                            cek = true;
                        }
    
                        if (cek){
                            if (Sks == 0){
                                System.out.println(student +"|"+ String.format("%.2f", Indeks) +"|"+ Sks);
                                break;
                            } else {
                                System.out.println(student +"|"+ String.format("%.2f", result/Sks) +"|"+ Sks);
                                break;
                            }
                        }
                    }

                } else if (tokens[0].equals("student-transcript")){
                    //student-transcript#12S20003
                    database.printStudentTranscript(tokens[1]);

                    
                    int sks = 0;
                    Double IPK = 0.00;
                    Double TotalIPK = 0.00;
                    int TotalSks = 0;
                    EnrollmentComparator comparator = new EnrollmentComparator();

                    for (Enrollment e : enrollments) {
                        if (e.getNim().equals(tokens[1])) {
                            boolean isNewer = true;

                            // Iterate through enrollments again to check if there is a newer enrollment
                            for (Enrollment other : enrollments) {
                                if (e.getCode().equals(other.getCode()) && e.getNim().equals(other.getNim())) {
                                    if (comparator.isNewer(e, other)) {
                                        isNewer = false;
                                        break;
                                    }
                                }
                            }


                            // Jika tidak ada nilai yang lebih baru, cetak nilai
                            if (isNewer) {

                                sks =  getSks(e.getCode(), courses);
                                //System.out.println(sks);
                                IPK = calculateIndeks(e.getGrade());
                                //System.out.println(IPK);

                                TotalSks = TotalSks + sks;

                                TotalIPK = TotalIPK + (IPK * sks);
                                //System.out.println(TotalIPK);

                                
                            }
                        }
                    }


                    boolean cek = false;
                    for (Student student : students) {
                            
                        if (student.getNim().equals(tokens[1])){
                            cek = true;
                        }

                        if (cek){
                            if (TotalSks == 0){
                                System.out.println(student +"|"+ String.format("%.2f", TotalIPK) +"|"+ TotalSks);
                                break;
                            } else {
                                System.out.println(student +"|"+ String.format("%.2f", TotalIPK/TotalSks) +"|"+ TotalSks);
                                break;
                            }
                        }
                    }


                    // Urutkan ArrayList berdasarkan semester
                    Collections.sort(courseOpenings, new SemesterComparator());

                    // Iterate through enrollments to find latest enrollment for each course
                    for (Enrollment e : enrollments) {
                        if (e.getNim().equals(tokens[1])) {
                            boolean isNewer = true;

                            // Iterate through enrollments again to check if there is a newer enrollment
                            for (Enrollment other : enrollments) {
                                if (e.getCode().equals(other.getCode()) && e.getNim().equals(other.getNim())) {
                                    if (comparator.isNewer(e, other)) {
                                        isNewer = false;
                                        break;
                                    }
                                }
                            }
                            
                            // Jika tidak ada nilai yang lebih baru, cetak nilai
                            if (isNewer) {
                                System.out.println(e.getCode() + "|" + e.getNim() + "|" + e.getYear() + "|" + e.getSemester() + "|" + e.getGrade() +
                                                    (!e.getNilaiRemed().equals("") ? "(" + e.getNilaiRemed() + ")" : ""));
                            }
                        }
                    }

                }else if (tokens[0].equals("lecturer-add")){
                    database.addLecturer(tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]);

                    //CEK APAKAH LECTURER SUDAH ADA
                    boolean found = false;
                    for (Lecturer lecturer : lecturers) {
                        if (lecturer.getNim().equals(tokens[1])) {
                            found = true;
                            break;
                        }
                    }
                    //JIKA BELUM ADA
                    if (!found) {
                        Lecturer lecturer = new Lecturer(tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]);
                        lecturers.add(lecturer);
                    }

                } else if (tokens[0].equals("enrollment-add")){
                    //enrollment-add#12S2102#12S20001#2021/2022#odd
                    String grade = "None";
                    database.addEnrollment(tokens[1], tokens[2], tokens[3], tokens[4], grade);

                    //CEK APAKAH COURSE DAN STUDENT TERSEDIA
                    boolean foundCourse = false;
                    boolean foundStudent = false;
                    for (Course course : courses) {
                        if (course.getCode().equals(tokens[1])) {
                            foundCourse = true;
                            break;
                        }
                    }
                    for (Student student : students) {
                        if (student.getNim().equals(tokens[2])) {
                            foundStudent = true;
                            break;
                        }
                    }
                    //JIKA TERSEDIA COURSE DAN STUDENT
                    if (foundCourse && foundStudent) {
                        //String grade = "None";
                        Enrollment enrollment = new Enrollment(tokens[1], tokens[2], tokens[3], tokens[4], grade);
                        enrollments.add(enrollment);
                    } 

                } else if (tokens[0].equals("enrollment-grade")){
                    //enrollment-grade#12S1101#12S20002#2020/2021#odd#B
                    database.setGrade(tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]);

                    for (Enrollment enrollment : enrollments) {
                        if (enrollment.getCode().equals(tokens[1]) && enrollment.getNim().equals(tokens[2]) && enrollment.getYear().equals(tokens[3]) && enrollment.getSemester().equals(tokens[4])){
                            enrollment.setGrade(tokens[5]);
                            break;
                        }
                    }

                } else if (tokens[0].equals("enrollment-remedial")){
                    //enrollment-remedial#12S1101#12S20001#2020/2021#odd#B
                    database.setRemed(tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]);

                    for (Enrollment enrollment : enrollments){
                        if (enrollment.getCode().equals(tokens[1]) && enrollment.getNim().equals(tokens[2]) && enrollment.getYear().equals(tokens[3]) && enrollment.getSemester().equals(tokens[4])&&enrollment.getGrade() != "None" ){
                            if (enrollment.getNilaiRemed() == "") {
                                String nilaiRemed = enrollment.getGrade();
                                enrollment.setGrade(tokens[5]);
                                enrollment.setNilaiRemed(nilaiRemed);     
                            }         
                        }
                    }

                }      
            }
            //paling bawah
            database.shutdown();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }

        
    }

    // Method untuk menghitung indeks prestasi berdasarkan nilai
    private static Double calculateIndeks(String grade) {
        switch (grade) {
            case "A":
                return 4.0;
            case "AB":
                return 3.5;
            case "B":
                return 3.0;
            case "BC":
                return 2.5;
            case "C":
                return 2.0;
            case "D":
                return 1.5;
            case "E":
                return 1.0;
            default:
                return 0.0;
        }
    }
    
        // Method untuk mendapatkan total SKS untuk suatu mata kuliah
    private static Integer getSks(String courseCode, List<Course> courses) {
        for (Course course : courses) {
            if (course.getCode().equals(courseCode)) {
                return course.getCredit();
            }
        }
        return 0;
    }
}
