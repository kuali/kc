DELIMITER /
INSERT INTO VALID_SP_REV_APPROVAL (VALID_SP_REV_APPROVAL_ID,SPECIAL_REVIEW_CODE,APPROVAL_TYPE_CODE,PROTOCOL_NUMBER_FLAG,APPLICATION_DATE_FLAG,APPROVAL_DATE_FLAG,EXEMPT_NUMBER_FLAG,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR)
    VALUES (3,(SELECT SPECIAL_REVIEW_CODE FROM SPECIAL_REVIEW WHERE DESCRIPTION = 'Animal Usage'),(SELECT APPROVAL_TYPE_CODE FROM SP_REV_APPROVAL_TYPE WHERE DESCRIPTION = 'Approved'),'Y','Y','Y','N','admin',NOW(),UUID(),1)
/
INSERT INTO VALID_SP_REV_APPROVAL (VALID_SP_REV_APPROVAL_ID,SPECIAL_REVIEW_CODE,APPROVAL_TYPE_CODE,PROTOCOL_NUMBER_FLAG,APPLICATION_DATE_FLAG,APPROVAL_DATE_FLAG,EXEMPT_NUMBER_FLAG,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR)
    VALUES (4,(SELECT SPECIAL_REVIEW_CODE FROM SPECIAL_REVIEW WHERE DESCRIPTION = 'Animal Usage'),(SELECT APPROVAL_TYPE_CODE FROM SP_REV_APPROVAL_TYPE WHERE DESCRIPTION = 'Exempt'),'Y','N','N','Y','admin',NOW(),UUID(),1)
/
UPDATE VALID_SP_REV_APPROVAL SET PROTOCOL_NUMBER_FLAG = 'Y'  WHERE SPECIAL_REVIEW_CODE = (SELECT SPECIAL_REVIEW_CODE FROM SPECIAL_REVIEW WHERE DESCRIPTION = 'Human Subjects') AND APPROVAL_TYPE_CODE = (SELECT APPROVAL_TYPE_CODE FROM SP_REV_APPROVAL_TYPE WHERE DESCRIPTION = 'Exempt')
/
DELIMITER ;
