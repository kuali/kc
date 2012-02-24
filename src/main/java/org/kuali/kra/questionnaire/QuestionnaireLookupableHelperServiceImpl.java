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
package org.kuali.kra.questionnaire;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.rice.kew.api.KEWPropertyConstants;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.doctype.bo.DocumentType;
import org.kuali.rice.kew.routeheader.DocumentRouteHeaderValue;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.KRADConstants;

/**
 * 
 * This class is mainly to override edit/copy action urls and create 'view' url.
 * Also, sort search results.
 */
public class QuestionnaireLookupableHelperServiceImpl extends KualiLookupableHelperServiceImpl {

    private static final long serialVersionUID = 1800678175555048310L;
    private static final String VIEW = "view";
    private static final String DOCHANDLER_LINK = "%s/DocHandler.do?command=displayDocSearchView&docId=%s";
    private static final String MAINTENANCE = "maintenance";
    private static final String NEW_MAINTENANCE = "../maintenanceQn";
    private static final String DOC_ROUTE_STATUS = "docRouteStatus";
    private static final String QUESTIONNAIRE_ID = "questionnaireId";
    private DocumentService documentService;
    private QuestionnaireAuthorizationService questionnaireAuthorizationService;
    private List<MaintenanceDocumentBase> questionnaireMaintenanceDocs;
    private List<MaintenanceDocumentBase> newQuestionnaireDocs;
    private List<String> questionnaireIds;
    private String isActive;

    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        isActive = fieldValues.get("isFinal");
        getQuestionnaireDocs();
        List<? extends BusinessObject> searchResults = super.getSearchResults(fieldValues);
        if (CollectionUtils.isNotEmpty(searchResults)) {
            Collections.sort((List<Questionnaire>) searchResults);
            Collections.reverse((List<Questionnaire>) searchResults);
        }
        return getCurrentVersionQuestionnaires(searchResults);
    }

    /*
     * only get the current version for search results
     */
    private List<? extends BusinessObject> getCurrentVersionQuestionnaires(List<? extends BusinessObject> searchResults) {
        List<Questionnaire> questionnaires = new ArrayList<Questionnaire>();
        int qId = 0;
        for (BusinessObject questionnaire : searchResults) {
            if (qId != ((Questionnaire) questionnaire).getQuestionnaireIdAsInteger()) {
                qId = ((Questionnaire) questionnaire).getQuestionnaireIdAsInteger();
                if (isCurrent((Questionnaire) questionnaire)) {
                    questionnaires.add((Questionnaire) questionnaire);
                }
            }
        }
        for (MaintenanceDocumentBase doc : newQuestionnaireDocs) {
            Questionnaire questionnaire = (Questionnaire) doc.getNewMaintainableObject().getDataObject();
            questionnaires.add(questionnaire);
        }

        return questionnaires;

    }

    /*
     * the criteria may filter out the current version of questionnaire, so has to make sure the
     * search results is indeed the current version.
     */
    private boolean isCurrent(Questionnaire questionnaire) {
        Questionnaire currentQnaire = getQuestionnaireById(questionnaire.getQuestionnaireId());
        return questionnaire.getQuestionnaireRefId().equals(currentQnaire.getQuestionnaireRefId());
    }
    
    // TODO : Maybe we need a versioning history for Questionnaire, so we don't have to do this.
    protected Questionnaire getQuestionnaireById(String questionnaireId) {
        Questionnaire questionnaire = null;
        if (questionnaireId != null) {
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put(QUESTIONNAIRE_ID, questionnaireId);
            Collection<Questionnaire> questionnaires = getBusinessObjectService().findMatching(Questionnaire.class, fieldValues);
            if (questionnaires.size() > 0) {
                questionnaire = (Questionnaire) Collections.max(questionnaires);
            }
        }
        return questionnaire;
    }


    /**
     * override edit/copy link and new 'view' link based on permission.
     * @see org.kuali.rice.kns.lookup.AbstractLookupableHelperServiceImpl#getCustomActionUrls(org.kuali.rice.krad.bo.BusinessObject, java.util.List)
     */
    @Override
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
        List<HtmlData> htmlDataList = new ArrayList<HtmlData>();
        Questionnaire questionnaire = (Questionnaire) businessObject;
        boolean hasModifyPermission = questionnaireAuthorizationService.hasPermission(PermissionConstants.MODIFY_QUESTIONNAIRE);
        boolean hasViewPermission = hasModifyPermission
                || questionnaireAuthorizationService.hasPermission(PermissionConstants.VIEW_QUESTIONNAIRE);
        if (hasModifyPermission && questionnaire.getQuestionnaireId() != null
                && (CollectionUtils.isEmpty(questionnaireIds) || !questionnaireIds.contains(questionnaire.getQuestionnaireId()))) {
            htmlDataList.add(getHtmlData(businessObject, KRADConstants.MAINTENANCE_EDIT_METHOD_TO_CALL, pkNames));
        }
        if (hasModifyPermission
                && (questionnaire.getQuestionnaireId() == null || (!CollectionUtils.isEmpty(questionnaireIds) && questionnaireIds
                        .contains(questionnaire.getQuestionnaireId())))) {
            AnchorHtmlData htmlData = (AnchorHtmlData) getHtmlData(businessObject, KRADConstants.MAINTENANCE_EDIT_METHOD_TO_CALL,
                    pkNames);
            String workflowUrl = getKualiConfigurationService().getPropertyValueAsString(KRADConstants.WORKFLOW_URL_KEY);
            htmlData.setHref(String.format(DOCHANDLER_LINK, workflowUrl, getDocumentNumber(questionnaire)));
            htmlData.setDisplayText("resume edit");
            htmlDataList.add(htmlData);
        }
        if (hasViewPermission && questionnaire.getQuestionnaireId() != null) {
            htmlDataList.add(getViewLink(businessObject, pkNames));
        }
        if (hasModifyPermission && questionnaire.getQuestionnaireId() != null) {
            htmlDataList.add(getHtmlData(businessObject, KRADConstants.MAINTENANCE_COPY_METHOD_TO_CALL, pkNames));
            
            htmlDataList.add(getHtmlData(businessObject, KRADConstants.MAINTENANCE_DELETE_METHOD_TO_CALL, pkNames));
        }
        return htmlDataList;
    }

    /*
     * for new questionnaire, documentnumber is from questionaire
     * for 'edit', documentnumber should be from the saved maintenance doc
     */
    private String getDocumentNumber (Questionnaire questionnaire) {
        String docNumber = null;
        if (questionnaire.getQuestionnaireId() == null) {
            docNumber = questionnaire.getDocumentNumber();
        } else {
            for (MaintenanceDocumentBase doc : questionnaireMaintenanceDocs) {
                if (((Questionnaire) doc.getNewMaintainableObject().getDataObject()).getQuestionnaireId().equals(questionnaire.getQuestionnaireId())) {
                    docNumber = doc.getDocumentNumber();
                }
            }
        }
        return docNumber;
    }
    
    protected AnchorHtmlData getViewLink(BusinessObject businessObject, List pkNames) {
        AnchorHtmlData htmlData = new AnchorHtmlData();
        Questionnaire questionnaire = (Questionnaire) businessObject;
        if (StringUtils.isNotBlank(questionnaire.getDocumentNumber())) {
            String workflowUrl = getKualiConfigurationService().getPropertyValueAsString(KRADConstants.WORKFLOW_URL_KEY);
            htmlData.setHref(String.format(DOCHANDLER_LINK, workflowUrl, questionnaire.getDocumentNumber()).replace("&docId",
                    "&readOnly=true&docId"));
        }
        else {
            htmlData = getUrlData(businessObject, KRADConstants.MAINTENANCE_EDIT_METHOD_TO_CALL, pkNames);
            htmlData.setHref(htmlData.getHref().replace(MAINTENANCE, NEW_MAINTENANCE) + "&readOnly=true");

        }
        htmlData.setDisplayText(VIEW);
        return htmlData;
    }
        
    protected AnchorHtmlData getHtmlData(BusinessObject businessObject, String methodToCall, List pkNames) {
        AnchorHtmlData htmlData = getUrlData(businessObject, methodToCall, pkNames);
        htmlData.setHref(htmlData.getHref().replace(MAINTENANCE, NEW_MAINTENANCE));
        return htmlData;        
    }
        
    /*
     * This method is to get questionnaire doc that are saved but not approved yet list.
     * If questionnaire is being edited, then it should not allow 'edit' until this is approved or cancelled 
     * Call this method one time for each search because this list maybe changed from search to search.
     */
    protected void getQuestionnaireDocs() {
        questionnaireMaintenanceDocs = new ArrayList<MaintenanceDocumentBase>();
        newQuestionnaireDocs = new ArrayList<MaintenanceDocumentBase>();
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        questionnaireIds = new ArrayList<String>();

        fieldValues.put(KEWPropertyConstants.NAME, getMaintenanceDocumentDictionaryService().getDocumentTypeName(
                Questionnaire.class));
        List<String> docTypeIds = new ArrayList<String>();
        for (DocumentType docType : (List<DocumentType>) getBusinessObjectService().findMatching(DocumentType.class, fieldValues)) {
            docTypeIds.add(docType.getDocumentTypeId());
        }

        fieldValues.clear();
        fieldValues.put(KEWPropertyConstants.DOCUMENT_TYPE_ID, docTypeIds);
        fieldValues.put(DOC_ROUTE_STATUS, KewApiConstants.ROUTE_HEADER_SAVED_CD);
        List<DocumentRouteHeaderValue> docHeaders = (List<DocumentRouteHeaderValue>) getBusinessObjectService().findMatching(
                DocumentRouteHeaderValue.class, fieldValues);
        try {
            for (DocumentRouteHeaderValue docHeader : docHeaders) {
                MaintenanceDocumentBase doc = (MaintenanceDocumentBase) documentService.getByDocumentHeaderId(docHeader
                        .getDocumentId().toString());
                if (doc.getNewMaintainableObject().getMaintenanceAction().equals(KRADConstants.MAINTENANCE_EDIT_ACTION)) {
                    questionnaireIds.add(((Questionnaire) doc.getNewMaintainableObject().getDataObject()).getQuestionnaireId());
                    questionnaireMaintenanceDocs.add(doc);
                } else if (doc.getNewMaintainableObject().getMaintenanceAction().equals(KRADConstants.MAINTENANCE_NEW_ACTION)) {
                    // new questionnaire which is not approved yet.
                    Questionnaire questionnaire = (Questionnaire) doc.getNewMaintainableObject().getDataObject();
                    if (StringUtils.isBlank(isActive)
                            || (KRADConstants.NO_INDICATOR_VALUE.equals(isActive) && !questionnaire.getIsFinal())
                            || (KRADConstants.YES_INDICATOR_VALUE.equals(isActive) && questionnaire.getIsFinal())) {
                        newQuestionnaireDocs.add(doc);
                    }
                }
            }
        }
        catch (Exception e) {

        }
    }

    public void setQuestionnaireAuthorizationService(QuestionnaireAuthorizationService questionnaireAuthorizationService) {
        this.questionnaireAuthorizationService = questionnaireAuthorizationService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

}
