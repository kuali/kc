/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.irb.questionnaire;

import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.bo.CoeusSubModule;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.questionnaire.answer.ModuleQuestionnaireBean;

public class ProtocolModuleQuestionnaireBean extends ModuleQuestionnaireBean {

    public ProtocolModuleQuestionnaireBean(Protocol protocol) {
        super(CoeusModule.IRB_MODULE_CODE, protocol.getProtocolNumber(), "0", protocol.getSequenceNumber().toString(), 
                                protocol.getProtocolDocument().getDocumentHeader().getWorkflowDocument().stateIsApproved());
        setProtocolSubItemCode(protocol) ;
    }
    
    public ProtocolModuleQuestionnaireBean(String moduleItemCode, String moduleItemKey, String moduleSubItemCode, String moduleSubItemKey, boolean finalDoc) {
        super(moduleItemCode, moduleItemKey, moduleSubItemCode, moduleSubItemKey, finalDoc);
    }
   
    private void setProtocolSubItemCode(Protocol protocol) {
        // For now check renewal/amendment.  will add 'Protocol Submission' when it is cleared
        String subModuleCode = CoeusSubModule.ZERO_SUBMODULE;
        
        if (protocol.isRenewal()) {
            subModuleCode = CoeusSubModule.AMENDMENT_RENEWAL;
            if (protocol.isRenewalWithoutAmendment()) {
                subModuleCode = CoeusSubModule.RENEWAL;
            }
        }
        if (protocol.isAmendment()) {
            subModuleCode = CoeusSubModule.AMENDMENT;
        }
        
        setModuleSubItemCode(subModuleCode);
    }
    
    public boolean equals(Object o) {
        boolean retVal = false;
        if( (o != null) && (o instanceof ProtocolModuleQuestionnaireBean) ) {
            ProtocolModuleQuestionnaireBean pmqb = (ProtocolModuleQuestionnaireBean)o;
            retVal = (  (this.getModuleItemCode().equals(pmqb.getModuleItemCode()))
                     && (this.getModuleItemKey().equals(pmqb.getModuleItemKey()))
                     && (this.getModuleSubItemCode().equals(pmqb.getModuleSubItemCode()))  
                     && (this.getModuleSubItemKey().equals(pmqb.getModuleSubItemKey()))
                     && (this.isFinalDoc() == pmqb.isFinalDoc()) );
        }
        return retVal;
    }
    
    // TODO: need to make this method deliver a better hashing 
    public int hashCode() {
        return 0;
    }

}
