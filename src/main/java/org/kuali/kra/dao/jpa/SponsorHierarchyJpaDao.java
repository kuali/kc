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
package org.kuali.kra.dao.jpa;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.core.bo.Parameter;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.kra.dao.SponsorHierarchyDao;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.exceptions.RiceRuntimeException;
import org.springframework.transaction.annotation.Transactional;

public class SponsorHierarchyJpaDao implements SponsorHierarchyDao {
    private static final Log LOG = LogFactory.getLog(SponsorHierarchyJpaDao.class);
    
    private static final String ERROR_MSG = "Error in SponsorHierarchyJpaDao";
    
    private EntityManager em;
    
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public Iterator<Object[]> getTopSponsorHierarchy() {
      try {
          Query query = em.createQuery("select distinct sh.hierarchyName from SponsorHierarchy sh");        
          return query.getResultList().iterator();
      } catch (Exception e) {
          LOG.error(e.getStackTrace());
          throw new RiceRuntimeException(ERROR_MSG, e);
      }      
    }

    /**
     * This is much faster than use 'businessobjectservice.findmatching, and then loop thru bo.
     * @see org.kuali.kra.dao.SponsorHierarchyDao#getAllSponsors(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Transactional
    public Iterator<Object[]> getAllSponsors(String hierarchyName) {
        try {
            Query query = em.createQuery("select sh.sponsorCode from SponsorHierarchy sh where sh.hierarchyName = :hierarchyName");
            query.setParameter("hierarchyName", hierarchyName);
            return query.getResultList().iterator();
        } catch (Exception e) {
            LOG.error(e.getStackTrace());
            throw new RiceRuntimeException(ERROR_MSG, e);
        }      
  }
    
    /**
     * 
     * @see org.kuali.kra.dao.SponsorHierarchyDao#getSponsorCodesForGroup(java.lang.String, int, java.lang.String[])
     */
    @SuppressWarnings("unchecked")
    @Transactional
    public String getSponsorCodesForGroup(String hierarchyName, int level, String[] levelNames) {
        try {
            Query query = createSponsorHierarchyQuery(hierarchyName, level, levelNames);
            List<Object[]> results = query.getResultList();
            
            int groupingNumber = findGroupingNumber();
            int i = groupingNumber;
            StringBuilder retStr = new StringBuilder(Constants.EMPTY_STRING);
            for(Object[] result : results) {
                if (StringUtils.isBlank(retStr.toString())) {
                    formatResult(retStr, result);
                } else {
                    i--;
                    if (i > 0) {
                        retStr.append(Constants.SPONSOR_HIERARCHY_SEPARATOR_C1C);
                        formatResult(retStr, result);
                    } else {
                        retStr.append("#1#");
                        formatResult(retStr, result);
                        i = groupingNumber;
                    }
                }
            }
                                       
            return retStr.toString();
        } catch (Exception e) {
            LOG.info(e.getStackTrace());
            throw new RiceRuntimeException(ERROR_MSG, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    @Transactional
    public String getSponsorCodesForDeletedGroup(String hierarchyName, int level, String[] levelNames) {
        try {
            Query query = createSponsorCodeDeletionQuery(hierarchyName, level, levelNames);
            List<String> results = query.getResultList();
            
            StringBuilder retStr = new StringBuilder(Constants.EMPTY_STRING);
            for(String sponsorCode: results) {                
                if (StringUtils.isBlank(retStr.toString())) {
                    retStr.append(sponsorCode);
                } else {
                    retStr = retStr.append(";").append(sponsorCode);
                }
            }
            
            return retStr.toString();
        } catch (Exception e) {
            LOG.info(e.getStackTrace());
            throw new RiceRuntimeException(ERROR_MSG, e);
        }
    }

    
    /**
     * This much faster then 'businessobjectservice.findmatching'
     * @see org.kuali.kra.dao.SponsorHierarchyDao#getsubGroups(java.lang.String, int, java.lang.String[])
     */
    @SuppressWarnings("unchecked")
    @Transactional
    public String getSubGroups(String hierarchyName, int level, String[] levelNames) {
        try {
            Query query = createSponsorHierarchySubGroupsQuery(hierarchyName, level, levelNames);
            List<Object[]> results = query.getResultList();
            
            StringBuilder retStr = new StringBuilder(Constants.EMPTY_STRING);
            for(Object[] result: results) {
                String levelN = (String) result[0];
//                String levelNSortedId = (String) result[1];
                if (StringUtils.isBlank(retStr.toString())) {
                    retStr.append(levelN);
                } else {
                    retStr.append(Constants.SPONSOR_HIERARCHY_SEPARATOR_C1C).append(levelN);
                }
            }
            
            return retStr.toString();
        } catch (Exception e) {
            LOG.info(e.getStackTrace());
            throw new RiceRuntimeException(ERROR_MSG, e);
        }
    }
    
    /**
     * 
     * @see org.kuali.kra.dao.SponsorHierarchyDao#runScripts(java.lang.String[])
     */
    @Transactional
    public void runScripts(final String[] sqls) {
        try {
            for(String sql: sqls) {
                if(sql == null) {
                    continue;
                }
                verifySafeSQL(sql);
                Query query = em.createNativeQuery(sql);
                query.executeUpdate();            
            }
        } catch (Exception e) {
            LOG.info(e.getStackTrace());
            throw new RiceRuntimeException(ERROR_MSG, e);
        }
    }

    private void verifySafeSQL(String sql) {
        String sqlUpperCase = sql.trim().toUpperCase();
        if(!(sqlUpperCase.startsWith("SELECT") || sqlUpperCase.startsWith("UPDATE") || sqlUpperCase.startsWith("DELETE"))) {
            throw new IllegalArgumentException("SQL starts with invalid command. Must be one of SELECT, UPDATE, DELETE");
        }        
    }

    private int findGroupingNumber() {
        int groupingNumber = 300;
        try {
           Parameter sysParam = KraServiceLocator.getService(KualiConfigurationService.class).getParameter(
                Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, "A", Constants.NUMBER_PER_SPONSOR_HIERARCHY_GROUP);
           groupingNumber=Integer.parseInt(sysParam.getParameterValue());
        } catch (Exception e) {
            LOG.debug("System param for numberPerSponsorHierarchyGroup is not defined");
        }
        return groupingNumber;
    }
    
    private StringBuilder formatResult(StringBuilder sb, Object[] result) {
        sb.append(result[0]).append(":").append(result[1]);
        return sb;
    }
    
    private Query createSponsorHierarchySubGroupsQuery(String hierarchyName, int level, String[] levelNames) {
        final String ALIAS = "sh";
        StringBuilder sb = new StringBuilder("select distinct ").append(ALIAS).append(".level").append(level).append(", ");
        sb.append(createSortIdQueryFieldFromLevel(ALIAS, level));
        sb.append(" from SponsorHierarchy ").append(ALIAS).append(" where ");
        addSponsorHierarchyNameWhereClause(sb);
        addLevelCriteria(sb, level, levelNames);
        sb.append(" order by sh.level").append(level).append("Sortid");
        return createQuery(sb, hierarchyName, level, levelNames);
    }
    
    private Query createSponsorHierarchyQuery(String hierarchyName, int level, String[] levelNames) {
        StringBuilder sb = new StringBuilder("select sh.sponsorCode, sp.sponsorName from SponsorHierarchy sh, Sponsor sp where ");
        sb.append("sp.sponsorCode = sh.sponsorCode");
        sb.append(" and ");
        addSponsorHierarchyNameWhereClause(sb);
        addLevelCriteria(sb, level, levelNames);
        return createQuery(sb, hierarchyName, level, levelNames);
    }

    private void addSponsorHierarchyNameWhereClause(StringBuilder sb) {
        sb.append("sh.hierarchyName = :hierarchyName ");
    }
    
    private Query createSponsorCodeDeletionQuery(String hierarchyName, int level, String[] levelNames) {
        StringBuilder sb = new StringBuilder("select sh.sponsorCode from SponsorHierarchy sh where ");
        addSponsorHierarchyNameWhereClause(sb);
        addLevelCriteria(sb, level, levelNames);
        return createQuery(sb, hierarchyName, level, levelNames);
    }

    private Query createQuery(StringBuilder sb, String hierarchyName, int level, String[] levelNames) {
        System.out.println(sb.toString());
        Query query = em.createQuery(sb.toString());
        setQueryParameters(query, hierarchyName, level, levelNames);
        return query;
    }
    
    private void setQueryParameters(Query query, String hierarchyName, int level, String[] levelNames) {
        query.setParameter("hierarchyName", hierarchyName);
        for (int i = 1; i < level; i++) {
            query.setParameter("level" + i, levelNames[i-1]);
        }
    }
    
    private void addLevelCriteria(StringBuilder sb, int level, String[] levelNames) {
        for (int i = 1; i < level; i++) {
            sb.append("and ");
            String parm = "level" + i;
            sb.append(parm);
            sb.append(" = :");
            sb.append(parm);
        }        
    }
    
    private String createSortIdQueryFieldFromLevel(String alias, int level) {
        return new StringBuilder(alias).append(".level").append(level).append("Sortid").toString();
    }
}
