/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.sys.framework.validation;

import org.kuali.rice.krad.document.Document;

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

    public Document getDocument();
}
