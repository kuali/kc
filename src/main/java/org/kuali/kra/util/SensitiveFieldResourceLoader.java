/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.springframework.core.io.Resource;

/**
 * This class is used to load sensitive fields resource file and store each
 * field in String ArrayList. Also, uses thread safe singleton pattern. 
 */
public class SensitiveFieldResourceLoader {
 
    //SINGLETON, thread safe
    private static class SensitiveFieldResourceLoaderHolder {
        private final static SensitiveFieldResourceLoader INSTANCE = new SensitiveFieldResourceLoader();
    }

    public static SensitiveFieldResourceLoader getInstance() {
        return SensitiveFieldResourceLoaderHolder.INSTANCE;
    }
    
    private List<String> listOfFields = new ArrayList<String>();

    protected SensitiveFieldResourceLoader() {
        Resource fileResource = (Resource) KraServiceLocator.getAppContext().getResource("classpath:sensitive-fields.txt");
        buildList(fileResource);
    }

    private void buildList(Resource resource) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            String line = reader.readLine();
            while (line != null) {
                listOfFields.add(line);
                line = reader.readLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method provides list of sensitive fields. 
     * @return
     */
    public List<String> getSensitiveFields() {
        return listOfFields;
    }
}
