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
package org.kuali.kra.institutionalproposal.home;

import java.io.Serializable;

import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.web.struts.form.InstitutionalProposalForm;

/**
 * This class...
 */
public class InstitutionalProposalNotepadBean implements Serializable {

 /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -2409602626444019766L;

    private InstitutionalProposalForm parent;
    
    private InstitutionalProposalNotepad newInstitutionalProposalNotepad;
    
    /**
     * Constructs a CostShareFormHelper
     * @param parent
     */
    public InstitutionalProposalNotepadBean() {
        super();
        init();
    }
    /**
     * Constructs a CostShareFormHelper
     * @param parent
     */
    public InstitutionalProposalNotepadBean(InstitutionalProposalForm parent) {
        this();
        this.parent = parent;
    }
    
    /**
     * Initialize subform
     */
    public void init() {
        newInstitutionalProposalNotepad = new InstitutionalProposalNotepad(); 
    }


    /**
     * Gets the newInstitutionalProposalNotepad attribute. 
     * @return Returns the newInstitutionalProposalNotepad.
     */
    public InstitutionalProposalNotepad getNewInstitutionalProposalNotepad() {
        return newInstitutionalProposalNotepad;
    }

    /**
     * Sets the newInstitutionalProposalNotepad attribute value.
     * @param newInstitutionalProposalNotepad The newInstitutionalProposalNotepad to set.
     */
    public void setNewInstitutionalProposalNotepad(InstitutionalProposalNotepad newInstitutionalProposalNotepad) {
        this.newInstitutionalProposalNotepad = newInstitutionalProposalNotepad;
    }

    /**
     * This method...
     * @return
     */
    public InstitutionalProposalDocument getInstitutionalProposalDocument() {
        return parent.getInstitutionalProposalDocument();
    }
    
    /**
     * This method...
     * @return
     */
    public Object getData() {
        return getNewInstitutionalProposalNotepad();
    }
    
    /**
     * This method is called when adding a new AwardCostShare
     * @param formHelper
     * @return
     * @throws Exception
     */
    public boolean addNote(InstitutionalProposalNotepadBean proposalNotepadBean) throws Exception {
        
        //AwardCostShareRuleEvent event = new AwardCostShareRuleEvent(
                                                            //"newAwardCostShare",
                                                           // formHelper.getAwardDocument(),
                                                           // formHelper.getNewAwardCostShare());
        //boolean success = new AwardCostShareRuleImpl().processAddCostShareBusinessRules(event);
            //if(success){
        proposalNotepadBean.getInstitutionalProposalDocument().getInstitutionalProposal().add(proposalNotepadBean.getNewInstitutionalProposalNotepad());
        proposalNotepadBean.init();
            //}
            //return success;
        return true;
    }
}
