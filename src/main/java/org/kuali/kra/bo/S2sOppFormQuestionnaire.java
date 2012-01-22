/*
 * Copyright 2005-2010 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the License);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.bo;


public class S2sOppFormQuestionnaire extends KraPersistableBusinessObjectBase {

    private static final long serialVersionUID = -2249378225351572499L;

    private Long s2sOppFormQuestionnaireId;

    private String oppNameSpace;

    private String formName;

    private Integer questionnaireId;

    public static final String OPP_NAMESPACE_FIELD = "oppNameSpace";

    public static final String FORM_NAME_FIELD = "formName";

    /**
     * Gets the s2SOppFormQuestionnaireMapId attribute. 
     * @return Returns the s2SOppFormQuestionnaireMapId.
     */
    public Long getS2sOppFormQuestionnaireId() {
        return s2sOppFormQuestionnaireId;
    }

    /**
     * Sets the s2SOppFormQuestionnaireMapId attribute value.
     * @param s2sOppFormQuestionnaireMapId The s2SOppFormQuestionnaireMapId to set.
     */
    public void setS2sOppFormQuestionnaireId(Long s2sOppFormQuestionnaireId) {
        this.s2sOppFormQuestionnaireId = s2sOppFormQuestionnaireId;
    }

    /**
     * Gets the oppNameSpace attribute. 
     * @return Returns the oppNameSpace.
     */
    public String getOppNameSpace() {
        return oppNameSpace;
    }

    /**
     * Sets the oppNameSpace attribute value.
     * @param oppNameSpace The oppNameSpace to set.
     */
    public void setOppNameSpace(String oppNameSpace) {
        this.oppNameSpace = oppNameSpace;
    }

    /**
     * Gets the formName attribute. 
     * @return Returns the formName.
     */
    public String getFormName() {
        return formName;
    }

    /**
     * Sets the formName attribute value.
     * @param formName The formName to set.
     */
    public void setFormName(String formName) {
        this.formName = formName;
    }

    /**
     * Gets the questionnaireId attribute. 
     * @return Returns the questionnaireId.
     */
    public Integer getQuestionnaireId() {
        return questionnaireId;
    }

    /**
     * Sets the questionnaireId attribute value.
     * @param questionnaireId The questionnaireId to set.
     */
    public void setQuestionnaireId(Integer questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public S2sOppFormQuestionnaire() {
        super();
    }
}
