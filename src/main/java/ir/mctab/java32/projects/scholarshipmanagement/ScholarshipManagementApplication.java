package ir.mctab.java32.projects.scholarshipmanagement;

import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl.*;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.*;
import ir.mctab.java32.projects.scholarshipmanagement.features.usermanagement.impl.LoginUseCaseImpl;
import ir.mctab.java32.projects.scholarshipmanagement.features.usermanagement.usecases.LoginUseCase;
import ir.mctab.java32.projects.scholarshipmanagement.model.Scholarship;
import ir.mctab.java32.projects.scholarshipmanagement.model.User;

import java.util.List;
import java.util.Scanner;

public class ScholarshipManagementApplication {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String command = "";
        while (!command.equals("exit")) {
            System.out.println("what do you want? ");
            command = scanner.nextLine();
            // Login
            if (command.equals("login")) {
                System.out.println("Username : ");
                String username = scanner.nextLine();
                System.out.println("Password : ");
                String password = scanner.nextLine();
                LoginUseCase loginUseCase = new LoginUseCaseImpl();
                User user = loginUseCase.login(username, password);
                if (user != null) {
                    System.out.println(" Login successful by " + user.getRole());
                }
            }
            if(command.equals("dashboard")){
                DashboardUseCase dashboardUseCase
                        = new DashboardImpl();

                List<Scholarship> dashboard= dashboardUseCase.Dashboard();

            }
            //request Scholarship by student
            if (command.equals("request")) {
                RequestScholarshipByStudentUseCase requestScholarshipByStudentUseCase =
                        new RequestScholarshipByStudentUseCaseImpl();
                Scanner scanner1 = new Scanner(System.in);
                System.out.println("pls inter ur name:");
                String name = scanner1.nextLine();
                System.out.println("pls inter ur family:");
                String family = scanner1.nextLine();
                System.out.println("pls inter ur nationalCode:");
                String nationalCode = scanner1.nextLine();

                System.out.println("pls inter ur lastUni :");
                String lastUni = scanner1.nextLine();

                System.out.println("pls inter ur lastDegree:");
                String lastDegree = scanner1.nextLine();

                System.out.println("pls inter ur lastField:");
                String lastField = scanner1.nextLine();

                System.out.println("pls inter ur lastScore:");
                float lastScore = scanner1.nextFloat();
                String s = scanner1.nextLine();
                System.out.println("pls inter ur applyUni:");
                String applyUni = scanner1.nextLine();

                System.out.println("pls inter ur applyDegree:");
                String goal_level = scanner1.nextLine();

                System.out.println("pls inter ur apply major :");
                String applyDegree = scanner1.nextLine();

                System.out.println("pls inter ur applyField:");
                String applyField = scanner1.nextLine();

                System.out.println("pls inter ur applyDate:");
                String applyDate = scanner1.nextLine();

                requestScholarshipByStudentUseCase.request(
                        name,
                        family,
                        nationalCode,
                        lastUni,
                        lastDegree,
                        lastField,
                        lastScore,
                        applyUni,
                        goal_level,
                        applyField,
                        applyDegree);
            }

            // find scholarship by supervisor
            if (command.equals("svlist")) {
                FindScholarshipBySupervisorUseCase findScholarshipBySupervisorUseCase
                        = new FindScholarshipBySupervisorUseCaseImpl();

                List<Scholarship> scholarships = findScholarshipBySupervisorUseCase
                        .listScholarships();
                for (int i = 0; i < scholarships.size(); i++) {
                    System.out.println(scholarships.get(i));
                }
            }
            if (command.equals("manlist")) {
                FindScholarshipByManagerUseCase findScholarshipByManagerUseCase = new FindScholarshipByManagerUseCaseImpl();
                List<Scholarship> scholarships = findScholarshipByManagerUseCase
                        .listScholarships();
                for (int i = 0; i < scholarships.size(); i++) {
                    System.out.println(scholarships.get(i));
                }
            }
            if (command.equals("unilist")) {
                FindScholarshipByUniversityUseCase findScholarshipByUniversityUseCase = new FindScholarshipByUniversityUseCaseImpl();
                List<Scholarship> scholarships = findScholarshipByUniversityUseCase
                        .listScholarships();
                for (int i = 0; i < scholarships.size(); i++) {
                    System.out.println(scholarships.get(i));
                }
            }
            // accept
            if (command.equals("svaccept")) {
                AcceptScholarshipBySupervisorUseCase acceptScholarshipBySupervisorUseCase
                        = new AcceptScholarshipBySupervisorUseCaseImpl();
                System.out.println("Scholarship Id: ");
                String scholarshipId = scanner.nextLine();
                acceptScholarshipBySupervisorUseCase.accept(Long.parseLong(scholarshipId));
                System.out.println("Done.");
            }
            if (command.equals("svreject")) {
                RejectScholarshipBySupervisorUseCase rejectScholarshipBySupervisorUseCase
                        = new RejectScholarshipBySupervisorUseCaseImpl();
                System.out.println("Scholarship Id: ");
                String scholarshipId = scanner.nextLine();
                rejectScholarshipBySupervisorUseCase.reject(Long.parseLong(scholarshipId));
                System.out.println("Reject Done.");
            }
            if (command.equals("manaccept")) {
                AcceptScholarshipByManagerUseCase acceptScholarshipByManagerUseCase
                        = new AcceptScholarshipByManagerUseCaseImpl();
                System.out.println("Scholarship Id: ");
                String scholarshipId = scanner.nextLine();
                acceptScholarshipByManagerUseCase.accept(Long.parseLong(scholarshipId));
                System.out.println("Done.");
            }
            //regected
            if (command.equals("manreject")) {
                RejectScholarshipByManagerUseCase rejectScholarshipByManagerUseCase
                        = new RejectScholarshipByManagerUseCaseImpl();
                System.out.println("Scholarship Id: ");
                String scholarshipId = scanner.nextLine();
                rejectScholarshipByManagerUseCase.reject(Long.parseLong(scholarshipId));
                System.out.println("Reject Done.");
            }
        }
    }
}
