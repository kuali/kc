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
package org.kuali.coeus.common.framework.person.citi;

import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PersonTrainingCitiRecord extends KcPersistableBusinessObjectBase {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String employeeNumber;
    private String crNumber;
    private String curriculum;
    private String groupId;
    private String group;
    private String score;
    private String passingScore;
    private String stageNumber;
    private String stage;
    private String learnerId;
    private Timestamp dateCompleted;
    private Timestamp expirationDate;
    private Timestamp registrationDate;
    private String name;
    private String username;
    private String institutionalUsername;
    private String institutionalLanguage;
    private String institutionalEmail;
    private String gender;
    private String highestDegree;
    private String department;
    private String addressField1;
    private String addressField2;
    private String addressField3;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private String phone;
    private String customField1;
    private String customField2;
    private String customField3;
    private String customField4;
    private String customField5;
    private String module;
    private String examId;
    private String examScore;
    private Timestamp moduleCompletionDate;
    private String rawRecord;
    private String statusCode;

    private List<PersonTrainingCitiRecordError> errors = new ArrayList<>();

    private transient KcPersonService kcPersonService;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getCrNumber() {
        return crNumber;
    }

    public void setCrNumber(String crNumber) {
        this.crNumber = crNumber;
    }

    public String getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(String curriculum) {
        this.curriculum = curriculum;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getPassingScore() {
        return passingScore;
    }

    public void setPassingScore(String passingScore) {
        this.passingScore = passingScore;
    }

    public String getStageNumber() {
        return stageNumber;
    }

    public void setStageNumber(String stageNumber) {
        this.stageNumber = stageNumber;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getLearnerId() {
        return learnerId;
    }

    public void setLearnerId(String learnerId) {
        this.learnerId = learnerId;
    }

    public Timestamp getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(Timestamp dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public Timestamp getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Timestamp expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getInstitutionalUsername() {
        return institutionalUsername;
    }

    public void setInstitutionalUsername(String institutionalUsername) {
        this.institutionalUsername = institutionalUsername;
    }

    public String getInstitutionalLanguage() {
        return institutionalLanguage;
    }

    public void setInstitutionalLanguage(String institutionalLanguage) {
        this.institutionalLanguage = institutionalLanguage;
    }

    public String getInstitutionalEmail() {
        return institutionalEmail;
    }

    public void setInstitutionalEmail(String institutionalEmail) {
        this.institutionalEmail = institutionalEmail;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHighestDegree() {
        return highestDegree;
    }

    public void setHighestDegree(String highestDegree) {
        this.highestDegree = highestDegree;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAddressField1() {
        return addressField1;
    }

    public void setAddressField1(String addressField1) {
        this.addressField1 = addressField1;
    }

    public String getAddressField2() {
        return addressField2;
    }

    public void setAddressField2(String addressField2) {
        this.addressField2 = addressField2;
    }

    public String getAddressField3() {
        return addressField3;
    }

    public void setAddressField3(String addressField3) {
        this.addressField3 = addressField3;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCustomField1() {
        return customField1;
    }

    public void setCustomField1(String customField1) {
        this.customField1 = customField1;
    }

    public String getCustomField2() {
        return customField2;
    }

    public void setCustomField2(String customField2) {
        this.customField2 = customField2;
    }

    public String getCustomField3() {
        return customField3;
    }

    public void setCustomField3(String customField3) {
        this.customField3 = customField3;
    }

    public String getCustomField4() {
        return customField4;
    }

    public void setCustomField4(String customField4) {
        this.customField4 = customField4;
    }

    public String getCustomField5() {
        return customField5;
    }

    public void setCustomField5(String customField5) {
        this.customField5 = customField5;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getExamScore() {
        return examScore;
    }

    public void setExamScore(String examScore) {
        this.examScore = examScore;
    }

    public Timestamp getModuleCompletionDate() {
        return moduleCompletionDate;
    }

    public void setModuleCompletionDate(Timestamp moduleCompletionDate) {
        this.moduleCompletionDate = moduleCompletionDate;
    }

    public String getRawRecord() {
        return rawRecord;
    }

    public void setRawRecord(String rawRecord) {
        this.rawRecord = rawRecord;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public List<PersonTrainingCitiRecordError> getErrors() {
        return errors;
    }

    public void setErrors(List<PersonTrainingCitiRecordError> errors) {
        this.errors = errors;
    }

    public KcPerson getInstitutionalPerson() {
        return getKcPersonService().getKcPersonByUserName(getInstitutionalUsername());
    }

    public void addError(PersonTrainingCitiRecordError err) {
        if (errors == null) {
            errors = new ArrayList<>();
        }

        errors.add(err);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonTrainingCitiRecord)) return false;

        PersonTrainingCitiRecord that = (PersonTrainingCitiRecord) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (employeeNumber != null ? !employeeNumber.equals(that.employeeNumber) : that.employeeNumber != null)
            return false;
        if (crNumber != null ? !crNumber.equals(that.crNumber) : that.crNumber != null)
            return false;
        if (curriculum != null ? !curriculum.equals(that.curriculum) : that.curriculum != null) return false;
        if (groupId != null ? !groupId.equals(that.groupId) : that.groupId != null) return false;
        if (group != null ? !group.equals(that.group) : that.group != null) return false;
        if (score != null ? !score.equals(that.score) : that.score != null) return false;
        if (passingScore != null ? !passingScore.equals(that.passingScore) : that.passingScore != null) return false;
        if (stageNumber != null ? !stageNumber.equals(that.stageNumber) : that.stageNumber != null) return false;
        if (stage != null ? !stage.equals(that.stage) : that.stage != null) return false;
        if (learnerId != null ? !learnerId.equals(that.learnerId) : that.learnerId != null) return false;
        if (dateCompleted != null ? !dateCompleted.equals(that.dateCompleted) : that.dateCompleted != null)
            return false;
        if (expirationDate != null ? !expirationDate.equals(that.expirationDate) : that.expirationDate != null)
            return false;
        if (registrationDate != null ? !registrationDate.equals(that.registrationDate) : that.registrationDate != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (institutionalUsername != null ? !institutionalUsername.equals(that.institutionalUsername) : that.institutionalUsername != null)
            return false;
        if (institutionalLanguage != null ? !institutionalLanguage.equals(that.institutionalLanguage) : that.institutionalLanguage != null)
            return false;
        if (institutionalEmail != null ? !institutionalEmail.equals(that.institutionalEmail) : that.institutionalEmail != null)
            return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;
        if (highestDegree != null ? !highestDegree.equals(that.highestDegree) : that.highestDegree != null)
            return false;
        if (department != null ? !department.equals(that.department) : that.department != null) return false;
        if (addressField1 != null ? !addressField1.equals(that.addressField1) : that.addressField1 != null)
            return false;
        if (addressField2 != null ? !addressField2.equals(that.addressField2) : that.addressField2 != null)
            return false;
        if (addressField3 != null ? !addressField3.equals(that.addressField3) : that.addressField3 != null)
            return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (zipCode != null ? !zipCode.equals(that.zipCode) : that.zipCode != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (customField1 != null ? !customField1.equals(that.customField1) : that.customField1 != null) return false;
        if (customField2 != null ? !customField2.equals(that.customField2) : that.customField2 != null) return false;
        if (customField3 != null ? !customField3.equals(that.customField3) : that.customField3 != null) return false;
        if (customField4 != null ? !customField4.equals(that.customField4) : that.customField4 != null) return false;
        if (customField5 != null ? !customField5.equals(that.customField5) : that.customField5 != null) return false;
        if (module != null ? !module.equals(that.module) : that.module != null) return false;
        if (examId != null ? !examId.equals(that.examId) : that.examId != null) return false;
        if (examScore != null ? !examScore.equals(that.examScore) : that.examScore != null) return false;
        if (moduleCompletionDate != null ? !moduleCompletionDate.equals(that.moduleCompletionDate) : that.moduleCompletionDate != null)
            return false;
        return statusCode != null ? statusCode.equals(that.statusCode) : that.statusCode == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (employeeNumber != null ? employeeNumber.hashCode() : 0);
        result = 31 * result + (crNumber != null ? crNumber.hashCode() : 0);
        result = 31 * result + (curriculum != null ? curriculum.hashCode() : 0);
        result = 31 * result + (groupId != null ? groupId.hashCode() : 0);
        result = 31 * result + (group != null ? group.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (passingScore != null ? passingScore.hashCode() : 0);
        result = 31 * result + (stageNumber != null ? stageNumber.hashCode() : 0);
        result = 31 * result + (stage != null ? stage.hashCode() : 0);
        result = 31 * result + (learnerId != null ? learnerId.hashCode() : 0);
        result = 31 * result + (dateCompleted != null ? dateCompleted.hashCode() : 0);
        result = 31 * result + (expirationDate != null ? expirationDate.hashCode() : 0);
        result = 31 * result + (registrationDate != null ? registrationDate.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (institutionalUsername != null ? institutionalUsername.hashCode() : 0);
        result = 31 * result + (institutionalLanguage != null ? institutionalLanguage.hashCode() : 0);
        result = 31 * result + (institutionalEmail != null ? institutionalEmail.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (highestDegree != null ? highestDegree.hashCode() : 0);
        result = 31 * result + (department != null ? department.hashCode() : 0);
        result = 31 * result + (addressField1 != null ? addressField1.hashCode() : 0);
        result = 31 * result + (addressField2 != null ? addressField2.hashCode() : 0);
        result = 31 * result + (addressField3 != null ? addressField3.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (zipCode != null ? zipCode.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (customField1 != null ? customField1.hashCode() : 0);
        result = 31 * result + (customField2 != null ? customField2.hashCode() : 0);
        result = 31 * result + (customField3 != null ? customField3.hashCode() : 0);
        result = 31 * result + (customField4 != null ? customField4.hashCode() : 0);
        result = 31 * result + (customField5 != null ? customField5.hashCode() : 0);
        result = 31 * result + (module != null ? module.hashCode() : 0);
        result = 31 * result + (examId != null ? examId.hashCode() : 0);
        result = 31 * result + (examScore != null ? examScore.hashCode() : 0);
        result = 31 * result + (moduleCompletionDate != null ? moduleCompletionDate.hashCode() : 0);
        result = 31 * result + (statusCode != null ? statusCode.hashCode() : 0);
        return result;
    }

    public KcPersonService getKcPersonService() {
        if (this.kcPersonService == null) {
            this.kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        }
        return this.kcPersonService;
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }
}
