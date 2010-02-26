delete from krim_role_perm_t where perm_id in (select perm_id from krim_perm_t where nm like '%Proposal Log%') and role_id in (select role_id from krim_role_t where role_nm = 'OSP Administrator');
