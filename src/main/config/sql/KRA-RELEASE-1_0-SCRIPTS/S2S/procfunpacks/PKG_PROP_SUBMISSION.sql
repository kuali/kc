CREATE OR REPLACE package PKG_PROP_SUBMISSION as

procedure GET_PROPOSAL_DETAILS
	(as_proposal_number in osp$eps_proposal.proposal_number%type,
	 cur_type IN OUT result_sets.cur_generic);

procedure GET_SPECIAL_REVIEW_DETAILS
          (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           cur_type IN OUT result_sets.cur_generic) ;

procedure GET_INVESTIGATOR_DETAILS
	  (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           cur_type IN OUT result_sets.cur_generic) ;

procedure GET_INV_CREDIT_DETAILS
          (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           cur_type IN OUT result_sets.cur_generic);

procedure get_prop_units (
						as_proposal_num in OSP$EPS_PROP_UNITS.proposal_number%type,
						cur_type IN OUT result_sets.cur_generic);

end;
/

CREATE OR REPLACE package body PKG_PROP_SUBMISSION as

procedure GET_PROPOSAL_DETAILS
	(as_proposal_number in osp$eps_proposal.proposal_number%type,
	 cur_type IN OUT result_sets.cur_generic)
is
  begin
  	   	open cur_type for
			SELECT
			prop.proposal_number,
			bus_contact.full_name as bus_contact_name,
			bus_contact.full_name ||
				decode(bus_contact.email_address,NULL,
				decode(bus_contact.office_phone,NULL,'',' ('||bus_contact.office_phone||')'),
				' ('||bus_contact.email_address||decode(bus_contact.office_phone,NULL,')',
				', '||bus_contact.office_phone||')'))
				as bus_contact_name_and_info,
			prop_units.unit_number || '  ' || unit.unit_name as lead_unit,
			prop.title,
			spon.sponsor_name,
			prime.sponsor_name as prime_sponsor_name,
			inv.person_name as pi_name,
			inv.person_name ||
				decode(inv_details.email_address,NULL,
				decode(inv_details.office_phone,NULL,'',' ('||inv_details.office_phone||')'),
				' ('||inv_details.email_address||decode(inv_details.office_phone,NULL,')',
				', '||inv_details.office_phone||')'))
				as pi_name_and_info,
			to_char(prop.requested_start_date_initial,'MM/DD/YYYY') || ' - ' ||
			    to_char(prop.requested_end_date_initial,'MM/DD/YYYY') as project_period,
			prop_type.description as proposal_type,
			act_type.description as activity_type,
			opp.description as notice_of_opp,
			prop.program_announcement_number as program_number,
			prop.program_announcement_title as program_title,
			decode(budget.final_version_flag,NULL,'*** No final budget ***',
				to_char(round(budget.total_cost),'FM$999,999,990')) as sponsor_cost,
			to_char(round(budget.cost_sharing_amount),'FM$999,999,990') as cost_sharing,
			comments.abstract as other_comments,
				--Truncate other_comments to 180 characters. If length>180, append '...' to
				--indicate that more information was cut off for the form.
			to_char(prop.deadline_date, 'mm/dd/yyyy') || decode(prop.deadline_type,
				'P',' (Postmark)','R',' (Receipt)') as sponsor_deadline,
			decode(prop.mail_type,'E','Electronic','R','Regular','D','DHL') as submission_method,
			prop.number_of_copies,
			decode(rolodex.rolodex_id, NULL, '',
			    (decode(rolodex.last_name,NULL,'',rolodex.last_name) ||
						decode(rolodex.first_name,NULL,'',', '||rolodex.first_name)||
						decode(rolodex.middle_name,NULL,'',', '||rolodex.middle_name)||
					    decode(rolodex.email_address,NULL,'',' ('||rolodex.email_address ||') ')||
						decode(rolodex.phone_number,NULL,'',rolodex.phone_number)|| chr(13)) ||
			    decode(rolodex.organization,NULL,'',rolodex.organization || chr(13)) ||
			    decode(rolodex.address_line_1,NULL,'',rolodex.address_line_1 || chr(13)) ||
			    decode(rolodex.address_line_2,NULL,'',rolodex.address_line_2 || chr(13)) ||
			    decode(rolodex.address_line_3,NULL,'',rolodex.address_line_3 || chr(13)) ||
			    rolodex.city || ', ' || rolodex.state || ' ' || rolodex.postal_code || ' ' ||
			    rolodex.country_code) as sponsor_address,
			prop.mail_description,
			mailinfo.abstract as addl_mailing_instructions,
			decode(ynq_1.answer,'Y','Yes','N','No','X','N/A') as confidential_info,
			decode(ynq_2.answer,'Y','Yes','N','No','X','N/A') as space_available,
			decode(ynq_3.answer,'Y','Yes','N','No','X','N/A') as equip_available,
			ynq_1.explanation as confidential_info_expl,
			ynq_2.explanation as space_available_expl,
			ynq_3.explanation as equip_available_expl,

				--For above three fields (ynq_1, 2, 3) also concatenate ' - '||ynq_X.explanation
				--to the above decode result, if ynq_X.explanation is not null. The explanation
				--field is a LONG data type. Does this conversion require a PL/SQL function...?
			decode(center1_unit.unit_number,NULL,cust_center1.column_value,
			    cust_center1.column_value || ' - ' || center1_unit.unit_name) || chr(10) ||
			decode(center2_unit.unit_number,NULL,cust_center2.column_value,
			    cust_center2.column_value || ' - ' || center2_unit.unit_name) || chr(10) ||
			decode(center3_unit.unit_number,NULL,cust_center3.column_value,
			    cust_center3.column_value || ' - ' || center3_unit.unit_name) || chr(10) ||
			decode(center4_unit.unit_number,NULL,cust_center4.column_value,
			    cust_center4.column_value || ' - ' || center4_unit.unit_name) || chr(10) ||
			decode(center5_unit.unit_number,NULL,cust_center5.column_value,
			    cust_center5.column_value || ' - ' || center5_unit.unit_name) as center_institute,
			cust_itap1.column_value as computation_time,
			cust_itap2.column_value as data_storage,
			cust_itap3.column_value as tech_funding,
			decode(cust_coi.column_value,
			    'No', 'does not require', 'Yes', 'does require', '__________')
			    as conflict_of_interest
			FROM
			osp$eps_proposal prop,
			osp$eps_prop_custom_data cust_bus_contact,
			osp$person bus_contact,
			osp$eps_prop_units prop_units,
			osp$unit unit,
			osp$sponsor spon,
			osp$sponsor prime,
			osp$eps_prop_investigators inv,
			osp$person inv_details,
			osp$proposal_type prop_type,
			osp$activity_type act_type,
			osp$notice_of_opportunity opp,
			osp$budget budget,
			osp$eps_prop_abstract comments,
			osp$rolodex rolodex,
			osp$eps_prop_abstract mailinfo,
			osp$eps_prop_ynq ynq_1,
			osp$eps_prop_ynq ynq_2,
			osp$eps_prop_ynq ynq_3,
			osp$eps_prop_custom_data cust_center1,
			osp$eps_prop_custom_data cust_center2,
			osp$eps_prop_custom_data cust_center3,
			osp$eps_prop_custom_data cust_center4,
			osp$eps_prop_custom_data cust_center5,
			osp$unit center1_unit,
			osp$unit center2_unit,
			osp$unit center3_unit,
			osp$unit center4_unit,
			osp$unit center5_unit,
			osp$eps_prop_custom_data cust_itap1,
			osp$eps_prop_custom_data cust_itap2,
			osp$eps_prop_custom_data cust_itap3,
			osp$eps_prop_custom_data cust_coi
			WHERE
			prop.proposal_number=prop_units.proposal_number(+) AND
			prop.proposal_number=cust_bus_contact.proposal_number(+) AND
			cust_bus_contact.column_name(+)='BUSINESS_CONTACT' AND
			cust_bus_contact.column_value=bus_contact.person_id(+) AND
			prop_units.lead_unit_flag(+)='Y' AND
			prop_units.unit_number=unit.unit_number(+) AND
			prop.sponsor_code=spon.sponsor_code(+) AND
			prop.prime_sponsor_code=prime.sponsor_code(+) AND
			prop.proposal_number=inv.proposal_number(+) AND
			inv.principal_investigator_flag(+)='Y' AND
			inv.person_id=inv_details.person_id(+) AND
			prop.proposal_type_code=prop_type.proposal_type_code(+) AND
			prop.activity_type_code=act_type.activity_type_code(+) AND
			prop.notice_of_opportunity_code=opp.notice_of_opportunity_code(+) AND
			prop.proposal_number=comments.proposal_number(+) AND
			comments.abstract_type_code(+)=17 AND
			prop.mailing_address_id=rolodex.rolodex_id(+) AND
			prop.proposal_number=mailinfo.proposal_number(+) AND
			mailinfo.abstract_type_code(+)=16 AND
			prop.proposal_number=budget.proposal_number(+) AND
			budget.final_version_flag(+)='Y' AND
			prop.proposal_number=ynq_1.proposal_number(+) AND ynq_1.question_id(+)='P-01' AND
			prop.proposal_number=ynq_2.proposal_number(+) AND ynq_2.question_id(+)='P-02' AND
			prop.proposal_number=ynq_3.proposal_number(+) AND ynq_3.question_id(+)='P-03' AND
			prop.proposal_number=cust_center1.proposal_number(+) AND cust_center1.column_name(+)='CENTER_INSTITUTE_CODE' AND
			prop.proposal_number=cust_center2.proposal_number(+) AND cust_center2.column_name(+)='CENTER_INSTITUTE_CODE_2' AND
			prop.proposal_number=cust_center3.proposal_number(+) AND cust_center3.column_name(+)='CENTER_INSTITUTE_CODE_3' AND
			prop.proposal_number=cust_center4.proposal_number(+) AND cust_center4.column_name(+)='CENTER_INSTITUTE_CODE_4' AND
			prop.proposal_number=cust_center5.proposal_number(+) AND cust_center5.column_name(+)='CENTER_INSTITUTE_CODE_5' AND
			cust_center1.column_value=center1_unit.unit_number(+) AND
			cust_center2.column_value=center2_unit.unit_number(+) AND
			cust_center3.column_value=center3_unit.unit_number(+) AND
			cust_center4.column_value=center4_unit.unit_number(+) AND
			cust_center5.column_value=center5_unit.unit_number(+) AND
			prop.proposal_number=cust_itap1.proposal_number(+) AND cust_itap1.column_name(+)='ITAP_FLAG_1' AND
			prop.proposal_number=cust_itap2.proposal_number(+) AND cust_itap2.column_name(+)='ITAP_FLAG_2' AND
			prop.proposal_number=cust_itap3.proposal_number(+) AND cust_itap3.column_name(+)='ITAP_FLAG_3' AND
			prop.proposal_number=cust_coi.proposal_number(+) AND cust_coi.column_name(+)='CONFLICT_OF_INTEREST' AND
			prop.proposal_number=as_proposal_number;

  end;
procedure GET_SPECIAL_REVIEW_DETAILS
	(as_proposal_number in osp$eps_proposal.proposal_number%type,
	 cur_type IN OUT result_sets.cur_generic)
is
  begin
    open cur_type for
	select
		prop_rev.proposal_number,
		rev.description as special_review,
		appr.description as approval_status,
		prop_rev.protocol_number
		from
		osp$eps_prop_special_review prop_rev,
		osp$special_review rev,
		osp$sp_rev_approval_type appr
		where
		prop_rev.special_review_code=rev.special_review_code(+) AND
		prop_rev.approval_type_code=appr.approval_type_code(+) AND
		proposal_number=as_proposal_number
		order by
		prop_rev.special_review_number;
  end;
procedure GET_INVESTIGATOR_DETAILS
	(as_proposal_number in osp$eps_proposal.proposal_number%type,
	 cur_type IN OUT result_sets.cur_generic)
is
  begin
    open cur_type for
    select
		inv.person_id,
		inv.person_name,
		inv.person_name ||
			decode(psn.email_address,NULL,
			decode(psn.office_phone,NULL,'',' ('||psn.office_phone||')'),
			' ('||psn.email_address||decode(psn.office_phone,NULL,')',
			', '||psn.office_phone||')'))
			as person_name_and_info,
		inv.principal_investigator_flag as pi_flag, cred.credit
		from
		osp$eps_proposal prop,
		osp$eps_prop_investigators inv,
		osp$eps_prop_per_credit_split cred,
		osp$person psn
		where
		prop.proposal_number=inv.proposal_number(+) AND
		inv.proposal_number=cred.proposal_number(+) AND
		inv.person_id=cred.person_id(+) AND
		cred.inv_credit_type_code(+)=1 AND
		inv.person_id=psn.person_id(+) AND
		prop.proposal_number=as_proposal_number
		order by
		inv.principal_investigator_flag desc,
		inv.person_name;
   end;
procedure GET_INV_CREDIT_DETAILS
	(as_proposal_number in osp$eps_proposal.proposal_number%type,
	 cur_type IN OUT result_sets.cur_generic)
is
  begin
    open cur_type for
	select
	prop.proposal_number,
	inv.person_id,
	inv.principal_investigator_flag as pi_flag,
	cred1.unit_number || ' - ' || substr(unit.unit_name,1,30) ||
		decode(sign(length(unit.unit_name)-30),-1,'','...')
	as unit,
	Nvl(to_char(cred1.credit),' --') as primary,
	Nvl(to_char(cred2.credit),' --') as center_institute
	from
	osp$eps_proposal prop,
	osp$eps_prop_investigators inv,
	(select proposal_number, person_id, unit_number, credit
	 from osp$eps_prop_unit_credit_split
	 where inv_credit_type_code=1) cred1,
	(select proposal_number, person_id, unit_number, credit
	 from osp$eps_prop_unit_credit_split
	 where inv_credit_type_code=2) cred2,
	osp$unit unit
	where
	prop.proposal_number=inv.proposal_number(+) AND
	inv.proposal_number=cred1.proposal_number(+) AND
	inv.proposal_number=cred2.proposal_number(+) AND
	inv.person_id=cred1.person_id(+) AND
	inv.person_id=cred2.person_id(+) AND
	cred1.unit_number=cred2.unit_number AND
	cred1.unit_number=unit.unit_number AND
	prop.proposal_number=as_proposal_number;
   end;
procedure get_prop_units (
						as_proposal_num in OSP$EPS_PROP_UNITS.proposal_number%type,
						cur_type IN OUT result_sets.cur_generic) is
  begin
   open cur_type for
	select 	b.UNIT_NUMBER,
			b.PROPOSAL_NUMBER,
         	b.LEAD_UNIT_FLAG,
			a.UNIT_NAME
	from OSP$EPS_PROP_UNITS b,OSP$UNIT a
	where b.PROPOSAL_NUMBER = as_proposal_num and
		  ( b.UNIT_NUMBER = a.UNIT_NUMBER  and b.LEAD_UNIT_FLAG = 'Y' )
   UNION
   select b.UNIT_NUMBER,
   		  b.PROPOSAL_NUMBER,
          b.LEAD_UNIT_FLAG,
		  a.UNIT_NAME
   from OSP$EPS_PROP_UNITS b,OSP$UNIT a
   where b.PROPOSAL_NUMBER = as_proposal_num and
   		 ( b.UNIT_NUMBER = a.UNIT_NUMBER AND b.LEAD_UNIT_FLAG = 'N'
		   				   AND b.unit_number not in (select unit_number from OSP$EPS_PROP_UNITS
						   	   				 where PROPOSAL_NUMBER = as_proposal_num  and LEAD_UNIT_FLAG = 'Y' ));

	end;
end;
/

