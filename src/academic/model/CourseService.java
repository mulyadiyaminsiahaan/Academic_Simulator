package academic.model;

import java.sql.PreparedStatement;

public class CourseService {
    public void courseAdd(String _courseCode, String _courseName, int _courseCredits, String _courseGrade, ContactDatabase _database) throws Exception {
        String sql = "INSERT INTO course (code, name, credits, grade) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = _database.connection.prepareStatement(sql);
        preparedStatement.setString(1, _courseCode);
        preparedStatement.setString(2, _courseName);
        preparedStatement.setInt(3, _courseCredits);
        preparedStatement.setString(4, _courseGrade);
        
        preparedStatement.executeUpdate();

        preparedStatement.close();
    }

    


}
