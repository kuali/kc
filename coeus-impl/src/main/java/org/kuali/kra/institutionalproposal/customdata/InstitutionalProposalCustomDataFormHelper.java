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
package org.kuali.kra.institutionalproposal.customdata;

import org.kuali.coeus.common.framework.custom.CustomDataHelperBase;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocument;
import org.kuali.kra.institutionalproposal.web.struts.form.InstitutionalProposalForm;
import org.kuali.rice.kew.api.WorkflowDocument;

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
