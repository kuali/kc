/*
 * Copyright 2006-2009 The Kuali Foundation Licensed under the Educational
 * Community License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License
 * at http://www.opensource.org/licenses/ecl1.php Unless required by applicable
 * law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.kuali.kra.lookup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.core.bo.BusinessObject;
import org.kuali.core.lookup.PessimisticLockLookupableHelperServiceImpl;

/**
 * Modifies the field values for a search.
 * 
 * @see #getSearchResults(Map)
 */
public class KraPessimisticLockLookupableHelperServiceImpl extends PessimisticLockLookupableHelperServiceImpl {

    private static final long serialVersionUID = -6838248755135780573L;
    
    private static final String GEN_TIMESTAMP_FORMAT = "MM/dd/yyyy";
    private static final String GEN_TIMESTAMP_NAME = "generatedTimestamp";
    private static final String DATE_RANGE_DELIMETER = "..";
    
    private static final Log LOG = LogFactory.getLog(KraPessimisticLockLookupableHelperServiceImpl.class);

    /**
     * This method modifies the field values for the following condition. If a
     * generated time is present then that time will be modified so that all
     * times for that day will be matched. For example: If a time exists with
     * the value 02/18/2009 11:59 AM then 02/18/2009 8:00 AM will also be a
     * match. Basically this method forces date/time lookups to be time
     * insensitive.
     * 
     * {@inheritDoc}
     */
    @Override
    public List<? extends BusinessObject> getSearchResults(final Map<String, String> fieldValues) {

        if (fieldValues == null) {
            throw new NullPointerException("the fieldValues is null");
        }

        this.addSingleDayDateRange(fieldValues);

        return super.getSearchResults(fieldValues);
    }

    /**
     * This method looks for the generated time value in the fieldValues map and
     * modifies the value to be a rice recognized date range. This method
     * expects the format of the generated time value be be in
     * {@value #GEN_TIMESTAMP_FORMAT} format. If it is not a message will be
     * logged and the generated time value will not be modified.
     * 
     * <p>
     * For example: if the the time is 01/01/2009 then it will be modified to be
     * 01/01/2009..01/02/2009.
     * </p>
     * 
     * @param fieldValues the values to map to modify.
     *            
     */
    private void addSingleDayDateRange(final Map<String, String> fieldValues) {
        assert fieldValues != null : "the fieldValues is null";

        final String timeStamp = fieldValues.get(GEN_TIMESTAMP_NAME);
        
        if (timeStamp != null) {
            final SimpleDateFormat sdf = new SimpleDateFormat(GEN_TIMESTAMP_FORMAT);
            
            try {
                final Date d = sdf.parse(timeStamp);
                final String dateRange = buildDateRange(timeStamp, this.getNextDateString(d));
                fieldValues.put(GEN_TIMESTAMP_NAME, dateRange);
            } catch (final ParseException e) {
                // logging at info level because timeStamp is likely to be at an incorrect format
                //which in this case is not a problem
                if (LOG.isInfoEnabled()) {
                    LOG.info("expected generated time at the following format "
                        + GEN_TIMESTAMP_FORMAT);
                }
            }
        }

    }

    /**
     * Creates a string representing of the date passed in incremented by 1 day in a
     * {@value #GEN_TIMESTAMP_FORMAT} format.
     * 
     * @param d the date
     * @return the incremented date string
     */
    private String getNextDateString(final Date d) {
        assert d != null : "the date is null";
        
        final SimpleDateFormat sdf = new SimpleDateFormat(GEN_TIMESTAMP_FORMAT);
        final Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.DATE, 1);

        return sdf.format(cal.getTime());
    }
    
    /**
     * Builds a rice recognizable date range.
     * 
     * @param leftDate the date on the left
     * @param rightDate the date on the right
     * @return date range
     */
    private String buildDateRange(final String leftDate, final String rightDate) {
        assert leftDate != null : "the leftDate is null";
        assert rightDate != null : "the rightDate is null";
        
        final StringBuilder range = new StringBuilder();
        
        range.append(leftDate);
        range.append(DATE_RANGE_DELIMETER);
        range.append(rightDate);
        
        return range.toString();
    }
}
