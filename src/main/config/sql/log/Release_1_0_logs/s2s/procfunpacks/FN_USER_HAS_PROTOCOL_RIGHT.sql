CREATE OR REPLACE function FN_USER_HAS_PROTOCOL_RIGHT
   ( AW_USER_ID IN OSP$PROTOCOL_USER_ROLES.user_id%TYPE,
	  AW_PROPOSAL_NUMBER IN OSP$PROTOCOL_USER_ROLES.PROTOCOL_NUMBER%TYPE,
	  AW_RIGHT_ID IN OSP$ROLE_RIGHTS.right_id%TYPE )
	RETURN smallint

is
	row_count smallint;
	ret_code smallint := NULL;
	right_exists smallint := 1;
	right_does_not_exist smallint := 0;

begin

  SELECT count(*)
	 INTO row_count
    FROM OSP$PROTOCOL_USER_ROLES PUR,
         OSP$ROLE_RIGHTS RR,
         OSP$ROLE R
   WHERE UPPER(PUR.USER_ID) = UPPER(AW_USER_ID)
      AND PUR.SEQUENCE_NUMBER IN 
	  	  (SELECT MAX(SEQUENCE_NUMBER) 
		   FROM OSP$PROTOCOL_USER_ROLES 
		   WHERE PROTOCOL_NUMBER = AW_PROPOSAL_NUMBER)
	  AND PUR.PROTOCOL_NUMBER = AW_PROPOSAL_NUMBER
	  AND R.STATUS_FLAG = 'A'
	  AND RR.RIGHT_ID = AW_RIGHT_ID
	  AND RR.ROLE_ID = PUR.ROLE_ID
	  AND R.ROLE_ID = RR.ROLE_ID ;

	IF row_count > 0 THEN
		ret_code := right_exists;
	ELSE
		ret_code := right_does_not_exist;
	END IF;

	RETURN (ret_code);
end;
/

