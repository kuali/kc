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
