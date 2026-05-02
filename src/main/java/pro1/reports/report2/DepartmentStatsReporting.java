package pro1.reports.report2;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.ActionsList;
import pro1.reports.report2.reportDataModel.DepartmentStats;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DepartmentStatsReporting {

    public static DepartmentStats GetReport(DataSource dataSource, String rok, String katedra) {
        var json = dataSource.getRozvrhByKatedra(rok, katedra);
        var actionsList = new Gson().fromJson(json, ActionsList.class);

        long maxStudents = 0;
        long emptyCount = 0;
        Map<Long, Long> teacherScores = new HashMap<>();

        if (actionsList != null && actionsList.items != null) {
            for (var action : actionsList.items) {
                if (action.katedra == null || !katedra.equalsIgnoreCase(action.katedra)) {
                    continue;
                }
                if (action.obsazeni == 0) {
                    emptyCount++;
                }
                if (action.obsazeni > maxStudents) {
                    maxStudents = action.obsazeni;
                }
                if (action.ucitIdno != null) {
                    teacherScores.put(action.ucitIdno, 
                        teacherScores.getOrDefault(action.ucitIdno, 0L) + action.obsazeni);
                }
            }
        }

        long maxScore = teacherScores.isEmpty() ? 0 : Collections.max(teacherScores.values());

        return new DepartmentStats(maxStudents, emptyCount, maxScore);
    }
}