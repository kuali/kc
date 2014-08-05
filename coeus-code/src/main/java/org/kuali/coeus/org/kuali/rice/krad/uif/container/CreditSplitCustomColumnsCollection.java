package org.kuali.coeus.org.kuali.rice.krad.uif.container;


import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentViewHelperServiceImpl;
import org.kuali.rice.krad.uif.component.BindingInfo;
import org.kuali.rice.krad.uif.component.Component;
import org.kuali.rice.krad.uif.container.CollectionGroupBase;
import org.kuali.rice.krad.uif.field.DataFieldBase;
import org.kuali.rice.krad.uif.util.*;



import java.util.ArrayList;
import java.util.List;

public class CreditSplitCustomColumnsCollection extends CollectionGroupBase {
    private static final Logger LOG = Logger.getLogger(CreditSplitCustomColumnsCollection.class);

    private DataFieldBase columnFieldPrototype;
    private BindingInfo columnBindingInfo;
    private Class<?> columnObjectClass;
    private String columnLabelPropertyName;

    @Override
    public void performInitialization(Object model) {
        super.performInitialization(model);
    }

    @Override
    public void performApplyModel(Object model, LifecycleElement parent) {

        ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) model;
        ((ProposalDevelopmentViewHelperServiceImpl) pdForm.getViewHelperService()).setInvestigatorCreditTypes(pdForm);
        if (CollectionUtils.isNotEmpty(((ProposalDevelopmentDocumentForm) model).getDevelopmentProposal().getInvestigators())) {
        List<Object> columnCollection = ObjectPropertyUtils.getPropertyValue(model,
                getColumnBindingInfo().getBindingPath());


        List<Component> columns = new ArrayList<Component>();
        for (Component component : this.getItems()) {
            if (component.isRender() || component.isHidden()) {
                columns.add(component);
            }
        }

            int index = 0;
            for (Object column : columnCollection) {
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
        super.performApplyModel(model, parent);
    }

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
