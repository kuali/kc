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
package org.kuali.coeus.propdev.impl.s2s;

import javax.persistence.*;

import org.kuali.coeus.propdev.api.s2s.S2sUserAttachedFormFileContract;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

@Entity
@Table(name = "S2S_USER_ATTACHED_FORM_FILE")
public class S2sUserAttachedFormFile extends KcPersistableBusinessObjectBase implements S2sUserAttachedFormFileContract { 

    private static final long serialVersionUID = 379923661813734826L;
    
    @PortableSequenceGenerator(name = "SEQ_S2S_USER_ATTD_FORM_FILE_ID")
    @GeneratedValue(generator = "SEQ_S2S_USER_ATTD_FORM_FILE_ID")
    @Id
    @Column(name = "S2S_USER_ATTACHED_FORM_FILE_ID")
    private Long id;
    
    @Column(name = "S2S_USER_ATTACHED_FORM_ID")
    private Long s2sUserAttachedFormId;

    @Basic(fetch = FetchType.LAZY)
    @Lob
    @Column(name = "FORM_FILE")
    private byte[] formFile;
    
    @Column(name = "XML_FILE")
    private String xmlFile;

    @Override
    public Long getS2sUserAttachedFormId() {
        return s2sUserAttachedFormId;
    }

    public void setS2sUserAttachedFormId(Long s2sUserAttachedFormId) {
        this.s2sUserAttachedFormId = s2sUserAttachedFormId;
    }

    @Override
    public byte[] getFormFile() {
        return formFile;
    }

    public void setFormFile(byte[] formFile) {
        this.formFile = formFile;
    }

    @Override
    public String getXmlFile() {
        return xmlFile;
    }

    public void setXmlFile(String xmlFile) {
        this.xmlFile = xmlFile;
    }

    @Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
  
}