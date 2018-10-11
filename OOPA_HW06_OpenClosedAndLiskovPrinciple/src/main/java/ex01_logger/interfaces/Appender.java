package ex01_logger.interfaces;

import ex01_logger.models.enums.ReportLevel;

public interface Appender {
    void append(ReportLevel level, String date, String message);

    ReportLevel getReportLevel();

    void setReportLevel(ReportLevel reportLevel);
}
