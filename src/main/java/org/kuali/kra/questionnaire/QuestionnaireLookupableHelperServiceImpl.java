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
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.util.KNSConstants;

import edu.emory.mathcs.backport.java.util.Collections;

public class QuestionnaireLookupableHelperServiceImpl extends KualiLookupableHelperServiceImpl {
    private DocumentService documentService;

//    @Override
//    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
//        List<? extends BusinessObject> searchResults = super.getSearchResults(fieldValues);
//        List<Questionnaire> activeQuestionnaires = new ArrayList<Questionnaire>();
//        List<Integer> questionnaireIds = new ArrayList<Integer>();
//        if (CollectionUtils.isNotEmpty(searchResults)) {
//            Collections.sort((List<Questionnaire>) searchResults);
//            Collections.reverse((List<Questionnaire>) searchResults);
//            // Collections.sort((List<Questionnaire>) searchResults, Collections.reverseOrder());
//            for (Questionnaire questionnaire : (List<Questionnaire>) searchResults) {
//                if (!questionnaireIds.contains(questionnaire.getQuestionnaireId()) && isFinal(questionnaire)) {
//                    activeQuestionnaires.add(questionnaire);
//                    questionnaireIds.add(questionnaire.getQuestionnaireId());
//                }
//            }
//        }
//        return activeQuestionnaires;
//    }

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
        Questionnaire questionnaire = null;
        if (questionnaireId != null) {
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put("QUESTIONNAIRE_ID", questionnaireId);
            // TODO : inject businessobjectservice
            Collection<Questionnaire> questionnaires = KraServiceLocator.getService(BusinessObjectService.class).findMatching(Questionnaire.class, fieldValues);
            if (questionnaires.size() > 0) {
                questionnaire = (Questionnaire) Collections.max(questionnaires);
            }
        }
        return questionnaire;
    }


    @Override
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
        List<HtmlData> htmlDataList = new ArrayList<HtmlData>();
        Questionnaire questionnaire = getQuestionnaireById(((Questionnaire)businessObject).getQuestionnaireId());
        AnchorHtmlData htmlData = getUrlData(businessObject, KNSConstants.MAINTENANCE_EDIT_METHOD_TO_CALL, pkNames);
        htmlData.setHref(htmlData.getHref().replace("maintenance", "../maintenanceQn"));
        if (!questionnaire.getQuestionnaireRefId().equals(((Questionnaire)businessObject).getQuestionnaireRefId())) {
            htmlData.setHref("../en/DocHandler.do?command=displayDocSearchView&readOnly=true&docId="+((Questionnaire)businessObject).getDocumentNumber());
            htmlData.setDisplayText("view");
        } else {
            AnchorHtmlData htmlData2 = getUrlData(businessObject, KNSConstants.MAINTENANCE_EDIT_METHOD_TO_CALL, pkNames);
            htmlData2.setHref("../en/DocHandler.do?command=displayDocSearchView&readOnly=true&docId="+((Questionnaire)businessObject).getDocumentNumber());
            htmlData2.setDisplayText("view");
            htmlDataList.add(htmlData2);
        }
        htmlDataList.add(htmlData);
        AnchorHtmlData htmlData1 = getUrlData(businessObject, KNSConstants.MAINTENANCE_COPY_METHOD_TO_CALL, pkNames);
        htmlData1.setHref(htmlData1.getHref().replace("maintenance", "../maintenanceQn"));
        htmlDataList.add(htmlData1);
        return htmlDataList;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }


}
