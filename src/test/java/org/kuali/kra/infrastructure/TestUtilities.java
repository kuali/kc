/*
 * Copyright 2005-2007 The Kuali Foundation.
 *
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
package org.kuali.kra.infrastructure;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.sql.DataSource;

import junit.framework.Assert;
import junit.framework.AssertionFailedError;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.apache.ojb.broker.PBKey;
import org.kuali.kra.bo.CustomAttribute;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.StatementCallback;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springmodules.orm.ojb.PersistenceBrokerTemplate;

import edu.iu.uis.eden.KEWServiceLocator;
import edu.iu.uis.eden.SpringLoader;
import edu.iu.uis.eden.actionitem.ActionItem;
import edu.iu.uis.eden.actionrequests.ActionRequestValue;
import edu.iu.uis.eden.clientapp.WorkflowDocument;
import edu.iu.uis.eden.clientapp.vo.NetworkIdVO;
import edu.iu.uis.eden.engine.node.RouteNodeInstance;
import edu.iu.uis.eden.exception.EdenUserNotFoundException;
import edu.iu.uis.eden.exception.WorkflowException;
import edu.iu.uis.eden.user.AuthenticationUserId;
import edu.iu.uis.eden.user.WorkflowUser;
import edu.iu.uis.eden.util.Utilities;

/**
 * Defines utilities for unit testing
 */
public class TestUtilities {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(TestUtilities.class);

    private static final String TEST_TABLE_NAME = "EN_UNITTEST_T";
    private static Thread exceptionThreader;

    private static List<String> BUS_TABLES = new ArrayList<String>();
    
    // custom attributes

    public static final String GROUP_NAME_1 = "asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf";
    public static final String GROUP_NAME_2 = "Personnel Items for Review";
    public static final String GROUP_NAME_3 = "Project Details";
    public static final String BILLING_ELEMENT_VALUE = "This is billing element";
    public static final String GRADUATE_STUDENT_COUNT_VALUE = "5";
    public static final String LOCAL_REVIEW_DATE_VALUE = "02/08/2008";
    public static final String TENURED_VALUE = "tdurkin";


    static {
    	BUS_TABLES.add("EN_SERVICE_DEF_T");
    	BUS_TABLES.add("EN_SERVICE_DEF_DUEX_T");
    	BUS_TABLES.add("EN_SERVICE_DEF_INTER_T");
    	BUS_TABLES.add("EN_MESSAGE_QUE_T");
    	BUS_TABLES.add("EN_BAM_T");
    	BUS_TABLES.add("EN_BAM_PARAM_T");
    }

    private TestUtilities() {
        // prevent construction
    }

    public static InputStream loadResource(Class packageClass, String resourceName) {
    	return packageClass.getResourceAsStream(resourceName);
    }

//    public static PersistedMessage createRouteQueue(DocumentRouteHeaderValue routeHeader) throws Exception {
//        Assert.assertNotNull(routeHeader);
//        Assert.assertNotNull(routeHeader.getRouteHeaderId());
//    	return createRouteQueue(routeHeader.getRouteHeaderId().toString());
//    }
//
//    public static PersistedMessage createRouteQueue(String payload) throws Exception {
//        PersistedMessage routeQueue = new PersistedMessage();
//        routeQueue.setIpNumber(InetAddress.getLocalHost().getHostAddress());
//        // set the time back 60 seconds so that we can pull it from the queue
//        routeQueue.setQueueDate(new Timestamp(new Date().getTime()-60*1000));
//        routeQueue.setQueuePriority(new Integer(0));
//        routeQueue.setQueueStatus("Q");
//        routeQueue.setRetryCount(new Integer(0));
//        routeQueue.setPayload( KSBServiceLocator.getMessageHelper().serializeObject(payload));
//        routeQueue.setMessageEntity(Core.getCurrentContextConfig().getMessageEntity());
//        KEWServiceLocator.getRouteQueueService().save(routeQueue);
//        return routeQueue;
//    }

//	/**
//	 * There's no easy way to wire up additional topics from the test harness because it's the
//	 * workflow server's point of view.  At the moment we'd have to override the ksbconfigurer in the
//	 * test spring files or give the ksbconfigurer an option to do nothing but register services...
//	 *
//	 * @throws Exception
//	 */
//	public static void programmaticallyRegisterTestHarnessTopic(QName serviceName, Object service) throws Exception {
//		ServiceDefinition serviceDef = new JavaServiceDefinition();
//		serviceDef.setPriority(3);
//		serviceDef.setRetryAttempts(3);
//		serviceDef.setService(service);
//		serviceDef.setServiceName(serviceName);
//		serviceDef.setQueue(false);
//		try {
//			serviceDef.validate();
//		} catch (Exception e) {
//			throw new WorkflowRuntimeException(e);
//		}
//		KEWServiceLocator.getServiceDeployer().registerService(serviceDef);
//		// force a refresh on our node
//		RemoteResourceServiceLocatorImpl remoteResourceServiceLocator = (RemoteResourceServiceLocatorImpl) GlobalResourceLoader.getResourceLoader(new QName(Core.getCurrentContextConfig().getMessageEntity(),
//				ResourceLoader.REMOTE_RESOURCE_LOADER_NAME));
//		remoteResourceServiceLocator.run();
//	}

    public static TransactionTemplate getTransactionTemplate() {
		return (TransactionTemplate)SpringLoader.getInstance().getBean(KEWServiceLocator.TRANSACTION_TEMPLATE);
	}

    public static void verifyTestEnvironment(DataSource dataSource) {
        if (dataSource == null) {
            Assert.fail("Could not locate the EDEN data source.");
        }
        JdbcTemplate template = new JdbcTemplate(dataSource);
        template.execute(new ConnectionCallback() {
            public Object doInConnection(Connection connection) throws SQLException {
                ResultSet resultSet = connection.getMetaData().getTables(null, null, TEST_TABLE_NAME, null);
                if (!resultSet.next()) {
                    LOG.error("No table named '"+TEST_TABLE_NAME+"' was found in the configured database.  " +
                            "You are attempting to run tests against a non-test database!!!");
                    LOG.error("The test environment will not start up properly!!!");
                    Assert.fail("No table named '"+TEST_TABLE_NAME+"' was found in the configured database.  " +
                    		"You are attempting to run tests against a non-test database!!!");
                }
                return null;
            }
        });
    }

    public static void clearTables(final PlatformTransactionManager transactionManager, final DataSource dataSource, final String edenSchemaName, final List<String> dontClear) {
        LOG.info("Clearing tables for schema " + edenSchemaName);
        if (dataSource == null) {
            Assert.fail("Null data source given");
        }
        if (edenSchemaName == null || edenSchemaName.equals("")) {
            Assert.fail("Empty eden schema name given");
        }
        new TransactionTemplate(transactionManager).execute(new TransactionCallback() {
            public Object doInTransaction(TransactionStatus status) {
                verifyTestEnvironment(dataSource);
                JdbcTemplate template = new JdbcTemplate(dataSource);
                return template.execute(new StatementCallback() {
                    public Object doInStatement(Statement statement) throws SQLException {
                        List<String> reEnableConstraints = new ArrayList<String>();
                    	ResultSet resultSet = statement.getConnection().getMetaData().getTables(null, edenSchemaName, null, new String[] { "TABLE" });
                        while (resultSet.next()) {
                            String tableName = resultSet.getString("TABLE_NAME");
                            if (tableName.startsWith("EN_") && !dontClear.contains(tableName)) {
                            	ResultSet keyResultSet = statement.getConnection().getMetaData().getExportedKeys(null, edenSchemaName, tableName);
                            	while (keyResultSet.next()) {
                            		String fkName = keyResultSet.getString("FK_NAME");
                            		String fkTableName = keyResultSet.getString("FKTABLE_NAME");
                            		statement.addBatch("ALTER TABLE "+fkTableName+" DISABLE CONSTRAINT "+fkName);
                            		reEnableConstraints.add("ALTER TABLE "+fkTableName+" ENABLE CONSTRAINT "+fkName);
                            	}
                            	keyResultSet.close();
                            	statement.addBatch("DELETE FROM "+tableName);
                            }
                        }
                        for (String constraint : reEnableConstraints) {
                    		statement.addBatch(constraint);
                    	}
                        statement.executeBatch();
                        resultSet.close();
                        return null;
                    }
                });
            }
        });
        LOG.info("Tables successfully cleared for schema " + edenSchemaName);
    }

    public static Set<String> createNodeInstanceNameSet(Collection nodeInstances) {
    	Set<String> nameSet = new HashSet<String>();
    	for (Iterator iterator = nodeInstances.iterator(); iterator.hasNext(); ) {
			RouteNodeInstance nodeInstance = (RouteNodeInstance) iterator.next();
			nameSet.add(nodeInstance.getName());
		}
    	return nameSet;
    }

    /**
     * Checks that the document is at a node with the given name.  This does not check that the document is at
     * the given node and only the given node, the document can be at other nodes as well and this assertion
     * will still pass.
     */
    public static void assertAtNode(String message, WorkflowDocument document, String nodeName) throws WorkflowException {
		String[] nodeNames = document.getNodeNames();
		for (int index = 0; index < nodeNames.length; index++) {
			String docNodeName = nodeNames[index];
			if (docNodeName.equals(nodeName)) {
				return;
			}
		}
		throw new AssertionFailedError((Utilities.isEmpty(message) ? "" : message + ": ") + "Was [" + StringUtils.join(nodeNames, ", ") + "], Expected " + nodeName);
	}

    public static void assertAtNode(WorkflowDocument document, String nodeName) throws WorkflowException {
    	assertAtNode("", document, nodeName);
    }

    /**
     * Asserts that the given document id is in the given user's action list.
     */
    public static void assertInActionList(NetworkIdVO networkId, Long documentId) throws EdenUserNotFoundException {
    	WorkflowUser user = KEWServiceLocator.getUserService().getWorkflowUser(networkId);
    	Assert.assertNotNull("Given network id was invalid: " + networkId, user);
    	Collection actionList = KEWServiceLocator.getActionListService().findByWorkflowUser(user);
    	for (Iterator iterator = actionList.iterator(); iterator.hasNext();) {
			ActionItem actionItem = (ActionItem) iterator.next();
			if (actionItem.getRouteHeaderId().equals(documentId)) {
				return;
			}
		}
    	Assert.fail("Could not locate an action item in the user's action list for the given document id.");
    }

    /**
     * Asserts that the given document id is NOT in the given user's action list.
     */
    public static void assertNotInActionList(NetworkIdVO networkId, Long documentId) throws EdenUserNotFoundException {
    	WorkflowUser user = KEWServiceLocator.getUserService().getWorkflowUser(networkId);
    	Assert.assertNotNull("Given network id was invalid: " + networkId, user);
    	Collection actionList = KEWServiceLocator.getActionListService().findByWorkflowUser(user);
    	for (Iterator iterator = actionList.iterator(); iterator.hasNext();) {
			ActionItem actionItem = (ActionItem) iterator.next();
			if (actionItem.getRouteHeaderId().equals(documentId)) {
				Assert.fail("Found an action item in the user's acton list for the given document id.");
			}
		}
    }

    public static void assertNumberOfPendingRequests(Long documentId, int numberOfPendingRequests) {
    	List actionRequests = KEWServiceLocator.getActionRequestService().findPendingByDoc(documentId);
    	Assert.assertEquals("Wrong number of pending requests for document: " + documentId, numberOfPendingRequests, actionRequests.size());
    }

    /**
     * Asserts that the user with the given network id has a pending request on the given document
     */
    public static void assertUserHasPendingRequest(Long documentId, String networkId) throws WorkflowException {
    	WorkflowUser user = KEWServiceLocator.getUserService().getWorkflowUser(new AuthenticationUserId(networkId));
    	List actionRequests = KEWServiceLocator.getActionRequestService().findPendingByDoc(documentId);
    	boolean foundRequest = false;
    	for (Iterator iterator = actionRequests.iterator(); iterator.hasNext();) {
			ActionRequestValue actionRequest = (ActionRequestValue) iterator.next();
			if (actionRequest.isUserRequest() && actionRequest.getWorkflowUser().getAuthenticationUserId().getAuthenticationId().equals(networkId)) {
				foundRequest = true;
				break;
			} else if (actionRequest.isWorkgroupRequest() && actionRequest.getWorkgroup().hasMember(user)) {
				foundRequest = true;
				break;
			}
		}
    	Assert.assertTrue("Could not locate pending request for the given user: " + networkId, foundRequest);
    }

    /**
     * Asserts that the specified users do or do not have outstanding approvals
     * @param docId the id of the document
     * @param users the list of users
     * @param shouldHaveApproval whether they should have an approval outstanding
     * @throws WorkflowException
     */
    public static void assertApprovals(Long docId, String[] users, boolean shouldHaveApproval) throws WorkflowException {
        List<String> failedUsers = new ArrayList<String>();
        for (String user: users) {
            WorkflowDocument doc = new WorkflowDocument(new NetworkIdVO(user), docId);
            boolean appRqsted = doc.isApprovalRequested();
            if (shouldHaveApproval != appRqsted) {
                failedUsers.add(user);
            }
            LOG.info("User " + user + (appRqsted ? " HAS " : " HAS NO ") + "approval request");
        }
        for (String user: failedUsers) {
            LOG.error("User " + user + (shouldHaveApproval ? " should have " : " should NOT have ") + " approval");
        }
        if (failedUsers.size() > 0) {
            Assert.fail("Outstanding approvals are incorrect");
        }
    }

    public static void logActionRequests(Long docId) {
        List<ActionRequestValue> actionRequests = KEWServiceLocator.getActionRequestService().findAllActionRequestsByRouteHeaderId(docId);
        LOG.info("Current action requests:");
        for (ActionRequestValue ar: actionRequests) {
            LOG.info(ar);
        }
    }

    public static PersistenceBrokerTemplate getPersistenceBrokerTemplate() {
    	PersistenceBrokerTemplate pbt = new PersistenceBrokerTemplate();
    	pbt.setPbKey(new PBKey("enWorkflowDataSource"));
    	pbt.setDataSource(KEWServiceLocator.getDataSource());
    	pbt.setJcdAlias("enWorkflowDataSource");
    	pbt.afterPropertiesSet();
    	return pbt;
    }

    /**
     * Waits "indefinately" for the exception routing thread to terminate.
     *
     * This actually doesn't wait forever but puts an upper bound of 5 minutes
     * on the time to wait for the exception routing thread to complete.  If a
     * document cannot go into exception routing within 5 minutes  then we got
     * problems.
     */
    public static void waitForExceptionRouting() {
    	waitForExceptionRouting(5*60*1000);
    }

    public static void waitForExceptionRouting(long milliseconds) {
    	try {
    		getExceptionThreader().join(milliseconds);
    	} catch (InterruptedException e) {
    		Assert.fail("This thread was interuppted while waiting for exception routing.");
    	}
    	if (getExceptionThreader().isAlive()) {
    		Assert.fail("Document was not put into exception routing within the specified amount of time " + milliseconds);
    	}
    }

    public static Thread getExceptionThreader() {
        return exceptionThreader;
    }

    public static void setExceptionThreader(Thread exceptionThreader) {
        TestUtilities.exceptionThreader = exceptionThreader;
    }

    private static final String DEFAULT_TEST_PLATFORM = "oracle";
	private static final String BUILD_PROPERTIES = "build.properties";
	private static final String TEST_PLATFORM = "test.platform";

    /**
     * Attempts to derive the database "platform" to use for unit tests by
     * inspected Ant build.properties files in typical locations.
     * @return the test platform if so defined in Ant build.properties file(s), or the DEFAULT_TEST_PLATFORM otherwise
     * @see #DEFAULT_TEST_PLATFORM
     * @throws IOException if anything goes awry
     */
	public static String getTestPlatform() throws IOException {
        // check the user's build.properties in user's home
		File userBuildProperties = new File(SystemUtils.USER_HOME + "/" + BUILD_PROPERTIES);
		if (userBuildProperties.isFile()) {
			Properties properties = loadProperties(userBuildProperties);
			if (properties.containsKey(TEST_PLATFORM)) {
				return properties.getProperty(TEST_PLATFORM).toLowerCase();
			}
		}
		// check the "local" build.properties in the current directory
        File localBuildProperties = new File(BUILD_PROPERTIES);
        if (localBuildProperties.isFile()) {
            Properties properties = loadProperties(localBuildProperties);
            if (properties.containsKey(TEST_PLATFORM)) {
                return properties.getProperty(TEST_PLATFORM).toLowerCase();
            }
        }
		return DEFAULT_TEST_PLATFORM.toLowerCase();
	}

    /**
     * Loads a file into a Properties object
     * @param file the file
     * @return a Properties object
     */
    private static Properties loadProperties(File file) throws IOException {
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(file);
        try {
            properties.load(fis);
        } finally {
            fis.close();
        }
        return properties;
    }

//	public static void clearDatabase() throws Exception {
//		new ClearDatabaseLifecycle().start();
//	}
//
//	public static void clearNonBusDatabase() throws Exception {
//		new ClearDatabaseLifecycle(BUS_TABLES).start();
//	}

	public static File createTempDir() throws Exception {
		File tmpFile = File.createTempFile("wfUnitTest", "");
		Assert.assertTrue(tmpFile.delete());
		File tmpDir = new File(new File(SystemUtils.JAVA_IO_TMPDIR), tmpFile.getName());
		Assert.assertTrue(tmpDir.mkdir());
		tmpDir.deleteOnExit();
		return tmpDir;
}
	public static File getEnPluginsDirectory() {
		return new File("./work/unit-test/en-plugins");
	}

	public static File getPluginsDirectory() {
		return new File("./work/unit-test/plugins");
	}

	public static void initializePluginDirectories() throws Exception {
		File enPluginDir = getEnPluginsDirectory();
		if (enPluginDir.exists()) {
			FileUtils.forceDelete(enPluginDir);
		}
		File pluginDir = getPluginsDirectory();
		if (pluginDir.exists()) {
			FileUtils.forceDelete(pluginDir);
		}
		FileUtils.forceMkdir(enPluginDir);
		FileUtils.forceMkdir(pluginDir);
		FileUtils.forceDeleteOnExit(enPluginDir);
		FileUtils.forceDeleteOnExit(pluginDir);
	}

	public static void cleanupPluginDirectories() throws Exception {
		FileUtils.deleteDirectory(getEnPluginsDirectory());
		FileUtils.deleteDirectory(getPluginsDirectory());
	}

	/**
     * This method searches for an exception of the specified type in the exception stack
     * @param topLevelException the exception whose stack to traverse
     * @param exceptionClass the exception class to look for
     * @return the first instance of an exception of the specified class if found, or null otherwise
     */
    public static <T extends Throwable> T findExceptionInStack(Throwable topLevelException, Class<T> exceptionClass) {
        Throwable t = topLevelException;
        while (t != null) {
            if (exceptionClass.isAssignableFrom(t.getClass())) return (T) t;
            t = t.getCause();
        }
        return null;
    }
    
    // custom attriutes
    public static Map<String, CustomAttributeDocument> setupTestCustomAttributeDocuments() {
        Map<String, CustomAttributeDocument> customAttributeDocuments = new HashMap<String, CustomAttributeDocument>();

        CustomAttribute customAttribute = buildCustomAttribute(1, "billingElement", "Billing Element", "1", new Integer(40), GROUP_NAME_1);
        CustomAttributeDocument customAttributeDocument = buildCustomAttributeDocument("PRDV", true, customAttribute);
        customAttributeDocuments.put("1", customAttributeDocument);

        customAttribute = buildCustomAttribute(2, "costSharingBudget", "Cost Sharing Budget", "1", new Integer(30), GROUP_NAME_1);
        customAttributeDocument = buildCustomAttributeDocument("PRDV", false, customAttribute);
        customAttributeDocuments.put("2", customAttributeDocument);

        customAttribute = buildCustomAttribute(3, "numberOfTrainees", "# of Trainees", "2", new Integer(6), GROUP_NAME_1);
        customAttributeDocument = buildCustomAttributeDocument("PRDV", false, customAttribute);
        customAttributeDocuments.put("3", customAttributeDocument);

        customAttribute = buildCustomAttribute(4, "graduateStudentCount", "Graduate Student Count", "2", new Integer(6), GROUP_NAME_2);
        customAttributeDocument = buildCustomAttributeDocument("PRDV", true, customAttribute);
        customAttributeDocuments.put("4", customAttributeDocument);

        customAttribute = buildCustomAttribute(5, "tenured", "Tenured", "1", new Integer(30), GROUP_NAME_2, "org.kuali.kra.bo.Person", "userName");
        customAttributeDocument = buildCustomAttributeDocument("PRDV", false, customAttribute);
        customAttributeDocuments.put("5", customAttributeDocument);

        customAttribute = buildCustomAttribute(6, "exportControls", "Export Controls", "1", new Integer(30), GROUP_NAME_3);
        customAttributeDocument = buildCustomAttributeDocument("PRDV", false, customAttribute);
        customAttributeDocuments.put("6", customAttributeDocument);

        customAttribute = buildCustomAttribute(7, "inventions", "Inventions", "1", new Integer(30), GROUP_NAME_3, "org.kuali.kra.bo.Person", "userName");
        customAttributeDocument = buildCustomAttributeDocument("PRDV", false, customAttribute);
        customAttributeDocuments.put("7", customAttributeDocument);

        customAttribute = buildCustomAttribute(8, "localReviewDate", "Local Review Date", "3", new Integer(10), GROUP_NAME_1);
        customAttributeDocument = buildCustomAttributeDocument("PRDV", false, customAttribute);
        customAttributeDocuments.put("8", customAttributeDocument);

        return customAttributeDocuments;
    }

    /**
     * This method...
     * @return
     */
    private static CustomAttributeDocument buildCustomAttributeDocument(String documentTypeCode, boolean required, CustomAttribute customAttribute) {
        CustomAttributeDocument customAttributeDocument = new CustomAttributeDocument();
        customAttributeDocument.setCustomAttributeId(customAttribute.getId());
        customAttributeDocument.setDocumentTypeCode(documentTypeCode);
        customAttributeDocument.setRequired(required);

        customAttributeDocument.setCustomAttribute(customAttribute);
        return customAttributeDocument;
    }

    private static CustomAttribute buildCustomAttribute(Integer id, String name, String label, String dataTypeCode, Integer dataLength, String groupName) {
        return buildCustomAttribute(id, name, label, dataTypeCode, dataLength, groupName, null, null);
    }

    /**
     * This method...
     * @return
     */
    private static CustomAttribute buildCustomAttribute(Integer id, String name, String label, String dataTypeCode, Integer dataLength, String groupName, String lookupClass, String lookupReturn) {
        CustomAttribute customAttribute = new CustomAttribute();

        customAttribute.setId(id);
        customAttribute.setName(name);
        customAttribute.setLabel(label);
        customAttribute.setDataTypeCode(dataTypeCode);
        customAttribute.setDataLength(dataLength);
        customAttribute.setGroupName(groupName);
        customAttribute.setLookupClass(lookupClass);
        customAttribute.setLookupReturn(lookupReturn);

        return customAttribute;
    }

}