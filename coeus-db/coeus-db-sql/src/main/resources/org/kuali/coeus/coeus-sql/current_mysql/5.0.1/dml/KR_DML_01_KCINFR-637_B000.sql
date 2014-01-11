DELIMITER /
delete from krim_role_perm_t where role_id = (select role_id from krim_role_t where role_nm = 'Initiator or Reviewer' and nmspc_cd = 'KR-WKFLW') AND
perm_id = (select perm_id from krim_perm_t where nm = 'Edit Kuali ENROUTE Document Route Status Code R' and nmspc_cd = 'KUALI')
/
DELIMITER ;
