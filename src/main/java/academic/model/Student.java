package main.java.academic.model;

//package inheritance;
/**
 * @author 12S22034 Mulyadi Yamin Siahaan
 */
public class Student extends Person {

    // nim, nama, angkatan, dan prodi
    private int year;

    // constructor
    public Student(String _nim, String _name, int _year, String _studyProgram) {
        this.nim = _nim;
        this.name = _name;
        this.year = _year;
        this.studyProgram = _studyProgram;
    }

    public int getYear() {
        return this.year;
    }

    public String getStudyProgram() {
        return this.studyProgram;
    }

    public String getName() {
        return this.name;
    }

    public String getNim() {
        return this.nim;
    }

    // toString

    public String toString() {
        return this.nim + "|" + this.name + "|" + this.year + "|" + this.studyProgram;
    }

}
