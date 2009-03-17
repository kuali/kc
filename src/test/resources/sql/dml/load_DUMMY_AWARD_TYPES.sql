-- This is just a dummy loader so our unit tests can execute

INSERT INTO AWARD_TYPE ( AWARD_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 1, 'Grant', sysdate, user ); 

INSERT INTO FP_DOC_TYPE_T (FDOC_TYP_CD,FDOC_NM,FDOC_TYP_ACTIVE_CD) 
 				VALUES ('ADTY','AwardType','Y');
commit;