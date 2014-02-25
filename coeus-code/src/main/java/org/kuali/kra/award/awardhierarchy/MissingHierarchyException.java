package org.kuali.kra.award.awardhierarchy;

/**
 * Custom exception thrown when a hierarchy node search fails to yield an AwardHierarchy when it should have
 */
public class MissingHierarchyException extends RuntimeException {
    public MissingHierarchyException(String awardNumber) {
        super("No hierarchy node found for awardNumber: " + awardNumber);   
    }
}
