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

import java.io.Serializable;

import org.kuali.kra.coi.CoiReviewer;
import org.kuali.kra.coi.CoiUserRole;

public class DisclosureActionHelper implements Serializable {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -4602681870006425531L;
    
    private CoiUserRole newCoiUserRole;

    public CoiUserRole getNewCoiUserRole() {
        return newCoiUserRole;
    }

    public void setNewCoiUserRole(CoiUserRole newCoiUserRole) {
        this.newCoiUserRole = newCoiUserRole;
    }


    

}
