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
package org.kuali.kra.award;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.award.service.AwardHierarchyUIService;
import org.kuali.kra.timeandmoney.AwardHierarchyNode;
import org.kuali.rice.kns.inquiry.KualiInquirableImpl;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
import org.kuali.rice.kns.web.ui.Section;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.ObjectUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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
        return KcServiceLocator.getService(AwardHierarchyUIService.class);
    }
} 
