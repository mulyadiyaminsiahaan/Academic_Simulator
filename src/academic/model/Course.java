package academic.model;

/**
 * @author 12S22034 Mulyadi Yamin Siahaan
 */
public class Course {

    // code course, name course, credits, and grade
    private String code;
    private String name;
    private int credits;
    private String grade;
    //private String pisah1;

    // constructor
    public Course(String _code, String _name, int _credits, String _grade) {
        this.code = _code;
        this.name = _name;
        this.credits = _credits;
        this.grade = _grade;

        //tadi salah
        //this.pisah1 = _pisah[1] + "();" + _pisah[2]+ ")";

        /* ini lanjut 100
        for (int i = 0; i < _pisah.length; i++){
            // di dalam array buat semua menjadi satu string dalam pisah1
            if (i == 0){
                this.pisah1 = _pisah[i];
            } else {
                this.pisah1 = this.pisah1 + ";" + _pisah[i];
            }


        }*/
    }

    // getter and setter
    public String getCode() {
        return this.code;
    }

    public Integer getCredit() {
        return this.credits;
    }


    // toString
    public String toString() {
        return this.code + "|" + this.name + "|" + this.credits + "|" + this.grade ;
    }

}