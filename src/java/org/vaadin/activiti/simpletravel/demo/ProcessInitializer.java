package org.vaadin.activiti.simpletravel.demo;

import javax.annotation.PostConstruct;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.activiti.simpletravel.identity.Groups;

@Component
public class ProcessInitializer implements Groups {

    @Autowired
    private IdentityService identityService;
    @Autowired
    private RepositoryService repositoryService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @PostConstruct
    public void setUp() {
        setUpGroups();
        setUpUsers();
        deployProcess();
    }

    public void setUpUsers() {
        logger.info("Creating users for Activiti");
        addUser("traveler", "Tom", "Traveler", "tom@foobar.net", null);
        addUser("manager", "Mike", "Manager", "mike@foobar.net", GROUP_MANAGERS);
        addUser("secretary", "Steven", "Secretary", "steven@foobar.net", GROUP_SECRETARIES);
        addUser("payroll", "Patrick", "Payroll", "patrick@foobar.net", GROUP_PAYROLLADMINS);
    }

    private void addUser(String username, String firstName, String lastName, String email, String group) {
        if (!userExists(username)) {
            logger.info("Adding user {} (firstName = {}, lastName = {}, email = {}, group = {})", new String[]{username, firstName, lastName, email, group});
            User user = identityService.newUser(username);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPassword("p");
            identityService.saveUser(user);
            if (group != null) {
                identityService.createMembership(username, group);
            }
            identityService.createMembership(username, GROUP_EMPLOYEES);
        }
    }

    private boolean userExists(String username) {
        return identityService.createUserQuery().userId(username).singleResult() != null;
    }

    private void setUpGroups() {
        logger.info("Creating user groups for Activiti");
        addGroup(GROUP_EMPLOYEES, "Employees");
        addGroup(GROUP_SECRETARIES, "Secretaries");
        addGroup(GROUP_MANAGERS, "Managers");
        addGroup(GROUP_PAYROLLADMINS, "Payroll Administrators");
    }

    private void addGroup(String groupId, String name) {
        if (!groupExists(groupId)) {
            logger.info("Adding group {} (name = {})", new String[]{groupId, name});
            Group group = identityService.newGroup(groupId);
            group.setName(name);
            identityService.saveGroup(group);
        }
    }

    private boolean groupExists(String groupId) {
        return identityService.createGroupQuery().groupId(groupId).singleResult() != null;
    }

    private void deployProcess() {
        if (!isDeployed()) {
            logger.info("Deploying process");
            repositoryService.createDeployment().addClasspathResource("process/simple-travel.bpmn20.xml").deploy();
        }
    }
    
    private boolean isDeployed() {
        return repositoryService.createProcessDefinitionQuery().processDefinitionKey("simple-travel").singleResult() != null;
    }
}
