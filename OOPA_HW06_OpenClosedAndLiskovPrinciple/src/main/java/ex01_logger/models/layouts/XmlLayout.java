package ex01_logger.models.layouts;

import ex01_logger.interfaces.Layout;
import ex01_logger.models.enums.ReportLevel;

public class XmlLayout implements Layout {
    public static final String LOG_TAG_OPEN = "<log>";
    public static final String LOG_TAG_CLOSE = "</log>";
    public static final String DATE_TAG_OPEN = "   <date>";
    public static final String DATE_TAG_CLOSE = "</date>";
    public static final String LEVEL_TAG_OPEN = "   <level>";
    public static final String LEVEL_TAG_CLOSE = "</level>";
    public static final String MSG_TAG_OPEN = "   <message>";
    public static final String MSG_TAG_CLOSE = "</message>";

    public XmlLayout() {
    }

    @Override
    public String format(ReportLevel level, String date, String message) {
        StringBuilder sb = new StringBuilder();
        sb.append(LOG_TAG_OPEN).append(System.lineSeparator())
                .append(DATE_TAG_OPEN).append(date).append(DATE_TAG_CLOSE).append(System.lineSeparator())
                .append(LEVEL_TAG_OPEN).append(level).append(LEVEL_TAG_CLOSE).append(System.lineSeparator())
                .append(MSG_TAG_OPEN).append(message).append(MSG_TAG_CLOSE).append(System.lineSeparator())
                .append(LOG_TAG_CLOSE);
        return sb.toString();
    }
}
