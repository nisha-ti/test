package com.thrivent.controller;

import com.thrivent.model.ExceptionResponse;
import com.thrivent.model.IrserviceInputJson;
import com.thrivent.model.StatusResponse;
import com.thrivent.service.ContentService;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

@RestController
@RequestMapping
public class IdentityResolutionController {
    private static final Logger LOGGER = Logger.getLogger(IdentityResolutionController.class.getName());
    public static final String SUCCESS = "Success";
    public static final String FAILURE = "Failure";

    private ContentService contentService;

    IdentityResolutionController(ContentService contentService) {
        this.contentService = contentService;
    }
    @PostMapping("/processData")
    public StatusResponse processData(@RequestBody IrserviceInputJson requestBody) throws IOException {
        StatusResponse response = new StatusResponse();
        try {
            response = new StatusResponse(SUCCESS, HttpStatus.OK.value(), contentService.processData(requestBody));
        } catch (Exception e) {
            e.printStackTrace();
            ExceptionResponse exceptionresponse = new ExceptionResponse(500, e.getMessage(), new Date());
            response = new StatusResponse(FAILURE, HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    exceptionresponse);
        }
        return response;
    }
}
