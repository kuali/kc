/**********************************************************************
This function determines whether the specified role exists for a given
user_id.
Note: at present, this function refers to neither the unit_number nor
the descend flag, nor does it consider the unit heirarchy.

Return values:
	1 = role exists
	0 = role does not exist
**********************************************************************/

create or replace function FN_USER_HAS_ROLE
   ( AW_USER_ID IN osp$user_roles.user_id%TYPE,
	  AW_ROLE_ID IN osp$user_roles.role_id%TYPE )
	RETURN smallint

IS 
	v_row_count smallint;
	v_ret_code smallint := NULL;
	v_role_exists smallint := 1; 
	v_role_does_not_exist smallint := 0;

BEGIN
   -- Determine whether the role exists for the user.
   SELECT count(*)
	  INTO v_row_count
     FROM OSP$USER_ROLES
    WHERE UPPER(USER_ID) = UPPER(AW_USER_ID)
		AND ROLE_ID = AW_ROLE_ID ;

	IF v_row_count > 0 THEN
		v_ret_code := v_role_exists ;
	ELSE
		v_ret_code := v_role_does_not_exist ;
	END IF;

	RETURN (v_ret_code);

END;

/

