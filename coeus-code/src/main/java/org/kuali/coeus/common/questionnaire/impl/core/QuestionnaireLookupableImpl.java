/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.questionnaire.impl.core;

import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireAuthorizationService;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.rice.kns.lookup.KualiLookupableImpl;
import org.kuali.rice.kns.lookup.LookupableHelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 
 * This class is to override 'create new' url.  MODIFY_QUESTIONNAIRE permission is need for this url. 
 */
@Component("questionnaireLookupable")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class QuestionnaireLookupableImpl extends KualiLookupableImpl {

    @Autowired
    @Qualifier("questionnaireAuthorizationService")
    private QuestionnaireAuthorizationService questionnaireAuthorizationService;

    /**
     * new url is redirect to "maintenanceQn", and also need to have MODIFY_QUESTIONNAIRE permission. 
     * @see org.kuali.rice.kns.lookup.KualiLookupableImpl#getCreateNewUrl()
     */
    @Override
    public String getCreateNewUrl() {
        String url = "";
        if (questionnaireAuthorizationService.hasPermission(PermissionConstants.MODIFY_QUESTIONNAIRE)) {
            url = super.getCreateNewUrl();
            url = url.replace("maintenance", "../maintenanceQn");
        }
        return url;
    }

    public void setQuestionnaireAuthorizationService(QuestionnaireAuthorizationService questionnaireAuthorizationService) {
        this.questionnaireAuthorizationService = questionnaireAuthorizationService;
    }

    public QuestionnaireAuthorizationService getQuestionnaireAuthorizationService() {
        return questionnaireAuthorizationService;
    }
    
    @Autowired
    @Qualifier("lookupableHelperService")
    @Override
    public void setLookupableHelperService(LookupableHelperService lookupableHelperService) {
        super.setLookupableHelperService(lookupableHelperService);
    }
}
