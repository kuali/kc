/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.award.detailsdates;

import java.io.Serializable;

import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.web.struts.form.AwardForm;
import org.kuali.kra.bo.Sponsor;

/**
 * This class encapsulates presentation-specific data and behavior
 * for the award details and dates functionality.
 */
public class DetailsAndDatesFormHelper implements Serializable {
    
    private AwardForm parent;
    
    private Sponsor newAwardTransferringSponsor;
    
    /**
     * Constructs a ApprovedSubawardFormHelper
     * @param parent
     */
    public DetailsAndDatesFormHelper(AwardForm parent) {
        this.parent = parent;
    }
    
    /**
     * Initialize subform
     */
    public void init() {
        newAwardTransferringSponsor = new Sponsor(); 
    }
    
    public Sponsor getNewAwardTransferringSponsor() {
        return newAwardTransferringSponsor;
    }

    public void setNewAwardTransferringSponsor(Sponsor newAwardTransferringSponsor) {
        this.newAwardTransferringSponsor = newAwardTransferringSponsor;
    }

    public AwardForm getParent() {
        return parent;
    }

    public void setParent(AwardForm parent) {
        this.parent = parent;
    }

    public AwardDocument getAwardDocument() {
        return parent.getAwardDocument();
    }
    
}
