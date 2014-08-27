package com.nulabinc.backlog4j.internal.json;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.nulabinc.backlog4j.Milestone;

import java.util.Date;

/**
 * @author nulab-inc
 */
public class MilestoneJSONImpl implements Milestone {

    private long id;
    private long projectId;
    private String name;
    private String description;
    @JsonDeserialize(using = JacksonCustomDateDeserializer.class)
    private Date startDate;
    @JsonDeserialize(using = JacksonCustomDateDeserializer.class)
    private Date releaseDueDate;
    private Boolean archived;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public long getProjectId() {
        return projectId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getReleaseDueDate() {
        return releaseDueDate;
    }

    public Boolean getArchived() {
        return archived;
    }
}
