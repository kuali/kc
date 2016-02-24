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
package org.kuali.kra.award.home;

import org.apache.commons.lang3.StringUtils;


/**
 * This class holds the possible values for the InvoiceInstructionsIndicator on 
 * the ValidBasisMethodPayment.  There are three possibilities:
 * 
 * Optional:  The user can fill out the invoice instructions, but it is not required.
 * Mandatory: The user must fill out the invoice instructions.
 * Blank: The user must not fill it out, and the rendered field for instructions shall be disabled.
 */
public enum InvInstructionsIndicatorConstants {
    
    Optional ("O"),
    Mandatory ("M"),
    Blank ("B");

    String code;
    
    InvInstructionsIndicatorConstants( String code ) {
        this.code = code;
    }
    
    /**
     * Gets the code attribute.  The code is stored in the db. 
     * @return Returns the code.
     */
    public String getCode() {
        return code;
    }

    public static InvInstructionsIndicatorConstants getByCode(  String code ) {
        for( InvInstructionsIndicatorConstants cont : InvInstructionsIndicatorConstants.values() ) {
            if( StringUtils.equals(cont.code, code )) return cont;
        }
        return null;
    }

}
