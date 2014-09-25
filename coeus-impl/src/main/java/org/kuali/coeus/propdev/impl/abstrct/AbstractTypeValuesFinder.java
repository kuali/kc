/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.coeus.propdev.impl.abstrct;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;
import org.kuali.rice.krad.uif.field.InputField;
import org.kuali.rice.krad.uif.view.ViewModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Finds the available set of supported Abstract Types.  See
 * the method <code>getKeyValues()</code> for a full description.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@Component("abstractTypeValuesFinder")
public class AbstractTypeValuesFinder extends UifKeyValuesFinderBase {

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

    @Override
    public List<KeyValue> getKeyValues(ViewModel model, InputField field){
        ProposalDevelopmentDocumentForm form = (ProposalDevelopmentDocumentForm) model;
        String selectedAbstractType = getFieldValue(model,field);
        Collection<AbstractType> abstractTypes = getDataObjectService().findAll(AbstractType.class).getResults();
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(new ConcreteKeyValue("", "select"));
        for (AbstractType abstractType : abstractTypes) {
            if (!hasAbstract(form.getProposalDevelopmentDocument(), abstractType) || StringUtils.equals(abstractType.getCode(),selectedAbstractType)) {
                keyValues.add(new ConcreteKeyValue(abstractType.getCode(), abstractType.getDescription()));
            }
        }
        return keyValues;
    }

    private boolean hasAbstract(ProposalDevelopmentDocument doc, AbstractType abstractType) {
        if (doc != null) {
            List<ProposalAbstract> proposalAbstracts = doc.getDevelopmentProposal().getProposalAbstracts();
            for (ProposalAbstract proposalAbstract : proposalAbstracts) {
                if (proposalAbstract.getAbstractTypeCode().equals(abstractType.getCode())) {
                    return true;
                }
            }
        }
        return false;
    }

    private String getFieldValue(ViewModel model, InputField field) {
        if (!StringUtils.startsWith(field.getBindingInfo().getBindingPath(),"new")) {
            try {
                return (String) PropertyUtils.getNestedProperty(model,field.getBindingInfo().getBindingPath());
            } catch (Exception e) {
                throw new RuntimeException("could not retrieve abstract type from the input field", e);
            }
        }
        return StringUtils.EMPTY;
    }

    public DataObjectService getDataObjectService() {
        if (dataObjectService == null) {
            dataObjectService = KcServiceLocator.getService(DataObjectService.class);
        }
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }
}
