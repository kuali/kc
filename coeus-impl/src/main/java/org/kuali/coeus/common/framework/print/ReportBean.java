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
package org.kuali.coeus.common.framework.print;

import org.kuali.rice.core.web.format.Formatter;
import org.kuali.rice.kns.web.comparator.CellComparatorHelper;
import org.kuali.rice.kns.web.ui.Column;
import org.kuali.rice.kns.web.ui.ResultRow;
import org.kuali.rice.krad.bo.BusinessObject;

import java.util.List;

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
