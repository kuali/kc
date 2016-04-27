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
package org.kuali.kra.award.dao.ojb;

import static org.kuali.kra.award.home.AwardConstants.AWARD_SEQUENCE_STATUS;

import org.kuali.kra.award.dao.AwardLookupDao;
import org.kuali.kra.award.home.Award;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.dao.impl.LookupDaoOjb;
import org.kuali.rice.krad.lookup.CollectionIncomplete;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class AwardLookupDaoOjb extends LookupDaoOjb  implements AwardLookupDao{

	private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(AwardLookupDaoOjb.class);

    private static final String ACTIVE_OR_PENDING_AWARD_SEQUENCE_STATUS = "ACTIVE|PENDING";
    private static final String BOTH_AWARD_SEQUENCE_STATUS = "BOTH";

    @SuppressWarnings("rawtypes")
    @Override
    public List<? extends BusinessObject> getAwardSearchResults(Map fieldValues, boolean usePrimaryKeys) {
        if (fieldValues.containsKey(AWARD_SEQUENCE_STATUS) &&
                fieldValues.get(AWARD_SEQUENCE_STATUS).toString().equalsIgnoreCase(BOTH_AWARD_SEQUENCE_STATUS)) {
            fieldValues.remove(AWARD_SEQUENCE_STATUS);
            fieldValues.put(AWARD_SEQUENCE_STATUS, ACTIVE_OR_PENDING_AWARD_SEQUENCE_STATUS);
        }
        
        Collection<Award> searchResults = findAwards(fieldValues, usePrimaryKeys);
        Collection<Award> newest = searchResults.stream()
        	.sorted(Comparator.comparing(Award::getAwardNumber).thenComparing(Comparator.comparing(Award::getSequenceNumber).reversed()))
        	.collect(Collectors.toMap(Award::getAwardNumber, Function.identity(), (p, q) -> p)).values();
        
        if (searchResults instanceof CollectionIncomplete) {
        	return new CollectionIncomplete<>(newest, ((CollectionIncomplete) searchResults).getActualSizeIfTruncated());
        } else {
        	return new ArrayList<>(newest);
        }
    }

	protected Collection<Award> findAwards(Map fieldValues, boolean usePrimaryKeys) {
		return this.findCollectionBySearchHelper(Award.class, fieldValues, false, usePrimaryKeys);
	}
}
