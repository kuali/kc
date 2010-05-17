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
package org.kuali.kra.award;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.AwardHierarchyUIService;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.inquiry.KualiInquirableImpl;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
import org.kuali.rice.kns.web.ui.Section;

public class AwardInquirable extends KualiInquirableImpl {
    
    private static final String COLUMN_CODE = "%3A";
   
    @Override
    public List<Section> getSections(BusinessObject bo) {
        List<Section> sections = new ArrayList<Section>();
        Section section = new Section();
        
        section.setRows(new ArrayList<Row>());
        section.setDefaultOpen(true);
        section.setNumberOfColumns(2);
        
        AwardHierarchyUIService service = getAwardHierarchyUIService();
        String awardRecord = null;
        try {
            awardRecord = service.getAwardRecord((Award)bo);
        }catch (ParseException e) {
            e.printStackTrace();
        }
        
        String awardNumber = awardRecord.substring(0, awardRecord.indexOf(COLUMN_CODE));
        awardRecord = awardRecord.substring(awardRecord.indexOf(COLUMN_CODE));
        
        String text1 = null;
        
        awardRecord = awardRecord.substring(3, awardRecord.length());
        text1 = awardRecord.substring(0, awardRecord.indexOf(COLUMN_CODE));
        section.setSectionTitle(awardNumber + text1);
        
        String text2 = null;        
        awardRecord = awardRecord.substring(awardRecord.indexOf(COLUMN_CODE), awardRecord.length());
        awardRecord = awardRecord.substring(3, awardRecord.length());
        text2 = awardRecord.substring(0, awardRecord.indexOf(COLUMN_CODE));
        
        awardRecord = awardRecord.substring(awardRecord.indexOf(COLUMN_CODE), awardRecord.length());
        awardRecord = awardRecord.substring(3, awardRecord.length());
        String text3 = awardRecord.substring(0, awardRecord.indexOf(COLUMN_CODE));
        
        awardRecord = awardRecord.substring(awardRecord.indexOf(COLUMN_CODE), awardRecord.length());
        awardRecord = awardRecord.substring(3, awardRecord.length());
        String text4 = awardRecord.substring(0, awardRecord.indexOf(COLUMN_CODE));
        
        awardRecord = awardRecord.substring(awardRecord.indexOf(COLUMN_CODE), awardRecord.length());
        awardRecord = awardRecord.substring(3, awardRecord.length());
        String text5 = awardRecord.substring(0, awardRecord.indexOf(COLUMN_CODE));
        
        awardRecord = awardRecord.substring(awardRecord.indexOf(COLUMN_CODE), awardRecord.length());
        awardRecord = awardRecord.substring(3, awardRecord.length());
        String text6 = awardRecord.substring(0, awardRecord.indexOf(COLUMN_CODE));
        
        awardRecord = awardRecord.substring(awardRecord.indexOf(COLUMN_CODE), awardRecord.length());
        awardRecord = awardRecord.substring(3, awardRecord.length());
        String text7 = awardRecord.substring(0, awardRecord.indexOf(COLUMN_CODE));
        
        awardRecord = awardRecord.substring(awardRecord.indexOf(COLUMN_CODE), awardRecord.length());
        awardRecord = awardRecord.substring(3, awardRecord.length());
        String text8 = awardRecord.substring(0, awardRecord.indexOf(COLUMN_CODE));
        
        awardRecord = awardRecord.substring(awardRecord.indexOf(COLUMN_CODE), awardRecord.length());
        awardRecord = awardRecord.substring(3, awardRecord.length());
        String text9 = awardRecord.substring(0, awardRecord.indexOf(COLUMN_CODE));
        
        awardRecord = awardRecord.substring(awardRecord.indexOf(COLUMN_CODE), awardRecord.length());
        awardRecord = awardRecord.substring(3, awardRecord.length());
        String text10 = awardRecord.substring(0, awardRecord.indexOf(COLUMN_CODE));
        
        awardRecord = awardRecord.substring(awardRecord.indexOf(COLUMN_CODE), awardRecord.length());
        awardRecord = awardRecord.substring(3, awardRecord.length());
        String text11 = awardRecord.substring(0, awardRecord.indexOf(COLUMN_CODE));
        
        awardRecord = awardRecord.substring(awardRecord.indexOf(COLUMN_CODE), awardRecord.length());
        awardRecord = awardRecord.substring(3, awardRecord.length());
        String text12 = awardRecord.substring(0, awardRecord.indexOf(COLUMN_CODE));
        
        awardRecord = awardRecord.substring(awardRecord.indexOf(COLUMN_CODE), awardRecord.length());
        awardRecord = awardRecord.substring(3, awardRecord.length());
        String text13 = awardRecord.substring(0, awardRecord.indexOf(COLUMN_CODE));
        
        Row row1 = new Row();        
        addField(text12, row1, "projectStartDate", "Project Start Date");
        addField(text2, row1, "obligationStartDate", "Obligation Start Date");
        
        Row row2 = new Row();        
        addField(text4, row2, "projectEndDate", "Project End Date");
        addField(text3, row2, "obligationEndDate", "Obligation End Date");
        
        Row row3 = new Row();
        addField(text6, row3, "anticipatedAmount", "Anticipated Amount");
        addField(text5, row3, "obligatedAmount", "Obligated Amount");
        
        Row row4 = new Row();
        addField(text13, row4, "title", "Title");
        
        section.setRows(new ArrayList<Row>());
        section.getRows().add(row1);
        section.getRows().add(row2);
        section.getRows().add(row3);
        section.getRows().add(row4);
        sections.add(section);
        return sections;
    }
    
    /**
     * This method...
     * @param text
     * @param row1
     * @param field_1_2
     * @param propertyName TODO
     * @param fieldLabel TODO
     */
    private void addField(String text, Row row1, String propertyName, String fieldLabel) {
        Field field = new Field();
        field.setPropertyName(propertyName);        
        field.setFieldLabel(fieldLabel);        
        field.setFieldType(Field.TEXT);
        if(StringUtils.equalsIgnoreCase(text, " &nbsp; ")){
            text="";
        }
        field.setPropertyValue(text);        
        row1.getFields().add(field);
    }

    private AwardHierarchyUIService getAwardHierarchyUIService() {
        return KraServiceLocator.getService(AwardHierarchyUIService.class);
    }
} 
