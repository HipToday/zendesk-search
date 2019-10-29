/*
 * Copyright (c) 2019 Nick Templeton
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.nicktempleton.zendesk.search.model;

import java.net.URL;
import java.util.List;
import java.util.UUID;

import com.google.gson.annotations.SerializedName;

/**
 * Ticket model object.
 */
public class Ticket{
    @SerializedName("_id") private String id;
    private URL url;
    private UUID externalId;
    private String createdAt;
    private String type;
    private String subject;
    private String description;
    private String priority;
    private String status;
    private Integer submitterId;
    private Integer assigneeId;
    private Integer organizationId;
    private List<String> tags;
    private Boolean hasIncidents;
    private String dueAt;
    private String via;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public URL getUrl() {
        return this.url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public UUID getExternalId() {
        return this.externalId;
    }

    public void setExternalId(UUID externalId) {
        this.externalId = externalId;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return this.priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSubmitterId() {
        return this.submitterId;
    }

    public void setSubmitterId(Integer submitterId) {
        this.submitterId = submitterId;
    }

    public Integer getAssigneeId() {
        return this.assigneeId;
    }

    public void setAssigneeId(Integer assigneeId) {
        this.assigneeId = assigneeId;
    }

    public Integer getOrganizationId() {
        return this.organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public List<String> getTags() {
        return this.tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Boolean isHasIncidents() {
        return this.hasIncidents;
    }

    public Boolean getHasIncidents() {
        return this.hasIncidents;
    }

    public void setHasIncidents(Boolean hasIncidents) {
        this.hasIncidents = hasIncidents;
    }

    public String getDueAt() {
        return this.dueAt;
    }

    public void setDueAt(String dueAt) {
        this.dueAt = dueAt;
    }

    public String getVia() {
        return this.via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", url='" + getUrl() + "'" +
            ", externalId='" + getExternalId() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", type='" + getType() + "'" +
            ", subject='" + getSubject() + "'" +
            ", description='" + getDescription() + "'" +
            ", priority='" + getPriority() + "'" +
            ", status='" + getStatus() + "'" +
            ", submitterId='" + getSubmitterId() + "'" +
            ", assigneeId='" + getAssigneeId() + "'" +
            ", organizationId='" + getOrganizationId() + "'" +
            ", tags='" + getTags() + "'" +
            ", hasIncidents='" + isHasIncidents() + "'" +
            ", dueAt='" + getDueAt() + "'" +
            ", via='" + getVia() + "'" +
            "}";
    }
}
