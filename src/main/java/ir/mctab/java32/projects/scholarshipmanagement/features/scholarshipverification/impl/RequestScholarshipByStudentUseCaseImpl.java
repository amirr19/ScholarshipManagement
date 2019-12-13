package ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl;

import ir.mctab.java32.projects.scholarshipmanagement.core.annotations.Service;
import ir.mctab.java32.projects.scholarshipmanagement.core.annotations.UseCase;
import ir.mctab.java32.projects.scholarshipmanagement.core.config.DatabaseConfig;
import ir.mctab.java32.projects.scholarshipmanagement.core.share.AuthenticationService;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.RequestScholarshipByStudentUseCase;
import ir.mctab.java32.projects.scholarshipmanagement.model.Scholarship;
import ir.mctab.java32.projects.scholarshipmanagement.model.User;

import java.sql.*;
import java.util.List;

@Service
public class RequestScholarshipByStudentUseCaseImpl implements RequestScholarshipByStudentUseCase {

    public void request(String name, String family, String nationalCode,
                        String lastUni, String lastDegree, String lastField,
                        Float lastScore, String applyUni, String applyDegree,
                        String applyField, String applyDate) {
        User loginUser = AuthenticationService.getInstance().getLoginUser();
        if (loginUser != null) {
            if (loginUser.getRole().equals("student")) {
                //connection
                Connection connection = null;
                try {
                    //query
                    String sql = "INSERT INTO scholarship(status,name,family,nationalCode,lastUni,lastDegree,lastField,lastScore,applyUni,applyDegree,applyField,applyDate)  VALUE (?,?,?,?,?,?,?,?,?,?,?,?)";
                    String sqlLog = "insert into scholarship_log (date,userID,fkscholarshipID,action)values" +
                            " (now(),?,?,'RequestByStudent')";
                    connection = DatabaseConfig.getDatabaseConnection();

                    PreparedStatement psLog = connection.prepareStatement(sqlLog);
                    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                    ps.setString(1, "RequestedByStudent");
                    ps.setString(2, name);
                    ps.setString(3, family);
                    ps.setString(4, nationalCode);
                    ps.setString(5, lastUni);
                    ps.setString(6, lastDegree);
                    ps.setString(7, lastField);
                    ps.setFloat(8, lastScore);
                    ps.setString(9, applyUni);
                    ps.setString(10, applyDegree);
                    ps.setString(11, applyField);
                    ps.setString(12, applyDate);
                    if (ps.executeUpdate() != 0) {
                        System.out.println("ur request has been sent!");
                        ResultSet rs = ps.getGeneratedKeys();
                        if (rs.next()){
                            psLog.setLong(1,loginUser.getId());
                            psLog.setLong(2,rs.getInt(1));
                        }
                        if(psLog.executeUpdate()!=0){
                            System.out.println("Request Scholarship insert into log.");
                        }
                    }
                    ps.close();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
