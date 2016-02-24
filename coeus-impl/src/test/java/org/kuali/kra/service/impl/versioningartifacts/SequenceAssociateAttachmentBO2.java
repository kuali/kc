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
package org.kuali.kra.service.impl.versioningartifacts;

import org.kuali.coeus.common.framework.version.sequence.associate.SeparatelySequenceableAssociate;

import java.util.Random;

/**
 * This class represents an attachment type that needs to be managed in a 
 * many-to-many way to prevent unnecessary copying of attachments that can 
 * be very large
 */
public class SequenceAssociateAttachmentBO2 implements SeparatelySequenceableAssociate {
    private static final long serialVersionUID = -1764304273143080320L;
    
    private Long attachmentId;
    private String name;
    private Integer sequenceNumber = 0;

    public SequenceAssociateAttachmentBO2(String name) {
        this.name = name;
        setAttachmentId(new Random().nextLong());
    }
        
    public Long getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(Long attachmentId) {
        this.attachmentId = attachmentId;
    }
    
    @Override
    public void resetPersistenceState() {
        setAttachmentId(null);
    }
    
    @Override
    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void incrementSequenceNumber() {
        sequenceNumber++;   
     }
}
