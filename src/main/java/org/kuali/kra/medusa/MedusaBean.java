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
package org.kuali.kra.medusa;

import java.io.Serializable;

import org.kuali.kra.award.AwardForm;

public class MedusaBean implements Serializable{

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -8727199559530816767L;
    private String medusaViewRadio;
    private AwardForm awardForm;
    
    /**
     * 
     * Constructs a AwardReportsBean.java.
     * @param form
     */
    public MedusaBean(AwardForm form) {
        this.awardForm = form;
        this.setMedusaViewRadio("1");
    }
    
    /**
     * Gets the medudaViewRadio attribute. 
     * @return Returns the medudaViewRadio.
     */
    public String getMedusaViewRadio() {
        return medusaViewRadio;
    }

    /**
     * Sets the medudaViewRadio attribute value.
     * @param medudaViewRadio The medudaViewRadio to set.
     */
    public void setMedusaViewRadio(String medusaViewRadio) {
        this.medusaViewRadio = medusaViewRadio;
    }

    /**
     * Gets the awardForm attribute. 
     * @return Returns the awardForm.
     */
    public AwardForm getAwardForm() {
        return awardForm;
    }

    /**
     * Sets the awardForm attribute value.
     * @param awardForm The awardForm to set.
     */
    public void setAwardForm(AwardForm awardForm) {
        this.awardForm = awardForm;
    }
    
}
