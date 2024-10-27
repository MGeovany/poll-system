
package com.mgeovany.pollsystem.dtos;

import java.util.List;

public class PollCreationRequest {
    private String title;
    private List<String> options;  

    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}
