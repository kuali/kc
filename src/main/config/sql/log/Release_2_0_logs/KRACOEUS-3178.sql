insert into krim_role_perm_t (role_perm_id, obj_id, ver_nbr, role_id, perm_id, actv_ind)
 values (krim_role_perm_id_s.nextval, sys_guid(), '1',
   (select role_id from krim_role_t t where t.role_nm = 'User' and t.nmspc_cd = 'KUALI'),
   (select perm_id from krim_perm_t u where u.nm = 'Open Document' and u.nmspc_cd = 'KC-SYS'),
   'Y');
   
Commit;