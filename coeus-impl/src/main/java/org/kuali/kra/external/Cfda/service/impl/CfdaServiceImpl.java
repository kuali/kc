/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.external.Cfda.service.impl;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.kuali.kra.award.home.CFDA;
import org.kuali.kra.external.Cfda.CfdaService;
import org.kuali.kra.external.Cfda.CfdaUpdateResults;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.core.framework.persistence.jdbc.sql.SQLUtils;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;


/**
 * This class is an implementation of the CfdaService. 
 * Updates the CFDA table with values from cfda.gov
 */
public class CfdaServiceImpl implements CfdaService {
  
    private ParameterService parameterService;
    private BusinessObjectService businessObjectService;
    private DateTimeService dateTimeService;
    private static final String FTP_PREFIX = "ftp://";
    private String cfdaFileName;
    private String govURL;
    
    private static final Log LOG = LogFactory.getLog(CfdaServiceImpl.class);
    protected static Comparator cfdaComparator;

    static {
        cfdaComparator = new Comparator() {
            public int compare(Object o1, Object o2) {
                String lhs = (String) o1;
                String rhs = (String) o2;
                return lhs.compareTo(rhs);
            }
        };
    }
    
   
    /**
     * This method retrieves cfda codes from the government site
     * @return
     * @throws IOException
     */
    public SortedMap<String, CFDA> retrieveGovCodes() throws IOException {
             
        SortedMap<String, CFDA> govMap = new TreeMap<String, CFDA>();
        createGovURL();
        LOG.info("Getting government file: " + cfdaFileName + " from URL " + govURL + " for update");

        InputStream inputStream = null;
        FTPClient ftp = connect(getGovURL());
        try {
            inputStream = ftp.retrieveFileStream(cfdaFileName);
            if (inputStream != null) {
                LOG.info("reading input stream");
                InputStreamReader screenReader = new InputStreamReader(inputStream);

                List<CSVRecord> records = CSVFormat.DEFAULT.withSkipHeaderRecord(true).parse(screenReader).getRecords();
                for (CSVRecord record : records) {
                    String title = record.get(0);
                    String number = record.get(1);
                    CFDA cfda = new CFDA();
                    cfda.setCfdaNumber(number);
                    cfda.setCfdaProgramTitleName(title);
                    cfda.setCfdaMaintenanceTypeId(Constants.CFDA_MAINT_TYP_ID_AUTOMATIC);
                    govMap.put(number, cfda);
                }
            } else { 
                // If file name is incorrect
                throw new IOException("Input stream is null. The file " + cfdaFileName + " could not be retrieved from " + govURL);
            }
        } finally {
            disconnect(ftp);
        }
        return govMap;
    }
    
    /**
     * This method connects to the FTP server.
     * @param url
     * @return ftp
     */
    public FTPClient connect(String url) {
        FTPClient ftp = new FTPClient();
        try {
            ftp.connect(getGovURL());
            // Entering passive mode to prevent firewall issues. The client will establish a  data transfer
            // connection.
            ftp.enterLocalPassiveMode();
            int reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                LOG.error("FTP connection to server not established.");
                throw new IOException("FTP connection to server not established.");
            }

            boolean loggedIn = ftp.login(Constants.CFDA_GOV_LOGIN_USERNAME, "");
            LOG.info("Logged in as " + Constants.CFDA_GOV_LOGIN_USERNAME);
            if (!loggedIn) {
                LOG.error("Could not login as anonymous.");
                throw new IOException("Could not login as anonymous.");
            }

        } catch(IOException io) {
            LOG.error(io.getMessage());
        }
        return ftp;
    }
    
    /**
     * This method disconnects from server.
     * @param ftp
     * @throws IOException
     */
    public void disconnect(FTPClient ftp) throws IOException {
        ftp.logout();
        if (ftp.isConnected()) {
            ftp.disconnect();
        }
    }
    
    /**
     * This method gets the url from the parameter and creates the fileName and 
     * the actual URL used to FTP.
     */
    protected void createGovURL() {
        // Example url ftp://ftp.cfda.gov/programs09187.csv
        String url = getParameterService().getParameterValueAsString(Constants.MODULE_NAMESPACE_AWARD, 
                Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.CFDA_GOV_URL_PARAMETER);
        String fileName = StringUtils.substringAfterLast(url, "/");
        url = StringUtils.substringBeforeLast(url, "/");
       
        if (StringUtils.contains(url, FTP_PREFIX)) {
            url = StringUtils.remove(url, FTP_PREFIX);
        }
        
        Calendar calendar = dateTimeService.getCurrentCalendar();
        // need to pull off the '20' in 2009
        String year = "" + calendar.get(Calendar.YEAR);
        year = year.substring(2, 4);
        fileName = fileName + year;

        // the last 3 numbers in the file name are the day of the year, but the files are from "yesterday"
        fileName = fileName + String.format("%03d", calendar.get(Calendar.DAY_OF_YEAR) - 1);
        fileName = fileName + ".csv";
        
        setGovURL(url);
        setCfdaFileName(fileName);
    }
    
    protected String getGovURL() {
        return govURL;
    }
    
    protected String getCfdaFileName() {
        return cfdaFileName;
    }
    
    protected void setGovURL(String url) {
        govURL = url;
    }
    
    protected void setCfdaFileName(String fileName) {
        cfdaFileName = fileName;
    }
    /**
     * This method updates the CFDA table with the values received from the 
     * gov site.
     * @see org.kuali.kra.external.Cfda.CfdaService#updateCfda()
     */
    public CfdaUpdateResults updateCfda() {
        CfdaUpdateResults updateResults = new CfdaUpdateResults();
        StringBuilder message = new StringBuilder();
        Map<String, CFDA> govCfdaMap;
        
        try {
            govCfdaMap = retrieveGovCodes();
        } catch (IOException ioe) {
            message.append("Problem encountered while retrieving cfda numbers, " 
                            + "the database was not updated." + ioe.getMessage());
            updateResults.setMessage(message.toString());
            return updateResults;
        }
        
        SortedMap<String, CFDA> kcMap = getCfdaValuesInDatabase();
        updateResults.setNumberOfRecordsInKcDatabase(kcMap.size());
        updateResults.setNumberOfRecordsRetrievedFromWebSite(govCfdaMap.size());
        
        for (String key : kcMap.keySet()) {
            CFDA kcCfda = kcMap.get(key);
            CFDA govCfda = govCfdaMap.get(key);


            if (kcCfda.getCfdaMaintenanceTypeId().equalsIgnoreCase(Constants.CFDA_MAINT_TYP_ID_MANUAL)) {
                // Leave it alone. It's maintained manually.
                updateResults.setNumberOfRecordsNotUpdatedBecauseManual(1 + updateResults.getNumberOfRecordsNotUpdatedBecauseManual());
            }
            else if (kcCfda.getCfdaMaintenanceTypeId().equalsIgnoreCase(Constants.CFDA_MAINT_TYP_ID_AUTOMATIC)) {

                if (govCfda == null) {
                    if (kcCfda.getActive()) {
                        kcCfda.setActive(false);
                        businessObjectService.save(kcCfda);
                        updateResults.setNumberOfRecordsDeactivatedBecauseNoLongerOnWebSite(updateResults.getNumberOfRecordsDeactivatedBecauseNoLongerOnWebSite() + 1);
                    }
                    else {
                        // Leave it alone for historical purposes
                        updateResults.setNumberOfRecordsNotUpdatedForHistoricalPurposes(updateResults.getNumberOfRecordsNotUpdatedForHistoricalPurposes() + 1);
                    }
                }
                else {
                    if (kcCfda.getActive()) {
                       /*if (!kcCfda.getCfdaProgramTitleName().equalsIgnoreCase(govCfda.getCfdaProgramTitleName())) {
                            message.append("The program title for CFDA " + kcCfda.getCfdaNumber() + " changed from " 
                                            + kcCfda.getCfdaProgramTitleName() + " to " + govCfda.getCfdaProgramTitleName() + ".<BR>");
                        }*/
                        updateResults.setNumberOfRecordsUpdatedBecauseAutomatic(updateResults.getNumberOfRecordsUpdatedBecauseAutomatic() + 1);
                    }
                    else {
                        kcCfda.setActive(true);
                        updateResults.setNumberOfRecordsReActivated(updateResults.getNumberOfRecordsReActivated() + 1);
                    }

                    kcCfda.setCfdaProgramTitleName(govCfda.getCfdaProgramTitleName());
                    businessObjectService.save(kcCfda);
                }
            }

            // Remove it from the govMap so the remaining codes are new 
            govCfdaMap.remove(key);
        }
        // New CFDA number from govt, added to the db
        updateResults.setMessage(message.toString());
        addNew(govCfdaMap);
        updateResults.setNumberOfRecordsNewlyAddedFromWebSite(govCfdaMap.size() + 1);
        return updateResults;
    }
 
    /**
     * This method gets the current CFDA values in the table.
     * @return
     */
    protected SortedMap<String, CFDA> getCfdaValuesInDatabase() {
        Collection<CFDA> cfdaValues = getBusinessObjectService().findAll(CFDA.class);
        
        SortedMap<String, CFDA> mapDatabaseCfda = new TreeMap<String, CFDA>(cfdaComparator);
        for (CFDA o : cfdaValues) {
            mapDatabaseCfda.put(o.getCfdaNumber(), o);
        }
        return mapDatabaseCfda;    
    }
    
    /**
     * This method adds new cfda numbers to the cfda table
     * @param newCfdas
     */
    protected void addNew(Map<String, CFDA> newCfdas) {
        for (String cfdaKey : newCfdas.keySet()) {
            CFDA cfda = newCfdas.get(cfdaKey);
            
            String cfdaProgramTitleName = trimProgramTitleName(cfda.getCfdaProgramTitleName());
            // all new cfda numbers are set to automatic and active
            cfda.setCfdaProgramTitleName(SQLUtils.cleanString(cfdaProgramTitleName));
            cfda.setActive(true);
            cfda.setCfdaMaintenanceTypeId(Constants.CFDA_MAINT_TYP_ID_AUTOMATIC);
            getBusinessObjectService().save(cfda);
        }
    }
    
    /**
     * This method trims the program title name so it cannot be longer 
     * than the specified length.
     * @param programTitleName
     * @return
     */
    protected String trimProgramTitleName(String programTitleName) {
        if (programTitleName != null && programTitleName.length() > Constants.MAX_ALLOWABLE_CFDA_PGM_TITLE_NAME) {
            programTitleName = programTitleName.substring(0, Constants.MAX_ALLOWABLE_CFDA_PGM_TITLE_NAME);
        }
        return programTitleName;
    }

   
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }
    /**
     * Sets the businessObjectService. Injected by spring.
     * @return
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    /**
     * Sets the dateTimeService. Injected by spring.
     * @return
     */
    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }
    /*
     * Sets the parameterService attribute value. Injected by Spring.
     * @param parameterService The parameterService to set.
     */
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
    /**
     * Sets the parameterService. Injected by spring.
     * @return
     */
    public ParameterService getParameterService() {
        return parameterService;
    }
}
