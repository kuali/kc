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
package org.kuali.kra.irb.actions.grantexemption;

import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.correspondence.AbstractProtocolActionsCorrespondence;

/**
 * 
 * This class deals with the template and the printing for the grant exemption protocol action.
 */
public class GrantExemptionCorrespondence extends AbstractProtocolActionsCorrespondence {

    public static final long serialVersionUID = 1234563880;
    @Override
    public String getProtocolActionType() {
        return ProtocolActionType.GRANT_EXEMPTION;
    }

}
