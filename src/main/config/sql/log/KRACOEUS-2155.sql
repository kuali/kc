-- fix approval date when review status is not approved
update eps_prop_special_review s 
set s.approval_date = null
where exists 
(
    select 's' 
    from en_doc_hdr_t d, eps_proposal p
    where p.proposal_number = s.proposal_number 
    and p.document_number = d.doc_hdr_id 
    and d.doc_rte_stat_cd = 'S'
)
and s.approval_type_code <> '2' 
and s.approval_date is not null;

-- fix approval date before application date
update eps_prop_special_review s 
set s.approval_date = s.application_date
where exists
(
   select 's'
   from en_doc_hdr_t d, eps_proposal p
   where p.proposal_number = s.proposal_number
   and p.document_number = d.doc_hdr_id
   and d.doc_rte_stat_cd = 'S'
)
and s.application_date is not null
and s.approval_date is not null
and s.approval_date < s.application_date;

-- fix expiration date before approval date
update eps_prop_special_review s 
set s.expiration_date = s.approval_date
where exists
(
   select 's'
   from en_doc_hdr_t d, eps_proposal p
   where p.proposal_number = s.proposal_number
   and p.document_number = d.doc_hdr_id
   and d.doc_rte_stat_cd = 'S'
)
and s.approval_date is not null
and s.expiration_date is not null
and s.expiration_date < s.approval_date;

-- fix expiration date before application date
update eps_prop_special_review s 
set s.expiration_date = s.application_date
where exists
(
   select 's'
   from en_doc_hdr_t d, eps_proposal p
   where p.proposal_number = s.proposal_number
   and p.document_number = d.doc_hdr_id
   and d.doc_rte_stat_cd = 'S'
)
and s.application_date is not null
and s.expiration_date is not null
and s.expiration_date < s.application_date;

commit;

