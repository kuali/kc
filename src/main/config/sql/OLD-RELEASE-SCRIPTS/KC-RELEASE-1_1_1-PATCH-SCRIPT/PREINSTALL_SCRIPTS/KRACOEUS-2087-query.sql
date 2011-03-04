set feedback off
spool install_kc_release-1_1_1-Patch_SpecialReview.log

PROMPT The following proposal documents have Special Review items that are in an invalid state. 
PROMPT If they are not corrected before the patch is applied, they will automatically be corrected.
PROMPT
PROMPT Items with "approval date when not approved" will remove the approval date
PROMPT Items with "approval date before application date" will set the approval date to the application date
PROMPT Items with "expiration date before approval date" will set the expiration date to the approval date
PROMPT Items with "expiration date before application date" will set the expiration date to the application date
PROMPT

select p.document_number, p.proposal_number, p.title, 'approval date when not approved' as ERROR
from en_doc_hdr_t d, eps_proposal p, eps_prop_special_review s
where p.proposal_number = s.proposal_number 
and p.document_number = d.doc_hdr_id 
and d.doc_rte_stat_cd = 'S'
and s.approval_type_code <> '2' and s.approval_date is not null
union
select p.document_number, p.proposal_number, p.title, 'approval date before application date' as ERROR
from en_doc_hdr_t d, eps_proposal p, eps_prop_special_review s
where p.proposal_number = s.proposal_number 
and p.document_number = d.doc_hdr_id 
and d.doc_rte_stat_cd = 'S'
and s.application_date is not null and s.approval_date is not null and s.approval_date < s.application_date
union
select p.document_number, p.proposal_number, p.title, 'expiration date before approval date' as ERROR
from en_doc_hdr_t d, eps_proposal p, eps_prop_special_review s
where p.proposal_number = s.proposal_number 
and p.document_number = d.doc_hdr_id 
and d.doc_rte_stat_cd = 'S'
and s.approval_date is not null and s.expiration_date is not null and s.expiration_date < s.approval_date
union
select p.document_number, p.proposal_number, p.title, 'expiration date before application date' as ERROR
from en_doc_hdr_t d, eps_proposal p, eps_prop_special_review s
where p.proposal_number = s.proposal_number 
and p.document_number = d.doc_hdr_id 
and d.doc_rte_stat_cd = 'S'
and s.application_date is not null and s.expiration_date is not null and s.expiration_date < s.application_date;


PROMPT Disregard ORA-00942 error if run against a new schema, check intended for upgrade installs only.
spool off