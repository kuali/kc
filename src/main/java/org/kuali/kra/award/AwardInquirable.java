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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.AwardHierarchyUIService;
import org.kuali.kra.timeandmoney.AwardHierarchyNode;
import org.kuali.rice.kns.inquiry.KualiInquirableImpl;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
import org.kuali.rice.kns.web.ui.Section;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.ObjectUtils;

public class AwardInquirable extends KualiInquirableImpl {
    
    private static final Log LOG = LogFactory.getLog(AwardInquirable.class);

    @Override
    public List<Section> getSections(BusinessObject bo) {
        List<Section> sections = new ArrayList<Section>();
        Section section = new Section();
        
        section.setRows(new ArrayList<Row>());
        section.setDefaultOpen(true);
        section.setNumberOfColumns(2);
        
        AwardHierarchyUIService service = getAwardHierarchyUIService();
        Award award = (Award) bo;
        AwardHierarchyNode awardNode = null;
        try {
            awardNode = service.getRootAwardNode(award);
        }
        catch (ParseException e) {
            // TODO Auto-generated catch block
            LOG.error("Error parsing award information" ,e);
        }
        
        // Adding the section title
        String sectionTitle = "";
        sectionTitle += awardNode.getAwardNumber();
        sectionTitle += KRADConstants.BLANK_SPACE;
        sectionTitle += Constants.COLON;
        sectionTitle += KRADConstants.BLANK_SPACE;
        
        if (ObjectUtils.isNotNull(award.getAccountNumber())) {
            sectionTitle += awardNode.getAccountNumber();
            sectionTitle += KRADConstants.BLANK_SPACE;
            sectionTitle += Constants.COLON;
            sectionTitle += KRADConstants.BLANK_SPACE;
        }
        if (ObjectUtils.isNotNull(awardNode.getPrincipalInvestigatorName())) {
            sectionTitle += awardNode.getPrincipalInvestigatorName();
            sectionTitle += KRADConstants.BLANK_SPACE;
            sectionTitle += Constants.COLON;
            sectionTitle += KRADConstants.BLANK_SPACE;
        } 
        if (ObjectUtils.isNotNull(awardNode.getLeadUnitName())) {
            sectionTitle += awardNode.getLeadUnitName();
        }
        
        section.setSectionTitle(sectionTitle);

        //Adding the rows to the sections
        section.setRows(new ArrayList<Row>());
        Row row1 = new Row();
        addField(awardNode.getProjectStartDate() + "", row1, "projectStartDate", "Project Start Date");
        addField(awardNode.getCurrentFundEffectiveDate() + "", row1, "obligationStartDate", "Obligation Start Date");
        section.getRows().add(row1);

        Row row2 = new Row();        
        addField(awardNode.getFinalExpirationDate() + "", row2, "projectEndDate", "Project End Date");
        addField(awardNode.getObligationExpirationDate() + "", row2, "obligationEndDate", "Obligation End Date");
        section.getRows().add(row2);

        Row row3 = new Row(); 
        addField(awardNode.getAnticipatedTotalAmount() + "", row3, "anticipatedAmount", "Anticipated Amount");
        addField(awardNode.getAmountObligatedToDate() + "", row3, "obligatedAmount", "Obligated Amount");
        section.getRows().add(row3);

        Row row4 = new Row();
        addField(awardNode.getTitle(), row4, "title", "Title");
        addField(award.getSponsorAwardNumber(), row4, "sponsorAwardNumber", "Sponsor Award Number");
        section.getRows().add(row4);

        Row row5 = new Row();
        addField(award.getAwardNumber(), row5, "awardNumber", "Award Number");
        addField(award.getAwardStatus().getStatusCode() + "", row5, "awardStatusCode", "Award Status Code");
        section.getRows().add(row5);

        Row row6 = new Row();
        addField(award.getOspAdministratorName(), row6, "ospAdminName", "OSP Administrator Name");
        addField(award.getCloseoutDate() + "", row6, "closeoutDate", "Closeout Date");
        section.getRows().add(row6);

        Row row7 = new Row();
        addField(award.getCfdaNumber(), row7, "cfdaNumber", "CFDA Number");
        section.getRows().add(row7);
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
            text = "";
        }
        field.setPropertyValue(text);        
        row1.getFields().add(field);
    }

    private AwardHierarchyUIService getAwardHierarchyUIService() {
        return KraServiceLocator.getService(AwardHierarchyUIService.class);
    }
} 
