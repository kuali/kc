/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.questionnaire;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.rice.kew.doctype.bo.DocumentType;
import org.kuali.rice.kew.routeheader.DocumentRouteHeaderValue;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kew.util.KEWPropertyConstants;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.util.KNSConstants;

import edu.emory.mathcs.backport.java.util.Collections;

public class QuestionnaireLookupableHelperServiceImpl extends KualiLookupableHelperServiceImpl {
    private static final String VIEW = "view";
    private static final String DOCHANDLER_VIEW_LINK = "../en/DocHandler.do?command=displayDocSearchView&readOnly=true&docId=";
    private static final String MAINTENANCE = "maintenance";
    private static final String NEW_MAINTENANCE = "../maintenanceQn";
    private static final String DOC_ROUTE_STATUS = "docRouteStatus";
    private static final String QUESTIONNAIRE_ID = "questionnaireId";
    private DocumentService documentService;
    private QuestionnaireAuthorizationService questionnaireAuthorizationService;
    private List<Integer> questionnaireMaintenanceDocs;

    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        questionnaireMaintenanceDocs = getQuestionnaireDocs();
        List<? extends BusinessObject> searchResults = super.getSearchResults(fieldValues);
        if (CollectionUtils.isNotEmpty(searchResults)) {
            Collections.sort((List<Questionnaire>) searchResults);
            Collections.reverse((List<Questionnaire>) searchResults);
        }
        return searchResults;
    }

    // TODO : Maybe we need a versioning history for Questionnaire, so we don't have to do this.
    private Questionnaire getQuestionnaireById(Integer questionnaireId) {
        Questionnaire questionnaire = null;
        if (questionnaireId != null) {
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put(QUESTIONNAIRE_ID, questionnaireId);
            Collection<Questionnaire> questionnaires = getBusinessObjectService().findMatching(
                    Questionnaire.class, fieldValues);
            if (questionnaires.size() > 0) {
                questionnaire = (Questionnaire) Collections.max(questionnaires);
            }
        }
        return questionnaire;
    }


    @Override
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
        List<HtmlData> htmlDataList = new ArrayList<HtmlData>();
        Questionnaire questionnaire = getQuestionnaireById(((Questionnaire) businessObject).getQuestionnaireId());
        boolean hasModifyPermission = questionnaireAuthorizationService.hasPermission(PermissionConstants.MODIFY_QUESTIONNAIRE);
        boolean hasViewPermission = hasModifyPermission
                || questionnaireAuthorizationService.hasPermission(PermissionConstants.VIEW_QUESTIONNAIRE);
        if (!questionnaire.getQuestionnaireRefId().equals(((Questionnaire) businessObject).getQuestionnaireRefId())) {
            if (hasViewPermission) {
                htmlDataList.add(getViewLink(businessObject));
            }
        }
        else {
            if (hasModifyPermission && (CollectionUtils.isEmpty(questionnaireMaintenanceDocs) || !questionnaireMaintenanceDocs.contains(questionnaire.getQuestionnaireId()))) {
                htmlDataList.add(getHtmlData(businessObject, KNSConstants.MAINTENANCE_EDIT_METHOD_TO_CALL, pkNames));
            }
            if (hasViewPermission) {
                htmlDataList.add(getViewLink(businessObject));
            }
        }
        if (hasModifyPermission) {
            htmlDataList.add(getHtmlData(businessObject, KNSConstants.MAINTENANCE_COPY_METHOD_TO_CALL, pkNames));
        }
        return htmlDataList;
    }

    private AnchorHtmlData getViewLink(BusinessObject businessObject) {
        AnchorHtmlData htmlData = new AnchorHtmlData();
        htmlData.setHref(DOCHANDLER_VIEW_LINK
                + ((Questionnaire) businessObject).getDocumentNumber());
        htmlData.setDisplayText(VIEW);
        return htmlData;        
    }
        
    private AnchorHtmlData getHtmlData(BusinessObject businessObject, String methodToCall, List pkNames) {
        AnchorHtmlData htmlData = getUrlData(businessObject, methodToCall, pkNames);
        htmlData.setHref(htmlData.getHref().replace(MAINTENANCE, NEW_MAINTENANCE));
        return htmlData;        
    }
        
    /*
     * This method is to get questionnaire doc that are saved but not approved yet list.
     * If questionnaire is being edited, then it should not allow 'edit' until this is approved or cancelled 
     * Call this method one time for each search because this list maybe changed from search to search.
     */
    private List<Integer> getQuestionnaireDocs() {
        List<Integer> questionnaireDocs = new ArrayList<Integer>();
        Map<String, String> fieldValues = new HashMap<String, String>();

        fieldValues.put(KEWPropertyConstants.NAME, getMaintenanceDocumentDictionaryService().getDocumentTypeName(Questionnaire.class));
        DocumentType docType = ((List<DocumentType>) getBusinessObjectService().findMatching(DocumentType.class, fieldValues))
                .get(0);
        fieldValues.clear();
        fieldValues.put(KEWPropertyConstants.DOCUMENT_TYPE_ID, docType.getDocumentTypeId().toString());
        fieldValues.put(DOC_ROUTE_STATUS, KEWConstants.ROUTE_HEADER_SAVED_CD);
        List<DocumentRouteHeaderValue> docHeaders = (List<DocumentRouteHeaderValue>) getBusinessObjectService().findMatching(
                DocumentRouteHeaderValue.class, fieldValues);
        try {
            for (DocumentRouteHeaderValue docHeader : docHeaders) {
                MaintenanceDocumentBase doc = (MaintenanceDocumentBase) documentService.getByDocumentHeaderId(docHeader
                        .getRouteHeaderId().toString());
                if (doc.getNewMaintainableObject().getMaintenanceAction().equals(KNSConstants.MAINTENANCE_EDIT_ACTION)) {
                    questionnaireDocs.add(((Questionnaire) doc.getNewMaintainableObject().getBusinessObject()).getQuestionnaireId());
                }
            }
        }
        catch (Exception e) {

        }
        return questionnaireDocs;
    }

    public void setQuestionnaireAuthorizationService(QuestionnaireAuthorizationService questionnaireAuthorizationService) {
        this.questionnaireAuthorizationService = questionnaireAuthorizationService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

}
