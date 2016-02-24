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
package org.kuali.kra.coi.lookup;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.auth.task.ApplicationTask;
import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.coi.CoiDisclosureUndisclosedEvents;
import org.kuali.kra.coi.disclosure.CoiDisclosureService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.lookup.KraLookupableHelperServiceImpl;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.web.struts.form.LookupForm;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.util.GlobalVariables;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class CoiDisclosureUndisclosedEventsLookupableHelper extends KraLookupableHelperServiceImpl {

    private CoiDisclosureService coiDisclosureService;

    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        List<CoiDisclosureUndisclosedEvents> undisclosedEvents = new ArrayList<CoiDisclosureUndisclosedEvents>();
        validateSearchParameters(fieldValues);
        addDateRangeCriteria(fieldValues);
        if(canViewUndisclosedEvents()) {
            undisclosedEvents = getCoiDisclosureService().getUndisclosedEvents(fieldValues);
        }
        return undisclosedEvents;
    }

    
    protected boolean validateDate(String dateFieldName, String dateFieldValue) {
        try{
            CoreApiServiceLocator.getDateTimeService().convertToSqlTimestamp(dateFieldValue);
            return true;
        } catch (ParseException e) {
            GlobalVariables.getMessageMap().putError(dateFieldName, KeyConstants.ERROR_SEARCH_INVALID_DATE);
            return false;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            GlobalVariables.getMessageMap().putError(dateFieldName, KeyConstants.ERROR_SEARCH_INVALID_DATE);
            return false;
        }
    }

    @Override
    public void validateSearchParameters(Map<String,String> fieldValues) {
        super.validateSearchParameters(fieldValues);
        for (String key : fieldValues.keySet()) {
            String value = fieldValues.get(key).toString();
            if (key.toUpperCase().indexOf("DATE") > 0) {
                //we have a date, now we need to weed out the calculated params that have '..' or '>=' or '<='
                if (value.indexOf("..") == -1 && value.indexOf(">=") == -1 && value.indexOf("<=") == -1) {
                    if (!StringUtils.isEmpty(value)) {
                        validateDate(key, value);
                    }
                }
            }
        }
    }
    
    private void addDateRangeCriteria(Map<String,String> fieldValues) {
        String dateParameter = fieldValues.get(CoiDisclosureUndisclosedEvents.SEARCH_CRITERIA_CREATE_DATE);
        if (dateParameter.contains("..")) {
            String[] values = dateParameter.split("\\.\\.");
            String startDate = values[0];
            String endDate = getRevisedCreateToDate(values[1], 1);
            fieldValues.put(CoiDisclosureUndisclosedEvents.SEARCH_CRITERIA_CREATE_DATE_FROM, startDate);
            fieldValues.put(CoiDisclosureUndisclosedEvents.SEARCH_CRITERIA_CREATE_DATE_TO, endDate);
        }else if(dateParameter.contains(">=")) {
            String dateValue = dateParameter.replace(">=", "");
            fieldValues.put(CoiDisclosureUndisclosedEvents.SEARCH_CRITERIA_CREATE_DATE_FROM, dateValue);
        }else if((dateParameter.contains("<="))) {
            String dateValue = dateParameter.replace("<=", "");
            String endDate = getRevisedCreateToDate(dateValue, 1);
            fieldValues.put(CoiDisclosureUndisclosedEvents.SEARCH_CRITERIA_CREATE_DATE_TO, endDate);
        }
    }
    
    @Override
    public List<Row> getRows() {
        List<Row> rows =  super.getRows();
        for (Row row : rows) {
            for (Field field : row.getFields()) {
                if (field.getPropertyName().equals("person.userName")) {
                    field.setFieldConversions("principalName:person.userName,principalId:personId");
                }
            }
        }
        return rows;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection performLookup(LookupForm lookupForm, Collection resultTable, boolean bounded) {
        String userName = (String) lookupForm.getFieldsForLookup().get("person.userName");
            if (StringUtils.isNotEmpty(userName)) {
                KcPerson person = getKcPersonService().getKcPersonByUserName(userName);
            if (person != null) {
                lookupForm.getFieldsForLookup().put("personId", person.getPersonId());
            }
        }
        
        return super.performLookup(lookupForm, resultTable, bounded);
    }
    
    public KcPersonService getKcPersonService() {
        return (KcPersonService) KcServiceLocator.getService(KcPersonService.class);
    }
    
   
    public CoiDisclosureService getCoiDisclosureService() {
        return coiDisclosureService;
    }
    
    public void setCoiDisclosureService(CoiDisclosureService coiDisclosureService) {
        this.coiDisclosureService = coiDisclosureService;
    }
    /*
     * The only action that we might have here in the future 
     * would be a notification to the reporter.
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
        List<HtmlData> htmlDataList = new ArrayList<HtmlData>();
        return htmlDataList;
    }
    
   /*
    * Leaving this empty because we are not trying to open any documents.
    */
    @Override
    protected String getDocumentTypeName() {
        return "";
    }

    /*
     *This lookup is also not associated with any particular document, so leaving the html action part to null 
     */
    @Override
    protected String getHtmlAction() {
        return null;
    }

    @Override
    protected String getKeyFieldName() {
        return "projectId";
    }
       
    private boolean canViewUndisclosedEvents() {
        ApplicationTask task = new ApplicationTask(TaskName.VIEW_COI_UNDISCLOSED_EVENTS);
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);     
    }

    private String getUserIdentifier() {
        return GlobalVariables.getUserSession().getPrincipalId();
    }
    
    protected TaskAuthorizationService getTaskAuthorizationService() {
        return KcServiceLocator.getService(TaskAuthorizationService.class);
    }
    
    /**
     * This method is to get an adjusted date to compare it with timestamp
     * @param dateInput
     * @param addDays
     * @return
     */
    private String getRevisedCreateToDate(String dateInput, int addDays) {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DEFAULT_DATE_FORMAT_PATTERN);
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(dateInput));
        }catch(Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
        cal.add(Calendar.DATE, addDays); 
        return sdf.format(cal.getTime());        
    }

}

