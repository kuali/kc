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
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.rice.shim.UniversalUser;
import org.kuali.rice.kew.doctype.bo.DocumentType;
import org.kuali.rice.kew.routeheader.DocumentRouteHeaderValue;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;

import edu.emory.mathcs.backport.java.util.Collections;

public class QuestionnaireLookupableHelperServiceImpl extends KualiLookupableHelperServiceImpl {
    private DocumentService documentService;
    private QuestionnaireAuthorizationService questionnaireAuthorizationService;
    private List<Integer> qnMaintDocs;

    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        qnMaintDocs = getQuestionnaireDocs();
        List<? extends BusinessObject> searchResults = super.getSearchResults(fieldValues);
        List<Questionnaire> activeQuestionnaires = new ArrayList<Questionnaire>();
        List<Integer> questionnaireIds = new ArrayList<Integer>();
        if (CollectionUtils.isNotEmpty(searchResults)) {
            Collections.sort((List<Questionnaire>) searchResults);
            Collections.reverse((List<Questionnaire>) searchResults);
            // Collections.sort((List<Questionnaire>) searchResults, Collections.reverseOrder());
            for (Questionnaire questionnaire : (List<Questionnaire>) searchResults) {
                if (isFinal(questionnaire)) {
                    // if (!questionnaireIds.contains(questionnaire.getQuestionnaireId()) && isFinal(questionnaire)) {
                    activeQuestionnaires.add(questionnaire);
                    // questionnaireIds.add(questionnaire.getQuestionnaireId());
                }
            }
        }
        return activeQuestionnaires;
    }

    private boolean isFinal(Questionnaire questionnaire) {
        boolean isFinal = true;
        if (questionnaire.getDocumentNumber() == null) {
            isFinal = false;
        }
        else {
            try {
                isFinal = documentService.getByDocumentHeaderId(questionnaire.getDocumentNumber()).getDocumentHeader()
                        .getWorkflowDocument().getRouteHeader().getDocRouteStatus().equals("F");
            }
            catch (Exception e) {

            }
        }
        return isFinal;
    }

    // TODO : Maybe we need a versioninghistory for Questionnaire, so we don't have to do this.
    private Questionnaire getQuestionnaireById(Integer questionnaireId) {
        Questionnaire qnaire = null;
        if (questionnaireId != null) {
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put("QUESTIONNAIRE_ID", questionnaireId);
            // TODO : inject businessobjectservice
            Collection<Questionnaire> questionnaires = KraServiceLocator.getService(BusinessObjectService.class).findMatching(
                    Questionnaire.class, fieldValues);
            if (questionnaires.size() > 0) {
                qnaire = (Questionnaire) Collections.max(questionnaires);
            }
            // Collections.sort((List<Questionnaire>) questionnaires);
            // Collections.reverse((List<Questionnaire>) questionnaires);
            // for (Questionnaire questionnaire : (List<Questionnaire>) questionnaires) {
            // if (isFinal(questionnaire)) {
            // qnaire = questionnaire;
            // break;
            // }
            // }
        }
        return qnaire;
    }


    @Override
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
        List<HtmlData> htmlDataList = new ArrayList<HtmlData>();
        Questionnaire questionnaire = getQuestionnaireById(((Questionnaire) businessObject).getQuestionnaireId());
        AnchorHtmlData htmlData = getUrlData(businessObject, KNSConstants.MAINTENANCE_EDIT_METHOD_TO_CALL, pkNames);
        htmlData.setHref(htmlData.getHref().replace("maintenance", "../maintenanceQn"));
        // this method does not work because kim_roles_persons_t is empty
        boolean hasModifyPermission = questionnaireAuthorizationService.hasPermission(PermissionConstants.MODIFY_QUESTIONNAIRE);
        boolean hasViewPermission = hasModifyPermission
                || questionnaireAuthorizationService.hasPermission(PermissionConstants.VIEW_QUESTIONNAIRE);
        // RoleConstants.IRB_ADMINISTRATOR);
        // boolean hadIrbAdminRole = hasRole(RoleConstants.IRB_ADMINISTRATOR);
        if (!questionnaire.getQuestionnaireRefId().equals(((Questionnaire) businessObject).getQuestionnaireRefId())) {
            // if (!questionnaire.getQuestionnaireRefId().equals(((Questionnaire) businessObject).getQuestionnaireRefId())) {
            if (hasViewPermission) {
                htmlData.setHref("../en/DocHandler.do?command=displayDocSearchView&readOnly=true&docId="
                        + ((Questionnaire) businessObject).getDocumentNumber());
                htmlData.setDisplayText("view");
                htmlDataList.add(htmlData);
            }
        }
        else {
            if (hasModifyPermission && !isQuestionnaireBeingEdited(questionnaire)) {
                htmlDataList.add(htmlData);
            }
            if (hasViewPermission) {
                AnchorHtmlData htmlData2 = getUrlData(businessObject, KNSConstants.MAINTENANCE_EDIT_METHOD_TO_CALL, pkNames);
                htmlData2.setHref("../en/DocHandler.do?command=displayDocSearchView&readOnly=true&docId="
                        + ((Questionnaire) businessObject).getDocumentNumber());
                htmlData2.setDisplayText("view");
                htmlDataList.add(htmlData2);
            }
        }
        if (hasModifyPermission) {
            AnchorHtmlData htmlData1 = getUrlData(businessObject, KNSConstants.MAINTENANCE_COPY_METHOD_TO_CALL, pkNames);
            htmlData1.setHref(htmlData1.getHref().replace("maintenance", "../maintenanceQn"));
            htmlDataList.add(htmlData1);
        }
        return htmlDataList;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    private String getUserName() {
        UniversalUser user = new UniversalUser(GlobalVariables.getUserSession().getPerson());
        return user.getPersonUserIdentifier();
    }

    /*
     * This method is to get questionnaire doc that are saved but not approved yet list.
     * If questionnaire is being edited, then it should not allow 'edit' until this is approved or cancelled 
     * Call this method one time for each search because this list maybe changed from search to search.
     */
    private List<Integer> getQuestionnaireDocs() {
        List<Integer> questionnaireDocs = new ArrayList<Integer>();
        Map fieldValues = new HashMap();
        DocumentService documentService = KraServiceLocator.getService(DocumentService.class);

        //fieldValues.put("name", "QuestionnaireMaintenanceDocument");
        fieldValues.put("name", getMaintenanceDocumentDictionaryService().getDocumentTypeName(Questionnaire.class));
        DocumentType docType = ((List<DocumentType>) getBusinessObjectService().findMatching(DocumentType.class, fieldValues))
                .get(0);
        fieldValues.clear();
        fieldValues.put("documentTypeId", docType.getDocumentTypeId());
        fieldValues.put("docRouteStatus", KEWConstants.ROUTE_HEADER_SAVED_CD);
        List<DocumentRouteHeaderValue> docHeaders = (List<DocumentRouteHeaderValue>) getBusinessObjectService().findMatching(
                DocumentRouteHeaderValue.class, fieldValues);
        try {
            for (DocumentRouteHeaderValue docHeader : docHeaders) {
                MaintenanceDocumentBase doc = (MaintenanceDocumentBase) documentService.getByDocumentHeaderId(docHeader
                        .getRouteHeaderId().toString());
                questionnaireDocs.add(((Questionnaire) doc.getNewMaintainableObject().getBusinessObject()).getQuestionnaireId());
            }
        }
        catch (Exception e) {

        }
        return questionnaireDocs;
    }

    /*
     * check if the active questionnaire is being edited.
     */
    private boolean isQuestionnaireBeingEdited(Questionnaire questionnaire) {
        boolean isEditing = false;
        for (Integer questionnaireId : qnMaintDocs) {
            if (questionnaireId.equals(questionnaire.getQuestionnaireId())) {
                isEditing = true;
                break;
            }
        }
        return isEditing;
    }

    public void setQuestionnaireAuthorizationService(QuestionnaireAuthorizationService questionnaireAuthorizationService) {
        this.questionnaireAuthorizationService = questionnaireAuthorizationService;
    }

}
