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
package org.kuali.kra.iacuc.correspondence;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.protocol.correspondence.CorrespondenceTypeModuleIdConstants;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondenceTypeBase;

public class IacucProtocolCorrespondenceType extends ProtocolCorrespondenceTypeBase{


    private static final long serialVersionUID = 2156930269919089226L;

    public static final String APPROVAL_LETTER                     = "1";       
    public static final String ADVERSE_EVENT_NOTIFICATION_LETTER   = "2";       
    public static final String AGENDA                              = "3";       
    public static final String MINUTES                             = "4";       
    public static final String ADVERSE_EVENT_REPORTING_LETTER      = "5";       
    public static final String AMENDMENT_APPROVAL_LETTER           = "6";       
    public static final String APPROVAL_LETTER_2                   = "7";       
    public static final String APPROVAL_WITHHELD_PENDING_LETTER    = "8";       
    public static final String CONTINUATION_APPROVAL_LETTER        = "9";       
    public static final String CORRESPONDENT_ACTION_NOTIFICATION   = "10";
    public static final String DEACTIVATED_LETTER                  = "11";
    public static final String DISAPPROVAL_LETTER                  = "12";
    public static final String EXPIRATION_NOTIFICATION_LETTER      = "13";
    public static final String EXPIRED_LETTER                      = "14";
    public static final String FAILED_ADMIN_PREREVIEW_LETTER       = "15";
    public static final String HOLD_LETTER                         = "16";
    public static final String LIFT_HOLD_LETTER                    = "17";
    public static final String ADMINISTRATIVE_CORRECTION_LETTER    = "18";
    public static final String NOTICE_OF_DEACTIVATION_LETTER       = "19";
    public static final String NOTIFICATION_OF_IACUC_REVIEW_LETTER = "20";
    public static final String NOTIFIED_COMMITTEE_LETTER           = "21";
    public static final String NOTIFY_IACUC_LETTER                 = "22";
    public static final String OVER_USAGE_OF_ANIMALS_LETTER        = "23";
    public static final String RENEWAL_REMINDER_NOTIFICATION       = "24";
    public static final String REQUEST_TO_DEACTIVATE_LETTER        = "25";
    public static final String SUSPENSION_LETTER                   = "26";
    public static final String TABLED_DEFERRAL_NOTIFICATION_LETTER = "27";
    public static final String TERMINATION_LETTER                  = "28";
    public static final String WITHDRAWL_CONFIRMATION_NOTIFICATION = "29";

    @SuppressWarnings("unused")
    private transient CorrespondenceTypeModuleIdConstants module;

    /**
     * 
     * This method returns the module enum specified by this {@code ProtocolCorrespondenceType}.
     * @return Matching {@code CorrespondenceTypeModuleIdConstants} specified by the moduleId of this
     * correspondence type, or CorrespondenceTypeModuleIdConstants.SYSTEM if no matching code is found.
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
