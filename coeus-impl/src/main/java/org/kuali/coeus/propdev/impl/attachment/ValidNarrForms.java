/*
 * Copyright 2005-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.coeus.propdev.impl.attachment;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

import javax.persistence.*;

@Entity
@Table(name = "VALID_NARR_FORMS")
public class ValidNarrForms {


    private static final long serialVersionUID = -5530788098530332763L;

    @Id
    @Column(name = "VALID_NARR_FORMS_ID")
    private Integer validNarrFormsId;

    @Column(name = "FORM_NAME")
    private String formName;

    @Column(name = "NARRATIVE_TYPE_CODE")
    private String narrativeTypeCode;

    @Column(name = "MANDATORY")
    private String mandatory;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "NARRATIVE_TYPE_CODE", referencedColumnName = "NARRATIVE_TYPE_CODE", insertable = false, updatable = false)
    private NarrativeType narrativeType;

    public ValidNarrForms() {
    }

    public Integer getValidNarrFormsId() {
        return validNarrFormsId;
    }

    public void setValidNarrFormsId(Integer validNarrFormsId) {
        this.validNarrFormsId = validNarrFormsId;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getNarrativeTypeCode() {
        return narrativeTypeCode;
    }

    public void setNarrativeTypeCode(String narrativeTypeCode) {
        this.narrativeTypeCode = narrativeTypeCode;
    }

    public String getMandatory() {
        return mandatory;
    }

    public void setMandatory(String mandatory) {
        this.mandatory = mandatory;
    }

    /**
     * Sets the narrativeType attribute value.
     * @param narrativeType The narrativeType to set.
     */
    public void setNarrativeType(NarrativeType narrativeType) {
        this.narrativeType = narrativeType;
    }

    /**
     * Gets the narrativeType attribute. 
     * @return Returns the narrativeType.
     */
    public NarrativeType getNarrativeType() {
        return narrativeType;
    }
}
