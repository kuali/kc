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
package org.kuali.kra.award.notification;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

public class AwardNoticePrintout extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = 1L;

    private Long awardNoticeId;
    private Long awardId;
    private String awardNumber;
    private String accountNumber;
    private String unitNumber;
    private String pdfContent;

    public AwardNoticePrintout() {
        super();
    }

    public AwardNoticePrintout(Long awardId, String awardNumber, String accountNumber) {
        this();
        this.awardId = awardId;
        this.awardNumber = awardNumber;
        this.accountNumber = accountNumber;
    }

    public Long getAwardNoticeId() {
        return awardNoticeId;
    }

    public void setAwardNoticeId(Long awardNoticeId) {
        this.awardNoticeId = awardNoticeId;
    }

    public Long getAwardId() {
        return awardId;
    }

    public void setAwardId(Long awardId) {
        this.awardId = awardId;
    }

    public String getAwardNumber() {
        return awardNumber;
    }

    public void setAwardNumber(String awardNumber) {
        this.awardNumber = awardNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public byte[] getPdfContent() throws DecoderException {
        return Hex.decodeHex(pdfContent.toCharArray());
    }

    public void setPdfContent(byte[] pdfContent) {
        this.pdfContent = Hex.encodeHexString(pdfContent);
    }
}
