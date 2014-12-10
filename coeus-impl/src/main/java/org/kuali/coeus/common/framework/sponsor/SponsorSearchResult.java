package org.kuali.coeus.common.framework.sponsor;

public class SponsorSearchResult {
    private String sponsorCode;
    private String sponsorName;

    public SponsorSearchResult() {
    }

    public SponsorSearchResult(String sponsorCode, String sponsorName) {
        this.sponsorCode = sponsorCode;
        this.sponsorName = sponsorName;
    }

    public String getSponsorCode() {
        return sponsorCode;
    }

    public void setSponsorCode(String sponsorCode) {
        this.sponsorCode = sponsorCode;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }
}
