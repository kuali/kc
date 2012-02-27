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
package org.kuali.kra.coi;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;

public class CoiDisclosureEventType extends KraPersistableBusinessObjectBase implements MutableInactivatable { 
    

    /**
     * Comment for <code>serialVersionUID</code>
     */
    public static final long serialVersionUID = -933728957419448190L;
    public static final String ANNUAL = "14";
    public static final String AWARD = "1";
    public static final String DEVELOPMENT_PROPOSAL = "2";
    public static final String INSTITUTIONAL_PROPOSAL = "10";
    public static final String IRB_PROTOCOL = "3";
    public static final String IACUC_PROTOCOL = "4";
    public static final String NEW = "5";
    public static final String UPDATE = "6";
    public static final String OTHER = "7";
    public static final String MANUAL_AWARD = "11";
    public static final String MANUAL_DEVELOPMENT_PROPOSAL = "12";
    public static final String MANUAL_IRB_PROTOCOL = "13";
    
    private String eventTypeCode; 
    private String description;
    private boolean excludeFromMasterDisclosure;
    private boolean active;
    
    private boolean useShortTextField1;
    private boolean useShortTextField2;
    private boolean useShortTextField3;
    private boolean useLongTextField1;
    private boolean useLongTextField2;
    private boolean useLongTextField3;
    private boolean useNumberField1;
    private boolean useNumberField2;
    private boolean useDateField1;
    private boolean useDateField2;
    
    private boolean requireShortTextField1;
    private boolean requireShortTextField2;
    private boolean requireShortTextField3;
    private boolean requireLongTextField1;
    private boolean requireLongTextField2;
    private boolean requireLongTextField3;
    private boolean requireNumberField1;
    private boolean requireNumberField2;
    private boolean requireDateField1;
    private boolean requireDateField2;
    
    private String shortTextField1Label;
    private String shortTextField2Label;
    private String shortTextField3Label;
    private String longTextField1Label;
    private String longTextField2Label;
    private String longTextField3Label;
    private String numberField1Label;
    private String numberField2Label;
    private String dateField1Label;
    private String dateField2Label;
    
    private boolean useSelectBox1;
    private boolean requireSelectBox1;
    private String selectBox1Label;
    private String selectBox1ValuesFinder;
    
    
    public CoiDisclosureEventType() { 

    }


    public String getEventTypeCode() {
        return eventTypeCode;
    }


    public void setEventTypeCode(String eventTypeCode) {
        this.eventTypeCode = eventTypeCode;
    }

    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public boolean isExcludeFromMasterDisclosure() {
        return excludeFromMasterDisclosure;
    }


    public void setExcludeFromMasterDisclosure(boolean excludeFromMasterDisclosure) {
        this.excludeFromMasterDisclosure = excludeFromMasterDisclosure;
    }


    public boolean isActive() {
        return active;
    }


    public void setActive(boolean active) {
        this.active = active;
    }


    public boolean isUseShortTextField1() {
        return useShortTextField1;
    }


    public void setUseShortTextField1(boolean useShortTextField1) {
        this.useShortTextField1 = useShortTextField1;
    }


    public boolean isUseShortTextField2() {
        return useShortTextField2;
    }


    public void setUseShortTextField2(boolean useShortTextField2) {
        this.useShortTextField2 = useShortTextField2;
    }


    public boolean isUseShortTextField3() {
        return useShortTextField3;
    }


    public void setUseShortTextField3(boolean useShortTextField3) {
        this.useShortTextField3 = useShortTextField3;
    }


    public boolean isUseLongTextField1() {
        return useLongTextField1;
    }


    public void setUseLongTextField1(boolean useLongTextField1) {
        this.useLongTextField1 = useLongTextField1;
    }


    public boolean isUseLongTextField2() {
        return useLongTextField2;
    }


    public void setUseLongTextField2(boolean useLongTextField2) {
        this.useLongTextField2 = useLongTextField2;
    }


    public boolean isUseLongTextField3() {
        return useLongTextField3;
    }


    public void setUseLongTextField3(boolean useLongTextField3) {
        this.useLongTextField3 = useLongTextField3;
    }


    public boolean isUseNumberField1() {
        return useNumberField1;
    }


    public void setUseNumberField1(boolean useNumberField1) {
        this.useNumberField1 = useNumberField1;
    }


    public boolean isUseNumberField2() {
        return useNumberField2;
    }


    public void setUseNumberField2(boolean useNumberField2) {
        this.useNumberField2 = useNumberField2;
    }


    public boolean isUseDateField1() {
        return useDateField1;
    }


    public void setUseDateField1(boolean useDateField1) {
        this.useDateField1 = useDateField1;
    }


    public boolean isUseDateField2() {
        return useDateField2;
    }


    public void setUseDateField2(boolean useDateField2) {
        this.useDateField2 = useDateField2;
    }


    public String getShortTextField1Label() {
        return shortTextField1Label;
    }


    public void setShortTextField1Label(String shortTextField1Label) {
        this.shortTextField1Label = shortTextField1Label;
    }


    public String getShortTextField2Label() {
        return shortTextField2Label;
    }


    public void setShortTextField2Label(String shortTextField2Label) {
        this.shortTextField2Label = shortTextField2Label;
    }


    public String getShortTextField3Label() {
        return shortTextField3Label;
    }


    public void setShortTextField3Label(String shortTextField3Label) {
        this.shortTextField3Label = shortTextField3Label;
    }


    public String getLongTextField1Label() {
        return longTextField1Label;
    }


    public void setLongTextField1Label(String longTextField1Label) {
        this.longTextField1Label = longTextField1Label;
    }


    public String getLongTextField2Label() {
        return longTextField2Label;
    }


    public void setLongTextField2Label(String longTextField2Label) {
        this.longTextField2Label = longTextField2Label;
    }


    public String getLongTextField3Label() {
        return longTextField3Label;
    }


    public void setLongTextField3Label(String longTextField3Label) {
        this.longTextField3Label = longTextField3Label;
    }


    public String getNumberField1Label() {
        return numberField1Label;
    }


    public void setNumberField1Label(String numberField1Label) {
        this.numberField1Label = numberField1Label;
    }


    public String getNumberField2Label() {
        return numberField2Label;
    }


    public void setNumberField2Label(String numberField2Label) {
        this.numberField2Label = numberField2Label;
    }


    public String getDateField1Label() {
        return dateField1Label;
    }


    public void setDateField1Label(String dateField1Label) {
        this.dateField1Label = dateField1Label;
    }


    public String getDateField2Label() {
        return dateField2Label;
    }


    public void setDateField2Label(String dateField2Label) {
        this.dateField2Label = dateField2Label;
    }


    public boolean isRequireShortTextField1() {
        return requireShortTextField1;
    }


    public void setRequireShortTextField1(boolean requireShortTextField1) {
        this.requireShortTextField1 = requireShortTextField1;
    }


    public boolean isRequireShortTextField2() {
        return requireShortTextField2;
    }


    public void setRequireShortTextField2(boolean requireShortTextField2) {
        this.requireShortTextField2 = requireShortTextField2;
    }


    public boolean isRequireShortTextField3() {
        return requireShortTextField3;
    }


    public void setRequireShortTextField3(boolean requireShortTextField3) {
        this.requireShortTextField3 = requireShortTextField3;
    }


    public boolean isRequireLongTextField1() {
        return requireLongTextField1;
    }


    public void setRequireLongTextField1(boolean requireLongTextField1) {
        this.requireLongTextField1 = requireLongTextField1;
    }


    public boolean isRequireLongTextField2() {
        return requireLongTextField2;
    }


    public void setRequireLongTextField2(boolean requireLongTextField2) {
        this.requireLongTextField2 = requireLongTextField2;
    }


    public boolean isRequireLongTextField3() {
        return requireLongTextField3;
    }


    public void setRequireLongTextField3(boolean requireLongTextField3) {
        this.requireLongTextField3 = requireLongTextField3;
    }


    public boolean isRequireNumberField1() {
        return requireNumberField1;
    }


    public void setRequireNumberField1(boolean requireNumberField1) {
        this.requireNumberField1 = requireNumberField1;
    }


    public boolean isRequireNumberField2() {
        return requireNumberField2;
    }


    public void setRequireNumberField2(boolean requireNumberField2) {
        this.requireNumberField2 = requireNumberField2;
    }


    public boolean isRequireDateField1() {
        return requireDateField1;
    }


    public void setRequireDateField1(boolean requireDateField1) {
        this.requireDateField1 = requireDateField1;
    }


    public boolean isRequireDateField2() {
        return requireDateField2;
    }


    public void setRequireDateField2(boolean requireDateField2) {
        this.requireDateField2 = requireDateField2;
    }   

    public boolean isUseSelectBox1() {
        return useSelectBox1;
    }


    public void setUseSelectBox1(boolean useSelectBox1) {
        this.useSelectBox1 = useSelectBox1;
    }


    public boolean isRequireSelectBox1() {
        return requireSelectBox1;
    }


    public void setRequireSelectBox1(boolean requireSelectBox1) {
        this.requireSelectBox1 = requireSelectBox1;
    }


    public String getSelectBox1Label() {
        return selectBox1Label;
    }


    public void setSelectBox1Label(String selectBox1Label) {
        this.selectBox1Label = selectBox1Label;
    }


    public String getSelectBox1ValuesFinder() {
        return selectBox1ValuesFinder;
    }


    public void setSelectBox1ValuesFinder(String selectBox1ValuesFinder) {
        this.selectBox1ValuesFinder = selectBox1ValuesFinder;
    }


    public boolean isManualDisclosureType() {
        //Using any of the custom labels indicates a manual disclosure...
        return useShortTextField1 || useShortTextField2 || useShortTextField3 || 
               useLongTextField1 || useLongTextField2 || useLongTextField3 ||
               useNumberField1 || useNumberField2 || useDateField1 || useDateField2;
    }
}
