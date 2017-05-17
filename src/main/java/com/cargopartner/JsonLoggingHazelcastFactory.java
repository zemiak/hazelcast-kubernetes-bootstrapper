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
import com.hazelcast.logging.LoggerFactory;
import java.util.logging.Level;

public class JsonLoggingHazelcastFactory implements LoggerFactory {
    @Override
    public ILogger getLogger(String loggerName) {
        return getLoggerStatic(loggerName);
    }

    public static ILogger getLoggerStatic(Class clazz) {
        return getLoggerStatic(clazz.getCanonicalName());
    }

    public static ILogger getLoggerStatic(String loggerName) {
        String levelName = System.getenv("JSON_LOGGING_HAZELCAST_LEVEL");
        if (null == levelName) {
            levelName = "INFO";
        }

        return new JsonLoggingHazelcastLogger(loggerName, Level.parse(levelName.toUpperCase()));
    }
}
