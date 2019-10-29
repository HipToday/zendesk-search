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
 * User model object.
 */
public class User {
    @SerializedName("_id") private Integer id;
    private URL url;
    private UUID externalId;
    private String name;
    private String alias;
    private String createdAt;
    private Boolean active;
    private Boolean verified;
    private Boolean shared;
    private String locale;
    private String timezone;
    private String lastLoginAt;
    private String email;
    private String phone;
    private String signature;
    private Integer organizationId;
    private List<String> tags;
    private Boolean suspended;
    private String role;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean isActive() {
        return this.active;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean isVerified() {
        return this.verified;
    }

    public Boolean getVerified() {
        return this.verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public Boolean isShared() {
        return this.shared;
    }

    public Boolean getShared() {
        return this.shared;
    }

    public void setShared(Boolean shared) {
        this.shared = shared;
    }

    public String getLocale() {
        return this.locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getTimezone() {
        return this.timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getLastLoginAt() {
        return this.lastLoginAt;
    }

    public void setLastLoginAt(String lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSignature() {
        return this.signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
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

    public Boolean isSuspended() {
        return this.suspended;
    }

    public Boolean getSuspended() {
        return this.suspended;
    }

    public void setSuspended(Boolean suspended) {
        this.suspended = suspended;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", url='" + getUrl() + "'" +
            ", externalId='" + getExternalId() + "'" +
            ", name='" + getName() + "'" +
            ", alias='" + getAlias() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", active='" + isActive() + "'" +
            ", verified='" + isVerified() + "'" +
            ", shared='" + isShared() + "'" +
            ", locale='" + getLocale() + "'" +
            ", timezone='" + getTimezone() + "'" +
            ", lastLoginAt='" + getLastLoginAt() + "'" +
            ", email='" + getEmail() + "'" +
            ", phone='" + getPhone() + "'" +
            ", signature='" + getSignature() + "'" +
            ", organizationId='" + getOrganizationId() + "'" +
            ", tags='" + getTags() + "'" +
            ", suspended='" + isSuspended() + "'" +
            ", role='" + getRole() + "'" +
            "}";
    }
}
