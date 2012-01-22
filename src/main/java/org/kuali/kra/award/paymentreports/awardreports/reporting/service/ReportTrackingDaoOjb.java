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
package org.kuali.kra.award.paymentreports.awardreports.reporting.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.apache.ojb.broker.query.ReportQueryByCriteria;
import org.kuali.kra.award.paymentreports.awardreports.reporting.ReportTracking;
import org.kuali.kra.logging.BufferedLogger;
import org.kuali.rice.krad.dao.impl.LookupDaoOjb;
import org.kuali.rice.krad.service.PersistenceStructureService;
import org.kuali.rice.krad.util.ObjectUtils;

/**
 * Report Tracking Dao for OJB.
 */
public class ReportTrackingDaoOjb extends LookupDaoOjb implements ReportTrackingDao {
    
    private PersistenceStructureService persistenceStructureServiceLocal;

    /**
     * @see org.kuali.kra.award.paymentreports.awardreports.reporting.service.ReportTrackingDao#getResultsGroupedBy(java.util.Map, java.util.List, java.util.List)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ReportTracking> getResultsGroupedBy(Map<String, String> searchValues, List<String> groupedByAttrs, List<String> displayByAttrs) throws IllegalAccessException, InvocationTargetException {
        Criteria criteria = getCollectionCriteriaFromMap(new ReportTracking(), searchValues);
        List<String> columns = new ArrayList<String>(groupedByAttrs);
        columns.add("count(*)");
        ReportQueryByCriteria query = QueryFactory.newReportQuery(ReportTracking.class, columns.toArray(new String[1]), criteria, true);
        query.addGroupBy(groupedByAttrs.toArray(new String[1]));
        for (String attr : groupedByAttrs) {
            query.addOrderByAscending(attr);
        }
        Iterator iter = getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(query);
        List<ReportTracking> searchResults = new ArrayList<ReportTracking>();
        while (iter.hasNext()) {
            Object[] curLine = (Object[]) iter.next();
            ReportTracking curItem = new ReportTracking();
            int i = 0;
            for (; i < groupedByAttrs.size(); i++) {
                String column = groupedByAttrs.get(i);
                if (curLine[i] != null) {
                    if (ObjectUtils.getPropertyType(curItem, column, persistenceStructureServiceLocal).isAssignableFrom(Timestamp.class)
                            && curLine[i] instanceof Date) {
                        BeanUtils.setProperty(curItem, column, new Timestamp(((Date) curLine[i]).getTime()));
                    } else {
                        BeanUtils.setProperty(curItem, column, curLine[i]);
                    }
                }
            }
            curItem.setItemCount(((BigDecimal) curLine[i]).intValue());
            curItem.refreshNonUpdateableReferences();
            searchResults.add(curItem);
        }
        Collections.sort(searchResults, new MultiColumnComparator(displayByAttrs));
        return searchResults;
    }
    
    /**
     * @see org.kuali.kra.award.paymentreports.awardreports.reporting.service.ReportTrackingDao#getDetailResults(java.util.Map, java.util.List)
     */
    public List<ReportTracking> getDetailResults(Map<String, String> searchValues, List<String> detailAttrs) throws IllegalAccessException, InvocationTargetException {
        Criteria criteria = getCollectionCriteriaFromMap(new ReportTracking(), searchValues);
        QueryByCriteria query = QueryFactory.newQuery(ReportTracking.class, criteria, false);
        List<ReportTracking> result = new ArrayList<ReportTracking>(getPersistenceBrokerTemplate().getCollectionByQuery(query));
        Collections.sort(result, new MultiColumnComparator(detailAttrs));
        return result;
    }
    
    /**
     * Comparator that supports sorting a list of report tracking BOs by a list of
     * columns. It will first sort by the first column, if that column is the same
     * then by the second column, etc.
     */
    protected class MultiColumnComparator implements Comparator<ReportTracking> {

        private List<String> columnsToSortBy;
        public MultiColumnComparator(List<String> columnsToSortBy) {
            this.columnsToSortBy = columnsToSortBy;
        }
        @Override
        public int compare(ReportTracking o1, ReportTracking o2) {
            int result = 0;
            for (String column : columnsToSortBy) {
                String v1 = null;
                String v2 = null;
                try {
                    v1 = BeanUtils.getProperty(o1, column);
                    v2 = BeanUtils.getProperty(o2, column);
                } catch (Exception e) { 
                    BufferedLogger.warn("Exception while trying to sort report tracking records.", e);
                }
                if (v1 == null && v2 == null) {
                    return 0;
                } else if (v1 == null && v2 != null) {
                    return -1;
                } else if (v2 == null && v1 != null) {
                    return 1;
                }
                result = v1.compareTo(v2);
                if (result != 0) { 
                    break;
                }
            }
            return result;
        }
        
    }

    protected PersistenceStructureService getPersistenceStructureServiceLocal() {
        return persistenceStructureServiceLocal;
    }

    public void setPersistenceStructureServiceLocal(PersistenceStructureService persistenceStructureServiceLocal) {
        this.persistenceStructureServiceLocal = persistenceStructureServiceLocal;
    }

}
