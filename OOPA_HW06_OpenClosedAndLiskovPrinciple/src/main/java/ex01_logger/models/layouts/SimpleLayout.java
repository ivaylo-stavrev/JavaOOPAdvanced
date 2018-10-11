package ex01_logger.models.layouts;

import ex01_logger.interfaces.Layout;
import ex01_logger.models.enums.ReportLevel;

public class SimpleLayout implements Layout {

    public SimpleLayout() {
    }

    @Override
    public String format(ReportLevel level, String date, String message) {
        return String.format("%s - %s - %s", date, level, message);
    }
}
