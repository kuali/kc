/*
 * Copyright 2006-2008 The Kuali Foundation
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
public class InstitutionalProposalUnrecoveredFandABean implements Serializable {

/**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -4162806132355841770L;

    private InstitutionalProposalForm parent;
    
    private InstitutionalProposalUnrecoveredFandA newInstitutionalProposalUnrecoveredFandA;
    
    /**
     * Constructs a InstitutionalProposalUnrecoveredFandABean
     * @param parent
     */
    public InstitutionalProposalUnrecoveredFandABean() {
        super();
    }
    /**
     * Constructs a InstitutionalProposalUnrecoveredFandABean
     * @param parent
     */
    public InstitutionalProposalUnrecoveredFandABean(InstitutionalProposalForm parent) {
        this.parent = parent;
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
        return getNewInstitutionalProposalUnrecoveredFandA();
    }
}
