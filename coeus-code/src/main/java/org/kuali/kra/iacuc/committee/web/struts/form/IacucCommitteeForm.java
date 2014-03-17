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
package org.kuali.kra.iacuc.committee.web.struts.form;

import org.kuali.coeus.common.committee.impl.document.CommitteeDocumentBase;
import org.kuali.coeus.common.committee.impl.web.struts.form.CommitteeFormBase;
import org.kuali.coeus.common.committee.impl.web.struts.form.CommitteeHelperBase;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kns.web.ui.HeaderField;
import org.springframework.util.CollectionUtils;

public class IacucCommitteeForm extends CommitteeFormBase {


    private static final long serialVersionUID = 5623611578157741521L;

    @Override
    protected CommitteeHelperBase getNewCommitteeHelperInstanceHook(CommitteeFormBase committeeForm) {
        return new IacucCommitteeHelper((IacucCommitteeForm)committeeForm);
    }

    @Override
    protected String getDefaultDocumentTypeName() {
        return "CommonCommitteeDocument";
    }
    
    @Override
    public void populateHeaderFields(WorkflowDocument workflowDocument) {
        super.populateHeaderFields(workflowDocument);
        CommitteeDocumentBase committeeDoc = getCommitteeDocument();

        getDocInfo().set(4, new HeaderField("DataDictionary.KraAttributeReferenceDummy.attributes.committeeId", (committeeDoc == null || CollectionUtils.isEmpty(committeeDoc.getCommitteeList())) ? null : committeeDoc.getCommittee().getCommitteeId()));
        getDocInfo().set(5, new HeaderField("DataDictionary.KraAttributeReferenceDummy.attributes.committeeName", getCommitteeNameForHeaderDisplay(committeeDoc)));
    }



}
