package main.java.academic.model;

//package inheritance;

/**
 * @author 12S22034 Mulyadi Yamin Siahaan
 */

public class Lecturer extends Person {

    //nidn, name, initial, email, and study program
    private String initial;
    private String email;
    

    // constructor
    public Lecturer(String _nidn, String _name, String _initial, String _email, String _studyProgram) {
        this.nim = _nidn;
        this.name = _name;
        this.initial = _initial;
        this.email = _email;
        this.studyProgram = _studyProgram;
    }


    public String getInitial() {
        return this.initial;
    }

    public String getEmail() {
        return this.email;
    }

    // toString
    public String toString() {
        return this.nim + "|" + this.name + "|" + this.initial + "|" + this.email + "|" + this.studyProgram;
    }
    
}
