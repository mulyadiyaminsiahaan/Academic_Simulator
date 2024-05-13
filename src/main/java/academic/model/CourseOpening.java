package main.java.academic.model;

/**
 * @author 12S22034 Mulyadi Yamin Siahaan
 */
public class CourseOpening<T>{

    // class definition
    //course-open#12S1101#2020/2021#odd#IUS
    private Course code;
    private String year;
    private String semester;
    private String lecturerInitial;

    // constructor
    public CourseOpening(Course course, String _year, String _semester, String _lecturerInitial) {
        this.code = course;
        this.year = _year;
        this.semester = _semester;
        this.lecturerInitial = _lecturerInitial;
    }

    //ambil code dari course
    public String getCodeCourse(){
        return code.getCode();
    }

    // getter
    public Course getCourse() {
        return this.code;
    }

    public String getYear() {
        return this.year;
    }

    public String getSemester() {
        return this.semester;
    }

    public String getLecturerInitial() {
        return this.lecturerInitial;
    }

    // toString
    public String toString() {
        return this.code + "|" + this.year + "|" + this.semester + "|" + this.lecturerInitial;
    }

}