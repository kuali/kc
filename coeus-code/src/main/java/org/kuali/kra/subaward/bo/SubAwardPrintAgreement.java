/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.subaward.bo;

import org.kuali.rice.kns.web.struts.form.KualiForm;
import org.kuali.rice.krad.bo.BusinessObjectBase;


public class SubAwardPrintAgreement extends BusinessObjectBase{

    
    private String fdpAgreement;
    private String fdpModification;
    private String fundingSource;
    private String subawardTemplateAttachment;
    private Boolean selectToPrint;
    private String oppNameSpace;
    private String awardNo;
    private String fdpType;
    
    
    public SubAwardPrintAgreement() {
        initialize();
    }     

    public void initialize() {
        setFdpType("fdpAgreement");        
    }
    

    /**
     * Gets the fdpType attribute. 
     * @return Returns the fdpType.
     */
    public String getFdpType() {
        return fdpType;
    }


    /**
     * Sets the fdpType attribute value.
     * @param fdpType The fdpType to set.
     */
    public void setFdpType(String fdpType) {
        this.fdpType = fdpType;
    }


    /**
     * Gets the awardNo attribute. 
     * @return Returns the awardNo.
     */
    public String getAwardNo() {
        return awardNo;
    }


    /**
     * Sets the awardNo attribute value.
     * @param awardNo The awardNo to set.
     */
    public void setAwardNo(String awardNo) {
        this.awardNo = awardNo;
    }

   

    /**
     * Gets the fdpAgreement attribute. 
     * @return Returns the fdpAgreement.
     */
    public String getFdpAgreement() {
        return fdpAgreement;
    }


    /**
     * Sets the fdpAgreement attribute value.
     * @param fdpAgreement The fdpAgreement to set.
     */
    public void setFdpAgreement(String fdpAgreement) {
        this.fdpAgreement = fdpAgreement;
    }
    /**
     * Gets the fdpModification attribute. 
     * @return Returns the fdpModification.
     */
    public String getFdpModification() {
        return fdpModification;
    }


    /**
     * Sets the fdpModification attribute value.
     * @param fdpModification The fdpModification to set.
     */
    public void setFdpModification(String fdpModification) {
        this.fdpModification = fdpModification;
    }


    /**
     * Gets the fundingSource attribute. 
     * @return Returns the fundingSource.
     */
    public String getFundingSource() {
        return fundingSource;
    }


    /**
     * Sets the fundingSource attribute value.
     * @param fundingSource The fundingSource to set.
     */
    public void setFundingSource(String fundingSource) {
        this.fundingSource = fundingSource;
    }


  

    /**
     * Gets the subawardTemplateAttachment attribute. 
     * @return Returns the subawardTemplateAttachment.
     */
    public String getSubawardTemplateAttachment() {
        return subawardTemplateAttachment;
    }


    /**
     * Sets the subawardTemplateAttachment attribute value.
     * @param subawardTemplateAttachment The subawardTemplateAttachment to set.
     */
    public void setSubawardTemplateAttachment(String subawardTemplateAttachment) {
        this.subawardTemplateAttachment = subawardTemplateAttachment;
    }


    /**
     * Gets the selectToPrint attribute. 
     * @return Returns the selectToPrint.
     */
    public Boolean getSelectToPrint() {
        return selectToPrint;
    }


    /**
     * Sets the selectToPrint attribute value.
     * @param selectToPrint The selectToPrint to set.
     */
    public void setSelectToPrint(Boolean selectToPrint) {
        this.selectToPrint = selectToPrint;
    }


    /**
     * Gets the oppNameSpace attribute. 
     * @return Returns the oppNameSpace.
     */
    public String getOppNameSpace() {
        return oppNameSpace;
    }


    /**
     * Sets the oppNameSpace attribute value.
     * @param oppNameSpace The oppNameSpace to set.
     */
    public void setOppNameSpace(String oppNameSpace) {
        this.oppNameSpace = oppNameSpace;
    }

    @Override
    public void refresh() {
        // TODO Auto-generated method stub
        
    }
    
   
}
