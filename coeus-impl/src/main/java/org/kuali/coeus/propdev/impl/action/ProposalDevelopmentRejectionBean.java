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

public class ProposalDevelopmentRejectionBean implements Serializable {


    private static final long serialVersionUID = 4081027512143550976L;
    
    private String rejectReason;
    transient private MultipartFile rejectFile;
    

    public ProposalDevelopmentRejectionBean() {
        
    }

    public MultipartFile getRejectFile() {
        return rejectFile;
    }

    public void setRejectFile(MultipartFile rejectFile) {
        this.rejectFile = rejectFile;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }
}
