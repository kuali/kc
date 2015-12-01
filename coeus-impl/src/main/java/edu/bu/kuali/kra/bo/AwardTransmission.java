/*
 * Copyright (c) 2014. Boston University
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl1.php
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND either express or
 * implied.
 *
 * See the License for the specific language governing permissions and limitations under the License.
 */
package edu.bu.kuali.kra.bo;


import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.krad.util.KRADConstants;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represent transmission fields added for BU implementation.
 *
 * @author mkousheh
 */
public class AwardTransmission extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = -184803987128257580L;
    private static final String DOC_HANDLER_TAG_PATTERN = "<a href=\"%s\" target=\"_BLANK\">%s</a>";
    private static final String DOC_HANDLER_URL_PATTERN = "%s/DocHandler.do?command=displayDocSearchView&docId=%s";

    private long transmissionId;
    private long awardId;
    private String initiatorId;
    private String transmitterId;
    private Date transmissionDate;
    private String successIndicator;
    private String sentData;
    private String returnedData;
    private String documentNumber;

    private String basisOfPaymentCode;
    private Integer accountTypeCode;
    private String sponsorCode;
    private String methodOfPaymentCode;
    private PersonService personService;
    private List<AwardTransmissionChild> transmissionChildren;
    private ConfigurationService kualiConfigurationService;

    public AwardTransmission() {
        transmissionChildren = new ArrayList<AwardTransmissionChild>();
    }

    public String getAlldocumentNumbers() {
        String documentNumbers = makeDocumentOpenTag(documentNumber);

        for (AwardTransmissionChild awardTransmissionChild : transmissionChildren) {
            documentNumbers += ", " + makeDocumentOpenTag(awardTransmissionChild.getChildDocumentNumber());
        }
        return documentNumbers;
    }

    private String makeDocumentOpenTag(String documentNumber) {
        String workflowUrl = CoreApiServiceLocator.getKualiConfigurationService().getPropertyValueAsString(KRADConstants.WORKFLOW_URL_KEY);
        String url = String.format(DOC_HANDLER_URL_PATTERN, workflowUrl, documentNumber);
        return String.format(DOC_HANDLER_TAG_PATTERN, url, documentNumber);
    }

    public String getBasisOfPaymentCode() {
        return basisOfPaymentCode;
    }

    public void setBasisOfPaymentCode(String basisOfPaymentCode) {
        this.basisOfPaymentCode = basisOfPaymentCode;
    }

    public Integer getAccountTypeCode() {
        return accountTypeCode;
    }

    public void setAccountTypeCode(Integer accountTypeCode) {
        this.accountTypeCode = accountTypeCode;
    }

    public String getSponsorCode() {
        return sponsorCode;
    }

    public void setSponsorCode(String sponsorCode) {
        this.sponsorCode = sponsorCode;
    }

    public String getMethodOfPaymentCode() {
        return methodOfPaymentCode;
    }

    public void setMethodOfPaymentCode(String methodOfPaymentCode) {
        this.methodOfPaymentCode = methodOfPaymentCode;
    }

    private Person initiator;
    private Person transmitter;

    public long getTransmissionId() {
        return transmissionId;
    }

    public void setTransmissionId(long transmissionId) {
        this.transmissionId = transmissionId;
    }

    public long getAwardId() {
        return awardId;
    }

    public void setAwardId(long awardId) {
        this.awardId = awardId;
    }

    public String getInitiatorId() {
        return initiatorId;
    }

    public void setInitiatorId(String initiatorId) {
        this.initiatorId = initiatorId;
    }

    public String getTransmitterId() {
        return transmitterId;
    }

    public void setTransmitterId(String transmitterId) {
        this.transmitterId = transmitterId;
    }

    public Date getTransmissionDate() {
        return transmissionDate;
    }

    public void setTransmissionDate(Date transmissionDate) {
        this.transmissionDate = transmissionDate;
    }

    public boolean isSuccess() {
        return "Y".equalsIgnoreCase(successIndicator);
    }

    public String getSuccessIndicator() {
        return successIndicator;
    }

    public void setSuccessIndicator(String successIndicator) {
        this.successIndicator = successIndicator;
    }

    public String getSentData() {
        return sentData;
    }

    public void setSentData(String sentData) {
        this.sentData = sentData;
    }

    public String getReturnedData() {
        return returnedData;
    }

    public void setReturnedData(String returnedData) {
        this.returnedData = returnedData;
    }

    public Person getTransmitter() {
        transmitter = getPersonService().updatePersonIfNecessary(transmitterId, transmitter);
        return transmitter;
    }

    public Person getInitiator() {
        initiator = getPersonService().updatePersonIfNecessary(initiatorId, initiator);
        return initiator;
    }

    public List<AwardTransmissionChild> getTransmissionChildren() {
        return transmissionChildren;
    }

    public void setTransmissionChildren(List<AwardTransmissionChild> transmissionChildren) {
        this.transmissionChildren = transmissionChildren;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public ConfigurationService getKualiConfigurationService() {
        return KcServiceLocator.getService(ConfigurationService.class);
    }


    public PersonService getPersonService() {
        return KcServiceLocator.getService(PersonService.class);
    }


}
