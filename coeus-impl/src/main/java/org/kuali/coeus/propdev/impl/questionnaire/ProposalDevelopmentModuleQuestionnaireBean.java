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
package org.kuali.coeus.propdev.impl.questionnaire;

import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.common.framework.module.CoeusSubModule;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.framework.krms.KrmsRulesContext;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.common.questionnaire.framework.answer.ModuleQuestionnaireBean;
import org.kuali.rice.krad.data.DataObjectService;

public class ProposalDevelopmentModuleQuestionnaireBean extends ModuleQuestionnaireBean {
    
    private DataObjectService dataObjectService;
    private DevelopmentProposal developmentProposal;
    
    public ProposalDevelopmentModuleQuestionnaireBean(DevelopmentProposal developmentProposal) {
        super(CoeusModule.PROPOSAL_DEVELOPMENT_MODULE_CODE, developmentProposal.getProposalNumber(), CoeusSubModule.ZERO_SUBMODULE, "0", 
                developmentProposal.getProposalDocument().getDocumentHeader().hasWorkflowDocument() ? developmentProposal.getProposalDocument().getDocumentHeader().getWorkflowDocument().isApproved() : true);
        this.developmentProposal = developmentProposal;
    }
    
    public ProposalDevelopmentModuleQuestionnaireBean(DevelopmentProposal developmentProposal, boolean finalDoc) {
        this(developmentProposal);
        setFinalDoc(finalDoc);
    }
    
    public ProposalDevelopmentModuleQuestionnaireBean(String moduleItemCode, String moduleItemKey, String moduleSubItemCode, String moduleSubItemKey, boolean finalDoc) {
        super(moduleItemCode, moduleItemKey, moduleSubItemCode, moduleSubItemKey, finalDoc);
    }

    @Override
    public KrmsRulesContext getKrmsRulesContextFromBean() {
        if (developmentProposal != null) {
            return developmentProposal.getKrmsRulesContext();
        } else {
            return loadKrmsRulesContext(getModuleItemKey());

        }
    }
    
    protected KrmsRulesContext loadKrmsRulesContext(String proposalNumber) {
        DevelopmentProposal proposal = getDataObjectService().find(DevelopmentProposal.class, proposalNumber);
        return proposal.getKrmsRulesContext();
    }

    protected DataObjectService getDataObjectService (){
        if (dataObjectService == null)
        dataObjectService = KcServiceLocator.getService(DataObjectService.class);
        return dataObjectService;
    }
    public DevelopmentProposal getDevelopmentProposal() {
        return developmentProposal;
    }

    public void setDevelopmentProposal(DevelopmentProposal developmentProposal) {
        this.developmentProposal = developmentProposal;
    }
    
}
