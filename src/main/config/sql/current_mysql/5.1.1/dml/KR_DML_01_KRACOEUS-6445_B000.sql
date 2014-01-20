DELIMITER /

update KRMS_TYP_T set SRVC_NM = CONCAT('{http://kc.kuali.org/core/v5_0}',SRVC_NM) where TYP_ID = 'KC1004'
/

DELIMITER ;

