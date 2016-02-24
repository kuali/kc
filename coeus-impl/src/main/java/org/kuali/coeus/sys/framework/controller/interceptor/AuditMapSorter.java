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
package org.kuali.coeus.sys.framework.controller.interceptor;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;

import java.io.Serializable;
import java.util.*;

/**
 * Contains methods to sort an AuditMap's {@link AuditError AuditError's}.
 */
final class AuditMapSorter {

    /**
     * Default pattern/comparator map.  Currently supports sorting y/n questions by question number.
     * @see #sort(Map). This map in not modifiable.
     */
    public static final Map<String, Comparator<AuditError>> DEFAULT_PATTERN_COMPARATOR_MAP;
    static {
        final Map<String, Comparator<AuditError>> tempComparators
            = new LinkedHashMap<String, Comparator<AuditError>>();
        
        tempComparators.put(".*ynq.*", YNQuestionByNumber.Q_NUM_ZERO_POSITION);
        DEFAULT_PATTERN_COMPARATOR_MAP = Collections.unmodifiableMap(tempComparators);
    }
    
    private final Map<String, AuditCluster> auditErrorsMap;
    
    /**
     * Sets the audit map to sort.
     * @throws NullPointerException if the audit map is null
     */
    public AuditMapSorter(final Map<String, AuditCluster> auditErrorsMap) {
        
        if (auditErrorsMap == null) {
            throw new NullPointerException("the auditErrorsMap is null");
        }
        
        this.auditErrorsMap = auditErrorsMap;
    }
    
    /**
     * <p>
     * Sorts the AuditMap's {@link AuditError AuditError's} based on comparison rules.  The passed in
     * Audit Map will be directly modified by the method.
     * </p>
     * 
     * <p>
     * These comparison rules are in the form of a {@link Map Map} of regex patterns with associated
     * {@link Comparator Comparators}. Each key for an audit map entry will be matched against the regex
     * patterns contained in the comparatorsForAuditErrors.  When a match is found the associated
     * comparator will be used to sort the AuditErrors.  In the case of multiple matches the first match
     * will be used.  If a predictable match order is desired a {@link LinkedHashMap LinkedHashMap}
     * should be used.
     * </p>
     * 
     * <p>
     * A default map is provided at {@link #DEFAULT_PATTERN_COMPARATOR_MAP DEFAULT_PATTERN_COMPARATOR_MAP}
     * </p>
     *
     * @throws NullPointerException if the pattern map is null or if it contains a null Comparator
     * @throws IllegalArgumentException if the pattern map is empty
     */
    public void sort(final Map<String, Comparator<AuditError>> patternComparatorMap) {
        
        if (patternComparatorMap == null) {
            throw new NullPointerException("the comparatorsForAuditErrors is null");
        }
        
        if (patternComparatorMap.isEmpty()) {
            throw new IllegalArgumentException("no comparator entries provided");
        }

        for (Map.Entry<String, AuditCluster> entryError : this.auditErrorsMap.entrySet()) {
            final AuditCluster cluster = entryError.getValue();
            
            for (Map.Entry<String, Comparator<AuditError>> compEntry : patternComparatorMap.entrySet()) {
                
                if (entryError.getKey().matches(compEntry.getKey())) {
                    @SuppressWarnings("unchecked")
                    List<AuditError> errors = cluster.getAuditErrorList();
                    
                    final Comparator<AuditError> comp = this.getComparator(compEntry.getValue());
                    Collections.sort(errors, comp);
                }
            }
        }
    }
    
    /**
     * This methods returns the comparator passed in if not null.
     * Used to prevent {@link Collections#sort(List, Comparator)} from falling back
     * to natural ordering.
     * 
     * @param comp The Comparator
     * @return The Comparator
     * @throws NullPointerException comp is null
     */
    private Comparator<AuditError> getComparator(final Comparator<AuditError> comp) {
        
        if (comp == null) {
            throw new NullPointerException("the comparator was null");
        }
        
        return comp;
    }
        
    /**
     * A Comparator that sorts Y/N questions in question number order.  Currently this class is nested
     * as it is only used by this class.  Feel free to refactor as needed.
     * 
     * <p>
     * This Comparator is not consistent with {@link AuditError#equals(Object) AuditError#equals(Object)}.
     * </p>
     */
    private static final class YNQuestionByNumber implements Comparator<AuditError>, Serializable {
        
        /** convenience instance that looks for the question number in the zero position. */
        public static final Comparator<AuditError> Q_NUM_ZERO_POSITION = new YNQuestionByNumber(0);
        
        private static final long serialVersionUID = 7978642168434892454L;
        
        private final int questionNumberParamPosition;
        
        /**
         * Sets the parameter position to find the question number.
         * 
         * @param questionNumberParamPosition parameter position
         * @throws IllegalArgumentException if questionNumberParamPosition is < 0
         */
        public YNQuestionByNumber(final int questionNumberParamPosition) {
            
            if (questionNumberParamPosition < 0) {
                throw new IllegalArgumentException(questionNumberParamPosition + " is < 0");
            }
            
            this.questionNumberParamPosition = questionNumberParamPosition;
        }
        
        /**
         * {@inheritDoc}
         * @throws NullPointerException if o1 or o2 is null
         */
        public int compare(AuditError o1, AuditError o2) {
            
            if (o1 == null) {
                throw new NullPointerException("o1 is null");
            }
            
            if (o2 == null) {
                throw new NullPointerException("o2 is null");
            }
            
            final String qNumber1 = this.getQuestonNumber(o1.getParams());
            final String qNumber2 = this.getQuestonNumber(o2.getParams());
            
            if (!NumberUtils.isNumber(qNumber1)) {
                return -1;
            }

            if (!NumberUtils.isNumber(qNumber2)) {
                return 1;
            }

            return Integer.valueOf(qNumber1).compareTo(Integer.valueOf(qNumber2));
        }
        
        /**
         * This method returns the question number from a parameter array (ex: array[0]).
         * @param array the parameter array holding the question number.  It can be null.
         * @return returns question number.
         * If array is null or question number is null this method will return null.
         * (i.e. (array[questionNumberParamPosition] == null || array == null) then returns null)
         */
        private String getQuestonNumber(String[] array) {
            if (ArrayUtils.getLength(array) > this.questionNumberParamPosition) {
                return array[this.questionNumberParamPosition];
            }
            return null;
        }
    }
        
}
