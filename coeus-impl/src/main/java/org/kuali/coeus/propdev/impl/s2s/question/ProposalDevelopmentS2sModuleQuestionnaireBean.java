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
