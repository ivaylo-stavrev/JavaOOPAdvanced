package ex01_logger.models.appenders;

import ex01_logger.interfaces.Appender;
import ex01_logger.interfaces.Layout;
import ex01_logger.io.implementations.ConsoleWriter;
import ex01_logger.io.io_interfaces.Writer;
import ex01_logger.models.enums.ReportLevel;

public class ConsoleAppender implements Appender {
    public static final ReportLevel DEFAULT_REPORT_LEVEL    = ReportLevel.INFO;
    public static final int         DEFAULT_COUNTER_VALUE   = 0;

    private ReportLevel reportLevel;
    private Layout layout;
    private Writer writer;
    private int appendedMsgCnt;

    public ConsoleAppender(Layout layout) {
        this.reportLevel = DEFAULT_REPORT_LEVEL;
        this.layout = layout;
        this.writer = new ConsoleWriter();
        this.appendedMsgCnt = DEFAULT_COUNTER_VALUE;
    }

    @Override
    public ReportLevel getReportLevel() {
        return this.reportLevel;
    }

    @Override
    public void setReportLevel(ReportLevel reportLevel) {
        this.reportLevel = reportLevel;
    }

    @Override
    public void append(ReportLevel level, String date, String message) {
        if (this.reportLevel.ordinal() <= level.ordinal()) {
            String formatedLog = this.layout.format(level, date, message);

            this.writer.writeLine(formatedLog);
            this.appendedMsgCnt++;
        }
    }

    @Override
    public String toString() {
        return String.format("Appender type: %s, Layout type: %s, Report level: %s, Messages appended: %d",
                this.getClass().getSimpleName(),
                this.layout.getClass().getSimpleName(),
                this.reportLevel.name(),
                this.appendedMsgCnt);
    }
}
