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

import java.util.LinkedHashMap;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerException;
import org.apache.struts.upload.FormFile;
import org.kuali.core.bo.PersistableAttachment;

@MappedSuperclass
public abstract class AbstractSponsorFormTemplate extends KraPersistableBusinessObjectBase implements Comparable<AbstractSponsorFormTemplate>{
	
    @Id
    @Column(name="PACKAGE_NUMBER")
    private Integer packageNumber;
    
    @Id
    @Column(name="PAGE_NUMBER")
	private Integer pageNumber;
    
    @Id
    @Column(name="SPONSOR_CODE")
	private String sponsorCode;
    
    @Column(name="PAGE_DESCRIPTION")
	private String pageDescription;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST })
    @JoinColumns({@JoinColumn(name="PACKAGE_NUMBER", insertable = false, updatable = false),
                  @JoinColumn(name="SPONSOR_CODE", insertable=false, updatable=false)})
    private SponsorForms sponsorForms;

	public Integer getPackageNumber() {
		return packageNumber;
	}

    public void setPackageNumber(Integer packageNumber) {
		this.packageNumber = packageNumber;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getSponsorCode() {
		return sponsorCode;
	}

	public void setSponsorCode(String sponsorCode) {
		this.sponsorCode = sponsorCode;
	}


	public String getPageDescription() {
		return pageDescription;
	}

	public void setPageDescription(String pageDescription) {
		this.pageDescription = pageDescription;
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

    public final SponsorForms getSponsorForms() {
        return sponsorForms;
    }

    public final void setSponsorForms(SponsorForms sponsorForms) {
        this.sponsorForms = sponsorForms;
    }
    
    public int compareTo(AbstractSponsorFormTemplate abstractSponsorFormTemplate) {
        int result = getPackageNumber().compareTo(abstractSponsorFormTemplate.getPackageNumber());
        result = result != 0 ? result : getPageNumber().compareTo(abstractSponsorFormTemplate.getPageNumber());
        return result;
    }
    
}
