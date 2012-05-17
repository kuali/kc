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
package org.kuali.kra.questionnaire.answer;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.krad.util.GlobalVariables;


/**
 * This class is intend as a link between modules & questionnaire answer So, Questionnaire answer service can be shared.
 **/
public class ModuleQuestionnaireBean {
    private String moduleItemCode;
    private String moduleSubItemCode;
    private String moduleItemKey;

    private String moduleSubItemKey;
    private boolean finalDoc;

    public ModuleQuestionnaireBean() {
        super();
    }

    public ModuleQuestionnaireBean(String moduleItemCode, String moduleItemKey, String moduleSubItemCode, String moduleSubItemKey, boolean finalDoc) {
        this.moduleItemCode = moduleItemCode;
        this.moduleSubItemCode = moduleSubItemCode;
        this.moduleItemKey = moduleItemKey;
        this.moduleSubItemKey = moduleSubItemKey;
        this.finalDoc = finalDoc;
    }

    
    public String getModuleItemCode() {
        return moduleItemCode;
    }

    public void setModuleItemCode(String moduleItemCode) {
        this.moduleItemCode = moduleItemCode;
    }

    public String getModuleItemKey() {
        return moduleItemKey;
    }

    public void setModuleItemKey(String moduleItemKey) {
        this.moduleItemKey = moduleItemKey;
    }

    public String getModuleSubItemKey() {
        return moduleSubItemKey;
    }

    public void setModuleSubItemKey(String moduleSubItemKey) {
        this.moduleSubItemKey = moduleSubItemKey;
    }

    public boolean isFinalDoc() {
        return finalDoc;
    }

    public void setFinalDoc(boolean finalDoc) {
        this.finalDoc = finalDoc;
    }

    public String getModuleSubItemCode() {
        return moduleSubItemCode;
    }

    public void setModuleSubItemCode(String moduleSubItemCode) {
        this.moduleSubItemCode = moduleSubItemCode;
    }

    /**
     * 
     * This method is to concate the rule evaluation results (which are referenced by the questionnaire/question
     * The format is "ruleId:Y", and separate by "," for each rule.  This string will be set as hidden field
     * in page as id = "ruleReferenced"
     * This method will be called by questionnairehelper.
     * @return
     */
    public String getRuleResults() {
        String namespace = null;
        String contextKey = null;
        if (CoeusModule.PROPOSAL_DEVELOPMENT_MODULE_CODE.equals(getModuleItemCode())) {
            namespace = Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT;
            String itemKey = Constants.EMPTY_STRING;
            if (getModuleItemKey().indexOf("|") > 0) {
                itemKey = getModuleItemKey().substring(0, getModuleItemKey().indexOf("|"));
            }
            contextKey = itemKey + "-" + getModuleSubItemKey();
        }
        else {
            namespace = Constants.MODULE_NAMESPACE_PROTOCOL;
            contextKey = getModuleItemKey() + "-" + getModuleSubItemKey();
        }

        StringBuffer sb = new StringBuffer();
        if (GlobalVariables.getUserSession().retrieveObject(namespace + "-" + contextKey + "-rulereferenced") != null) {
            Map<String, Boolean> ruleResults = (Map<String, Boolean>) GlobalVariables.getUserSession().retrieveObject(
                    namespace + "-" + contextKey + "-rulereferenced");


            for (String key : ruleResults.keySet()) {
                if (StringUtils.isNotBlank(sb.toString())) {
                    sb.append(",");
                }
                sb.append(key).append(":").append(ruleResults.get(key) ? "Y" : "N");
            }
        }
        return sb.toString();

    }


}
