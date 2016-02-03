-- Institutional Proposal
insert into krms_typ_t (typ_id, nm, nmspc_cd, srvc_nm, actv, ver_nbr)
values ('KC' || krms_typ_s.nextval, 'Institutional Proposal Java Function Term Service', 'KC-IP', 'institutionalProposalJavaFunctionKrmsTermService', 'Y', 1);

insert into krms_func_t (func_id, nmspc_cd, nm, desc_txt, rtrn_typ, typ_id, actv, ver_nbr)
values ('KC' || krms_func_s.nextval, 'KC-IP', 'doSponsorAndPrimeSponsorMatch', 'Check if Sponsor and Prime Sponsor are the same', 'java.lang.Boolean', (select typ_id from krms_typ_t where nm = 'Institutional Proposal Java Function Term Service'), 'Y', 1);
insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC' || krms_func_parm_s.nextval, 'InstitutionalProposal', 'Institutional Proposal BO', 'org.kuali.coeus.common.framework.sponsor.Sponsorable', (select func_id from krms_func_t where nm = 'doSponsorAndPrimeSponsorMatch' and nmspc_cd = 'KC-IP'), 1);

insert into krms_term_spec_t (term_spec_id, nm, typ, actv, ver_nbr, desc_txt, nmspc_cd)
values ('KC' || krms_term_spec_s.nextval, (select func_id from krms_func_t where nm = 'doSponsorAndPrimeSponsorMatch' and nmspc_cd = 'KC-IP'), 'java.lang.Boolean', 'Y', 1, 'Check if Sponsor and Prime Sponsor are the same', 'KC-IP');
insert into krms_term_t (term_id, term_spec_id, ver_nbr, desc_txt)
values ('KC' || krms_term_s.nextval, (select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'doSponsorAndPrimeSponsorMatch' and nmspc_cd = 'KC-IP')), '1', 'Check if Sponsor and Prime Sponsor are the same');

insert into krms_term_rslvr_t (term_rslvr_id, nmspc_cd, nm, typ_id, output_term_spec_id, actv, ver_nbr)
values ('KC' || krms_term_rslvr_s.nextval, 'KC-IP', 'Sponsor and Prime Sponsor Match Resolver', 'KC1001', (select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'doSponsorAndPrimeSponsorMatch' and nmspc_cd = 'KC-IP')), 'Y', 1);

insert into krms_cntxt_vld_term_spec_t (cntxt_term_spec_prereq_id, cntxt_id, term_spec_id, prereq)
values ('KC' || krms_cntxt_vld_term_spec_s.nextval, 'KC-IP-CONTEXT', (select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'doSponsorAndPrimeSponsorMatch' and nmspc_cd = 'KC-IP')), 'Y');
insert into krms_term_spec_ctgry_t (term_spec_id, ctgry_id)
values ((select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'doSponsorAndPrimeSponsorMatch' and nmspc_cd = 'KC-IP')), 'KC1016');

--

insert into krms_func_t (func_id, nmspc_cd, nm, desc_txt, rtrn_typ, typ_id, actv, ver_nbr)
values ('KC' || krms_typ_s.nextval, 'KC-IP', 'hasSpecialReviewOfType', 'Check if IP has a special review with the specified type', 'java.lang.Boolean', (select typ_id from krms_typ_t where nm = 'Institutional Proposal Java Function Term Service'), 'Y', 1);
insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC' || krms_func_parm_s.nextval, 'InstitutionalProposal', 'Institutional Proposal BO', 'org.kuali.kra.institutionalproposal.home.InstitutionalProposal', (select func_id from krms_func_t where nm = 'hasSpecialReviewOfType' and nmspc_cd = 'KC-IP'), 1);
insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC' || krms_func_parm_s.nextval, 'Special Review Type', 'Special Review Type Code or Description', 'java.lang.String', (select func_id from krms_func_t where nm = 'hasSpecialReviewOfType' and nmspc_cd = 'KC-IP'), 2);

insert into krms_term_spec_t (term_spec_id, nm, typ, actv, ver_nbr, desc_txt, nmspc_cd)
values ('KC' || krms_term_spec_s.nextval, (select func_id from krms_func_t where nm = 'hasSpecialReviewOfType' and nmspc_cd = 'KC-IP'), 'java.lang.Boolean', 'Y', 1, 'Check if IP has a special review with the specified type', 'KC-IP');

insert into krms_term_rslvr_t (term_rslvr_id, nmspc_cd, nm, typ_id, output_term_spec_id, actv, ver_nbr)
values ('KC' || krms_term_rslvr_s.nextval, 'KC-IP', 'IP Special Review Type Resolver', 'KC1001', (select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'hasSpecialReviewOfType' and nmspc_cd = 'KC-IP')), 'Y', 1);
insert into krms_term_rslvr_parm_spec_t (term_rslvr_parm_spec_id, term_rslvr_id, nm, ver_nbr)
values ('KC' || krms_term_rslvr_parm_spec_s.nextval, (select term_rslvr_id from krms_term_rslvr_t where output_term_spec_id = (select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'hasSpecialReviewOfType' and nmspc_cd = 'KC-IP'))), 'Special Review Type', 1);

insert into krms_cntxt_vld_term_spec_t (cntxt_term_spec_prereq_id, cntxt_id, term_spec_id, prereq)
values ('KC' || krms_cntxt_vld_term_spec_s.nextval, 'KC-IP-CONTEXT', (select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'hasSpecialReviewOfType' and nmspc_cd = 'KC-IP')), 'Y');
insert into krms_term_spec_ctgry_t (term_spec_id, ctgry_id)
values ((select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'hasSpecialReviewOfType' and nmspc_cd = 'KC-IP')), 'KC1016');

--

insert into krms_func_t (func_id, nmspc_cd, nm, desc_txt, rtrn_typ, typ_id, actv, ver_nbr)
values ('KC' || krms_func_s.nextval, 'KC-IP', 'isCurrentFiscalMonth', 'Check if IP has a fiscal month the same as the current fiscal month', 'java.lang.Boolean', (select typ_id from krms_typ_t where nm = 'Institutional Proposal Java Function Term Service'), 'Y', 1);
insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC' || krms_func_parm_s.nextval, 'InstitutionalProposal', 'Institutional Proposal BO', 'org.kuali.kra.institutionalproposal.home.InstitutionalProposal', (select func_id from krms_func_t where nm = 'isCurrentFiscalMonth' and nmspc_cd = 'KC-IP'), 1);

insert into krms_term_spec_t (term_spec_id, nm, typ, actv, ver_nbr, desc_txt, nmspc_cd)
values ('KC' || krms_term_spec_s.nextval, (select func_id from krms_func_t where nm = 'isCurrentFiscalMonth' and nmspc_cd = 'KC-IP'), 'java.lang.Boolean', 'Y', 1, 'Check if IP has a fiscal month the same as the current fiscal month', 'KC-IP');
insert into krms_term_t (term_id, term_spec_id, ver_nbr, desc_txt)
values ('KC' || krms_term_s.nextval, (select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'isCurrentFiscalMonth' and nmspc_cd = 'KC-IP')), '1', 'Check if IP has a fiscal month the same as the current fiscal month');

insert into krms_term_rslvr_t (term_rslvr_id, nmspc_cd, nm, typ_id, output_term_spec_id, actv, ver_nbr)
values ('KC' || krms_term_rslvr_s.nextval, 'KC-IP', 'IP Current Fiscal Month Resolver', 'KC1001', (select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'isCurrentFiscalMonth' and nmspc_cd = 'KC-IP')), 'Y', 1);

insert into krms_cntxt_vld_term_spec_t (cntxt_term_spec_prereq_id, cntxt_id, term_spec_id, prereq)
values ('KC' || krms_cntxt_vld_term_spec_s.nextval, 'KC-IP-CONTEXT', (select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'isCurrentFiscalMonth' and nmspc_cd = 'KC-IP')), 'Y');
insert into krms_term_spec_ctgry_t (term_spec_id, ctgry_id)
values ((select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'isCurrentFiscalMonth' and nmspc_cd = 'KC-IP')), 'KC1016');

-- Award
insert into krms_func_t (func_id, nmspc_cd, nm, desc_txt, rtrn_typ, typ_id, actv, ver_nbr)
values ('KC' || krms_func_s.nextval, 'KC-AWARD', 'doSponsorAndPrimeSponsorMatch', 'Check if Sponsor and Prime Sponsor are the same', 'java.lang.Boolean', (select typ_id from krms_typ_t where nm = 'Award Java Function Term Service'), 'Y', 1);
insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC' || krms_func_parm_s.nextval, 'award', 'Award BO', 'org.kuali.coeus.common.framework.sponsor.Sponsorable', (select func_id from krms_func_t where nm = 'doSponsorAndPrimeSponsorMatch' and nmspc_cd = 'KC-AWARD'), 1);

insert into krms_term_spec_t (term_spec_id, nm, typ, actv, ver_nbr, desc_txt, nmspc_cd)
values ('KC' || krms_term_spec_s.nextval, (select func_id from krms_func_t where nm = 'doSponsorAndPrimeSponsorMatch' and nmspc_cd = 'KC-AWARD'), 'java.lang.Boolean', 'Y', 1, 'Check if Sponsor and Prime Sponsor are the same', 'KC-AWARD');
insert into krms_term_t (term_id, term_spec_id, ver_nbr, desc_txt)
values ('KC' || krms_term_s.nextval, (select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'doSponsorAndPrimeSponsorMatch' and nmspc_cd = 'KC-AWARD')), '1', 'Check if Sponsor and Prime Sponsor are the same');

insert into krms_term_rslvr_t (term_rslvr_id, nmspc_cd, nm, typ_id, output_term_spec_id, actv, ver_nbr)
values ('KC' || krms_term_rslvr_s.nextval, 'KC-AWARD', 'Sponsor and Prime Sponsor Match Resolver', 'KC1001', (select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'doSponsorAndPrimeSponsorMatch' and nmspc_cd = 'KC-AWARD')), 'Y', 1);

insert into krms_cntxt_vld_term_spec_t (cntxt_term_spec_prereq_id, cntxt_id, term_spec_id, prereq)
values ('KC' || krms_cntxt_vld_term_spec_s.nextval, 'KC-AWARD-CONTEXT', (select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'doSponsorAndPrimeSponsorMatch' and nmspc_cd = 'KC-AWARD')), 'Y');
insert into krms_term_spec_ctgry_t (term_spec_id, ctgry_id)
values ((select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'doSponsorAndPrimeSponsorMatch' and nmspc_cd = 'KC-AWARD')), 'KC1010');

--

insert into krms_func_t (func_id, nmspc_cd, nm, desc_txt, rtrn_typ, typ_id, actv, ver_nbr)
values ('KC' || krms_func_s.nextval, 'KC-AWARD', 'checkPropertyValueForAnyPreviousVersion', 'Check if Property Value matches for any previous Award version', 'java.lang.Boolean', (select typ_id from krms_typ_t where nm = 'Award Java Function Term Service'), 'Y', 1);
insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC' || krms_func_parm_s.nextval, 'award', 'Award BO', 'org.kuali.coeus.common.framework.version.sequence.owner.SequenceOwner', (select func_id from krms_func_t where nm = 'checkPropertyValueForAnyPreviousVersion' and nmspc_cd = 'KC-AWARD'), 1);
insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC' || krms_func_parm_s.nextval, 'Property Name', 'Property to compare', 'java.lang.String', (select func_id from krms_func_t where nm = 'checkPropertyValueForAnyPreviousVersion' and nmspc_cd = 'KC-AWARD'), 2);
insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC' || krms_func_parm_s.nextval, 'Comparison Value', 'Value to compare', 'java.lang.String', (select func_id from krms_func_t where nm = 'checkPropertyValueForAnyPreviousVersion' and nmspc_cd = 'KC-AWARD'), 3);

insert into krms_term_spec_t (term_spec_id, nm, typ, actv, ver_nbr, desc_txt, nmspc_cd)
values ('KC' || krms_term_spec_s.nextval, (select func_id from krms_func_t where nm = 'checkPropertyValueForAnyPreviousVersion' and nmspc_cd = 'KC-AWARD'), 'java.lang.Boolean', 'Y', 1, 'Check Previous Version has Property matching Value', 'KC-AWARD');

insert into krms_term_rslvr_t (term_rslvr_id, nmspc_cd, nm, typ_id, output_term_spec_id, actv, ver_nbr)
values ('KC' || krms_term_rslvr_s.nextval, 'KC-AWARD', 'Previous Version Property Value Comparison Resolver', 'KC1001', (select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'checkPropertyValueForAnyPreviousVersion' and nmspc_cd = 'KC-AWARD')), 'Y', 1);
insert into krms_term_rslvr_parm_spec_t (term_rslvr_parm_spec_id, term_rslvr_id, nm, ver_nbr)
values ('KC' || krms_term_rslvr_parm_spec_s.nextval, (select term_rslvr_id from krms_term_rslvr_t where output_term_spec_id = (select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'checkPropertyValueForAnyPreviousVersion' and nmspc_cd = 'KC-AWARD'))), 'Property Name', 1);
insert into krms_term_rslvr_parm_spec_t (term_rslvr_parm_spec_id, term_rslvr_id, nm, ver_nbr)
values ('KC' || krms_term_rslvr_parm_spec_s.nextval, (select term_rslvr_id from krms_term_rslvr_t where output_term_spec_id = (select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'checkPropertyValueForAnyPreviousVersion' and nmspc_cd = 'KC-AWARD'))), 'Comparison Value', 1);

insert into krms_cntxt_vld_term_spec_t (cntxt_term_spec_prereq_id, cntxt_id, term_spec_id, prereq)
values ('KC' || krms_cntxt_vld_term_spec_s.nextval, 'KC-AWARD-CONTEXT', (select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'checkPropertyValueForAnyPreviousVersion' and nmspc_cd = 'KC-AWARD')), 'Y');
insert into krms_term_spec_ctgry_t (term_spec_id, ctgry_id)
values ((select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'checkPropertyValueForAnyPreviousVersion' and nmspc_cd = 'KC-AWARD')), 'KC1010');

--

insert into krms_func_t (func_id, nmspc_cd, nm, desc_txt, rtrn_typ, typ_id, actv, ver_nbr)
values ('KC' || krms_func_s.nextval, 'KC-AWARD', 'hasPropertyChangedThisVersion', 'Check if Property Value has changed since the previous Award version', 'java.lang.Boolean', (select typ_id from krms_typ_t where nm = 'Award Java Function Term Service'), 'Y', 1);
insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC' || krms_func_parm_s.nextval, 'award', 'Award BO', 'org.kuali.coeus.common.framework.version.sequence.owner.SequenceOwner', (select func_id from krms_func_t where nm = 'hasPropertyChangedThisVersion' and nmspc_cd = 'KC-AWARD'), 1);
insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC' || krms_func_parm_s.nextval, 'Property Name', 'Property to compare', 'java.lang.String', (select func_id from krms_func_t where nm = 'hasPropertyChangedThisVersion' and nmspc_cd = 'KC-AWARD'), 2);

insert into krms_term_spec_t (term_spec_id, nm, typ, actv, ver_nbr, desc_txt, nmspc_cd)
values ('KC' || krms_term_spec_s.nextval, (select func_id from krms_func_t where nm = 'hasPropertyChangedThisVersion' and nmspc_cd = 'KC-AWARD'), 'java.lang.Boolean', 'Y', 1, 'Check if Property Value has changed since the previous Award version', 'KC-AWARD');

insert into krms_term_rslvr_t (term_rslvr_id, nmspc_cd, nm, typ_id, output_term_spec_id, actv, ver_nbr)
values ('KC' || krms_term_rslvr_s.nextval, 'KC-AWARD', 'Has Property Changed Comparison Resolver', 'KC1001', (select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'hasPropertyChangedThisVersion' and nmspc_cd = 'KC-AWARD')), 'Y', 1);
insert into krms_term_rslvr_parm_spec_t (term_rslvr_parm_spec_id, term_rslvr_id, nm, ver_nbr)
values ('KC' || krms_term_rslvr_parm_spec_s.nextval, (select term_rslvr_id from krms_term_rslvr_t where output_term_spec_id = (select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'hasPropertyChangedThisVersion' and nmspc_cd = 'KC-AWARD'))), 'Property Name', 1);

insert into krms_cntxt_vld_term_spec_t (cntxt_term_spec_prereq_id, cntxt_id, term_spec_id, prereq)
values ('KC' || krms_cntxt_vld_term_spec_s.nextval, 'KC-AWARD-CONTEXT', (select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'hasPropertyChangedThisVersion' and nmspc_cd = 'KC-AWARD')), 'Y');
insert into krms_term_spec_ctgry_t (term_spec_id, ctgry_id)
values ((select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'hasPropertyChangedThisVersion' and nmspc_cd = 'KC-AWARD')), 'KC1010');

--

insert into krms_func_t (func_id, nmspc_cd, nm, desc_txt, rtrn_typ, typ_id, actv, ver_nbr)
values ('KC' || krms_func_s.nextval, 'KC-AWARD', 'checkCommentEntered', 'Check if Award has a Comment Entered of Type', 'java.lang.Boolean', (select typ_id from krms_typ_t where nm = 'Award Java Function Term Service'), 'Y', 1);
insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC' || krms_func_parm_s.nextval, 'award', 'Award BO', 'org.kuali.kra.award.home.Award', (select func_id from krms_func_t where nm = 'checkCommentEntered' and nmspc_cd = 'KC-AWARD'), 1);
insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC' || krms_func_parm_s.nextval, 'commentTypeCode', 'Comment Type Code', 'java.lang.String', (select func_id from krms_func_t where nm = 'checkCommentEntered' and nmspc_cd = 'KC-AWARD'), 2);

insert into krms_term_spec_t (term_spec_id, nm, typ, actv, ver_nbr, desc_txt, nmspc_cd)
values ('KC' || krms_term_spec_s.nextval, (select func_id from krms_func_t where nm = 'checkCommentEntered' and nmspc_cd = 'KC-AWARD'), 'java.lang.Boolean', 'Y', 1, 'Check if Award has a Comment Entered of Type', 'KC-AWARD');

insert into krms_term_rslvr_t (term_rslvr_id, nmspc_cd, nm, typ_id, output_term_spec_id, actv, ver_nbr)
values ('KC' || krms_term_rslvr_s.nextval, 'KC-AWARD', 'Award Comment Type Existence Resolver', 'KC1001', (select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'checkCommentEntered' and nmspc_cd = 'KC-AWARD')), 'Y', 1);
insert into krms_term_rslvr_parm_spec_t (term_rslvr_parm_spec_id, term_rslvr_id, nm, ver_nbr)
values ('KC' || krms_term_rslvr_parm_spec_s.nextval, (select term_rslvr_id from krms_term_rslvr_t where output_term_spec_id = (select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'checkCommentEntered' and nmspc_cd = 'KC-AWARD'))), 'commentTypeCode', 1);

insert into krms_cntxt_vld_term_spec_t (cntxt_term_spec_prereq_id, cntxt_id, term_spec_id, prereq)
values ('KC' || krms_cntxt_vld_term_spec_s.nextval, 'KC-AWARD-CONTEXT', (select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'checkCommentEntered' and nmspc_cd = 'KC-AWARD')), 'Y');
insert into krms_term_spec_ctgry_t (term_spec_id, ctgry_id)
values ((select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'checkCommentEntered' and nmspc_cd = 'KC-AWARD')), 'KC1010');

--

insert into krms_func_t (func_id, nmspc_cd, nm, desc_txt, rtrn_typ, typ_id, actv, ver_nbr)
values ('KC' || krms_func_s.nextval, 'KC-AWARD', 'hasSpecialReviewOfType', 'Check if Award has a special review with the specified type', 'java.lang.Boolean', (select typ_id from krms_typ_t where nm = 'Award Java Function Term Service'), 'Y', 1);
insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC' || krms_func_parm_s.nextval, 'award', 'Award BO', 'org.kuali.kra.award.home.Award', (select func_id from krms_func_t where nm = 'hasSpecialReviewOfType' and nmspc_cd = 'KC-AWARD'), 1);
insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC' || krms_func_parm_s.nextval, 'Special Review Type', 'Special Review Type Code or Description', 'java.lang.String', (select func_id from krms_func_t where nm = 'hasSpecialReviewOfType' and nmspc_cd = 'KC-AWARD'), 2);

insert into krms_term_spec_t (term_spec_id, nm, typ, actv, ver_nbr, desc_txt, nmspc_cd)
values ('KC' || krms_term_spec_s.nextval, (select func_id from krms_func_t where nm = 'hasSpecialReviewOfType' and nmspc_cd = 'KC-AWARD'), 'java.lang.Boolean', 'Y', 1, 'Check if Award has a special review with the specified type', 'KC-AWARD');

insert into krms_term_rslvr_t (term_rslvr_id, nmspc_cd, nm, typ_id, output_term_spec_id, actv, ver_nbr)
values ('KC' || krms_term_rslvr_s.nextval, 'KC-AWARD', 'Award Historical Special Review Resolver', 'KC1001', (select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'hasSpecialReviewOfType' and nmspc_cd = 'KC-AWARD')), 'Y', 1);
insert into krms_term_rslvr_parm_spec_t (term_rslvr_parm_spec_id, term_rslvr_id, nm, ver_nbr)
values ('KC' || krms_term_rslvr_parm_spec_s.nextval, (select term_rslvr_id from krms_term_rslvr_t where output_term_spec_id = (select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'hasSpecialReviewOfType' and nmspc_cd = 'KC-AWARD'))), 'Special Review Type', 1);

insert into krms_cntxt_vld_term_spec_t (cntxt_term_spec_prereq_id, cntxt_id, term_spec_id, prereq)
values ('KC' || krms_cntxt_vld_term_spec_s.nextval, 'KC-AWARD-CONTEXT', (select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'hasSpecialReviewOfType' and nmspc_cd = 'KC-AWARD')), 'Y');
insert into krms_term_spec_ctgry_t (term_spec_id, ctgry_id)
values ((select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'hasSpecialReviewOfType' and nmspc_cd = 'KC-AWARD')), 'KC1010');

-- Proposal Development
insert into krms_func_t (func_id, nmspc_cd, nm, desc_txt, rtrn_typ, typ_id, actv, ver_nbr)
values ('KC' || krms_func_s.nextval, 'KC-PD', 'doSponsorAndPrimeSponsorMatch', 'Check if Sponsor and Prime Sponsor are the same', 'java.lang.Boolean', (select typ_id from krms_typ_t where nm = 'ProposalDevelopment Java Function Term Service'), 'Y', 1);
insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC' || krms_func_parm_s.nextval, 'DevelopmentProposal', 'DevelopmentProposal', 'org.kuali.coeus.common.framework.sponsor.Sponsorable', (select func_id from krms_func_t where nm = 'doSponsorAndPrimeSponsorMatch' and nmspc_cd = 'KC-PD'), 1);

insert into krms_term_spec_t (term_spec_id, nm, typ, actv, ver_nbr, desc_txt, nmspc_cd)
values ('KC' || krms_term_spec_s.nextval, (select func_id from krms_func_t where nm = 'doSponsorAndPrimeSponsorMatch' and nmspc_cd = 'KC-PD'), 'java.lang.Boolean', 'Y', 1, 'Check if Sponsor and Prime Sponsor are the same', 'KC-PD');
insert into krms_term_t (term_id, term_spec_id, ver_nbr, desc_txt)
values ('KC' || krms_term_s.nextval, (select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'doSponsorAndPrimeSponsorMatch' and nmspc_cd = 'KC-PD')), '1', 'Check if Sponsor and Prime Sponsor are the same');

insert into krms_term_rslvr_t (term_rslvr_id, nmspc_cd, nm, typ_id, output_term_spec_id, actv, ver_nbr)
values ('KC' || krms_term_rslvr_s.nextval, 'KC-PD', 'Sponsor and Prime Sponsor Match Resolver', 'KC1001', (select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'doSponsorAndPrimeSponsorMatch' and nmspc_cd = 'KC-PD')), 'Y', 1);

insert into krms_cntxt_vld_term_spec_t (cntxt_term_spec_prereq_id, cntxt_id, term_spec_id, prereq)
values ('KC' || krms_cntxt_vld_term_spec_s.nextval, 'KC-PD-CONTEXT', (select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'doSponsorAndPrimeSponsorMatch' and nmspc_cd = 'KC-PD')), 'Y');
insert into krms_term_spec_ctgry_t (term_spec_id, ctgry_id)
values ((select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'doSponsorAndPrimeSponsorMatch' and nmspc_cd = 'KC-PD')), 'KC1001');

-- IRB
insert into krms_func_t (func_id, nmspc_cd, nm, desc_txt, rtrn_typ, typ_id, actv, ver_nbr)
values ('KC' || krms_func_s.nextval, 'KC-PROTOCOL', 'hasProtocolContainsAmendRenewModule', 'Check if Protocol has passed-in Amendment Section', 'java.lang.Boolean', (select typ_id from krms_typ_t where nm = 'IRB Java Function Term Service'), 'Y', 1);
insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC' || krms_func_parm_s.nextval, 'IrbProtocol', 'Irb Protocol BO', 'org.kuali.kra.protocol.ProtocolBase', (select func_id from krms_func_t where nm = 'hasProtocolContainsAmendRenewModule' and nmspc_cd = 'KC-PROTOCOL'), 1);
insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC' || krms_func_parm_s.nextval, 'Amendment Section', 'Amendment Section Code or Description', 'java.lang.String', (select func_id from krms_func_t where nm = 'hasProtocolContainsAmendRenewModule' and nmspc_cd = 'KC-PROTOCOL'), 2);

insert into krms_term_spec_t (term_spec_id, nm, typ, actv, ver_nbr, desc_txt, nmspc_cd)
values ('KC' || krms_term_spec_s.nextval, (select func_id from krms_func_t where nm = 'hasProtocolContainsAmendRenewModule' and nmspc_cd = 'KC-PROTOCOL'), 'java.lang.Boolean', 'Y', 1, 'Check Protocol has Amendment Section', 'KC-PROTOCOL');

insert into krms_term_rslvr_t (term_rslvr_id, nmspc_cd, nm, typ_id, output_term_spec_id, actv, ver_nbr)
values ('KC' || krms_term_rslvr_s.nextval, 'KC-PROTOCOL', 'Protocol has Amendment Section Resolver', 'KC1001', (select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'hasProtocolContainsAmendRenewModule' and nmspc_cd = 'KC-PROTOCOL')), 'Y', 1);
insert into krms_term_rslvr_parm_spec_t (term_rslvr_parm_spec_id, term_rslvr_id, nm, ver_nbr)
values ('KC' || krms_term_rslvr_parm_spec_s.nextval, (select term_rslvr_id from krms_term_rslvr_t where output_term_spec_id = (select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'hasProtocolContainsAmendRenewModule' and nmspc_cd = 'KC-PROTOCOL'))), 'Amendment Section', 1);

insert into krms_cntxt_vld_term_spec_t (cntxt_term_spec_prereq_id, cntxt_id, term_spec_id, prereq)
values ('KC' || krms_cntxt_vld_term_spec_s.nextval, 'KC-PROTOCOL-CONTEXT', (select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'hasProtocolContainsAmendRenewModule' and nmspc_cd = 'KC-PROTOCOL')), 'Y');
insert into krms_term_spec_ctgry_t (term_spec_id, ctgry_id)
values ((select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'hasProtocolContainsAmendRenewModule' and nmspc_cd = 'KC-PROTOCOL')), 'KC1004');

--

insert into krms_func_t (func_id, nmspc_cd, nm, desc_txt, rtrn_typ, typ_id, actv, ver_nbr)
values ('KC' || krms_func_s.nextval, 'KC-PROTOCOL', 'getProtocolParticipantTypeCount', 'How many Participants does a Protocol have of a passed-in Type', 'java.lang.Integer', (select typ_id from krms_typ_t where nm = 'IRB Java Function Term Service'), 'Y', 1);
insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC' || krms_func_parm_s.nextval, 'IrbProtocol', 'Irb Protocol BO', 'org.kuali.kra.irb.Protocol', (select func_id from krms_func_t where nm = 'getProtocolParticipantTypeCount' and nmspc_cd = 'KC-PROTOCOL'), 1);
insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC' || krms_func_parm_s.nextval, 'Participant Type Code or Description', 'Participant Type Code or Description', 'java.lang.String', (select func_id from krms_func_t where nm = 'getProtocolParticipantTypeCount' and nmspc_cd = 'KC-PROTOCOL'), 2);

insert into krms_term_spec_t (term_spec_id, nm, typ, actv, ver_nbr, desc_txt, nmspc_cd)
values ('KC' || krms_term_spec_s.nextval, (select func_id from krms_func_t where nm = 'getProtocolParticipantTypeCount' and nmspc_cd = 'KC-PROTOCOL'), 'java.lang.Integer', 'Y', 1, 'How many participants does Protocol have of type', 'KC-PROTOCOL');

insert into krms_term_rslvr_t (term_rslvr_id, nmspc_cd, nm, typ_id, output_term_spec_id, actv, ver_nbr)
values ('KC' || krms_term_rslvr_s.nextval, 'KC-PROTOCOL', 'Protocol participant type count Resolver', 'KC1001', (select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'getProtocolParticipantTypeCount' and nmspc_cd = 'KC-PROTOCOL')), 'Y', 1);
insert into krms_term_rslvr_parm_spec_t (term_rslvr_parm_spec_id, term_rslvr_id, nm, ver_nbr)
values ('KC' || krms_term_rslvr_parm_spec_s.nextval, (select term_rslvr_id from krms_term_rslvr_t where output_term_spec_id = (select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'getProtocolParticipantTypeCount' and nmspc_cd = 'KC-PROTOCOL'))), 'Participant Type Code or Description', 1);

insert into krms_cntxt_vld_term_spec_t (cntxt_term_spec_prereq_id, cntxt_id, term_spec_id, prereq)
values ('KC' || krms_cntxt_vld_term_spec_s.nextval, 'KC-PROTOCOL-CONTEXT', (select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'getProtocolParticipantTypeCount' and nmspc_cd = 'KC-PROTOCOL')), 'Y');
insert into krms_term_spec_ctgry_t (term_spec_id, ctgry_id)
values ((select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'getProtocolParticipantTypeCount' and nmspc_cd = 'KC-PROTOCOL')), 'KC1004');

--

insert into krms_func_t (func_id, nmspc_cd, nm, desc_txt, rtrn_typ, typ_id, actv, ver_nbr)
values ('KC' || krms_func_s.nextval, 'KC-PROTOCOL', 'hasProtocolContainsSponsorType', 'Check if Protocol has Funding Source with passed-in Sponsor Type', 'java.lang.Boolean', (select typ_id from krms_typ_t where nm = 'IRB Java Function Term Service'), 'Y', 1);
insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC' || krms_func_parm_s.nextval, 'IrbProtocol', 'Irb Protocol BO', 'org.kuali.kra.protocol.ProtocolBase', (select func_id from krms_func_t where nm = 'hasProtocolContainsSponsorType' and nmspc_cd = 'KC-PROTOCOL'), 1);
insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC' || krms_func_parm_s.nextval, 'Sponsor Type Code or Description', 'Sponsor Type Code or Description', 'java.lang.String', (select func_id from krms_func_t where nm = 'hasProtocolContainsSponsorType' and nmspc_cd = 'KC-PROTOCOL'), 2);

insert into krms_term_spec_t (term_spec_id, nm, typ, actv, ver_nbr, desc_txt, nmspc_cd)
values ('KC' || krms_term_spec_s.nextval, (select func_id from krms_func_t where nm = 'hasProtocolContainsSponsorType' and nmspc_cd = 'KC-PROTOCOL'), 'java.lang.Boolean', 'Y', 1, 'Check Protocol has Funding Source with Sponsor Type', 'KC-PROTOCOL');

insert into krms_term_rslvr_t (term_rslvr_id, nmspc_cd, nm, typ_id, output_term_spec_id, actv, ver_nbr)
values ('KC' || krms_term_rslvr_s.nextval, 'KC-PROTOCOL', 'Protocol funding source sponsor type Resolver', 'KC1001', (select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'hasProtocolContainsSponsorType' and nmspc_cd = 'KC-PROTOCOL')), 'Y', 1);
insert into krms_term_rslvr_parm_spec_t (term_rslvr_parm_spec_id, term_rslvr_id, nm, ver_nbr)
values ('KC' || krms_term_rslvr_parm_spec_s.nextval, (select term_rslvr_id from krms_term_rslvr_t where output_term_spec_id = (select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'hasProtocolContainsSponsorType' and nmspc_cd = 'KC-PROTOCOL'))), 'Sponsor Type Code or Description', 1);

insert into krms_cntxt_vld_term_spec_t (cntxt_term_spec_prereq_id, cntxt_id, term_spec_id, prereq)
values ('KC' || krms_cntxt_vld_term_spec_s.nextval, 'KC-PROTOCOL-CONTEXT', (select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'hasProtocolContainsSponsorType' and nmspc_cd = 'KC-PROTOCOL')), 'Y');
insert into krms_term_spec_ctgry_t (term_spec_id, ctgry_id)
values ((select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'hasProtocolContainsSponsorType' and nmspc_cd = 'KC-PROTOCOL')), 'KC1004');

--

insert into krms_func_t (func_id, nmspc_cd, nm, desc_txt, rtrn_typ, typ_id, actv, ver_nbr)
values ('KC' || krms_func_s.nextval, 'KC-PROTOCOL', 'hasBaseProtocolHasLastApprovalDate', 'Check if Base Protocol has a Last Approval Date', 'java.lang.Boolean', (select typ_id from krms_typ_t where nm = 'IRB Java Function Term Service'), 'Y', 1);
insert into krms_func_parm_t (func_parm_id, nm, desc_txt, typ, func_id, seq_no)
values ('KC' || krms_func_parm_s.nextval, 'IrbProtocol', 'Irb Protocol BO', 'org.kuali.kra.protocol.ProtocolBase', (select func_id from krms_func_t where nm = 'hasBaseProtocolHasLastApprovalDate' and nmspc_cd = 'KC-PROTOCOL'), 1);

insert into krms_term_spec_t (term_spec_id, nm, typ, actv, ver_nbr, desc_txt, nmspc_cd)
values ('KC' || krms_term_spec_s.nextval, (select func_id from krms_func_t where nm = 'hasBaseProtocolHasLastApprovalDate' and nmspc_cd = 'KC-PROTOCOL'), 'java.lang.Boolean', 'Y', 1, 'Check if Base Protocol has Last Approval Date', 'KC-PROTOCOL');
insert into krms_term_t (term_id, term_spec_id, ver_nbr, desc_txt)
values ('KC' || krms_term_s.nextval, (select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'hasBaseProtocolHasLastApprovalDate' and nmspc_cd = 'KC-PROTOCOL')), 1, 'Check if Base Protocol has Last Approval Date');

insert into krms_term_rslvr_t (term_rslvr_id, nmspc_cd, nm, typ_id, output_term_spec_id, actv, ver_nbr)
values ('KC' || krms_term_rslvr_s.nextval, 'KC-PROTOCOL', 'Base Protocol has Last Approval Date Resolver', 'KC1001', (select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'hasBaseProtocolHasLastApprovalDate' and nmspc_cd = 'KC-PROTOCOL')), 'Y', 1);

insert into krms_cntxt_vld_term_spec_t (cntxt_term_spec_prereq_id, cntxt_id, term_spec_id, prereq)
values ('KC' || krms_cntxt_vld_term_spec_s.nextval, 'KC-PROTOCOL-CONTEXT', (select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'hasBaseProtocolHasLastApprovalDate' and nmspc_cd = 'KC-PROTOCOL')), 'Y');
insert into krms_term_spec_ctgry_t (term_spec_id, ctgry_id)
values ((select term_spec_id from krms_term_spec_t where nm = (select func_id from krms_func_t where nm = 'hasBaseProtocolHasLastApprovalDate' and nmspc_cd = 'KC-PROTOCOL')), 'KC1004');