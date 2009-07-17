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
package org.kuali.kra.timeandmoney.service.impl;

import java.util.Map.Entry;

import org.codehaus.plexus.util.StringUtils;
import org.kuali.kra.timeandmoney.AwardHierarchyNode;
import org.kuali.kra.timeandmoney.TimeAndMoneyForm;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.service.ActivePendingTransactionsService;
import org.kuali.kra.timeandmoney.transactions.PendingTransaction;
import org.kuali.rice.kns.util.GlobalVariables;

public class ActivePendingTransactionsServiceImpl implements ActivePendingTransactionsService {

    public void approveTransactions() {
        
        TimeAndMoneyForm form = (TimeAndMoneyForm) GlobalVariables.getKualiForm();
        TimeAndMoneyDocument doc = (TimeAndMoneyDocument) form.getDocument();
        for(PendingTransaction pendingTransaction: doc.getPendingTransactions()){
            for(Entry<String, AwardHierarchyNode> awardHierarchyNode: doc.getAwardHierarchyNodes().entrySet()){
                if(StringUtils.equalsIgnoreCase(awardHierarchyNode.getKey(), pendingTransaction.getSourceAwardNumber())){
                    awardHierarchyNode.getValue().setAnticipatedTotalAmount(awardHierarchyNode.getValue().getAnticipatedTotalAmount().subtract(pendingTransaction.getAnticipatedAmount()));
                    awardHierarchyNode.getValue().setAntDistributableAmount(awardHierarchyNode.getValue().getAntDistributableAmount().subtract(pendingTransaction.getAnticipatedAmount()));
                    awardHierarchyNode.getValue().setAmountObligatedToDate(awardHierarchyNode.getValue().getAmountObligatedToDate().subtract(pendingTransaction.getObligatedAmount()));
                    awardHierarchyNode.getValue().setObliDistributableAmount(awardHierarchyNode.getValue().getObliDistributableAmount().subtract(pendingTransaction.getObligatedAmount()));
                }
                if(StringUtils.equalsIgnoreCase(awardHierarchyNode.getKey(), pendingTransaction.getDestinationAwardNumber())){
                    awardHierarchyNode.getValue().setAnticipatedTotalAmount(awardHierarchyNode.getValue().getAnticipatedTotalAmount().add(pendingTransaction.getAnticipatedAmount()));
                    awardHierarchyNode.getValue().setAntDistributableAmount(awardHierarchyNode.getValue().getAntDistributableAmount().add(pendingTransaction.getAnticipatedAmount()));
                    awardHierarchyNode.getValue().setAmountObligatedToDate(awardHierarchyNode.getValue().getAmountObligatedToDate().add(pendingTransaction.getObligatedAmount()));
                    awardHierarchyNode.getValue().setObliDistributableAmount(awardHierarchyNode.getValue().getObliDistributableAmount().add(pendingTransaction.getObligatedAmount()));
                }
                
            }
        }
        

    }

}
