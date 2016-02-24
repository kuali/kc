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
package org.kuali.kra.coi.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiDisclosureEventType;
import org.kuali.kra.coi.service.CoiMessagesService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class CoiMessagesServiceImpl implements CoiMessagesService {
    private static final Log LOG = LogFactory.getLog(CoiMessagesServiceImpl.class);

    private transient BusinessObjectService businessObjectService;
    private transient ParameterService parameterService;
    private transient ConfigurationService configurationService;
    
    /**
     * @ Check COI to see if annual disclosure is coming due
     */
    public List<String> getMessages() {
        List<String>results = new ArrayList<String>();

        UserSession session = GlobalVariables.getUserSession();
        if (session != null && StringUtils.isNotEmpty(GlobalVariables.getUserSession().getPrincipalId())) {
            String personId = GlobalVariables.getUserSession().getPrincipalId();
            String renewalDateString = getParameterService().getParameterValueAsString(Constants.MODULE_NAMESPACE_COIDISCLOSURE, ParameterConstants.DOCUMENT_COMPONENT, "ANNUAL_DISCLOSURE_RENEWAL_DATE");
            LOG.debug("renewalDateString=" + renewalDateString);
            if (StringUtils.isNotEmpty(renewalDateString)) {
                Date renewalDue = null;
                try {
                    renewalDue = new Date(new SimpleDateFormat("MM/dd/yyyy").parse(renewalDateString).getTime());
                }
                catch (Exception e) {
                    LOG.error("***** no valid Annual Disclosure Certification renewal date found.  Defaulting to anniversary of last Annual");
                }
                String advanceNoticeString = getParameterService().getParameterValueAsString(Constants.MODULE_NAMESPACE_COIDISCLOSURE, ParameterConstants.DOCUMENT_COMPONENT, "ANNUAL_DISCLOSURE_ADVANCE_NOTICE");
                int advanceDays = -1;
                try {
                    advanceDays = Integer.parseInt(advanceNoticeString);
                } 
                catch (Exception e) {
                    LOG.error("***** no valid Annual Disclosure Certification advance notice parameter found.  Defaulting to 30 days.");
                    advanceDays = 30;
                }
                LOG.debug("advanceDays=" + advanceDays);
                // find latest existing annual review
                Map<String, Object> fieldValues = new HashMap<String, Object>();
                fieldValues.put("personId", personId);
                fieldValues.put("eventTypeCode", CoiDisclosureEventType.ANNUAL);
                List<CoiDisclosure> annualDisclosures = (List<CoiDisclosure>) businessObjectService.findMatching(CoiDisclosure.class, fieldValues);
                Timestamp lastAnnualDate = null;
                for (CoiDisclosure disclosure: annualDisclosures) {
                    final Timestamp disclosureCertificationTimestamp = disclosure.getCertificationTimestamp();
                    if (disclosureCertificationTimestamp != null) {
                        if (lastAnnualDate == null || lastAnnualDate.before(disclosureCertificationTimestamp)) {
                            lastAnnualDate = disclosureCertificationTimestamp;
                        }
                    }
                }
                Calendar lastAnnualCalendar = null;
                if (lastAnnualDate != null) {
                    lastAnnualCalendar = Calendar.getInstance();
                    lastAnnualCalendar.setTimeInMillis(lastAnnualDate.getTime());
                }
                final Calendar currentTime = Calendar.getInstance();
                boolean sendErrorWithDate = false;
                boolean sendError = false;
                LOG.debug("renewalDue=" + renewalDue);
                if (renewalDue != null) {
                    final Calendar reminderDate = Calendar.getInstance();
                    reminderDate.setTimeInMillis(renewalDue.getTime());
                    reminderDate.add(Calendar.DATE, -advanceDays);
                    if (currentTime.after(reminderDate) &&
                        ((lastAnnualCalendar == null) || currentTime.after(lastAnnualCalendar))) {
                        sendErrorWithDate = true;                        
                    }
                } else {
                    final Calendar dueCalendarDate = Calendar.getInstance();
                    if (lastAnnualDate == null) {
                        sendError = true;
                    } else {
                        dueCalendarDate.setTimeInMillis(lastAnnualDate.getTime());
                        dueCalendarDate.add(Calendar.YEAR, 1);
                        dueCalendarDate.add(Calendar.DATE, -1);
                        renewalDue = new Date(dueCalendarDate.getTimeInMillis());
                        final Calendar reminderDate = Calendar.getInstance();
                        reminderDate.setTimeInMillis(renewalDue.getTime());
                        reminderDate.add(Calendar.DATE, -advanceDays);
                        if (currentTime.after(reminderDate)) {
                            sendErrorWithDate = true;                        
                        }
                    }
                }
                if (sendError) {
                    String msg = getConfigurationService().getPropertyValueAsString("annual.disclosure.due.message");
                    if (!StringUtils.isEmpty(msg)) {
                        results.add(msg);
                    }
                }
                if (sendErrorWithDate) {
                    String msg = getConfigurationService().getPropertyValueAsString("annual.disclosure.due.message.with.date");
                    if (!StringUtils.isEmpty(msg)) {
                        results.add(msg.replace("{0}", new SimpleDateFormat("MM/dd/yyyy").format(renewalDue)));
                    }
                }
            }
        }
        return results;
    }

    protected BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }
    
    public void setBusinessObjectService( BusinessObjectService businessObjectService ) {
        this.businessObjectService = businessObjectService;
    }
    
    protected ParameterService getParameterService() {
        return parameterService;
    }
    
    public void setParameterService( ParameterService parameterService ) {
        this.parameterService = parameterService;
    }

    public ConfigurationService getConfigurationService() {
        return configurationService;
    }

    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }
    
}
