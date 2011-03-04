create or replace FUNCTION          "FN_GET_CATEGORY_DESC" (as_proposal_number OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE,
																 as_sponsor varchar2,
																 ai_version_number osp$budget.version_number%type,
																 ai_budget_period osp$budget_periods.budget_period%type,
                                                 as_category_code osp$budget_category_mapping.target_category_code%type)
 return  varchar2 is
li_cost number;
ls_description varchar2(200);
ls_Line_description varchar2(200);
ls_string varchar2(2000) := '';
begin
	DECLARE CURSOR c_desc is
	select osp$cost_element.description, ROUND(osp$budget_details.line_item_cost),
			 osp$budget_details.line_item_description
	from osp$budget_details, osp$cost_element, OSP$BUDGET_CATEGORY_MAPPING
	where osp$budget_details.proposal_number = as_proposal_number
		and osp$budget_details.version_number = ai_version_number
		and osp$budget_details.budget_period  = ai_budget_period
      AND osp$budget_details.budget_category_code = OSP$BUDGET_cATEGORY_MAPPING.COEUS_CATEGORY_CODE
      and osp$budget_category_mapping.mapping_name = as_sponsor
      AND OSP$BUDGET_CATEGORY_MAPPING.TARGET_CATEGORY_CODE = as_category_code
		and osp$budget_details.cost_element = osp$cost_element.cost_element ;


	BEGIN
		OPEN c_desc;
		LOOP
				FETCH c_desc INTO ls_description,li_cost, ls_Line_description;
			   EXIT WHEN c_desc%NOTFOUND;

				--If there is a lineitem description then print that
				--else print cost element description.

				IF LENGTH(RTRIM(ls_Line_description)) > 0 THEN
					ls_description := ls_Line_description;
				END IF;

				if (ls_string is NULL) then
					ls_string := ls_description || TO_CHAR(li_cost, '$9,999,999,990');
				else
					ls_string := ls_string || ';   ' || ls_description || TO_CHAR(li_cost, '$9,999,999,990');
				end if;

		END LOOP;
		CLOSE c_desc;
	END;
	return ls_string;
end;
/