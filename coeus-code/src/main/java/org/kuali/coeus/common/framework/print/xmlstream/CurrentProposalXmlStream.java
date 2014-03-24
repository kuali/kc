package org.kuali.coeus.common.framework.print.xmlstream;

import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.framework.print.service.CurrentAndPendingReportService;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.customdata.AwardCustomData;
import org.kuali.kra.common.printing.CurrentReportBean;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.printing.schema.CurrentAndPendingSupportDocument;
import org.kuali.kra.printing.schema.CurrentAndPendingSupportDocument.CurrentAndPendingSupport;
import org.kuali.kra.printing.schema.CurrentAndPendingSupportDocument.CurrentAndPendingSupport.CurrentReportCEColumnNames;
import org.kuali.kra.printing.schema.CurrentAndPendingSupportDocument.CurrentAndPendingSupport.CurrentSupport;
import org.kuali.kra.printing.schema.CurrentAndPendingSupportDocument.CurrentAndPendingSupport.CurrentSupport.CurrentReportCEColomnValues;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

import java.util.*;
/**
 * This class generates XML that confirms with the XSD related to Current
 * Proposal Report. The data for XML is derived from
 * {@link org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase} and {@link Map} of details passed to the class.
 * 
 */
public class CurrentProposalXmlStream extends CurrentAndPendingBaseStream {

    private List<String> columsList;
    private ParameterService parameterService;
	/**
	 * This method generates XML for Current Proposal Report. It uses data
	 * passed in {@link org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase} for populating the XML nodes. The
	 * XMl once generated is returned as {@link XmlObject}
	 * 
	 * @param printableBusinessObject
	 *            using which XML is generated
	 * @param reportParameters
	 *            parameters related to XML generation
	 * @return {@link XmlObject} representing the XML
	 */
	public Map<String, XmlObject> generateXmlStream(
			KcPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters) {
		Map<String, XmlObject> xmlObjectList = new LinkedHashMap<String, XmlObject>();
		CurrentAndPendingSupportDocument currentAndPendingSupportDocument=CurrentAndPendingSupportDocument.Factory.newInstance();
		CurrentAndPendingSupport currentAndPendingSupport = CurrentAndPendingSupport.Factory
				.newInstance();
        List<CurrentReportBean> currentReportBeans = (List<CurrentReportBean>)reportParameters.get(CurrentAndPendingReportService.CURRENT_REPORT_BEANS_KEY);
        CurrentReportCEColumnNames currentReportCEColumnNames = getPendingSupportCustomColumnName(currentReportBeans);
		CurrentSupport[] currentSupports = getCurrentSupportInformation(currentReportBeans);
		currentAndPendingSupport.setPersonName((String)reportParameters.get(CurrentAndPendingReportService.REPORT_PERSON_NAME_KEY));
		currentAndPendingSupport.setCurrentSupportArray(currentSupports);
		currentAndPendingSupport.setCurrentReportCEColumnNames(currentReportCEColumnNames);
		currentAndPendingSupportDocument.setCurrentAndPendingSupport(currentAndPendingSupport);
		xmlObjectList.put(CurrentAndPendingReportService.CURRENT_REPORT_TYPE, currentAndPendingSupportDocument);
		return xmlObjectList;
	}
	
	private CurrentReportCEColumnNames getPendingSupportCustomColumnName(List<CurrentReportBean> currentReportBeans){
	    CurrentReportCEColumnNames currentReportCEColumnNames =  CurrentReportCEColumnNames.Factory.newInstance();
        columsList=new ArrayList<String>();
        String columName ="";
        for (CurrentReportBean bean : currentReportBeans){
            if(bean.getAwardCustomDataList() !=null){
               
                    for(AwardCustomData awardcutomdata :bean.getAwardCustomDataList()) {
                        if(awardcutomdata.getCustomAttribute()!=null)
                             columName = awardcutomdata.getCustomAttribute().getLabel();
                        if(!columsList.contains(columName))
                             columsList.add(columName);
                    }
            }
        }
        for(int columnLabelIndex=0;columnLabelIndex<columsList.size();columnLabelIndex++){
            if(columnLabelIndex == 0)
                currentReportCEColumnNames.setCEColumnName1(columsList.get(columnLabelIndex).toString());
            
            if(columnLabelIndex == 1)
                currentReportCEColumnNames.setCEColumnName2(columsList.get(columnLabelIndex).toString());
          
            if(columnLabelIndex == 2)
                currentReportCEColumnNames.setCEColumnName3(columsList.get(columnLabelIndex).toString());
          
            if(columnLabelIndex == 3)
                currentReportCEColumnNames.setCEColumnName4(columsList.get(columnLabelIndex).toString());
          
            if(columnLabelIndex == 4)
                currentReportCEColumnNames.setCEColumnName5(columsList.get(columnLabelIndex).toString());
            
            if(columnLabelIndex == 5)
                currentReportCEColumnNames.setCEColumnName6(columsList.get(columnLabelIndex).toString());
            
            if(columnLabelIndex == 6)
                currentReportCEColumnNames.setCEColumnName7(columsList.get(columnLabelIndex).toString());
            
            if(columnLabelIndex == 7)
                currentReportCEColumnNames.setCEColumnName8(columsList.get(columnLabelIndex).toString());
            
            if(columnLabelIndex == 8)
                currentReportCEColumnNames.setCEColumnName9(columsList.get(columnLabelIndex).toString());
            
            if(columnLabelIndex == 9)
                currentReportCEColumnNames.setCEColumnName10(columsList.get(columnLabelIndex).toString());
          
        }
        return currentReportCEColumnNames;
    }
	/*
	 * This method will set the values to current support information xml object
	 * and finally returns a array of currentSupport xml objects
	 */
	private CurrentSupport[] getCurrentSupportInformation(List<CurrentReportBean> currentReportBeans) {
		List<CurrentSupport> currentSupports = new ArrayList<CurrentSupport>();
		 parameterService = KcServiceLocator.getService(ParameterService.class);
		 String directIndirectEnabledValue = parameterService.getParameterValueAsString(Constants.PARAMETER_MODULE_AWARD, ParameterConstants.DOCUMENT_COMPONENT, "ENABLE_AWD_ANT_OBL_DIRECT_INDIRECT_COST");

		for (CurrentReportBean bean : currentReportBeans) {
		    Map<String,String> cutomDataValueMap = new HashMap<String,String>();
		    CurrentSupport currentSupport = CurrentSupport.Factory.newInstance();
		    currentSupports.add(currentSupport);
            if (bean.getAcademicYearEffort() != null) {
                currentSupport.setAcademicYearEffort(bean
                        .getAcademicYearEffort().bigDecimalValue());
            }
            if (bean.getCalendarYearEffort() != null) {
                currentSupport.setCalendarYearEffort(bean
                        .getCalendarYearEffort().bigDecimalValue());
            }
            if (bean.getTotalEffort() != null) {
                currentSupport.setPercentageEffort(bean.getTotalEffort()
                        .bigDecimalValue());
            }
            if (bean.getSummerEffort() != null) {
                currentSupport.setSummerYearEffort(bean
                        .getSummerEffort().bigDecimalValue());
            }
            if (bean.getRoleCode() != null) {
                currentSupport.setPI(bean.getRoleCode());
            }
            if (bean.getAwardAmount() != null) {
                currentSupport.setAwardAmount(bean.getAwardAmount().bigDecimalValue());
            }
            if (bean.getProjectEndDate() != null) {
                currentSupport.setEndDate(dateTimeService.getCalendar(bean.getProjectEndDate()));
            }
            if (bean.getAwardTitle() != null) {
                currentSupport.setTitle(bean.getAwardTitle());
            }
            if (bean.getProjectStartDate() != null) {
                currentSupport.setEffectiveDate(dateTimeService.getCalendar(bean.getProjectStartDate()));
            }
            if (bean.getSponsorAwardNumber() != null) {
                currentSupport.setSponsorAwardNumber(bean.getSponsorAwardNumber());
            }
            if (bean.getSponsorName() != null) {
                currentSupport.setAgency(bean.getSponsorName());
            }
            if (bean.getTotalDirectCostTotal() !=null && directIndirectEnabledValue.equals("1")){
                currentSupport.setTotalDirectCost(bean.getTotalDirectCostTotal().bigDecimalValue());
            }
            if (bean.getTotalIndirectCostTotal() !=null && directIndirectEnabledValue.equals("1")){
                currentSupport.setTotalIndirectCost(bean.getTotalIndirectCostTotal().bigDecimalValue());
            }
            if(bean.getAwardCustomDataList() !=null){
                
                List<CurrentReportCEColomnValues> currentReportCEColomnValues = new ArrayList<CurrentReportCEColomnValues>();
                for(AwardCustomData awardcutomdata :bean.getAwardCustomDataList()) {
                    if(awardcutomdata.getCustomAttribute()!=null && awardcutomdata.getValue()!=null && awardcutomdata.getCustomAttribute().getLabel()!=null){
                        cutomDataValueMap.put(awardcutomdata.getCustomAttribute().getLabel(), awardcutomdata.getValue());
                    }
                }
                for(int columnLabelIndex=0;columnLabelIndex<columsList.size();columnLabelIndex++){
                    CurrentReportCEColomnValues currentReportCEColumnValue = CurrentReportCEColomnValues.Factory.newInstance();
                    if(cutomDataValueMap.get(columsList.get(columnLabelIndex))!=null)
                        currentReportCEColumnValue.setCurrentReportCEColumnValue(cutomDataValueMap.get(columsList.get(columnLabelIndex)).toString());
                    else{
                        currentReportCEColumnValue.setCurrentReportCEColumnValue("");
                    }
                    currentReportCEColomnValues.add(currentReportCEColumnValue);
                }
                currentSupport.setCurrentReportCEColomnValuesArray(currentReportCEColomnValues.toArray(new CurrentReportCEColomnValues[0]));
            }
		}
		return currentSupports.toArray(new CurrentSupport[0]);
	}
}
