package org.kuali.kra.award.lookup.keyvalue;

import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.home.ValidRates;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.sys.framework.keyvalue.PrefixValuesFinder;
import org.kuali.kra.timeandmoney.transactions.AwardTransactionType;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.coreservice.framework.CoreFrameworkServiceLocator;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.keyvalues.PersistableBusinessObjectValuesFinder;
import org.kuali.rice.krad.service.BusinessObjectService;

public class AwardTransactionTypeBusinessObjectFilteredValuesFinder extends PersistableBusinessObjectValuesFinder {

    private static final String SYSTEM_PARAMETER_NAME = "AwardTransactionTypeAllowedValues";
    private static final String SYSTEM_PARAMETER_CMPNT_CODE = "Document";
    public static final String TRANSACTION_TYPE_CODE = "awardTransactionTypeCode";
    BusinessObjectService businessObjectService;

    class PBOComparator implements Comparator
    {
        public int compare(Object kv1, Object kv2 )
        {
            try
            {
                String desc1 = ((KeyValue)kv1).getValue();
                String desc2 = ((KeyValue)kv2).getValue();
                if (desc1 == null)
                {
                    desc1 = "";
                }
                if (desc2 == null)
                {
                    desc2 = "";
                }
                return desc1.compareTo(desc2);
            }
            catch (Exception e)
            {
                return 0;
            }
        }

    }
    /**
     * Build the list of KeyValues using the key (keyAttributeName) and
     * label (labelAttributeName) of the list of all business objects found
     * for the BO class specified along with a "select" entry.
     *
     * {@inheritDoc}
     */
    @Override
    public List<KeyValue> getKeyValues(){
        List<KeyValue> labels;

        labels = super.getKeyValues();
        Collections.sort(labels, new PBOComparator());
        labels.add(0, new ConcreteKeyValue(PrefixValuesFinder.getPrefixKey(), PrefixValuesFinder.getDefaultPrefixValue()));

        ParameterService parameterService = CoreFrameworkServiceLocator.getParameterService();
        // "AwardTransactionTypeAllowedValues" is the value of "parm_nm" column in table "krcr_parm_t"
        Collection<String> transactionValues = parameterService.getParameterValuesAsString(Constants.MODULE_NAMESPACE_AWARD, SYSTEM_PARAMETER_CMPNT_CODE, SYSTEM_PARAMETER_NAME);

        // In case the above system parameter is not defined under table "krcr_parm_t"
        // Let's return the full list of Award Transaction Types
        if (transactionValues == null || transactionValues.size() == 0) {
            return labels;
        }

        // Otherwise - System parameter has been defined
        List<KeyValue> filteredLabels = new ArrayList<KeyValue>();
        KeyValue [] labelsArray = labels.toArray(new KeyValue[labels.size()]);
        Iterator iter = transactionValues.iterator();

        while (iter.hasNext()) {
            String filteredTransactionType = (String) iter.next();
            for (int i = 0; i < labelsArray.length; i++) {

                if (!StringUtils.isBlank(labelsArray[i].getKey()) && !StringUtils.isBlank(filteredTransactionType)) {
                    if (StringUtils.equals(filteredTransactionType,labelsArray[i].getKey())) {

                        // The same index (key) should be used for the filtered list.
                        // Otherwise the value of the transaction type cannot be displayed for
                        // readOnlyAlternateDisplay mode when opening the award page
                        filteredLabels.add(new ConcreteKeyValue(labelsArray[i].getKey(), labelsArray[i].getValue()));
                    }
                }
                // If any transaction type defined in table "krcr_parm_t" is not in the original drop down list
                // Then we won't add it into the filtered list.
            }
        }

        Collections.sort(filteredLabels, new PBOComparator());
        // We need "select" on the top of the drop down list as well
        filteredLabels.add(0, new ConcreteKeyValue(PrefixValuesFinder.getPrefixKey(), PrefixValuesFinder.getDefaultPrefixValue()));

        return filteredLabels;
    }

    /**
     * Gets the businessObjectService attribute.
     * @return Returns the businessObjectService.
     */
    protected BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }


    /**
     * Sets the businessObjectService attribute value.
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
}
