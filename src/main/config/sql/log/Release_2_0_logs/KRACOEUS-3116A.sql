--this script takes all the roles assigned to the admin user and gives them to the quickstart
--user.  All start end dates for the role membership are set to null.
declare
  quickstart_id KRIM_PRNCPL_T.PRNCPL_ID%TYPE;
  admin_id KRIM_PRNCPL_T.PRNCPL_ID%TYPE;
  CURSOR rolesMembers(m_id KRIM_PRNCPL_T.PRNCPL_ID%TYPE) is select * from krim_role_mbr_t t where t.mbr_id = m_id and t.mbr_typ_cd ='P';
  
  cnt number(2);
begin
  select t.prncpl_id into quickstart_id from krim_prncpl_t t where t.prncpl_nm = 'quickstart'; 
  select t.prncpl_id into admin_id from krim_prncpl_t t where t.prncpl_nm = 'admin';

  FOR rec in rolesMembers(admin_id)
  LOOP
    select COUNT(*) into cnt from krim_role_mbr_t c where rec.role_id = c.role_id and c.mbr_id = quickstart_id and c.mbr_typ_cd = 'P';
      if (cnt = 0) then
        INSERT into krim_role_mbr_t (role_mbr_id, ver_nbr, obj_id, role_id, mbr_id, mbr_typ_cd, actv_frm_dt, actv_to_dt, last_updt_dt)
          values (krim_role_mbr_id_s.nextval, 1, sys_guid(), rec.role_id, quickstart_id, 'P', rec.actv_frm_dt, rec.actv_to_dt, sysdate);
      end if;
  END LOOP;
  
end;
