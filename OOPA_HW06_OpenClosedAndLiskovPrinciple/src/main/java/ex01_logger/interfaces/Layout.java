package ex01_logger.interfaces;

import ex01_logger.models.enums.ReportLevel;

public interface Layout {
    String format(ReportLevel level, String date, String message);
}
