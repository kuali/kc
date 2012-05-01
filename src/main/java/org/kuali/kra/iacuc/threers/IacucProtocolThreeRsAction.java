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
package org.kuali.kra.iacuc.threers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolAction;
import org.kuali.kra.iacuc.IacucProtocolForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.protocol.ProtocolForm;
import org.kuali.rice.krad.util.KRADConstants;
import org.springframework.util.CollectionUtils;

public class IacucProtocolThreeRsAction extends IacucProtocolAction {


    /**
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#execute(org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        ActionForward actionForward = super.execute(mapping, form, request, response);
        
        return actionForward;
    }
    
    public ActionForward addAlternateSearchDatabase (ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucAlternateSearch altSearch = protocolForm.getIacucAlternateSearchHelper().getNewAlternateSearch();
        List<String> newDatabases = protocolForm.getIacucAlternateSearchHelper().getNewDatabases();
        
        //TODO fix this...
        altSearch.setSearchDate(new Date());
               
        List<IacucProtocolAlternateSearchDatabase> databases = new ArrayList<IacucProtocolAlternateSearchDatabase>();
        for (String newDb : newDatabases) {
            databases.add(createAltSearchDB(newDb));
        }
        
        altSearch.setDatabases(databases);
        
        List<IacucAlternateSearch> searches = ((IacucProtocol)protocolForm.getProtocolDocument().getProtocol()).getIacucAlternateSearches();
        if (searches == null) {
          searches = new ArrayList<IacucAlternateSearch>(); 
        }
                
        searches.add(altSearch);
        
        ((IacucProtocol)protocolForm.getProtocolDocument().getProtocol()).setIacucAlternateSearches(searches);
        
        getDocumentService().saveDocument(protocolForm.getProtocolDocument());
        
        return mapping.findForward(Constants.MAPPING_BASIC);        
    }
    
    private IacucProtocolAlternateSearchDatabase createAltSearchDB(String dbName) {
        IacucProtocolAlternateSearchDatabase db = new IacucProtocolAlternateSearchDatabase();
        db.setAlternateSearchDatabaseName(dbName);
        return db;
    }
    
    public ActionForward deleteAlternateSearch (ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        int commentIndex = getAlternateSearchIndexNumber(parameterName, "deleteAlternateSearch");

        return mapping.findForward(Constants.MAPPING_BASIC);        
    }
    
    private int getAlternateSearchIndexNumber(String parameterName, String actionMethodToCall) {
        int result = -1;
        if (StringUtils.isBlank(parameterName)||parameterName.indexOf("."+actionMethodToCall+".") == -1) {
            throw new IllegalArgumentException(
                    String.format("getAlternateSearchIndex expects a non-empty value for parameterName parameter, "+
                            "and it must contain as a substring the parameter actionMethodToCall. "+
                            "The passed values were (%s,%s)."
                            ,parameterName,actionMethodToCall)
                    );
        }
        String idxNmbr = StringUtils.substringBetween(parameterName, ".line.", ".anchor");
        result = Integer.parseInt(idxNmbr);
        return result;
    }
}
