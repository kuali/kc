CREATE OR REPLACE PROCEDURE GET_AUTHORIZED_SIGNER (
     AS_PROPOSAL_NUMBER IN OSP$BUDGET_PERIODS.PROPOSAL_NUMBER%TYPE,
    CUR_GENERIC IN OUT RESULT_SETS.CUR_GENERIC )

IS
   li_count  number;
   ls_signer varchar2(50);
BEGIN


--   SELECT  count(*)
--   INTO    li_count
--   FROM   OSP$PROPOSAL_ADMIN_DETAILS
--   WHERE   DEV_PROPOSAL_NUMBER = as_proposal_number;
--   if li_count < 1 then

   -- proposal has not been submitted

     open cur_generic for

     select DECODE(LENGTH(RTRIM(R.FIRST_NAME )), 0, ' ', RTRIM(R.FIRST_NAME))  FIRST_NAME,
      		DECODE(LENGTH(RTRIM(R.MIDDLE_NAME)), 0, ' ', RTRIM(R.MIDDLE_NAME))  MIDDLE_NAME,
            r.LAST_NAME,
			   to_char(r.rolodex_id) as PERSON_ID,
				r.EMAIL_ADDRESS,
			   r.phone_number as OFFICE_PHONE,
            R.TITLE AS PRIMARY_TITLE,
            R.ADDRESS_LINE_1,
            R.CITY,
            R.COUNTRY_CODE,
            R.Fax_number,
            R.POSTAL_CODE,
            R.State,
            O.ORGANIZATION_NAME AS ORGANIZATION,
            O.ORGANIZATION_NAME AS DEPARTMENT
		from	osp$eps_proposal p, osp$rolodex r, osp$organization o
		where p.proposal_number= as_proposal_number
		and 	o.organization_id = p.organization_id
		and 	o.contact_address_id = r.rolodex_id;

--   else
-- 
-- 	--proposal has been submitted
-- 
--    SELECT  SIGNED_BY
--    INTO    ls_signer
--    FROM   OSP$PROPOSAL_ADMIN_DETAILS
--    WHERE   DEV_PROPOSAL_NUMBER = as_proposal_number;
-- 
--    li_count := 0;
--    select count(*)
--    into 	li_count
--    from	osp$person p, osp$user u
--    where  p.person_id = u.person_id
--    and    upper(ls_signer) = upper(u.user_id);
-- 
--    if li_count > 0 then
-- 
-- 	   -- person id is in the osp$user table (the normal case)
-- 
--  		open cur_generic for
--    	 SELECT DECODE(LENGTH(RTRIM(P.FIRST_NAME )), 0, ' ', RTRIM(P.FIRST_NAME))  FIRST_NAME,
--       	DECODE(LENGTH(RTRIM(P.MIDDLE_NAME)), 0, ' ', RTRIM(P.MIDDLE_NAME))  MIDDLE_NAME,
--      		DECODE(LENGTH(RTRIM(P.LAST_NAME)), 0, ' ', RTRIM(P.LAST_NAME)) LAST_NAME,
--          RTRIM(P.FULL_NAME) FULL_NAME,
--          P.PERSON_ID,P.EMAIL_ADDRESS,P.OFFICE_PHONE,P.PRIMARY_TITLE,P.OFFICE_LOCATION,
--          p.fax_number , P.POSTAL_CODE,
--          p.address_line_1, p.city, p.country_code,p.state
--     	FROM OSP$PERSON P, OSP$USER U
--     	WHERE P.PERSON_ID = U.PERSON_ID
--     	AND   UPPER(U.USER_ID)  = UPPER(ls_signer);
-- 
--    else
-- 
-- 		select count(*)
-- 		into   li_count
-- 		from   osp$person p, osp$user u
--       where  upper(u.user_id) = upper(ls_signer)
--       and    rtrim(ltrim(upper(p.user_name))) = upper(u.user_id);
-- 
-- 		if li_count > 0 then
-- 			open cur_generic for
--    	 	 	SELECT DECODE(LENGTH(RTRIM(P.FIRST_NAME )), 0, ' ', RTRIM(P.FIRST_NAME))  FIRST_NAME,
--       			DECODE(LENGTH(RTRIM(P.MIDDLE_NAME)), 0, ' ', RTRIM(P.MIDDLE_NAME))  MIDDLE_NAME,
--      				DECODE(LENGTH(RTRIM(P.LAST_NAME)), 0, ' ', RTRIM(P.LAST_NAME)) LAST_NAME,
--          		RTRIM(P.FULL_NAME) FULL_NAME,
--          		P.PERSON_ID,P.EMAIL_ADDRESS,P.OFFICE_PHONE,P.PRIMARY_TITLE,P.OFFICE_LOCATION,
--          		p.fax_number, p.postal_code,
--                p.address_line_1, p.city, p.country_code,p.state
--     			FROM OSP$PERSON P, OSP$USER U
--     			WHERE rtrim(ltrim(upper(P.USER_NAME)) ) = upper(U.user_id)
--     			AND   UPPER(u.USER_ID)  = UPPER(ls_signer);
-- 
-- 
-- 		else
-- 				open cur_generic for
--      				SELECT 'Unknown'  as FIRST_NAME,
--          		  	'Unknown'  as MIDDLE_NAME,
--          		  	'Unknown' as LAST_NAME,
--          		  	'Unknown' as FULL_NAME,
--         		   	'Unknown' as PERSON_ID,
--         		   	'Unknown' as EMAIL_ADDRESS,
--         		   	'Unknown' as OFFICE_PHONE,
--         		   	'Unknown' as PRIMARY_TITLE,
--         		   	'Unknown' as OFFICE_LOCATION,
-- 			        	'Unknown' as ADDRESS_1  , 		-- added mar 20,2006
--  			  			'Unknown' as CITY ,  			-- added mar 20,2006
--  			  			'USA' as COUNTRY_CODE   ,	-- added mar 20,2006
--                    'MA' as STATE
--     			   FROM DUAL;
-- 
-- 		end if;
-- 
--  	end if;
-- 
--  end if;


end;
/

