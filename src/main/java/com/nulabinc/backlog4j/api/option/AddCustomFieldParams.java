package com.nulabinc.backlog4j.api.option;

import com.nulabinc.backlog4j.CustomFieldSetting;
import org.apache.http.message.BasicNameValuePair;

import java.util.List;

/**
 * Parameters for add custom field API.
 *
 * @author nulab-inc
 */
public abstract class AddCustomFieldParams extends PostParams {

    private long projectId;
    private String projectKey;

    public AddCustomFieldParams(long projectId, CustomFieldSetting.FieldType fieldType, String name){
        this.projectId = projectId;
        parameters.add(new BasicNameValuePair("typeId", String.valueOf(fieldType.getIntValue())));
        parameters.add(new BasicNameValuePair("name", name));
    }

    public AddCustomFieldParams(String projectKey, CustomFieldSetting.FieldType fieldType, String name){
        this.projectKey = projectKey;
        parameters.add(new BasicNameValuePair("typeId", String.valueOf(fieldType.getIntValue())));
        parameters.add(new BasicNameValuePair("name", name));
    }

    public String getProjectIdOrKeyString() {
        if(projectKey != null){
            return projectKey;
        }else{
            return String.valueOf(projectId);
        }
    }

    public AddCustomFieldParams applicableIssueTypes(List<Long> applicableIssueTypes) {
        for (Long applicableIssueType :applicableIssueTypes) {
            parameters.add(new BasicNameValuePair("applicableIssueTypes[]", String.valueOf(applicableIssueType)));
        }
        return this;
    }

    public AddCustomFieldParams description(String description) {
        parameters.add(new BasicNameValuePair("description", description));
        return this;
    }

    public AddCustomFieldParams required(boolean required) {
        parameters.add(new BasicNameValuePair("required", String.valueOf(required)));
        return this;
    }

}
