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
package org.kuali.kra.subaward.bo;

import java.util.Arrays;
import java.util.List;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.accesslayer.QueryCustomizerDefaultImpl;
import org.apache.ojb.broker.metadata.CollectionDescriptor;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.kuali.coeus.common.framework.version.VersionStatus;

public class AllSubAwardAmountInfoQueryCustomizer extends QueryCustomizerDefaultImpl {

	static final String SUB_AWARD_AMOUNT_INFO_ID = "subAwardAmountInfoId";
	static final String SUB_AWARD_SEQUENCE_NUMBER_PATH = "subAward.sequenceNumber";
	static final List<String> ALLOWED_STATUSES = Arrays.asList(new String[]{VersionStatus.ACTIVE.toString(), VersionStatus.PENDING.toString(), VersionStatus.ARCHIVED.toString()});
	static final String SUB_AWARD_SUB_AWARD_SEQUENCE_STATUS_PATH = "subAward.subAwardSequenceStatus";
	static final String SUB_AWARD_SUB_AWARD_CODE_PATH = "subAward.subAwardCode";

	@Override
    public Query customizeQuery(Object anObject,
            PersistenceBroker aBroker,
            CollectionDescriptor aCod, QueryByCriteria aQuery){
    	Criteria crit = buildCriteria((SubAward)anObject);
        aQuery.setCriteria(crit);
        return aQuery;
    }

	Criteria buildCriteria(SubAward subAward) {
		Criteria crit = new Criteria();
    	crit.addEqualTo(SUB_AWARD_SUB_AWARD_CODE_PATH, subAward.getSubAwardCode());
    	crit.addIn(SUB_AWARD_SUB_AWARD_SEQUENCE_STATUS_PATH, ALLOWED_STATUSES);
    	crit.addLessOrEqualThan(SUB_AWARD_SEQUENCE_NUMBER_PATH, subAward.getSequenceNumber());
    	crit.addOrderByAscending(SUB_AWARD_AMOUNT_INFO_ID);
		return crit;
	}
}
