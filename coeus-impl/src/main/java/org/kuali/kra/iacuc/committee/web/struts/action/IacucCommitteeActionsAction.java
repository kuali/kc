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
package org.kuali.kra.iacuc.committee.web.struts.action;

import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.dao.CommitteeBatchCorrespondenceDao;
import org.kuali.coeus.common.committee.impl.document.authorization.CommitteeTaskBase;
import org.kuali.coeus.common.committee.impl.print.service.CommitteePrintingServiceBase;
import org.kuali.coeus.common.committee.impl.rule.event.CommitteeActionGenerateBatchCorrespondenceEventBase;
import org.kuali.coeus.common.committee.impl.service.CommitteeBatchCorrespondenceServiceBase;
import org.kuali.coeus.common.committee.impl.web.struts.action.CommitteeActionsActionBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.questionnaire.framework.print.CorrespondencePrintingService;
import org.kuali.kra.iacuc.committee.bo.IacucCommittee;
import org.kuali.kra.iacuc.committee.dao.IacucCommitteeBatchCorrespondenceDao;
import org.kuali.kra.iacuc.committee.print.service.IacucCommitteeCorrespondencePrint;
import org.kuali.kra.iacuc.committee.print.service.IacucCommitteePrintingService;
import org.kuali.kra.iacuc.committee.rule.event.IacucCommitteeActionGenerateBatchCorrespondenceEvent;
import org.kuali.kra.iacuc.committee.service.IacucCommitteeBatchCorrespondenceService;
import org.kuali.kra.infrastructure.TaskGroupName;
import org.kuali.rice.krad.document.Document;

import java.sql.Date;

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
        return KcServiceLocator.getService(IacucCommitteeBatchCorrespondenceService.class);
    }

    @Override
    protected CommitteePrintingServiceBase getCommitteePrintingService() {
        return KcServiceLocator.getService(IacucCommitteePrintingService.class);
    }

    @Override
    protected CommitteeBatchCorrespondenceDao getCommitteeBatchCorrespondenceDao() {
        return KcServiceLocator.getService(IacucCommitteeBatchCorrespondenceDao.class);
    }

    @Override
    protected String getCommitteeDocumentTypeSimpleNameHook() {
        return "CommonCommitteeDocument";
    }

    @Override
    protected CorrespondencePrintingService getCorrespondencePrintingService() {
        return KcServiceLocator.getService(IacucCommitteeCorrespondencePrint.class);
    }

}
