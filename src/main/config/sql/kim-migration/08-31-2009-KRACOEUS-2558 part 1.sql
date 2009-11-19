set define off
--Important:

--This script will do the following:
  --1) Add type code entries in KIM to support KC Person data

--This script assumes that the KRIM tables are clean otherwise constraint
--violations may occur.

--Any errors detected during data transformation will generate a message within the dbms.

--Enjoy :-)

DECLARE
  
  the_sql varchar2(9999);
  
BEGIN

  --Add new type code-table entries needed by KC
  --TODO: find the last sort code for each entry & increment if this is really that important?
  
  --Add new Prior Name Type
  the_sql := 'insert into krim_ent_nm_typ_t (ent_nm_typ_cd, obj_id, ver_nbr, nm, actv_ind, display_sort_cd, last_updt_dt)
  values (:1, :2, :3, :4, :5, :6, :7)';
  execute immediate the_sql USING 'PRIOR', SYS_GUID(), 1, 'Prior', 'Y', 'd', SYSDATE;

  --Add new Fax Phone Type
  the_sql := 'insert into krim_phone_typ_t (phone_typ_cd, obj_id, ver_nbr, phone_typ_nm, actv_ind, display_sort_cd, last_updt_dt)
  values (:1, :2, :3, :4, :5, :6, :7)';
  execute immediate the_sql USING 'FAX', SYS_GUID(), 1, 'Fax', 'Y', 'e', SYSDATE;
  
  --Add new Pager Phone Type
  the_sql := 'insert into krim_phone_typ_t (phone_typ_cd, obj_id, ver_nbr, phone_typ_nm, actv_ind, display_sort_cd, last_updt_dt)
  values (:1, :2, :3, :4, :5, :6, :7)';
  execute immediate the_sql USING 'PGR', SYS_GUID(), 1, 'Pager', 'Y', 'f', SYSDATE;

  --Add new Grad Student Staff Affiliation Type
  the_sql := 'insert into krim_afltn_typ_t (afltn_typ_cd, obj_id, ver_nbr, nm, emp_afltn_typ_ind, actv_ind, display_sort_cd, last_updt_dt)
  values (:1, :2, :3, :4, :5, :6, :7, :8)';
  execute immediate the_sql USING 'GRD_STDNT_STAFF', SYS_GUID(), 1, 'Graduate Student Staff', 'Y', 'Y', 'e', SYSDATE;

  --Add new Research Staff Affiliation Type
  the_sql := 'insert into krim_afltn_typ_t (afltn_typ_cd, obj_id, ver_nbr, nm, emp_afltn_typ_ind, actv_ind, display_sort_cd, last_updt_dt)
  values (:1, :2, :3, :4, :5, :6, :7, :8)';
  execute immediate the_sql USING 'RSRCH_STAFF', SYS_GUID(), 1, 'Research Staff', 'Y', 'Y', 'f', SYSDATE;
  
  --Add new Service Staff Affiliation Type
  the_sql := 'insert into krim_afltn_typ_t (afltn_typ_cd, obj_id, ver_nbr, nm, emp_afltn_typ_ind, actv_ind, display_sort_cd, last_updt_dt)
  values (:1, :2, :3, :4, :5, :6, :7, :8)';
  execute immediate the_sql USING 'SRVC_STAFF', SYS_GUID(), 1, 'Service Staff', 'Y', 'Y', 'g', SYSDATE;
  
  --Add new Support Staff Affiliation Type
  the_sql := 'insert into krim_afltn_typ_t (afltn_typ_cd, obj_id, ver_nbr, nm, emp_afltn_typ_ind, actv_ind, display_sort_cd, last_updt_dt)
  values (:1, :2, :3, :4, :5, :6, :7, :8)';
  execute immediate the_sql USING 'SUPPRT_STAFF', SYS_GUID(), 1, 'Support Staff', 'Y', 'Y', 'h', SYSDATE;
  
  --Add new Other Academic Group Affiliation Type
  the_sql := 'insert into krim_afltn_typ_t (afltn_typ_cd, obj_id, ver_nbr, nm, emp_afltn_typ_ind, actv_ind, display_sort_cd, last_updt_dt)
  values (:1, :2, :3, :4, :5, :6, :7, :8)';
  execute immediate the_sql USING 'OTH_ACADMC_GRP', SYS_GUID(), 1, 'Other Academic Group', 'N', 'Y', 'i', SYSDATE;
  
  --Add new Medical Staff Affiliation Type
  the_sql := 'insert into krim_afltn_typ_t (afltn_typ_cd, obj_id, ver_nbr, nm, emp_afltn_typ_ind, actv_ind, display_sort_cd, last_updt_dt)
  values (:1, :2, :3, :4, :5, :6, :7, :8)';
  execute immediate the_sql USING 'MED_STAFF', SYS_GUID(), 1, 'Medical Staff', 'Y', 'Y', 'j', SYSDATE;
  
  --Add new EXT id ERA Ccommons Type
  the_sql := 'insert into krim_ext_id_typ_t (ext_id_typ_cd, obj_id, ver_nbr, nm, encr_req_ind, actv_ind, display_sort_cd, last_updt_dt)
  values (:1, :2, :3, :4, :5, :6, :7, :8)';
  execute immediate the_sql USING 'ERAC', SYS_GUID(), 1, 'Electronic Research Admin Commons User', 'N', 'Y', '08', SYSDATE;
  
END;
