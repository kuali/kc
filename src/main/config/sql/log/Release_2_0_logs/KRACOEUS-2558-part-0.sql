set define off
--This script will do the following:
  --1) Delete all entity data in the KRIM tables
  --2) Add KIM bootstrap data required from rice

delete from krim_entity_addr_t;
delete from krim_entity_emp_info_t;
delete from krim_entity_bio_t;
delete from krim_entity_cache_t;
delete from krim_entity_ctznshp_t;
delete from krim_entity_email_t;
delete from krim_entity_ext_id_t;
delete from krim_entity_nm_t;
delete from krim_entity_phone_t;
delete from krim_entity_priv_pref_t;

delete from krim_entity_afltn_t;
delete from krim_entity_ent_typ_t;
delete from krim_entity_t;
delete from krim_prncpl_t;
commit;

INSERT INTO KRIM_ENTITY_T (ACTV_IND,ENTITY_ID,LAST_UPDT_DT,OBJ_ID,VER_NBR)
  VALUES ('Y','1',TO_DATE( '20081107094902', 'YYYYMMDDHH24MISS' ),'5B1B6B919CC96496E0404F8189D822F2',1);
/
INSERT INTO KRIM_PRNCPL_T (ACTV_IND,ENTITY_ID,LAST_UPDT_DT,OBJ_ID,PRNCPL_ID,PRNCPL_NM,VER_NBR)
  VALUES ('Y','1',TO_DATE( '20081107094902', 'YYYYMMDDHH24MISS' ),'5B1B6B919CCA6496E0404F8189D822F2','1','kr',1);
/
INSERT INTO KRIM_ENTITY_ENT_TYP_T (ACTV_IND,ENTITY_ID,ENT_TYP_CD,LAST_UPDT_DT,OBJ_ID,VER_NBR)
  VALUES ('Y','1','SYSTEM',TO_DATE( '20081107094902', 'YYYYMMDDHH24MISS' ),'5B1B6B919CCB6496E0404F8189D822F2',1);
/
commit;