DELIMITER /
INSERT INTO KRIM_ROLE_MBR_ID_S VALUES(NULL)
/
insert into krim_role_mbr_t
(role_mbr_id, role_id, mbr_id, mbr_typ_cd, last_updt_dt, ver_nbr, obj_id)
values ((SELECT (MAX(ID)) FROM KRIM_ROLE_MBR_ID_S),
(select role_id from krim_role_t where role_nm = 'Kuali Rules Management System Administrator' and nmspc_cd = 'KR-RULE'),
(select prncpl_id from krim_prncpl_t where prncpl_nm = 'quickstart'),
'P', current_date, 1, UUID())
/

DELIMITER ;
