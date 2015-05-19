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

    public AwardNoticePrintout(Long awardId, String awardNumber, String accountNumber, String unitNumber) {
        this();
        this.awardId = awardId;
        this.awardNumber = awardNumber;
        this.accountNumber = accountNumber;
        this.unitNumber = unitNumber;
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
