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
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Organization model object.
 */
public class Organization {
    private String id;
    private URL url;
    private UUID externalId;
    private String name;
    private List<String> domainNames;
    private ZonedDateTime createdAt;
    private String details;
    private boolean sharedTickets;
    private List<String> tags;

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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDomainNames() {
        return this.domainNames;
    }

    public void setDomainNames(List<String> domainNames) {
        this.domainNames = domainNames;
    }

    public ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getDetails() {
        return this.details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isSharedTickets() {
        return this.sharedTickets;
    }

    public boolean getSharedTickets() {
        return this.sharedTickets;
    }

    public void setSharedTickets(boolean sharedTickets) {
        this.sharedTickets = sharedTickets;
    }

    public List<String> getTags() {
        return this.tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}