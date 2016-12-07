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
package org.kuali.coeus.propdev.impl.person.creditsplit;


import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.kuali.coeus.common.framework.type.InvestigatorCreditType;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentViewHelperServiceImpl;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.krad.uif.component.BindingInfo;
import org.kuali.rice.krad.uif.component.Component;
import org.kuali.rice.krad.uif.container.CollectionGroupBase;
import org.kuali.rice.krad.uif.field.DataFieldBase;
import org.kuali.rice.krad.uif.lifecycle.ViewLifecycleRestriction;
import org.kuali.rice.krad.uif.util.*;



import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CreditSplitCustomColumnsCollection extends CollectionGroupBase {
    private static final Logger LOG = Logger.getLogger(CreditSplitCustomColumnsCollection.class);

    private DataFieldBase columnFieldPrototype;
    private BindingInfo columnBindingInfo;
    private Class<?> columnObjectClass;
    private String columnLabelPropertyName;

    @Override
    public void performInitialization(Object model) {

        ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) model;
        ((ProposalDevelopmentViewHelperServiceImpl) pdForm.getViewHelperService()).setInvestigatorCreditTypes(pdForm);
        List<ProposalPerson> investigators = ((ProposalDevelopmentDocumentForm) model).getDevelopmentProposal().getInvestigators();
        if (CollectionUtils.isNotEmpty(investigators)) {
        List<InvestigatorCreditType> columnCollection = ObjectPropertyUtils.getPropertyValue(model,
                getColumnBindingInfo().getBindingPath());


        List<Component> columns = new ArrayList<Component>();
        for (Component component : this.getItems()) {
            if (component.isRender() || component.isHidden()) {
                columns.add(component);
            }
        }

        WorkflowDocument workflowDocument = pdForm.getProposalDevelopmentDocument().getDocumentHeader().getWorkflowDocument();
        boolean isSubmitted = !workflowDocument.isInitiated() && !workflowDocument.isSaved();
        int index = 0;
        for (Object column : filterColumns(columnCollection, investigators, isSubmitted)) {
            DataFieldBase columnField = ComponentUtils.copy(columnFieldPrototype);
            String columnLabel = StringUtils.isEmpty(columnLabelPropertyName)?"description":columnLabelPropertyName;

            try {
                columnField.getFieldLabel().setLabelText(PropertyUtils.getNestedProperty(column,columnLabel).toString());
                columnField.getBindingInfo().setBindingName("creditSplits[" + index + "].credit");
                columnField.setPropertyName("creditSplits.credit");
                columnField.setOrder(100 + index);
                columns.add(columnField);
            } catch (Exception e) {
                LOG.error("Could not retrieve column label from column collection item",e);
            }
        index++;
        }
        this.setItems(columns);
        }
        super.performInitialization(model);
    }

    protected List<InvestigatorCreditType> filterColumns(List<InvestigatorCreditType> columnCollection, List<ProposalPerson> investigators, boolean isSubmitted) {
        if (!isSubmitted) {
            return columnCollection;
        }

        return columnCollection.stream().filter(column -> {
            return investigators.stream().anyMatch(investigator -> {
                return investigator.getCreditSplits().stream().anyMatch(proposalPersonCreditSplit ->  {
                    return proposalPersonCreditSplit.getInvCreditTypeCode().equals(column.getCode());
                });
            });
        }).collect(Collectors.toList());

    }

    @ViewLifecycleRestriction
    public DataFieldBase getColumnFieldPrototype() {
        return columnFieldPrototype;
    }

    public void setColumnFieldPrototype(DataFieldBase columnFieldPrototype) {
        this.columnFieldPrototype = columnFieldPrototype;
    }

    public BindingInfo getColumnBindingInfo() {
        return columnBindingInfo;
    }

    public void setColumnBindingInfo(BindingInfo columnBindingInfo) {
        this.columnBindingInfo = columnBindingInfo;
    }

    public Class<?> getColumnObjectClass() {
        return columnObjectClass;
    }

    public void setColumnObjectClass(Class<?> columnObjectClass) {
        this.columnObjectClass = columnObjectClass;
    }

    public String getColumnLabelPropertyName() {
        return columnLabelPropertyName;
    }

    public void setColumnLabelPropertyName(String columnLabelPropertyName) {
        this.columnLabelPropertyName = columnLabelPropertyName;
    }
}
