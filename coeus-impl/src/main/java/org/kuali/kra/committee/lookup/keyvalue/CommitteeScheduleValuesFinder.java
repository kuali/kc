/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.committee.lookup.keyvalue;

import org.kuali.coeus.common.committee.impl.lookup.keyvalue.CommitteeScheduleValuesFinderBase;
import org.kuali.coeus.common.committee.impl.service.CommitteeServiceBase;
import org.kuali.kra.committee.service.CommitteeService;

/**
 * Finds the available set of dates where a protocol can be scheduled
 * for a review by a committee.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class CommitteeScheduleValuesFinder extends CommitteeScheduleValuesFinderBase {
    

    private static final long serialVersionUID = 338230898950097350L;

    @Override
    protected Class<? extends CommitteeServiceBase> getCommitteeServiceClassHook() {
        return CommitteeService.class;
    }
}
