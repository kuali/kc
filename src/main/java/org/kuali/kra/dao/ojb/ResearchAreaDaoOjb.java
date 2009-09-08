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
import org.kuali.kra.rice.shim.UniversalUser;
import org.kuali.rice.kns.dao.impl.PlatformAwareDaoBaseOjb;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.OjbCollectionAware;
import org.springmodules.orm.ojb.PersistenceBrokerCallback;

/**
 * 
 * This class is to run the sql scripts to update research areas table.
 */
public class ResearchAreaDaoOjb extends PlatformAwareDaoBaseOjb implements OjbCollectionAware, ResearchAreaDao {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory
            .getLog(ResearchAreaDaoOjb.class);

    /**
     * 
     * @see org.kuali.kra.dao.ResearchAreaDao#runScripts(java.lang.String[])
     */
    public void runScripts(final String[] sqls) {

        this.getPersistenceBrokerTemplate().execute(new PersistenceBrokerCallback() {
            public Object doInPersistenceBroker(PersistenceBroker pb) {
                Statement stmt = null;
                try {
                    stmt = pb.serviceConnectionManager().getConnection().createStatement();
                    String userName = new UniversalUser(GlobalVariables.getUserSession().getPerson()).getPersonUserIdentifier();
                    for (int i = 0; i < sqls.length; i++) {
                        if (StringUtils.isNotBlank(sqls[i])) {
                            if (sqls[i].startsWith("remove((")) {
                                removeResearchAreas(stmt, sqls[i], userName);
                            }
                            else if (sqls[i].startsWith("insert R")) {
                                insertResearchAreas(stmt, sqls[i], userName);
                            }
                            else if (sqls[i].startsWith("update R")) {
                                updateResearchAreas(stmt, sqls[i], userName);
                            }                            
                            else if (sqls[i].startsWith("delete R")) {
                                deleteResearchAreas(stmt, sqls[i], userName);
                            }
                            else {
                                LOG.info("unknown scripts " + i + sqls[i]);
                            }
                        }
                    }
                    int[] updCnt = stmt.executeBatch();
                    for (int i = 0; i < updCnt.length; i++) {
                        // do we need to do check
                    }
                }
                catch (Exception e) {
                    LOG.error("exception error " + e.getStackTrace());
                    GlobalVariables.getUserSession().addObject("raError", (Object) ("error running scripts" + e.getMessage()));
                }
                finally {
                    if (stmt != null) {
                        try {
                            stmt.close();
                        }
                        catch (Exception e) {
                            LOG.error("error closing statement", e);
                            GlobalVariables.getUserSession().addObject("raError",
                                    (Object) ("error closing statement " + e.getMessage()));
                        }
                    }
                }
                return null;
            }
        });

    }

    /*
     * create proper sql scripts to delete research areas and its descendant nodes.
     * Also check to update parent's has_children flag if needed. 
     * This script is for 'remove' node.
     */
    private void removeResearchAreas(Statement stmt, String sql, String userName) {
        try {
            String[] codes = StringUtils.substringBetween(sql, "((", "))").split(";");
            String deleteStmt = "delete from research_areas where RESEARCH_AREA_CODE = '" + codes[0] + "'";
            LOG.info("Save run scripts " + deleteStmt);
            stmt.addBatch(deleteStmt);
            String updateStmt = "update research_areas set HAS_CHILDREN_FLAG = 'N', UPDATE_USER = '" + userName
                    + "', UPDATE_TIMESTAMP = sysdate where RESEARCH_AREA_CODE = '" + codes[1]
                    + "' and (select count(*) from research_areas where PARENT_RESEARCH_AREA_CODE = '" + codes[1] + "') = 0";
            LOG.info("Save run scripts " + updateStmt);
            stmt.addBatch(updateStmt);
            getNodesToDelete(codes[0], stmt);
        }
        catch (Exception e) {
            LOG.error("Exception " + e.getStackTrace());
            GlobalVariables.getUserSession().addObject("raError", (Object) ("error delete nodes " + e.getMessage()));
        }
    }
    
    /*
     * delete one research area but not its descendants.  This is used for cut/paste.
     * The cut node will be deleted.
     */
    private void deleteResearchAreas(Statement stmt, String sql, String userName) {
        try {
            String parentCode = StringUtils.substringBetween(sql, "RESEARCH_AREA_CODE = '","'");
            String deleteStmt = sql.replace("delete R",
                    "delete from research_areas where RESEARCH_AREA_CODE = ");
            LOG.info("Save run scripts " + deleteStmt);
            stmt.addBatch(deleteStmt);
            String updateStmt = "update research_areas set HAS_CHILDREN_FLAG = 'N', UPDATE_USER = '" + userName+ "', UPDATE_TIMESTAMP = sysdate  where RESEARCH_AREA_CODE = '" + parentCode+"' and (select count(*) from research_areas where PARENT_RESEARCH_AREA_CODE = '" + parentCode+"') = 0";
            LOG.info("Save run scripts " + updateStmt);
            stmt.addBatch(updateStmt);
        }
        catch (Exception e) {
            LOG.error("Exception " + e.getStackTrace());
            GlobalVariables.getUserSession().addObject("raError", (Object) ("error delete nodes " + e.getMessage()));
        }
    }

    /*
     * create insert research area statement based on data send in.
     */
    private void insertResearchAreas(Statement stmt, String sql, String userName) {
        try {
            String columns = "(RESEARCH_AREA_CODE,PARENT_RESEARCH_AREA_CODE,HAS_CHILDREN_FLAG, DESCRIPTION, update_timestamp, update_user)";
            String insertStmt = sql.replace("insert R", "insert into research_areas " + columns);
            insertStmt = insertStmt.replace(", user)", ", '" + userName + "')");
            LOG.info("Save run scripts " + insertStmt);
            stmt.addBatch(insertStmt);
            String data[] = sql.split(",");
            String updateStmt = "update research_areas set HAS_CHILDREN_FLAG = 'Y', UPDATE_USER = '" + userName
                    + "', UPDATE_TIMESTAMP = sysdate  where RESEARCH_AREA_CODE = " + data[1];
            LOG.info("Save run scripts " + updateStmt);
            stmt.addBatch(updateStmt);
        }
        catch (Exception e) {
            LOG.error("Exception " + e.getStackTrace());
            GlobalVariables.getUserSession().addObject("raError", (Object) ("error insert nodes " + e.getMessage()));
        }
    }

    /*
     * create sql script to update 'description' of research areas.
     */
    private void updateResearchAreas(Statement stmt, String sql, String userName) {
        try {
            String updateStmt = sql.replace("update R", "update research_areas set UPDATE_USER = '" + userName
                    + "', UPDATE_TIMESTAMP = sysdate, DESCRIPTION =");
            LOG.info("Save run scripts " + updateStmt);
            stmt.addBatch(updateStmt);
        }
        catch (Exception e) {
            LOG.error("Exception " + e.getStackTrace());
            GlobalVariables.getUserSession().addObject("raError", (Object) ("error update nodes " + e.getMessage()));
        }
    }

    private void getNodesToDelete(String researchAreaCode, Statement stmt) {
        try {
            for (ResearchArea researchArea : getSubResearchAreas(researchAreaCode)) {
                String deleteStmt = "delete from research_areas where RESEARCH_AREA_CODE = '" + researchArea.getResearchAreaCode()
                        + "'";
                LOG.info("Save run scripts " + deleteStmt);
                stmt.addBatch(deleteStmt);
                getNodesToDelete(researchArea.getResearchAreaCode(), stmt);
            }
        }
        catch (Exception e) {
            LOG.error("Exception " + e.getStackTrace());
            GlobalVariables.getUserSession().addObject("raError", (Object) ("error delete nodes " + e.getMessage()));
        }
    }

    private List<ResearchArea> getSubResearchAreas(String researchAreaCode) {
        List<ResearchArea> researchAreasList = new ArrayList<ResearchArea>();
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("parentResearchAreaCode", researchAreaCode);
        researchAreasList.addAll(KraServiceLocator.getService(BusinessObjectService.class).findMatchingOrderBy(ResearchArea.class,
                fieldValues, "researchAreaCode", true));
        return researchAreasList;
    }

}
