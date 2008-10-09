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
package org.kuali.kra.s2s.bo;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.IdClass;

import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.proposaldevelopment.bo.Narrative;

@IdClass(org.kuali.kra.s2s.bo.id.S2sOppFormsId.class)
@Entity
@Table(name="S2S_OPP_FORMS")
public class S2sOppForms extends KraPersistableBusinessObjectBase {
    @Id
    @Column(name="PROPOSAL_NUMBER")
    private String proposalNumber;
    
	@Id
	@Column(name="OPP_NAME_SPACE")
	private String oppNameSpace;
	
	@Column(name="AVAILABLE")
	private Boolean available;
	
	@Column(name="FORM_NAME")
	private String formName;
	
	@Column(name="INCLUDE")
	private Boolean include;
	
	@Column(name="MANDATORY")
	private Boolean mandatory;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST })
    @JoinColumn(name="PROPOSAL_NUMBER", insertable = false, updatable = false)
    private S2sOpportunity s2sOpportunity;
	
	@Transient
	private String schemaUrl;
	
	@Transient
	private Boolean selectToPrint;

	public String getOppNameSpace() {
		return oppNameSpace;
	}

	public void setOppNameSpace(String oppNameSpace) {
		this.oppNameSpace = oppNameSpace;
	}

	public String getProposalNumber() {
		return proposalNumber;
	}

	public void setProposalNumber(String proposalNumber) {
		this.proposalNumber = proposalNumber;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}
	
	public Boolean getAvailable() {
		return available;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public void setInclude(Boolean include) {
		this.include = include;
	}
	
	public Boolean getInclude() {
		return include;
	}	

	public void setMandatory(Boolean mandatory) {
		this.mandatory = mandatory;
	}
	
	public Boolean getMandatory() {
		return mandatory;
	}	


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("oppNameSpace", getOppNameSpace());
		hashMap.put("proposalNumber", getProposalNumber());
		hashMap.put("available", getAvailable());
		hashMap.put("formName", getFormName());
		hashMap.put("include", getInclude());
		hashMap.put("mandatory", getMandatory());
        hashMap.put("schemaUrl", getSchemaUrl());
		return hashMap;
	}

    public String getSchemaUrl() {
        return schemaUrl;
    }

    public void setSchemaUrl(String schemaUrl) {
        this.schemaUrl = schemaUrl;
    }

    public Boolean getSelectToPrint() {
        return selectToPrint;
    }

    public void setSelectToPrint(Boolean selectToPrint) {
        this.selectToPrint = selectToPrint;
    }

    public S2sOpportunity getS2sOpportunity() {
        return s2sOpportunity;
    }

    public void setS2sOpportunity(S2sOpportunity opportunity) {
        s2sOpportunity = opportunity;
    }
}

