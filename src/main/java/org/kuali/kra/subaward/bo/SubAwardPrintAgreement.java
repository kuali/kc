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

import org.kuali.rice.krad.bo.BusinessObjectBase;


public class SubAwardPrintAgreement extends BusinessObjectBase{

    
    private String fdpAgreement;
    private String fdpModification;
    private String fundingSource;
    private String subawardTemplateAttachment;
    private Boolean selectToPrint;
    private Boolean attachment3A;
    private Boolean attachment3BPage2;
    private Boolean attachment3B;
    private Boolean attachment4A;
    
    /**
     * Gets the attachment3A attribute. 
     * @return Returns the attachment3A.
     */
    public Boolean getAttachment3A() {
        return attachment3A;
    }

    /**
     * Sets the attachment3A attribute value.
     * @param attachment3a The attachment3A to set.
     */
    public void setAttachment3A(Boolean attachment3a) {
        attachment3A = attachment3a;
    }

    /**
     * Gets the attachment3BPage2 attribute. 
     * @return Returns the attachment3BPage2.
     */
    public Boolean getAttachment3BPage2() {
        return attachment3BPage2;
    }

    /**
     * Sets the attachment3BPage2 attribute value.
     * @param attachment3bPage2 The attachment3BPage2 to set.
     */
    public void setAttachment3BPage2(Boolean attachment3bPage2) {
        attachment3BPage2 = attachment3bPage2;
    }

    /**
     * Gets the attachment3B attribute. 
     * @return Returns the attachment3B.
     */
    public Boolean getAttachment3B() {
        return attachment3B;
    }

    /**
     * Sets the attachment3B attribute value.
     * @param attachment3b The attachment3B to set.
     */
    public void setAttachment3B(Boolean attachment3b) {
        attachment3B = attachment3b;
    }

    /**
     * Gets the attachment4A attribute. 
     * @return Returns the attachment4A.
     */
    public Boolean getAttachment4A() {
        return attachment4A;
    }

    /**
     * Sets the attachment4A attribute value.
     * @param attachment4a The attachment4A to set.
     */
    public void setAttachment4A(Boolean attachment4a) {
        attachment4A = attachment4a;
    }

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
    
    /**
     * Sets all items the select all/none button
     */
    private void setAllItems(Boolean value) {
        attachment3A = true;
        attachment3BPage2 = true;
        attachment3B = true;
    }
    /**
     * Selects all items 
     */
    
    public void selectAllItems() {
        setAllItems(true);
    }

    /**
     * Deselects all items
     */
    public void deselectAllItems() {
        setAllItems(false);
    }

    @Override
    public void refresh() {
        // TODO Auto-generated method stub
        
    }
    
   
}
