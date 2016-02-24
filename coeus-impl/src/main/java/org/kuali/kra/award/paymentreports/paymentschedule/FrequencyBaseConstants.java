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
package org.kuali.kra.award.paymentreports.paymentschedule;


public enum FrequencyBaseConstants {
    AWARD_EXECUTION_DATE("1"),AWARD_EFFECTIVE_DATE("2"),AWARD_EXPIRATION_DATE_OF_OBLIGATION("3"),FINAL_EXPIRATION_DATE("4")
        ,AWARD_EFFECTIVE_DATE_OF_OBLIGATION("5");
    
    String frequencyBase;
    
    FrequencyBaseConstants(String frequencyBase){
        this.frequencyBase = frequencyBase;
    }
    
    public String getfrequencyBase(){
        return frequencyBase;
    }
    

}
