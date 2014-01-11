CREATE OR REPLACE PACKAGE  subcontracting_rpts_pkg as


procedure get_294_amts_and_goals (as_award_number IN osp$award.mit_award_number%type,
							  cur_type IN OUT result_sets.cur_generic);

procedure get_295_amts(as_sponsor_group IN  OSP$SPONSOR_HIERARCHY.LEVEL1%TYPE,
								cur_type IN OUT result_sets.cur_generic) ;

function fn_get_other_sponsors(as_sponsor_group IN OSP$SPONSOR_HIERARCHY.LEVEL1%TYPE)
      	return varchar2 ;

function fn_get_294_sponsor(as_award_number IN osp$award.mit_award_number%type)
		   return varchar2 ;

procedure get_294_prime_contract_num (as_award_number IN osp$award.mit_award_number%type,
								cur_type IN OUT result_sets.cur_generic);

 
procedure get_ceo (as_argument in varchar2, 
			cur_type IN OUT result_sets.cur_generic);

procedure get_official (as_argument in varchar2, 
			cur_type IN OUT result_sets.cur_generic);


function fn_get_admin_activity(as_award_number IN osp$award.mit_award_number%type)
		return varchar2;


procedure get_contractor_info (as_award_number IN osp$award.mit_award_number%type,
						cur_type IN OUT result_sets.cur_generic);

procedure get_company_info (as_argument in varchar2,
					   cur_type IN OUT result_sets.cur_generic);

function fn_get_is_march_rpt(as_argument in varchar2)
		return varchar2;

function fn_get_report_year (as_argument in varchar2)
		return varchar2;

function fn_pop_sub_exp_cat_by_fy (ls_period_start IN varchar2,
											  ls_period_end  IN varchar2)
		return number;

function fn_get_295_hbcu_amt (as_sponsor_group in OSP$SPONSOR_HIERARCHY.LEVEL1%TYPE)
return varchar2 ;	

function fn_get_295_hubz_amt	(as_sponsor_group in OSP$SPONSOR_HIERARCHY.LEVEL1%TYPE)
return varchar2 ;

function fn_get_295_small_bus_amt (as_sponsor_group in OSP$SPONSOR_HIERARCHY.LEVEL1%TYPE)
return varchar2 ;

function fn_get_295_sdb_amt (as_sponsor_group in OSP$SPONSOR_HIERARCHY.LEVEL1%TYPE)
return varchar2 ;

function fn_get_295_vet_dis_amt	(as_sponsor_group in OSP$SPONSOR_HIERARCHY.LEVEL1%TYPE)
		return varchar2 ;

function fn_get_295_veteran_amt	(as_sponsor_group in OSP$SPONSOR_HIERARCHY.LEVEL1%TYPE)
return varchar2 ;
	
function fn_get_295_large_bus_amt	(as_sponsor_group in OSP$SPONSOR_HIERARCHY.LEVEL1%TYPE)
return varchar2 ;

function fn_get_295_tot_small_large_amt	(as_sponsor_group in OSP$SPONSOR_HIERARCHY.LEVEL1%TYPE)
return varchar2 ;

function fn_get_295_wosb_amt	(as_sponsor_group in OSP$SPONSOR_HIERARCHY.LEVEL1%TYPE)
return varchar2 ;

procedure get_295_other_names (as_argument in varchar2,
				cur_type IN OUT result_sets.cur_generic);



end;
/

CREATE OR REPLACE package body subcontracting_rpts_pkg as

				
------------------------------------
--	get_295_amts
-------------------------------------
procedure get_295_amts(as_sponsor_group IN  OSP$SPONSOR_HIERARCHY.LEVEL1%TYPE,
								cur_type IN OUT result_sets.cur_generic) is
hbcu_amt	number;
hub_amt number;
smallamt number;
sdbamt number;
vetamt number;
vetdisamt number;
largeamt number;
womanamt number;
totamt number;
other_sponsors varchar2(400);

begin

-- HANDLE OTHERS sponsor group
other_sponsors := ' ';
if as_sponsor_group = 'OTHER' then
	other_sponsors := fn_get_other_sponsors('OTHER');
end if;

hbcu_amt := fn_get_295_hbcu_amt (as_sponsor_group );	
hub_amt :=fn_get_295_hubz_amt	(as_sponsor_group );
smallamt :=fn_get_295_small_bus_amt (as_sponsor_group );
sdbamt :=fn_get_295_sdb_amt (as_sponsor_group );
vetdisamt :=fn_get_295_vet_dis_amt	(as_sponsor_group );
vetamt :=fn_get_295_veteran_amt	(as_sponsor_group );
largeamt :=fn_get_295_large_bus_amt	(as_sponsor_group );
totamt :=fn_get_295_tot_small_large_amt	(as_sponsor_group );
womanamt :=fn_get_295_wosb_amt	(as_sponsor_group );


open cur_type for
  select other_sponsors as SPONSOR,
   hbcu_amt as HBCU_AMT	,
	hub_amt as HUB_AMT,
	smallamt as SMALL_BUS_AMT,
	sdbamt as DISADVANTAGED_BUS_AMT,
	vetamt as VET_AMT,
	vetdisamt as SDVO_AMT,
	largeamt as LARGE_BUS_AMT,
	womanamt WOMAN_OWNED_AMT,
	totamt  as TOT_AMT
  from dual;

end;

----------------------------
--function fn_get_294_sponsor
--  for 'other' sponsor group
----------------------------

function fn_get_294_sponsor(as_award_number IN osp$award.mit_award_number%type)
		   return varchar2 is

ls_sponsor	OSP$SPONSOR.SPONSOR_NAME%TYPE;

begin

ls_sponsor := ' ';

SELECT S.SPONSOR_NAME
INTO   ls_sponsor
FROM   OSP$SPONSOR S, OSP$AWARD A
WHERE  A.MIT_AWARD_NUMBER = as_award_number
AND    A.SPONSOR_CODE = S.SPONSOR_CODE
AND    A.SEQUENCE_NUMBER IN (SELECT MAX(A1.SEQUENCE_NUMBER) FROM OSP$AWARD A1
								  WHERE A1.MIT_AWARD_NUMBER = A.MIT_AWARD_NUMBER);


return ls_sponsor;
end;


-----------------------
-- fn_get_other_sponsors
-----------------------
function fn_get_other_sponsors(as_sponsor_group IN OSP$SPONSOR_HIERARCHY.LEVEL1%TYPE)
	return varchar2 is

ls_sponsor_list	varchar2(2000);
ls_name			OSP$SPONSOR.SPONSOR_NAME%TYPE;

begin

ls_sponsor_list := ' ';

DECLARE CURSOR c_sponsors IS
	SELECT S.SPONSOR_NAME
	FROM   OSP$SPONSOR S,
			 OSP$SUB_EXP_CAT_BY_FY E,
			 OSP$SPONSOR_HIERARCHY H,
			 OSP$AWARD A
	WHERE	 E.MIT_AWARD_NUMBER = A.MIT_AWARD_NUMBER
	AND    A.SEQUENCE_NUMBER = (SELECT MAX(SEQUENCE_NUMBER)
										 FROM OSP$AWARD
										 WHERE MIT_AWARD_NUMBER = A.MIT_AWARD_NUMBER)
	AND    UPPER(H.HIERARCHY_NAME) = 'ADMINISTERING ACTIVITY'
	AND	 H.SPONSOR_CODE = S.SPONSOR_CODE
	AND	 S.SPONSOR_CODE = A.SPONSOR_CODE
	AND	 UPPER(LTRIM(RTRIM(H.LEVEL1))) = UPPER(LTRIM(RTRIM(as_sponsor_group)))
	AND	 E.MIT_AWARD_NUMBER NOT IN
				(SELECT	E.MIT_AWARD_NUMBER
				 FROM    OSP$SUB_EXP_CAT_BY_FY E,
							OSP$AWARD_HEADER AH
				 WHERE	E.MIT_aWARD_NUMBER = AH.MIT_aWARD_NUMBER
				 AND		AH.SEQUENCE_NUMBER = (SELECT MAX(SEQUENCE_NUMBER)	
														 FROM	OSP$AWARD_HEADER
														 WHERE MIT_AWARD_NUMBER = AH.MIT_AWARD_NUMBER)
				 AND	 AH.PRIME_SPONSOR_CODE IS NOT NULL);

	BEGIN
			OPEN c_sponsors;
			loop
				FETCH c_sponsors INTO ls_name;
				EXIT WHEN c_sponsors%NOTFOUND;
				
				ls_sponsor_list := ls_sponsor_list || ', ' || LTRIM(RTRIM(ls_name));
			end loop;
			close c_sponsors;
	END;

-- get 'other' prime sponsors

DECLARE CURSOR c_prime_sponsors IS
	SELECT S.SPONSOR_NAMe
	FROM   OSP$SPONSOR S,
			 OSP$SUB_EXP_CAT_BY_FY E,
			 OSP$SPONSOR_HIERARCHY H,
			 OSP$AWARD_HEADER AH
	WHERE	 E.MIT_AWARD_NUMBER = AH.MIT_AWARD_NUMBER	AND    AH.SEQUENCE_NUMBER = (SELECT MAX(SEQUENCE_NUMBER)
										 FROM OSP$AWARD_HEADER
										 WHERE MIT_AWARD_NUMBER = AH.MIT_AWARD_NUMBER)
	AND	 AH.PRIME_SPONSOR_CODE IS NOT NULL
	AND    UPPER(H.HIERARCHY_NAME) = 'ADMINISTERING ACTIVITY'
	AND	 H.SPONSOR_CODE = S.SPONSOR_CODE
	AND	 S.SPONSOR_CODE = AH.prime_SPONSOR_CODE
	AND	 UPPER(LTRIM(RTRIM(H.LEVEL1))) = UPPER(LTRIM(RTRIM(as_sponsor_group)));
	
	BEGIN
		OPEN c_PRIME_sponsors;
			loop
				FETCH c_PRIME_sponsors INTO ls_name;
				EXIT WHEN c_prime_sponsors%NOTFOUND;
				
				ls_sponsor_list := ls_sponsor_list || ', ' || LTRIM(RTRIM(ls_name));
			end loop;
		close c_prime_sponsors;

	END;


RETURN ls_sponsor_list;

end;

-------------------
-- get_294_amts_and_goals
-------------------
procedure get_294_amts_and_goals (as_award_number IN osp$award.mit_award_number%type,
							  cur_type IN OUT result_sets.cur_generic) is
							  
	begin
	open cur_type for

		SELECT  	decode(large_business_amt,null,0, large_business_amt) as LARGE_BUS_AMT,
				   decode(small_business_amt,null,0,small_business_amt)  as SMALL_BUS_AMT,
	            decode(large_business_amt,null,0,large_business_amt) + decode(small_business_amt,null,0,small_business_amt) as TOT_AMT,
					decode(woman_owned_amt,null,0,woman_owned_amt) AS WOMAN_OWNED_AMT,
					decode(a8_disadvantage_amt,null,0,a8_disadvantage_amt ) as DISADVANTAGED_BUS_AMT,
					decode(hub_zone_amt,null,0, hub_zone_amt) as HUB_AMT,
					decode(veteran_owned_amt,null,0,veteran_owned_amt) as VET_AMT,
					decode(service_disabled_vet_owned_amt,null,0,service_disabled_vet_owned_amt) as SDVO_AMT,
					decode(historical_black_college_amt,null,0,historical_black_college_amt) as HBCU_AMT,
					decode(large_business_goal,null,0,large_business_goal) as LARGE_BUS_GOAL,
					decode(small_business_goal,null,0,small_business_goal) as SMALL_BUS_goal,
				   decode(large_business_goal,null,0,large_business_goal) +  decode(small_business_goal,null,0,small_business_goal) as TOTAL_GOAL,
					decode(woman_owned_goal,null,0,woman_owned_goal) as WOMAN_OWNED_GOAL,
					decode(sdb_goal,null,0,sdb_goal) as DISADVANTAGED_BUS_goaL,
					decode(hub_zone_goal,null,0,hub_zone_goal)  as HUB_GOAL,
					decode(veteran_owned_goal,null,0,veteran_owned_goal) as VET_GOAL,
					decode(sdv_goal ,null,0,sdv_goal) as SDV_GOAL,
					decode(hbcu_goal ,null,0,hbcu_goal) as HBCU_GOAL,
					ltrim(rtrim(comments)) as COMMENTS
		FROM  	OSP$SUBCONTRACT_EXP_CAT,
		        OSP$SUBCONTRACTING_BUD
      WHERE 	OSP$SUBCONTRACT_EXP_CAT.MIT_AWARD_NUMBER = substr(as_award_number,1,6) || '-001'
	  AND       OSP$SUBCONTRACT_EXP_CAT.MIT_AWARD_NUMBER = OSP$SUBCONTRACTING_BUD.MIT_AWARD_NUMBER(+);


	 
	end;				  
							  


procedure get_ceo (as_argument in varchar2, 
			cur_type IN OUT result_sets.cur_generic) is
    begin
	 open cur_type for
		SELECT 'Patrick W. Fitzgerald' as name,
				  'Director, Office Of Sponsored Programs' as title
		FROM  dual;
	end;


procedure get_official (as_argument in varchar2, 
			cur_type IN OUT result_sets.cur_generic) is
	begin
		open cur_type for
		SELECT 'Thomas Egan' as name,
				 'Associate Director' as title,
					'617' as areaCode,
					'253-9999' as phone
		FROM dual;
	end;



----------------------------
-- get_294_prime_contract_num
----------------------------
procedure get_294_prime_contract_num (as_award_number IN osp$award.mit_award_number%type,
								cur_type IN OUT result_sets.cur_generic) is
	begin
	open cur_type for
		SELECT SPONSOR_AWARD_NUMBER as prime_contract_number
		FROM   OSP$AWARD
		WHERE  MIT_AWARD_NUMBER = as_award_number
		AND    SEQUENCE_NUMBER IN
			(SELECT MAX(SEQUENCE_NUMBER)
			 FROM OSP$AWARD
			 WHERE MIT_AWARD_NUMBER = as_Award_number);
 

	end;


------------------------
-- fn_get_admin_activity
--    returns ARMY, NAVY, AIR FORCE, GSA
--            DOE, DLA, NASA, or something else
------------------------
function fn_get_admin_activity(as_award_number IN osp$award.mit_award_number%type)
		return varchar2 is

   ls_activity	varchar2(100);
   ls_sponsor  OSP$SPONSOR_HIERARCHY.LEVEL1%TYPE;
   ls_sponsor_name	varchar2(100);

	begin

		SELECT 	LEVEL1
		INTO	   ls_sponsor
		FROM 		OSP$SPONSOR_HIERARCHY
		WHERE    UPPER(HIERARCHY_NAME) = 'ADMINISTERING ACTIVITY'
		AND  		SPONSOR_CODE =
			(SELECT 	DECODE(AH.PRIME_SPONSOR_CODE, NULL, A.SPONSOR_CODE, AH.PRIME_SPONSOR_CODE)
		 	 FROM   	OSP$AWARD_HEADER AH,
			 			OSP$AWARD A,
			 			OSP$SUBCONTRACT_EXP_CAT E
		 	 WHERE  	E.MIT_AWARD_NUMBER = as_award_number
		 	 AND    	A.SEQUENCE_NUMBER IN
						(SELECT 	MAX(SEQUENCE_NUMBER)
			 	 	 	 FROM 	OSP$AWARD
			 	 	 	 WHERE 	MIT_AWARD_NUMBER = A.MIT_AWARD_NUMBER)
		 	 AND     AH.SEQUENCE_NUMBER IN
						(SELECT 	MAX(SEQUENCE_NUMBER)
			 	 	 	 FROM 	OSP$AWARD_HEADER
			 		 	 WHERE 	MIT_AWARD_NUMBER = AH.MIT_AWARD_NUMBER)
		 	 AND     E.MIT_AWARD_NUMBER = A.MIT_AWARD_NUMBER
		 	 AND     E.MIT_AWARD_NUMBER = AH.MIT_aWARD_NUMBER);

	 ls_activity := UPPER(LTRIM(RTRIM(ls_sponsor))) ;

    if (ls_activity <> 'ARMY' and	
        ls_activity <> 'NAVY' and	
        ls_activity <> 'AIR FORCE' and	
        ls_activity <> 'NASA' and	
        ls_activity <> 'DOE' and	
        ls_activity <> 'DLA' and	
		  ls_activity <> 'GSA' ) THEN
          
        SELECT SPONSOR_NAME
		  into 	ls_sponsor_name
		  FROM 	OSP$SPONSOR
		  WHERE 	SPONSOR_CODE =
				(SELECT decode(AH.PRIME_SPONSOR_CODE,NULL,A.SPONSOR_CODE,AH.PRIME_SPONSOR_CODE)
				 FROM   OSP$AWARD A, OSP$AWARD_HEADER AH
				 WHERE  A.MIT_AWARD_NUMBER = as_award_number
				 AND    A.MIT_AWARD_NUMBER = AH.MIT_AWARD_NUMBER
				 AND    A.SEQUENCE_NUMBER IN
					(SELECT MAX(SEQUENCE_NUMBER)
			    	 FROM OSP$AWARD
			 	 	 WHERE MIT_AWARD_NUMBER = A.MIT_AWARD_NUMBER)
				 AND    AH.SEQUENCE_NUMBER IN
					(SELECT MAX(SEQUENCE_NUMBER)
			    	 FROM OSP$AWARD_HEADER
			 	 	 WHERE MIT_AWARD_NUMBER = AH.MIT_AWARD_NUMBER));  
		

	 END IF;
	



	 return ls_activity;

	end;





----------------------------------
--fn_get_is_march_rpt
-----------------------------------
function fn_get_is_march_rpt (as_argument in varchar2)
  return varchar2 is
	ls_ret 		varchar2(10);
	ls_year		varchar2(4);
	li_months	number;

	begin

	select 	to_char(sysdate,'YYYY')
	into   	ls_year
	from 		dual;


	SELECT MONTHS_BETWEEN 
   	(SYSDATE,
    	 TO_DATE('09-30-' || ls_year,'MM-DD-YYYY') ) "Months"
	 INTO 		li_months
    FROM 		DUAL;

	if li_months > 0 then
		return 'FALSE';
	else
		return 'TRUE';
	end if;

end;

----------------------------------
--fn_get_report_year
-----------------------------------
function fn_get_report_year (as_argument in varchar2)
return varchar2 is
	ls_ret 		varchar2(4);
begin

select 	to_char(sysdate,'YYYY')
into   	ls_ret
from 		dual;


return ls_ret;

end;

-----------------------------
-- get_company_info
-----------------------------
procedure get_company_info (as_argument in varchar2,
					   cur_type IN OUT result_sets.cur_generic) is

begin
	open cur_type for
		SELECT O.ORGANIZATION_NAME,
				 O.DUNS_NUMBER,
				 RTRIM(R.ADDRESS_LINE_1) || 
			  		DECODE(LENGTH(RTRIM(R.ADDRESS_LINE_1)), 0, '', NULL, '', ' ') ||
					RTRIM(ADDRESS_LINE_2) || 
 			   	DECODE(LENGTH(RTRIM(R.ADDRESS_LINE_2)), 0, '', NULL, '', ' ') ||
					RTRIM(ADDRESS_LINE_3) || 
 			   	DECODE(LENGTH(RTRIM(R.ADDRESS_LINE_3)), 0, '', NULL, '', ' ') as ADDRESS,
					R.CITY,
				   R.STATE,
					R.POSTAL_CODE
		FROM     OSP$ORGANIZATION O, OSP$ROLODEX R
		WHERE    O.ORGANIZATION_ID = '000001'
		AND      O.CONTACT_ADDRESS_ID = R.ROLODEX_ID;

end;


----------------------------------
-- get_contractor_info
-----------------------------------

procedure get_contractor_info (as_award_number IN osp$award.mit_award_number%type,
						cur_type IN OUT result_sets.cur_generic) is


	ls_sponsor_name	OSP$SPONSOR.SPONSOR_NAME%TYPE;
	 
begin

open cur_type for
		
		SELECT   DECODE(FIRST_NAME,NULL,' ',FIRST_NAME) || ' ' || LAST_NAME as NAME,
					city, state, postal_code,
					RTRIM(ADDRESS_LINE_1) || 
			  		DECODE(LENGTH(RTRIM(ADDRESS_LINE_1)), 0, '', NULL, '', ', ') ||
					RTRIM(ADDRESS_LINE_2) || 
 			   	DECODE(LENGTH(RTRIM(ADDRESS_LINE_2)), 0, '', NULL, '', ', ') ||
					RTRIM(ADDRESS_LINE_3) || 
 			   	DECODE(LENGTH(RTRIM(ADDRESS_LINE_3)), 0, '', NULL, '', ' ') as address

		FROM     OSP$ROLODEX
		WHERE    ROLODEX_ID =
			(SELECT 	max(A.ROLODEX_ID)
			FROM 		OSP$AWARD_REPORT_TERMS A, OSP$SUBCONTRACT_EXP_CAT S
			WHERE 	A.MIT_AWARD_NUMBER = as_Award_number
			AND  		A.MIT_AWARD_NUMBER = S.MIT_AWARD_NUMBER
         and      REPORT_CLASS_CODE = 5 AND REPORT_CODE = 37
			AND		A.SEQUENCE_NUMBER = (SELECT MAX(SEQUENCE_NUMBER)
										 		 FROM OSP$AWARD_REPORT_TERMS
										  		WHERE MIT_AWARD_NUMBER = A.MIT_AWARD_NUMBER) );


	
end;


-------------------------
-- fn_pop_sub_exp_cat_by_fy 
--    called for 295 report to populate osp$sub_exp_cat_by_fy table
-- returns 1 for success, -1 for error
-- commit is done here
--------------------------
function fn_pop_sub_exp_cat_by_fy (ls_period_start IN varchar2,
											  ls_period_end  IN varchar2)
		return number is


begin


	delete from OSP$SUB_EXP_CAT_BY_FY;

	begin

	INSERT INTO OSP$SUB_EXP_CAT_BY_FY
		(MIT_AWARD_NUMBER,               
		LARGE_BUSINESS_AMT,             
		SMALL_BUSINESS_AMT,             
		WOMAN_OWNED_AMT,                
		A8_DISADVANTAGE_AMT,            
		HUB_ZONE_AMT,                   
		VETERAN_OWNED_AMT,              
		SERVICE_DISABLED_VET_OWNED_AMT,
		HISTORICAL_BLACK_COLLEGE_AMT,   
		UPDATE_TIMESTAMP,               
		UPDATE_USER)                    
		SELECT distinct det.MIT_AWARD_NUMBER, LB.AMT LARGE_BUSINESS, SB.AMT SMALL_BUSINESS,
						 WO.AMT WOMAN_OWNED, DA.AMT DISADVANTAGE, HZ.AMT HUB_ZONE,
						 VO.AMT VETERAN_OWNED, DV.AMT DISABLE_VETERAN, HBC.AMT HB_COLLEGE, sysdate, user
		FROM OSP$SUBCONTRACT_EXP_CAT_DET DET, 
			(select MIT_AWARD_NUMBER, sum(AMOUNT) amt
			 from OSP$SUBCONTRACT_EXP_CAT_DET 
			 where IS_LARGE_BUSINESS = 'Y' 
			 and (FISCAL_PERIOD >= ls_period_start AND FISCAL_PERIOD <= ls_period_end)
			 group by MIT_AWARD_NUMBER) LB,
			(select MIT_AWARD_NUMBER, sum(AMOUNT) amt
			 from OSP$SUBCONTRACT_EXP_CAT_DET 
			 where IS_SMALL_BUSINESS = 'Y'
			and (FISCAL_PERIOD >= ls_period_start AND FISCAL_PERIOD <= ls_period_end)
			 group by MIT_AWARD_NUMBER) SB,
			(select MIT_AWARD_NUMBER, sum(AMOUNT) amt
			 from OSP$SUBCONTRACT_EXP_CAT_DET 
			 where IS_WOMAN_OWNED = 'Y'
		and (FISCAL_PERIOD >= ls_period_start AND FISCAL_PERIOD <= ls_period_end)
			 group by MIT_AWARD_NUMBER) WO,
			(select MIT_AWARD_NUMBER, sum(AMOUNT) amt
			 from OSP$SUBCONTRACT_EXP_CAT_DET 
			 where IS_8A_DISADVANTAGE = 'Y'
		and (FISCAL_PERIOD >= ls_period_start AND FISCAL_PERIOD <= ls_period_end)
			 group by MIT_AWARD_NUMBER) DA,
			(select MIT_AWARD_NUMBER, sum(AMOUNT) amt
			 from OSP$SUBCONTRACT_EXP_CAT_DET 
			 where IS_HUB_ZONE = 'Y'
		and (FISCAL_PERIOD >= ls_period_start AND FISCAL_PERIOD <= ls_period_end)
			 group by MIT_AWARD_NUMBER) HZ,
			(select MIT_AWARD_NUMBER, sum(AMOUNT) amt
			 from OSP$SUBCONTRACT_EXP_CAT_DET 
			 where IS_VETERAN_OWNED = 'Y'
		and (FISCAL_PERIOD >= ls_period_start AND FISCAL_PERIOD <= ls_period_end)
			 group by MIT_AWARD_NUMBER) VO,
			(select MIT_AWARD_NUMBER, sum(AMOUNT) amt
			 from OSP$SUBCONTRACT_EXP_CAT_DET 
			 where IS_SERVICE_DISABLED_VET_OWNED = 'Y'
		and (FISCAL_PERIOD >= ls_period_start AND FISCAL_PERIOD <= ls_period_end)
			 group by MIT_AWARD_NUMBER) DV,
			(select MIT_AWARD_NUMBER, sum(AMOUNT) amt
			 from OSP$SUBCONTRACT_EXP_CAT_DET 
			 where IS_HISTORICAL_BLACK_COLLEGE = 'Y'
		and (FISCAL_PERIOD >= ls_period_start AND FISCAL_PERIOD <= ls_period_end)
			 group by MIT_AWARD_NUMBER) HBC
		WHERE DET.MIT_AWARD_NUMBER = LB.MIT_AWARD_NUMBER (+)
		and 	DET.MIT_AWARD_NUMBER = SB.MIT_AWARD_NUMBER (+) 
		and 	DET.MIT_AWARD_NUMBER = WO.MIT_AWARD_NUMBER (+) 
		and 	DET.MIT_AWARD_NUMBER = DA.MIT_AWARD_NUMBER (+) 
		and 	DET.MIT_AWARD_NUMBER = HZ.MIT_AWARD_NUMBER (+) 
		and 	DET.MIT_AWARD_NUMBER = VO.MIT_AWARD_NUMBER (+) 
		and 	DET.MIT_AWARD_NUMBER = DV.MIT_AWARD_NUMBER (+)
		and 	DET.MIT_AWARD_NUMBER = HBC.MIT_AWARD_NUMBER (+)  
  	 	AND DET.FISCAL_PERIOD >= ls_period_start AND DET.FISCAL_PERIOD <= ls_period_end;
	
	
	
	exception
		when others then
			rollback;
			return -1;
end;

commit;
Return(1);
end;







----------------------------------
--get_295_other_names
-----------------------------------
procedure get_295_other_names (as_argument in varchar2,
				cur_type IN OUT result_sets.cur_generic) is


begin

open cur_type for

	SELECT S.SPONSOR_NAME
	FROM   OSP$SPONSOR S,
			 OSP$SUB_EXP_CAT_BY_FY E,
			 OSP$SPONSOR_HIERARCHY H,
			 OSP$AWARD A
	WHERE	 E.MIT_AWARD_NUMBER = A.MIT_AWARD_NUMBER
	AND    A.SEQUENCE_NUMBER = (SELECT MAX(SEQUENCE_NUMBER)
										 FROM OSP$AWARD
										 WHERE MIT_AWARD_NUMBER = A.MIT_AWARD_NUMBER)
	AND    UPPER(H.HIERARCHY_NAME) = 'ADMINISTERING ACTIVITY'
	AND	 H.SPONSOR_CODE = S.SPONSOR_CODE
	AND	 S.SPONSOR_CODE = A.SPONSOR_CODE
	AND	 UPPER(LTRIM(RTRIM(H.LEVEL1))) = 'OTHER'
	AND	 E.MIT_AWARD_NUMBER NOT IN
				(SELECT	E.MIT_AWARD_NUMBER
					 FROM    OSP$SUB_EXP_CAT_BY_FY E,
							OSP$AWARD_HEADER AH
				 WHERE	E.MIT_aWARD_NUMBER = AH.MIT_aWARD_NUMBER
				 AND		AH.SEQUENCE_NUMBER = (SELECT MAX(SEQUENCE_NUMBER)	
														 FROM	OSP$AWARD_HEADER
														 WHERE MIT_AWARD_NUMBER = AH.MIT_AWARD_NUMBER)
				 AND	 AH.PRIME_SPONSOR_CODE IS NOT NULL)

	UNION

	-- get 'other' prime sponsors

	SELECT S.SPONSOR_NAMe
	FROM   OSP$SPONSOR S,
			 OSP$SUB_EXP_CAT_BY_FY E,
			 OSP$SPONSOR_HIERARCHY H,
			 OSP$AWARD_HEADER AH
	WHERE	 E.MIT_AWARD_NUMBER = AH.MIT_AWARD_NUMBER
	AND    AH.SEQUENCE_NUMBER = (SELECT MAX(SEQUENCE_NUMBER)
										 FROM OSP$AWARD_HEADER
										 WHERE MIT_AWARD_NUMBER = AH.MIT_AWARD_NUMBER)
	AND	 AH.PRIME_SPONSOR_CODE IS NOT NULL
	AND    UPPER(H.HIERARCHY_NAME) = 'ADMINISTERING ACTIVITY'
	AND	 H.SPONSOR_CODE = S.SPONSOR_CODE
	AND	 S.SPONSOR_CODE = AH.prime_SPONSOR_CODE
	AND	 UPPER(LTRIM(RTRIM(H.LEVEL1))) = 'OTHER';

	end;

----------------------------------
--fn_get_295_hbcu_amt
-----------------------------------
function fn_get_295_hbcu_amt
		(as_sponsor_group in OSP$SPONSOR_HIERARCHY.LEVEL1%TYPE)
return varchar2 is
	ls_ret 	varchar2(200);
	ll_amt1	number;
	ll_amt2	number;
	ll_amt_tot	number;
	li_len	number;

begin
	ll_amt1 := 0;
	ll_amt2 := 0;

	ls_ret := '            ';
 -- get sum of all awards that do not have prime sponsors 

	SELECT	 decode(SUM(E.HISTORICAL_BLACK_COLLEGE_AMT),NULL,0,SUM(E.HISTORICAL_BLACK_COLLEGE_AMT))
	INTO		ll_amt1
	FROM		OSP$SUB_EXP_CAT_BY_FY E,
				OSP$SPONSOR S,
				OSP$SPONSOR_HIERARCHY H,
				OSP$AWARD A
	WHERE    E.MIT_AWARD_NUMBER = A.MIT_AWARD_NUMBER
	AND      A.SEQUENCE_NUMBER = (SELECT MAX(SEQUENCE_NUMBER) FROM OSP$AWARD
											WHERE MIT_AWARD_NUMBER = A.MIT_aWARD_NUMBER)
	AND      UPPER(H.HIERARCHY_NAME) = 'ADMINISTERING ACTIVITY'
	AND      H.SPONSOR_CODE = S.SPONSOR_CODE
	AND		S.SPONSOR_CODE = A.SPONSOR_CODE
	AND      UPPER(LTRIM(RTRIM(H.LEVEL1))) = UPPER(LTRIM(RTRIM(as_sponsor_group)))
   and      E.mit_award_number not in
					(select  e.mit_award_number
					from		OSP$SUB_EXP_CAT_BY_FY E,
    	   					osp$award_header ah
			   	where    e.mit_award_number = ah.mit_award_number
				 	and 		ah.SEQUENCE_NUMBER = (SELECT MAX(SEQUENCE_NUMBER) FROM OSP$AWARD_header
													WHERE MIT_AWARD_NUMBER = Ah.MIT_aWARD_NUMBER)
					and      ah.prime_sponsor_code is not null);

	-- get sum for awards whose prime sponsor is the sponsor group
	SELECT	decode(SUM(E.HISTORICAL_BLACK_COLLEGE_AMT),NULL,0,SUM(E.HISTORICAL_BLACK_COLLEGE_AMT))
	INTO		ll_amt2
	FROM		OSP$SUB_EXP_CAT_BY_FY E,
				OSP$SPONSOR S,
				OSP$SPONSOR_HIERARCHY H,
				OSP$AWARD_header AH
	WHERE    E.MIT_AWARD_NUMBER = AH.MIT_AWARD_NUMBER
	AND      AH.SEQUENCE_NUMBER = (SELECT MAX(SEQUENCE_NUMBER) FROM OSP$AWARD_header
											WHERE MIT_AWARD_NUMBER = AH.MIT_aWARD_NUMBER)
	and      AH.prime_sponsor_code is not null
	AND      UPPER(H.HIERARCHY_NAME) = 'ADMINISTERING ACTIVITY'
	AND      H.SPONSOR_CODE = S.SPONSOR_CODE
	AND		S.SPONSOR_CODE = AH.prime_SPONSOR_CODE
	AND      UPPER(LTRIM(RTRIM(H.LEVEL1))) = UPPER(LTRIM(RTRIM(as_sponsor_group)));

	ll_amt_tot := ll_amt1 + ll_amt2;

   ls_ret := ll_amt_tot;

	return ls_ret;

	EXCEPTION
		WHEN NO_DATA_FOUND  THEN
			 ls_ret := '';
			 return ls_ret;
end;

----------------------------------
--fn_get_295_hubz_amt
-----------------------------------
function fn_get_295_hubz_amt
			(as_sponsor_group in OSP$SPONSOR_HIERARCHY.LEVEL1%TYPE)
return varchar2 is
	ls_ret 	varchar2(200);
	ll_amt1	number;
	ll_amt2	number;
	ll_amt_tot	number;
begin
	ll_amt1 := 0;
	ll_amt2 := 0;

	ls_ret := '            ';

 -- get sum of all awards that do not have prime sponsors 

	SELECT	 decode(SUM(E.HUB_ZONE_AMT),NULL,0,SUM(E.HUB_ZONE_AMT))
	INTO		ll_amt1
	FROM		OSP$SUB_EXP_CAT_BY_FY E,
				OSP$SPONSOR S,
				OSP$SPONSOR_HIERARCHY H,
				OSP$AWARD A
	WHERE    E.MIT_AWARD_NUMBER = A.MIT_AWARD_NUMBER
	AND      A.SEQUENCE_NUMBER = (SELECT MAX(SEQUENCE_NUMBER) FROM OSP$AWARD
											WHERE MIT_AWARD_NUMBER = A.MIT_aWARD_NUMBER)
	AND      UPPER(H.HIERARCHY_NAME) = 'ADMINISTERING ACTIVITY'
	AND      H.SPONSOR_CODE = S.SPONSOR_CODE
	AND		S.SPONSOR_CODE = A.SPONSOR_CODE
	AND      UPPER(LTRIM(RTRIM(H.LEVEL1))) = UPPER(LTRIM(RTRIM(as_sponsor_group)))
   and      E.mit_award_number not in
					(select  e.mit_award_number
			
					 FROM    OSP$SUB_EXP_CAT_BY_FY E,
    	   					osp$award_header ah
			   	where    e.mit_award_number = ah.mit_award_number
				 	and 		ah.SEQUENCE_NUMBER = (SELECT MAX(SEQUENCE_NUMBER) FROM OSP$AWARD_header
													WHERE MIT_AWARD_NUMBER = Ah.MIT_aWARD_NUMBER)
					and      ah.prime_sponsor_code is not null);

	-- get sum for awards whose prime sponsor is the sponsor group
	SELECT	decode(SUM(E.HUB_ZONE_AMT),NULL,0,SUM(E.HUB_ZONE_AMT))
	INTO		ll_amt2
	FROM		OSP$SUB_EXP_CAT_BY_FY E,
				OSP$SPONSOR S,
				OSP$SPONSOR_HIERARCHY H,
				OSP$AWARD_header AH
	WHERE    E.MIT_AWARD_NUMBER = AH.MIT_AWARD_NUMBER
	AND      AH.SEQUENCE_NUMBER = (SELECT MAX(SEQUENCE_NUMBER) FROM OSP$AWARD_header
											WHERE MIT_AWARD_NUMBER = AH.MIT_aWARD_NUMBER)
	and      AH.prime_sponsor_code is not null
	AND      UPPER(H.HIERARCHY_NAME) = 'ADMINISTERING ACTIVITY'
	AND      H.SPONSOR_CODE = S.SPONSOR_CODE
	AND		S.SPONSOR_CODE = AH.prime_SPONSOR_CODE
	AND      UPPER(LTRIM(RTRIM(H.LEVEL1))) = UPPER(LTRIM(RTRIM(as_sponsor_group)));

	ll_amt_tot := ll_amt1 + ll_amt2;
	
	return ll_amt_tot;

	EXCEPTION
		WHEN NO_DATA_FOUND  THEN
			 ls_ret := '';
			 return ls_ret;
end;



----------------------------------
--fn_get_295_small_bus_amt
-----------------------------------
function fn_get_295_small_bus_amt
			(as_sponsor_group in OSP$SPONSOR_HIERARCHY.LEVEL1%TYPE)
return varchar2 is
	ls_ret 	varchar2(200);
	ll_amt1	number;
	ll_amt2	number;
	ll_amt_tot	number;

begin
	ll_amt1 := 0;
	ll_amt2 := 0;

    
	ls_ret := '            ';

 -- get sum of all awards that do not have prime sponsors 

	SELECT	 decode(SUM(E.SMALL_BUSINESS_AMT),NULL,0,SUM(E.SMALL_BUSINESS_AMT))
	INTO		ll_amt1
	FROM		OSP$SUB_EXP_CAT_BY_FY E,
				OSP$SPONSOR S,
				OSP$SPONSOR_HIERARCHY H,
				OSP$AWARD A
	WHERE    E.MIT_AWARD_NUMBER = A.MIT_AWARD_NUMBER
	AND      A.SEQUENCE_NUMBER = (SELECT MAX(SEQUENCE_NUMBER) FROM OSP$AWARD
											WHERE MIT_AWARD_NUMBER = A.MIT_aWARD_NUMBER)
	AND      UPPER(H.HIERARCHY_NAME) = 'ADMINISTERING ACTIVITY'
	AND      H.SPONSOR_CODE = S.SPONSOR_CODE
	AND		S.SPONSOR_CODE = A.SPONSOR_CODE
	AND      UPPER(LTRIM(RTRIM(H.LEVEL1))) = UPPER(LTRIM(RTRIM(as_sponsor_group)))
   and      E.mit_award_number not in
					(select  e.mit_award_number
					FROM	   OSP$SUB_EXP_CAT_BY_FY E,
    	   					osp$award_header ah
			   	where    e.mit_award_number = ah.mit_award_number
				 	and 		ah.SEQUENCE_NUMBER = (SELECT MAX(SEQUENCE_NUMBER) FROM OSP$AWARD_header
													WHERE MIT_AWARD_NUMBER = Ah.MIT_aWARD_NUMBER)
					and      ah.prime_sponsor_code is not null);

	-- get sum for awards whose prime sponsor is the sponsor group
	SELECT	decode(SUM(E.SMALL_BUSINESS_AMT),NULL,0,SUM(E.SMALL_BUSINESS_AMT))
	INTO		ll_amt2
	FROM		OSP$SUB_EXP_CAT_BY_FY E,
				OSP$SPONSOR S,
				OSP$SPONSOR_HIERARCHY H,
				OSP$AWARD_header AH
	WHERE    E.MIT_AWARD_NUMBER = AH.MIT_AWARD_NUMBER
	AND      AH.SEQUENCE_NUMBER = (SELECT MAX(SEQUENCE_NUMBER) FROM OSP$AWARD_header
											WHERE MIT_AWARD_NUMBER = AH.MIT_aWARD_NUMBER)
	and      AH.prime_sponsor_code is not null
	AND      UPPER(H.HIERARCHY_NAME) = 'ADMINISTERING ACTIVITY'
	AND      H.SPONSOR_CODE = S.SPONSOR_CODE
	AND		S.SPONSOR_CODE = AH.prime_SPONSOR_CODE
	AND      UPPER(LTRIM(RTRIM(H.LEVEL1))) = UPPER(LTRIM(RTRIM(as_sponsor_group)));



	ll_amt_tot := ll_amt1 + ll_amt2;
	ls_ret := ll_amt_tot ;
	
	return ls_ret;

	EXCEPTION
		WHEN NO_DATA_FOUND  THEN
			 ls_ret := '';
			 return ls_ret;
end;


----------------------------------
--fn_get_295_sdb_amt
-----------------------------------
function fn_get_295_sdb_amt
			(as_sponsor_group in OSP$SPONSOR_HIERARCHY.LEVEL1%TYPE)
return varchar2 is
	ls_ret 	varchar2(200);
	ll_amt1	number;
	ll_amt2	number;
	ll_amt_tot	number;

begin
	ll_amt1 := 0;
	ll_amt2 := 0;
	ls_ret := '            ';

 -- get sum of all awards that do not have prime sponsors 

	SELECT	 decode(SUM(E.A8_DISADVANTAGE_AMT),NULL,0,SUM(E.A8_DISADVANTAGE_AMT))
	INTO		ll_amt1
	FROM		OSP$SUB_EXP_CAT_BY_FY E,
				OSP$SPONSOR S,
				OSP$SPONSOR_HIERARCHY H,
				OSP$AWARD A
	WHERE    E.MIT_AWARD_NUMBER = A.MIT_AWARD_NUMBER
	AND      A.SEQUENCE_NUMBER = (SELECT MAX(SEQUENCE_NUMBER) FROM OSP$AWARD
											WHERE MIT_AWARD_NUMBER = A.MIT_aWARD_NUMBER)
	AND      UPPER(H.HIERARCHY_NAME) = 'ADMINISTERING ACTIVITY'
	AND      H.SPONSOR_CODE = S.SPONSOR_CODE
	AND		S.SPONSOR_CODE = A.SPONSOR_CODE
	AND      UPPER(LTRIM(RTRIM(H.LEVEL1))) = UPPER(LTRIM(RTRIM(as_sponsor_group)))
   and      E.mit_award_number not in
					(select  e.mit_award_number
			--	 	from 		OSP$SUBCONTRACT_EXP_CAT E,
					FROM     OSP$SUB_EXP_CAT_BY_FY E,
    	   					osp$award_header ah
			   	where    e.mit_award_number = ah.mit_award_number
				 	and 		ah.SEQUENCE_NUMBER = (SELECT MAX(SEQUENCE_NUMBER) FROM OSP$AWARD_header
													WHERE MIT_AWARD_NUMBER = Ah.MIT_aWARD_NUMBER)
					and      ah.prime_sponsor_code is not null);

	-- get sum for awards whose prime sponsor is the sponsor group
	SELECT	decode(SUM(E.A8_DISADVANTAGE_AMT),NULL,0,SUM(E.A8_DISADVANTAGE_AMT))
	INTO		ll_amt2
	FROM		OSP$SUB_EXP_CAT_BY_FY E,
				OSP$SPONSOR S,
				OSP$SPONSOR_HIERARCHY H,
				OSP$AWARD_header AH
	WHERE    E.MIT_AWARD_NUMBER = AH.MIT_AWARD_NUMBER
	AND      AH.SEQUENCE_NUMBER = (SELECT MAX(SEQUENCE_NUMBER) FROM OSP$AWARD_header
											WHERE MIT_AWARD_NUMBER = AH.MIT_aWARD_NUMBER)
	and      AH.prime_sponsor_code is not null
	AND      UPPER(H.HIERARCHY_NAME) = 'ADMINISTERING ACTIVITY'
	AND      H.SPONSOR_CODE = S.SPONSOR_CODE
	AND		S.SPONSOR_CODE = AH.prime_SPONSOR_CODE
	AND      UPPER(LTRIM(RTRIM(H.LEVEL1))) = UPPER(LTRIM(RTRIM(as_sponsor_group)));

	ll_amt_tot := ll_amt1 + ll_amt2;
	ls_ret := ll_amt_tot ;

 
	return ls_ret;

	EXCEPTION
		WHEN NO_DATA_FOUND  THEN
			 ls_ret := '';
			 return ls_ret;
end;



----------------------------------
--fn_get_295_vet_dis_amt
----------------------------------
function fn_get_295_vet_dis_amt
			(as_sponsor_group in OSP$SPONSOR_HIERARCHY.LEVEL1%TYPE)
		return varchar2 is
ls_ret 	varchar2(200);
	ll_amt1	number;
	ll_amt2	number;
	ll_amt_tot	number;
begin
	ll_amt1 := 0;
	ll_amt2 := 0;
	ls_ret := '            ';
 -- get sum of all awards that do not have prime sponsors 

	SELECT	 decode(SUM(E.SERVICE_DISABLED_VET_OWNED_AMT),NULL,0,SUM(E.SERVICE_DISABLED_VET_OWNED_AMT))
	INTO		ll_amt1
	FROM		OSP$SUB_EXP_CAT_BY_FY E,
				OSP$SPONSOR S,
				OSP$SPONSOR_HIERARCHY H,
				OSP$AWARD A
	WHERE    E.MIT_AWARD_NUMBER = A.MIT_AWARD_NUMBER
	AND      A.SEQUENCE_NUMBER = (SELECT MAX(SEQUENCE_NUMBER) FROM OSP$AWARD
											WHERE MIT_AWARD_NUMBER = A.MIT_aWARD_NUMBER)
	AND      UPPER(H.HIERARCHY_NAME) = 'ADMINISTERING ACTIVITY'
	AND      H.SPONSOR_CODE = S.SPONSOR_CODE
	AND		S.SPONSOR_CODE = A.SPONSOR_CODE
	AND      UPPER(LTRIM(RTRIM(H.LEVEL1))) = UPPER(LTRIM(RTRIM(as_sponsor_group)))
   and      E.mit_award_number not in
					(select  e.mit_award_number
				-- 	from 		OSP$SUBCONTRACT_EXP_CAT E,
					 FROM		OSP$SUB_EXP_CAT_BY_FY E,
    	   					osp$award_header ah
			   	where    e.mit_award_number = ah.mit_award_number
				 	and 		ah.SEQUENCE_NUMBER = (SELECT MAX(SEQUENCE_NUMBER) FROM OSP$AWARD_header
													WHERE MIT_AWARD_NUMBER = Ah.MIT_aWARD_NUMBER)
					and      ah.prime_sponsor_code is not null);

	-- get sum for awards whose prime sponsor is the sponsor group
	SELECT	decode(SUM(E.SERVICE_DISABLED_VET_OWNED_AMT),NULL,0,SUM(E.SERVICE_DISABLED_VET_OWNED_AMT))
	INTO		ll_amt2
	FROM		OSP$SUB_EXP_CAT_BY_FY E,
				OSP$SPONSOR S,
				OSP$SPONSOR_HIERARCHY H,
				OSP$AWARD_header AH
	WHERE    E.MIT_AWARD_NUMBER = AH.MIT_AWARD_NUMBER
	AND      AH.SEQUENCE_NUMBER = (SELECT MAX(SEQUENCE_NUMBER) FROM OSP$AWARD_header
											WHERE MIT_AWARD_NUMBER = AH.MIT_aWARD_NUMBER)
	and      AH.prime_sponsor_code is not null
	AND      UPPER(H.HIERARCHY_NAME) = 'ADMINISTERING ACTIVITY'
	AND      H.SPONSOR_CODE = S.SPONSOR_CODE
	AND		S.SPONSOR_CODE = AH.prime_SPONSOR_CODE
	AND      UPPER(LTRIM(RTRIM(H.LEVEL1))) = UPPER(LTRIM(RTRIM(as_sponsor_group)));

	ll_amt_tot := ll_amt1 + ll_amt2;
	ls_ret := ll_amt_tot ;
	return ls_ret;

	EXCEPTION
		WHEN NO_DATA_FOUND  THEN
			 ls_ret := '';
			 return ls_ret;
end;


----------------------------------
--fn_get_295_veteran_amt
-----------------------------------
function fn_get_295_veteran_amt
			(as_sponsor_group in OSP$SPONSOR_HIERARCHY.LEVEL1%TYPE)
return varchar2 is
	ls_ret 	varchar2(200);
	ll_amt1	number;
	ll_amt2	number;
	ll_amt_tot	number;
begin
	ll_amt1 := 0;
	ll_amt2 := 0;
	ls_ret := '            ';
 -- get sum of all awards that do not have prime sponsors 

	SELECT	 decode(SUM(E.VETERAN_OWNED_AMT),NULL,0,SUM(E.VETERAN_OWNED_AMT))
	INTO		ll_amt1
	FROM		OSP$SUB_EXP_CAT_BY_FY E,
				OSP$SPONSOR S,
				OSP$SPONSOR_HIERARCHY H,
				OSP$AWARD A
	WHERE    E.MIT_AWARD_NUMBER = A.MIT_AWARD_NUMBER
	AND      A.SEQUENCE_NUMBER = (SELECT MAX(SEQUENCE_NUMBER) FROM OSP$AWARD
											WHERE MIT_AWARD_NUMBER = A.MIT_aWARD_NUMBER)
	AND      UPPER(H.HIERARCHY_NAME) = 'ADMINISTERING ACTIVITY'
	AND      H.SPONSOR_CODE = S.SPONSOR_CODE
	AND		S.SPONSOR_CODE = A.SPONSOR_CODE
	AND      UPPER(LTRIM(RTRIM(H.LEVEL1))) = UPPER(LTRIM(RTRIM(as_sponsor_group)))
   and      E.mit_award_number not in
					(select  e.mit_award_number
			--	 	from 		OSP$SUBCONTRACT_EXP_CAT E,
					FROM		OSP$SUB_EXP_CAT_BY_FY E,
    	   					osp$award_header ah
			   	where    e.mit_award_number = ah.mit_award_number
				 	and 		ah.SEQUENCE_NUMBER = (SELECT MAX(SEQUENCE_NUMBER) FROM OSP$AWARD_header
													WHERE MIT_AWARD_NUMBER = Ah.MIT_aWARD_NUMBER)
					and      ah.prime_sponsor_code is not null);

	-- get sum for awards whose prime sponsor is the sponsor group
	SELECT	decode(SUM(E.VETERAN_OWNED_AMT),NULL,0,SUM(E.VETERAN_OWNED_AMT))
	INTO		ll_amt2
	FROM		OSP$SUB_EXP_CAT_BY_FY E,
				OSP$SPONSOR S,
				OSP$SPONSOR_HIERARCHY H,
				OSP$AWARD_header AH
	WHERE    E.MIT_AWARD_NUMBER = AH.MIT_AWARD_NUMBER
	AND      AH.SEQUENCE_NUMBER = (SELECT MAX(SEQUENCE_NUMBER) FROM OSP$AWARD_header
											WHERE MIT_AWARD_NUMBER = AH.MIT_aWARD_NUMBER)
	and      AH.prime_sponsor_code is not null
	AND      UPPER(H.HIERARCHY_NAME) = 'ADMINISTERING ACTIVITY'
	AND      H.SPONSOR_CODE = S.SPONSOR_CODE
	AND		S.SPONSOR_CODE = AH.prime_SPONSOR_CODE
	AND      UPPER(LTRIM(RTRIM(H.LEVEL1))) = UPPER(LTRIM(RTRIM(as_sponsor_group)));

	ll_amt_tot := ll_amt1 + ll_amt2;
ls_ret := ll_amt_tot ;
	return ls_ret;

	EXCEPTION
		WHEN NO_DATA_FOUND  THEN
			 ls_ret := '';
			 return ls_ret;
end;


----------------------------------
--fn_get_295_large_bus_amt
-----------------------------------
function fn_get_295_large_bus_amt
			(as_sponsor_group in OSP$SPONSOR_HIERARCHY.LEVEL1%TYPE)
return varchar2 is
	ls_ret 	varchar2(200);
	ll_amt1	number;
	ll_amt2	number;
	ll_amt_tot	number;
	
begin
	ll_amt1 := 0;
	ll_amt2 := 0;

	ls_ret := '            ';

 -- get sum of all awards that do not have prime sponsors 

	SELECT	 decode(SUM(E.LARGE_BUSINESS_AMT),NULL,0,SUM(E.LARGE_BUSINESS_AMT))
	INTO		ll_amt1
	FROM		OSP$SUB_EXP_CAT_BY_FY E,
				OSP$SPONSOR S,
				OSP$SPONSOR_HIERARCHY H,
				OSP$AWARD A
	WHERE    E.MIT_AWARD_NUMBER = A.MIT_AWARD_NUMBER
	AND      A.SEQUENCE_NUMBER = (SELECT MAX(SEQUENCE_NUMBER) FROM OSP$AWARD
											WHERE MIT_AWARD_NUMBER = A.MIT_aWARD_NUMBER)
	AND      UPPER(H.HIERARCHY_NAME) = 'ADMINISTERING ACTIVITY'
	AND      H.SPONSOR_CODE = S.SPONSOR_CODE
	AND		S.SPONSOR_CODE = A.SPONSOR_CODE
	AND      UPPER(LTRIM(RTRIM(H.LEVEL1))) = UPPER(LTRIM(RTRIM(as_sponsor_group)))
   and      E.mit_award_number not in
					(select  e.mit_award_number
					FROM		OSP$SUB_EXP_CAT_BY_FY E,
    	   					osp$award_header ah
			   	where    e.mit_award_number = ah.mit_award_number
				 	and 		ah.SEQUENCE_NUMBER = (SELECT MAX(SEQUENCE_NUMBER) FROM OSP$AWARD_header
													WHERE MIT_AWARD_NUMBER = Ah.MIT_aWARD_NUMBER)
					and      ah.prime_sponsor_code is not null);

	-- get sum for awards whose prime sponsor is the sponsor group
	SELECT	decode(SUM(E.LARGE_BUSINESS_AMT),NULL,0,SUM(E.LARGE_BUSINESS_AMT))
	INTO		ll_amt2
	FROM		OSP$SUB_EXP_CAT_BY_FY E,
				OSP$SPONSOR S,
				OSP$SPONSOR_HIERARCHY H,
				OSP$AWARD_header AH
	WHERE    E.MIT_AWARD_NUMBER = AH.MIT_AWARD_NUMBER
	AND      AH.SEQUENCE_NUMBER = (SELECT MAX(SEQUENCE_NUMBER) FROM OSP$AWARD_header
											WHERE MIT_AWARD_NUMBER = AH.MIT_aWARD_NUMBER)
	and      AH.prime_sponsor_code is not null
	AND      UPPER(H.HIERARCHY_NAME) = 'ADMINISTERING ACTIVITY'
	AND      H.SPONSOR_CODE = S.SPONSOR_CODE
	AND		S.SPONSOR_CODE = AH.prime_SPONSOR_CODE
	AND      UPPER(LTRIM(RTRIM(H.LEVEL1))) = UPPER(LTRIM(RTRIM(as_sponsor_group)));

	ll_amt_tot := ll_amt1 + ll_amt2;
	ls_ret := ll_amt_tot ;
	

	return ls_ret;
 

	EXCEPTION
		WHEN NO_DATA_FOUND  THEN
			 ls_ret := '';
			 return ls_ret;
end;


----------------------------------
--fn_get_295_tot_small_large_amt
-----------------------------------
function fn_get_295_tot_small_large_amt
			(as_sponsor_group in OSP$SPONSOR_HIERARCHY.LEVEL1%TYPE)
return varchar2 is
	ls_ret 			varchar2(200);
	ls_amt_large	varchar2(200);
	ls_amt_small	varchar2(200);
	ll_amt_large	number;
	ll_amt_small	number;
	ll_amt_tot		number;
	

begin

	ls_ret := '            ';

	ls_amt_large := fn_get_295_large_bus_amt(as_sponsor_group);
	ls_amt_small := fn_get_295_small_bus_amt(as_sponsor_group);
--	ll_amt_large := TO_NUMBER(ls_amt_large,'$99,999,999,990');
--	ll_amt_small := TO_NUMBER(ls_amt_small,'$99,999,999,990');
   ll_amt_large := TO_NUMBER(ls_amt_large);
	ll_amt_small := TO_NUMBER(ls_amt_small);

	ll_amt_tot := ll_amt_large + ll_amt_small;
	ls_ret := ll_amt_tot ;

	return ls_ret;
end;

----------------------------------
--fn_get_295_wosb_amt
-----------------------------------
function fn_get_295_wosb_amt
			(as_sponsor_group in OSP$SPONSOR_HIERARCHY.LEVEL1%TYPE)
return varchar2 is
	ls_ret 	varchar2(200);
	ll_amt1	number;
	ll_amt2	number;
	ll_amt_tot	number;
	
begin
	ll_amt1 := 0;
	ll_amt2 := 0;

	ls_ret := '            ';

 -- get sum of all awards that do not have prime sponsors 

	SELECT	 decode(SUM(E.WOMAN_OWNED_AMT),NULL,0,SUM(E.WOMAN_OWNED_AMT))
	INTO		ll_amt1
	FROM		OSP$SUB_EXP_CAT_BY_FY E,
				OSP$SPONSOR S,
				OSP$SPONSOR_HIERARCHY H,
				OSP$AWARD A
	WHERE    E.MIT_AWARD_NUMBER = A.MIT_AWARD_NUMBER
	AND      A.SEQUENCE_NUMBER = (SELECT MAX(SEQUENCE_NUMBER) FROM OSP$AWARD
											WHERE MIT_AWARD_NUMBER = A.MIT_aWARD_NUMBER)
	AND      UPPER(H.HIERARCHY_NAME) = 'ADMINISTERING ACTIVITY'
	AND      H.SPONSOR_CODE = S.SPONSOR_CODE
	AND		S.SPONSOR_CODE = A.SPONSOR_CODE
	AND      UPPER(LTRIM(RTRIM(H.LEVEL1))) = UPPER(LTRIM(RTRIM(as_sponsor_group)))
   and      E.mit_award_number not in
					(select  e.mit_award_number
			--	 	from 		OSP$SUBCONTRACT_EXP_CAT E,
					FROM		OSP$SUB_EXP_CAT_BY_FY E,
    	   					osp$award_header ah
			   	where    e.mit_award_number = ah.mit_award_number
				 	and 		ah.SEQUENCE_NUMBER = (SELECT MAX(SEQUENCE_NUMBER) FROM OSP$AWARD_header
													WHERE MIT_AWARD_NUMBER = Ah.MIT_aWARD_NUMBER)
					and      ah.prime_sponsor_code is not null);

	-- get sum for awards whose prime sponsor is the sponsor group
	SELECT	decode(SUM(E.WOMAN_OWNED_AMT),NULL,0,SUM(E.WOMAN_OWNED_AMT))
	INTO		ll_amt2
	FROM		OSP$SUB_EXP_CAT_BY_FY E,
				OSP$SPONSOR S,
				OSP$SPONSOR_HIERARCHY H,
				OSP$AWARD_header AH
	WHERE    E.MIT_AWARD_NUMBER = AH.MIT_AWARD_NUMBER
	AND      AH.SEQUENCE_NUMBER = (SELECT MAX(SEQUENCE_NUMBER) FROM OSP$AWARD_header
											WHERE MIT_AWARD_NUMBER = AH.MIT_aWARD_NUMBER)
	and      AH.prime_sponsor_code is not null
	AND      UPPER(H.HIERARCHY_NAME) = 'ADMINISTERING ACTIVITY'
	AND      H.SPONSOR_CODE = S.SPONSOR_CODE
	AND		S.SPONSOR_CODE = AH.prime_SPONSOR_CODE
	AND      UPPER(LTRIM(RTRIM(H.LEVEL1))) = UPPER(LTRIM(RTRIM(as_sponsor_group)));

	ll_amt_tot := ll_amt1 + ll_amt2;
	ls_ret := ll_amt_tot ;

	
	return ls_ret;

	EXCEPTION
		WHEN NO_DATA_FOUND  THEN
			 ls_ret := '';
			 return ls_ret;
end;



end;   --end of Package

/

