package com.jaeden.pin.api;

import com.jaeden.pin.domain.request.SaveTemplateIdRequest;
import com.jaeden.pin.infrastructure.cache.TemplateCache;
import com.jaeden.pin.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类TemplateController.java
 *
 */
@RestController
@RequestMapping("template")
public class TemplateController {
    @Autowired
    private CacheService cacheService;
    @Autowired
    private TemplateCache templateCache;

    private String blackStr = "the formId is a mock one";

    @PostMapping("save")
    public void save(@RequestBody SaveTemplateIdRequest request) {
        if (request.getTemplateId().contains(blackStr)) {
            return;
        }
        Integer userId = cacheService.getUserId(request.getToken());
        templateCache.setId(userId, request.getTemplateId());
    }
}
