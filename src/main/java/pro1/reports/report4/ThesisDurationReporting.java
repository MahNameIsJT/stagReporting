package pro1.reports.report4;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.Thesis;
import pro1.apiDataModel.ThesisList;
import pro1.reports.report4.reportDataModel.ThesisDurationItem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ThesisDurationReporting {

    public static ThesisDurationItem[] GetReport(DataSource dataSource, String katedra, String[] years) {
        var reportItems = new ThesisDurationItem[years.length];
        var gson = new Gson();
        var formatter = DateTimeFormatter.ofPattern("d.M.yyyy");

        for (int i = 0; i < years.length; i++) {
            var year = years[i];
            var json = dataSource.getKvalifikacniPrace(year, katedra);

            long totalDays = 0;
            int count = 0;

            if (json != null && !json.trim().isEmpty()) {
                var thesisList = gson.fromJson(json, ThesisList.class);

                if (thesisList != null && thesisList.kvalifikacniPrace != null) {
                    for (Thesis t : thesisList.kvalifikacniPrace) {
                        if (t.datumZadani != null && t.datumZadani.value != null &&
                                t.datumOdevzdani != null && t.datumOdevzdani.value != null) {

                            try {
                                var valOd = t.datumZadani.value.split(" ")[0];
                                var valDo = t.datumOdevzdani.value.split(" ")[0];

                                var datumOd = LocalDate.parse(valOd, formatter);
                                var datumDo = LocalDate.parse(valDo, formatter);

                                totalDays += ChronoUnit.DAYS.between(datumOd, datumDo);
                                count++;
                            } catch (Exception e) {
                            }
                        }
                    }
                }
            }

            long averageDays = count > 0 ? Math.round((double) totalDays / count) : 0;
            reportItems[i] = new ThesisDurationItem(year, averageDays);
        }

        return reportItems;
    }
}