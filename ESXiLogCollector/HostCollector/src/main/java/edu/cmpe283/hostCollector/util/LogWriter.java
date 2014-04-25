package edu.cmpe283.hostCollector.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class LogWriter {

    private FileOutputStream outputStream;
    private OutputStreamWriter outputStreamWriter;
    private String fileName;
    private static long MAX_LOG_SIZE = 1048576;

    public LogWriter(String fileName) throws IOException {
        this.fileName = fileName;
        reset();
    }

    private void reset() throws IOException {
        if (outputStream != null) {
            outputStream.close();
        }
        outputStream = new FileOutputStream(fileName, false);
        outputStreamWriter = new OutputStreamWriter(outputStream);
    }

    public void close() throws IOException {
        outputStream.close();
    }

    public void write(String s) throws IOException {
        if (outputStream.getChannel().size() > MAX_LOG_SIZE) {
            reset();
        }
        outputStreamWriter.write(s);
        outputStreamWriter.flush();
    }
}
