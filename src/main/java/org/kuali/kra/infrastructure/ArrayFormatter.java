package org.kuali.kra.infrastructure;

import org.kuali.core.web.format.Formatter;

/**
 * Currently, in the {@link Formatter Formatter} class, array are formatted
 * by returning the first item in the array.  This is not acceptable in some cases.
 * 
 * <p>
 * One example is a struts multi-select dropdown.  In order for struts to correctly handle
 * field highlighting struts expects an array.
 * </p>
 * 
 * <p>
 * This formatter does not handle {@code null} arrays or arrays with {@code null} items.
 * This class may need to be modified to handle these conditions.
 * </p>
 */
public class ArrayFormatter extends Formatter {
    
    private static final long serialVersionUID = -2207993820610088716L;

    /**
     * Returns the value passed in.
     * 
     * {@inheritDoc}
     */
    @Override
    public Object formatObject(Object value) {
        return value;
    }
}