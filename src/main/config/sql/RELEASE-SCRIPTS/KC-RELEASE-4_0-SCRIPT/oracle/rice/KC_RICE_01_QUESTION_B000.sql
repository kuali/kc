UPDATE QUESTION SET LOOKUP_CLASS = 'org.kuali.rice.location.impl.state.StateBo' WHERE QUESTION = 'State:'
/
UPDATE QUESTION SET LOOKUP_CLASS = 'org.kuali.rice.location.impl.country.CountryBo' WHERE QUESTION = 'Country:'
/
UPDATE QUESTION SET LOOKUP_RETURN = 'name' WHERE QUESTION = 'State:'
/
UPDATE QUESTION SET LOOKUP_RETURN = 'name'  WHERE QUESTION = 'Country:'
/
