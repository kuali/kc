DELIMITER /

UPDATE CUSTOM_ATTRIBUTE SET LOOKUP_CLASS = 'org.kuali.coeus.common.framework.custom.arg.ArgValueLookup' WHERE LOOKUP_CLASS = 'org.kuali.kra.bo.ArgValueLookup'
/
UPDATE QUESTION SET LOOKUP_CLASS = 'org.kuali.coeus.common.framework.custom.arg.ArgValueLookup' WHERE LOOKUP_CLASS = 'org.kuali.kra.bo.ArgValueLookup'
/

DELIMITER ;
