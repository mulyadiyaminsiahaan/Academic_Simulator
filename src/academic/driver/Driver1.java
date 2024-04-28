package academic.driver;

import java.sql.SQLException;
import java.util.Scanner;

public class Driver1 {

    static final String US = "root";
    static final String PS = "Mulyad1yam1n.";

    public static void main(String[] args) {

        //javac example\*.java -d bin
        //java -cp "bin;./libs/*" example.Driver
        try {
            //Contact ke database
            ContactDatabase database = new ContactDatabase("jdbc:mysql://localhost:3306/DBAcademic");
            //Masukkan inputan
            Scanner masukan = new Scanner(System.in);

            //Percabangan
            while (masukan.hasNextLine()){
                String input = masukan.nextLine();
                if (input.equals("---")){
                    //PRINT ALL LECTURER
                    database.printAllLecturers();
                    // PRINT ALL COURSE
                    database.printAllCourses();
                    // PRINT ALL STUDENT
                    database.printAllStudents();
                    // PRINT ALL ENROLLMENT
                    database.printAllEnrollment();
                    break;
                }

                String[] tokens = input.split("#");

                if (tokens[0].equals("course-add")){
                    database.addCourse(tokens[1], tokens[2], Integer.parseInt(tokens[3]), tokens[4]);

                }  else if (tokens[0].equals("course-open")){
                    //course-open#12S1101#2020/2021#odd#IUS
                    database.addCourseOpening(tokens[1], tokens[2], tokens[3], tokens[4]);

                } else if (tokens[0].equals("course-history")){
                    //course-history#12S1101
                    database.showCourseHistory(tokens[1]);
                    
                }else if (tokens[0].equals("student-add")){
                    database.addStudent(tokens[1], tokens[2], Integer.parseInt(tokens[3]), tokens[4]);

                } else if (tokens[0].equals("student-details")){
                    //student-details#12S20003
                    database.printStudentDetails(tokens[1]);

                } else if (tokens[0].equals("student-transcript")){
                    //student-transcript#12S20003
                    database.printStudentTranscript(tokens[1]);

                }else if (tokens[0].equals("lecturer-add")){
                    database.addLecturer(tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]);

                } else if (tokens[0].equals("enrollment-add")){
                    //enrollment-add#12S2102#12S20001#2021/2022#odd
                    String grade = "None";
                    database.addEnrollment(tokens[1], tokens[2], tokens[3], tokens[4], grade);

                } else if (tokens[0].equals("enrollment-grade")){
                    //enrollment-grade#12S1101#12S20002#2020/2021#odd#B
                    database.setGrade(tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]);

                } else if (tokens[0].equals("enrollment-remedial")){
                    //enrollment-remedial#12S1101#12S20001#2020/2021#odd#B
                    database.setRemed(tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]);

                } 
                      
            }
            //paling bawah
            database.shutdown();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }

    }
}
