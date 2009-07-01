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
package org.kuali.kra.s2s.validator;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * 
 * This class is used get the error messages from the S2SErrorMessages.properties file for a particular key.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class S2SErrorMessages {


    private static Properties props = null;

    private static final String errorFileName = "/S2SErrorMessages.properties";

    private S2SErrorMessages() {
    }


    /**
     * 
     * This method is used to get the property for a particular key.
     * 
     * @param key {@link String}
     * @return getProperty(key)
     */
    public static String getProperty(String key){
        return getProperty(key, null);
    }


    /**
     * 
     * This method is used to get the property for a particular key based on properties file.
     * 
     * @param key {@link String}
     * @return getProperty(key)
     */
    public static String getProperty(String key, String defaultValue){
        if (props == null) {
            synchronized (S2SErrorMessages.class) {
                if (props == null) {
                    props = loadProperties();
                }
            }
        }
        return props.getProperty(key, defaultValue);
    }


    /**
     * 
     * This method is used to load the properties from properties file
     * @return props{@link Properties}
     */
    private static Properties loadProperties(){
        InputStream stream = null;
        try {
            props = new Properties();
            stream = new S2SErrorMessages().getClass().getResourceAsStream(errorFileName);
            props.load(stream);

        }
        catch (IOException e) {
        }
        finally {
            try {
                stream.close();
            }
            catch (Exception ignored) {

            }
        }
        return props;
    }


}
