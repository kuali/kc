/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.iacuc.committee.web.struts.action;

import java.sql.Date;

import org.kuali.kra.common.committee.bo.CommitteeBase;
import org.kuali.kra.common.committee.dao.CommitteeBatchCorrespondenceDao;
import org.kuali.kra.common.committee.document.authorization.CommitteeTaskBase;
import org.kuali.kra.common.committee.print.service.CommitteePrintingServiceBase;
import org.kuali.kra.common.committee.rule.event.CommitteeActionGenerateBatchCorrespondenceEventBase;
import org.kuali.kra.common.committee.service.CommitteeBatchCorrespondenceServiceBase;
import org.kuali.kra.common.committee.web.struts.action.CommitteeActionsActionBase;
import org.kuali.kra.iacuc.committee.bo.IacucCommittee;
import org.kuali.kra.iacuc.committee.dao.IacucCommitteeBatchCorrespondenceDao;
import org.kuali.kra.iacuc.committee.print.service.IacucCommitteePrintingService;
import org.kuali.kra.iacuc.committee.rule.event.IacucCommitteeActionGenerateBatchCorrespondenceEvent;
import org.kuali.kra.iacuc.committee.service.IacucCommitteeBatchCorrespondenceService;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskGroupName;
import org.kuali.rice.krad.document.Document;

public class IacucCommitteeActionsAction extends CommitteeActionsActionBase {

    @Override
    protected CommitteeTaskBase getNewCommitteeTaskInstanceHook(String taskName, CommitteeBase committee) {
        // creating an anonymous class to avoid task hierarchy issues
        return new CommitteeTaskBase<IacucCommittee>(TaskGroupName.IACUC_COMMITTEE, taskName, (IacucCommittee) committee) {};
    }

    @Override
    protected CommitteeActionGenerateBatchCorrespondenceEventBase getNewCommitteeActionGenerateBatchCorrespondenceEventInstanceHook(String errorPathPrefix,
            Document document, String batchCorrespondenceTypeCode, Date startDate, Date endDate, String committeeId) {
        return new IacucCommitteeActionGenerateBatchCorrespondenceEvent(errorPathPrefix, document, batchCorrespondenceTypeCode, startDate, endDate, committeeId);
    }

    @Override
    protected CommitteeBatchCorrespondenceServiceBase getCommitteeBatchCorrespondenceService() {
        return KraServiceLocator.getService(IacucCommitteeBatchCorrespondenceService.class);
    }

    @Override
    protected CommitteePrintingServiceBase getCommitteePrintingService() {
        return KraServiceLocator.getService(IacucCommitteePrintingService.class);
    }

    @Override
    protected CommitteeBatchCorrespondenceDao getCommitteeBatchCorrespondenceDao() {
        return KraServiceLocator.getService(IacucCommitteeBatchCorrespondenceDao.class);
    }

    @Override
    protected String getCommitteeDocumentTypeSimpleNameHook() {
        return "CommonCommitteeDocument";
    }

}
