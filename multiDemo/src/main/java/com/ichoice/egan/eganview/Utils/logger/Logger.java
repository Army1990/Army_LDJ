package com.ichoice.egan.eganview.Utils.logger;

/**
 * Created by Hornen on 15/9/29.
 */
public class Logger implements ILogger {

    public final static String DEBUG = "DEBUG";
    public final static String WARN = "WARN";
    public final static String ERROR = "ERROR";
    public final static String FATAL = "FATAL";

    private final static int DEBUG_LEVEL = 1;
    private final static int WARN_LEVEL = 2;
    private final static int ERROR_LEVEL = 3;
    private final static int FATAL_LEVEL = 4;

    protected int level = DEBUG_LEVEL;
    protected boolean enable = true;

    private ILogger logger = new ConsoleLogger();
    private ILogger emptyLogger = new EmptyLogger();

    private static Logger instance = new Logger();

    public void setLevel(String level) {
        if(level.equals(DEBUG)) {
            this.level = DEBUG_LEVEL;
        } else if(level.equals(WARN)) {
            this.level = WARN_LEVEL;
        } else if(level.equals(ERROR)) {
            this.level = ERROR_LEVEL;
        } else if(level.equals(FATAL)) {
            this.level = FATAL_LEVEL;
        }
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void setLogger(ILogger logger) {
        this.logger = logger;
    }

    private ILogger getLogger() {
        if(this.logger == null) {
            this.logger = new ConsoleLogger();
        }

        if(this.enable) {
            return this.logger;
        } else {
            return this.emptyLogger;
        }
    }


    @Override
    public void error(String tag, String msg) {
        if(this.level <= ERROR_LEVEL) {
            getLogger().error(tag, msg);
        }
    }

    @Override
    public void warn(String tag, String msg) {
        if(this.level <= WARN_LEVEL) {
            getLogger().warn(tag, msg);
        }
    }

    @Override
    public void fatal(String tag, String msg) {
        if(this.level <= FATAL_LEVEL) {
            getLogger().fatal(tag, msg);
        }
    }

    @Override
    public void debug(String tag, String msg) {
        if(this.level <= DEBUG_LEVEL) {
            getLogger().debug(tag, msg);
        }
    }

    public static void e(String msg) {
        e(null, msg);
    }

    public static void d(String msg) {
        d(null, msg);
    }

    public static void w(String msg) {
        w(null, msg);
    }

    public static void f(String msg) {
        f(null, msg);
    }

    public static void e(String tag, String msg) {
        instance.error(null != tag ? tag : ERROR, msg);
    }

    public static void w(String tag, String msg) {
        instance.warn(null != tag ? tag : WARN, msg);
    }

    public static void d(String tag, String msg) {
        instance.debug(null != tag ? tag : DEBUG, msg);
    }

    public static void f(String tag, String msg) {

        instance.fatal(null!=tag?tag:FATAL, msg);
    }

    public static void level(String level) {
        instance.setLevel(level);
    }

    public static void enable(boolean enable) {
        instance.setEnable(enable);
    }

    public static void loadLogger(ILogger logger) {
        instance.setLogger(logger);
    }

}
