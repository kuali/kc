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
import java.text.ParseException;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.kra.service.MedusaService;

public class MedusaBean implements Serializable{

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -8727199559530816767L;
    private String medusaViewRadio;
    private String moduleName;
    private Long moduleIdentifier;
    private AwardForm awardForm;
    private ProposalDevelopmentForm proposalDevelopmentForm;
    private String medusa;
    
    /**
     * 
     * Constructs a AwardReportsBean.java.
     * @param form
     */
    public MedusaBean(ProposalDevelopmentForm form) {
        this.proposalDevelopmentForm = form;
        this.setMedusaViewRadio("0");
    }
    
    /**
     * 
     * Constructs a AwardReportsBean.java.
     * @param form
     */
    public MedusaBean(AwardForm form) {
        this.awardForm = form;
        this.setMedusaViewRadio("0");
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
     * Gets the moduleName attribute. 
     * @return Returns the moduleName.
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * Sets the moduleName attribute value.
     * @param moduleName The moduleName to set.
     */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    /**
     * Gets the moduleIdentifier attribute. 
     * @return Returns the moduleIdentifier.
     */
    public Long getModuleIdentifier() {
        return moduleIdentifier;
    }

    /**
     * Sets the moduleIdentifier attribute value.
     * @param moduleIdentifier The moduleIdentifier to set.
     */
    public void setModuleIdentifier(Long moduleIdentifier) {
        this.moduleIdentifier = moduleIdentifier;
    }

    /**
     * Gets the medusaForm attribute. 
     * @return Returns the medusaForm.
     */
    public AwardForm getAwardForm() {
        return awardForm;
    }

    /**
     * Sets the medusaForm attribute value.
     * @param medusaForm The medusaForm to set.
     */
    public void setAwardForm(AwardForm awardForm) {
        this.awardForm = awardForm;
    }

    /**
     * Gets the medusa attribute. 
     * @return Returns the medusa.
     */
    public String getMedusa() throws ParseException{
        medusa = "";
        
        if(StringUtils.equalsIgnoreCase("0", getMedusaViewRadio())){
            setMedusa(getMedusaService().getMedusaByAward(getModuleName(), getModuleIdentifier()));    
        }else if(StringUtils.equalsIgnoreCase("1", getMedusaViewRadio())){
            setMedusa(getMedusaService().getMedusaByProposal(getModuleName(), getModuleIdentifier()));    
        } 
        
        return medusa;
    }

    /**
     * Sets the medusa attribute value.
     * @param medusa The medusa to set.
     */
    public void setMedusa(String medusa) {
        this.medusa = medusa;
    }
    
    /**
     * This method...
     * @return
     */
    private MedusaService getMedusaService() {
        return KraServiceLocator.getService(MedusaService.class);
    }

    /**
     * Gets the proposalDevelopmentForm attribute. 
     * @return Returns the proposalDevelopmentForm.
     */
    public ProposalDevelopmentForm getProposalDevelopmentForm() {
        return proposalDevelopmentForm;
    }

    /**
     * Sets the proposalDevelopmentForm attribute value.
     * @param proposalDevelopmentForm The proposalDevelopmentForm to set.
     */
    public void setProposalDevelopmentForm(ProposalDevelopmentForm proposalDevelopmentForm) {
        this.proposalDevelopmentForm = proposalDevelopmentForm;
    }
    
}
