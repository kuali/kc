UPDATE QUESTION
SET LOOKUP_CLASS   = 'org.kuali.rice.kns.bo.State', LOOKUP_RETURN = 'postalStateName'
WHERE LOOKUP_CLASS = 'org.kuali.kra.bo.State';

COMMIT;