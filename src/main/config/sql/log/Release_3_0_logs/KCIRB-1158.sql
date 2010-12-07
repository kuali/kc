UPDATE QUESTION SET LOOKUP_CLASS = "org.kuali.rice.kns.bo.CountryImpl", 
                    LOOKUP_RETURN = "postalCountryName" 
WHERE LOOKUP_CLASS = "org.kuali.kra.bo.Country";

UPDATE QUESTION SET LOOKUP_CLASS = "org.kuali.rice.kns.bo.StateImpl", 
WHERE LOOKUP_CLASS = "org.kuali.rice.kns.bo.State";
