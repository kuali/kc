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
package org.kuali.kra.coi.actions;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiDisclosureDocument;
import org.kuali.kra.coi.CoiDisclosureForm;
import org.kuali.kra.coi.certification.SubmitDisclosureAction;
import org.kuali.kra.coi.CoiUserRole;

/**
 * 
 * This is an interface for methods needed for business logic in disclosure actions page
 */
public interface CoiDisclosureActionService {

    /**
     * 
     * This method implements the business logic for approving disclosure
     * @param coiDisclosure
     * @param coiDisclosureStatusCode
     */
    void approveDisclosure(CoiDisclosure coiDisclosure, String coiDisclosureStatusCode);
    
    /**
     * This method adds a coi reviewer to the disclosure
     * @param coiDisclosure
     * @param coiUserRole
     */
    void addCoiUserRole(CoiDisclosure coiDisclosure, CoiUserRole coiUserRole);
    
    /**
     * This method removes a coi reviewer from the disclosure
     * @param coiDisclosure
     * @param index
     */
    void deleteCoiUserRole(CoiDisclosure coiDisclosure, int index);

    /**
     * This method submits a disclosure to workflow
     * @param coiDisclosure
     * @param submitDisclosureAction
     */
    void submitToWorkflow(CoiDisclosureDocument coiDisclosureDocument, CoiDisclosureForm coiDisclosureForm, 
                          SubmitDisclosureAction submitDisclosureAction);

    public ActionForward sendCertificationNotifications(CoiDisclosureDocument coiDisclosureDocument, CoiDisclosureForm coiDisclosureForm, 
                                                        SubmitDisclosureAction submitDisclosureAction, ActionMapping mapping);

}
