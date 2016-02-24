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
package org.kuali.coeus.sys.framework.scheduling.util;

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
