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
import org.kuali.rice.kns.util.KualiDecimal;

import java.util.LinkedHashMap;

public class AwardHierarchyNode extends AwardHierarchy { 
    
    private KualiDecimal anticipatedTotalDirect; 
    private KualiDecimal anticipatedTotalIndirect; 
    private KualiDecimal obligatedTotalDirect; 
    private KualiDecimal obligatedTotalIndirect;
    
    /**
     * Gets the anticipatedTotalDirect attribute. 
     * @return Returns the anticipatedTotalDirect.
     */
    public KualiDecimal getAnticipatedTotalDirect() {
        return anticipatedTotalDirect;
    }

    /**
     * Sets the anticipatedTotalDirect attribute value.
     * @param anticipatedTotalDirect The anticipatedTotalDirect to set.
     */
    public void setAnticipatedTotalDirect(KualiDecimal anticipatedTotalDirect) {
        this.anticipatedTotalDirect = anticipatedTotalDirect;
    }

    /**
     * Gets the anticipatedTotalIndirect attribute. 
     * @return Returns the anticipatedTotalIndirect.
     */
    public KualiDecimal getAnticipatedTotalIndirect() {
        return anticipatedTotalIndirect;
    }

    /**
     * Sets the anticipatedTotalIndirect attribute value.
     * @param anticipatedTotalIndirect The anticipatedTotalIndirect to set.
     */
    public void setAnticipatedTotalIndirect(KualiDecimal anticipatedTotalIndirect) {
        this.anticipatedTotalIndirect = anticipatedTotalIndirect;
    }

    /**
     * Gets the obligatedTotalDirect attribute. 
     * @return Returns the obligatedTotalDirect.
     */
    public KualiDecimal getObligatedTotalDirect() {
        return obligatedTotalDirect;
    }

    /**
     * Sets the obligatedTotalDirect attribute value.
     * @param obligatedTotalDirect The obligatedTotalDirect to set.
     */
    public void setObligatedTotalDirect(KualiDecimal obligatedTotalDirect) {
        this.obligatedTotalDirect = obligatedTotalDirect;
    }

    /**
     * Gets the obligatedTotalIndirect attribute. 
     * @return Returns the obligatedTotalIndirect.
     */
    public KualiDecimal getObligatedTotalIndirect() {
        return obligatedTotalIndirect;
    }

    /**
     * Sets the obligatedTotalIndirect attribute value.
     * @param obligatedTotalIndirect The obligatedTotalIndirect to set.
     */
    public void setObligatedTotalIndirect(KualiDecimal obligatedTotalIndirect) {
        this.obligatedTotalIndirect = obligatedTotalIndirect;
    }   
    
    public AwardHierarchyNode() { 

    }

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = super.toStringMapper();
        hashMap.put("anticipatedTotalDirect", this.getAnticipatedTotalDirect());
        hashMap.put("anticipatedTotalIndirect", this.getAnticipatedTotalIndirect());
        hashMap.put("obligatedTotalDirect", this.getObligatedTotalDirect());
        hashMap.put("obligatedTotalIndirect", this.getObligatedTotalIndirect());        
        return hashMap;
    }
    
}