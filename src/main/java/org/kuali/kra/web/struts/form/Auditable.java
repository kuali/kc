/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.web.struts.form;

/**
 * Defines the methods required for an object that can be "audited".
 */
public interface Auditable {

    /**
     * Gets whether or not audit has been activated.
     * @return true if audit is activated false if not.
     */
    public boolean isAuditActivated();

    /**
     * 
     * Sets whether or not audit has been activated.
     * @param auditActivated true if audit is activated false if not.
     */
    public void setAuditActivated(boolean auditActivated);
}
