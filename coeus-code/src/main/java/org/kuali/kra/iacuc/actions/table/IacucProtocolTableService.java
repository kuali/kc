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
