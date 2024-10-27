
package com.mgeovany.pollsystem.dtos;

import java.util.List;
import java.util.UUID;

 
public class PollResponse {
    private UUID id;
    private String title;
    private String uniqueUrl;
    private List<String> options;

    public PollResponse(UUID id, String title, String uniqueUrl, List<String> options) {
        this.id = id;
        this.title = title;
        this.uniqueUrl = uniqueUrl;
        this.options = options;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUniqueUrl() {
        return uniqueUrl;
    }

    public List<String> getOptions() {
        return options;
    }
}
