/*
 * Copyright 2005-2010 The Kuali Foundation
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

import org.kuali.kra.common.committee.bo.Committee;
import org.kuali.kra.common.committee.dao.CommonCommitteeBatchCorrespondenceDao;
import org.kuali.kra.common.committee.document.authorization.CommitteeTask;
import org.kuali.kra.common.committee.print.service.CommonCommitteePrintingService;
import org.kuali.kra.common.committee.rule.event.CommitteeActionGenerateBatchCorrespondenceEvent;
import org.kuali.kra.common.committee.service.CommonCommitteeBatchCorrespondenceService;
import org.kuali.kra.common.committee.web.struts.action.CommitteeActionsAction;
import org.kuali.kra.iacuc.committee.bo.IacucCommittee;
import org.kuali.kra.iacuc.committee.dao.IacucCommitteeBatchCorrespondenceDao;
import org.kuali.kra.iacuc.committee.rule.event.IacucCommitteeActionGenerateBatchCorrespondenceEvent;
import org.kuali.kra.iacuc.committee.service.IacucCommitteeBatchCorrespondenceService;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskGroupName;
import org.kuali.rice.krad.document.Document;

public class IacucCommitteeActionsAction extends CommitteeActionsAction {

    @Override
    protected CommitteeTask getNewCommitteeTaskInstanceHook(String taskName, Committee committee) {
        // creating an anonymous class to avoid task hierarchy issues
        return new CommitteeTask<IacucCommittee>(TaskGroupName.IACUC_COMMITTEE, taskName, (IacucCommittee) committee) {};
    }

    @Override
    protected CommitteeActionGenerateBatchCorrespondenceEvent getNewCommitteeActionGenerateBatchCorrespondenceEventInstanceHook(String errorPathPrefix,
            Document document, String batchCorrespondenceTypeCode, Date startDate, Date endDate, String committeeId) {
        return new IacucCommitteeActionGenerateBatchCorrespondenceEvent(errorPathPrefix, document, batchCorrespondenceTypeCode, startDate, endDate, committeeId);
    }

    @Override
    protected CommonCommitteeBatchCorrespondenceService getCommitteeBatchCorrespondenceService() {
        return KraServiceLocator.getService(IacucCommitteeBatchCorrespondenceService.class);
    }

    @Override
    protected CommonCommitteePrintingService getCommitteePrintingService() {
        return KraServiceLocator.getService(CommonCommitteePrintingService.class);
    }

    @Override
    protected CommonCommitteeBatchCorrespondenceDao getCommitteeBatchCorrespondenceDao() {
        return KraServiceLocator.getService(IacucCommitteeBatchCorrespondenceDao.class);
    }

}
