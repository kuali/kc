/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.bo;

import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.IdClass;

import java.util.LinkedHashMap;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerException;
import org.apache.struts.upload.FormFile;
import org.kuali.core.bo.PersistableAttachment;

@IdClass(org.kuali.kra.bo.id.SponsorFormTemplateId.class)
@Entity
@Table(name="SPONSOR_FORM_TEMPLATES")
public class SponsorFormTemplate extends AbstractSponsorFormTemplate implements PersistableAttachment{
    @Column(name="FILE_NAME")
	private String fileName;
    @Column(name="CONTENT_TYPE")
	private String contentType;
    @Column(name="FORM_TEMPLATE")
	private byte[] attachmentContent;
    @Transient
    private FormFile templateFile;
    @Transient
    private Boolean selectToPrint = false;

    public byte[] getAttachmentContent() {
        return this.attachmentContent;
    }

    public void setAttachmentContent(byte[] attachmentContent) {
        this.attachmentContent = attachmentContent;
    }



	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("packageNumber", getPackageNumber());
		hashMap.put("pageNumber", getPageNumber());
		hashMap.put("sponsorCode", getSponsorCode());
		hashMap.put("pageDescription", getPageDescription());
		return hashMap;
	}

    public FormFile getTemplateFile() {
        return templateFile;
    }
    
    public void setTemplateFile(FormFile templateFile) {
        this.templateFile = templateFile;
    }

    public void beforeInsert(PersistenceBroker persistenceBroker) throws PersistenceBrokerException {
	    super.beforeInsert(persistenceBroker);
	}

	public void afterLookup(PersistenceBroker persistenceBroker) throws PersistenceBrokerException {
	    super.afterLookup(persistenceBroker);
	}	

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public final Boolean getSelectToPrint() {
        return selectToPrint;
    }

    public final void setSelectToPrint(Boolean selectToPrint) {
        this.selectToPrint = selectToPrint;
    }
    
}

