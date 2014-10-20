package org.kuali.coeus.propdev.impl.dataovveride;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.krad.service.DataDictionaryService;
import org.kuali.rice.krad.uif.field.InputFieldBase;
import org.kuali.rice.krad.uif.util.ComponentFactory;
import org.kuali.rice.krad.uif.util.LifecycleElement;



import java.util.Collections;


public class DataOverrideInputField extends InputFieldBase {
    private static final String MAILING_ADDRESS_COLUMN = "MAILING_ADDRESS_ID";
    private boolean inCollection;


    private DataDictionaryService dataDictionaryService;

    @Override
    public void performInitialization(Object model){
        if(!inCollection) {
            this.setDictionaryAttributeName(((ProposalDevelopmentDocumentForm)model).getNewProposalChangedData().getAttributeName());
            if(this.getDictionaryAttributeName() == null) {
                this.setDictionaryAttributeName("title");
            }
        }
        this.setDictionaryObjectEntry(DevelopmentProposal.class.getName());

        if (StringUtils.equals(((ProposalDevelopmentDocumentForm)model).getNewProposalChangedData().getColumnName(),MAILING_ADDRESS_COLUMN)) {
            this.setControl(ComponentFactory.getTextControl());
            this.setWidgetInputOnly(true);
            this.setQuickfinder(ComponentFactory.getQuickFinder());
            this.getQuickfinder().setReturnByScript(true);
            this.getQuickfinder().setDataObjectClassName(Rolodex.class.getName());
            this.getQuickfinder().setFieldConversions(Collections.singletonMap("newProposalChangedData.changedValue","mailingAddressId"));
        }
        super.performInitialization(model);
    }

    @Override
    public void performApplyModel(Object model, LifecycleElement parent) {
        if (inCollection) {
            this.copyFromAttributeDefinition(getDataDictionaryService().getAttributeDefinition("DevelopmentProposal",this.getDictionaryAttributeName()));
        }

        super.performApplyModel(model, parent);
    }

    public boolean isInCollection() {
        return inCollection;
    }

    public void setInCollection(boolean inCollection) {
        this.inCollection = inCollection;
    }

    public DataDictionaryService getDataDictionaryService() {
        if (dataDictionaryService == null) {
            dataDictionaryService = KcServiceLocator.getService(DataDictionaryService.class);
        }
        return dataDictionaryService;
    }

    public void setDataDictionaryService(DataDictionaryService dataDictionaryService) {
        this.dataDictionaryService = dataDictionaryService;
    }
}
