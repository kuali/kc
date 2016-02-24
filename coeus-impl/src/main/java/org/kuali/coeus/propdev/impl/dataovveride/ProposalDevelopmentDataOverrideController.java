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
package org.kuali.coeus.propdev.impl.dataovveride;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.notification.impl.service.KcNotificationService;
import org.kuali.coeus.propdev.impl.core.*;
import org.kuali.coeus.propdev.impl.editable.ProposalChangedData;
import org.kuali.coeus.propdev.impl.editable.ProposalColumnsToAlter;
import org.kuali.coeus.propdev.impl.editable.ProposalDataOverrideEvent;
import org.kuali.coeus.propdev.impl.notification.ProposalDevelopmentNotificationContext;
import org.kuali.coeus.propdev.impl.notification.ProposalDevelopmentNotificationRenderer;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.data.DataType;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.core.api.exception.RiceRuntimeException;
import org.kuali.rice.krad.data.metadata.DataObjectRelationship;
import org.kuali.rice.krad.service.KualiRuleService;
import org.kuali.rice.krad.uif.UifConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

@Controller
public class ProposalDevelopmentDataOverrideController extends ProposalDevelopmentControllerBase {
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ProposalDevelopmentDataOverrideController.class);

    @Autowired
    @Qualifier("kualiRuleService")
    private KualiRuleService kualiRuleService;

    @Autowired
    @Qualifier("kcNotificationService")
    private KcNotificationService kcNotificationService;

    @Autowired
    @Qualifier("dateTimeService")
    private DateTimeService dateTimeService;

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=prepareDataOverride")
    public ModelAndView prepareDataOverride(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form)
            throws Exception {

        String columnName = form.getNewProposalChangedData().getColumnName();
        if (StringUtils.isNotEmpty(columnName)){
        form.getNewProposalChangedData().setEditableColumn(getDataObjectService().find(ProposalColumnsToAlter.class,
                columnName));

        Object propertyObject = getPropertyValue(form.getDevelopmentProposal(),form.getNewProposalChangedData().getAttributeName());
        DataType dataType = DataType.getDataTypeFromClass(DevelopmentProposal.class.getDeclaredField(form.getNewProposalChangedData().getAttributeName()).getType());
        String propertyValue = null;
        if (propertyObject != null) {
            if (dataType.isTemporal()) {
                propertyValue = getDateTimeService().toDateString((Date)propertyObject);
            } else if (dataType.equals(DataType.STRING)){
                propertyValue = (String) propertyObject;
            } else {
                propertyValue = propertyObject.toString();
            }
        }
        form.getNewProposalChangedData().setDisplayValue(propertyValue);
        form.getNewProposalChangedData().setOldDisplayValue(propertyValue);
        } else {
            form.setNewProposalChangedData(new ProposalChangedData());
        }
        form.setUpdateComponentId("PropDev-DataOverride-Dialog");
        form.setAjaxReturnType(UifConstants.AjaxReturnTypes.UPDATECOMPONENT.getKey());
        return getRefreshControllerService().refresh(form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=createOverride")
    public ModelAndView createOverride(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form)
            throws Exception {
        ProposalDevelopmentDocument pdDocument = form.getProposalDevelopmentDocument();
        ProposalChangedData newProposalChangedData = form.getNewProposalChangedData();


        int changeNumber = pdDocument.getDocumentNextValue("proposalDevelopment.proposalChangedDataList.changeNumber");
        newProposalChangedData.setProposalNumber(pdDocument.getDevelopmentProposal().getProposalNumber());
        newProposalChangedData.setChangeNumber(changeNumber);
        newProposalChangedData.setDisplayValue(newProposalChangedData.getChangedValue());

        if(getKualiRuleService().applyRules(new ProposalDataOverrideEvent(pdDocument, newProposalChangedData))){
            setChangedValue(pdDocument.getDevelopmentProposal(),newProposalChangedData);
            growProposalChangedHistory(pdDocument, newProposalChangedData);
            List<ProposalChangedData> proposalChangedDataList= new ArrayList<ProposalChangedData>();
            proposalChangedDataList.add(newProposalChangedData);
            proposalChangedDataList.addAll(form.getDevelopmentProposal().getProposalChangedDataList());
            form.getDevelopmentProposal().setProposalChangedDataList(proposalChangedDataList);

            super.save(form);

            getDisplayReferenceValue(newProposalChangedData,form.getDevelopmentProposal());
            form.setNewProposalChangedData(new ProposalChangedData());

            ProposalDevelopmentNotificationContext context =
                    new ProposalDevelopmentNotificationContext(pdDocument.getDevelopmentProposal(), Constants.PROPOSAL_DATA_OVVERRIDE_ACTION_TYPE_CODE, Constants.DATA_OVERRIDE_CONTEXT);
            ((ProposalDevelopmentNotificationRenderer) context.getRenderer()).setProposalChangedData(newProposalChangedData);
            ((ProposalDevelopmentNotificationRenderer) context.getRenderer()).setDevelopmentProposal(pdDocument.getDevelopmentProposal());

            sendNotificationIfNoErrors(form, context);

        }

       return getRefreshControllerService().refresh(form);
    }

    protected void sendNotificationIfNoErrors(ProposalDevelopmentDocumentForm form, ProposalDevelopmentNotificationContext context) {
        if (getGlobalVariableService().getMessageMap().hasNoErrors()) {
            if (form.getNotificationHelper().getPromptUserForNotificationEditor(context)) {
                form.getNotificationHelper().initializeDefaultValues(context);
                form.setSendOverrideNotification(true);
            } else {
                getKcNotificationService().sendNotification(context);
            }
        } else {
            form.setSendOverrideNotification(false);
        }
    }

    protected void setChangedValue(DevelopmentProposal developmentProposal, ProposalChangedData proposalChangedData) throws Exception {
        String propertyName = proposalChangedData.getAttributeName();
        DataType dataType = DataType.getDataTypeFromClass(DevelopmentProposal.class.getDeclaredField(propertyName).getType());
        if (dataType.isTemporal()) {
            PropertyUtils.setNestedProperty(developmentProposal,propertyName,getDateTimeService().convertToSqlDate(proposalChangedData.getChangedValue()));
        } else if (dataType.equals(DataType.INTEGER)){
            PropertyUtils.setNestedProperty(developmentProposal, propertyName, Integer.valueOf(proposalChangedData.getChangedValue()));
        } else if (dataType.equals(DataType.LONG)){
            PropertyUtils.setNestedProperty(developmentProposal, propertyName, Long.valueOf(proposalChangedData.getChangedValue()));
        } else if (dataType.equals(DataType.FLOAT)){
            PropertyUtils.setNestedProperty(developmentProposal, propertyName, Float.valueOf(proposalChangedData.getChangedValue()));
        } else if (dataType.equals(DataType.DOUBLE)){
            PropertyUtils.setNestedProperty(developmentProposal, propertyName, Double.valueOf(proposalChangedData.getChangedValue()));
        } else if (dataType.equals(DataType.LARGE_INTEGER)){
            PropertyUtils.setNestedProperty(developmentProposal, propertyName, BigInteger.valueOf(Long.valueOf(proposalChangedData.getChangedValue())));
        } else if (dataType.equals(DataType.PRECISE_DECIMAL)){
            PropertyUtils.setNestedProperty(developmentProposal, propertyName, BigDecimal.valueOf(Long.valueOf(proposalChangedData.getChangedValue())));
        } else if (dataType.equals(DataType.STRING)){
            PropertyUtils.setNestedProperty(developmentProposal,propertyName,proposalChangedData.getChangedValue());
        } else {
            throw new RuntimeException("Data override does not work for this class");
        }
    }

    protected void getDisplayReferenceValue(ProposalChangedData proposalChangedData, DevelopmentProposal proposal) {
        String refName = "";

        DataObjectRelationship relationship = getDataObjectService().getMetadataRepository().getMetadata(DevelopmentProposal.class).getRelationshipByLastAttributeInRelationship(proposalChangedData.getAttributeName());
        if (relationship != null) {
            refName = relationship.getName();
        }

        if (StringUtils.isNotEmpty(refName)){
            getDataObjectService().wrap(proposal).fetchRelationship(refName);
            try {
                Object refObject = PropertyUtils.getNestedProperty(proposal,refName);
                String refDescription = (String) PropertyUtils.getNestedProperty(refObject,"description");
                proposalChangedData.setDisplayValue(refDescription);
            } catch (Exception e) {
                LOG.warn("no description field found on ref object",e);
            }
        }
    }


    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=sendOverrideNotification")
    public ModelAndView sendOverrideNotification(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) {
        if (form.isSendOverrideNotification()) {
            return getModelAndViewService().showDialog("Kc-SendNotification-Wizard",true,form);
        }
        form.setSendOverrideNotification(false);
        return getModelAndViewService().getModelAndView(form);
    }

    protected Object getPropertyValue(DevelopmentProposal developmentProposal, String propertyName) {
        try{
            return PropertyUtils.getNestedProperty(developmentProposal,propertyName);
        } catch (Exception e) {
            throw new RiceRuntimeException("propertyName " + propertyName + "can not be found on development proposal");
        }
    }

    private void growProposalChangedHistory(ProposalDevelopmentDocument pdDocument, ProposalChangedData newProposalChangedData) {
        Map<String, List<ProposalChangedData>> changeHistory = pdDocument.getDevelopmentProposal().getProposalChangeHistory();
        if(changeHistory.get(newProposalChangedData.getEditableColumn().getColumnLabel()) == null) {
            changeHistory.put(newProposalChangedData.getEditableColumn().getColumnLabel(), new ArrayList<ProposalChangedData>());
        }

        changeHistory.get(newProposalChangedData.getEditableColumn().getColumnLabel()).add(0, newProposalChangedData);
    }

    public KualiRuleService getKualiRuleService() {
        return kualiRuleService;
    }

    public void setKualiRuleService(KualiRuleService kualiRuleService) {
        this.kualiRuleService = kualiRuleService;
    }

    public KcNotificationService getKcNotificationService() {
        return kcNotificationService;
    }

    public void setKcNotificationService(KcNotificationService kcNotificationService) {
        this.kcNotificationService = kcNotificationService;
    }

    public DateTimeService getDateTimeService() {
        return dateTimeService;
    }

    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }
}
