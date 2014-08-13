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
package org.kuali.kra.institutionalproposal.home;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.framework.costshare.CostShareFunctions;
import org.kuali.coeus.common.framework.costshare.CostShareService;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.rules.InstitutionalProposalAddCostShareRuleEvent;
import org.kuali.kra.institutionalproposal.rules.InstitutionalProposalAddCostShareRuleImpl;
import org.kuali.kra.institutionalproposal.web.struts.form.InstitutionalProposalForm;

import java.io.Serializable;


public class InstitutionalProposalCostShareBean implements Serializable, CostShareFunctions {

    private static final long serialVersionUID = -7837407094828925591L;

    private InstitutionalProposalForm parent;
    
    private InstitutionalProposalCostShare newInstitutionalProposalCostShare;
    
    /**
     * Constructs a InstitutionalProposalCostshareBean
     * @param parent
     */
    public InstitutionalProposalCostShareBean() {
        super();
    }
    /**
     * Constructs a InstitutionalProposalCostShareBean
     * @param parent
     */
    public InstitutionalProposalCostShareBean(InstitutionalProposalForm parent) {
        this.parent = parent;
        init(); 
    }
    
    /**
     * Initialize subform
     */
    public void init() {
        newInstitutionalProposalCostShare = new InstitutionalProposalCostShare(); 
    }


    /**
     * Gets the newInstitutionalProposalCostShare attribute. 
     * @return Returns the newInstitutionalProposalCostShare.
     */
    public InstitutionalProposalCostShare getNewInstitutionalProposalCostShare() {
        return newInstitutionalProposalCostShare;
    }

    /**
     * Sets the newInstitutionalProposalCostShare attribute value.
     * @param newInstitutionalProposalCostShare The newInstitutionalProposalCostShare to set.
     */
    public void setNewInstitutionalProposalCostShare(InstitutionalProposalCostShare newInstitutionalProposalCostShare) {
        this.newInstitutionalProposalCostShare = newInstitutionalProposalCostShare;
    }


    public InstitutionalProposalDocument getInstitutionalProposalDocument() {
        return parent.getInstitutionalProposalDocument();
    }
    

    public Object getData() {
        return getNewInstitutionalProposalCostShare();
    }
    
    /**
     * This method is called when adding a new AwardCostShare
     * @param formHelper
     * @return
     * @throws Exception
     */
    public boolean addCostShare(InstitutionalProposalCostShareBean formBean) throws Exception {
        
        InstitutionalProposalAddCostShareRuleEvent event = new InstitutionalProposalAddCostShareRuleEvent(
                                                            "newInstitutionalProposalCostShare",
                                                            formBean.getInstitutionalProposalDocument(),
                                                            formBean.getNewInstitutionalProposalCostShare());
        boolean success = new InstitutionalProposalAddCostShareRuleImpl().processAddInstitutionalProposalCostShareBusinessRules(event);
            if(success){
                formBean.getInstitutionalProposalDocument().getInstitutionalProposal().add(formBean.getNewInstitutionalProposalCostShare());
                formBean.init();
            }
            return success;
    }
    
    @Override
    public String getProjectPeriodLabel() {
        String label = KcServiceLocator.getService(CostShareService.class).getCostShareLabel();
        return label;
    }
}
