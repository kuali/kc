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
