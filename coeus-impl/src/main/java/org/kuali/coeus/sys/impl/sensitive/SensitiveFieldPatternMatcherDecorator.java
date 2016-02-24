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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * This class is decorated to accommodate Pattern Matching
 */
@Component("sensitiveFieldPatternMatcher")
public class SensitiveFieldPatternMatcherDecorator implements SensitiveFieldMatcher {
    
    public static final String REPEAT_NTIMES = "[\\w\\.]*"; //\w = a-z or A-Z or _ or 0-9
    
    public static final String STAR_CONSTANT = "*";
    
    private List<Pattern> listOfPatterns = new ArrayList<Pattern>();

    private final SensitiveFieldMatcher matcher;

    @Autowired
    public SensitiveFieldPatternMatcherDecorator(@Qualifier("sensitiveFieldSimpleMatcher") SensitiveFieldMatcher matcher,
                                                 @Qualifier("sensitiveFieldResourceLoader") SensitiveFields sensitiveFields) {
        this.matcher = matcher;
        compileToPatterns(sensitiveFields.getSensitiveFields());
    }

    /**
     * Private helper method pre-compiles resource fields into patterns.
     * @param listOfStrings
     */
    private void compileToPatterns(List<String> listOfStrings) {
        for(String regex: listOfStrings) {
            if(!regex.contains(STAR_CONSTANT)) 
                continue;
            listOfPatterns.add(Pattern.compile(REPEAT_NTIMES+regex.substring(0, regex.length() - 1)+REPEAT_NTIMES, Pattern.CASE_INSENSITIVE));
        }
    }
    
    @Override
    public boolean match(String text) {
        //SimpleMatcher
        if(matcher.match(text)) return true;
        //PatternMathcer is used only if SimpleMatcher returns with no match. 
        for(Pattern pattern : listOfPatterns) {
            if(pattern.matcher(text).matches()) {
                return true;
            }
        }
        return false;
    }
    
}
