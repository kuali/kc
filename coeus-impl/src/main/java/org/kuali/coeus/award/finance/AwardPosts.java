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
package org.kuali.coeus.award.finance;

import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;

import javax.persistence.*;

@Entity
@Table(name="AWARD_POSTS")
public class AwardPosts extends KcPersistableBusinessObjectBase {

    @PortableSequenceGenerator(name = "SEQ_AWARD_POSTS_ID")
    @GeneratedValue(generator = "SEQ_AWARD_POSTS_ID")
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;
    @Column(name = "AWARD_ID")
    private Long awardId;
    @Column(name = "DOCUMENT_NUMBER")
    private String documentNumber;
    @Column(name = "AWARD_FAMILY")
    private String awardFamily;
    @Column(name="POSTED")
    @Convert(converter = BooleanYNConverter.class)
    private boolean posted;
    @Column(name="ACTIVE")
    @Convert(converter = BooleanYNConverter.class)
    private boolean active;


    public AwardPosts() {
        posted = Boolean.TRUE;
        active = Boolean.TRUE;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getAwardFamily() {
        return awardFamily;
    }

    public void setAwardFamily(String awardFamily) {
        this.awardFamily = awardFamily;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getAwardId() {
        return awardId;
    }

    public void setAwardId(Long awardId) {
        this.awardId = awardId;
    }

    public boolean isPosted() {
        return posted;
    }

    public void setPosted(boolean posted) {
        this.posted = posted;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
