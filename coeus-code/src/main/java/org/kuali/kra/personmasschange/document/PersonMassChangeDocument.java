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
package org.kuali.kra.personmasschange.document;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.custom.DocumentCustomData;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.personmasschange.bo.PersonMassChange;
import org.kuali.kra.personmasschange.service.PersonMassChangeService;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.framework.postprocessor.DocumentRouteStatusChange;
import org.kuali.rice.kew.routeheader.DocumentRouteHeaderValue;
import org.kuali.rice.kew.routeheader.service.RouteHeaderService;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.GlobalVariables;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class PersonMassChangeDocument extends KcTransactionalDocumentBase implements Serializable {

    public static final String DOCUMENT_TYPE_CODE = "PMC";
    
    private static final long serialVersionUID = 4841496352465715699L;

    private static final Log LOG = LogFactory.getLog(ProtocolDocument.class);

    private List<PersonMassChange> personMassChangeList;
    
    private transient PersonMassChangeService personMassChangeService;
    
    public PersonMassChangeDocument() {
        personMassChangeList = new ArrayList<PersonMassChange>();
        PersonMassChange newPersonMassChange = new PersonMassChange();
        newPersonMassChange.setPersonMassChangeDocument(this);
        personMassChangeList.add(newPersonMassChange);
    }
    
    @Override
    public String getDocumentTypeCode() {
        return DOCUMENT_TYPE_CODE;
    }

    public List<PersonMassChange> getPersonMassChangeList() {
        return personMassChangeList;
    }

    public void setPersonMassChangeList(List<PersonMassChange> personMassChangeList) {
        this.personMassChangeList = personMassChangeList;
    }

    public PersonMassChange getPersonMassChange() {
        return personMassChangeList.isEmpty() ? null : personMassChangeList.get(0);
    }

    public void setPersonMassChange(PersonMassChange personMassChange) {
        personMassChangeList.set(0, personMassChange);
    }
    
    @Override
    public void doRouteStatusChange(DocumentRouteStatusChange statusChangeEvent) {
        super.doRouteStatusChange(statusChangeEvent);
        
        if (isFinal(statusChangeEvent)) {
            // Workaround to not save objects as asynchronous 'kr' user
            try {
                String routeHeaderId = getDocumentHeader().getWorkflowDocument().getDocumentId();
                DocumentRouteHeaderValue document = KcServiceLocator.getService(RouteHeaderService.class).getRouteHeader(routeHeaderId);
                String principalId = document.getActionsTaken().get(document.getActionsTaken().size() - 1).getPrincipalId();
                String asyncPrincipalId = GlobalVariables.getUserSession().getPrincipalId();
                String asyncPrincipalName = GlobalVariables.getUserSession().getPrincipalName();
                if (!principalId.equals(asyncPrincipalId)) {
                    KcPerson person = KcServiceLocator.getService(KcPersonService.class).getKcPersonByPersonId(principalId);
                    GlobalVariables.setUserSession(new UserSession(person.getUserName()));
                
                    getPersonMassChangeService().performPersonMassChange(getPersonMassChange());
                    
                    GlobalVariables.setUserSession(new UserSession(asyncPrincipalName));
                }
            } catch (Exception we) {
                LOG.error("Could not access route header id to perform Person Mass Change");
            }
        }
    }
    
    private boolean isFinal(DocumentRouteStatusChange statusChangeEvent) {
        return StringUtils.equals(KewApiConstants.ROUTE_HEADER_FINAL_CD, statusChangeEvent.getNewRouteStatus());
    }
    
    public boolean isProcessComplete() {
        boolean isComplete = false;
        
        if (getDocumentHeader().hasWorkflowDocument()) {
            if ( getDocumentHeader().getWorkflowDocument().isFinal()) {
                isComplete = true;
            }
        }
           
        return isComplete;
    }
    
    public PersonMassChangeService getPersonMassChangeService() {
        if (personMassChangeService == null) {
            personMassChangeService = KcServiceLocator.getService(PersonMassChangeService.class);
        }
        return personMassChangeService;
    }
    
    public void setPersonMassChangeService(PersonMassChangeService personMassChangeService) {
        this.personMassChangeService = personMassChangeService;
    }

    @Override
    public List<? extends DocumentCustomData> getDocumentCustomData() {
        return new ArrayList();
    }

    public String getDocumentBoNumber() {
        return getPersonMassChange().getPersonMassChangeId()+"";
    }
    
}