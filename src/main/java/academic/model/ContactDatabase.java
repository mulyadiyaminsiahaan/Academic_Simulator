package main.java.academic.model;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class ContactDatabase extends AbstractDatabase {

    static String NilaiSebelum = "";
    static Double totalIPK = 0.00;
    static Integer totalSKS = 0;
    static String BCourse = "";
    static Double BGrade = 0.00;
    static Double gradePoint = 0.00;

    public ContactDatabase(String url) throws SQLException {
        super(url);
    }

    protected void createTables() throws SQLException {
        String Course = "CREATE TABLE IF NOT EXISTS course (" + 
            "code VARCHAR(255) NOT NULL PRIMARY KEY," +
            "name TEXT NOT NULL," +
            "credits INT NOT NULL," +
            "grade VARCHAR(255) NOT NULL" + 
            ")";
    
        
        String Student = "CREATE TABLE IF NOT EXISTS student (" + 
            "nim VARCHAR(255) NOT NULL PRIMARY KEY," +
            "name TEXT NOT NULL," +
            "year INT NOT NULL," +
            "studyProgram TEXT NOT NULL" +
            ")";
        

        String Lecturer = "CREATE TABLE IF NOT EXISTS lecturer (" + 
            "NID VARCHAR(255) NOT NULL," +
            "name TEXT NOT NULL," +
            "initial TEXT NOT NULL," +
            "email VARCHAR(255) NOT NULL," + // Remove the PRIMARY KEY or UNIQUE constraint from here
            "studyProgram TEXT NOT NULL" +
            ")";

        
        //create table enrollment nim, code, year, semester, grade
        String Enrollment = "CREATE TABLE IF NOT EXISTS enrollment (" + 
            "code VARCHAR(255) NOT NULL," +
            "nim VARCHAR(255) NOT NULL," +
            "year VARCHAR(255) NOT NULL," +
            "semester VARCHAR(255) NOT NULL," +
            "grade VARCHAR(255) NOT NULL" +
            ")";

        String CourseOpening = "CREATE TABLE IF NOT EXISTS course_opening (" + 
            "course VARCHAR(255) NOT NULL," +
            "year VARCHAR(255) NOT NULL," +
            "semester VARCHAR(255) NOT NULL," +
            "IE VARCHAR(255) NOT NULL" +
            ")";
        
        Statement statement = this.getConnection().createStatement();

        statement.execute("DROP TABLE IF EXISTS course");
        statement.execute("DROP TABLE IF EXISTS student");
        statement.execute("DROP TABLE IF EXISTS lecturer");  
        statement.execute("DROP TABLE IF EXISTS enrollment");
        statement.execute("DROP TABLE IF EXISTS course_opening");      

        statement.execute(Course);
        statement.execute(Student);
        statement.execute(Lecturer);
        statement.execute(Enrollment);
        statement.execute(CourseOpening);

        statement.close();
    }


    //ADD COURSE
    // public void addCourse(String code, String name, int credits, String grade) throws SQLException {
    //     String sql = "INSERT INTO course (code, name, credits, grade) VALUES (?, ?, ?, ?)";

    //     PreparedStatement pStatement = this.getConnection().prepareStatement(sql);
    //     pStatement.setString(1, code);
    //     pStatement.setString(2, name);
    //     pStatement.setInt(3, credits);
    //     pStatement.setString(4, grade);

    //     pStatement.executeUpdate();

    //     pStatement.close();
    // }

    //ADD STUDENT
    public void addStudent(String nim, String name, int year, String studyProgram) throws SQLException {
        String sql = "INSERT INTO student (nim, name, year, studyProgram) VALUES (?, ?, ?, ?)";

        PreparedStatement pStatement = this.getConnection().prepareStatement(sql);
        pStatement.setString(1, nim);
        pStatement.setString(2, name);
        pStatement.setInt(3, year);
        pStatement.setString(4, studyProgram);

        pStatement.executeUpdate();

        pStatement.close();
    }

    //ADD LECTURER
    public void addLecturer(String NID, String name, String initial, String email, String studyProgram) throws SQLException {
        String sql = "INSERT INTO lecturer (NID, name, initial, email, studyProgram) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement pStatement = this.getConnection().prepareStatement(sql);
        //0130058501|Parmonangan Rotua Togatorop|PAT|mona.togatorop@del.ac.id|Information Systems
        pStatement.setString(1, NID);
        pStatement.setString(2, name);
        pStatement.setString(3, initial);
        pStatement.setString(4, email);
        pStatement.setString(5, studyProgram);

        pStatement.executeUpdate();

        pStatement.close();
    }

    //add enrollment jika nim pada student dan code pada course ada
    public void addEnrollment(String code, String nim, String year, String semester, String grade) throws SQLException {
        String sql = "INSERT INTO enrollment (code, nim, year, semester, grade) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement pStatement = this.getConnection().prepareStatement(sql);
        pStatement.setString(2, nim);
        pStatement.setString(1, code);
        pStatement.setString(3, year);
        pStatement.setString(4, semester);
        pStatement.setString(5, grade);

        pStatement.executeUpdate();

        pStatement.close();
    }


    //PRINT ALL COURSES
    public void printAllCourses() throws SQLException {

        Statement statement = this.getConnection().createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM course");

        while (resultSet.next()){
            System.out.println(
                resultSet.getString("code") + "|" +
                resultSet.getString("name") + "|" +
                resultSet.getInt("credits") + "|" +
                resultSet.getString("grade")
            );
        }

        resultSet.close();
        statement.close();
    }

    //PRINT ALL STUDENTS
    public void printAllStudents() throws SQLException {

        Statement statement = this.getConnection().createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM student");

        while (resultSet.next()){
            System.out.println(
                resultSet.getString("nim") + "|" +
                resultSet.getString("name") + "|" +
                resultSet.getInt("year") + "|" +
                resultSet.getString("studyProgram")
            );
        }

        resultSet.close();
        statement.close();
    }

    //PRINT ALL LECTURERS
    public void printAllLecturers() throws SQLException {

        Statement statement = this.getConnection().createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM lecturer");

        while (resultSet.next()){
            System.out.println(
                resultSet.getString("NID") + "|" +
                resultSet.getString("name") + "|" +
                resultSet.getString("initial") + "|" +
                resultSet.getString("email") + "|" +
                resultSet.getString("studyProgram")
            );
        }

        resultSet.close();
        statement.close();
    }

    //PRINT ALL ENROLLMENT
    public void printAllEnrollment() throws SQLException {

        Statement statement = this.getConnection().createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM enrollment");

        while (resultSet.next()){
            System.out.println(
                resultSet.getString("code") + "|" +
                resultSet.getString("nim") + "|" +
                resultSet.getString("year") + "|" +
                resultSet.getString("semester") + "|" +
                resultSet.getString("grade")
            );
        }

        resultSet.close();
        statement.close();
    }

    // ENROLLMENT SET GRADE
    public void setGrade(String code, String nim, String year, String semester, String grade) throws SQLException {
        String sql = "UPDATE enrollment SET grade = ? WHERE code = ? AND nim = ? AND year = ? AND semester = ?";

        //enrollment-grade#12S2102#12S20001#2021/2022#odd#B

        PreparedStatement pStatement = this.getConnection().prepareStatement(sql);
        pStatement.setString(1, grade);
        pStatement.setString(3, nim);
        pStatement.setString(2, code);
        pStatement.setString(4, year);
        pStatement.setString(5, semester);


        pStatement.executeUpdate();

        pStatement.close();
    }

    // ENROLLMENT SET REMED 
    public void setRemed(String code, String nim, String year, String semester, String grade) throws SQLException {
        String queryEnrollment = "SELECT * FROM enrollment WHERE grade != 'None'";
        PreparedStatement statementEnrollment = this.getConnection().prepareStatement(queryEnrollment);
        //statementEnrollment.setString(1, nim);
        ResultSet enrollmentResult = statementEnrollment.executeQuery();

        while (enrollmentResult.next()) {
            String Enrolcode = enrollmentResult.getString("code");
            String Enrollnim = enrollmentResult.getString("nim");
            String Enrolyear = enrollmentResult.getString("year");
            String Enrolsemester = enrollmentResult.getString("semester");
            String Enrollgrade = enrollmentResult.getString("grade");

            if (Enrolcode.equals(code) && Enrollnim.equals(nim) && Enrolyear.equals(year) && Enrolsemester.equals(semester)){
                NilaiSebelum = Enrollgrade;
            }
        }

        String sql = "UPDATE enrollment SET grade = ? WHERE code = ? AND nim = ? AND year = ? AND semester = ? AND grade != 'None'";

        //String

        if (!(NilaiSebelum.equals(""))){
            PreparedStatement pStatement = this.getConnection().prepareStatement(sql);
            //NilaiSebelum = ;
            pStatement.setString(1, grade + "(" + NilaiSebelum + ")");
            pStatement.setString(3, nim);
            pStatement.setString(2, code);
            pStatement.setString(4, year);
            pStatement.setString(5, semester);

            pStatement.executeUpdate();

            pStatement.close();
        }
        
    }

    //PRINT STUDENT DETAILS tambahkan total ipk mahasiswa dan total sks mahasiswa
    public void printStudentDetails(String nim) throws SQLException {
        totalIPK = 0.0;
        totalSKS = 0;
        gradePoint = 0.00;
        BGrade = 0.00;
        BCourse = "";
        
        // Query untuk mengambil semua data mahasiswa
        String query = "SELECT * FROM student WHERE nim = ?";
        PreparedStatement statement = this.getConnection().prepareStatement(query);
        statement.setString(1, nim);
        ResultSet studentResult = statement.executeQuery();
    
        // Jika mahasiswa ditemukan
        while (studentResult.next()) {
            String studentName = studentResult.getString("name");
            String studyProgram = studentResult.getString("studyProgram");
            Integer year = studentResult.getInt("year");

            //cek apakah ada data di dalam enrollment yang memiliki nim yang sama
            String queryEnrollment = "SELECT * FROM enrollment WHERE nim = ?";
            PreparedStatement statementEnrollment = this.getConnection().prepareStatement(queryEnrollment);
            statementEnrollment.setString(1, nim);
            ResultSet enrollmentResult = statementEnrollment.executeQuery();

            // Jika ada data di dalam enrollment
            while (enrollmentResult.next()) {
                String code = enrollmentResult.getString("code");
                String grade = enrollmentResult.getString("grade");

                String[] Realgrade = grade.split("\\(");

                gradePoint = this.calculateGradePoint(Realgrade[0]);

                this.AllTotal(code, gradePoint);
            }
    
            // Menampilkan detail mahasiswa beserta total IPK dan total SKS
            System.out.println(nim + "|" + studentName + "|" + year + "|" + studyProgram + "|" + String.format("%.2f", totalIPK / totalSKS) + "|" + totalSKS);
        } 
    
        statement.close();
    }

    // ADD COURSE OPENING yang nilainya course iambil dari table course dan lakukan print
    public void addCourseOpening(String code, String year, String semester, String initial ) throws SQLException {

        String IE = "";
        //ambil dari course
        String query = "SELECT * FROM course WHERE code = ?";
        PreparedStatement statement = this.getConnection().prepareStatement(query);
        statement.setString(1, code);
        ResultSet Courseresult = statement.executeQuery();
        

        while (Courseresult.next()) {
            String courseAll = Courseresult.getString("code") +"|"+ Courseresult.getString("name") +"|"+ Courseresult.getInt("credits") +"|"+ Courseresult.getString("grade");
            String getLecturer = "SELECT * FROM lecturer WHERE initial = ?";
            PreparedStatement statementLecturer = this.getConnection().prepareStatement(getLecturer);
            statementLecturer.setString(1, initial);
            ResultSet Lecturerresult = statementLecturer.executeQuery();

            while (Lecturerresult.next()) {
                IE = Lecturerresult.getString("initial") + " (" + Lecturerresult.getString("email") + ")";

                String sql = "INSERT INTO course_opening (course, year, semester, IE) VALUES (?, ?, ?, ?)";

                PreparedStatement pStatement = this.getConnection().prepareStatement(sql);
                pStatement.setString(1, courseAll);
                pStatement.setString(2, year);
                pStatement.setString(3, semester);
                pStatement.setString(4, IE);

                pStatement.executeUpdate();

                pStatement.close();

                break;
            }

        }

    
    }

    // SHOW COURSE HISTORY

    public void showCourseHistory(String code) throws SQLException {
        String query = "SELECT * FROM course_opening ORDER BY CASE WHEN semester = 'odd' THEN 1 ELSE 2 END";
        PreparedStatement statement = this.getConnection().prepareStatement(query);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            String course = result.getString("course");
            String year = result.getString("year");
            String semester = result.getString("semester");
            String IE = result.getString("IE");

            System.out.println(course + "|" + year + "|" + semester + "|" + IE);

            //tokenize course
            String[] tokens = course.split("\\|");
            String courseCode = tokens[0];

            String queryEnrollment = "SELECT * FROM enrollment WHERE code = ? AND year = ? AND semester = ?";
            PreparedStatement statementEnrollment = this.getConnection().prepareStatement(queryEnrollment);
            statementEnrollment.setString(1, courseCode);
            statementEnrollment.setString(2, year);
            statementEnrollment.setString(3, semester);
            ResultSet enrollmentResult = statementEnrollment.executeQuery();

            while (enrollmentResult.next()) {
                String Enrolcode = enrollmentResult.getString("code");
                String Enrollnim = enrollmentResult.getString("nim");
                String Enrolyear = enrollmentResult.getString("year");
                String Enrolsemester = enrollmentResult.getString("semester");
                String Enrollgrade = enrollmentResult.getString("grade");

                System.out.println(Enrolcode + "|" + Enrollnim + "|" + Enrolyear + "|" + Enrolsemester + "|" + Enrollgrade);
            }

        }

        statement.close();
    }

    // PRINT STUDENT TRANSCRIPT
    public void printStudentTranscript(String nim) throws SQLException {
        totalIPK = 0.0;
        totalSKS = 0;
        gradePoint = 0.00;
        BGrade = 0.00;
        BCourse = "";
        
        // Query untuk mengambil semua data mahasiswa
        String query = "SELECT * FROM student WHERE nim = ?";
        PreparedStatement statement = this.getConnection().prepareStatement(query);
        statement.setString(1, nim);
        ResultSet studentResult = statement.executeQuery();
    
        // Jika mahasiswa ditemukan
        while (studentResult.next()) {
            String studentName = studentResult.getString("name");
            String studyProgram = studentResult.getString("studyProgram");
            Integer year = studentResult.getInt("year");


            //cek apakah ada data di dalam enrollment yang memiliki nim yang sama
            String RealEnroll = "SELECT * FROM enrollment m1 WHERE nim = ? AND CAST(SUBSTR(year, INSTR(year, '/') + 1) AS INTEGER) = (SELECT MAX(CAST(SUBSTR(year, INSTR(year, '/') + 1) AS INTEGER)) FROM enrollment m2 WHERE m1.code = m2.code)";
            PreparedStatement enrollmentStat = this.getConnection().prepareStatement(RealEnroll);
            enrollmentStat.setString(1, nim);
            ResultSet result = enrollmentStat.executeQuery();

            // Jika ada data di dalam enrollment
            while (result.next()) {
                String code = result.getString("code");
                String grade = result.getString("grade");

                String[] Realgrade = grade.split("\\(");

                gradePoint = this.calculateGradePoint(Realgrade[0]);

                this.AllTotal(code, gradePoint);
            }
    
            // Menampilkan detail mahasiswa beserta total IPK dan total SKS
            System.out.println(nim + "|" + studentName + "|" + year + "|" + studyProgram + "|" + String.format("%.2f", totalIPK / totalSKS) + "|" + totalSKS);
        }

        //while
        String query2 = "SELECT * FROM student WHERE nim = ?";
        PreparedStatement statement2 = this.getConnection().prepareStatement(query2);
        statement2.setString(1, nim);
        ResultSet studentResult2 = statement2.executeQuery();


        while (studentResult2.next()){

            

            String RealEnroll = "SELECT * FROM enrollment m1 WHERE nim = ? AND CAST(SUBSTR(year, INSTR(year, '/') + 1) AS INTEGER) = (SELECT MAX(CAST(SUBSTR(year, INSTR(year, '/') + 1) AS INTEGER)) FROM enrollment m2 WHERE m1.code = m2.code)";
            PreparedStatement enrollmentStat = this.getConnection().prepareStatement(RealEnroll);
            enrollmentStat.setString(1, nim);
            ResultSet result = enrollmentStat.executeQuery();

            // Jika ada data di dalam enrollment
            while (result.next()) {
                String Enrollnim = result.getString("nim");
                String Enrollcode = result.getString("code");
                String Enrolyear = result.getString("year");
                String Enrolsemester = result.getString("semester");
                String Enrollgrade = result.getString("grade");

                System.out.println(Enrollcode + "|" + Enrollnim + "|" + Enrolyear + "|" + Enrolsemester + "|" + Enrollgrade);
            }
        
        }
        
    
        statement2.close();
        statement.close();
    }



    // Method untuk mengambil jumlah kredit dari sebuah mata kuliah
    public void AllTotal(String code, Double gradePoint) throws SQLException {
        String query = "SELECT * FROM course WHERE code = ?";
        PreparedStatement statement = this.getConnection().prepareStatement(query);
        statement.setString(1, code);
        ResultSet Courseresult = statement.executeQuery();
        
        while (Courseresult.next()) {

            String coursecode = Courseresult.getString("code");

            if (code.equals(coursecode) && gradePoint > 0.00){
                // jika course selanjutnya sama dengan BCourse
                if (BCourse.equals(coursecode)){
                    Integer credits = Courseresult.getInt("credits");
                    
                    // Menghitung total IPK dan total SKS
                    totalSKS -= credits;
                    totalIPK -= gradePoint * credits;
                }
                // Mengambil jumlah kredit dari mata kuliah
                Integer credits = Courseresult.getInt("credits");
                
                // Menghitung total IPK dan total SKS
                totalSKS += credits;
                totalIPK += gradePoint * credits;
                
                BCourse = code;
                BGrade = gradePoint;
                break;
            }
            //return result.getInt("credits");
        }
    }

    
    // Method untuk menghitung nilai indeks berdasarkan grade
    private Double calculateGradePoint(String grade) {
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
       
    @Override
    protected void prepareTables() throws SQLException {
        this.createTables();
        //this.seedTables();
    }

}