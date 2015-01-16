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
package org.kuali.coeus.common.framework.person.attr;

import java.io.Serializable;

import org.kuali.coeus.common.framework.custom.DocumentCustomData;
import org.kuali.coeus.common.framework.custom.attr.CustomAttribute;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

import javax.persistence.*;

@Entity
@Table(name = "PERSON_CUSTOM_DATA")
public class PersonCustomData extends KcPersistableBusinessObjectBase implements DocumentCustomData, Serializable {

    private static final long serialVersionUID = 7498061394015743173L;

    @PortableSequenceGenerator(name = "SEQ_PERSON_CUSTOM_DATA_ID")
    @GeneratedValue(generator = "SEQ_PERSON_CUSTOM_DATA_ID")
    @Id
    @Column(name = "PERSON_CUSTOM_DATA_ID")
    private Long personCustomDataId;

    @Column(name = "PERSON_ID")
    private String personId;

    @Column(name = "CUSTOM_ATTRIBUTE_ID")
    private Long customAttributeId;

    @Column(name = "VALUE")
    private String value;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "CUSTOM_ATTRIBUTE_ID", referencedColumnName = "CUSTOM_ATTRIBUTE_ID", insertable = false, updatable = false)
    private CustomAttribute customAttribute;

    public Long getPersonCustomDataId() {
        return personCustomDataId;
    }

    // putting in getter for "id" since custom data jsp/tag files are expecting it
    public Long getId() {
        return customAttributeId;
    }

    public void setPersonCustomDataId(Long personCustomDataId) {
        this.personCustomDataId = personCustomDataId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public Long getCustomAttributeId() {
        return customAttributeId;
    }

    public void setCustomAttributeId(Long customAttributeId) {
        this.customAttributeId = customAttributeId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public CustomAttribute getCustomAttribute() {
        return customAttribute;
    }

    public void setCustomAttribute(CustomAttribute customAttribute) {
        this.customAttribute = customAttribute;
    }
    
}