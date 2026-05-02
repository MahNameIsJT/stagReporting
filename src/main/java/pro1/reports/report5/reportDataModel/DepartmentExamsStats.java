package pro1.reports.report5.reportDataModel;

import java.util.List;

public class DepartmentExamsStats {
    public int realizedExamsCount;
    public List<String> reservedRooms;

    public DepartmentExamsStats(int realizedExamsCount, List<String> reservedRooms) {
        this.realizedExamsCount = realizedExamsCount;
        this.reservedRooms = reservedRooms;
    }
}