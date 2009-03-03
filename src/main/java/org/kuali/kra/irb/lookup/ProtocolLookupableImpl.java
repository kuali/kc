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
package org.kuali.kra.irb.lookup;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.kuali.core.bo.BusinessObject;
import org.kuali.core.lookup.KualiLookupableImpl;
import org.kuali.core.web.struts.form.LookupForm;
import org.kuali.core.web.ui.ResultRow;

public class ProtocolLookupableImpl extends KualiLookupableImpl {

    private static final long serialVersionUID = 1707861010746829601L;

    @Override
    public String getCreateNewUrl() {
//        String url = "../protocolProtocol.do?methodToCall=docHandler&command=initiate&docTypeName=ProtocolDocument";

//        return "<a href=\"" + url + "\"><img src=\"images/tinybutton-createnew.gif\" alt=\"create new\" width=\"70\" height=\"15\"/></a>";
         return "";
    }

    @Override
    public Collection performLookup(LookupForm lookupForm, List<ResultRow> resultTable, boolean bounded) {
        lookupForm.setShowMaintenanceLinks(true);
        return super.performLookup(lookupForm, resultTable, bounded);
    }

    @Override
    public List<BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        // TODO Auto-generated method stub
        return super.getSearchResults(fieldValues);
    }


}
