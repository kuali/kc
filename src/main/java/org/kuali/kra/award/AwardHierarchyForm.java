/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.award;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.AwardHierarchyUIService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.web.struts.form.KualiForm;

/**
 * 
 * This class represents the Award Form Struts class.
 */
public class AwardHierarchyForm extends KualiForm {
    
    private String awardHierarchy;
    private String awardNumber;
    private String addRA;    
    private String deletedRas;
    private String controlForAwardHierarchyView;
    
    
    public String getAwardHierarchy() {
        awardHierarchy = "";
        if (StringUtils.isNotBlank(addRA) && addRA.equals("Y")) {
            if (getAwardHierarchyUIService().doesAwardHierarchyExist(awardNumber, deletedRas)) {
                setAwardHierarchy("<h3>true</h3>");
            }else {
                setAwardHierarchy("<h3>false</h3>");
            }
        } else if (StringUtils.isNotBlank(addRA) && addRA.equals("S")) {
            //KraServiceLocator.getService(AwardHierarchyUIService.class).saveResearchAreas(sqlScripts);
            String error = (String) GlobalVariables.getUserSession().retrieveObject("raError");
            if (StringUtils.isNotBlank(error)) {
                setAwardHierarchy("<h3>" + error + "</h3>");
                GlobalVariables.getUserSession().addObject("raError", (Object) null);
            } else {
                setAwardHierarchy("<h3>Success</h3>");
            }
        } else if (awardNumber!=null && StringUtils.isNotBlank(addRA) && addRA.equals("E")){
            setAwardHierarchy(getAwardHierarchyUIService().getSubAwardHierarchiesForTreeView(awardNumber));
        } else if (awardNumber!=null && StringUtils.isNotBlank(addRA) && addRA.equals("N")){
            setAwardHierarchy(getAwardHierarchyUIService().getRootAwardNode(awardNumber));
        }
        return awardHierarchy;
    }
    
    /**
     * This method...
     * @return
     */
    private AwardHierarchyUIService getAwardHierarchyUIService() {
        return KraServiceLocator.getService(AwardHierarchyUIService.class);
    }

    /**
     * Gets the awardNumber attribute. 
     * @return Returns the awardNumber.
     */
    public String getAwardNumber() {
        return awardNumber;
    }

    /**
     * Sets the awardNumber attribute value.
     * @param awardNumber The awardNumber to set.
     */
    public void setAwardNumber(String awardNumber) {
        this.awardNumber = awardNumber;
    }

    /**
     * Gets the addRA attribute. 
     * @return Returns the addRA.
     */
    public String getAddRA() {
        return addRA;
    }

    /**
     * Sets the addRA attribute value.
     * @param addRA The addRA to set.
     */
    public void setAddRA(String addRA) {
        this.addRA = addRA;
    }

    /**
     * Gets the deletedRas attribute. 
     * @return Returns the deletedRas.
     */
    public String getDeletedRas() {
        return deletedRas;
    }

    /**
     * Sets the deletedRas attribute value.
     * @param deletedRas The deletedRas to set.
     */
    public void setDeletedRas(String deletedRas) {
        this.deletedRas = deletedRas;
    }

    /**
     * Gets the controlForAwardHierarchyView attribute. 
     * @return Returns the controlForAwardHierarchyView.
     */
    public String getControlForAwardHierarchyView() {
        return controlForAwardHierarchyView;
    }

    /**
     * Sets the controlForAwardHierarchyView attribute value.
     * @param controlForAwardHierarchyView The controlForAwardHierarchyView to set.
     */
    public void setControlForAwardHierarchyView(String controlForAwardHierarchyView) {
        this.controlForAwardHierarchyView = controlForAwardHierarchyView;
    }

    /**
     * Sets the awardHierarchy attribute value.
     * @param awardHierarchy The awardHierarchy to set.
     */
    public void setAwardHierarchy(String awardHierarchy) {
        this.awardHierarchy = awardHierarchy;
    }
    
}
