INSERT INTO KRIM_GRP_MBR_ID_S (ID) SELECT DISTINCT NULL FROM KRIM_GRP_MBR_ID_S
    WHERE NOT EXISTS
	(SELECT GRP_MBR_ID FROM KRIM_GRP_MBR_T
	    WHERE GRP_ID = (select grp_id from krim_grp_t where GRP_NM = 'IRBAdmin' and NMSPC_CD = 'KC-WKFLW')
	      AND MBR_ID = (select prncpl_id from krim_prncpl_t where prncpl_nm = 'kr'));

INSERT INTO KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT) 
    SELECT MAXid, 0, UUID(), 
        (select grp_id from krim_grp_t where GRP_NM = 'IRBAdmin' and NMSPC_CD = 'KC-WKFLW'), 
        (select prncpl_id from krim_prncpl_t where prncpl_nm = 'kr'), 
        'P', NULL, NULL, NULL FROM (select Max(ID) maxid from KRIM_GRP_MBR_ID_S) s
    WHERE NOT EXISTS
     (SELECT GRP_MBR_ID FROM KRIM_GRP_MBR_T
         WHERE GRP_ID = (select grp_id from krim_grp_t where GRP_NM = 'IRBAdmin' and NMSPC_CD = 'KC-WKFLW')
           AND MBR_ID = (select prncpl_id from krim_prncpl_t where prncpl_nm = 'kr'));