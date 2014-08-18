/*
 * Copyright 2005-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.iacuc.actions.submit;

import org.kuali.kra.protocol.actions.submit.ValidProtocolActionActionBase;

/**
 * This class represents the action follow up mapping as it exists in coeus.
 * A follow up action is mapped to an action via this bo.  When the user takes the action
 * protocolActionTypeCode they will be prompted to complete each of the actions associated with it 
 * via this object.  The properties protocolActionTypeCode, actionNumber, and followUpActionCode form
 * a unique key on the underlying table.
 * 
 */
public class IacucValidProtocolActionAction extends ValidProtocolActionActionBase {


    private static final long serialVersionUID = 920783769885103992L;

}
