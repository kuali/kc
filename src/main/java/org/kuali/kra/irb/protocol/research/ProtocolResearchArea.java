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
package org.kuali.kra.irb.protocol.research;

import java.util.LinkedHashMap;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.kuali.kra.bo.ResearchArea;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolAssociate;


@javax.persistence.Entity 
@Table(name="PROTOCOL_RESEARCH_AREAS")
public class ProtocolResearchArea extends ProtocolAssociate { 
	
    private static final long serialVersionUID = -1522011425745031200L;

    @javax.persistence.Id 
    @Column(name="ID")
    private Long id; 

    @Column(name="RESEARCH_AREA_CODE")
	private String researchAreaCode; 
	
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="PROTOCOL_ID", insertable=false, updatable=false)    
	private Protocol protocol;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="RESEARCH_AREA_CODE", insertable=false, updatable=false)
	private ResearchArea researchAreas; 
	
	public ProtocolResearchArea() { 

	} 
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
	
	public String getResearchAreaCode() {
		return researchAreaCode;
	}

	public void setResearchAreaCode(String researchAreaCode) {
		this.researchAreaCode = researchAreaCode;
	}

	public Protocol getProtocol() {
		return protocol;
	}

	public void setProtocol(Protocol protocol) {
		this.protocol = protocol;
	}

	public ResearchArea getResearchAreas() {
		return researchAreas;
	}

	public void setResearchAreas(ResearchArea researchAreas) {
		this.researchAreas = researchAreas;
	}

	@SuppressWarnings("unchecked")
    @Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = super.toStringMapper();		
		hashMap.put("researchAreaCode", getResearchAreaCode());
		return hashMap;
	}

    public void init(Protocol protocol) {
        setId(null);
        setProtocolNumber(protocol.getProtocolNumber());
        setProtocol(protocol);
    }
	
}