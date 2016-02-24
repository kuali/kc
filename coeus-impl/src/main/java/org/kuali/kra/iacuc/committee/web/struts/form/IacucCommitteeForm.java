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
