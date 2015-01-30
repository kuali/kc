/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.common.framework.print;

import org.kuali.coeus.sys.api.model.KcFile;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

import javax.persistence.*;

/**
 * 
 * This class helps create the foundation of attachment data sources.
 */
@MappedSuperclass
public abstract class AttachmentDataSource extends KcPersistableBusinessObjectBase implements KcFile {

    @Column(name = "FILE_NAME")
    private String name;

    @Column(name = "CONTENT_TYPE")
    private String type;

    @Column(name = "DATA")
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private byte[] data;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
