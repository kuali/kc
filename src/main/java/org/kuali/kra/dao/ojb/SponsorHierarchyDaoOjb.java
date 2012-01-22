/*
 * Copyright 2005-2010 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.dao.ojb;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.ReportQueryByCriteria;
import org.kuali.kra.bo.SponsorHierarchy;
import org.kuali.kra.dao.SponsorHierarchyDao;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.framework.persistence.ojb.dao.PlatformAwareDaoBaseOjb;
import org.kuali.rice.krad.util.OjbCollectionAware;
import org.springframework.transaction.annotation.Transactional;

public class SponsorHierarchyDaoOjb extends PlatformAwareDaoBaseOjb implements OjbCollectionAware, SponsorHierarchyDao {
    
    public Iterator getTopSponsorHierarchy() {
        
      Criteria criteriaID = new Criteria();
      ReportQueryByCriteria queryID = new ReportQueryByCriteria(SponsorHierarchy.class,criteriaID);
      queryID.setAttributes(new String[] {Constants.HIERARCHY_NAME});
      queryID.setDistinct(true);
      
      return getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(queryID);
      
  }
    
    /**
     * This is much faster than use 'businessobjectservice.findmatching, and then loop thru bo.
     * @see org.kuali.kra.dao.SponsorHierarchyDao#getAllSponsors(java.lang.String)
     */
    public Iterator getAllSponsors(String hierarchyName) {
        
      Criteria criteriaID = new Criteria();
      criteriaID.addEqualTo(Constants.HIERARCHY_NAME, hierarchyName);
      ReportQueryByCriteria queryID = new ReportQueryByCriteria(SponsorHierarchy.class,criteriaID);
      queryID.setAttributes(new String[] {Constants.SPONSOR_CODE});
      
      return getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(queryID);
      
   }

    /**
     * Appears to need to be transactional when this is called during the maint doc page rendering.
     * @see org.kuali.kra.dao.SponsorHierarchyDao#getUniqueNamesAtLevel(java.lang.String, int)
     */
    @Transactional
    public List<String> getUniqueNamesAtLevel(String hierarchyName, int depth) {
        Criteria criteriaID = new Criteria();
        criteriaID.addEqualTo(Constants.HIERARCHY_NAME, hierarchyName);
        ReportQueryByCriteria queryID = new ReportQueryByCriteria(SponsorHierarchy.class,criteriaID);
        queryID.setAttributes(new String[] {"level" + depth});
        queryID.setDistinct(true);
        
        Iterator iter = getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(queryID);
        List<String> result = new ArrayList<String>();
        while (iter.hasNext()) {
            Object[] objects = (Object[]) iter.next();
            result.add((String) objects[0]);
        }
        return result;
    }
}
