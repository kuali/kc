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
package org.kuali.coeus.propdev.impl.action;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public class ProposalDevelopmentActionBean implements Serializable {

    private static final long serialVersionUID = 4081027512143550976L;
    
    private String actionReason;
    private  transient MultipartFile actionFile;

    public MultipartFile getActionFile() {
        return actionFile;
    }

    public void setActionFile(MultipartFile actionFile) {
        this.actionFile = actionFile;
    }

    public String getActionReason() {
        return actionReason;
    }

    public void setActionReason(String actionReason) {
        this.actionReason = actionReason;
    }
}
