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
package org.kuali.coeus.common.framework.auth.docperm;


import org.kuali.coeus.sys.api.model.DocumentNumbered;
import org.kuali.coeus.sys.api.model.Identifiable;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "DOCUMENT_ACCESS")
public class DocumentAccess extends KcPersistableBusinessObjectBase implements Identifiable, DocumentNumbered {

    @PortableSequenceGenerator(name = "SEQ_DOCUMENT_ACCESS_ID")
    @GeneratedValue(generator = "SEQ_DOCUMENT_ACCESS_ID")
    @Id
    @Column(name = "DOC_ACCESS_ID")
    private String id;

    @Column(name = "DOC_HDR_ID", length=14)
    private String documentNumber;

    @Column(name = "PRNCPL_ID")
    private String principalId;

    @Column(name = "ROLE_NM")
    private String roleName;

    @Column(name = "NMSPC_CD")
    private String namespaceCode;

    public DocumentAccess() {
        super();
    }

    public DocumentAccess(String documentNumber, String principalId, String roleName, String namespaceCode) {
        this.documentNumber = documentNumber;
        this.principalId = principalId;
        this.roleName = roleName;
        this.namespaceCode = namespaceCode;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(String principalId) {
        this.principalId = principalId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getNamespaceCode() {
        return namespaceCode;
    }

    public void setNamespaceCode(String namespaceCode) {
        this.namespaceCode = namespaceCode;
    }
}
