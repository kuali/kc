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

import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.web.struts.form.InstitutionalProposalForm;

import java.io.Serializable;


public class InstitutionalProposalNotepadBean implements Serializable {

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


    public InstitutionalProposalDocument getInstitutionalProposalDocument() {
        return parent.getInstitutionalProposalDocument();
    }
    

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
