-- assign Time and Money modifier role to quickstart
insert into krim_role_mbr_t (role_mbr_id, ver_nbr, obj_id, role_id, mbr_id, mbr_typ_cd) values (KRIM_ROLE_MBR_ID_BS_S.nextval, 1, sys_guid(), (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Time And Money Modifier'), '10000000001', 'P');

-- assign Award budget viewer role to awdbudviewer
insert into krim_role_mbr_t (role_mbr_id, ver_nbr, obj_id, role_id, mbr_id, mbr_typ_cd) values (KRIM_ROLE_MBR_ID_BS_S.nextval, 1, sys_guid(), (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Award Budget Viewer'), '10000000063', 'P');

COMMIT;