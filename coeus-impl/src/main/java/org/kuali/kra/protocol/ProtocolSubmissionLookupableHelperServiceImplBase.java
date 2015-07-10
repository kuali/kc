/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.protocol;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.kra.lookup.KraLookupableHelperServiceImpl;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import java.util.List;

/**
 * 
 * This class is to create action links and inquiry url for protocolsubmission lookup. 
 */
@SuppressWarnings("serial")
public abstract class ProtocolSubmissionLookupableHelperServiceImplBase extends KraLookupableHelperServiceImpl {
    protected static final String COMMITTEE_ID = "committeeId";
    protected static final String COMMITTEE_SCHEDULE_SCHEDULE_DATE = "committeeSchedule.scheduledDate";
    protected static final String PROTOCOL_TITLE = "protocol.title";
    protected static final String PROTOCOL_NUMBER = "protocolNumber";
    protected static final String DOC_TYPE_NAME_PARAM = "&docTypeName=";
    protected KcAuthorizationService kraAuthorizationService;
    protected KcPersonService kcPersonService;

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
          
    /*
     * create the view link url for protocolsubmission
     */
    protected AnchorHtmlData getViewLink(ProtocolSubmissionBase protocolSubmission) {
        AnchorHtmlData viewHtmlData = super.getViewLink(protocolSubmission.getProtocol().getProtocolDocument());
        String submissionIdParam = "&submissionId=" + protocolSubmission.getSubmissionId();
        String href = viewHtmlData.getHref();
        href = href.replace(DOC_TYPE_NAME_PARAM, submissionIdParam + DOC_TYPE_NAME_PARAM);
        viewHtmlData.setHref(href);
        return viewHtmlData;
    }

    public void setKraAuthorizationService(KcAuthorizationService kraAuthorizationService) {
        this.kraAuthorizationService = kraAuthorizationService;
    }

    protected String getUserIdentifier() {
        return GlobalVariables.getUserSession().getPrincipalId();
    }

    @Override
    protected String getHtmlAction() {
        return "protocolProtocol.do";
    }

    @Override
    protected String getDocumentTypeName() {
        return "ProtocolDocumentBase";
    }

    @Override
    protected String getKeyFieldName() {
        return PROTOCOL_NUMBER;
    }
  
    /**
     * This method is for several fields that does not have inquiry created by lookup frame work.
     * Also, disable inquiry link for protocol title &amp; schedule date.
     * 
     */
    @Override
    public HtmlData getInquiryUrl(BusinessObject bo, String propertyName) {

        BusinessObject inqBo = bo;
        String inqPropertyName = propertyName;
        HtmlData inqUrl = new AnchorHtmlData();
        if (!COMMITTEE_SCHEDULE_SCHEDULE_DATE.equals(propertyName) && !PROTOCOL_TITLE.equals(propertyName)) {
            if (PROTOCOL_NUMBER.equals(propertyName)) {
                inqBo = ((ProtocolSubmissionBase) bo).getProtocol();
            }
            else if (propertyName.equals(COMMITTEE_ID)) {
                inqBo = ((ProtocolSubmissionBase) bo).getCommittee();
                
            }
            else if ("piName".equals(propertyName)) {
                //-- commented as part of GENERATED CODE need to verify
                ProtocolBase protocol = ((ProtocolSubmissionBase) bo).getProtocol();
                ProtocolPersonBase principalInvestigator = protocol.getPrincipalInvestigator();
                if (principalInvestigator != null) {
                    if (StringUtils.isNotBlank(principalInvestigator.getPersonId())) {
                        inqBo = this.kcPersonService.getKcPersonByPersonId(principalInvestigator.getPersonId());
                        inqPropertyName = ProtocolLookupConstants.Property.PERSON_ID;
                    }
                    else {
                        if (principalInvestigator.getRolodexId() != null) {
                            inqBo = new Rolodex();
                            ((Rolodex) inqBo).setRolodexId(principalInvestigator.getRolodexId());
                            inqPropertyName = ProtocolLookupConstants.Property.ROLODEX_ID;
                        }
                    }
                }
            }
            if (inqBo != null) {
                // withdraw committeeidfk = null will cause inqbo=null
                inqUrl = super.getInquiryUrl(inqBo, inqPropertyName);
            }
        }
        return inqUrl;
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

    /**
     * To disable the search icon for 'title' &amp; 'scheduleddate' fields.  These fields are referencing to reference objects' fields.
     * @see org.kuali.rice.kns.lookup.AbstractLookupableHelperServiceImpl#getRows()
     */
    @Override
    public List<Row> getRows() {
        List<Row> rows = super.getRows();
        for (Row row : rows) {
            for (Field field : row.getFields()) {
                if (PROTOCOL_TITLE.equals(field.getPropertyName()) || COMMITTEE_SCHEDULE_SCHEDULE_DATE.equals(field.getPropertyName())) {
                   
                    field.setQuickFinderClassNameImpl(KRADConstants.EMPTY_STRING);                 
                }
            }
        }
        return rows;
    }

}
