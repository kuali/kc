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
package org.kuali.kra.institutionalproposal.home;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;


public class InstitutionalProposalAttachmentType extends KcPersistableBusinessObjectBase  {
	
	private static final long serialVersionUID = 391856374612054089L;

    private Integer attachmentTypeCode;

    private String description;
    
    private boolean allowMultiple;

    /**
     * empty ctor to satisfy JavaBean convention.
     */
    public InstitutionalProposalAttachmentType() {
        super();
    }

    public Integer getAttachmentTypeCode() {
        return this.attachmentTypeCode;
    }

    public void setAttachmentTypeCode(Integer attachmentTypeCode) {
        this.attachmentTypeCode = attachmentTypeCode;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public boolean getAllowMultiple() {
		return allowMultiple;
	}

	public void setAllowMultiple(boolean allowMultiple) {
		this.allowMultiple = allowMultiple;
	}
}
