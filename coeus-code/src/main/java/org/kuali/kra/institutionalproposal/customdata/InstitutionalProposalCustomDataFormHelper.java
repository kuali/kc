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
package org.kuali.kra.institutionalproposal.customdata;

import org.kuali.coeus.common.framework.custom.CustomDataHelperBase;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocument;
import org.kuali.kra.institutionalproposal.web.struts.form.InstitutionalProposalForm;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.krad.document.Document;

import java.util.List;
import java.util.Map;

public class InstitutionalProposalCustomDataFormHelper extends CustomDataHelperBase<InstitutionalProposalCustomData> {


    private static final long serialVersionUID = -716264183914346452L;
    
    private InstitutionalProposalForm institutionalProposalForm;
    
    /**
     * Constructs a CustomDataHelper.
     * @param from the awardForm
     */
    public InstitutionalProposalCustomDataFormHelper(InstitutionalProposalForm institutionalProposalForm) {
        this.institutionalProposalForm = institutionalProposalForm;
    }
    

    @Override
    public boolean canModifyCustomData() {

        return false;
    }


    @Override
    protected InstitutionalProposalCustomData getNewCustomData() {
        return new InstitutionalProposalCustomData();
    }

    @Override
    public List<InstitutionalProposalCustomData> getCustomDataList() {
        return institutionalProposalForm.getInstitutionalProposalDocument().getInstitutionalProposal().getInstitutionalProposalCustomDataList();
    }


    @Override
    public Map<String, CustomAttributeDocument> getCustomAttributeDocuments() {
        return institutionalProposalForm.getInstitutionalProposalDocument().getCustomAttributeDocuments();
    }

    @Override
    public boolean documentNotRouted() {
        WorkflowDocument doc = institutionalProposalForm.getInstitutionalProposalDocument().getDocumentHeader().getWorkflowDocument();
        return doc.isSaved() || doc.isInitiated();
    }

}
