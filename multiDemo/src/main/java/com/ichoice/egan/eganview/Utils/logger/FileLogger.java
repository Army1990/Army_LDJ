package com.ichoice.egan.eganview.Utils.logger;

import com.hornen.eventbus.Action;
import com.hornen.eventbus.EventParameter;
import com.hornen.eventbus.EventsController;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Hornen on 15/9/29.
 */
public class FileLogger implements ILogger, Action<EventParameter> {

    private final static int FILE_LOGGER_DEALER_ID = 2005121899;

    private final static int LOG_BUFFER_SIZE = 2048;

    private StringBuffer buffer;

    private String path;

    private EventsController fileLogEventsCtr;

    private long lastWrite = System.currentTimeMillis();

    private long writeInteper = 1000;

    private ConsoleLogger logger = new ConsoleLogger();

    public FileLogger() {
        this("general.log");
    }

    public FileLogger(String filePath) {

        this.path = filePath;

        this.buffer = new StringBuffer();

        this.fileLogEventsCtr = new EventsController();

        this.fileLogEventsCtr.setEnable(true);

        this.fileLogEventsCtr.registDealer(FILE_LOGGER_DEALER_ID, this);
    }

    private String getFormatString(String tag, String msg) {
        SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss", Locale.US);
        String date = fmt.format(new Date());

        return String.format("%s %s:%s\r\n", date, tag, msg);
    }

    private void save(String tag, String msg) throws IOException {
        String log = getFormatString(tag, msg);

        synchronized (this.buffer) {

            this.buffer.append(log);

            if (this.buffer.length() >= LOG_BUFFER_SIZE || System.currentTimeMillis() - lastWrite > writeInteper) {
                lastWrite = System.currentTimeMillis();

                this.fileLogEventsCtr.fire(FILE_LOGGER_DEALER_ID, EventParameter.create(this.buffer.toString()));
                this.buffer.setLength(0);
            }
        }
    }

    @Override
    public void error(String tag, String msg) {
        try {
            logger.error(tag, msg);
            this.save(tag, msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void warn(String tag, String msg) {
        try {
            logger.warn(tag, msg);
            this.save(tag, msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void fatal(String tag, String msg) {
        try {
            logger.fatal(tag, msg);
            this.save(tag, msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void debug(String tag, String msg) {
        try {
            logger.debug(tag, msg);
            this.save(tag, msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exectue(EventParameter param) {
        try {
            FileWriter writer = new FileWriter(path, true);
            writer.write((String) param.obj);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
