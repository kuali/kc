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
package org.kuali.coeus.common.committee.impl.lookup;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.irb.ResearchArea;
import org.kuali.kra.lookup.KraLookupableHelperServiceImpl;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.lookup.LookupUtils;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.lookup.CollectionIncomplete;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 
 * This class is for committee lookup.
 */
public abstract class CommitteeLookupableHelperServiceImplBase<CMT extends CommitteeBase<CMT, ?, ?>>

                                                           extends KraLookupableHelperServiceImpl {

    private static final String COMMITTEE_TYPE_CODE_FIELD_NAME = "committeeTypeCode";
    private static final String PERSON_NAME = "personName";
    private static final String RESEARCH_AREA_CODE = "researchAreaCode";
    private static final String COMMITTEE_ID = "committeeId";
    private static final String VIEW_ACTIVE = "view active";
    private static final String RESUME_EDIT = "resume edit";
    private static final String COMMITTEE_MEMBERSHIPS_PERSON_NAME = "committeeMemberships.personName";
    private static final String COMMITTEE_RESEARCH_AREAS_RESEARCH_AREA_CODE = "committeeResearchAreas.researchAreaCode";

    private Map<String, String> latestFinalCommitteeMap = new HashMap<>();

    private transient KcAuthorizationService kcAuthorizationService;

    @Override
    public List<CMT> getSearchResults(Map<String, String> fieldValues) {
        // we set the lookup to only list committees of type chosen
        fieldValues.put(COMMITTEE_TYPE_CODE_FIELD_NAME, getCommitteeTypeCodeHook());

        @SuppressWarnings("unchecked")
        final List<CMT> results = (List<CMT>) super.getSearchResultsUnbounded(fieldValues);

        final List<CMT> activeCommittees = getUniqueList(results);
        latestFinalCommitteeMap = getLatestDocumentNumber(results, KewApiConstants.ROUTE_HEADER_FINAL_CD);

        final long matchingResultsCount = (long) activeCommittees.size();
        final Integer searchResultsLimit = LookupUtils.getSearchResultsLimit(getCommitteeBOClassHook());

        return matchingResultsCount <= searchResultsLimit ? activeCommittees :
                new CollectionIncomplete<>(activeCommittees.stream().limit(searchResultsLimit).collect(Collectors.toList()), matchingResultsCount);
    }

    
    protected abstract String getCommitteeTypeCodeHook();

    @Override
    public List<Row> getRows() {
        final List<Row> rows =  super.getRows();
        for (Row row : rows) {
            for (Field field : row.getFields()) {
                if (field.getPropertyName().equals(COMMITTEE_RESEARCH_AREAS_RESEARCH_AREA_CODE)) {
                    super.updateLookupField(field,RESEARCH_AREA_CODE,ResearchArea.class.getName());
                } else if (field.getPropertyName().equals(COMMITTEE_MEMBERSHIPS_PERSON_NAME)) {
                    super.updateLookupField(field,PERSON_NAME, getCommitteeMembershipFullyQualifiedClassNameHook());
                }
            }
        }
        return rows;
    }

    protected abstract String getCommitteeMembershipFullyQualifiedClassNameHook();

    
    /*
     * remove duplicates and get only the one with the highest sequence number from the search results
     */
    protected List<CMT> getUniqueList(List<CMT> committees) {

        final List<CMT> uniqueResults = new ArrayList<>();
        final List<String> committeeIds = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(committees)) {
            Collections.sort(committees, Collections.reverseOrder());
            committees.stream().filter(committee -> !committeeIds.contains(committee.getCommitteeId())).forEach(committee -> {
                committee.getCommitteeChair();
                uniqueResults.add(committee);
                committeeIds.add(committee.getCommitteeId());
            });
        }
        return uniqueResults;
    }

    protected Map<String, String> getLatestDocumentNumber(List<CMT> committees, String routeHeaderCode) {
        return committees.stream()
                .filter(committee -> committee.getCommitteeDocument().getDocStatusCode().equals(routeHeaderCode))
                .collect(Collectors.groupingBy(committee -> committee.getCommitteeId())).entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, value -> value.getValue().stream()
                        .max(Comparator.comparingInt(committee -> committee.getSequenceNumber())).get().getCommitteeDocument().getDocumentNumber()));
    }

    protected abstract String getHtmlAction();
    protected abstract String getDocumentTypeName();
    protected abstract String getCustomResumeEditUrl(final String editCommitteeDocId);
    
    protected String getKeyFieldName() {
        return COMMITTEE_ID;
    }
    
    @Override
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
        final List<HtmlData> htmlDataList = new ArrayList<>();

        if (getKraAuthorizationService().hasPermission(getUserIdentifier(), (CMT) businessObject, getModifyCommitteePermissionNameHook())){
            final String editCommitteeDocId = latestFinalCommitteeMap.get(((CMT) businessObject).getCommitteeId());
            if ((StringUtils.isBlank(editCommitteeDocId) ||
                    (!editCommitteeDocId.equals(((CMT) businessObject).getCommitteeDocument().getDocumentNumber())) && !((CMT) businessObject).getCommitteeDocument().getDocStatusCode().equals(KewApiConstants.ROUTE_HEADER_CANCEL_CD))) {
                final AnchorHtmlData resumeEdit = new AnchorHtmlData(getCustomResumeEditUrl(((CMT) businessObject).getCommitteeDocument().getDocumentNumber()),
                        KRADConstants.DOC_HANDLER_METHOD, KRADConstants.MAINTENANCE_EDIT_METHOD_TO_CALL);
                resumeEdit.setDisplayText(RESUME_EDIT);
                htmlDataList.add(resumeEdit);
            } else {
                htmlDataList.add(getEditLink(businessObject));
            }
        }

        final String finalCommitteeDocId = latestFinalCommitteeMap.get(((CMT) businessObject).getCommitteeId());
        if (StringUtils.isNotBlank(finalCommitteeDocId) && getKraAuthorizationService().hasPermission(getUserIdentifier(), (CMT) businessObject, getViewCommitteePermissionNameHook())) {
            final AnchorHtmlData htmlData = getViewLink(finalCommitteeDocId);
            htmlData.setDisplayText(VIEW_ACTIVE);
            htmlDataList.add(htmlData);
        }
        return htmlDataList;
    }

    protected abstract String getViewCommitteePermissionNameHook();

    protected abstract String getModifyCommitteePermissionNameHook();
    
    protected abstract Class<CMT> getCommitteeBOClassHook();

    protected String getUserIdentifier() {
         return GlobalVariables.getUserSession().getPrincipalId();
    }

    protected KcAuthorizationService getKraAuthorizationService() {
        if (kcAuthorizationService == null) {
            kcAuthorizationService = KcServiceLocator.getService(KcAuthorizationService.class);
        }
        return kcAuthorizationService;
    }

    public void setKcAuthorizationService(KcAuthorizationService kcAuthorizationService) {
        this.kcAuthorizationService = kcAuthorizationService;
    }
}
