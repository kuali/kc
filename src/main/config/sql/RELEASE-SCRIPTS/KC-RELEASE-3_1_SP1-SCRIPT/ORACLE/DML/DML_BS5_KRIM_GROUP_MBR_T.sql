INSERT INTO KRIM_GRP_MBR_T(GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT) 
    VALUES(krim_grp_mbr_id_bs_s.nextval, 0, SYS_GUID(), (select grp_id from krim_grp_t where GRP_NM = 'IRBAdmin' and NMSPC_CD = 'KC-WKFLW'), 
    (select prncpl_id from krim_prncpl_t where prncpl_nm = 'kr'), 'P', NULL, NULL, NULL);
