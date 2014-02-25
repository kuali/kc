DELIMITER /

INSERT INTO KRIM_GRP_MBR_ID_S VALUES(NULL)
/

INSERT INTO KRIM_GRP_MBR_T (GRP_MBR_ID, GRP_ID, MBR_ID, MBR_TYP_CD, LAST_UPDT_DT, OBJ_ID, VER_NBR) 
       VALUES ((SELECT (MAX(ID)) FROM KRIM_GRP_MBR_ID_S), (SELECT GRP_ID FROM KRIM_GRP_T WHERE NMSPC_CD = 'KC-WKFLW' AND GRP_NM = 'QuestionnaireAdmin'), (SELECT GRP_ID FROM KRIM_GRP_T WHERE NMSPC_CD = 'KC-WKFLW' AND GRP_NM = 'KcAdmin'), 'G', NOW(), UUID(), 1)
/

DELIMITER ;

