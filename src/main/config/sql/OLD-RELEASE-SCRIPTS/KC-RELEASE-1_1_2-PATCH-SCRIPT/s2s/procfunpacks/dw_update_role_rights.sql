/********************************************************************
This procedure updates OSP$.ROLE_RIGHTS.
********************************************************************/

CREATE or REPLACE PROCEDURE dw_update_role_rights
    ( AV_RIGHT_ID IN osp$ROLE_RIGHTS.RIGHT_ID%TYPE := NULL,
      AV_ROLE_ID IN osp$ROLE_RIGHTS.ROLE_ID%TYPE := NULL,
      AV_DESCEND_FLAG IN osp$ROLE_RIGHTS.DESCEND_FLAG%TYPE := NULL,
      AV_UPDATE_TIMESTAMP IN osp$ROLE_RIGHTS.UPDATE_TIMESTAMP%TYPE := NULL,
      AV_UPDATE_USER IN osp$ROLE_RIGHTS.UPDATE_USER%TYPE := NULL,
      AW_RIGHT_ID IN osp$ROLE_RIGHTS.RIGHT_ID%TYPE := NULL,   
      AW_ROLE_ID IN osp$ROLE_RIGHTS.ROLE_ID%TYPE := NULL,   
      AW_UPDATE_TIMESTAMP IN osp$ROLE_RIGHTS.UPDATE_TIMESTAMP%TYPE := NULL,
		AC_TYPE IN CHAR )

IS 
	data_changed		EXCEPTION;
BEGIN

   IF AC_TYPE = 'I' THEN

  		INSERT INTO osp$ROLE_RIGHTS  
         	 ( RIGHT_ID,   
         	   ROLE_ID,   
           		DESCEND_FLAG,   
           		UPDATE_TIMESTAMP,   
           		UPDATE_USER )  
  		VALUES ( AV_RIGHT_ID,
					AV_ROLE_ID,   
           		AV_DESCEND_FLAG,   
           		AV_UPDATE_TIMESTAMP,   
           		AV_UPDATE_USER ) ;


	ELSIF AC_TYPE = 'U' THEN

		UPDATE osp$ROLE_RIGHTS  
     		SET DESCEND_FLAG = AV_DESCEND_FLAG,   
         	 UPDATE_TIMESTAMP = AV_UPDATE_TIMESTAMP,   
         	 UPDATE_USER = AV_UPDATE_USER
   	 WHERE RIGHT_ID = AW_RIGHT_ID
   	   AND ROLE_ID = AW_ROLE_ID
	  		AND UPDATE_TIMESTAMP = AW_UPDATE_TIMESTAMP ;

		--	IF the update fails with a not found, then data has changed between retrieve
		--	and update. Raise an application error now.

		IF SQL%NOTFOUND THEN
			RAISE data_changed;
		END IF;


	ELSIF AC_TYPE = 'D' THEN

  		DELETE FROM osp$ROLE_RIGHTS  
   	 WHERE ROLE_ID = AW_ROLE_ID
			AND UPDATE_TIMESTAMP = AW_UPDATE_TIMESTAMP ;

	END IF;

EXCEPTION
	WHEN data_changed THEN
		raise_application_error(-20100, 'Data changed between retrieve and update');

END;

/




