package org.kuali.kra.bo;

import javax.persistence.Version;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;

import java.util.LinkedHashMap;

/**
 * Class representation of a Notice of Opportunity Business Object
 */
@Entity
@Table(name="NOTICE_OF_OPPORTUNITY")
public class NoticeOfOpportunity extends KraPersistableBusinessObjectBase {

    @Id
	@Column(name="NOTICE_OF_OPPORTUNITY_CODE")
	private String noticeOfOpportunityCode;
    @Column(name="DESCRIPTION")
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

