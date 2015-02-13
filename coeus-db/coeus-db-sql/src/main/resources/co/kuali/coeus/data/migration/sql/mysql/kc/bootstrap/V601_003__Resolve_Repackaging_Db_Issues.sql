
update eps_prop_columns_to_alter set LOOKUP_ARGUMENT = 'org.kuali.coeus.common.framework.noo.NoticeOfOpportunity' where LOOKUP_ARGUMENT = 'org.kuali.kra.bo.NoticeOfOpportunity';
update eps_prop_columns_to_alter set LOOKUP_ARGUMENT = 'org.kuali.coeus.common.framework.rolodex.Rolodex' where LOOKUP_ARGUMENT = 'org.kuali.kra.bo.Rolodex';
update eps_prop_columns_to_alter set LOOKUP_ARGUMENT = 'org.kuali.coeus.common.framework.type.DeadlineType' where LOOKUP_ARGUMENT = 'org.kuali.kra.proposaldevelopment.bo.DeadlineType';
update question set LOOKUP_CLASS = 'org.kuali.coeus.common.framework.person.KcPerson' where LOOKUP_CLASS = 'org.kuali.kra.bo.KcPerson';
update question set LOOKUP_CLASS = 'org.kuali.coeus.propdev.impl.attachment.Narrative' where LOOKUP_CLASS = 'org.kuali.kra.proposaldevelopment.bo.Narrative';
update question set LOOKUP_CLASS = 'org.kuali.coeus.propdev.impl.attachment.NarrativeAttachment' where LOOKUP_CLASS = 'org.kuali.kra.proposaldevelopment.bo.NarrativeAttachment';
