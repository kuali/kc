/********************************************************************
This procedure updates OSP$USER.
--This should be called for UPDATE, INSERT or DELETE
--the argument AC_TYPE species the sql operation
--					U for UPDATE
--					I for INSERT
--					D for DELETE
-- The WHERE clause in the UPDATE and DELETE statements use
--	user_id and update_timestamp
********************************************************************/

CREATE or REPLACE PROCEDURE dw_update_user
    (	AV_USER_ID IN osp$user.user_id%TYPE := NULL,
		AV_USER_NAME IN osp$user.user_name%TYPE := NULL,
		AV_NON_MIT_PERSON_FLAG IN osp$user.non_mit_person_flag%TYPE := NULL,   
      AV_PERSON_ID IN osp$user.person_id%TYPE := NULL,   
      AV_USER_TYPE IN osp$user.user_type%TYPE := NULL,   
      AV_UNIT_NUMBER IN osp$user.unit_number%TYPE := NULL,   
      AV_STATUS IN osp$user.status%TYPE := NULL,   
      AV_UPDATE_TIMESTAMP IN osp$user.update_timestamp%TYPE := NULL,
      AV_UPDATE_USER IN osp$user.update_user%TYPE := NULL,
		AW_USER_ID IN osp$user.user_id%TYPE := NULL,
		AW_UPDATE_TIMESTAMP IN osp$user.update_timestamp%TYPE := NULL,
		AC_TYPE IN CHAR )

IS 

BEGIN

   IF AC_TYPE = 'I' THEN

      INSERT INTO OSP$USER  
         	 ( USER_ID,   
           		USER_NAME,   
           		NON_MIT_PERSON_FLAG,   
           		PERSON_ID,   
           		USER_TYPE,   
           		UNIT_NUMBER,   
           		STATUS,   
           		UPDATE_TIMESTAMP,   
           		UPDATE_USER )  
      VALUES ( AV_USER_ID,   
           		AV_USER_NAME,   
           		AV_NON_MIT_PERSON_FLAG,   
           		AV_PERSON_ID,   
          		AV_USER_TYPE,   
           		AV_UNIT_NUMBER,   
           		AV_STATUS,   
           		AV_UPDATE_TIMESTAMP,   
           		AV_UPDATE_USER ) ;

	  ELSIF AC_TYPE = 'U' THEN

  			UPDATE OSP$USER  
     			SET USER_NAME = AV_USER_NAME,   
         		 NON_MIT_PERSON_FLAG = AV_NON_MIT_PERSON_FLAG,   
        			 PERSON_ID = AV_PERSON_ID,   
        			 USER_TYPE = AV_USER_TYPE,   
        			 UNIT_NUMBER = AV_UNIT_NUMBER,   
        			 STATUS = AV_STATUS,   
        			 UPDATE_TIMESTAMP = AV_UPDATE_TIMESTAMP,   
        			 UPDATE_USER = AV_UPDATE_USER  
   		 WHERE USER_ID = AW_USER_ID
	  			AND UPDATE_TIMESTAMP = AW_UPDATE_TIMESTAMP ;

	  ELSIF AC_TYPE = 'D' THEN

  			DELETE FROM OSP$USER  
   		 WHERE USER_ID = AW_USER_ID
	  			AND UPDATE_TIMESTAMP = AW_UPDATE_TIMESTAMP ;

	END IF;

END;

/


