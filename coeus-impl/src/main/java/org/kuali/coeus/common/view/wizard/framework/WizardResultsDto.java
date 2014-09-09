package org.kuali.coeus.common.view.wizard.framework;

import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.rice.kim.impl.role.RoleBo;

public class WizardResultsDto {
    private boolean selected;
    private RoleBo role;
    private Rolodex rolodex;
    private KcPerson kcPerson;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public RoleBo getRole() {
        return role;
    }

    public void setRole(RoleBo role) {
        this.role = role;
    }

    public Rolodex getRolodex() {
        return rolodex;
    }

    public void setRolodex(Rolodex rolodex) {
        this.rolodex = rolodex;
    }

    public KcPerson getKcPerson() {
        return kcPerson;
    }

    public void setKcPerson(KcPerson kcPerson) {
        this.kcPerson = kcPerson;
    }
}
