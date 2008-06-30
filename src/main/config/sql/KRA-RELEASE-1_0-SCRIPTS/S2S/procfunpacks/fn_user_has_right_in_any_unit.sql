/**********************************************************************
This function determines whether the specified user has a specific 
right anywhere in the unit hierarchy.
Return values:
	1 = right_exists
	0 = right does not exist
**********************************************************************/

CREATE or REPLACE function fn_user_has_right_in_any_unit
   ( AW_USER_ID IN OSP$USER_ROLES.user_id%TYPE,
	  AW_RIGHT_ID IN osp$role_rights.right_id%TYPE)
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
         OSP$ROLE_RIGHTS RR
   WHERE UPPER(UR.USER_ID) = UPPER(AW_USER_ID)
	  AND RR.ROLE_ID = UR.ROLE_ID
     AND RR.RIGHT_ID = AW_RIGHT_ID;
	
	IF (row_count > 0) THEN
		ret_code := right_exists;
	ELSE
		ret_code := right_does_not_exist;
	END IF;

	RETURN (ret_code);
end;

/




