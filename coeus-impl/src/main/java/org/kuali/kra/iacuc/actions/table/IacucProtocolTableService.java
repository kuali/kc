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
package org.kuali.kra.iacuc.actions.table;

import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolDocument;

/**
 * This class is the service API for the protocol "table" action
 */
public interface IacucProtocolTableService {
    
    /**
     * @param committee
     * @param schedule
     * @return the next schedule in order of schedule date, or null if there is no next schedule.
     */
    public CommitteeScheduleBase getNextScheduleForCommittee(CommitteeScheduleBase schedule);
    
    
    
    /**
     * This method will "table" the protocol by bumping it from its current assigned schedule to the next 
     * in order by date for the same committee, will also record the data from the actionBean. 
     * @param protocol
     * @param actionBean
     * @throws Exception 
     */
    public IacucProtocolDocument tableProtocol(IacucProtocol protocol, IacucProtocolTableBean actionBean) throws Exception;

}
