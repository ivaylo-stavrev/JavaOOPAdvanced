package ex01_logger.models.files;

import ex01_logger.interfaces.File;

public class LogFile implements File {
    private StringBuilder sb;

    public LogFile() {
        this.sb = new StringBuilder();
    }

    @Override
    public void write(String log) {
        this.sb.append(log).append(System.lineSeparator());
    }

    @Override
    public long getSize() {
        String log = this.sb.toString();
        long fileSize = 0;
        for (int i = 0; i < log.length(); i++) {
            if (String.valueOf(log.charAt(i)).matches("[A-Za-z]")) {
                fileSize += log.charAt(i);
            }
        }
        return fileSize;
    }
}
