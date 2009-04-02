package org.kuali.kra.bo;

import java.util.LinkedHashMap;


public class SchoolCode extends KraPersistableBusinessObjectBase {
    
    private Integer schoolCode;
    private String description;
    
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getSchoolCode() {
        return schoolCode;
    }
    public void setSchoolCode(Integer schoolCode) {
        this.schoolCode = schoolCode;
    }

    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap propMap = new LinkedHashMap();
        propMap.put("schoolCode", this.getSchoolCode());
        propMap.put("description", this.getDescription());
        propMap.put("updateTimestamp", this.getUpdateTimestamp());
        propMap.put("updateUser", this.getUpdateUser());
        return propMap;
    }
}