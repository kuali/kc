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
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.core.api.exception.RiceRuntimeException;
import org.kuali.rice.krad.data.metadata.DataObjectAttribute;
import org.kuali.rice.krad.service.KualiRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class ProposalDevelopmentDataOverrideController extends ProposalDevelopmentControllerBase {
    private static final String DATE_TYPE = "DATE";
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

    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=prepareDataOverride")
    public ModelAndView prepareDataOverride(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form)
            throws Exception {

        String columnName = form.getNewProposalChangedData().getColumnName();

        form.getNewProposalChangedData().setEditableColumn(getDataObjectService().find(ProposalColumnsToAlter.class,
                columnName));

        Object propertyObject = getPropertyValue(form.getDevelopmentProposal(),form.getNewProposalChangedData().getAttributeName());
        String propertyValue = null;
        if (form.getNewProposalChangedData().getEditableColumn().getDataType().equals(DATE_TYPE)) {
            propertyValue = getDateTimeService().toDateString((Date)propertyObject);
        } else {
            propertyValue = (String) propertyObject;
        }

        form.getNewProposalChangedData().setDisplayValue(propertyValue);
        form.getNewProposalChangedData().setOldDisplayValue(propertyValue);

        return getModelAndViewService().getModelAndView(form);
    }

    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=createOverride")
    public ModelAndView createOverride(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form)
            throws Exception {
        ProposalDevelopmentDocument pdDocument = form.getProposalDevelopmentDocument();
        ProposalChangedData newProposalChangedData = form.getNewProposalChangedData();


        int changeNumber = pdDocument.getDocumentNextValue("proposalDevelopment.proposalChangedDataList.changeNumber");
        newProposalChangedData.setProposalNumber(pdDocument.getDevelopmentProposal().getProposalNumber());
        newProposalChangedData.setChangeNumber(changeNumber);
        if(StringUtils.isEmpty(newProposalChangedData.getDisplayValue()) && StringUtils.isNotEmpty(newProposalChangedData.getChangedValue())) {
            newProposalChangedData.setDisplayValue(newProposalChangedData.getChangedValue());
        }

        if(getKualiRuleService().applyRules(new ProposalDataOverrideEvent(pdDocument, newProposalChangedData))){

            String propertyName = getPropertyName(form,newProposalChangedData.getColumnName());
            if (newProposalChangedData.getEditableColumn().getDataType().equals(DATE_TYPE)) {
                PropertyUtils.setNestedProperty(pdDocument.getDevelopmentProposal(),propertyName,getDateTimeService().convertToSqlDate(newProposalChangedData.getChangedValue()));
            } else {
                PropertyUtils.setNestedProperty(pdDocument.getDevelopmentProposal(),propertyName,newProposalChangedData.getChangedValue());
            }
            growProposalChangedHistory(pdDocument, newProposalChangedData);
            form.getDevelopmentProposal().getProposalChangedDataList().add(newProposalChangedData);
            super.save(form);


            form.setNewProposalChangedData(new ProposalChangedData());

            ProposalDevelopmentNotificationContext context =
                    new ProposalDevelopmentNotificationContext(pdDocument.getDevelopmentProposal(), "103", "Proposal Data Override");
            ((ProposalDevelopmentNotificationRenderer) context.getRenderer()).setProposalChangedData(newProposalChangedData);
            ((ProposalDevelopmentNotificationRenderer) context.getRenderer()).setDevelopmentProposal(pdDocument.getDevelopmentProposal());
            if (form.getNotificationHelper().getPromptUserForNotificationEditor(context)) {
                form.getNotificationHelper().initializeDefaultValues(context);
                return getModelAndViewService().showDialog("Kc-SendNotification-Wizard",true,form);
            } else {
                getKcNotificationService().sendNotification(context);
            }

        }

       return getRefreshControllerService().refresh(form);
    }


    protected String getPropertyName(ProposalDevelopmentDocumentForm form, String columnName) {
        for (DataObjectAttribute attribute : getDataObjectService().wrap(form.getDevelopmentProposal()).getMetadata().getAttributes()) {
            if (attribute.getBackingObjectName().equals(columnName)) {
                return attribute.getName();
            }
        }
        return StringUtils.EMPTY;
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
