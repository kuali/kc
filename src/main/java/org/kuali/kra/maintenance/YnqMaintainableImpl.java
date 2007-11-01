/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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

import java.util.ArrayList;
import java.util.List;

import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.util.AssertionUtils;
import org.kuali.kra.bo.Ynq;
import org.kuali.kra.bo.YnqExplanation;
import org.kuali.kra.bo.YnqExplanationType;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.YnqService;
public class YnqMaintainableImpl extends KraMaintainableImpl {

    /**
     * This is a hook for initializing the BO from the maintenance framework.
     * It initializes the {@link Explanation}s collection.
     *
     * @param generateDefaultValues true for initialization
     */
    @Override
    public void setGenerateDefaultValues(boolean generateDefaultValues) {
        if (generateDefaultValues) {
            initExplanation();
        }
        super.setGenerateDefaultValues(generateDefaultValues);
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
        AssertionUtils.assertThat(ynqExplanations.isEmpty());
        
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
