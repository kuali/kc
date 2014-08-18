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
package org.kuali.coeus.common.questionnaire.framework.answer;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.krms.KrmsRulesContext;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.Map;


/**
 * This class is intend as a link between modules & questionnaire answer So, Questionnaire answer service can be shared.
 **/
public abstract class ModuleQuestionnaireBean {
    private String moduleItemCode;
    private String moduleSubItemCode;
    private String moduleItemKey;

    private String moduleSubItemKey;
    private boolean finalDoc;

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
        StringBuffer sb = new StringBuffer();
        if (GlobalVariables.getUserSession().retrieveObject(getSessionContextKey() + "-rulereferenced") != null) {
            Map<String, Boolean> ruleResults = (Map<String, Boolean>) GlobalVariables.getUserSession().retrieveObject(
                    getSessionContextKey() + "-rulereferenced");


            for (String key : ruleResults.keySet()) {
                if (StringUtils.isNotBlank(sb.toString())) {
                    sb.append(",");
                }
                sb.append(key).append(":").append(ruleResults.get(key) ? "Y" : "N");
            }
        }
        return sb.toString(); 
    }

    public abstract KrmsRulesContext getKrmsRulesContextFromBean();


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (finalDoc ? 1231 : 1237);
        result = prime * result + ((moduleItemCode == null) ? 0 : moduleItemCode.hashCode());
        result = prime * result + ((moduleItemKey == null) ? 0 : moduleItemKey.hashCode());
        result = prime * result + ((moduleSubItemCode == null) ? 0 : moduleSubItemCode.hashCode());
        result = prime * result + ((moduleSubItemKey == null) ? 0 : moduleSubItemKey.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ModuleQuestionnaireBean other = (ModuleQuestionnaireBean) obj;
        if (finalDoc != other.finalDoc)
            return false;
        if (moduleItemCode == null) {
            if (other.moduleItemCode != null)
                return false;
        }
        else if (!moduleItemCode.equals(other.moduleItemCode))
            return false;
        if (moduleItemKey == null) {
            if (other.moduleItemKey != null)
                return false;
        }
        else if (!moduleItemKey.equals(other.moduleItemKey))
            return false;
        if (moduleSubItemCode == null) {
            if (other.moduleSubItemCode != null)
                return false;
        }
        else if (!moduleSubItemCode.equals(other.moduleSubItemCode))
            return false;
        if (moduleSubItemKey == null) {
            if (other.moduleSubItemKey != null)
                return false;
        }
        else if (!moduleSubItemKey.equals(other.moduleSubItemKey))
            return false;
        return true;
    }

    public String getSessionContextKey() {
        return moduleItemCode + "-" + moduleItemKey + "-" + moduleSubItemCode + "-" + moduleSubItemKey;
    }

}
