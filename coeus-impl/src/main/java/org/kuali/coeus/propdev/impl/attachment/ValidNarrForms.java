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
package org.kuali.coeus.propdev.impl.attachment;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

import javax.persistence.*;

@Entity
@Table(name = "VALID_NARR_FORMS")
public class ValidNarrForms extends KcPersistableBusinessObjectBase{


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
