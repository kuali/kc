insert into krim_role_mbr_t
(role_mbr_id, role_id, mbr_id, mbr_typ_cd, last_updt_dt, ver_nbr, obj_id)
values ((select (max(to_number(role_mbr_id)) + 1) from krim_role_mbr_t where role_mbr_id is not NULL and to_number(role_mbr_id) < 10000),
        (select role_id from krim_role_t where role_nm = 'Kuali Rules Management System Administrator' and nmspc_cd = 'KR-RULE'),
        (select prncpl_id from krim_prncpl_t where prncpl_nm = 'admin'),
        'P', current_date, 1, sys_guid())
/
