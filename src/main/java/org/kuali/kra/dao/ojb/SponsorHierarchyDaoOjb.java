/*
 * Copyright 2008 The Kuali Foundation.
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

import java.sql.PreparedStatement;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.ReportQueryByCriteria;
import org.kuali.core.dao.ojb.PlatformAwareDaoBaseOjb;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.OjbCollectionAware;
import org.kuali.kra.bo.SponsorHierarchy;
import org.kuali.kra.bo.SponsorHierarchyMt;
import org.kuali.kra.dao.SponsorHierarchyDao;
import org.springmodules.orm.ojb.PersistenceBrokerCallback;

import edu.iu.uis.eden.exception.WorkflowRuntimeException;

public class SponsorHierarchyDaoOjb extends PlatformAwareDaoBaseOjb implements OjbCollectionAware, SponsorHierarchyDao {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory
    .getLog(SponsorHierarchyDaoOjb.class);

    public Iterator getTopSponsorHierarchy() {
        
//      ReportQueryByCriteria queryCriteria = QueryFactory.newReportQuery(SponsorHierarchy.class, new Criteria());
//      queryCriteria.setAttributes(new String[] { "hierarchyName" });
//      //queryCriteria.addGroupBy("hierarchyName");
//      
//      //Iterator results = getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(queryCriteria);
//      List results = (List)getPersistenceBrokerTemplate().getCollectionByQuery(queryCriteria);
      Criteria criteriaID = new Criteria();
      ReportQueryByCriteria queryID = new ReportQueryByCriteria(SponsorHierarchy.class,criteriaID);
      queryID.setAttributes(new String[] {"hierarchyName"});
      queryID.setDistinct(true);
      
      Iterator results = 
          getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(queryID);
      
      return results;

  }
    
    
    public Iterator getSponsorHierarchyMt() {
        
      Criteria criteriaID = new Criteria();
      Calendar c = Calendar.getInstance();
      c.setTime(new Date());
      // more than one day old
      c.add (Calendar.DAY_OF_YEAR, -1);

      criteriaID.addLessThan("updateTimestamp", new java.sql.Date(c.getTime().getTime()));
      criteriaID.addEqualTo("updateUser", GlobalVariables.getUserSession().getLoggedInUserNetworkId());
      ReportQueryByCriteria queryID = new ReportQueryByCriteria(SponsorHierarchyMt.class,criteriaID);
      queryID.setAttributes(new String[] {"hierarchyName"});
      queryID.setDistinct(true);
      
      Iterator results = 
          getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(queryID);
      
      return results;

  }
    
    public void changeSortId(final String hierarchyName, String depth, String groups, final int sortid) {
        
        String sql = "update sponsor_hierarchy_mt set ";
        sql = sql+"level" + depth+ "_sortid = ? where hierarchy_name = ?";
        final String[] ascendantList = groups.split(";1;");
        int level = 1;
        if (StringUtils.isNotBlank(groups)) {
            // last one is new group name
            for (int i = 0; i < ascendantList.length ; i++) {
               // if (level == 1) {
               //     sql = sql + "level" + level +"=? ";
               // } else {
                    sql = sql + "and level" + level +"=? ";                    
               // }
                level++;
            }
        }
        final String updSql = sql;
        this.getPersistenceBrokerTemplate().execute(new PersistenceBrokerCallback() {
            public Object doInPersistenceBroker(PersistenceBroker pb) {
                PreparedStatement ps = null;
                
                try {
                    ps = pb.serviceConnectionManager().getConnection().prepareStatement(updSql);
                    ps.setInt(1, sortid);
                    ps.setString(2, hierarchyName);
                    for (int i = 0; i < ascendantList.length ; i++) {
                        ps.setString(i+3, ascendantList[i]);
                    }
                    //pb.beginTransaction();
                    ps.executeUpdate();
                    //pb.commitTransaction();
                } catch (Exception e) {
                    LOG.error("exception error " +e.getStackTrace());
                } finally {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (Exception e) {
                            LOG.error("error closing preparedstatement", e);
                        }
                    }
                }
                return null;
            }
        });

        }


}
