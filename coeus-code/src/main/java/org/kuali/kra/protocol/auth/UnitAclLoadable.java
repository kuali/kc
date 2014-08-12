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
package org.kuali.kra.protocol.auth;

import org.kuali.coeus.common.framework.auth.perm.Permissionable;

/**
 * This interface defines a document class to be load able with a unit access control list.
 */
public interface UnitAclLoadable extends Permissionable {
    /**
     * Returns the unit number of the unit to which the document belongs to.
     * 
     * @return unitNumber of document
     */
    String getDocumentUnitNumber();
    
    /**
     * Returns the type code of the document (i.e. Proposal, Protocol, etc).
     * 
     * @return typeCode of document
     */
    String getDocumentRoleTypeCode();
}
