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
import org.kuali.kra.coi.CoiDisclProject;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiDisclosureEventType;
import org.kuali.kra.coi.auth.CoiDisclosureTask;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.util.GlobalVariables;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class CoiDisclosureLookupableHelperServiceImpl extends CoiDisclosureLookupableHelperBase {


    private static final long serialVersionUID = 7142988696939655833L;
    
    //field names
    private static final String LEAD_UNIT = "leadUnitNumber";


    protected boolean validateDate(String dateFieldName, String dateFieldValue) {
        try{
            CoreApiServiceLocator.getDateTimeService().convertToSqlTimestamp(dateFieldValue);
            return true;
        } catch (ParseException e) {
            GlobalVariables.getMessageMap().putError(dateFieldName, KeyConstants.ERROR_PROTOCOL_SEARCH_INVALID_DATE);
            return false;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            GlobalVariables.getMessageMap().putError(dateFieldName, KeyConstants.ERROR_PROTOCOL_SEARCH_INVALID_DATE);
            return false;
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void validateSearchParameters(Map<String,String> fieldValues) {
        super.validateSearchParameters(fieldValues);
        for (String key : fieldValues.keySet()) {
            String value = fieldValues.get(key).toString();
            if (key.toUpperCase().indexOf("DATE") > 0) {
                //we have a date, now we need to weed out the calculated params that have '..' or '>=' or '<='
                if (value.indexOf("..") == -1 && value.indexOf(">=") == -1 && value.indexOf("<=") == -1) {
                    if (StringUtils.isNotBlank(value)) {
                        validateDate(key, value);
                    }
                }
            }
        }
    }

    @Override
    public List<? extends BusinessObject> getLookupSpecificSearchResults(Map<String, String> fieldValues) {
        validateSearchParameters(fieldValues);
        List<CoiDisclosure> results;
        // need to set backlocation & docformkey here. Otherwise, they are empty
        super.setBackLocationDocFormKey(fieldValues);
        results = (List<CoiDisclosure>)super.getResults(fieldValues);
        return filterResults(results, fieldValues);
    }

    protected List<CoiDisclosure> filterResults(List<CoiDisclosure> rawResults, Map<String, String> fieldValues) {
        List<CoiDisclosure> finalResults = new ArrayList<CoiDisclosure>();
        String researcherLeadUnit = fieldValues.get(LEAD_UNIT);
        for (CoiDisclosure disclosure : rawResults) {
            if (disclosure.getCoiDisclosureDocument() != null) {
                CoiDisclosureTask task = new CoiDisclosureTask(TaskName.VIEW_COI_DISCLOSURE, disclosure);
                if (getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task) && 
                        (StringUtils.isBlank(researcherLeadUnit) || researcherLeadUnit.equals(disclosure.getLeadUnitNumber()))) {
                    //populate project id, title only for system generated and manual projects
                    if ( disclosure.isSystemEvent() || disclosure.isManualEvent())
                    {
                        List<CoiDisclProject> coiDisclProjects = disclosure.getCoiDisclProjects();

                        CoiDisclosureEventType coiDisclosureEventType = disclosure.getCoiDisclosureEventType();
                        String coiDisclosureModuleItemKey = disclosure.getModuleItemKey();
                        int index = 0;
                        for(CoiDisclProject coiDisclProject : coiDisclProjects)
                        {
                            if ( coiDisclosureEventType.getEventTypeCode().equals(coiDisclProject.getDisclosureEventType()) &&
                                        coiDisclosureModuleItemKey.equals(coiDisclProject.getModuleItemKey()) )
                            {
                                disclosure.setCoiDisclProjectId(coiDisclProjects.get(index).getProjectId());
                                disclosure.setCoiDisclProjectTitle(coiDisclProjects.get(index).getCoiProjectTitle());
                                break;
                            }
                            index++;
                            
                        }
                    }
                    finalResults.add(disclosure);
                }
            }
        }
        return finalResults;
    }
    
    @Override
    protected boolean isAuthorizedForCoiLookups() {
        return true;
    }


}
