/*
 * Copyright 2005-2010 The Kuali Foundation
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

import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.accesslayer.LookupException;
import org.kuali.kra.bo.ResearchArea;
import org.kuali.kra.dao.ResearchAreaDao;
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
    private BusinessObjectService businessObjectService;

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
                    String userName = GlobalVariables.getUserSession().getPerson().getPrincipalName();
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
                catch (SQLException e) {
                    throw new ResearchAreaDaoException("sql: " + Arrays.toString(sqls), e);
                } catch (LookupException e) {
                    throw new ResearchAreaDaoException("sql: " + Arrays.toString(sqls), e);
                } finally {
                    if (stmt != null) {
                        try {
                            stmt.close();
                        }
                        catch (Exception e) {
                            throw new ResearchAreaDaoException("sql: " + Arrays.toString(sqls), e);
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
            String deleteStmt = "delete from RESEARCH_AREAS where RESEARCH_AREA_CODE = '" + codes[0] + "'";
            LOG.info("Save run scripts " + deleteStmt);
            stmt.addBatch(deleteStmt);
            String updateStmt = "update RESEARCH_AREAS set HAS_CHILDREN_FLAG = 'N', UPDATE_USER = '" + userName
                    + "', UPDATE_TIMESTAMP = " + getSysdate() + " where RESEARCH_AREA_CODE = '" + codes[1]
                    + "' and (select count(*) from (select * from RESEARCH_AREAS) ra where ra.PARENT_RESEARCH_AREA_CODE = '" + codes[1] + "') = 0";
            LOG.info("Save run scripts " + updateStmt);
            stmt.addBatch(updateStmt);
            getNodesToDelete(codes[0], stmt);
        }
        catch (SQLException e) {
            throw new ResearchAreaDaoException("sql: " + sql, e);
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
                    "delete from RESEARCH_AREAS where RESEARCH_AREA_CODE = ");
            LOG.info("Save run scripts " + deleteStmt);
            stmt.addBatch(deleteStmt);
            String updateStmt = "update RESEARCH_AREAS set HAS_CHILDREN_FLAG = 'N', UPDATE_USER = '" + userName+ "', UPDATE_TIMESTAMP = " + getSysdate() + "  where RESEARCH_AREA_CODE = '" + parentCode+"' and (select count(*) from (select * from RESEARCH_AREAS) ra where ra.PARENT_RESEARCH_AREA_CODE = '" + parentCode+"') = 0";
            LOG.info("Save run scripts " + updateStmt);
            stmt.addBatch(updateStmt);
        }
        catch (SQLException e) {
            throw new ResearchAreaDaoException("sql: " + sql, e);
        }
    }

    /*
     * create insert research area statement based on data send in.
     */
    private void insertResearchAreas(Statement stmt, String sql, String userName) {
        try {
            String columns = "(RESEARCH_AREA_CODE,PARENT_RESEARCH_AREA_CODE,HAS_CHILDREN_FLAG, DESCRIPTION, update_timestamp, update_user, OBJ_ID)";
            sql = sql.replace(";amp", "&");
            String insertStmt = sql.replace("insert R", "insert into RESEARCH_AREAS " + columns);
            // use time for OBJ_ID will have uniqueness
            insertStmt = insertStmt.replace("sysdate, user)", getSysdate() + ", '" + userName + "','" +(new Long(new java.util.Date().getTime())).toString() +"')");
            LOG.info("Save run scripts " + insertStmt);
            stmt.addBatch(insertStmt);
            String data[] = sql.split(",");
            String updateStmt = "update RESEARCH_AREAS set HAS_CHILDREN_FLAG = 'Y', UPDATE_USER = '" + userName
                    + "', UPDATE_TIMESTAMP = " + getSysdate() + "  where RESEARCH_AREA_CODE = " + data[1];
            LOG.info("Save run scripts " + updateStmt);
            stmt.addBatch(updateStmt);
        }
        catch (SQLException e) {
            throw new ResearchAreaDaoException("sql: " + sql, e);
        }
    }

    /*
     * create sql script to update 'description' of research areas.
     */
    private void updateResearchAreas(Statement stmt, String sql, String userName) {
        try {
            sql = sql.replace(";amp", "&");
            String updateStmt = sql.replace("update R", "update RESEARCH_AREAS set UPDATE_USER = '" + userName
                    + "', UPDATE_TIMESTAMP = " + getSysdate() + ", DESCRIPTION =");
            LOG.info("Save run scripts " + updateStmt);
            stmt.addBatch(updateStmt);
        }
        catch (SQLException e) {
            throw new ResearchAreaDaoException("sql: " + sql, e);
        }
    }

    /*
     * This method is to create delete statement to remove the node and its descendants
     */
    private void getNodesToDelete(String researchAreaCode, Statement stmt) {
        try {
            for (ResearchArea researchArea : getSubResearchAreas(researchAreaCode)) {
                String deleteStmt = "delete from RESEARCH_AREAS where RESEARCH_AREA_CODE = '" + researchArea.getResearchAreaCode()
                        + "'";
                LOG.info("Save run scripts " + deleteStmt);
                stmt.addBatch(deleteStmt);
                getNodesToDelete(researchArea.getResearchAreaCode(), stmt);
            }
        }
        catch (SQLException e) {
            throw new ResearchAreaDaoException(e);
        }
    }

    /*
     * This method is to get the children research areas of the 'researchAreaCode'
     */
    private List<ResearchArea> getSubResearchAreas(String researchAreaCode) {
        List<ResearchArea> researchAreasList = new ArrayList<ResearchArea>();
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("parentResearchAreaCode", researchAreaCode);
        researchAreasList.addAll(businessObjectService.findMatching(ResearchArea.class, fieldValues));
        return researchAreasList;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    private String getSysdate() {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "to_date('" + formatter.format(new Date()) +"','YYYY-MM-DD HH24:MI:SS')";
    }
    
    /** simple runtime exception - probably be better long term to use a generic dao runtime exception. */
    private static class ResearchAreaDaoException extends RuntimeException {
        public ResearchAreaDaoException(String msg, Throwable t) {
            super(t);
        }
        
        public ResearchAreaDaoException(Throwable t) {
            super(t);
        }
    }
}
