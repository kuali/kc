/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.s2s.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 
 * This class reads a property file and return value for a key
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 * 
 */
public class PropertyFileReader {
    private static final Logger LOG = Logger.getLogger(PropertyFileReader.class);
    private static Properties props = null;

    /**
     * 
     * This method is used to get the property for a particular key.
     * 
     * @param key {@link String}
     * @return getProperty(key)
     * @throws IOException
     */
    public static String getProperty(String fileName, String key) throws IOException {
        return getProperty(fileName, key, null);
    }


    /**
     * 
     * This method is used to get the property for a particular key based on properties file.
     * 
     * @param key {@link String}
     * @return getProperty(key)
     * @throws IOException
     */
    public static String getProperty(String fileName, String key, String defaultValue) throws IOException {
        if (props == null) {
            synchronized (PropertyFileReader.class) {
                if (props == null) {
                    props = loadProperties(fileName);
                }
            }
        }
        if (props == null) {
            // props is still not available. Return the default value
            return defaultValue;
        }
        else {
            // return the property value from the props collection
            return props.getProperty(key, defaultValue);
        }
    }


    /**
     * 
     * This method is used to load the properties from properties file
     * 
     * @return props{@link Properties}
     * @throws IOException
     */
    private static Properties loadProperties(String fileName) throws IOException {
        InputStream stream = null;
        try {
            props = new Properties();
            stream = new PropertyFileReader().getClass().getResourceAsStream(fileName);
            props.load(stream);
        }
        finally {
            try {
                stream.close();
            }
            catch (Exception ignored) {
                LOG.error(ignored.getMessage(), ignored);
            }
        }
        return props;
    }

}
