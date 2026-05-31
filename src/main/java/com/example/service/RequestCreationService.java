package com.example.service;

import com.example.entity.AutoClaimTemplate;

public interface RequestCreationService {

    /**
     * Creates a request from the given template.
     * 
     * @param template the template to create request from
     * @return the created request response as Object
     * @throws Exception if creation fails
     */
    Object createRequestFromTemplate(AutoClaimTemplate template) throws Exception;
}
