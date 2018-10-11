package ex01_logger.models.appenders;

import ex01_logger.interfaces.Appender;
import ex01_logger.interfaces.File;
import ex01_logger.interfaces.Layout;
import ex01_logger.models.enums.ReportLevel;
import ex01_logger.models.files.LogFile;

public class FileAppender implements Appender {
    private final ReportLevel DEFAULT_REPORT_LEVEL = ReportLevel.INFO;

    private Layout layout;
    private ReportLevel reportLevel;
    private File file;
    private long appendedMessageCount;

    public FileAppender(Layout layout) {
        this.layout = layout;
        this.reportLevel = DEFAULT_REPORT_LEVEL;
        this.file = new LogFile();
    }

    @Override
    public void append(ReportLevel level, String date, String message) {
        if (this.reportLevel.ordinal() <= level.ordinal()) {
            if (this.file == null) {
                throw new IllegalStateException("Cannot write a log when custom File class is null.");
            }
            this.file.write(this.layout.format(level, date, message));
            this.appendedMessageCount++;
        }
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
    public String toString() {
        return String.format("Appender type: %s, Layout type: %s, Report level: %s, Messages appended: %d, File size: %d",
                this.getClass().getSimpleName(),
                this.layout.getClass().getSimpleName(),
                this.reportLevel.name(),
                this.appendedMessageCount,
                this.file.getSize());
    }
}
