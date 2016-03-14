package com.ichoice.egan.eganview.Utils.logger;

/**
 * Created by Hornen on 15/9/29.
 */
public interface ILogger {

    void error(String tag, String msg);

    void warn(String tag, String msg);

    void fatal(String tag, String msg);

    void debug(String tag, String msg);
}
