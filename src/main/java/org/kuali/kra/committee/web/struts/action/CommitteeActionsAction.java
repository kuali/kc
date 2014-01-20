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
package org.kuali.kra.committee.web.struts.action;

import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.dao.CommitteeBatchCorrespondenceDao;
import org.kuali.kra.committee.rule.event.CommitteeActionGenerateBatchCorrespondenceEvent;
import org.kuali.kra.committee.service.CommitteeBatchCorrespondenceService;
import org.kuali.kra.committee.service.CommitteeCorrespondencePrint;
import org.kuali.kra.committee.service.CommitteePrintingService;
import org.kuali.kra.common.committee.bo.CommitteeBase;
import org.kuali.kra.common.committee.document.authorization.CommitteeTaskBase;
import org.kuali.kra.common.committee.print.service.CommitteePrintingServiceBase;
import org.kuali.kra.common.committee.rule.event.CommitteeActionGenerateBatchCorrespondenceEventBase;
import org.kuali.kra.common.committee.service.CommitteeBatchCorrespondenceServiceBase;
import org.kuali.kra.common.committee.web.struts.action.CommitteeActionsActionBase;
import org.kuali.kra.common.printing.CorrespondencePrintingService;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskGroupName;

import java.sql.Date;



/**
 * The CommitteeActionsAction corresponds to the Actions tab (web page).  It is
 * responsible for handling all user requests from that tab (web page).
 */
public class CommitteeActionsAction extends CommitteeActionsActionBase {

    @Override
    protected CommitteeTaskBase getNewCommitteeTaskInstanceHook(String taskName, CommitteeBase committee) {
        // creating an anonymous class to avoid task hierarchy issues
        return new CommitteeTaskBase<Committee>(TaskGroupName.COMMITTEE, taskName, (Committee) committee) {};
    }

    @Override
    protected CommitteeActionGenerateBatchCorrespondenceEventBase getNewCommitteeActionGenerateBatchCorrespondenceEventInstanceHook(
            String errorPathPrefix, org.kuali.rice.krad.document.Document document, String batchCorrespondenceTypeCode,
            Date startDate, Date endDate, String committeeId) {
        return new CommitteeActionGenerateBatchCorrespondenceEvent(errorPathPrefix, document, batchCorrespondenceTypeCode, startDate, endDate, committeeId);
    }

    @Override
    protected CommitteeBatchCorrespondenceServiceBase getCommitteeBatchCorrespondenceService() {
        return KraServiceLocator.getService(CommitteeBatchCorrespondenceService.class);
    }

    @Override
    protected CommitteePrintingServiceBase getCommitteePrintingService() {
        return KraServiceLocator.getService(CommitteePrintingService.class);
    }

    @Override
    protected org.kuali.kra.common.committee.dao.CommitteeBatchCorrespondenceDao getCommitteeBatchCorrespondenceDao() {
        return KraServiceLocator.getService(CommitteeBatchCorrespondenceDao.class);
    }

    @Override
    protected String getCommitteeDocumentTypeSimpleNameHook() {
        return "CommitteeDocument";
    }

    @Override
    protected CorrespondencePrintingService getCorrespondencePrintingService() {
        return KraServiceLocator.getService(CommitteeCorrespondencePrint.class);

    }
}
