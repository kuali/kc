CREATE SEQUENCE SEQ_CUSTOM_ATTRIBUTE_TMP
/
INSERT INTO CUSTOM_ATTRIBUTE (DATA_LENGTH,DATA_TYPE_CODE,GROUP_NAME,ID,LABEL,NAME,OBJ_ID,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR)
  VALUES (40,'1','asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf',SEQ_CUSTOM_ATTRIBUTE_TMP.NEXTVAL,'Billing Element','billingElement',SYS_GUID(),TO_DATE( '20080221000000', 'YYYYMMDDHH24MISS' ),'admin',1)
/
INSERT INTO CUSTOM_ATTRIBUTE (DATA_LENGTH,DATA_TYPE_CODE,GROUP_NAME,ID,LABEL,NAME,OBJ_ID,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR)
  VALUES (30,'1','asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf',SEQ_CUSTOM_ATTRIBUTE_TMP.NEXTVAL,'Cost Sharing Budget','costSharingBudget',SYS_GUID(),TO_DATE( '20080221000000', 'YYYYMMDDHH24MISS' ),'admin',1)
/
INSERT INTO CUSTOM_ATTRIBUTE (DATA_LENGTH,DATA_TYPE_CODE,GROUP_NAME,ID,LABEL,NAME,OBJ_ID,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR)
  VALUES (6,'2','asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf',SEQ_CUSTOM_ATTRIBUTE_TMP.NEXTVAL,'# of Trainees','numberOfTrainees',SYS_GUID(),TO_DATE( '20080221000000', 'YYYYMMDDHH24MISS' ),'admin',1)
/
INSERT INTO CUSTOM_ATTRIBUTE (DATA_LENGTH,DATA_TYPE_CODE,GROUP_NAME,ID,LABEL,NAME,OBJ_ID,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR)
  VALUES (6,'2','Personnel Items for Review',SEQ_CUSTOM_ATTRIBUTE_TMP.NEXTVAL,'Graduate Student Count','graduateStudentCount',SYS_GUID(),TO_DATE( '20080221000000', 'YYYYMMDDHH24MISS' ),'admin',1)
/
INSERT INTO CUSTOM_ATTRIBUTE (DATA_LENGTH,DATA_TYPE_CODE,GROUP_NAME,ID,LABEL,LOOKUP_CLASS,LOOKUP_RETURN,NAME,OBJ_ID,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR)
  VALUES (30,'1','Personnel Items for Review',SEQ_CUSTOM_ATTRIBUTE_TMP.NEXTVAL,'Tenured','org.kuali.kra.bo.KcPerson','userName','tenured',SYS_GUID(),TO_DATE( '20080221000000', 'YYYYMMDDHH24MISS' ),'admin',1)
/
INSERT INTO CUSTOM_ATTRIBUTE (DATA_LENGTH,DATA_TYPE_CODE,GROUP_NAME,ID,LABEL,NAME,OBJ_ID,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR)
  VALUES (30,'1','Project Details',SEQ_CUSTOM_ATTRIBUTE_TMP.NEXTVAL,'Export Controls','exportControls',SYS_GUID(),TO_DATE( '20080221000000', 'YYYYMMDDHH24MISS' ),'admin',1)
/
INSERT INTO CUSTOM_ATTRIBUTE (DATA_LENGTH,DATA_TYPE_CODE,GROUP_NAME,ID,LABEL,LOOKUP_CLASS,LOOKUP_RETURN,NAME,OBJ_ID,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR)
  VALUES (30,'1','Project Details',SEQ_CUSTOM_ATTRIBUTE_TMP.NEXTVAL,'Inventions','org.kuali.kra.bo.KcPerson','userName','inventions',SYS_GUID(),TO_DATE( '20080221000000', 'YYYYMMDDHH24MISS' ),'admin',1)
/
INSERT INTO CUSTOM_ATTRIBUTE (DATA_LENGTH,DATA_TYPE_CODE,GROUP_NAME,ID,LABEL,NAME,OBJ_ID,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR)
  VALUES (10,'3','asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf',SEQ_CUSTOM_ATTRIBUTE_TMP.NEXTVAL,'Local Review Date','localReviewDate',SYS_GUID(),TO_DATE( '20080221000000', 'YYYYMMDDHH24MISS' ),'admin',1)
/
INSERT INTO CUSTOM_ATTRIBUTE (DATA_LENGTH,DATA_TYPE_CODE,GROUP_NAME,ID,LABEL,NAME,OBJ_ID,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR)
  VALUES (80,'1','Course Related',SEQ_CUSTOM_ATTRIBUTE_TMP.NEXTVAL,'Course Name','courseName',SYS_GUID(),TO_DATE( '20090317000000', 'YYYYMMDDHH24MISS' ),'admin',1)
/
INSERT INTO CUSTOM_ATTRIBUTE (DATA_LENGTH,DATA_TYPE_CODE,GROUP_NAME,ID,LABEL,LOOKUP_CLASS,LOOKUP_RETURN,NAME,OBJ_ID,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR)
  VALUES (80,'1','Course Related',SEQ_CUSTOM_ATTRIBUTE_TMP.NEXTVAL,'Instructor Name','org.kuali.kra.bo.KcPerson','fullName','instructorName',SYS_GUID(),TO_DATE( '20090317000000', 'YYYYMMDDHH24MISS' ),'admin',1)
/
DROP SEQUENCE SEQ_CUSTOM_ATTRIBUTE_TMP
/