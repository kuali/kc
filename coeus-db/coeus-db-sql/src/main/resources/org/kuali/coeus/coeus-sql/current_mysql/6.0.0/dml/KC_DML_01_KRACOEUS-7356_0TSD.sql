DELIMITER /

UPDATE CUSTOM_ATTRIBUTE 
SET LOOKUP_CLASS='org.kuali.coeus.common.framework.person.KcPerson' 
WHERE LOOKUP_CLASS = 'org.kuali.kra.bo.KcPerson'
/

DELIMITER ;
