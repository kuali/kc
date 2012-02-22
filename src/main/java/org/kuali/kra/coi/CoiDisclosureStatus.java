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
package org.kuali.kra.coi;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.irb.actions.ProtocolActionType;

public class CoiDisclosureStatus extends KraPersistableBusinessObjectBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -204509679832775700L;
  
    public static final String IN_PROGRESS = "1";
    public static final String ROUTED_FOR_REVIEW = "2";
    public static final String APPROVED = "3";
    public static final String DISAPPROVED = "4";

    private String coiDisclosureStatusCode;

    private String description;

    public String getCoiDisclosureStatusCode() {
        return coiDisclosureStatusCode;
    }

    public void setCoiDisclosureStatusCode(String coiDisclosureStatusCode) {
        this.coiDisclosureStatusCode = coiDisclosureStatusCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
