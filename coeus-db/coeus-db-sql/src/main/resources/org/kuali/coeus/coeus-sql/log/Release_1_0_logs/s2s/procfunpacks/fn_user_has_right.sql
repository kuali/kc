/**********************************************************************
This function determines whether the specified right exists for a given
user_id in a given unit_number.

Outline of logic:
1. Determine whether the right exists for the user in the given unit
number. If so, then the user has the right, and no further processing is
required.
2. Traverse the unit hierarchy tree, starting from the given unit number's
superior and moving towards the root.  For each unit along the way,
determine whether the right exists for the user.  If so, then stop the
traversal and check the descend flag in both the user_roles and role_rights
tables.  If the value is 'Y', then the user has the right, otherwise
the user does not have the right.

Return values:
	1 = right_exists
	0 = right does not exist
**********************************************************************/

create or replace function FN_USER_HAS_RIGHT
   ( AW_USER_ID IN osp$user_roles.user_id%TYPE,
	  AW_UNIT_NUMBER IN osp$user_roles.unit_number%TYPE,
	  AW_RIGHT_ID IN osp$role_rights.right_id%TYPE )
	RETURN smallint

IS 
	row_count smallint;
	ret_code smallint := NULL;
	right_exists smallint := 1; 
	right_does_not_exist smallint := 0;
	r_status_flag osp$role.status_flag%TYPE;
	rr_descend_flag osp$role_rights.descend_flag%TYPE;
	ur_descend_flag osp$user_roles.descend_flag%TYPE;
	ls_unit_number osp$user_roles.unit_number%TYPE;

BEGIN

	--------------------------------------------------------------------
   -- Determine whether the right exists for the user within the given
	-- unit number.  The role must be active (status_flag = 'A').
   SELECT count(*)  
	  INTO row_count
     FROM OSP$USER_ROLES UR,   
          OSP$ROLE_RIGHTS RR,
			 OSP$ROLE R
    WHERE UPPER(UR.USER_ID) = UPPER(AW_USER_ID)
		AND R.STATUS_FLAG = 'A'
	   AND UR.UNIT_NUMBER =	AW_UNIT_NUMBER
	   AND RR.RIGHT_ID = AW_RIGHT_ID
	   AND RR.ROLE_ID = UR.ROLE_ID
	   AND R.ROLE_ID = RR.ROLE_ID ;

	IF row_count > 0 THEN
		ret_code := right_exists ;
		GOTO end_of_procedure ;
	END IF ;

	---------------------------------------------------------------------
	-- Determine whether the right exists for the user within the first
	-- parent unit of the given unit number, where the descend flag equals
	-- 'Y' in both the user_roles and the role_rights table.

	DECLARE
		-- Traverse the hierarchy starting from the given unit's parent
		-- and going in the direction of the root unit.		
		CURSOR CURS_1 IS 
				 SELECT UNIT_NUMBER
			  		FROM OSP$UNIT_HIERARCHY
			START WITH UNIT_NUMBER = 
					(SELECT PARENT_UNIT_NUMBER
				   	FROM OSP$UNIT_HIERARCHY
				  	  WHERE UNIT_NUMBER = AW_UNIT_NUMBER )
			CONNECT BY UNIT_NUMBER = PRIOR PARENT_UNIT_NUMBER ;

	BEGIN
		OPEN CURS_1;

		LOOP
			FETCH CURS_1 INTO ls_unit_number ;
			IF CURS_1%NOTFOUND THEN
				ret_code := right_does_not_exist ;
				EXIT ;
			END IF ;			
				
			-- Determine whether the role exists for this unit number and has
			-- the descend flag turned on.
	  		DECLARE
				CURSOR CURS_2 IS 
					SELECT	RR.DESCEND_FLAG,
								UR.DESCEND_FLAG,
								R.STATUS_FLAG
    	  			FROM 		OSP$USER_ROLES UR,
								OSP$ROLE_RIGHTS RR,
								OSP$ROLE R
   	 			WHERE		UPPER(UR.USER_ID) = UPPER(AW_USER_ID)
	  				AND		UR.UNIT_NUMBER = ls_unit_number
	  				AND		RR.RIGHT_ID = AW_RIGHT_ID
	  				AND		RR.ROLE_ID = UR.ROLE_ID
	  				AND		R.ROLE_ID = RR.ROLE_ID ;

			BEGIN
				OPEN CURS_2;

			LOOP

				FETCH CURS_2 INTO rr_descend_flag,
										ur_descend_flag,
										r_status_flag ;

				Exit WHEN CURS_2%NOTFOUND ;

				IF rr_descend_flag = 'Y' AND ur_descend_flag = 'Y'
						AND r_status_flag = 'A' THEN
					ret_code := right_exists ;
					EXIT ;
				ELSE
					ret_code := right_does_not_exist ;
				END IF;
					
			END LOOP;
			CLOSE CURS_2;
			
			END;
		
		IF ret_code = right_exists THEN
			Exit;
		END IF;

		END LOOP ;

		CLOSE CURS_1 ;
			
	END;

	<<end_of_procedure>>
	RETURN (ret_code);

END;

/


