/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.cargopartner;

import com.hazelcast.logging.ILogger;
import com.hazelcast.logging.LogEvent;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class JsonLoggingHazelcastLogger implements ILogger {
    private final String loggerName;
    private final Level configuredLevel;
    private final JSONLogFormatter formatter;

    public JsonLoggingHazelcastLogger(String loggerName, Level level) {
        this.loggerName = loggerName;
        this.configuredLevel = level;
        this.formatter = new JSONLogFormatter();
    }

    @Override
    public void finest(String message) {
        log(Level.FINEST, message, null);
    }

    @Override
    public void finest(Throwable thrown) {
        log(Level.FINEST, null, thrown);
    }

    @Override
    public void finest(String message, Throwable thrown) {
        log(Level.FINEST, message, thrown);
    }

    @Override
    public boolean isFinestEnabled() {
        return isLoggable(Level.FINEST);
    }

    @Override
    public void fine(String message) {
        log(Level.FINE, message, null);
    }

    @Override
    public boolean isFineEnabled() {
        return isLoggable(Level.FINE);
    }

    @Override
    public void info(String message) {
        log(Level.INFO, message, null);
    }

    @Override
    public void warning(String message) {
        log(Level.WARNING, message, null);
    }

    @Override
    public void warning(Throwable thrown) {
        log(Level.WARNING, null, thrown);
    }

    @Override
    public void warning(String message, Throwable thrown) {
        log(Level.WARNING, message, thrown);
    }

    @Override
    public void severe(String message) {
        log(Level.SEVERE, message, null);
    }

    @Override
    public void severe(Throwable thrown) {
        log(Level.SEVERE, null, thrown);
    }

    @Override
    public void severe(String message, Throwable thrown) {
        log(Level.SEVERE, message, thrown);
    }

    @Override
    public void log(Level level, String message) {
        log(level, message, null);
    }

    @Override
    public void log(Level level, String message, Throwable thrown) {
        LogRecord logRecord = new LogRecord(level, message);
        logRecord.setLoggerName(loggerName);
        logRecord.setThrown(thrown);
        logRecord.setSourceClassName(loggerName);
        logRecord(logRecord);
    }

    @Override
    public void log(LogEvent le) {
        logRecord(le.getLogRecord());
    }

    @Override
    public Level getLevel() {
        return configuredLevel;
    }

    @Override
    public boolean isLoggable(Level level) {
        return configuredLevel.intValue() <= level.intValue();
    }

    private void logRecord(LogRecord logRecord) {
        System.out.println(formatter.format(logRecord));
    }
}
