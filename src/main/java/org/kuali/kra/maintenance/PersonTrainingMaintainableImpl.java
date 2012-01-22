/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.maintenance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.PersonTraining;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.Maintainable;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
import org.kuali.rice.kns.web.ui.Section;
import org.kuali.rice.krad.util.KRADConstants;

/**
 * 
 * This class is to initialize for person training maintenance document.
 */
public class PersonTrainingMaintainableImpl extends KraMaintainableImpl {
    private static final String COLUMN = ":";

    /**
     * This is a hook for initializing the BO from the maintenance framework. It initializes the {@link Explanation}s collection.
     * 
     * @param generateDefaultValues true for initialization
     */
    @Override
    public void setGenerateDefaultValues(String docTypeName) {
        initTrainingNumber();
        super.setGenerateDefaultValues(docTypeName);
    }

    /**
     * This is just trying to populate training number for person training
     * 
     * @see org.kuali.core.maintenance.KualiMaintainableImpl#getCoreSections(org.kuali.core.maintenance.Maintainable)
     */
    @Override
    public List<Section> getCoreSections(MaintenanceDocument document, Maintainable oldMaintainable) {
        if (!document.getNewMaintainableObject().getMaintenanceAction().equals(KRADConstants.MAINTENANCE_EDIT_ACTION)) {
            if (document.getDocumentHeader().getWorkflowDocument().isSaved() || document.getDocumentHeader().getWorkflowDocument().isInitiated()) {
            initTrainingNumber();
            }
        }
        return super.getCoreSections(document, oldMaintainable);
    }

    /**
     * Gets the {@link PersonTrainin}
     * 
     * @return
     */
    public PersonTraining getPersonTraining() {
        return (PersonTraining) getBusinessObject();
    }

    /**
     * Method to initialize PersonTraining with next training number.
     * 
     */
    private void initTrainingNumber() {
        if (getPersonTraining().getPersonTrainingId() == null) {
            Integer trainingNumber = 1;
            if (StringUtils.isNotBlank(getPersonTraining().getPersonId())) {
                Map fieldValues = new HashMap();
                fieldValues.put("personId", getPersonTraining().getPersonId());
                List<PersonTraining> personTrainings = (List<PersonTraining>) getBusinessObjectService().findMatching(
                        PersonTraining.class, fieldValues);
                if (!personTrainings.isEmpty()) {
                    trainingNumber = personTrainings.size() + 1;
                }
            }
            getPersonTraining().setTrainingNumber(trainingNumber);
        }
    }

    public List getSections(MaintenanceDocument document, Maintainable oldMaintainable) {
        List<Section> sections = super.getSections(document, oldMaintainable);
        String keyName = "personId";
        for (Section section : sections) {
            for (Row row : section.getRows()) {
                for (Field field : row.getFields()) {
                    if ("personId".equals(field.getPropertyName())) {
                        if (document.isEdit()) {
                            field.setReadOnly(true);
                        } else {
                            field.setFieldConversions(keyName + COLUMN + field.getPropertyName());
                            field.setLookupParameters(field.getPropertyName() + COLUMN + keyName);
                            field.setInquiryParameters(field.getPropertyName() + COLUMN + keyName);
                            field.setQuickFinderClassNameImpl("org.kuali.kra.bo.KcPerson");
                            field.setFieldDirectInquiryEnabled(true);
                        }
                    }
                }
            }
        }
        return sections;
    }

}
