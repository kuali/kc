/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
    private Boolean attachment4;
    private String awardNo;
    private String fdpType;
    private Boolean afosrSponsor;
    private Boolean amrmcSponsor;
    private Boolean aroSponsor;
    private Boolean doeSponsor;
    private Boolean epaSponsor;
    private Boolean nasaSponsor;
    private Boolean nihSponsor;
    private Boolean nsfSponsor;
    private Boolean onrSponsor;
    private Boolean usdaSponsor;
    
    
    
    /**
     * Gets the attachment4 attribute. 
     * @return Returns the attachment4.
     */
    public Boolean getAttachment4() {
        return attachment4;
    }

    /**
     * Sets the attachment4 attribute value.
     * @param attachment4 The attachment4 to set.
     */
    public void setAttachment4(Boolean attachment4) {
        this.attachment4 = attachment4;
    }

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
     * Sets all items the select all/none button
     */
    private void setAllItems(Boolean value) {
        attachment3A = true;
        attachment3BPage2 = true;
        attachment3B = true;
    }
    /**
     * Gets the afosrSponsor attribute. 
     * @return Returns the afosrSponsor.
     */
    public Boolean getAfosrSponsor() {
        return afosrSponsor;
    }

    /**
     * Sets the afosrSponsor attribute value.
     * @param afosrSponsor The afosrSponsor to set.
     */
    public void setAfosrSponsor(Boolean afosrSponsor) {
        this.afosrSponsor = afosrSponsor;
    }

    /**
     * Gets the amrmcSponsor attribute. 
     * @return Returns the amrmcSponsor.
     */
    public Boolean getAmrmcSponsor() {
        return amrmcSponsor;
    }

    /**
     * Sets the amrmcSponsor attribute value.
     * @param amrmcSponsor The amrmcSponsor to set.
     */
    public void setAmrmcSponsor(Boolean amrmcSponsor) {
        this.amrmcSponsor = amrmcSponsor;
    }

    /**
     * Gets the aroSponsor attribute. 
     * @return Returns the aroSponsor.
     */
    public Boolean getAroSponsor() {
        return aroSponsor;
    }

    /**
     * Sets the aroSponsor attribute value.
     * @param aroSponsor The aroSponsor to set.
     */
    public void setAroSponsor(Boolean aroSponsor) {
        this.aroSponsor = aroSponsor;
    }

    /**
     * Gets the doeSponsor attribute. 
     * @return Returns the doeSponsor.
     */
    public Boolean getDoeSponsor() {
        return doeSponsor;
    }

    /**
     * Sets the doeSponsor attribute value.
     * @param doeSponsor The doeSponsor to set.
     */
    public void setDoeSponsor(Boolean doeSponsor) {
        this.doeSponsor = doeSponsor;
    }

    /**
     * Gets the epaSponsor attribute. 
     * @return Returns the epaSponsor.
     */
    public Boolean getEpaSponsor() {
        return epaSponsor;
    }

    /**
     * Sets the epaSponsor attribute value.
     * @param epaSponsor The epaSponsor to set.
     */
    public void setEpaSponsor(Boolean epaSponsor) {
        this.epaSponsor = epaSponsor;
    }

    /**
     * Gets the nasaSponsor attribute. 
     * @return Returns the nasaSponsor.
     */
    public Boolean getNasaSponsor() {
        return nasaSponsor;
    }

    /**
     * Sets the nasaSponsor attribute value.
     * @param nasaSponsor The nasaSponsor to set.
     */
    public void setNasaSponsor(Boolean nasaSponsor) {
        this.nasaSponsor = nasaSponsor;
    }

    /**
     * Gets the nihSponsor attribute. 
     * @return Returns the nihSponsor.
     */
    public Boolean getNihSponsor() {
        return nihSponsor;
    }

    /**
     * Sets the nihSponsor attribute value.
     * @param nihSponsor The nihSponsor to set.
     */
    public void setNihSponsor(Boolean nihSponsor) {
        this.nihSponsor = nihSponsor;
    }

    /**
     * Gets the nsfSponsor attribute. 
     * @return Returns the nsfSponsor.
     */
    public Boolean getNsfSponsor() {
        return nsfSponsor;
    }

    /**
     * Sets the nsfSponsor attribute value.
     * @param nsfSponsor The nsfSponsor to set.
     */
    public void setNsfSponsor(Boolean nsfSponsor) {
        this.nsfSponsor = nsfSponsor;
    }

    /**
     * Gets the onrSponsor attribute. 
     * @return Returns the onrSponsor.
     */
    public Boolean getOnrSponsor() {
        return onrSponsor;
    }

    /**
     * Sets the onrSponsor attribute value.
     * @param onrSponsor The onrSponsor to set.
     */
    public void setOnrSponsor(Boolean onrSponsor) {
        this.onrSponsor = onrSponsor;
    }

    /**
     * Gets the usdaSponsor attribute. 
     * @return Returns the usdaSponsor.
     */
    public Boolean getUsdaSponsor() {
        return usdaSponsor;
    }

    /**
     * Sets the usdaSponsor attribute value.
     * @param usdaSponsor The usdaSponsor to set.
     */
    public void setUsdaSponsor(Boolean usdaSponsor) {
        this.usdaSponsor = usdaSponsor;
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
