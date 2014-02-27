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
package org.kuali.coeus.common.committee.impl.web.struts.form.schedule;

public enum Months {
    
    JANUARY("JAN"), FEBRUARY("FEB"), MARCH("MAR"), APRIL("APR"), MAY("MAY"), JUNE("JUN"), JULY("JUL"), AUGUST("AUG"), SEPTEMBER("SEP"), OCTOBER("OCT"), NOVEMBER("NOV"), DECEMBER("DEC");
    
    private String abbr;

    Months(String abbr) {
        this.abbr = abbr;
    }
    
    public String getAbbr() {
        return abbr;
    }
        
}
