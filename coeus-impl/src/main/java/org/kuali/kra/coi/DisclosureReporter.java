/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
