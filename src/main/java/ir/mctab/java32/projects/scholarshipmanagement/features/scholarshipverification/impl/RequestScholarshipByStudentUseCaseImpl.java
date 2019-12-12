package ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl;

import ir.mctab.java32.projects.scholarshipmanagement.core.annotations.Service;
import ir.mctab.java32.projects.scholarshipmanagement.core.annotations.UseCase;
import ir.mctab.java32.projects.scholarshipmanagement.core.config.DatabaseConfig;
import ir.mctab.java32.projects.scholarshipmanagement.core.share.AuthenticationService;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.RequestScholarshipByStudentUseCase;
import ir.mctab.java32.projects.scholarshipmanagement.model.Scholarship;
import ir.mctab.java32.projects.scholarshipmanagement.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class RequestScholarshipByStudentUseCaseImpl implements RequestScholarshipByStudentUseCase {

    public void request(String name, String family, String nationalCode,
                        String lastUni, String lastDegree, String lastField,
                        Float lastScore, String applyUni, String applyDegree,
                        String applyField, String applyDate) {
        User loginUser = AuthenticationService.getInstance().getLoginUser();
        if (loginUser!= null){
            if(loginUser.getRole().equals("student")){
                //connection
                Connection connection = null;
                try {
                    connection = DatabaseConfig.getDatabaseConnection();
                    //query
                    String requestByStudent = "INSERT INTO scholarship(status,name,family,nationalCode,lastUni,lastDegree,lastField,lastScore,applyUni,applyDegree,applyField,applyDate)  VALUE (?,?,?,?,?,?,?,?,?,?,?,?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(requestByStudent);
                    preparedStatement.setString(1,"RequestedByStudent");
                    preparedStatement.setString(2,name);
                    preparedStatement.setString(3,family);
                    preparedStatement.setString(4,nationalCode);
                    preparedStatement.setString(5,lastUni);
                    preparedStatement.setString(6,lastDegree);
                    preparedStatement.setString(7,lastField);
                    preparedStatement.setFloat(8,lastScore);
                    preparedStatement.setString(9,applyUni);
                    preparedStatement.setString(10,applyDegree);
                    preparedStatement.setString(11,applyField);
                    preparedStatement.setString(12,applyDate);
                    preparedStatement.executeUpdate();
                    preparedStatement.close();

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
