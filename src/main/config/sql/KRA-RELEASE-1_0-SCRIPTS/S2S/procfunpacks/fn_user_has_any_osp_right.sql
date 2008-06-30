/**********************************************************************
This function determines whether the specified user has OSP right.
Return values:
	1 = right_exists
	0 = right does not exist
**********************************************************************/

CREATE or REPLACE function FN_USER_HAS_ANY_OSP_RIGHT
   ( AW_USER_ID IN OSP$USER_ROLES.user_id%TYPE)
	RETURN smallint

is 
	ret_code 					number := NULL;
	right_exists 				number := 1; 
	right_does_not_exist 	number := 0; 
	row_count					number;

begin

  SELECT count(*) 
	 INTO row_count
    FROM OSP$USER_ROLES UR,   
         OSP$ROLE_RIGHTS RR,
         OSP$RIGHTS R
   WHERE UPPER(UR.USER_ID) = UPPER(AW_USER_ID)
	  AND RR.ROLE_ID = UR.ROLE_ID
	  AND RR.RIGHT_ID = R.RIGHT_ID 
	  AND	R.RIGHT_TYPE = 'O';
	
	IF (row_count > 0) THEN
		ret_code := right_exists;
	ELSE
		ret_code := right_does_not_exist;
	END IF;

	RETURN (ret_code);
end;

/


