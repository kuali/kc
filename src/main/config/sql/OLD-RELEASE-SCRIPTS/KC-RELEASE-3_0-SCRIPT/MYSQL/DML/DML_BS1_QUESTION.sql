UPDATE QUESTION
SET LOOKUP_CLASS   = 'org.kuali.rice.kns.bo.StateImpl', LOOKUP_RETURN = 'postalStateName'
WHERE LOOKUP_CLASS = 'org.kuali.kra.bo.State';

UPDATE QUESTION 
SET LOOKUP_CLASS = 'org.kuali.rice.kns.bo.CountryImpl', LOOKUP_RETURN = 'postalCountryName' 
WHERE LOOKUP_CLASS = 'org.kuali.kra.bo.Country';

COMMIT;