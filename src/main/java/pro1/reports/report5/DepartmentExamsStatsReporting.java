package pro1.reports.report5;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import pro1.DataSource;
import pro1.reports.report5.reportDataModel.DepartmentExamsStats;

import java.util.ArrayList;
import java.util.TreeSet;

public class DepartmentExamsStatsReporting {

    public static DepartmentExamsStats GetReport(DataSource dataSource, String katedra) {
        var json = dataSource.getTerminyZkousek2(katedra);

        int count = 0;
        var rooms = new TreeSet<String>(); 

        if (json != null && !json.trim().isEmpty()) {
            try {
                JsonElement root = new JsonParser().parse(json);
                
                JsonArray examsArray = findArray(root);

                if (examsArray != null) {
                    for (JsonElement elem : examsArray) {
                        if (!elem.isJsonObject()) continue;
                        JsonObject exam = elem.getAsJsonObject();

                        int prihlaseno = 0;
                        if (exam.has("pocetPrihlasenych") && !exam.get("pocetPrihlasenych").isJsonNull()) {
                            prihlaseno = exam.get("pocetPrihlasenych").getAsInt();
                        } else if (exam.has("obsazeni") && !exam.get("obsazeni").isJsonNull()) {
                            prihlaseno = exam.get("obsazeni").getAsInt();
                        }

                        if (prihlaseno > 0) {
                            count++;
                        }

                        if (exam.has("mistnost") && !exam.get("mistnost").isJsonNull()) {
                            String mistnost = exam.get("mistnost").getAsString().trim();
                            if (!mistnost.isEmpty()) {
                                rooms.add(mistnost);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Chyba při parsování JSONu: " + e.getMessage());
            }
        }

        return new DepartmentExamsStats(count, new ArrayList<>(rooms));
    }

    private static JsonArray findArray(JsonElement element) {
        if (element.isJsonArray()) {
            return element.getAsJsonArray();
        }
        if (element.isJsonObject()) {
            JsonObject obj = element.getAsJsonObject();
            for (String key : obj.keySet()) {
                JsonArray arr = findArray(obj.get(key));
                if (arr != null) return arr;
            }
        }
        return null;
    }
}