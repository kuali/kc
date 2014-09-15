package org.kuali.coeus.propdev.impl.dataovveride;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.custom.arg.ArgValueLookup;
import org.kuali.coeus.common.impl.custom.arg.ArgValueLookupValuesFinder;
import org.kuali.coeus.common.questionnaire.framework.answer.Answer;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentViewHelperServiceImpl;
import org.kuali.coeus.propdev.impl.editable.ProposalChangedData;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.web.krad.KcBindingInfo;
import org.kuali.rice.krad.uif.control.Control;
import org.kuali.rice.krad.uif.field.InputFieldBase;
import org.kuali.rice.krad.uif.util.ComponentFactory;
import org.kuali.rice.krad.uif.util.LifecycleElement;
import org.kuali.rice.krad.uif.util.ObjectPropertyUtils;
import org.kuali.rice.krad.uif.view.DefaultExpressionEvaluator;


public class DataOverrideInputField extends InputFieldBase {
    private boolean inCollection;

    @Override
    public void performInitialization(Object model){
        if(!inCollection) {
            this.setDictionaryAttributeName(((ProposalDevelopmentDocumentForm)model).getNewProposalChangedData().getAttributeName());
            if(this.getDictionaryAttributeName() == null) {
                this.setDictionaryAttributeName("title");
            }
        }
        this.setDictionaryObjectEntry(DevelopmentProposal.class.getName());
        super.performInitialization(model);
    }


    @Override
    public void performApplyModel(Object model, LifecycleElement parent) {
        super.performApplyModel(model, parent);
    }

    public boolean isInCollection() {
        return inCollection;
    }

    public void setInCollection(boolean inCollection) {
        this.inCollection = inCollection;
    }
}
