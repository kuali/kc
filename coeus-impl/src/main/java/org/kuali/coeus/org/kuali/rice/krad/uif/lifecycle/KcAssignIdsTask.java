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
package org.kuali.coeus.org.kuali.rice.krad.uif.lifecycle;

import org.apache.commons.lang3.StringUtils;
import org.kuali.rice.krad.uif.UifConstants;
import org.kuali.rice.krad.uif.UifPropertyPaths;
import org.kuali.rice.krad.uif.component.Component;
import org.kuali.rice.krad.uif.lifecycle.ViewLifecycle;
import org.kuali.rice.krad.uif.lifecycle.ViewLifecycleTaskBase;
import org.kuali.rice.krad.uif.lifecycle.initialize.AssignIdsTask;
import org.kuali.rice.krad.uif.util.LifecycleElement;
import org.kuali.rice.krad.uif.view.View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Kc Override to ensure ids within dialogs are uniquely generated.
 */
public class KcAssignIdsTask extends ViewLifecycleTaskBase<LifecycleElement> {

    private static final Pattern DIALOGS_PATTERN = Pattern.compile(UifPropertyPaths.DIALOGS + "\\[([0-9]+?)\\]");

    /**
     * Create a task to assign component IDs during the initialize phase.
     */
    public KcAssignIdsTask() {
        super(LifecycleElement.class);
    }

    /**
     * Generate a new ID for a lifecycle element at the current phase.
     *
     * <p>
     * This method used a product of primes similar to the one used for generating String hash
     * codes. In order to minimize to collisions a large prime is used, then when collisions are
     * detected a different large prime is used to generate an alternate ID.
     * </p>
     *
     * <p>
     * The hash code that the generated ID is based on is equivalent (though not identical) to
     * taking the hash code of the string concenation of all class names, non-null IDs, and
     * successor index positions in the lifecycle phase tree for all predecessors of the current
     * phase. This technique leads to a reliably unique ID that is also repeatable across server
     * instances and test runs.
     * </p>
     *
     * <p>
     * The use of large primes by this method minimizes collisions, and therefore reduces the
     * likelihood of a race condition causing components to come out with different IDs on different
     * server instances and/or test runs.
     * </p>
     *
     * @param element The lifecycle element for which to generate an ID.
     * @param view View containing the lifecycle element.
     * @return An ID, unique within the current view, for the given element.
     *
     * @see org.kuali.rice.krad.uif.view.ViewIndex#observeAssignedId(String)
     * @see String#hashCode() for the algorithm this method is based on.
     */
    public static String generateId(LifecycleElement element, View view) {
        // Calculate a hash code based on the path to the top of the phase tree
        // without building a string.
        int prime = 6971;

        // Initialize hash to the class of the lifecycle element
        int hash = element.getClass().getName().hashCode();

        // Add the element's path to the hash code.
        hash += prime;
        if (element.getViewPath() != null) {
            hash += element.getViewPath().hashCode();
        }

        // Ensure sure dialog child components have a unique id (because dialogs can be dynamically requested)
        // and end up with a similar viewPath
        // Uses the dialog id as part of the hash for their child component ids
        if (element.getViewPath() != null && element.getViewPath().startsWith(UifPropertyPaths.DIALOGS + "[")) {
            Matcher matcher = DIALOGS_PATTERN.matcher(element.getViewPath());
            int index = -1;
            matcher.find();
            String strIndex = matcher.group(1);
            if (StringUtils.isNotBlank(strIndex)) {
                index = Integer.valueOf(strIndex);
            }

            if (view.getDialogs() != null && index > -1 && index < view.getDialogs().size()) {
                Component parentDialog = view.getDialogs().get(index);
                if (parentDialog != null && StringUtils.isNotBlank(parentDialog.getId())) {
                    hash += parentDialog.getId().hashCode();
                }
            }
        }

        // Eliminate negatives without losing precision, and express in base-36
        String id = Long.toString(((long) hash) - ((long) Integer.MIN_VALUE), 36);
        while (!view.getViewIndex().observeAssignedId(id)) {
            // Iteratively take the product of the hash and another large prime
            // until a unique ID has been generated.
            hash *= 4507;
            id = Long.toString(((long) hash) - ((long) Integer.MIN_VALUE), 36);
        }

        return UifConstants.COMPONENT_ID_PREFIX + id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performLifecycleTask() {
        LifecycleElement element = getElementState().getElement();

        if (StringUtils.isBlank(element.getId())) {
            element.setId(generateId(element, ViewLifecycle.getView()));
        }
    }

}
