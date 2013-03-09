DELIMITER /
update KRIM_TYP_T set SRVC_NM = CONCAT('{http://kc.kuali.org/core/v5_0}',SRVC_NM) where NM LIKE 'Derived Role%' AND NMSPC_CD LIKE 'KC-%'
/
update KRIM_TYP_T set SRVC_NM = CONCAT('{http://kc.kuali.org/core/v5_0}',SRVC_NM) where SRVC_NM = 'unitHierarchyRoleTypeService'
/
update KRIM_TYP_T set SRVC_NM = CONCAT('{http://kc.kuali.org/core/v5_0}',SRVC_NM) where SRVC_NM = 'unitRoleTypeService'
/
update KRIM_TYP_T set SRVC_NM = CONCAT('{http://kc.kuali.org/core/v5_0}',SRVC_NM) where SRVC_NM = 'protocolApproverRoleTypeService'
/
update KRMS_TYP_T set SRVC_NM = CONCAT('{http://kc.kuali.org/core/v5_0}',SRVC_NM) where NM= 'Unit Agenda'
/
update KRMS_TYP_T set SRVC_NM = CONCAT('{http://kc.kuali.org/core/v5_0}',SRVC_NM) where NM= 'Question Term Resolver Type Service'
/
update KREW_TYP_T set SRVC_NM = CONCAT('{http://kc.kuali.org/core/v5_0}',SRVC_NM) where NMSPC_CD like '%KC-%'
/
DELIMITER ;
