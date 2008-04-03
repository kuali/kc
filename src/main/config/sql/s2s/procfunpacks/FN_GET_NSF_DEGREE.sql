CREATE OR REPLACE FUNCTION FN_GET_NSF_DEGREE (as_degree_code in OSP$DEGREE_TYPE.DEGREE_CODE%TYPE)
RETURN VARCHAR2 IS
	ls_degree   VARCHAR2(150);
   ls_description	osp$degree_type.description%type;

BEGIN
		SELECT description
		INTO   ls_description
		FROM 	 OSP$DEGREE_TYPE 
		WHERE  DEGREE_CODE = as_degree_code;

	   ls_degree := as_degree_code || ': ' || ltrim(rtrim(ls_description));
		


RETURN ls_degree;

EXCEPTION
		WHEN NO_DATA_FOUND THEN
			ls_degree := '';
			return ls_degree;
END;
/

