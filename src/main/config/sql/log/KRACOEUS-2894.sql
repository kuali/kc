UPDATE PROPOSAL_TYPE SET DESCRIPTION = 'Resubmission' where PROPOSAL_TYPE_CODE = 2;
UPDATE PROPOSAL_TYPE SET DESCRIPTION = 'Continuation' where PROPOSAL_TYPE_CODE = 4;
UPDATE krns_parm_t SET txt = '4' where nmspc_cd = 'KRA-PD' and parm_dtl_typ_cd = 'D' and parm_nm =  'proposaldevelopment.proposaltype.continuation';
commit;
