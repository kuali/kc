/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.external.Cfda.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.kuali.kra.award.home.CFDA;
import org.kuali.kra.external.Cfda.CfdaService;
import org.kuali.kra.external.Cfda.CfdaUpdateResults;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;

import au.com.bytecode.opencsv.CSVReader;


/**
 * This class is an implementation of the CfdaService. 
 * Updates the CFDA table with values from cfda.gov
 */
public class CfdaServiceImpl implements CfdaService {
  
    private ParameterService parameterService;
    private BusinessObjectService businessObjectService;
    private DateTimeService dateTimeService;
    private String ftpPrefix = "ftp://";
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
                CSVReader csvReader = new CSVReader(screenReader, ',', '"', 1);
                List<String[]> lines = csvReader.readAll();
                for (String[] line : lines) {
                    String title = line[0];
                    String number = line[1];
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
       
        if (StringUtils.contains(url, ftpPrefix)) {
            url = StringUtils.remove(url, ftpPrefix);
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
        SortedMap<String, CFDA> dbCfdaMap = getCfdaValuesInDatabase();
        updateResults.setNumberOfRecordsInKcDatabase(dbCfdaMap.size());
        updateResults.setNumberOfRecordsRetrievedFromWebSite(govCfdaMap.size());
        
        for (String dbCfdaKey : dbCfdaMap.keySet()) {
            CFDA dbCfda = dbCfdaMap.get(dbCfdaKey);
            CFDA govCfda = govCfdaMap.get(dbCfdaKey);
            // if the database already has the new gov cfda number
            if (govCfdaMap.containsKey(dbCfdaKey)) {
               
                // these Cfda values are maintained automatically
                if (dbCfda.getCfdaMaintenanceTypeId().equalsIgnoreCase(Constants.CFDA_MAINT_TYP_ID_AUTOMATIC)) {
                    // check if title has changed and report, do not change in the database. This can be
                    // done manually.
                    String govCfdaProgramTitleName = trimProgramTitleName(govCfda.getCfdaProgramTitleName());
                    if (!dbCfda.getCfdaProgramTitleName().equalsIgnoreCase(govCfdaProgramTitleName)) {
                        // report in email
                        message.append("The cfda program title for the cfda number " + dbCfda.getCfdaNumber() + " has changed.<BR>");
                    }
                    // if it is active, do nothing
                    if (dbCfda.getActiveFlag()) {   
                        updateResults.increaseNumberOfRecordsUpdatedBecauseAutomatic(1);
                    } else {
                        // if it is not active, set it to active.
                        dbCfda.setActiveFlag(true);
                        getBusinessObjectService().save(dbCfda);
                        updateResults.increaseNumberOfRecordsReActivated(1);
                    }
                } else if (dbCfda.getCfdaMaintenanceTypeId().equalsIgnoreCase(Constants.CFDA_MAINT_TYP_ID_MANUAL)) {
                    // leave this alone, it is maintained manually
                    updateResults.increaseNumberOfRecordsNotUpdatedBecauseManual(1);
                }
                // delete the CFDA number from the govMap
                govCfdaMap.remove(dbCfdaKey);
            } else {
                // gov does not have this cfda number
                // if active, set not active
                if (dbCfda.getActiveFlag()) {
                    dbCfda.setActiveFlag(false);
                    getBusinessObjectService().save(dbCfda);
                    updateResults.increaseNumberOfRecordsDeactivatedBecauseNoLongerOnWebSite(1);
                } else {
                    // if not active, leave it alone for historical purposes. 
                    updateResults.increaseNumberOfRecordsNotUpdatedForHistoricalPurposes(1);
                }
            }
        }
        // add all the new CFDA numbers to the db
        updateResults.setMessage(message.toString());
        addNew(govCfdaMap);
        updateResults.increaseNumberOfRecordsNewlyAddedFromWebSite(govCfdaMap.size());
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
            cfda.setCfdaProgramTitleName(cfdaProgramTitleName);
            cfda.setActiveFlag(true);
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
