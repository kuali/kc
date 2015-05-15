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
     * This method grabs the root award number from the hierarchy nodes since it is not set when add the T&amp;M document to the session data.
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
