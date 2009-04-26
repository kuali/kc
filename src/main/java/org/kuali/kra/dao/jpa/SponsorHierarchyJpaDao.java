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
import org.hibernate.Session;
import org.kuali.kra.dao.SponsorHierarchyDao;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.bo.Parameter;
import org.kuali.rice.kns.dao.impl.PlatformAwareDaoBaseOjb;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.util.OjbCollectionAware;

public class SponsorHierarchyJpaDao extends PlatformAwareDaoBaseOjb implements OjbCollectionAware, SponsorHierarchyDao {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(SponsorHierarchyJpaDao.class);
    private static final String ERROR_MSG = "Error get sponsor codes.";
    private static final String SPONSOR_CODE_NAME_SQL = "select sponsor_code, (select sponsor_name from sponsor where sponsor_code = sponsor_hierarchy.sponsor_code) from sponsor_hierarchy ";

    private EntityManager em;
    
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    public Iterator getTopSponsorHierarchy() {
//      Criteria criteriaID = new Criteria();
//      ReportQueryByCriteria queryID = new ReportQueryByCriteria(SponsorHierarchy.class,criteriaID);
//      queryID.setAttributes(new String[] {Constants.HIERARCHY_NAME});
//      queryID.setDistinct(true);

      Query query = em.createQuery("select distinct sh.hierarchyName from SponsorHierarchy sh");        
      return query.getResultList().iterator();
      
  }
    
    /**
     * This is much faster than use 'businessobjectservice.findmatching, and then loop thru bo.
     * @see org.kuali.kra.dao.SponsorHierarchyDao#getAllSponsors(java.lang.String)
     */
    public Iterator getAllSponsors(String hierarchyName) {
        
//      Criteria criteriaID = new Criteria();
//      criteriaID.addEqualTo(Constants.HIERARCHY_NAME, hierarchyName);
//      ReportQueryByCriteria queryID = new ReportQueryByCriteria(SponsorHierarchy.class,criteriaID);
//      queryID.setAttributes(new String[] {Constants.SPONSOR_CODE});
//      
      Query query = em.createQuery("select sh.sponsorCode from SponsorHierarchy sh where sh.hierarchyName = :hierarchyName");
      query.setParameter("hierarchyName", hierarchyName);
      
      return query.getResultList().iterator();
      
  }
    
    /**
     * 
     * @see org.kuali.kra.dao.SponsorHierarchyDao#getSponsorCodesForGroup(java.lang.String, int, java.lang.String[])
     */
    public String getSponsorCodesForGroup(String hierarchyName, int level, String[] levelNames) {
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
    }
    
    public String getSponsorCodesForDeletedGroup(String hierarchyName, int level, String[] levelNames) {
        Query query = createSponsorCodeDeletionQuery(hierarchyName, level, levelNames);
        List<Object[]> results = query.getResultList();
        
        StringBuilder retStr = new StringBuilder(Constants.EMPTY_STRING);
        for(Object[] result: results) {
            String sponsorCode = (String) result[0];
            
            if (StringUtils.isBlank(retStr.toString())) {
                retStr.append(sponsorCode);
            } else {
                retStr = retStr.append(";").append(sponsorCode);
            }
        }
        
        return retStr.toString();
    }

    
    /**
     * This much faster then 'businessobjectservice.findmatching'
     * @see org.kuali.kra.dao.SponsorHierarchyDao#getsubGroups(java.lang.String, int, java.lang.String[])
     */
    public String getsubGroups(String hierarchyName, int level, String[] levelNames) {
        Query query = createSponsorHierarchySubGroupsQuery(hierarchyName, level, levelNames);
        List<Object[]> results = query.getResultList();
        
        StringBuilder retStr = new StringBuilder(Constants.EMPTY_STRING);
        for(Object[] result: results) {
            String levelN = (String) result[0];
            String levelNSortedId = (String) result[1];
            if (StringUtils.isBlank(retStr.toString())) {
                retStr.append(levelN);
            } else {
                retStr.append(Constants.SPONSOR_HIERARCHY_SEPARATOR_C1C).append(levelN);
            }
        }
        
        return retStr.toString();
    }
    
    /**
     * 
     * @see org.kuali.kra.dao.SponsorHierarchyDao#runScripts(java.lang.String[])
     */
    public void runScripts(final String[] sqls) {
        for(String sql: sqls) {
            if(sql == null) {
                continue;
            }
            Query query = em.createNativeQuery(sql);
            query.executeUpdate();            
        }    
    }

    private Session getSession() {
        return ((Session) em.getDelegate());
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
        StringBuilder sb = new StringBuilder("select unique sh.level").append(level).append(", sh.level").append(level).append("_sortid from SponsorHierarchy sh");
        sb.append("where hierarchyName = :hierarchyName");
        addLevelCriteria(sb, level, levelNames);
        sb.append(" order by sh.level").append(level).append("_sortid");
        return createQuery(sb, hierarchyName, level, levelNames);
    }
    
    private Query createSponsorHierarchyQuery(String hierarchyName, int level, String[] levelNames) {
        StringBuilder sb = new StringBuilder("select sh.sponsorCode, sp.sponsorName from SponsorHierarchy sh, Sponsor sp");
        sb.append("where hierarchyName = :hierarchyName");
        sb.append(" and sp.sponsorCode = sh.sponsorCode");
        addLevelCriteria(sb, level, levelNames);
        return createQuery(sb, hierarchyName, level, levelNames);
    }
    
    private Query createSponsorCodeDeletionQuery(String hierarchyName, int level, String[] levelNames) {
        StringBuilder sb = new StringBuilder("select sh.sponsorCode from SponsorHierarchy sh");
        sb.append("where hierarchyName = :hierarchyName");
        addLevelCriteria(sb, level, levelNames);
        return createQuery(sb, hierarchyName, level, levelNames);
    }

    private Query createQuery(StringBuilder sb, String hierarchyName, int level, String[] levelNames) {
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
}
