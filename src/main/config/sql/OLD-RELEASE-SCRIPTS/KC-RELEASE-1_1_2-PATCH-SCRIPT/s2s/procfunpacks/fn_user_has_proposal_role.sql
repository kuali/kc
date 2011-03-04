-- This function determines whether the user has the specified role for the proposal
-- 	1 = right exists
-- 	0 = right does not exist
-- Ramesh B Nair
----------------------------------------------------------------------------------

create or replace function FN_USER_HAS_PROPOSAL_ROLE
   (AW_USER_ID IN osp$eps_prop_user_roles.user_id%TYPE,
	 AW_PROP_NUM IN osp$eps_prop_user_roles.proposal_number%TYPE,
	 AW_ROLE_ID IN osp$eps_prop_user_roles.role_id%TYPE)
	RETURN smallint

IS 
	row_count smallint;
	ret_code smallint := NULL;
	right_exists smallint := 1; 
	right_does_not_exist smallint := 0;
BEGIN

   SELECT count(*)  
	  INTO row_count
     FROM osp$eps_prop_user_roles
    WHERE role_id = AW_ROLE_ID 
		AND UPPER(user_id) = UPPER(AW_USER_ID) 
		AND proposal_number = AW_PROP_NUM;

	IF row_count > 0 THEN
		ret_code := right_exists ;
	ELSE
		ret_code := right_does_not_exist ;
	END IF ;

	RETURN (ret_code);
END;

/


