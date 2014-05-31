DELIMITER /

UPDATE KRIM_TYP_T SET SRVC_NM = CONCAT('{http://kc.kuali.org/core/v5_0}', SRVC_NM) 
WHERE NM = 'Derived Role: IACUC Correspondent' AND SRVC_NM = 'iacucCorrespondentDerivedRoleTypeService' AND NMSPC_CD = 'KC-IACUC'
/

DELIMITER ;
