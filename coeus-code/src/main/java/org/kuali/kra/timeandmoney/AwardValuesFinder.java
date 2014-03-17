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
package org.kuali.kra.timeandmoney;

import org.kuali.coeus.sys.framework.keyvalue.FormViewAwareUifKeyValuesFinderBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

public class AwardValuesFinder extends FormViewAwareUifKeyValuesFinderBase {

    @Override
    protected Object getFormOrView() {
        Object formOrView = super.getFormOrView();
        if (formOrView == null) {
            formOrView = updateDocumentFromSession();
        }
        return formOrView;
    }

    @Override
    public List<KeyValue> getKeyValues() {
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        TimeAndMoneyDocument document = (TimeAndMoneyDocument) getDocument();

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

}
