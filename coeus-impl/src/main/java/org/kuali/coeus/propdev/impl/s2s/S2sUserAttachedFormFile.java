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

    @Lob
    @Column(name = "FORM_FILE")
    private byte[] formFile;
    
    @Column(name = "XML_FILE")
    private String xmlFile;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "S2S_USER_ATTACHED_FORM_ID")
    private S2sUserAttachedForm s2sUserAttachedForm;

    @Override
    public Long getS2sUserAttachedFormId() {
        return s2sUserAttachedForm != null ? s2sUserAttachedForm.getId() : null;
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

    public S2sUserAttachedForm getS2sUserAttachedForm() {
        return s2sUserAttachedForm;
    }

    public void setS2sUserAttachedForm(S2sUserAttachedForm s2sUserAttachedForm) {
        this.s2sUserAttachedForm = s2sUserAttachedForm;
    }
}
