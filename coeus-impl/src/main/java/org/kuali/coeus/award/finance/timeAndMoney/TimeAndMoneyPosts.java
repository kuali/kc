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

package org.kuali.coeus.award.finance.timeAndMoney;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name="TIME_AND_MONEY_POSTS")
public class TimeAndMoneyPosts extends KcPersistableBusinessObjectBase {

    @PortableSequenceGenerator(name = "SEQ_TIME_AND_MONEY_POSTS_ID")
    @GeneratedValue(generator = "SEQ_TIME_AND_MONEY_POSTS_ID")
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "AWARD_ID")
    private Long awardId;
    @Column(name="ACTIVE")
    @Convert(converter = BooleanYNConverter.class)
    private boolean active;
    @Column(name = "DOCUMENT_NUMBER")
    private String documentNumber;
    @Column(name = "AWARD_FAMILY")
    private String awardFamily;

    public TimeAndMoneyPosts() {
        this.active = Boolean.TRUE;
    }

    public String getAwardFamily() {
        return awardFamily;
    }

    public void setAwardFamily(String awardFamily) {
        this.awardFamily = awardFamily;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAwardId() {
        return awardId;
    }

    public void setAwardId(Long awardId) {
        this.awardId = awardId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
