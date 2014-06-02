package org.kuali.coeus.propdev.impl.person.creditsplit;

import java.util.ArrayList;
import java.util.List;

public class ProposalCreditSplitListDto {
    private String description;
    private String lineType;
    private List<Object> creditSplits;

    public ProposalCreditSplitListDto() {
        super();
        creditSplits = new ArrayList<Object>();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Object> getCreditSplits() {
        return creditSplits;
    }

    public void setCreditSplits(List<Object> creditSplits) {
        this.creditSplits = creditSplits;
    }

    public String getLineType() {
        return lineType;
    }

    public void setLineType(String lineType) {
        this.lineType = lineType;
    }
}
