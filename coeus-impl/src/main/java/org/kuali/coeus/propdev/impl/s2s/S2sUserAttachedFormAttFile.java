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
package org.kuali.coeus.propdev.impl.s2s;

import org.kuali.coeus.propdev.api.s2s.S2sUserAttachedFormAttFileContract;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

import javax.persistence.*;

@Entity
@Table(name = "S2S_USER_ATTD_FORM_ATT_FILE")
public class S2sUserAttachedFormAttFile extends KcPersistableBusinessObjectBase implements S2sUserAttachedFormAttFileContract { 

    private static final long serialVersionUID = 435752267453958277L;

    @PortableSequenceGenerator(name = "SEQ_S2S_USR_ATD_FRM_ATT_FLE_ID")
    @GeneratedValue(generator = "SEQ_S2S_USR_ATD_FRM_ATT_FLE_ID")
    @Id
    @Column(name = "S2S_USER_ATTD_FORM_ATT_FILE_ID")
    private Long id;

    @Column(name = "ATTACHMENT")
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] attachment;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "S2S_USER_ATTACHED_FORM_ATT_ID")
    private S2sUserAttachedFormAtt s2sUserAttachedFormAtt;

    @Override
    public Long getS2sUserAttachedFormAttId() {
        return s2sUserAttachedFormAtt != null ? s2sUserAttachedFormAtt.getId() : null;
    }

    @Override
    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }

    @Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public S2sUserAttachedFormAtt getS2sUserAttachedFormAtt() {
        return s2sUserAttachedFormAtt;
    }

    public void setS2sUserAttachedFormAtt(S2sUserAttachedFormAtt s2sUserAttachedFormAtt) {
        this.s2sUserAttachedFormAtt = s2sUserAttachedFormAtt;
    }
}
