/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.award.paymentreports.awardreports.reporting.service;

import org.kuali.kra.award.paymentreports.awardreports.reporting.ReportTracking;
import org.kuali.rice.krad.dao.LookupDao;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * Report Tracking DAO interface.
 */
public interface ReportTrackingDao extends LookupDao {

    /**
     * Get a list of report tracking BOs with the groupedByAttr populated
     * and then sorted by the displayByAttrs. This allows grouping and returning
     * code fields but after an OJB refresh of the BO, sorting by the displayed
     * attributes(such as the related description fields).
     * @param searchValues
     * @param groupedByAttrs
     * @param displayByAttrs
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    List<ReportTracking> getResultsGroupedBy(Map<String, String> searchValues, List<String> groupedByAttrs, List<String> displayByAttrs) throws IllegalAccessException, InvocationTargetException;
    
    /**
     * Return a list of report tracking BOs that match the search values and are sorted by
     * the detailAttrs list of columns.
     * @param searchValues
     * @param detailAttrs
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    List<ReportTracking> getDetailResults(Map<String, String> searchValues, List<String> detailAttrs) throws IllegalAccessException, InvocationTargetException;

}
