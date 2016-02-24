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
package org.kuali.kra.jqueryajax;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.committee.impl.bo.CommitteeType;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.common.notification.impl.service.NotificationRoleSubQualifierFinders;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.home.AwardTemplate;
import org.kuali.kra.coi.CoiDispositionStatus;
import org.kuali.kra.coi.disclosure.CoiDisclosureService;
import org.kuali.kra.iacuc.IacucLocationName;
import org.kuali.kra.iacuc.actions.IacucProtocolActionAjaxService;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolReviewType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.actions.submit.ProtocolReviewType;
import org.kuali.kra.protocol.actions.ProtocolActionAjaxService;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase;
import org.kuali.rice.krad.service.BusinessObjectService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 
 * This class is to handle jquery ajax calls.
 */
public class JqueryAjaxAction extends KualiDocumentActionBase {

    protected NotificationRoleSubQualifierFinders notificationRoleSubQualifierFinders;
    private CoiDisclosureService coiDisclosureService;

    /**
     * 
     * This method uses the case-insensitive lookup method of the unit service to get the unit name using the unit number
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getUnitName (ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
    throws Exception {
        
        JqueryAjaxForm ajaxForm = (JqueryAjaxForm)form;
        Unit unit = getUnitService().getUnitCaseInsensitive(ajaxForm.getCode());
        String unitName = null;
        if(null != unit){
            unitName = unit.getUnitName();
            if(null != unit.getUnitNumber()){
                ajaxForm.setCode(unit.getUnitNumber());
            }
        }
        if (unitName == null && StringUtils.isNotBlank(ajaxForm.getCode())) {
            unitName = "<span style='color: red;'>not found</span>";
        }
        ajaxForm.setReturnVal(unitName);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward getCoiDispositionStatus(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        JqueryAjaxForm ajaxForm = (JqueryAjaxForm) form;
        String disclosureStatusCode = ajaxForm.getCode();
        List<CoiDispositionStatus> coiDispositionStatuses = getCoiDisclosureService().getDispositionStatuses(disclosureStatusCode);
        StringBuffer buffer = new StringBuffer();
        buffer.append("[ ");
        int index = 0;
        for (CoiDispositionStatus value : coiDispositionStatuses) {
            if (index > 0) {
                buffer.append(" , ");
            }
            buffer.append("{ 'key' :'");
            buffer.append(value.getCoiDispositionCode());
            buffer.append("', 'value' : '");
            buffer.append(value.getDescription());
            buffer.append("'}");
            index++;
        }
        buffer.append("]");
        ajaxForm.setReturnVal(buffer.toString());
        return mapping.findForward(Constants.MAPPING_BASIC);

    }

    public ActionForward getIacucProcedureLocationNames(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        JqueryAjaxForm ajaxForm = (JqueryAjaxForm) form;
        String locationTypeCode = ajaxForm.getCode();

        Map<String, Object> filterValues = new HashMap<String, Object>();
        filterValues.put("locationTypeCode", locationTypeCode);
        List<IacucLocationName> iacucLocationNames = (List<IacucLocationName>)getBusinessObjectService().findMatching(IacucLocationName.class, filterValues);
        
        StringBuffer buffer = new StringBuffer();
        buffer.append("[ ");
        for (IacucLocationName value : iacucLocationNames) {
            buffer.append("{ 'key' :'");
            buffer.append(value.getLocationId());
            buffer.append("', 'value' : '");
            buffer.append(value.getLocationName());
            buffer.append("'} , ");
        }
        buffer.append("]");
        ajaxForm.setReturnVal(buffer.toString());
        return mapping.findForward(Constants.MAPPING_BASIC);

    }
    
    public ActionForward getProtocolReviewTypes(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        JqueryAjaxForm ajaxForm = (JqueryAjaxForm) form;
        String committeeTypeCode = ajaxForm.getCode();
        StringBuffer buffer = new StringBuffer();

        if (StringUtils.equalsIgnoreCase(committeeTypeCode, CommitteeType.IRB_TYPE_CODE)) {
            List<ProtocolReviewType> reviewTypes = (List<ProtocolReviewType>) getBusinessObjectService().findAll(ProtocolReviewType.class);
            buffer.append("[ ");
            for (ProtocolReviewType reviewType : reviewTypes) {
                buffer.append("{ 'key' :'");
                buffer.append(reviewType.getReviewTypeCode());
                buffer.append("', 'value' : '");
                buffer.append(reviewType.getDescription());
                buffer.append("'} , ");
            }
            buffer.append("]");
        } else if (StringUtils.equalsIgnoreCase(committeeTypeCode, CommitteeType.IACUC_TYPE_CODE)) {
            List<IacucProtocolReviewType> reviewTypes = (List<IacucProtocolReviewType>) getBusinessObjectService().findAll(IacucProtocolReviewType.class);
            buffer.append("[ ");
            for (IacucProtocolReviewType reviewType : reviewTypes) {
                buffer.append("{ 'key' :'");
                buffer.append(reviewType.getReviewTypeCode());
                buffer.append("', 'value' : '");
                buffer.append(reviewType.getDescription());
                buffer.append("'} , ");
            }
            buffer.append("]");
        }       
        
        ajaxForm.setReturnVal(buffer.toString());
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    private UnitService getUnitService() {
        return KcServiceLocator.getService(UnitService.class);
    }
    
    public ActionForward getProtocolReviewers(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        String committeeId = request.getParameter("committeeId");
        String scheduleId = request.getParameter("scheduleId");
        String protocolId = request.getParameter("protocolId");
        JqueryAjaxForm ajaxForm = (JqueryAjaxForm) form;

        String reviewers = getProtocolAjaxService().getReviewers(protocolId, committeeId, scheduleId);
        ajaxForm.setReturnVal(reviewers.toString());

        return mapping.findForward(Constants.MAPPING_BASIC);

    }
    
    public ActionForward getModifySubmissionProtocolReviewers(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        String committeeId = request.getParameter("committeeId");
        String scheduleId = request.getParameter("scheduleId");
        String protocolId = request.getParameter("protocolId");
        String protocolReviewTypeCode = request.getParameter("protocolReviewTypeCode");

        JqueryAjaxForm ajaxForm = (JqueryAjaxForm) form;

        String reviewers = ((IacucProtocolActionAjaxService)getProtocolAjaxService()).getModifySubmissionProtocolReviewers(protocolId, committeeId, scheduleId, protocolReviewTypeCode);
        ajaxForm.setReturnVal(reviewers.toString());

        return mapping.findForward(Constants.MAPPING_BASIC);

    }
    
    public ActionForward getProtocolReviewerTypes(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
            JqueryAjaxForm ajaxForm = (JqueryAjaxForm) form;
            String types = getProtocolAjaxService().getReviewerTypes();
            ajaxForm.setReturnVal(types);
        return mapping.findForward(Constants.MAPPING_BASIC);

    }

    public ProtocolActionAjaxService getProtocolAjaxService() {
        return KcServiceLocator.getService(IacucProtocolActionAjaxService.class);
    }
    /**
     * Get and return the template description for ajax queries.
     */
    public ActionForward getSponsorTemplateDescription(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
        throws Exception {
        final JqueryAjaxForm ajaxForm = (JqueryAjaxForm) form;
        if (StringUtils.isNotBlank(ajaxForm.getCode())) {
            if (NumberUtils.isDigits(ajaxForm.getCode())) {
                final AwardTemplate template = getBusinessObjectService().findBySinglePrimaryKey(AwardTemplate.class, ajaxForm.getCode());
                if (template != null) {
                    ajaxForm.setReturnVal(template.getDescription());
                } else {
                    ajaxForm.setReturnVal("<span style='color: red;'>not found</span>");
                }
            } else {
                ajaxForm.setReturnVal("<span style='color: red;'>not found</span>");
            }
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
        
    }
    
    public ActionForward getNotificationRoleSubQualifiers(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
    throws Exception {
        JqueryAjaxForm ajaxForm = (JqueryAjaxForm) form;
        List<KeyValue> values = getNotificationRoleSubQualifierFinders().getKeyValuesForRole(ajaxForm.getCode());
        StringBuffer buffer = new StringBuffer();
        buffer.append("[ ");
        for (KeyValue value : values) {
            buffer.append("{ 'key' :'");
            buffer.append(value.getKey());
            buffer.append("', 'value' : '");
            buffer.append(value.getValue());
            buffer.append("'} , ");
        }
        buffer.append("]");
        ajaxForm.setReturnVal(buffer.toString());
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    protected BusinessObjectService getBusinessObjectService() {
        return KcServiceLocator.getService(BusinessObjectService.class);
    }
    
    protected CoiDisclosureService getCoiDisclosureService() {
        if (coiDisclosureService == null) {
            coiDisclosureService = KcServiceLocator.getService(CoiDisclosureService.class);
        }
        return coiDisclosureService;
    }
    
    protected NotificationRoleSubQualifierFinders getNotificationRoleSubQualifierFinders() {
        if (notificationRoleSubQualifierFinders == null) {
            notificationRoleSubQualifierFinders = KcServiceLocator.getService(NotificationRoleSubQualifierFinders.class);
        }
        return notificationRoleSubQualifierFinders;
    }


    public void setNotificationRoleSubQualifierFinders(NotificationRoleSubQualifierFinders notificationRoleSubQualifierFinders) {
        this.notificationRoleSubQualifierFinders = notificationRoleSubQualifierFinders;
    }
}
