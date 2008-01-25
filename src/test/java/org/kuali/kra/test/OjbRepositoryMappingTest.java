/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.test;

import static java.io.File.separator;
import static org.apache.commons.beanutils.PropertyUtils.getPropertyDescriptors;
import static org.junit.Assert.fail;

import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import oracle.jdbc.pool.OracleDataSource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Unit Tests for validating an OJB repository XML file. The objective is to validate without initializing OJB. If OJB starts up and the repository.xml
 * file is bad, then it will fast-fail. This is an undesirable effect. What is needed is to know that OJB will fail beforehand.
 * 
 */
public class OjbRepositoryMappingTest {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(OjbRepositoryMappingTest.class);

    // For XML parsing and validation
    private static final String CLASS_DESCRIPTOR_NAME       = "class-descriptor";
    private static final String FIELD_DESCRIPTOR_NAME       = "field-descriptor";
    private static final String CLASS_ATTRIBUTE_NAME        = "class";
    private static final String TABLE_ATTRIBUTE_NAME        = "table";
    private static final String DEFAULT_ATTRIBUTE_NAME      = "name";
    private static final String COLUMN_ATTRIBUTE_NAME       = "column";
    private static final String COLLECTION_DESCRIPTOR_NAME  = "collection-descriptor";
    private static final String REFERENCE_DESCRIPTOR_NAME   = "reference-descriptor";
    private static final String CLASS_REF_ATTRIBUTE_NAME    = "class-ref";
    private static final String EL_CLASS_REF_ATTRIBUTE_NAME = "element-class-ref";
    private static final String FIELD_REF_ATTRIBUTE_NAME    = "field-ref";
    private static final String FOREIGN_KEY_NAME            = "foreignkey";
    private static final String INVERSE_FOREIGN_KEY_NAME    = "inverse-foreignkey";
    private static final String COLLECTION_CLASS_NAME       = "collection-class";
    private static final String DATASOURCE_URL_NAME         = "datasource.url";
    private static final String DATASOURCE_DRIVER_NAME      = "datasource.driver.name";
    private static final String DATASOURCE_USERNAME_NAME    = "datasource.username";
    private static final String DATASOURCE_PASSWORD_NAME    = "datasource.password";

    private String dsUrl;
    private String dsDriver;
    private String dsUser;
    private String dsPass;
    private String dsSchema;
    private String configPath;
    
    /**
     * 
     */
    @Before
    public void setUp() throws Exception {
        dsSchema = "LEOTST";
        configPath = System.getProperty("user.home") + separator + "kuali" + separator + "test" + separator + "dev" + separator + "kra-test-config.xml";
        
        BufferedReader reader = new BufferedReader(new FileReader(configPath));
        String line = null;
        while ((line = reader.readLine()) != null) {
            if (StringUtils.contains(line, DATASOURCE_URL_NAME)) {
                dsUrl = getValueFromConfig(line, DATASOURCE_URL_NAME);
            }
            else if (StringUtils.contains(line, DATASOURCE_DRIVER_NAME)) {
                dsDriver = getValueFromConfig(line, DATASOURCE_DRIVER_NAME);
            }
            else if (StringUtils.contains(line, DATASOURCE_USERNAME_NAME)) {
                dsUser = getValueFromConfig(line, DATASOURCE_USERNAME_NAME);
            }
            else if (StringUtils.contains(line, DATASOURCE_PASSWORD_NAME)) {
                dsPass = getValueFromConfig(line, DATASOURCE_PASSWORD_NAME);
            }
        }
        
        LOG.info("dsUrl = " + dsUrl);
        LOG.info("dsUser = " + dsUser);
        LOG.info("dsPass = " + dsPass);
        LOG.info("dsSchema = " + dsSchema);
    }
    
    private String getValueFromConfig(String line, String name) {
        LOG.info("Parsing line " + line);
        String tempLine = StringUtils.substringAfter(line, name + "\">");
        LOG.info("Parsing temp line " + tempLine);
        return tempLine.substring(0, tempLine.indexOf("<"));
    }

    /**
     * 
     */
    @After 
    public void tearDown() {
        
    }

    /**
     * Just validates the repository.xml against the repository.dtd
     * 
     * @throws Exception
     */
    @Test
    public void xmlValidation() throws Exception { 
        LOG.info("Starting XML validation");
        final URL dtdUrl = getClass().getClassLoader().getResource("repository.dtd");
        final URL repositoryUrl = getClass().getClassLoader().getResource("repository.xml");
        
        LOG.info("Found dtd url " + dtdUrl);
        LOG.info("Found repository url " + repositoryUrl);

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        saxParserFactory.setValidating(true);
        saxParserFactory.setNamespaceAware(false);
        
        SAXParser parser = saxParserFactory.newSAXParser();
        try {
            parser.parse(repositoryUrl.getFile(), new DefaultHandler());
        }
        catch (Exception e) {
            e.printStackTrace();
            fail("Test should not encounter exceptions during parsing.");
        }
    }
    
    @Test
    public void verifyTables() throws Exception {
        final OracleDataSource ods = new OracleDataSource();
        ods.setURL(dsUrl);
        ods.setUser(dsUser);
        ods.setPassword(dsPass);
        
        final Connection conn = ods.getConnection();
        final DefaultHandler handler = new TableValidationHandler(conn); 
        
        LOG.info("Starting XML validation");
        final URL dtdUrl = getClass().getClassLoader().getResource("repository.dtd");
        final URL repositoryUrl = getClass().getClassLoader().getResource("repository.xml");
        
        LOG.info("Found dtd url " + dtdUrl);
        LOG.info("Found repository url " + repositoryUrl);

        final SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        saxParserFactory.setValidating(true);
        saxParserFactory.setNamespaceAware(false);
        
        final SAXParser parser = saxParserFactory.newSAXParser();
        try {
            parser.parse(repositoryUrl.getFile(), handler);
        }
        finally {
            try {
                conn.close();
            }
            catch (Exception e) { }
        }
    }
    
    /**
     * Test for validating <code>&lt;class-descriptor ... /&gt;</code> definitions in the repository.xml
     * 
     * @throws Exception
     */
    // @Test
    public void verifyClasses() throws Exception {
        final DefaultHandler handler = new ClassValidationHandler(); 
        
        LOG.info("Starting XML validation");
        final URL dtdUrl = getClass().getClassLoader().getResource("repository.dtd");
        final URL repositoryUrl = getClass().getClassLoader().getResource("repository.xml");
        
        LOG.info("Found dtd url " + dtdUrl);
        LOG.info("Found repository url " + repositoryUrl);

        final SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        saxParserFactory.setValidating(true);
        saxParserFactory.setNamespaceAware(false);
        
        final SAXParser parser = saxParserFactory.newSAXParser();
        parser.parse(repositoryUrl.getFile(), handler);
    }

    /**
     * <code>{@link DefaultHandler}</code> for SAX2 for validating classes in the repository.xml. Verifies the following use cases:
     * <ul>
     *   <li><code>class</code> defined in <code>class-descriptor</code> exists</li>
     *   <li>fields in <code>class</code> defined in <code>class-descriptor</code> exist</li>
     *   <li><code>foreignkey</code> and <code>inverse-foreignkey</code> <code>field-ref</code> attribute values exist as properties
     *    in classes.</li>
     * </ul>
     * 
     * @see org.xml.sax.SAXParser
     */
    class ClassValidationHandler extends DefaultHandler {
        private Locator locator;
        private Class currentMappedClass;
        private Class currentClassRef;

        /**
         * Used for constructing <code>{@link SAXParseException}</code> instances
         * 
         * @see org.xml.sax.helpers.DefaultHandler#setDocumentLocator(org.xml.sax.Locator)
         * @see org.xml.sax.SAXParseException
         */
        public void setDocumentLocator(Locator locator) {
            super.setDocumentLocator(locator);
            this.locator = locator;
        }

        /**
         * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
         */
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXParseException {
            if (CLASS_DESCRIPTOR_NAME.equals(qName)) {
                try {
                    setCurrentMappedClass(Class.forName(attributes.getValue(CLASS_ATTRIBUTE_NAME)));
                    LOG.info("Parsing " + CLASS_DESCRIPTOR_NAME + " for " + getCurrentMappedClass().getSimpleName());
                }
                catch (Exception e) {
                    throw createSaxParseException("There is no class named " + attributes.getValue(CLASS_ATTRIBUTE_NAME), e);
                }
            }
            else {
                handleFieldDescriptor(qName, attributes);
                handleReferenceDescriptor(qName, attributes);
                handleCollectionDescriptor(qName, attributes);
                handleForeignKeyDescriptor(qName, attributes);
            }
        }
        
        /**
         * Handles <code>&lt;reference-descriptor ... /&gt;</code> in the repository.xml. Verify that the class
         * defined in the <code>class</code> attribute actually exists. 
         * 
         * @param qName
         * @param attributes
         * @throws SAXParseException if the class does not exist
         */
        private void handleReferenceDescriptor(String qName, Attributes attributes) throws SAXParseException {
            if (REFERENCE_DESCRIPTOR_NAME.equals(qName)) {
                try {
                    setCurrentClassRef(Class.forName(attributes.getValue(CLASS_REF_ATTRIBUTE_NAME)));
                }
                catch (ClassNotFoundException cnfe) {
                    throw createSaxParseException("Could not find " + CLASS_REF_ATTRIBUTE_NAME + " " + attributes.getValue(CLASS_REF_ATTRIBUTE_NAME), cnfe);                
                }
            }
        }

        /**
         * Generic method for finding a property by name within a given <code>{@link Class}</code>. This method uses the 
         * ASF commons <code>{@link PropertyUtils} class</code> to lookup <code>{@link PropertyDescriptor}</code> instances within 
         * a given class</code>. If the specified property given by name isn't found among the names of the <code>{@link PropertyDescriptor}</code>
         * instances, then a <code>{@link SAXParseException}</code> is thrown.
         * 
         * @param clazz Class to look in
         * @param property or name of a property to find
         * @throws SAXParseException is thrown when the property is not found in the class
         */
        private void findFieldInClass(Class clazz, String property) throws SAXParseException {
            try {
                boolean exists = false;
                for (int i = 0; i < getPropertyDescriptors(clazz).length && !exists; i++) {
                    PropertyDescriptor descriptor = getPropertyDescriptors(clazz)[i];
                    exists = descriptor.getName().equals(property);
                }
                if (!exists) {
                    throw new NoSuchFieldException("Property with name " + property + " does not exist in class " + clazz.getName());
                }
            }
            catch (NoSuchFieldException e) {
                throw createSaxParseException("", e);
            }
        }
        
        /**
         * Handles validation of <code>&lt;foreignkey ... /&gt</code> definitions. <code>&lt;foreignkey ... /&gt</code> definitions
         * are typically found nested in <code>&lt;reference-descriptor ... /&gt</code> or <code>&lt;collection-descriptor ... /&gt</code> 
         * definitions. The value of the <code>field-ref</code> attribute a property that must exist within the <code>&lt;class-descriptor ... /&gt</code>
         * for <code>&lt;reference-descriptor ... /&gt</code> definitions and in the value of the <code>element-class-ref</code> attribute
         * of the <code>&lt;collection-descriptor ... /&gt</code>.
         * 
         * @param qName For verifying if it is a reference-descriptor or collection-descriptor
         * @param attributes
         * @throws SAXParseException is thrown if any property does not exist. 
         */
        private void handleForeignKeyDescriptor(String qName, Attributes attributes) throws SAXParseException {
            if (FOREIGN_KEY_NAME.equals(qName)) {
                findFieldInClass(getCurrentMappedClass(), attributes.getValue(FIELD_REF_ATTRIBUTE_NAME));
            }
            else if (INVERSE_FOREIGN_KEY_NAME.equals(qName)) {
                findFieldInClass(getCurrentClassRef(), attributes.getValue(FIELD_REF_ATTRIBUTE_NAME));                
            }
        }

        /**
         * Handles validation of <code>&lt;field-descriptor ... /&gt;</code> definitions. Checks to make sure that the
         * field of the <code>&lt;field-descriptor ... /&gt;</code> definition is a property of the class specified by the
         * enclosing <code>&lt;class-descriptor ... /&gt;</code> definition.
         * 
         * @param qName
         * @param attributes
         * @throws SAXParseException is thrown if any property does not exist
         */
        private void handleFieldDescriptor(String qName, Attributes attributes) throws SAXParseException {
            if (FIELD_DESCRIPTOR_NAME.equals(qName) || COLLECTION_DESCRIPTOR_NAME.equals(qName)) {
                findFieldInClass(getCurrentMappedClass(), attributes.getValue(DEFAULT_ATTRIBUTE_NAME));
            }
        }
        
        /**
         * Just like the <code>{@link #handleReferenceDescriptor(String, Attributes)}</code>, only for <code>&lt;collection-descriptor ... /&gt</code>
         * definitions. This method also makes a call to <code>{@link #handleFieldDescriptor(String, Attributes)}</code> because 
         * it has to validate the same attributes for properties.
         * 
         * @param qName
         * @param attributes
         * @throws SAXParseException is thrown if any property does not exist
         */
        private void handleCollectionDescriptor(String qName, Attributes attributes) throws SAXParseException {
            handleFieldDescriptor(qName, attributes);
            
            if (COLLECTION_DESCRIPTOR_NAME.equals(qName)) {
                try {
                    String collectionClassName = attributes.getValue(COLLECTION_CLASS_NAME);
                    if (collectionClassName != null) {
                        Class.forName(collectionClassName);
                    }
                }
                catch (ClassNotFoundException cnfe) {
                    throw createSaxParseException("Could not find " + COLLECTION_CLASS_NAME + " " + attributes.getValue(COLLECTION_CLASS_NAME), cnfe);
                }
            
                try {
                    setCurrentClassRef(Class.forName(attributes.getValue(EL_CLASS_REF_ATTRIBUTE_NAME)));
                }
                catch (ClassNotFoundException cnfe) {
                    throw createSaxParseException("Could not find " + EL_CLASS_REF_ATTRIBUTE_NAME + " " + attributes.getValue(EL_CLASS_REF_ATTRIBUTE_NAME), cnfe);                
                }
            }
        }

        /**
         * Convenience method for creating a <code>{@link SAXParseException}</code> instance.
         * 
         * @param msg
         * @param e
         * @return SAXParseException
         */
        private SAXParseException createSaxParseException(String msg, Exception e) {
            return new SAXParseException(msg + "\n" + e.getMessage(), locator.getPublicId(), locator.getSystemId(), locator.getLineNumber(), locator.getColumnNumber());
        }

        /**
         * Convenience method for creating a <code>{@link SAXParseException}</code> instance.
         * 
         * @param msg
         * @return SAXParseException
         */
        private SAXParseException createSaxParseException(String msg) {
            return new SAXParseException(msg, locator);
        }

        /**
         * Accessor for currentMappedClass
         * 
         * @return Class
         */
        public Class getCurrentMappedClass() {
            return currentMappedClass;
        }

        /**
         * Accessor for curentMappedClass
         * 
         * @param currentMappedClass
         */
        public void setCurrentMappedClass(Class currentMappedClass) {
            this.currentMappedClass = currentMappedClass;
        }

        /**
         * Accessor for currentClassRef
         * 
         * @return Class
         */
        public Class getCurrentClassRef() {
            return currentClassRef;
        }

        /**
         * Accessor for currentClassRef
         * 
         * @param currentClassRef
         */
        public void setCurrentClassRef(Class currentClassRef) {
            this.currentClassRef = currentClassRef;
        }
        
    }

    /**
     * For parsing the repository.xml file and validating database table information as it goes. Primarily, this will verify that 
     * tables exist in the database, and that the fields of each table exist as they are mapped in the repository.xml file.
     * 
     */
    class TableValidationHandler extends DefaultHandler {
        private Connection connection;
        private Locator locator;
        private String currentTableName;

        /**
         * Default Constructor
         */
        public TableValidationHandler(Connection conn) {
            connection = conn;
        }
        
        /**
         * Used for constructing <code>{@link SAXParseException}</code> instances
         * 
         * @see org.xml.sax.helpers.DefaultHandler#setDocumentLocator(org.xml.sax.Locator)
         * @see org.xml.sax.SAXParseException
         */
        public void setDocumentLocator(Locator locator) {
            super.setDocumentLocator(locator);
            this.locator = locator;
        }

        /**
         * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
         */
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXParseException {
            if (CLASS_DESCRIPTOR_NAME.equals(qName)) {
                setCurrentTableName(attributes.getValue(TABLE_ATTRIBUTE_NAME));
                // LOG.info("Looking for table " + getCurrentTableName());
                ResultSet results = null;
                try {
                    results = getConnection().getMetaData().getTables(null, dsSchema, getCurrentTableName(), new String[] {"TABLE"});
                    
                    boolean found = false;
                    while(results.next() && !found) {
                        String tableNameResult = results.getString("TABLE_NAME");
                        if (getCurrentTableName().equals(tableNameResult)) {
                            found = true;
                        }
                    }
                    
                    if (!found) {
                        LOG.info("Not found table " + getCurrentTableName());
                        
                        // throw createSaxParseException("There is no table named " + attributes.getValue(TABLE_ATTRIBUTE_NAME));
                    }
                    else {
                        LOG.info("Found table " + getCurrentTableName());
                    }
                }
                catch (Exception e) {
                    throw createSaxParseException("There is no table named " + attributes.getValue(TABLE_ATTRIBUTE_NAME), e);
                }
                finally {
                    if (results != null) {
                        try {
                            results.close();
                        }
                        catch (Exception e) {}
                    }
                }
            }
            
            handleFieldDescriptor(qName, attributes);
        }

        private void handleFieldDescriptor(String qName, Attributes attributes) throws SAXParseException {
            if (FIELD_DESCRIPTOR_NAME.equals(qName)) {
                String columnName = attributes.getValue(COLUMN_ATTRIBUTE_NAME).toUpperCase();

                ResultSet results = null;
                try {
                    results = getConnection().getMetaData().getColumns(null, dsSchema, getCurrentTableName(), columnName);
                    
                    boolean found = false;
                    String columnNameResult = null;
                    while(results.next() && !found) {
                        columnNameResult = results.getString("COLUMN_NAME");
                        if (columnName.equals(columnNameResult)) {
                            found = true;
                        }
                    }
                    
                    if (!found) {
                        throw createSaxParseException("There is no column named " + attributes.getValue(COLUMN_ATTRIBUTE_NAME) + " in table " + getCurrentTableName());
                    }
                }
                catch (Exception e) {
                    throw createSaxParseException("There is no column named " + attributes.getValue(COLUMN_ATTRIBUTE_NAME) + " in table " + getCurrentTableName(), e);
                }
                finally {
                    if (results != null) {
                        try {
                            results.close();
                        }
                        catch (Exception e) {}
                    }
                }
            }
            
        }

        /**
         * Convenience method for creating a <code>{@link SAXParseException}</code> instance.
         * 
         * @param msg
         * @param e
         * @return SAXParseException
         */
        private SAXParseException createSaxParseException(String msg, Exception e) {
            return new SAXParseException(msg + "\n" + e.getMessage(), locator.getPublicId(), locator.getSystemId(), locator.getLineNumber(), locator.getColumnNumber());
        }

        /**
         * Convenience method for creating a <code>{@link SAXParseException}</code> instance.
         * 
         * @param msg
         * @return SAXParseException
         */
        private SAXParseException createSaxParseException(String msg) {
            return new SAXParseException(msg, locator);
        }

        public String getCurrentTableName() {
            return currentTableName;
        }

        public void setCurrentTableName(String currentTableName) {
            this.currentTableName = currentTableName;
        }

        public Connection getConnection() {
            return connection;
        }

        public void setConnection(Connection connection) {
            this.connection = connection;
        }
    }
}

