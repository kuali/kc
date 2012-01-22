package org.kuali.kra.common.printing;

import java.util.List;

import org.kuali.rice.core.web.format.Formatter;
import org.kuali.rice.kns.web.comparator.CellComparatorHelper;
import org.kuali.rice.kns.web.ui.Column;
import org.kuali.rice.kns.web.ui.ResultRow;
import org.kuali.rice.krad.bo.BusinessObject;

/**
 * Rice foolishly requires beans used in DisplayTag to be BusinessObjects, so this class implements an interface whose behavior is completely inapplicable
 */
public abstract class ReportBean implements BusinessObject {

    protected abstract List<Column> createColumns();

    public void refresh() {
        throw new UnsupportedOperationException("This bean can't be refreshed");
    }

    public void prepareForWorkflow() {
        throw new UnsupportedOperationException("This bean has no workflow");
    }

    protected Column createColumn(String label, String propertyName, Object property, Class clazz) {
//        if(property == null) {
//            property = "";
//        }
        Column column = new Column(label, propertyName);
        column.setComparator(CellComparatorHelper.getAppropriateComparatorForPropertyClass(clazz));
        column.setFormatter(Formatter.getFormatter(clazz));
        column.setPropertyValue(property == null ? "" : column.getFormatter().format(property).toString());
        return column;
    }

    /**
     * Here's the story of this method:
     *
     * Rice found a great utility called DisplayTag (http://displaytag.sourceforge.net). I've worked with DisplayTag. It's a simple and effective tool for
     * creating tables with a lot of inherent functionality.
     *
     * Rice then ruined its simplicity by creating couplings to Rice classes in the exporters (CSV, XML, and XLS) so that the
     * basic DisplayTag behavior of exporting a table of POJOs no longer works. (These dependencies are manifested in properties set in displaytag.properties,
     * specifically, the export.<media>.class properties.
     *
     * Instead of working with a collection of POJOs Rice forced the collection to be a collection of ResultRows which have a bunch of Rice framework specific
     * properties. Each ResultRow forces the properties of the bean to be expressed as another Rice-specific type called a Column. A Column also has a bunch of
     * Rice-framework specific properties.
     *
     * This is the modus operandi of Rice. Find something which has great utility and modify it until it only works to solve one kind of Rice-specific
     * problem. In this case, they were trying to create a generic lookup solution. Along the way, they ruined a perfectly good utility API, DisplayTag.
     *
     * To work around this foolishness, this POJO has a factory method to create a ResultRow from itself. That ResultRow creates Column objects for each
     * property.
     */
    public ResultRow createResultRow() {
        ResultRow resultRow = new ResultRow(createColumns(), null, null);
        resultRow.setBusinessObject(this);
        return resultRow;
    }
}
