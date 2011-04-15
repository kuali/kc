set echo on;
spool POST-3_0_1-KR-2011-01-26-ORACLE.log

-- Check for pre-existing group membership
DECLARE
    DUMMY NUMBER;
BEGIN
    SELECT GRP_MBR_ID INTO DUMMY FROM KRIM_GRP_MBR_T 
        WHERE GRP_ID = (select grp_id from krim_grp_t where GRP_NM = 'IRBAdmin' and NMSPC_CD = 'KC-WKFLW')
          AND MBR_ID = (select prncpl_id from krim_prncpl_t where prncpl_nm = 'kr');
    EXCEPTION WHEN NO_DATA_FOUND THEN 
        INSERT INTO KRIM_GRP_MBR_T(GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT) 
            VALUES(krim_grp_mbr_id_s.nextval, 0, SYS_GUID(), 
                (select grp_id from krim_grp_t where GRP_NM = 'IRBAdmin' and NMSPC_CD = 'KC-WKFLW'), 
                (select prncpl_id from krim_prncpl_t where prncpl_nm = 'kr'), 'P', NULL, NULL, NULL);
END;
/

COMMIT;