package main.java.academic.model;

/**
 * @author 12S22034 Mulyadi Yamin Siahaan
 */
public class Enrollment {

    // nim, code course, tahun ajaran, semester, dan nilai
    private String nim;
    private String code;
    private String year;
    private String semester;
    private String grade;
    String nilaiRemed = "" ;


    // constructor
    public Enrollment(String _code, String _nim, String _year, String _semester, String _grade) {
        this.nim = _nim;
        this.code = _code;
        this.year = _year;
        this.semester = _semester;
        this.grade = _grade;

    }

    public String getCode() {
        return this.code;
    }

    public String getYear() {
        return this.year;
    }

    public String getNim() {
        return this.nim;
    }

    public String setGrade(String Grade) {
        return this.grade = Grade;
    }

    public String getGrade() {
        return this.grade;
    }

    public void setNilaiRemed(String nilai){
        this.nilaiRemed = nilai;
    }

    public String getNilaiRemed(){
        return this.nilaiRemed;
    }

    public String getSemester() {
        return this.semester;
    }
    
    // toString
    public String toString() {
        if (this.nilaiRemed == "") {
            return this.code + "|" + this.nim + "|" + this.year + "|" + this.semester + "|" + this.grade;
        } else {
            return this.code + "|" + this.nim + "|" + this.year + "|" + this.semester + "|" + this.grade +"("+ nilaiRemed + ")";
        }
    }

}