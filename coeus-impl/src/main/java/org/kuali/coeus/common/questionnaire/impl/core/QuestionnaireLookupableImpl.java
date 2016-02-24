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
    @Qualifier("questionnaireLookupableHelperService")
    @Override
    public void setLookupableHelperService(LookupableHelperService lookupableHelperService) {
        super.setLookupableHelperService(lookupableHelperService);
    }
}
