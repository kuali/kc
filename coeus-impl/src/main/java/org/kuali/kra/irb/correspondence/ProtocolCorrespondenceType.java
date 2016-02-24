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
package org.kuali.kra.irb.correspondence;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondenceTypeBase;

public class ProtocolCorrespondenceType extends ProtocolCorrespondenceTypeBase {


    private static final long serialVersionUID = 581785796901521423L;

    public static final String APPROVAL_LETTER = "1";
    public static final String WITHDRAWAL_NOTICE = "16";
    public static final String GRANT_EXEMPTION_NOTICE = "17";
    public static final String CLOSURE_NOTICE = "26";
    public static final String ABANDON_NOTICE = "28";
    public static final String NOTICE_OF_DEFERRAL = "3";
    public static final String SRR_LETTER = "4";
    public static final String SMR_LETTER = "6";
    public static final String EXPEDITED_APPROVAL_LETTER = "5";
    public static final String SUSPENSION_NOTICE = "7";
    public static final String TERMINATION_NOTICE = "8";
    
    @SuppressWarnings("unused")
    private transient CorrespondenceTypeModuleIdConstants module;

    /**
     * 
     * This method returns the module enum specified by this {@code ProtocolCorrespondenceType}.
     * @return Matching {@code CorrespondenceTypeModuleIdConstants} specified by the moduleId of this
     * correspondence type, or CorrespondenceTypeModuleIdConstants.SYSTEM if no matching co
     */
    public CorrespondenceTypeModuleIdConstants getModule() {
        String moduleId = getModuleId();
        for(CorrespondenceTypeModuleIdConstants module : CorrespondenceTypeModuleIdConstants.values()) {
            if(StringUtils.equals(module.getCode(),moduleId)) {
                return module;
            }
        }
        return CorrespondenceTypeModuleIdConstants.SYSTEM;
    }

    public void setModule(CorrespondenceTypeModuleIdConstants module) {
        this.module = module;
    }
}
