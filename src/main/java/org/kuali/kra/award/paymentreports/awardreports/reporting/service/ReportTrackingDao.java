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
import java.util.List;
import java.util.Map;

import org.kuali.kra.award.paymentreports.awardreports.reporting.ReportTracking;
import org.kuali.rice.kns.dao.LookupDao;

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
