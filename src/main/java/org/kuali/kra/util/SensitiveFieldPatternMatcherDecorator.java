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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * This class is decorated to accommodate Pattern Matching
 */
public class SensitiveFieldPatternMatcherDecorator extends SensitiveFieldMatcherDecorator {
    
    public static final String REPEAT_NTIMES = "[\\w\\.]*"; //\w = a-z or A-Z or _ or 0-9
    
    public static final String STAR_CONSTANT = "*";
    
    private List<Pattern> listOfPatterns = new ArrayList<Pattern>();
    
    public SensitiveFieldPatternMatcherDecorator(SensitiveFieldMatcher matcher) {
        super(matcher);
        compileToPatterns(SensitiveFieldResourceLoader.getInstance().getSensitiveFields());
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
    
    /**
     * @see org.kuali.kra.util.SensitiveFieldMatcher#match(java.lang.String)
     */
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
