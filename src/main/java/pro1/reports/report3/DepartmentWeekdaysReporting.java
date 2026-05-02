package pro1.reports.report3;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.ActionsList;
import pro1.reports.report3.reportDataModel.WeekdayReportItem;

public class DepartmentWeekdaysReporting {

    public static WeekdayReportItem[] GetReport(DataSource dataSource, String rok, String katedra, String[] days) {

        var akceJson = dataSource.getRozvrhByKatedra(rok, katedra);
        var actionsList = new Gson().fromJson(akceJson, ActionsList.class);
        var reportItems = new WeekdayReportItem[days.length];

        for (int i = 0; i < days.length; i++) {
            var denJmeno = days[i];
            var denZkr = prevedNaZkratku(denJmeno);
            var count = 0;

            if (actionsList != null && actionsList.items != null) {
                for (var a : actionsList.items) {
                    if (a.denZkr != null && a.denZkr.equalsIgnoreCase(denZkr)) {
                        count++;
                    }
                }
            }

            reportItems[i] = new WeekdayReportItem(denZkr, count);
        }

        return reportItems;
    }

    private static String prevedNaZkratku(String den) {
        if (den == null)
            return "";
        if (den.length() <= 2)
            return den;

        switch (den.toLowerCase()) {
            case "pondělí":
                return "Po";
            case "úterý":
                return "Út";
            case "středa":
                return "St";
            case "čtvrtek":
                return "Čt";
            case "pátek":
                return "Pá";
            case "sobota":
                return "So";
            case "neděle":
                return "Ne";
            default:
                return den;
        }
    }
}