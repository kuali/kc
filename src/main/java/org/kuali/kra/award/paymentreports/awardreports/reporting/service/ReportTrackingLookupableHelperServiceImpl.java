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

import org.kuali.kra.lookup.KraLookupableHelperServiceImpl;

/**
 * Report Tracking Lookupable Helper Service.
 */
public class ReportTrackingLookupableHelperServiceImpl extends KraLookupableHelperServiceImpl {

    private static final long serialVersionUID = 721845462946339775L;
    
    private ReportTrackingDao reportTrackingDao;

    @Override
    protected String getDocumentTypeName() {
        return "ReportTracking";
    }

    @Override
    protected String getHtmlAction() {
        return "reportTrackingLookup.do";
    }

    @Override
    protected String getKeyFieldName() {
        return "awardReportTrackingId";
    }

    protected ReportTrackingDao getReportTrackingDao() {
        return reportTrackingDao;
    }

    public void setReportTrackingDao(ReportTrackingDao reportTrackingDao) {
        this.reportTrackingDao = reportTrackingDao;
    }

}
