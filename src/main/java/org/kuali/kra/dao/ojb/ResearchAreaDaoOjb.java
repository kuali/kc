/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.dao.ojb;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ojb.broker.PersistenceBroker;
import org.kuali.kra.bo.ResearchArea;
import org.kuali.kra.dao.ResearchAreaDao;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.dao.impl.PlatformAwareDaoBaseOjb;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.OjbCollectionAware;
import org.springmodules.orm.ojb.PersistenceBrokerCallback;

public class ResearchAreaDaoOjb extends PlatformAwareDaoBaseOjb implements OjbCollectionAware, ResearchAreaDao {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory
    .getLog(ResearchAreaDaoOjb.class);

    public void runScripts(final String[] sqls) {
        
        this.getPersistenceBrokerTemplate().execute(new PersistenceBrokerCallback() {
            public Object doInPersistenceBroker(PersistenceBroker pb) {
                Statement stmt = null;
                try {
                    stmt = pb.serviceConnectionManager().getConnection().createStatement();
                    for (int i = 0; i < sqls.length; i++) {
                        if (StringUtils.isNotBlank(sqls[i])) {
                            if (sqls[i].startsWith("remove((")) {
                                String researchAreaCode = StringUtils.substringBetween(sqls[i], "((", "))");
                                String deleteStmt = "delete from research_areas where research_area_code = '" + researchAreaCode
                                + "'";
                                LOG.info("Save run scripts " + deleteStmt);
                                stmt.addBatch(deleteStmt);
                            getNodesToDelete(researchAreaCode, stmt);
                            } else {
                            LOG.info("Save run scripts " + i + sqls[i]);
                            stmt.addBatch(sqls[i]);
                            }
                        }
                    }
                    int[] updCnt = stmt.executeBatch();
                    // for (int i = 0; i < updCnt.length ; i++) {
                    // // do we need to do check
                    // }
                }
                catch (Exception e) {
                    LOG.error("exception error " + e.getStackTrace());
                }
                finally {
                    if (stmt != null) {
                        try {
                            stmt.close();
                        }
                        catch (Exception e) {
                            LOG.error("error closing statement", e);
                        }
                    }
                }
                return null;
            }
        });

    }
    
    private void getNodesToDelete(String researchAreaCode, Statement stmt) {
        try {
            for (ResearchArea researchArea : getSubResearchAreas(researchAreaCode)) {
                String deleteStmt = "delete from research_areas where research_area_code = '" + researchArea.getResearchAreaCode()
                        + "'";
                LOG.info("Save run scripts " + deleteStmt);
                stmt.addBatch(deleteStmt);
                getNodesToDelete(researchArea.getResearchAreaCode(), stmt);
            }
        }
        catch (Exception e) {
            LOG.error("Exception " + e.getStackTrace());
        }
    }

    private List<ResearchArea> getSubResearchAreas(String researchAreaCode) {
        List<ResearchArea> researchAreasList = new ArrayList<ResearchArea>();
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("parentResearchAreaCode", researchAreaCode);
        researchAreasList.addAll(KraServiceLocator.getService(BusinessObjectService.class).findMatchingOrderBy(ResearchArea.class, fieldValues, "researchAreaCode", true));
        return researchAreasList;
    }

}
