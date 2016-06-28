package org.kuali.coeus.common.framework.person.citi;


import org.kuali.rice.krad.bo.TransientBusinessObjectBase;

public class PersonTrainingCitiCourse extends TransientBusinessObjectBase {
    private String groupId;
    private String group;
    private String stageNumber;
    private String stage;

    public PersonTrainingCitiCourse() {
    }

    public PersonTrainingCitiCourse(String groupId, String group, String stageNumber, String stage) {
        this.groupId = groupId;
        this.group = group;
        this.stageNumber = stageNumber;
        this.stage = stage;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getStageNumber() {
        return stageNumber;
    }

    public void setStageNumber(String stageNumber) {
        this.stageNumber = stageNumber;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getTitle() {
        return getGroupId() + " " + getStageNumber();
    }
}
