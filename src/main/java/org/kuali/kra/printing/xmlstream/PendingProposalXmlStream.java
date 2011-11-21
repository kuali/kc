package org.kuali.kra.printing.xmlstream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import noNamespace.CurrentAndPendingSupportDocument;
import noNamespace.CurrentAndPendingSupportDocument.CurrentAndPendingSupport;
import noNamespace.CurrentAndPendingSupportDocument.CurrentAndPendingSupport.PendingReportCEColumnNames;
import noNamespace.CurrentAndPendingSupportDocument.CurrentAndPendingSupport.PendingSupport;
import noNamespace.CurrentAndPendingSupportDocument.CurrentAndPendingSupport.PendingSupport.PendingReportCEColomnValues;

import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.common.printing.PendingReportBean;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.institutionalproposal.customdata.InstitutionalProposalCustomData;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.printing.service.InstitutionalProposalPersonService;
import org.kuali.kra.printing.service.CurrentAndPendingReportService;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.rice.kns.util.KualiDecimal;

/**
 * This class generates XML that confirms with the XSD related to Pending
 * Proposal Report. The data for XML is derived from
 * {@link ResearchDocumentBase} and {@link Map} of details passed to the class.
 * 
 */
public class PendingProposalXmlStream extends CurrentAndPendingBaseStream {
	private InstitutionalProposalPersonService institutionalProposalPersonService;
	private ArrayList columsList;
    private static final String PROP_SEQ_STATUS = "ACTIVE";
    private static final String PROP_NUMBER = "proposalNumber";
    private static final int PROP_TYPE_CONTINUATION = 4;
    private static final int PROP_TYPE_TASK_ORDER = 6;
    private static final int PROP_PENDING_STATUS = 1;    
	/**
	 * This method generates XML for Pending Proposal Report. It uses data
	 * passed in {@link ResearchDocumentBase} for populating the XML nodes. The
	 * XMl once generated is returned as {@link XmlObject}
	 * 
	 * @param printableBusinessObject
	 *            using which XML is generated
	 * @param reportParameters
	 *            parameters related to XML generation
	 * @return {@link XmlObject} representing the XML
	 */
	public Map<String, XmlObject> generateXmlStream(
			KraPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters) {
		Map<String, XmlObject> xmlObjectList = new LinkedHashMap<String, XmlObject>();
		CurrentAndPendingSupportDocument currentAndPendingSupportDocument=CurrentAndPendingSupportDocument.Factory.newInstance();
		CurrentAndPendingSupport currentAndPendingSupport = CurrentAndPendingSupport.Factory
				.newInstance();
		List<PendingReportBean> pendingReportBeans = (List<PendingReportBean>)reportParameters.get(CurrentAndPendingReportService.PENDING_REPORT_BEANS_KEY);
       
        PendingReportCEColumnNames pendingReportCEColumnNames =getPendingSupportCustomColumnName(pendingReportBeans);
        
        PendingSupport[] pendingSupports = getPendingSupportInformation(pendingReportBeans);
        
        currentAndPendingSupport.setPersonName((String)reportParameters.get(CurrentAndPendingReportService.REPORT_PERSON_NAME_KEY));
        currentAndPendingSupport.setPendingSupportArray(pendingSupports);
        currentAndPendingSupport.setPendingReportCEColumnNames(pendingReportCEColumnNames);
        currentAndPendingSupportDocument.setCurrentAndPendingSupport(currentAndPendingSupport);
        xmlObjectList.put(CurrentAndPendingReportService.PENDING_REPORT_TYPE, currentAndPendingSupportDocument);
		return xmlObjectList;
	}

	private PendingReportCEColumnNames getPendingSupportCustomColumnName(List<PendingReportBean> pendingReportBeans){
	    PendingReportCEColumnNames pendingReportCEColumnNames =  PendingReportCEColumnNames.Factory.newInstance();
	    columsList=new ArrayList<String>();
        String columName ="";
        for (PendingReportBean bean : pendingReportBeans) {
            if(bean.getInstitutionalProposalCustomDataList() !=null){
                    for(InstitutionalProposalCustomData institutionalProposalCustomData :bean.getInstitutionalProposalCustomDataList()) {
                        if(institutionalProposalCustomData.getCustomAttribute()!=null)
                             columName = institutionalProposalCustomData.getCustomAttribute().getLabel();
                        if(!columsList.contains(columName))
                            columsList.add(columName);
                    }
            }
        }
        for(int columnLabelIndex=0;columnLabelIndex<columsList.size();columnLabelIndex++){
            if(columnLabelIndex == 0)
                pendingReportCEColumnNames.setCEColumnName1(columsList.get(columnLabelIndex).toString());
            
            if(columnLabelIndex == 1)
                pendingReportCEColumnNames.setCEColumnName2(columsList.get(columnLabelIndex).toString());
          
            if(columnLabelIndex == 2)
                pendingReportCEColumnNames.setCEColumnName3(columsList.get(columnLabelIndex).toString());
          
            if(columnLabelIndex == 3)
                pendingReportCEColumnNames.setCEColumnName4(columsList.get(columnLabelIndex).toString());
          
            if(columnLabelIndex == 4)
                pendingReportCEColumnNames.setCEColumnName5(columsList.get(columnLabelIndex).toString());
            
            if(columnLabelIndex == 5)
                pendingReportCEColumnNames.setCEColumnName6(columsList.get(columnLabelIndex).toString());
            
            if(columnLabelIndex == 6)
                pendingReportCEColumnNames.setCEColumnName7(columsList.get(columnLabelIndex).toString());
            
            if(columnLabelIndex == 7)
                pendingReportCEColumnNames.setCEColumnName8(columsList.get(columnLabelIndex).toString());
            
            if(columnLabelIndex == 8)
                pendingReportCEColumnNames.setCEColumnName9(columsList.get(columnLabelIndex).toString());
            
            if(columnLabelIndex == 9)
                pendingReportCEColumnNames.setCEColumnName10(columsList.get(columnLabelIndex).toString());
          
        }
        return pendingReportCEColumnNames;
	}
	/*
	 * This method set the values to pending support xml object attributes and
	 * finally returns the Pending support xml object.
	 */
	private PendingSupport getPendingSupportProposals(
			InstitutionalProposal instituteProposal,
			Map<String, Object> reportParameters) {
		PendingSupport pendingSupport = PendingSupport.Factory.newInstance();
		String persionId = null;
//		if (reportParameters != null && !reportParameters.isEmpty()
//				&& reportParameters.get(PERSON_ID) != null) {
//			persionId = (String) reportParameters.get(PERSON_ID);
//		}
//		if (instituteProposal.getTitle() != null) {
//			pendingSupport.setTitle(instituteProposal.getTitle());
//		}
//		setPendingSupportProposalsFromProposalPersons(instituteProposal,
//				pendingSupport, persionId);
//		KualiDecimal totalCost = getTotalCost(instituteProposal);
//		if (totalCost != null) {
//			pendingSupport.setTotalRequested(totalCost.bigDecimalValue());
//		}
//		if (instituteProposal.getProposalNumber() != null) {
//			pendingSupport.setProposalNumber(instituteProposal
//					.getProposalNumber());
//		}
//		if (instituteProposal.getRequestedEndDateInitial() != null) {
//			pendingSupport
//					.setEndDate(dateTimeService.getCalendar(instituteProposal
//							.getRequestedEndDateInitial()));
//		}
//		if (instituteProposal.getRequestedStartDateInitial() != null) {
//			pendingSupport.setEffectiveDate(dateTimeService
//					.getCalendar(instituteProposal
//							.getRequestedStartDateInitial()));
//		}
//		if (instituteProposal.getSponsorName() != null) {
//			pendingSupport.setAgency(instituteProposal.getSponsorName());
//		}
		return pendingSupport;
	}

	/*
	 * This method will return the sum of total direct cost and total indirect
	 * cost.
	 */
	private KualiDecimal getTotalCost(InstitutionalProposal instituteProposal) {
		KualiDecimal totalDirectCostTotal = instituteProposal
				.getTotalDirectCostTotal();
		KualiDecimal totalIndirectCostTotal = instituteProposal
				.getTotalIndirectCostTotal();
		KualiDecimal totalCost = null;
		if (totalIndirectCostTotal != null && totalIndirectCostTotal != null) {
			totalCost = totalDirectCostTotal.add(totalIndirectCostTotal);
		}
		return totalCost;
	}

	private PendingSupport[] getPendingSupportInformation(List<PendingReportBean> pendingReportBeans) {
	    List<PendingSupport> pendingSupports = new ArrayList<PendingSupport>();
	    for (PendingReportBean bean : pendingReportBeans) {
	        PendingSupport pendingSupport = PendingSupport.Factory.newInstance();
	        Map<String,String> cutomDataValueMap = new HashMap<String,String>();
	        Map<String, String> proposalNumberMap = new HashMap<String, String>();
	        List<InstitutionalProposal> institutionalProposalList = null;  
	        pendingSupports.add(pendingSupport);
	        
	        proposalNumberMap.put(PROP_NUMBER, String.valueOf(bean.getProposalNumber()));
	        institutionalProposalList = (List<InstitutionalProposal>) getBusinessObjectService()
                                    .findMatching(InstitutionalProposal.class,proposalNumberMap);
	      for(InstitutionalProposal institutionalProposal:institutionalProposalList){
	        
	       if(institutionalProposal.getProposalSequenceStatus().equals(PROP_SEQ_STATUS) && institutionalProposal.getStatusCode()== PROP_PENDING_STATUS 
	               && institutionalProposal.getProposalTypeCode()!= PROP_TYPE_CONTINUATION && institutionalProposal.getProposalTypeCode()!= PROP_TYPE_TASK_ORDER ){
	        
	        if (bean.getProposalTitle() != null) {
	            pendingSupport.setTitle(bean.getProposalTitle());
	        }
	        if (bean.getTotalDirectCostTotal() != null) {
	            pendingSupport.setTotalDirectCost(bean.getTotalDirectCostTotal().bigDecimalValue());
	        }
	        if (bean.getTotalIndirectCostTotal() != null) {
	            pendingSupport.setTotalIndirectCost(bean.getTotalIndirectCostTotal().bigDecimalValue());
	        }
	        if (bean.getTotalRequestedCost() != null) {
	            pendingSupport.setTotalRequested(bean.getTotalRequestedCost().bigDecimalValue());
	        }
	        if (bean.getProposalNumber() != null) {
	            pendingSupport.setProposalNumber(bean
	                    .getProposalNumber());
	        }
	        if (bean.getRequestedEndDateTotal() != null) {
	            pendingSupport
	                    .setEndDate(dateTimeService.getCalendar(bean
	                            .getRequestedEndDateTotal()));
	        }
	        if (bean.getRequestedStartDateInitial() != null) {
	            pendingSupport.setEffectiveDate(dateTimeService
	                    .getCalendar(bean
	                            .getRequestedStartDateInitial()));
	        }
	        if (bean.getSponsorName() != null) {
	            pendingSupport.setAgency(bean.getSponsorName());
	        }
            if (bean.getRoleCode() != null) {
                pendingSupport.setPI(bean.getRoleCode());
            }
            if (bean.getTotalEffort() != null) {
                pendingSupport.setPercentageEffort(bean.getTotalEffort().bigDecimalValue());
            }
            if (bean.getAcademicYearEffort() != null) {
                pendingSupport.setAcademicYearEffort(bean.getAcademicYearEffort().bigDecimalValue());
            }
            if (bean.getCalendarYearEffort() != null) {
                pendingSupport.setCalendarYearEffort(bean.getCalendarYearEffort().bigDecimalValue());
            }
            if (bean.getSummerEffort() != null) {
                pendingSupport.setSummerYearEffort(bean.getSummerEffort().bigDecimalValue());
            }
            if(bean.getInstitutionalProposalCustomDataList() !=null){
                List<PendingReportCEColomnValues> pendingReportCEColomnValues = new ArrayList<PendingReportCEColomnValues>();
                for(InstitutionalProposalCustomData institutionalProposalCustomData :bean.getInstitutionalProposalCustomDataList()) {
                    if(institutionalProposalCustomData.getCustomAttribute()!=null && institutionalProposalCustomData.getValue()!=null && institutionalProposalCustomData.getCustomAttribute().getLabel()!=null){
                        cutomDataValueMap.put(institutionalProposalCustomData.getCustomAttribute().getLabel(), institutionalProposalCustomData.getValue());
                    }
                }
                for(int columnLabelIndex=0;columnLabelIndex<columsList.size();columnLabelIndex++){
                    PendingReportCEColomnValues pendingReportCEColomnValue = PendingReportCEColomnValues.Factory.newInstance();
                    if(cutomDataValueMap.get(columsList.get(columnLabelIndex))!=null)
                        pendingReportCEColomnValue.setPendingReportCEColumnValue(cutomDataValueMap.get(columsList.get(columnLabelIndex)).toString());
                    else{
                        pendingReportCEColomnValue.setPendingReportCEColumnValue("");
                    }
                    pendingReportCEColomnValues.add(pendingReportCEColomnValue);
                }
                pendingSupport.setPendingReportCEColomnValuesArray(pendingReportCEColomnValues.toArray(new PendingReportCEColomnValues[0]));
            }
	    }
	  }
	 }
	    return pendingSupports.toArray(new PendingSupport[0]);
	}

}
