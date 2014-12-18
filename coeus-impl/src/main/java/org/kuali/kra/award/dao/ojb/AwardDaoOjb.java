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
package org.kuali.kra.award.dao.ojb;

import org.apache.ojb.broker.query.*;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.dao.AwardDao;
import org.kuali.kra.award.dao.AwardPersonDao;
import org.kuali.kra.award.home.Award;
import org.kuali.rice.core.framework.persistence.ojb.dao.PlatformAwareDaoBaseOjb;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.dao.LookupDao;
import org.kuali.rice.krad.service.util.OjbCollectionAware;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AwardDaoOjb extends PlatformAwareDaoBaseOjb implements OjbCollectionAware, AwardDao {

    @Override
    public String getAwardNumber(Long awardId) {
        Criteria crit = new Criteria();
        crit.addEqualTo("awardId", awardId);
        ReportQueryByCriteria q = QueryFactory.newReportQuery(Award.class, crit);
        q.setAttributes(new String[] { "awardNumber" });
        Iterator<Object> resultsIter = getPersistenceBrokerTemplate().getIteratorByQuery(q);
        if (!resultsIter.hasNext()) {
            return null;
        }
        final String awardNumber = (String)resultsIter.next();
        while (resultsIter.hasNext()) {
            resultsIter.next(); // exhaust the iterator so the result set can be returned
        }
        return awardNumber;
    }
}
