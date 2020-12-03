/**
 * @author eddeveloper <ed.developer90@gmail.com>
 * Date :  2020-12-03
 * Time : 8:30 PM
 */
package com.coders.cc.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class Loggerg extends Formatter {

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    public void log(Level level, String msg) {
        String formatted = format(new LogRecord(level, msg));
        System.out.println(formatted);
    }

    @Override
    public String format(LogRecord record) {
        StringBuilder sb = new StringBuilder();

        sb.append(new Date(record.getMillis()))
                .append(" ")
                .append(record.getLevel().getLocalizedName())
                .append(": ")
                .append(formatMessage(record));

        if (record.getThrown() != null) {
            try {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                record.getThrown().printStackTrace(pw);
                pw.close();
                sb.append(sw.toString());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return sb.toString();
    }
}