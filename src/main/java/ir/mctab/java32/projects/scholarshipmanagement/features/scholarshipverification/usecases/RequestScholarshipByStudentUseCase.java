package ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases;

import ir.mctab.java32.projects.scholarshipmanagement.core.annotations.UseCase;
import ir.mctab.java32.projects.scholarshipmanagement.model.Scholarship;

import java.util.ArrayList;
import java.util.List;

@UseCase
public interface RequestScholarshipByStudentUseCase {
    void request(
            String name, String family, String nationalCode, String lastUni, String lastDegree, String lastField,
            Float lastScore, String applyUni, String applyDegree, String applyField, String applyDate
//        Scholarship scholarship
    );
}
