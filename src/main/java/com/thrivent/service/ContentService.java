package com.thrivent.service;

import com.thrivent.model.IrserviceInputJson;
import org.json.JSONObject;

import java.io.IOException;

public interface ContentService {

    public Object processData(IrserviceInputJson requestData) throws IOException, ClassNotFoundException;
}

