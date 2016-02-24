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
package org.kuali.kra.committee.web.struts.action;

import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.document.authorization.CommitteeTaskBase;
import org.kuali.coeus.common.committee.impl.print.service.CommitteePrintingServiceBase;
import org.kuali.coeus.common.committee.impl.rule.event.CommitteeActionGenerateBatchCorrespondenceEventBase;
import org.kuali.coeus.common.committee.impl.service.CommitteeBatchCorrespondenceServiceBase;
import org.kuali.coeus.common.committee.impl.web.struts.action.CommitteeActionsActionBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.dao.CommitteeBatchCorrespondenceDao;
import org.kuali.kra.committee.rule.event.CommitteeActionGenerateBatchCorrespondenceEvent;
import org.kuali.kra.committee.service.CommitteeBatchCorrespondenceService;
import org.kuali.kra.committee.service.CommitteeCorrespondencePrint;
import org.kuali.kra.committee.service.CommitteePrintingService;
import org.kuali.coeus.common.questionnaire.framework.print.CorrespondencePrintingService;
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
        return KcServiceLocator.getService(CommitteeBatchCorrespondenceService.class);
    }

    @Override
    protected CommitteePrintingServiceBase getCommitteePrintingService() {
        return KcServiceLocator.getService(CommitteePrintingService.class);
    }

    @Override
    protected org.kuali.coeus.common.committee.impl.dao.CommitteeBatchCorrespondenceDao getCommitteeBatchCorrespondenceDao() {
        return KcServiceLocator.getService(CommitteeBatchCorrespondenceDao.class);
    }

    @Override
    protected String getCommitteeDocumentTypeSimpleNameHook() {
        return "CommitteeDocument";
    }

    @Override
    protected CorrespondencePrintingService getCorrespondencePrintingService() {
        return KcServiceLocator.getService(CommitteeCorrespondencePrint.class);

    }
}
