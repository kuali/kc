package org.kuali.coeus.sys.framework.persistence;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.eclipse.persistence.config.DescriptorCustomizer;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.expressions.Expression;
import org.eclipse.persistence.expressions.ExpressionBuilder;
import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.ForeignReferenceMapping;

import java.util.Map;

/**
 * This is a simple EclipseLink customizer which filters the retrieval of elements for the
 * attribute name using the criteria supplied in the filterMap.
 *
 * For example:
 *
 * @Customizer(FullTimeActiveEmployeesCustomizer.class)
 * class Department {
 *     List<Employee> employees;
 * }
 *
 * class Employee {
 *     boolean active;
 *     String type; //F for full time
 * }
 *
 * You could use this class to only retrieve all full time active employees
 *
 * class FullTimeActiveEmployeesCustomizer extends FilterByMapDescriptorCustomizer {
 *   @Override
 *   protected String getAttributeName() [
 *      return "employees";
 *   }
 *
 *   @Override
 *   protected Map<String, ?> getFilterMap()  {
 *      Map<String, Object> filterMap = new HashMap<String, Object>();
 *      filterMap.put("active", true);
 *      filterMap.put("type", "F");
 *      return filterMap;
 *   }
 * }
 */
@Deprecated
public abstract class FilterByMapDescriptorCustomizer implements DescriptorCustomizer {

    /**
     * The attribute to customize the fetch query for.  Must return a valid attribute name.
     * @return attribute name.
     */
    protected abstract String getAttributeName();

    /**
     * The filter criteria.  The keys must be attribute names on the referenced class.  The values
     * must be values that valid for the attribute.  The values are used to build up filter criteria
     * in order to customize the fetch query.  Assuming the map implementation supports it, null
     * values are permitted in the map.  A null map is not permitted.
     * @return filter map of attribute names to attribute values
     */
    protected abstract Map<String, ?> getFilterMap();

    @Override
    public final void customize(ClassDescriptor descriptor) throws Exception {
        if (StringUtils.isBlank(getAttributeName())) {
            throw new IllegalStateException("getAttributeName() must return a non-blank attribute name");
        }

        if (MapUtils.isEmpty(getFilterMap())) {
            throw new IllegalStateException("getFilterMap() must return a non-blank attribute name");
        }

        final DatabaseMapping dbMapping = descriptor.getMappingForAttributeName(getAttributeName());
        if (dbMapping instanceof ForeignReferenceMapping) {
            final ForeignReferenceMapping frMapping  = (ForeignReferenceMapping) dbMapping;
            final Class<?> refClass = frMapping.getReferenceClass();
            final Expression curExpr = frMapping.getSelectionCriteria();
            frMapping.setSelectionCriteria(curExpr.and(createFilterExpressionForAttrFromMap(refClass, getFilterMap())));
        } else {
            throw new IllegalStateException("this DescriptorCustomizer only works on reference attributes " + dbMapping != null ? dbMapping.getClass().getName() : "null");
        }
    }

    private Expression createFilterExpressionForAttrFromMap(Class<?> refClass, Map<String, ?> filterMap) {
        final ExpressionBuilder eb = new ExpressionBuilder(refClass);
        Expression filterExpr = null;
        for (Map.Entry<String, ?> entry : filterMap.entrySet()) {
            final Object value = entry.getValue();
            final Expression subExpr = value != null ? eb.get(entry.getKey()).equal(value) : eb.get(entry.getKey()).isNull();
            filterExpr = (filterExpr == null) ? subExpr : filterExpr.and(subExpr);
        }
        return filterExpr;
    }
}
