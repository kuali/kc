create or replace function fn_get_nsf_checklist_answer
			(as_proposal_number osp$eps_proposal.proposal_number%type,
			as_question_id number) 
			return varchar2
is

ls_yes varchar2(3) := 'Yes';
ls_no varchar2(2) := 'No';
ls_NA varchar2(25) := 'NotApplicable';

ls_proposal_type_code osp$eps_proposal.proposal_type_code%type;
ls_ynq_answer osp$eps_prop_ynq.answer%type;
li_narrative_type_code osp$narrative.narrative_type_code%type;
li_module_number osp$narrative_pdf.module_number%type;
li_abstract_type_code osp$eps_prop_abstract.abstract_type_code%type;

cursor cur_narr_pdf is
	select module_number 
	from osp$narrative_pdf
	where proposal_number = as_proposal_number;
	
cursor cur_prop_abstracts is
	select abstract_type_code
	from osp$eps_prop_abstract
	where proposal_number = as_proposal_number;

begin

	--NSF Cover page completed.
	if as_question_id = 1
	then	
		return ls_yes;
	end if;

	-- Is this a renewal?
	if as_question_id = 2
	then
		select proposal_type_code into ls_proposal_type_code
		from osp$eps_proposal
		where proposal_number = as_proposal_number;

		if ls_proposal_type_code in (5,8)
		then return ls_yes;
		else return ls_NA;
		end if;
	end if;

	-- Is this a full application related to a previously submitted preliminary application?
	if as_question_id = 3
	then
		begin
			select answer into ls_ynq_answer
			from osp$eps_prop_ynq
			where proposal_number = as_proposal_number
			and question_id = '21';
		exception 
			when NO_DATA_FOUND then
				return ls_NA;
		end;
		
		if
			ls_ynq_answer = 'Y'
		then
			return ls_yes;
		else
			return ls_NA;
		end if;
	end if;
	
	-- On SF424, continuation is not a valid NSF proposal type, and should not be checked.
	if as_question_id = 4
	then
		select proposal_type_code into ls_proposal_type_code
		from osp$eps_proposal
		where proposal_number = as_proposal_number;

		if ls_proposal_type_code in (2,3)
		then return ls_no;
		else return ls_yes;
		end if;
	end if;
	
	
	-- Is the application certified?
	if as_question_id = 5
	then
		begin
			select answer into ls_ynq_answer
			from osp$eps_prop_ynq
			where proposal_number = as_proposal_number
			and question_id = 'FG';
		exception
			when NO_DATA_FOUND then
				return ls_yes;
		end;
		
		if
			ls_ynq_answer = 'Y'
		then
			return ls_yes;
		else
			return ls_no;
		end if;
	end if;
	
	-- Is RRPerformanceSite done?
	if as_question_id = 6
	then
		return ls_yes;
	end if;
	
	
	-- Is RROtherInfo done?
	if as_question_id = 7
	then
		return ls_yes;
	end if;
	
	
	-- Is Project Summary included?
	if as_question_id = 8
	then
		open cur_narr_pdf;
		
		loop
			fetch cur_narr_pdf into li_module_number;
			exit when cur_narr_pdf%notfound;
			
			select narrative_type_code
			into li_narrative_type_code
			from osp$narrative
			where proposal_number = as_proposal_number
			and module_number = li_module_number;
			
			if li_narrative_type_code = 5
			then
				return ls_yes;
			end if;
		
		end loop;
		return ls_no;		
		close cur_narr_pdf;
	end if;
	
	-- Is narrative included?
	if as_question_id = 9
	then
		open cur_narr_pdf;
		
		loop
			fetch cur_narr_pdf into li_module_number;
			exit when cur_narr_pdf%notfound;
			
			select narrative_type_code
			into li_narrative_type_code
			from osp$narrative
			where proposal_number = as_proposal_number
			and module_number = li_module_number;
			
			if li_narrative_type_code = 1
			then
				return ls_yes;
			end if;
		
		end loop;
		return ls_no;		
		close cur_narr_pdf;
	end if;
	
	-- Does narrative include merit review criteria?
	if as_question_id = 10
	then
		return ls_yes;
	end if;
	
	
	-- Narrative should not include URLs
	if as_question_id = 11
	then
		return ls_yes;
	end if;
	
	-- Is necessary info included regarding prior NSF support?
	if as_question_id = 12
	then
		return ls_yes;
	end if;		
	
	-- Does the narrative include necessary HR info required for renewals?
	if as_question_id = 13
	then
		select proposal_type_code into ls_proposal_type_code
		from osp$eps_proposal
		where proposal_number = as_proposal_number;

		if ls_proposal_type_code in (5,8)
		then return ls_yes;
		else return ls_NA;
		end if;
	end if;	 

	-- Is the bibliography included?
	if as_question_id = 14
	then
		open cur_narr_pdf;
		
		loop
			fetch cur_narr_pdf into li_module_number;
			exit when cur_narr_pdf%notfound;
			
			select narrative_type_code
			into li_narrative_type_code
			from osp$narrative
			where proposal_number = as_proposal_number
			and module_number = li_module_number;
			
			if li_narrative_type_code = 4
			then
				return ls_yes;
			end if;
		
		end loop;
		return ls_no;		
		close cur_narr_pdf;	
	end if;
	
	-- Is the facilities pdf included?
	if as_question_id = 15
	then
		open cur_narr_pdf;
		
		loop
			fetch cur_narr_pdf into li_module_number;
			exit when cur_narr_pdf%notfound;
			
			select narrative_type_code
			into li_narrative_type_code
			from osp$narrative
			where proposal_number = as_proposal_number
			and module_number = li_module_number;
			
			if li_narrative_type_code = 2
			then
				return ls_yes;
			end if;
		
		end loop;
		return ls_no;		
		close cur_narr_pdf;	
	end if;
	
	-- Is the equipment pdf included?
	if as_question_id = 16
	then
		open cur_narr_pdf;
		
		loop
			fetch cur_narr_pdf into li_module_number;
			exit when cur_narr_pdf%notfound;
			
			select narrative_type_code
			into li_narrative_type_code
			from osp$narrative
			where proposal_number = as_proposal_number
			and module_number = li_module_number;
			
			if li_narrative_type_code = 3
			then
				return ls_yes;
			end if;
		
		end loop;
		return ls_no;		
		close cur_narr_pdf;	
	end if;
	
	-- Is there supplementary documentation?
	if as_question_id = 17
	then
		open cur_narr_pdf;
		
		loop
			fetch cur_narr_pdf into li_module_number;
			exit when cur_narr_pdf%notfound;
			
			select narrative_type_code
			into li_narrative_type_code
			from osp$narrative
			where proposal_number = as_proposal_number
			and module_number = li_module_number;
			
			if li_narrative_type_code = 15
			then
				return ls_yes;
			end if;
		
		end loop;
		return ls_NA;		
		close cur_narr_pdf;	
	end if;	
	
	-- Any additional items specific to NSF Program solicitation have been completed
	if as_question_id = 18
	then
		return ls_yes;
	end if;
	
	-- RRKeyPerson is done.
	if as_question_id = 19
	then
		return ls_yes;
	end if;
	
	-- BioSketches are atached.
	if as_question_id = 20
	then
		return ls_yes;
	end if;
	
	-- Current Pending reports are included.
	if as_question_id = 21
	then
		return ls_yes;
	end if;	
	
	-- RRPersonalData forms are done.
	if as_question_id = 22
	then
		return ls_yes;
	end if;		

	-- RRBudget form is done.
	if as_question_id = 23
	then
		return ls_yes;
	end if;	
	
	-- Is budget justification included?
	if as_question_id = 24
	then
		open cur_narr_pdf;
		
		loop
			fetch cur_narr_pdf into li_module_number;
			exit when cur_narr_pdf%notfound;
			
			select narrative_type_code
			into li_narrative_type_code
			from osp$narrative
			where proposal_number = as_proposal_number
			and module_number = li_module_number;
			
			if li_narrative_type_code = 7
			then
				return ls_yes;
			end if;
		
		end loop;
		return ls_NA;		
		close cur_narr_pdf;	
	end if;	
	
	-- RRBudget Cost Sharing
	if as_question_id = 25
	then
		return ls_yes;
	end if;	
	
	-- NSF Cover Page done.
	if as_question_id = 26
	then
		return ls_yes;
	end if;
	
	-- NSF Unit info on NSF Cover Page
	if as_question_id = 27
	then
		return ls_yes;
	end if;
	
	-- Other NSF info included
	if as_question_id = 28
	then
		return ls_yes;
	end if;	
	
	-- SF LL Lobbying Disclosure stream included?
	if as_question_id = 29
	then
		return ls_NA;
	end if;
	
	-- Deviation authorization included?
	if as_question_id = 30
	then
		open cur_prop_abstracts;
		
		loop
		
		
			fetch cur_prop_abstracts into li_abstract_type_code;
			exit when cur_prop_abstracts%notfound;
				
			
			if li_abstract_type_code = 15
			then
				return ls_yes;
			end if;
						
		
		end loop;
		close cur_prop_abstracts;
		return ls_NA;		
	end if;	
	
	-- Does this application include an NSF Fast Lane registration?
	if as_question_id = 31
	then
		return ls_NA;
	end if;	
	
	-- Is a list of suggested reviewers included in the application?
	if as_question_id = 32
	then
		
		open cur_prop_abstracts;
		loop
		
		
			fetch cur_prop_abstracts into li_abstract_type_code;
			exit when cur_prop_abstracts%notfound;
				
			
			if li_abstract_type_code in (12, 14)
			then
				return ls_yes;
			end if;
						
		
		end loop;
		close cur_prop_abstracts;
		return ls_NA;		
	end if;	

end;
/