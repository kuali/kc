-- convert sh_parm to kns_parm
insert into krns_parm_typ_t (PARM_TYP_CD,OBJ_ID,VER_NBR,NM,ACTV_IND) 
   select SH_PARM_TYP_CD,OBJ_ID,VER_NBR,SH_PARM_TYP_NM ,ACTIVE_IND from sh_parm_typ_t a  where 
     (select count(*) from krns_parm_typ_t b where PARM_TYP_CD = SH_PARM_TYP_CD) = 0 ;    
  
insert into KRNS_NMSPC_T (NMSPC_CD,OBJ_ID,VER_NBR,NM,ACTV_IND)    
   select SH_PARM_NMSPC_CD,OBJ_ID,VER_NBR,SH_PARM_NMSPC_NM,ACTIVE_IND from SH_PARM_NMSPC_T where  
     (select count(*) from KRNS_NMSPC_T b where NMSPC_CD = SH_PARM_NMSPC_CD) = 0 ;    

 
insert into krns_parm_dtl_typ_t (NMSPC_CD,PARM_DTL_TYP_CD,OBJ_ID,VER_NBR,NM,ACTV_IND) 
   select SH_PARM_NMSPC_CD,SH_PARM_DTL_TYP_CD,OBJ_ID,VER_NBR,SH_PARM_DTL_TYP_NM,ACTIVE_IND from sh_parm_dtl_typ_t ;    

insert into krns_parm_t (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT, CONS_CD,GRP_NM,ACTV_IND)    
  select SH_PARM_NMSPC_CD, SH_PARM_DTL_TYP_CD, SH_PARM_NM, OBJ_ID, VER_NBR,  SH_PARM_TYP_CD,SH_PARM_TXT, SH_PARM_DESC, SH_PARM_CONS_CD, WRKGRP_NM, ACTIVE_IND from sh_parm_t where
     (select count(*) from krns_parm_t b where NMSPC_CD = SH_PARM_NMSPC_CD and PARM_DTL_TYP_CD = SH_PARM_DTL_TYP_CD) = 0 ;    
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
-- convert en_wrkgrp_t to krim_grp_t
insert into krim_grp_t (GRP_ID, OBJ_ID, VER_NBR, GRP_NM, NMSPC_CD, GRP_DESC, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
  select WRKGRP_ID, SYS_GUID(), DB_LOCK_VER_NBR, WRKGRP_NM, 'KC', WRKGRP_DESC, WRKGRP_TYP_CD, WRKGRP_ACTV_IND, SYSTIMESTAMP from en_wrkgrp_t, dual
  where WRKGRP_CUR_IND != '0' and (select count(*) from krim_grp_t b where WRKGRP_ID = GRP_ID) = 0 ;

update krim_grp_t set ACTV_IND='Y' where ACTV_IND='1';
update krim_grp_t set ACTV_IND='N' where ACTV_IND='0';
