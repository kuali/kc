/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.maintenance;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.kuali.kra.bo.Ynq;
import org.kuali.kra.bo.YnqExplanation;
import org.kuali.kra.bo.YnqExplanationType;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.YnqService;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.Maintainable;
import org.kuali.rice.kns.web.ui.Section;

public class YnqMaintainableImpl extends KraMaintainableImpl {
    private static final long serialVersionUID = -8242765028364970138L;

    /**
     * This is a hook for initializing the BO from the maintenance framework.
     * It initializes the {@link Explanation}s collection.
     *
     * @param generateDefaultValues true for initialization
     */
    @Override
    public void setGenerateDefaultValues(String docTypeName) {
        initExplanation();
        super.setGenerateDefaultValues(docTypeName);
    }

    /**
     * This is just trying to populate existing ynq that has no explanation
     * @see org.kuali.core.maintenance.KualiMaintainableImpl#getCoreSections(org.kuali.core.maintenance.Maintainable)
     */
    @Override
    public List<Section> getCoreSections(MaintenanceDocument document, Maintainable oldMaintainable) {
        Ynq ynq = ((Ynq)getBusinessObject());
        if (CollectionUtils.isEmpty(ynq.getYnqExplanations())) {
            initExplanation();
        }
        return super.getCoreSections(document, oldMaintainable);
    }

    /**
     * Gets the {@link Ynq}
     * 
     * @return
     */
    public Ynq getYnq() {
        return (Ynq) getBusinessObject();
    }

    /**
     * Method to initialize YNQ with Explanation types.
     *
     */
    private void initExplanation() {
        List<YnqExplanation> ynqExplanations = getYnq().getYnqExplanations();
        if (!ynqExplanations.isEmpty()) {
            throw new AssertionError();
        }
        
        List<YnqExplanationType> ynqExplanationTypes = getYnqExplanationTypes();
        for (YnqExplanationType type : ynqExplanationTypes) {
            YnqExplanation ynqExplanation = new YnqExplanation();
            ynqExplanation.setExplanationType(type.getExplanationType());
            ynqExplanation.setYnqExplanationType(type); 
            ynqExplanations.add(ynqExplanation);
        }
    }

    private List<YnqExplanationType> getYnqExplanationTypes() {
         List<YnqExplanationType> ynqExplanationTypes = (KraServiceLocator.getService(YnqService.class).getYnqExplanationTypes());
         return ynqExplanationTypes;
     }

}
