CREATE OR REPLACE PROCEDURE  getBudPersonSal
              (as_proposal_number IN OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE, 
                ai_version IN OSP$BUDGET.VERSION_NUMBER%TYPE,
                ai_period IN OSP$BUDGET_PERIODS.BUDGET_PERIOD%TYPE,
                cur_generic IN OUT result_sets.cur_generic) is 

 ls_personid  OSP$EPS_PROP_INVESTIGATORS.PERSON_ID%TYPE; 
 ls_PIid    OSP$EPS_PROP_INVESTIGATORS.PERSON_ID%TYPE; 
 li_count   integer; 
 ls_title   varchar2(51); 
   li_index       integer; 
 li_fund   number; 
 ls_cost_element  integer; 
 li_ret   number; 
 li_month   integer; 
 LS_DESCRIPTION  OSP$BUDGET_CATEGORY.DESCRIPTION%TYPE;           
 li_vacation  osp$budget_personnel_cal_amts.calculated_cost%TYPE := 0 ;   
 li_line_item osp$budget_details.line_item_number%type;              
 li_la    osp$budget_personnel_cal_amts.calculated_cost%TYPE := 0;   
 ld_start_date date;   
 ld_end_date  date;   
 ls_SessionId varchar2(50); 
 ls_JobCode   OSP$BUDGET_PERSONS.JOB_CODE%TYPE; 
 ls_AppntType OSP$BUDGET_PERSONS.APPOINTMENT_TYPE%TYPE; 
 ls_Name   osp$person.full_name%type; 
 li_BaseSalary osp$budget_persons.calculation_base%type; 
 li_SalaryRequested  osp$budget_personnel_details.salary_requested%TYPE; 
 ls_percentEffort   varchar2(10); 
 li_fringe     osp$budget_personnel_cal_amts.calculated_cost%TYPE; 
 li_totalsal     osp$budget_personnel_details.salary_requested%TYPE; 

 ls_role      varchar2(150);     
 ls_prop_title    varchar2(150);    
   li_person_number  number;     
 TYPE r_person_record IS RECORD  (nametitle  varchar2(100), 
     person_id     varchar2(9), 
     person_name    varchar2(90), 
     proj_role     varchar2(100), 
     appnt_type    varchar2(5), 
     effort       varchar2(10),  
     base_salary   number, 
             salary_requested   number, 
     fringe_benifits   number, 
     total     number); 
 TYPE  t_person_list IS TABLE OF r_person_record INDEX BY BINARY_INTEGER; 
 gtbl_person_list          t_person_list; 
   CURSOR c_person_line_item (prop_num VARCHAR2, id VARCHAR2, jcode varchar2, period_num number, version number) is 
  select DECODE(sum(ROUND(osp$budget_personnel_details.salary_requested)),NULL,0, 
          sum(ROUND(osp$budget_personnel_details.salary_requested))) salary,  
       ltrim(rtrim(to_char(osp$budget_personnel_details.percent_effort,'990'))) percent, 
--     ltrim(rtrim(to_char(osp$budget_personnel_details.percent_charged, '990'))) percent,  
       DECODE(sum(ROUND(osp$budget_personnel_details.salary_requested)),NULL,0, 
       sum(ROUND(osp$budget_personnel_details.salary_requested))) total, 
       person_number            
   from osp$budget_personnel_details 
  where osp$budget_personnel_details.proposal_number = prop_num 
   and osp$budget_personnel_details.version_number = version 
   and osp$budget_personnel_details.budget_period = period_num 
   and osp$budget_personnel_details.person_id = id 
   and osp$budget_personnel_details.job_code = jcode 
   group by osp$budget_personnel_details.job_code,  
--    osp$budget_personnel_details.percent_charged, 
      osp$budget_personnel_details.percent_effort, 
         person_number;                
begin 
-- sept 21-- li_index := 0; 
 li_month := 0; 
   gtbl_person_list.delete; 
 li_index := 1; 
 select user || to_char(sysdate, 'mmddyyyyhh24miss') 
 into ls_SessionId 
 from dual; 


--Get Salary requested for the PI 
--IF PI is not specified in the budget add PI name as first person in the list 
 DECLARE CURSOR c_pi IS 
   select distinct  osp$budget_persons.person_id, osp$person.full_name, osp$budget_persons.job_code, 
          DECODE(LTRIM(RTRIM(osp$budget_persons.appointment_type)),
                '9M  DURATION', '9', 
					 '9M DURATION','9',
                '10M DURATION', '10',  
                '11M DURATION', '11', 
                '12M DURATION', '12', 
                'REG EMPLOYEE', '12', 
                'SUM EMPLOYEE', '3', 
                'TMP EMPLOYEE', '1', '12'), 
         ROUND(osp$budget_persons.calculation_base) 
   from osp$budget_persons, osp$person, osp$eps_prop_investigators 
   where osp$budget_persons.proposal_number = as_proposal_number 
    and osp$budget_persons.version_number = ai_version
    and osp$budget_persons.person_id = osp$person.person_id 
    and osp$eps_prop_investigators.proposal_number = as_proposal_number 
    and osp$budget_persons.person_id = osp$eps_prop_investigators.person_id 
    and osp$eps_prop_investigators.principal_investigator_flag = 'Y' 
    and osp$budget_persons.effective_date = (select min(effective_date) from 
              osp$budget_persons bp 
              where bp.proposal_number = osp$budget_persons.proposal_number 
               and bp.version_number = osp$budget_persons.version_number 
                 and bp.job_code = osp$budget_persons.job_code 
               and bp.person_id = osp$budget_persons.person_id); 
 BEGIN 
 OPEN c_pi; 
 LOOP 
  FETCH c_pi INTO ls_PIid, ls_Name, ls_JobCode, ls_AppntType, li_BaseSalary; 
  EXIT WHEN c_pi%NOTFOUND; 

dbms_output.put_line('pi id is ' || ls_PIid);

  OPEN c_person_line_item(as_proposal_number, ls_PIid, ls_JobCode, AI_PERIOD, ai_version);  
  LOOP 
   FETCH c_person_line_item INTO li_SalaryRequested, ls_percentEffort, li_totalsal, 
         li_person_number;                 
   EXIT WHEN c_person_line_item%NOTFOUND; 
   gtbl_person_list(li_Index).person_id := ls_PIid; 
   gtbl_person_list(li_Index).person_name := ls_Name; 
   gtbl_person_list(li_Index).proj_role := 'Principal Investigator'; 
   gtbl_person_list(li_Index).appnt_type := ls_AppntType; 
   gtbl_person_list(li_Index).base_salary := li_BaseSalary; 
   gtbl_person_list(li_Index).effort := ls_percentEffort; 
   gtbl_person_list(li_Index).salary_requested := li_SalaryRequested; 
   gtbl_person_list(li_Index).total := li_totalsal; 

   dbms_output.put_line('index is ' || li_index || ' id is ' || ls_PIid || ' effort is ' || ls_percentEffort);

  ---------------------------------------------------------------------------------- 
  --  GET THE SUM OF THE FRINGES (EB AND VAC) ON THIS PERSON, BUT DO Not ADD THE EB AND VAC ON LA. 
  --  IT WILL APPEAR ON THE FRINGE LINE FOR MR LA  
  ------------------------------------------------------------------------------------  
   select  sum(ROUND(osp$budget_personnel_cal_amts.calculated_cost )) 
   into   li_fringe  
   from   osp$budget_personnel_cal_amts,  osp$budget_personnel_details, 
      OSP$RATE_CLASS    
   where  osp$budget_personnel_cal_amts.proposal_number= as_proposal_number 
   and    osp$budget_personnel_cal_amts.version_number = ai_version 
   and    osp$budget_personnel_cal_amts.budget_period  = AI_PERIOD
   and    osp$budget_personnel_cal_amts.rate_class_code = osp$rate_class.rate_class_code 
   and   ( (osp$RATE_CLASS.rate_class_TYPE = 'E' AND  
      osp$budget_personnel_cal_amts.RATE_TYPE_CODE <> 3) 
       OR 
     (osp$RATE_CLASS.rate_class_TYPE = 'V' AND 
       osp$budget_personnel_cal_amts.RATE_TYPE_CODE <> 2)) 
   and   osp$budget_personnel_cal_Amts.person_number = osp$budget_personnel_details.person_number 
   and   osp$budget_personnel_cal_Amts.person_number = li_person_number  
   and   osp$budget_personnel_details.proposal_number =  osp$budget_personnel_cal_amts.proposal_number 
   and   osp$budget_personnel_details.line_item_number = osp$budget_personnel_cal_amts.line_item_number 
   and   osp$budget_personnel_details.version_number = ai_version 
   and   osp$budget_personnel_details.budget_period = AI_PERIOD
   and   osp$budget_personnel_details.person_id = ls_piid 
   and   osp$budget_personnel_details.job_code = ls_JobCode; 
   if li_fringe is null then 
    li_fringe := 0; 
   end if; 
   gtbl_person_list(li_index).fringe_benifits := li_fringe; 
   gtbl_person_list(li_index).total := gtbl_person_list(li_index).total + li_fringe; 
   li_Index := li_Index + 1; 
  END LOOP; 
  CLOSE c_person_line_item; 
 END LOOP; 
 CLOSE c_pi; 
 END; 
 --------------------------------- 
 -- if PI is not part of the budget 
 --------------------------------- 
dbms_output.put_line('li index is ' || li_index);

 IF li_Index = 1 THEN  
-- sept 21--if li_index = 0 then
  BEGIN 
dbms_output.put_line('no pi, index is ' || li_index);

   select osp$eps_prop_investigators.person_id, osp$person.full_name 
   into ls_PIid, ls_Name 
   from osp$eps_prop_investigators, osp$person 
   where osp$eps_prop_investigators.proposal_number = as_proposal_number 
    and osp$eps_prop_investigators.principal_investigator_flag = 'Y' 
    and osp$eps_prop_investigators.person_id = osp$person.person_id; 
   gtbl_person_list(li_Index).person_id := ls_PIid; 
   gtbl_person_list(li_Index).person_name := ls_Name; 
   gtbl_person_list(li_Index).proj_role := 'Principal Investigator'; 
   gtbl_person_list(li_Index).salary_requested := 0; 
   gtbl_person_list(li_Index).fringe_benifits := 0; 
   gtbl_person_list(li_Index).total := 0; 
   li_Index := li_Index + 1; 
  EXCEPTION 
   WHEN NO_DATA_FOUND THEN 
    GOTO open_cursor; 
   RETURN; 
  END; 
 END IF; 
---------------------------------------- 
-- Now that we have taken care of the PI, 
-- get rest of the budget persons 
-- get project role from key person role if possible 
--        if not, try to use directory title from proposal persons 
--        if not, use directory title from person 
---------------------------------------- 
 DECLARE CURSOR c_person IS 
  select distinct   osp$budget_persons.person_id,  
        osp$person.full_name ,  
        osp$budget_persons.job_code, 
        DECODE(LTRIM(RTRIM(osp$budget_persons.appointment_type)),                      
           '9M DURATION', '9', 
           '9M  DURATION', '9',
           '10M DURATION', '10',  
           '11M DURATION', '11', 
           '12M DURATION', '12', 
           'REG EMPLOYEE', '12', 
           'SUM EMPLOYEE', '3', 
           'TMP EMPLOYEE', '1', '12'), 
         ROUND(osp$budget_persons.calculation_base),  
        RTRIM(osp$eps_prop_key_persons.project_role), 
        RTRIM(osp$eps_prop_person.directory_title), 
        RTRIM(osp$person.directory_title) 
  from       osp$budget_persons,  
        osp$person, 
        osp$eps_prop_person,  
        osp$eps_prop_key_persons 
  where      osp$budget_persons.proposal_number = as_proposal_number 
  and      osp$budget_persons.version_number = ai_version
  and      osp$budget_persons.person_id = osp$person.person_id 
  and      osp$budget_persons.person_id <> ls_PIid 
  AND         osp$budget_persons.person_id = osp$eps_prop_person.person_id (+) 
  and         osp$budget_persons.person_id = osp$eps_prop_key_persons.person_id (+)  
  and         OSP$BUDGET_PERSONS.PROPOSAL_NUMBER = OSP$EPS_PROP_PERSON.PROPOSAL_NUMBER(+) 
  and         osp$budget_persons.proposal_number = osp$eps_prop_key_persons.proposal_number(+) 
  and      osp$budget_persons.effective_date = (select min(effective_date) from 
               osp$budget_persons bp 
               where bp.proposal_number = osp$budget_persons.proposal_number 
               and bp.version_number = osp$budget_persons.version_number 
                 and bp.job_code = osp$budget_persons.job_code 
               and bp.person_id = osp$budget_persons.person_id); 
 BEGIN 
 OPEN c_person; 
 LOOP 
  FETCH c_person INTO ls_personid, ls_Name, ls_JobCode, ls_AppntType, li_BaseSalary, ls_role, 
          ls_prop_title, ls_title; 
  EXIT WHEN c_person%NOTFOUND; 
    if (ls_role is not null) then 
      ls_title := ls_role; 
    elsif (ls_prop_title is not null) then 
      ls_title := ls_prop_title; 
    else 
      if (substr(ls_name,1,3) = 'TBA') then
      	ls_title := ltrim(rtrim(substr(ls_name,6,80)));
       end if;
  end if; 
  OPEN c_person_line_item(as_proposal_number, ls_personid, ls_JobCode, AI_PERIOD, ai_version); 
  LOOP 
   FETCH c_person_line_item INTO li_SalaryRequested, ls_percentEffort,  li_totalsal, 
              li_person_number;           
   EXIT WHEN c_person_line_item%NOTFOUND; 

dbms_output.put_line('adding more to gtbl, index is ' || li_index || ' person id is ' || ls_personid || ' name is '
 || ls_name);

   gtbl_person_list(li_Index).person_id := ls_personid; 
   gtbl_person_list(li_Index).person_name := ls_Name; 
   gtbl_person_list(li_Index).proj_role := ls_Title; 
   gtbl_person_list(li_Index).appnt_type := ls_AppntType; 
   gtbl_person_list(li_Index).base_salary := li_BaseSalary; 
   gtbl_person_list(li_Index).effort := ls_percentEffort; 
   gtbl_person_list(li_Index).salary_requested := li_SalaryRequested; 
   gtbl_person_list(li_Index).total := li_totalsal; 
  -- get fringes on this person 
   select sum(ROUND(osp$budget_personnel_cal_amts.calculated_cost) ) 
   into  li_fringe  
   from  osp$budget_personnel_cal_amts,  osp$budget_personnel_details, 
     OSP$RATE_CLASS    
   where osp$budget_personnel_cal_amts.proposal_number= as_proposal_number 
   and   osp$budget_personnel_cal_amts.version_number = ai_version
   and   osp$budget_personnel_cal_amts.budget_period  = AI_PERIOD 
   and   osp$budget_personnel_cal_amts.rate_class_code = osp$rate_class.rate_class_code 
   and  ( (osp$RATE_CLASS.rate_class_TYPE = 'E' AND  
      osp$budget_personnel_cal_amts.RATE_TYPE_CODE <> 3) 
       OR 
     (osp$RATE_CLASS.rate_class_TYPE = 'V' AND 
       osp$budget_personnel_cal_amts.RATE_TYPE_CODE <> 2)) 
   and   osp$budget_personnel_cal_Amts.person_number = osp$budget_personnel_details.person_number 
     and   osp$budget_personnel_cal_Amts.person_number = li_person_number   
   and   osp$budget_personnel_details.proposal_number =  osp$budget_personnel_cal_amts.proposal_number 
   and   osp$budget_personnel_details.line_item_number = osp$budget_personnel_cal_amts.line_item_number 
   and   osp$budget_personnel_details.version_number = ai_version
   and   osp$budget_personnel_details.budget_period = AI_PERIOD
   and   osp$budget_personnel_details.person_id = ls_personid 
   and   osp$budget_personnel_details.job_code = ls_JobCode; 
   if li_fringe IS NULL then 
    li_fringe := 0; 
   end if; 
   gtbl_person_list(li_index).fringe_benifits := li_fringe; 
   gtbl_person_list(li_Index).total := gtbl_person_list(li_Index).total + li_fringe; 
   li_Index := li_Index + 1; 
  END LOOP; 
  CLOSE c_person_line_item; 
 END LOOP; 
 CLOSE c_person; 
 END; 
begin 
 ------------------------------------------------ 
 -- get all the personnel cost elements  
   -- that do not have persons attached to the cost element  
 -- for each line item, get the EB, VAC and LA 
 ------------------------------------------------ 
 DECLARE CURSOR C_OTHER_PERSONNEL IS 
  select   OSP$BUDGET_DETAILS.line_item_number, 
     OSP$BUDGET_CATEGORY.description, 
     ROUND(OSP$BUDGET_DETAILS.line_item_cost)      
  from    OSP$BUDGET_DETAILS, 
       OSP$BUDGET_CATEGORY 
  where  OSP$BUDGET_DETAILS.proposal_number = as_proposal_number 
  and  OSP$BUDGET_DETAILS.VERSION_NUMBER = ai_version
  and    osp$budget_details.budget_period  = AI_PERIOD
  and    OSP$BUDGET_DETAILS.BUDGET_CATEGORY_CODE = OSP$BUDGET_CATEGORY.budget_CATEGORY_CODE 
  AND    OSP$BUDGET_CATEGORY.category_type = 'P' 
  AND    OSP$BUDGET_DETAILS.line_item_number not in 
     (select line_item_number 
          from  osp$budget_personnel_details 
          where proposal_number = as_proposal_number 
           and   version_number  = ai_version
          and   budget_period  = AI_PERIOD); 
  BEGIN 
   OPEN c_other_personnel; 
   LOOP 
    FETCH c_other_personnel INTO li_line_item, ls_description, li_SalaryRequested; 
    EXIT WHEN c_other_personnel%NOTFOUND; 
    -- now get the EB on this line item 
    -- but do not get the EB and VAC on Mr LA 
   begin  
    SELECT sum(ROUND(a.calculated_cost)) 
    into     li_fringe 
    from  osp$budget_details_cal_amts a 
    where  a.proposal_number = as_proposal_number 
    and  a.version_number =  ai_version
    and  a.line_item_number = li_line_item 
    and  a.budget_period = AI_PERIOD
    and  ( (a.rate_class_code = 5 
              and 
        a.rate_type_code <> 3) 
             or 
        (a.rate_class_code = 8 
              and 
        a.rate_type_code <> 2)); 
    EXCEPTION 
    WHEN OTHERS THEN 
     -- there is no EB on this line item 
     li_fringe := 0; 
   END; 
   if li_fringe is null then 
    li_fringe := 0; 
   end if; 
   if li_salaryRequested > 0 then 
 dbms_output.put_line('adding another line index is ' || li_index );

     gtbl_person_list(li_Index).person_name := SUBSTR(ls_description,1,90); 
     gtbl_person_list(li_Index).salary_requested := li_SalaryRequested; 
     gtbl_person_list(li_Index).fringe_benifits := li_fringe; 
     gtbl_person_list(li_Index).total := li_salaryrequested + li_fringe; 
     li_Index := li_Index + 1; 
   end if; 
   END LOOP; 
  CLOSE C_OTHER_PERSONNEL; 
 END; 
end; 
 ------------------------------- 
 --Add a line for Lab Allocation 
 ------------------------------- 
 BEGIN 
  select DECODE(sum(ROUND(osp$budget_details_cal_amts.calculated_cost)),NULL,0, 
       sum(ROUND(osp$budget_details_cal_amts.calculated_cost))) 
  into li_SalaryRequested 
  from osp$budget_details_cal_amts, osp$rate_class 
  where osp$budget_details_cal_amts.proposal_number= as_proposal_number 
   and osp$budget_details_cal_amts.version_number = ai_version
   and osp$budget_details_cal_amts.budget_period  = AI_PERIOD
   and osp$budget_details_cal_amts.rate_class_code = osp$rate_class.rate_class_code 
   and osp$rate_class.rate_class_type = 'Y'; 
  IF li_SalaryRequested > 0 THEN 
   gtbl_person_list(li_Index).person_name := 'Lab Allocation'; 
  
   gtbl_person_list(li_Index).salary_requested := li_SalaryRequested; 
   gtbl_person_list(li_Index).total := li_SalaryRequested; 
   /* get fringes on MR LA  */ 
   select  DECODE(sum(ROUND(osp$budget_details_cal_amts.calculated_cost)),NULL,0, 
              sum(ROUND(osp$budget_details_cal_amts.calculated_cost))) 
   into   li_fringe 
   from   osp$budget_details_cal_amts 
   where  osp$budget_details_cal_amts.proposal_number= as_proposal_number 
   and   osp$budget_details_cal_amts.version_number = ai_version
   and   osp$budget_details_cal_amts.budget_period  = AI_PERIOD 
   and   ( (osp$budget_details_cal_amts.rate_class_code = 5 
              and 
        osp$budget_details_cal_amts.rate_type_code = 3) 
             or 
       (osp$budget_details_cal_amts.rate_class_code = 8 
              and 
        osp$budget_details_cal_amts.rate_type_code = 2)); 
   gtbl_person_list(li_Index).fringe_benifits := li_fringe; 
   gtbl_person_list(li_index).total := gtbl_person_list(li_index).total + li_fringe; 
   li_Index := li_Index + 1; 
  END IF; 
 EXCEPTION 
  WHEN OTHERS THEN 
   li_index := li_Index; --Do Nothing dummy statement 
 END; 
 --Insert values in gtbl_person_list to the temp table osp$bud_per_sal_det_prn_tmp 
 IF gtbl_person_list.COUNT > 0 THEN 
  FOR li_Index IN gtbl_person_list.FIRST .. gtbl_person_list.LAST LOOP 
 
dbms_output.put_line ('inserting into table index is ' || li_index || ' name is ' || gtbl_person_list(li_index).person_id);

   INSERT into osp$bud_per_sal_det_prn_tmp 
    (session_id, person_id, person_name, proj_role, appnt_type, effort, base_salary,  
    salary_requested, fringe_benifits, total) 
   VALUES (ls_SessionId,  
      gtbl_person_list(li_Index).person_id,  
      gtbl_person_list(li_Index).person_name,  
      gtbl_person_list(li_Index).proj_role,  
      gtbl_person_list(li_Index).appnt_type,  
      gtbl_person_list(li_Index).effort,  
      gtbl_person_list(li_Index).base_salary,  
      gtbl_person_list(li_Index).salary_requested,  
      gtbl_person_list(li_Index).fringe_benifits,  
      gtbl_person_list(li_Index).total); 
  END LOOP; 
 END IF; 
 <<open_cursor>> 
 open cur_generic for 

/* changed to combine persons to one line
  select t.person_id as PERSONID,  
     t.person_name AS FULLNAME, 
     p.last_name AS LASTNAME, 
     p.first_name AS FIRSTNAME, 
     p.middle_name AS MIDDLENAME,  
     t.proj_role AS PROJECTROLE,  
     t.appnt_type AS APPOINTMENTTYPE,  
     t.effort AS EFFORT,  
     t.base_salary AS BASESALARY,  
     t.salary_requested AS SALARYREQUESTED,  
     t.fringe_benifits AS FRINGEBENEFITS,  
     t.total AS TOTAL 
  from osp$bud_per_sal_det_prn_tmp t, osp$person p 
  where session_id = ls_SessionId 
  and  t.person_id = p.person_id  (+)
  order by DECODE(proj_role, 'Principal Investigator', 1, 2) ASC, person_name; 
*/

select sum(t.effort) as EFFORT, 
       sum(t.salary_requested) AS SALARYREQUESTED, 
       sum(t.fringe_benifits) AS FRINGEBENEFITS, 
       sum( t.total) AS TOTAL ,
       t.person_id as PERSONID,  
       t.person_name AS FULLNAME, 
       p.last_name AS LASTNAME, 
       p.first_name AS FIRSTNAME, 
       p.middle_name AS MIDDLENAME,  
       t.proj_role AS PROJECTROLE,  
       t.appnt_type AS APPOINTMENTTYPE,  
       t.base_salary AS BASESALARY
  from osp$bud_per_sal_det_prn_tmp t, osp$person p 
  where t.person_id = p.person_id  (+)
  and   session_id = ls_SessionId 
group by t.person_id, t.person_name, p.last_name,p.first_name,
         p.middle_name,t.proj_role, t.appnt_type,t.base_salary
 order by DECODE(proj_role, 'Principal Investigator', 1, 2) ASC, person_name; 

 delete from osp$bud_per_sal_det_prn_tmp 
  where session_id = ls_SessionId; 

END;

/








