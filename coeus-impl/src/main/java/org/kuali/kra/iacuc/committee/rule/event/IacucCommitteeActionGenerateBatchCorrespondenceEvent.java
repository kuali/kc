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
package org.kuali.kra.iacuc.committee.rule.event;

import org.kuali.coeus.common.committee.impl.rule.event.CommitteeActionGenerateBatchCorrespondenceEventBase;
import org.kuali.coeus.common.committee.impl.rules.CommitteeActionGenerateBatchCorrespondenceRuleBase;
import org.kuali.kra.iacuc.committee.rules.IacucCommitteeActionGenerateBatchCorrespondenceRule;
import org.kuali.rice.krad.document.Document;

import java.sql.Date;


public class IacucCommitteeActionGenerateBatchCorrespondenceEvent extends CommitteeActionGenerateBatchCorrespondenceEventBase {

    public IacucCommitteeActionGenerateBatchCorrespondenceEvent(String errorPathPrefix, Document document,
            String batchCorrespondenceTypeCode, Date startDate, Date endDate, String committeeId) {
        super(errorPathPrefix, document, batchCorrespondenceTypeCode, startDate, endDate, committeeId);
    }

    @Override
    protected CommitteeActionGenerateBatchCorrespondenceRuleBase getNewCommitteeActionGenerateBatchCorrespondenceRuleInstanceHook() {
        return new IacucCommitteeActionGenerateBatchCorrespondenceRule();
    }

}
