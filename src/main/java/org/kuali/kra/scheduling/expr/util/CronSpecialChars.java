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
package org.kuali.kra.scheduling.expr.util;

/**
 * This class defines cron special character construct.
 */
public enum CronSpecialChars {
    
    SPACE(" "),STAR("*"),QUESTION("?"), COMMA(","), HASH("#"), HYPHEN("-"), LAST("L"),SLASH("/"),COMMASEPRATOR(","),
                        SUN("SUN"),MON("MON"),TUE("TUE"),WED("WED"),THU("THU"),FRI("FRI"),SAT("SAT"),
                        FIRST("1"),SECOND("2"),THIRD("3"),FOURTH("4"),FIFTH("5"),
                        JAN("JAN"),FEB("FEB"),MAR("MAR"),APR("APR"),MAY("MAY"),JUN("JUN"),
                        JUL("JUL"),AUG("AUG"),SEP("SEP"),OCT("OCT"),NOV("NOV"),DEC("DEC"); 
    
    private String chr;
    
    /**
     * Constructs a CronSpecialChars.java.
     * @param chr value is used to create cron expression. 
     */
    CronSpecialChars(String chr) {
        this.chr = chr;
    }
   
    public String toString() {
        return chr;
    }

}
