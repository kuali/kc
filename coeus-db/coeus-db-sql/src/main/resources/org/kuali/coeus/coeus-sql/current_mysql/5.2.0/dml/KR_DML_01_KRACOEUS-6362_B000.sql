
DELIMITER /

INSERT INTO KRMS_TYP_S VALUES(NULL)
/

insert into KRMS_TYP_T (TYP_ID, NM, NMSPC_CD, SRVC_NM, ACTV, VER_NBR)
values (CONCAT('KC', (SELECT (MAX(ID)) FROM KRMS_TYP_S)),'Award Java Function Term Service','KC-AWARD','awardJavaFunctionKrmsTermService','Y',1)
/

DELIMITER ;
