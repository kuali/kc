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
import org.kuali.kra.institutionalproposal.rules.InstitutionalProposalAddUnrecoveredFandARuleEvent;
import org.kuali.kra.institutionalproposal.rules.InstitutionalProposalUnrecoveredFandARuleImpl;
import org.kuali.kra.institutionalproposal.web.struts.form.InstitutionalProposalForm;

import java.io.Serializable;


public class InstitutionalProposalUnrecoveredFandABean implements Serializable {

    private static final long serialVersionUID = -4162806132355841770L;

    private InstitutionalProposalForm parent;
    
    private InstitutionalProposalUnrecoveredFandA newInstitutionalProposalUnrecoveredFandA;
    
    /**
     * Constructs a InstitutionalProposalUnrecoveredFandABean
     * @param parent
     */
    public InstitutionalProposalUnrecoveredFandABean() {
        super();
        newInstitutionalProposalUnrecoveredFandA = new InstitutionalProposalUnrecoveredFandA(); 
    }
    /**
     * Constructs a InstitutionalProposalUnrecoveredFandABean
     * @param parent
     */
    public InstitutionalProposalUnrecoveredFandABean(InstitutionalProposalForm parent) {
        this.parent = parent;
        newInstitutionalProposalUnrecoveredFandA = new InstitutionalProposalUnrecoveredFandA(); 
    }
    
    /**
     * Initialize subform
     */
    public void init() {
        newInstitutionalProposalUnrecoveredFandA = new InstitutionalProposalUnrecoveredFandA(); 
    }


    /**
     * Gets the newInstitutionalProposalUnrecoveredFandA attribute. 
     * @return Returns the newInstitutionalProposalUnrecoveredFandA.
     */
    public InstitutionalProposalUnrecoveredFandA getNewInstitutionalProposalUnrecoveredFandA() {
        return newInstitutionalProposalUnrecoveredFandA;
    }

    /**
     * Sets the newInstitutionalProposalUnrecoveredFandA attribute value.
     * @param newInstitutionalProposalUnrecoveredFandA The newInstitutionalProposalUnrecoveredFandA to set.
     */
    public void setnewInstitutionalProposalUnrecoveredFandA(InstitutionalProposalUnrecoveredFandA thisInstitutionalProposalUnrecoveredFandA) {
        this.newInstitutionalProposalUnrecoveredFandA = thisInstitutionalProposalUnrecoveredFandA;
    }


    public InstitutionalProposalDocument getInstitutionalProposalDocument() {
        return parent.getInstitutionalProposalDocument();
    }
    

    public Object getData() {
        return getNewInstitutionalProposalUnrecoveredFandA();
    }
    
    /**
     * This method is called when adding a new AwardCostShare
     * @param formHelper
     * @return
     * @throws Exception
     */
    public boolean addUnrecoveredFandA(InstitutionalProposalUnrecoveredFandABean formBean) throws Exception {
        
        InstitutionalProposalAddUnrecoveredFandARuleEvent event = new InstitutionalProposalAddUnrecoveredFandARuleEvent(
                                                            "newInstitutionalProposalUnrecoveredFandA",
                                                            formBean.getInstitutionalProposalDocument(),
                                                            formBean.getNewInstitutionalProposalUnrecoveredFandA());
        boolean success = new InstitutionalProposalUnrecoveredFandARuleImpl().processAddInstitutionalProposalUnrecoveredFandABusinessRules(event);
            if(success){
                formBean.getInstitutionalProposalDocument().getInstitutionalProposal().add(formBean.getNewInstitutionalProposalUnrecoveredFandA());
                formBean.init();
            }
                return success;
    }
}
