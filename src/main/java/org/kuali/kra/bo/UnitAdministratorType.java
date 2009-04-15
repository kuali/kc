package org.kuali.kra.bo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.kra.award.bo.ContactRole;

/**
 * This class models the UnitAdministratorType
 */
public class UnitAdministratorType extends KraPersistableBusinessObjectBase implements ContactRole {
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -8872381393239718701L;
    
    private String unitAdministratorTypeCode;
    private String description;
    private List<UnitAdministrator> unitAdministrators;

    private UnitContactType unitContactType;

    public UnitAdministratorType(){
        super();
        unitAdministrators = new ArrayList<UnitAdministrator>();

    }

    public String getUnitAdministratorTypeCode() {
        return unitAdministratorTypeCode;
    }

    public void setUnitAdministratorTypeCode(String unitAdministratorTypeCode) {
        this.unitAdministratorTypeCode = unitAdministratorTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("unitAdministratorTypeCode", getUnitAdministratorTypeCode());
        hashMap.put("description", getDescription());
        return hashMap;
    }

    public List<UnitAdministrator> getUnitAdministrators() {
        return unitAdministrators;
    }

    public void setUnitAdministrators(List<UnitAdministrator> unitAdministrators) {
        this.unitAdministrators = unitAdministrators;
    }

    public String getRoleCode() {
        return getUnitAdministratorTypeCode();
    }

    public String getRoleDescription() {
        return getDescription();
    }
    
    /**
     * @return
     */
    public UnitContactType getUnitContactType() { 
        return unitContactType; 
    }

    /**
     * @param unitContactType
     */
    public void setUnitContactType(UnitContactType unitContactType) {
        this.unitContactType = unitContactType;
    }
}
