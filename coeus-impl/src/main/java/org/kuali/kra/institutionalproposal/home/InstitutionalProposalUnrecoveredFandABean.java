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
