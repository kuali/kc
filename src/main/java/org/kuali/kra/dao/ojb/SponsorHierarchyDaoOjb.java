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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.ReportQueryByCriteria;
import org.kuali.kra.bo.SponsorHierarchy;
import org.kuali.kra.dao.SponsorHierarchyDao;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.exception.RiceRuntimeException;
import org.kuali.rice.kns.dao.impl.PlatformAwareDaoBaseOjb;
import org.kuali.rice.kns.service.ParameterService;
import org.kuali.rice.kns.util.OjbCollectionAware;
import org.springmodules.orm.ojb.PersistenceBrokerCallback;

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
}
