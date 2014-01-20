update KRNS_PARM_T 
 set TXT = 'Y', PARM_DESC_TXT = 'Linking from Institute Proposal to PROTOCOL Funding source is configurable at impl time'
where PARM_NM = 'irb.protocol.institute.proposal.linking.enabled';
/
update KRNS_PARM_T 
 set TXT = 'Y', PARM_DESC_TXT = 'Linking from Development Proposal to PROTOCOL Funding source is configurable at impl time'
where PARM_NM = 'irb.protocol.development.proposal.linking.enabled';
/
commit;
