/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.timeandmoney;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import org.kuali.kra.award.awardhierarchy.AwardHierarchy;
import org.kuali.kra.award.awardhierarchy.AwardHierarchyService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.AwardHierarchyUIService;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;
import org.kuali.rice.krad.util.GlobalVariables;

public class AwardValuesFinder extends KeyValuesBase{
    
    public List<KeyValue> getKeyValues() {
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) KNSGlobalVariables.getKualiForm();        
        TimeAndMoneyDocument document;
        if(timeAndMoneyForm == null) {
            document = updateDocumentFromSession();
        }else {
            document = timeAndMoneyForm.getTimeAndMoneyDocument();
        }        
        document.setAwardHierarchyItems(((TimeAndMoneyDocument)GlobalVariables.getUserSession().retrieveObject(
                GlobalVariables.getUserSession().getKualiSessionId() + Constants.TIME_AND_MONEY_DOCUMENT_STRING_FOR_SESSION)).getAwardHierarchyItems());    
        
        if(document.getAwardHierarchyItems()!=null && document.getAwardHierarchyItems().size()!=0){
            Object[] keyset = document.getAwardHierarchyItems().keySet().toArray();
            Arrays.sort(keyset);
            for(Object awardNumber : keyset) {
                keyValues.add(new ConcreteKeyValue((String) awardNumber, document.getAwardHierarchyItems().get(awardNumber).getAwardNumber()));
            }
        }
        
        return keyValues;
    }
    
    /*
     * This method...
     * @param doc
     */
    private TimeAndMoneyDocument updateDocumentFromSession() {
        TimeAndMoneyDocument document;
        if(GlobalVariables.getUserSession().retrieveObject(GlobalVariables.getUserSession().getKualiSessionId()+Constants.TIME_AND_MONEY_DOCUMENT_STRING_FOR_SESSION)!=null){
            document = (TimeAndMoneyDocument)GlobalVariables.getUserSession().retrieveObject(GlobalVariables.getUserSession().getKualiSessionId()+Constants.TIME_AND_MONEY_DOCUMENT_STRING_FOR_SESSION);
            document.setAwardHierarchyItems(document.getAwardHierarchyItems());
            document.setAwardHierarchyNodes(document.getAwardHierarchyNodes());
            document.setRootAwardNumber(getRootAwardFromHierarchyNodes(document));
        }else {
            throw new RuntimeException("Can't Retrieve Time And Money Document from Session");
        }
        return document;
    }
    
    
    /**
     * This method grabs the root award number from the hierarchy nodes since it is not set when add the T&M document to the session data.
     * 
     * @param document
     * @return
     */
    private String getRootAwardFromHierarchyNodes(TimeAndMoneyDocument document) {
        String rootAwardNumber = null;
        for(Entry<String, AwardHierarchyNode> awardHierarchyNode : document.getAwardHierarchyNodes().entrySet()){
            if(awardHierarchyNode != null) {
               rootAwardNumber = awardHierarchyNode.getValue().getRootAwardNumber();
            }
        }
        return rootAwardNumber;
    }
    
    public AwardHierarchyService getAwardHierarchyService(){        
        return (AwardHierarchyService) KraServiceLocator.getService(AwardHierarchyService.class);
    }
    
    public AwardHierarchyUIService getAwardHierarchyUIService(){        
        return (AwardHierarchyUIService) KraServiceLocator.getService(AwardHierarchyUIService.class);
    }

}
