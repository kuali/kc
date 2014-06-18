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
package org.kuali.coeus.propdev.impl.s2s.question;

import org.kuali.coeus.common.framework.module.CoeusSubModule;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.questionnaire.ProposalDevelopmentModuleQuestionnaireBean;

public class ProposalDevelopmentS2sModuleQuestionnaireBean extends ProposalDevelopmentModuleQuestionnaireBean {
    
    public ProposalDevelopmentS2sModuleQuestionnaireBean(DevelopmentProposal developmentProposal) {
        super(developmentProposal);
        this.setModuleSubItemCode(CoeusSubModule.PROPOSAL_S2S_SUBMODULE);
        this.setModuleSubItemKey("0");      
    }
    
    public ProposalDevelopmentS2sModuleQuestionnaireBean(String moduleItemCode, String moduleItemKey, String moduleSubItemCode, String moduleSubItemKey, boolean finalDoc) {
        super(moduleItemCode, moduleItemKey, moduleSubItemCode, moduleSubItemKey, finalDoc);
    }
}
