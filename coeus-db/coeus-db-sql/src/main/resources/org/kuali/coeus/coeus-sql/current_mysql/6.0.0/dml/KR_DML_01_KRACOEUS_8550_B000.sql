DELIMITER /
delete from KRIM_ROLE_MBR_T where ROLE_ID =
  (select ROLE_ID from krim_role_t where ROLE_NM = 'CustomReviewer' and NMSPC_CD = 'KC-WKFLW')
/
DELIMITER ;
