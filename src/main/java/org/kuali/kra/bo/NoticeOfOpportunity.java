package org.kuali.kra.bo;

import java.util.LinkedHashMap;

/**
 * Class representation of a Notice of Opportunity Business Object
 */
public class NoticeOfOpportunity extends KraPersistableBusinessObjectBase {

    private String noticeOfOpportunityCode;
    private String description;

    /**
     * Retrieves the description attribute
     *
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Assigns the description attribute
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retrieves the noticeOfOpportunityCode attribute
     *
     * @return String
     */
    public String getNoticeOfOpportunityCode() {
        return noticeOfOpportunityCode;
    }

    /**
     * Assigns the noticeOfOpportunityCode attribute
     * @param noticeOfOpportunityCode
     */
    public void setNoticeOfOpportunityCode(String noticeOfOpportunityCode) {
        this.noticeOfOpportunityCode = noticeOfOpportunityCode;
    }

    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap propMap = new LinkedHashMap();
        propMap.put("noticeOfOpportunityCode", this.getNoticeOfOpportunityCode().toString());
        propMap.put("description", this.getDescription());
        propMap.put("updateTimestamp", this.getUpdateTimestamp());
        propMap.put("updateUser", this.getUpdateUser());
        return propMap;
    }
}
