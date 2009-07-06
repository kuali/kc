/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.award.awardhierarchy;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import java.util.LinkedHashMap;

public class AwardHierarchy extends KraPersistableBusinessObjectBase { 
    
    private static final long serialVersionUID = 1L;

    private String rootAwardNumber; 
    private String awardNumber; 
    private String parentAwardNumber; 
    
    
    public AwardHierarchy() { 

    } 
    
    public String getRootAwardNumber() {
        return rootAwardNumber;
    }

    public void setRootAwardNumber(String rootAwardNumber) {
        this.rootAwardNumber = rootAwardNumber;
    }

    public String getAwardNumber() {
        return awardNumber;
    }

    public void setAwardNumber(String awardNumber) {
        this.awardNumber = awardNumber;
    }

    public String getParentAwardNumber() {
        return parentAwardNumber;
    }

    public void setParentAwardNumber(String parentAwardNumber) {
        this.parentAwardNumber = parentAwardNumber;
    }

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("rootAwardNumber", this.getRootAwardNumber());
        hashMap.put("awardNumber", this.getAwardNumber());
        hashMap.put("parentAwardNumber", this.getParentAwardNumber());
        return hashMap;
    }
    
}