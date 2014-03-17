/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.sys.impl.sensitive;

import org.kuali.coeus.sys.framework.sensitive.SensitiveFieldMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * This class is simple implementation of SensitiveFieldMatcher, where sorted
 * TreeSet is populated with sensitive fields (ignoring wild card entries) and
 * matched against searchString. 
 */
@Component("sensitiveFieldSimpleMatcher")
public class SensitiveFieldSimpleMatcher implements SensitiveFieldMatcher {
    
    public static final String STAR_CONSTANT = "*";
    
    //Provides log(n) time for search and add operation
    private Set<String> fields = new TreeSet<String>(new Comparator<String>(){
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    });

    @Autowired
    public SensitiveFieldSimpleMatcher(@Qualifier("sensitiveFieldResourceLoader") SensitiveFields sensitiveFields) {
        addToSortedSet(sensitiveFields.getSensitiveFields());
    }

    /**
     * Private helper method to populate sensitive fields from resource.
     * @param listOfStrings
     */
    private void addToSortedSet(List<String> listOfStrings) {
        for(String str: listOfStrings) {
            if(str.contains(STAR_CONSTANT))
                continue;
            fields.add(str);
        }
    }
    
    @Override
    public boolean match(String searchString) {        
        return fields.contains(searchString);
    }
    
}
