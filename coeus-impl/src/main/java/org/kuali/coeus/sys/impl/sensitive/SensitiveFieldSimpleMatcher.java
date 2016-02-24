/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
