
declare

  quickstart_id KRIM_PRNCPL_T.PRNCPL_ID%TYPE;

  cursor missingGroups(m_id KRIM_PRNCPL_T.PRNCPL_ID%TYPE) is select distinct t.grp_id from krim_grp_t t
       where (select count(*) from krim_grp_mbr_t u where u.grp_id = t.grp_id
             and u.mbr_typ_cd = 'P'
             and u.mbr_id = m_id) = 0;      

begin
  select t.prncpl_id into quickstart_id from krim_prncpl_t t where t.prncpl_nm = 'quickstart'; 
  
  FOR rec IN missingGroups(quickstart_id)
  LOOP
     insert into krim_grp_mbr_t (grp_mbr_id, ver_nbr, obj_id, grp_id, mbr_id, mbr_typ_cd, actv_frm_dt, actv_to_dt, last_updt_dt)
     values (krim_grp_mbr_id_s.nextval, 1, sys_guid(), rec.grp_id, quickstart_id, 'P', null, null, sysdate);
  END LOOP;
  
end;


