/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.timeandmoney.service.impl;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.version.service.AwardVersionService;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.service.TimeAndMoneyVersionService;
import org.kuali.kra.timeandmoney.transactions.AwardAmountTransaction;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeAndMoneyVersionServiceImpl implements TimeAndMoneyVersionService {

    private AwardVersionService awardVersionService;
    private DocumentService documentService;

    /*
     * Find any existing T&amp;M document for the given award number, with the intent to
     * edit it.  If open one is found, return it. If no open one is found, create new one.
     */
    public TimeAndMoneyDocument findOpenedTimeAndMoney(String rootAwardNumber) throws WorkflowException {
        TimeAndMoneyDocument result = null;
        Map<String, String> criteria = new HashMap<String, String>();
        criteria.put("rootAwardNumber", rootAwardNumber);
        BusinessObjectService businessObjectService =  KcServiceLocator.getService(BusinessObjectService.class);

        List<TimeAndMoneyDocument> timeAndMoneyDocuments = 
            (List<TimeAndMoneyDocument>)businessObjectService.findMatching(TimeAndMoneyDocument.class, criteria);
        Collections.sort(timeAndMoneyDocuments);
        
        // check for existing non-finalized T &amp; M document before versioning the existing one.
        TimeAndMoneyDocument timeAndMoneyDocument = getLastNonCanceledTandMDocument(timeAndMoneyDocuments);
        if(timeAndMoneyDocument == null || timeAndMoneyDocuments.size() == 0) {
            throw new WorkflowException("Missing Time and Money Document");
        } else {
            if (!timeAndMoneyDocument.getDocumentHeader().getWorkflowDocument().isInitiated() &&
                !timeAndMoneyDocument.getDocumentHeader().getWorkflowDocument().isSaved()) {
                timeAndMoneyDocument = editOrVersionTandMDocument(rootAwardNumber);
            }
        }
        return timeAndMoneyDocument;
    }

    private TimeAndMoneyDocument getLastNonCanceledTandMDocument(List<TimeAndMoneyDocument> timeAndMoneyDocuments) throws WorkflowException {
        TimeAndMoneyDocument returnVal = null;
        DocumentService documentService = KcServiceLocator.getService(DocumentService.class);
        while(timeAndMoneyDocuments.size() > 0) {
            TimeAndMoneyDocument docWithWorkFlowData = 
                (TimeAndMoneyDocument) documentService.getByDocumentHeaderId(timeAndMoneyDocuments.get(timeAndMoneyDocuments.size() - 1).getDocumentNumber());
            if(docWithWorkFlowData.getDocumentHeader().getWorkflowDocument().isCanceled()) {
                timeAndMoneyDocuments.remove(timeAndMoneyDocuments.size() - 1);
            }else {
                returnVal = docWithWorkFlowData;
                break;
            }
        }
        return returnVal;
    }

    private TimeAndMoneyDocument editOrVersionTandMDocument(String rootAwardNumber) throws WorkflowException {
        Award rootAward = getAwardVersionService().getWorkingAwardVersion(rootAwardNumber);
        TimeAndMoneyDocument timeAndMoneyDocument = (TimeAndMoneyDocument) getDocumentService().getNewDocument(TimeAndMoneyDocument.class);
        timeAndMoneyDocument.getDocumentHeader().setDocumentDescription("timeandmoney document");
        timeAndMoneyDocument.setRootAwardNumber(rootAwardNumber);
        timeAndMoneyDocument.setAwardNumber(rootAward.getAwardNumber());
        timeAndMoneyDocument.setAward(rootAward);
        AwardAmountTransaction aat = new AwardAmountTransaction();
        aat.setAwardNumber("000000-00000");//need to initialize one element in this collection because the doc is saved on creation.
        aat.setDocumentNumber(timeAndMoneyDocument.getDocumentNumber());
        aat.setTransactionTypeCode(null);
        timeAndMoneyDocument.getAwardAmountTransactions().add(aat);
        getDocumentService().saveDocument(timeAndMoneyDocument);
        return timeAndMoneyDocument;
    }

    public AwardVersionService getAwardVersionService() {
        return awardVersionService;
    }

    public void setAwardVersionService(AwardVersionService awardVersionService) {
        this.awardVersionService = awardVersionService;
    }

    public DocumentService getDocumentService() {
        return documentService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    
}
