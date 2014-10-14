DELIMITER /
UPDATE QUESTION SET LOOKUP_CLASS='org.kuali.coeus.common.framework.rolodex.NonOrganizationalRolodex' WHERE LOOKUP_CLASS ='org.kuali.kra.bo.NonOrganizationalRolodex'
/
UPDATE QUESTION SET LOOKUP_CLASS='org.kuali.coeus.common.framework.org.Organization' WHERE LOOKUP_CLASS ='org.kuali.kra.bo.Organization'
/

DELIMITER ;
