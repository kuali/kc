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
package org.kuali.coeus.propdev.impl.s2s.question;


import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

public class S2sOppFormQuestionnaire extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = -2249378225351572499L;

    private Long s2sOppFormQuestionnaireId;

    private String oppNameSpace;

    private String formName;

    private Long questionnaireId;

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
     * @param s2sOppFormQuestionnaireId The s2SOppFormQuestionnaireMapId to set.
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
    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    /**
     * Sets the questionnaireId attribute value.
     * @param questionnaireId The questionnaireId to set.
     */
    public void setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
    }
}
