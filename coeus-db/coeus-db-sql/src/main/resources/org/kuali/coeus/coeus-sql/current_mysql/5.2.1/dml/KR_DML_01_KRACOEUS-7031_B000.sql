DELIMITER /

INSERT INTO KRCR_PARM_T( NMSPC_CD, CMPNT_CD, PARM_NM, APPL_ID, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD ) 
VALUES( 'KC-SUBAWARD', 'Document', 'FDP_Prime_Administrative_Contact_Code', 'KC', UUID(), 1, 'CONFG', 0, 'FDP Prime Administrative Contact Code', 'A' )
/

INSERT INTO KRCR_PARM_T( NMSPC_CD, CMPNT_CD, PARM_NM, APPL_ID, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD )
VALUES( 'KC-SUBAWARD', 'Document', 'FDP_Prime_Authorized_Official_Code', 'KC', UUID(), 1, 'CONFG', 0, 'FDP Prime Authorized Official Code', 'A')
/

INSERT INTO KRCR_PARM_T( NMSPC_CD, CMPNT_CD, PARM_NM, APPL_ID, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD ) 
VALUES( 'KC-SUBAWARD', 'Document', 'FDP_Prime_Financial_Contact_Code', 'KC', UUID(), 1, 'CONFG', 0, 'FDP Prime Financial Contact Code', 'A' )
/

INSERT INTO KRCR_PARM_T( NMSPC_CD, CMPNT_CD, PARM_NM, APPL_ID, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD ) 
VALUES( 'KC-SUBAWARD', 'Document', 'FDP_Sub_Administrative_Contact_Code', 'KC', UUID(), 1, 'CONFG', 0, 'FDP Sub Administrative Contact Code', 'A' )
/

INSERT INTO KRCR_PARM_T( NMSPC_CD, CMPNT_CD, PARM_NM, APPL_ID, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD ) 
VALUES( 'KC-SUBAWARD', 'Document', 'FDP_Sub_Authorized_Official_Code', 'KC', UUID(), 1, 'CONFG', 0, 'FDP Sub Authorized Official Code', 'A' )
/

INSERT INTO KRCR_PARM_T( NMSPC_CD, CMPNT_CD, PARM_NM, APPL_ID, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD )
VALUES( 'KC-SUBAWARD', 'Document', 'FDP_Sub_Financial_Contact_Code', 'KC', UUID(), 1, 'CONFG', 0, 'FDP Sub Financial Contact Code', 'A' )
/

INSERT INTO KRCR_PARM_T( NMSPC_CD, CMPNT_CD, PARM_NM, APPL_ID, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD ) 
VALUES( 'KC-SUBAWARD', 'Document', 'Subaward_FDP_Attachment_3B_Form_ID', 'KC', UUID(), 1, 'CONFG', 1, 'Subaward FDP Attachment 3B Form ID', 'A' )
/

INSERT INTO KRCR_PARM_T( NMSPC_CD, CMPNT_CD, PARM_NM, APPL_ID, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD) 
VALUES( 'KC-SUBAWARD', 'Document', 'Subaward_FDP_Attachment_4_Form_ID', 'KC', UUID(), 1, 'CONFG', 1, 'Subaward FDP Attachment 4 Form ID', 'A' )
/

INSERT INTO KRCR_PARM_T( NMSPC_CD, CMPNT_CD, PARM_NM, APPL_ID, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD ) 
VALUES( 'KC-SUBAWARD', 'Document', 'Subaward_FDP_Attachment_5_Form_ID', 'KC', UUID(), 1, 'CONFG', 1, 'Subaward FDP Attachment 5 Form ID', 'A' )
/

INSERT INTO KRCR_PARM_T( NMSPC_CD, CMPNT_CD, PARM_NM, APPL_ID, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD ) 
VALUES( 'KC-SUBAWARD', 'Document', 'Subaward_Print_Attachment_Type_Inclusion', 'KC', UUID(), 1, 'CONFG', 1, 'Subaward Print Attachment Type Inclusion', 'A' )
/

UPDATE KRCR_PARM_T SET VAL = 35 WHERE PARM_NM = 'FDP_Prime_Administrative_Contact_Code'
/

UPDATE KRCR_PARM_T SET VAL = 37 WHERE PARM_NM = 'FDP_Prime_Authorized_Official_Code'
/

UPDATE KRCR_PARM_T SET VAL = 36 WHERE PARM_NM = 'FDP_Prime_Financial_Contact_Code'
/

UPDATE KRCR_PARM_T SET VAL = 31 WHERE PARM_NM = 'FDP_Sub_Administrative_Contact_Code'
/

UPDATE KRCR_PARM_T SET VAL = 34 WHERE PARM_NM = 'FDP_Sub_Authorized_Official_Code'
/

UPDATE KRCR_PARM_T SET VAL = 33 WHERE PARM_NM = 'FDP_Sub_Financial_Contact_Code'
/

UPDATE KRCR_PARM_T SET VAL = 'FDP_ATT_3B' WHERE PARM_NM = 'Subaward_FDP_Attachment_3B_Form_ID'
/

UPDATE KRCR_PARM_T SET VAL = 'FDP_ATT_4' WHERE PARM_NM = 'Subaward_FDP_Attachment_4_Form_ID'
/

UPDATE KRCR_PARM_T SET VAL = 0 WHERE PARM_NM = 'Subaward_FDP_Attachment_5_Form_ID'
/

UPDATE KRCR_PARM_T SET VAL = '1,2,3,5,6,7' WHERE PARM_NM = 'Subaward_Print_Attachment_Type_Inclusion'
/

DELIMITER ;
