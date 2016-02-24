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
package org.kuali.kra.coi;

import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;

import java.util.List;

public  abstract class DisclosureReporter extends KcPersistableBusinessObjectBase {

    private transient KcPersonService kcPersonService;
    private transient KcPerson reporter;
    private int selectedUnit;

    
    public abstract String getPersonId();
 //   public abstract void setPersonId(String personId);
    public abstract List<? extends DisclosureReporterUnit> getDisclosureReporterUnits();

    public KcPerson getReporter() {
        if (reporter == null && getPersonId() != null) {
            reporter = getKcPersonService().getKcPersonByPersonId(getPersonId());
        }
        return reporter;
    }

    /**
     * Gets the KC Person Service.
     * 
     * @return KC Person Service.
     */
    protected KcPersonService getKcPersonService() {
        if (this.kcPersonService == null) {
            this.kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        }

        return this.kcPersonService;
    }

    public int getSelectedUnit() {
        return selectedUnit;
    }

    public void setSelectedUnit(int selectedUnit) {
        this.selectedUnit = selectedUnit;
    }

}
