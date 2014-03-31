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
package org.kuali.kra.iacuc.committee.document;

import org.kuali.coeus.common.committee.impl.document.CommitteeDocumentBase;
import org.kuali.coeus.common.framework.custom.DocumentCustomData;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.committee.bo.IacucCommittee;
import org.kuali.kra.iacuc.committee.bo.IacucCommitteeSchedule;
import org.kuali.kra.iacuc.committee.service.IacucCommitteeService;

import java.util.ArrayList;
import java.util.List;

public class CommonCommitteeDocument extends CommitteeDocumentBase<CommonCommitteeDocument, IacucCommittee, IacucCommitteeSchedule> {


    private static final long serialVersionUID = 7253898081493879835L;

    @Override
    protected CommonCommitteeDocument getThisHook() {
        return this;
    }

    @Override
    protected IacucCommittee getNewCommitteeInstanceHook() {
        return new IacucCommittee();
    }

    @Override
    protected IacucCommitteeService getCommitteeService() {
        return KcServiceLocator.getService(IacucCommitteeService.class);
    }

    @Override
    public List<? extends DocumentCustomData> getDocumentCustomData() {
        return new ArrayList();
    }

}
