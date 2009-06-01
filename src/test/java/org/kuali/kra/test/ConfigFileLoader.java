/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;

/**
 * This class reads parms from a Kuali config file, loading them into a key/value map.
 * Note: Values containing macros (i.e. ${someValue} ) will be as fully expanded as possible 
 * through recursive processing of the value. If a macro definition is not in this file, the 
 * original macro will be left in place 
 */
public class ConfigFileLoader {
    private static final String VALUE_PROPERTY_NAME = "value";
    private static final String ADD_PARM_METHOD_NAME = "addParm";
    private static final String PARAM_ELEMENT = "config/param";
    private static final String EXPRESSION_END = "}";
    private static final String EXPRESSION_START = "${";
    private String configFilePath;
    private Map<String, String> parmMap;
    
    public ConfigFileLoader(String configFilePath) {
        this.configFilePath = configFilePath;
        parmMap = new TreeMap<String, String>();
    }
    
    public void addParm(ConfigParm parm) {
        parmMap.put(parm.getName(), parm.getValue());
    }
    
    /**
     * Read the parms using the Digester and add them to a key/value map
     * 
     * Note: the only parm attribute read is the name field
     * 
     * @param configPath
     * @throws FileNotFoundException
     * @throws IOException
     */
    public Map<String, String> loadConfigFileParms() throws FileNotFoundException, IOException, SAXException {
        File file = new File(configFilePath);
        
        Digester digester = new Digester();
        digester.push(this);
        digester.addObjectCreate(PARAM_ELEMENT, ConfigParm.class.getName());
        digester.addSetNext( PARAM_ELEMENT, ADD_PARM_METHOD_NAME );
        digester.addSetProperties(PARAM_ELEMENT);
        digester.addBeanPropertySetter(PARAM_ELEMENT, VALUE_PROPERTY_NAME);
                    
        try {
            digester.parse(file);
        } catch(SAXException e) {
            e.printStackTrace();
            throw e;
        }
        
        for(String parmName: parmMap.keySet()) {
            String value = evaluateExpression(parmMap.get(parmName));
            parmMap.put(parmName, value);
        }
        
        return parmMap;
    }
    
    private boolean containsExpression(String value) {
        return value.trim().contains(EXPRESSION_START);
    }
    
    /*
     * Recursivley expand the expression, replace the parm map value with the expanded value
     * If the expression cannot be expanded, it's left as is; For example, ${environment} is not
     * defined in the config file, so occurrences of ${environment} are left as is
     */
    private String evaluateExpression(String value) {
        if(containsExpression(value)) {
            String expression = extractExpression(value);
            String elParmName = extractExpressionValue(expression);
            String elParmValue = parmMap.get(elParmName);
            if(elParmValue != null) {
                value = value.replace(expression, elParmValue);
                if(containsExpression(value)) {
                    evaluateExpression(value);
                }
            }
        }
        
        return value;
    }
    
    private String extractExpression(String value) {
        return value.substring(value.indexOf(EXPRESSION_START), value.indexOf(EXPRESSION_END) + 1).trim();
    }
    
    private String extractExpressionValue(String value) {
        return value.substring(value.indexOf(EXPRESSION_START) + 2, value.indexOf(EXPRESSION_END)).trim();
    }
    
    /**
     * This class holds Digester read values
     */
    public static class ConfigParm {
        private String name;
        private String value;        
        public String getName() { return name; }
        public String getValue() { return value; }
        public void setName(String name) { this.name = name; }
        public void setValue(String value) { this.value = value; }
        
        public String toString() {
            return String.format("%s=%s", name, value);  
        }
    }
}