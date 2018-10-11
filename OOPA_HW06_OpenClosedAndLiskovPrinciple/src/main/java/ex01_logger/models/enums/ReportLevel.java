package ex01_logger.models.enums;

public enum ReportLevel {
    INFO,
    WARNING,
    ERROR,
    CRITICAL,
    FATAL;


    @Override
    public String toString() {
        return String.valueOf(this.name().charAt(0) + this.name().substring(1));
    }
}
