package ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl;

import ir.mctab.java32.projects.scholarshipmanagement.core.config.DatabaseConfig;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.DashboardUseCase;
import ir.mctab.java32.projects.scholarshipmanagement.model.Scholarship;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DashboardImpl implements DashboardUseCase {
    public List<Scholarship> Dashboard() {
        List<Scholarship> dashboardResult = new ArrayList<Scholarship>();
        // connection
        Connection connection = null;
        try {
            connection = DatabaseConfig.getDatabaseConnection();
            // query
            String requestedByStudent = "SELECT * From scholarship where status = 'RequestedByStudent' ";
            String acceptedByManager = "select COUNT(*) from scholarship where status = 'AcceptedByManager' ";
            String rejectedByManager = "select COUNT(*) from scholarship where status = 'RejectedByManager' ";
            String acceptedBySupervisor = "select COUNT(*) from scholarship where status = 'AcceptedBySupervisor' ";
            String rejectedBySupervisor = "select COUNT(*) from scholarship where status = 'RejectedBySupervisor' ";

            // result
            PreparedStatement psRequestedByStudent = connection.prepareStatement(requestedByStudent);
            PreparedStatement psAcceptedByManager = connection.prepareStatement(acceptedByManager);
            PreparedStatement psRejectedByManager = connection.prepareStatement(rejectedByManager);
            PreparedStatement psAcceptedBySupervisor = connection.prepareStatement(acceptedBySupervisor);
            PreparedStatement psRejectedBySupervisor = connection.prepareStatement(rejectedBySupervisor);
            ResultSet rsReqBySt = psRequestedByStudent.executeQuery();
            ResultSet rsAccByMan = psAcceptedByManager.executeQuery();
            ResultSet rsRejByMan = psRejectedByManager.executeQuery();
            ResultSet rsAccBySv = psAcceptedBySupervisor.executeQuery();
            ResultSet rsRejBySv = psRejectedBySupervisor.executeQuery();
            int i=0;
            while (rsReqBySt.next()) {
                i++;
            }int j=0;
            while (rsAccByMan.next()) {
                j++;
            }int k=0;
            while (rsRejByMan.next()) {
                k++;
            }int w=0;
            while (rsAccBySv.next()) {
                w++;
            }int v=0;
            while (rsRejBySv.next()) {
                v++;
            }
            System.out.println("________________________________");
            System.out.println("RequestedByStudent:\t\t\t" +i);
            System.out.println("________________________________");
            System.out.println("AcceptedByManager:\t\t\t" +j);
            System.out.println("________________________________");
            System.out.println("RejectedByManager:\t\t\t" +k);
            System.out.println("________________________________");
            System.out.println("AcceptedBySupervisor:\t\t" +w);
            System.out.println("________________________________");
            System.out.println("RejectedBySupervisor:\t\t" +v);
            System.out.println("________________________________");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dashboardResult;
    }
}
