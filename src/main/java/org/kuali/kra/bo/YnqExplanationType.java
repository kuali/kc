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

import javax.persistence.Version;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;

import java.util.LinkedHashMap;

@Entity
@Table(name="YNQ_EXPLANATION_TYPE")
public class YnqExplanationType extends KraPersistableBusinessObjectBase {

    @Id
	@Column(name="EXPLANATION_TYPE")
	private String explanationType; 
    @Column(name="DESCRIPTION")
	private String description; 

    public YnqExplanationType(){
        super();
    }

    public String getExplanationType() {
        return explanationType;
    }

    public void setExplanationType(String explanationType) {
        this.explanationType = explanationType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override 
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap hashMap = new LinkedHashMap();
        hashMap.put("explanationType", getExplanationType());
        hashMap.put("description", getDescription());
        return hashMap;
    }

}

