/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 The Kuali Foundation
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
package org.kuali.kra.iacuc.correspondence;

/**
 * 
 * This class used in the CorrespondentTypeQualifierValuesFinder.
 */
public enum IacucCorrespondentTypeQualifierConstants {
    PROTOCOL("P", "Protocol"),
    UNIT("U", "Unit"),
    ORGANIZATION("O", "Organization");
    
    private final String code;   
    private final String description; 
    IacucCorrespondentTypeQualifierConstants(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String code()   
    { 
        return code; 
    }

    public String description() 
    { 
        return description; 
    }
    
}
