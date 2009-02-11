package org.kuali.kra.web.struts.action;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.kuali.core.util.AuditCluster;
import org.kuali.core.util.AuditError;

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
     * Sorts the AuditMap's {@link AuditError AuditError's} based on comparison rules.
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
                    
                    final Comparator<AuditError> comp = compEntry.getValue();
                    if (comp == null) {
                        throw new NullPointerException("the comparator was null");
                    }
                    
                    Collections.sort(errors, comp);
                }
            }
        }
    }
        
    /**
     * A Comparator that sorts Y/N questions in question number order.  Currently this class is nested
     * as it is only used by this class.  Feel free to refactor as needed.
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
         */
        public YNQuestionByNumber(final int questionNumberParamPosition) {
            this.questionNumberParamPosition = questionNumberParamPosition;
        }
        
        /** {@inheritDoc} */
        public int compare(AuditError o1, AuditError o2) {
            
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