package com.example.PresProProject;

import lombok.Data;

@Data
public class ApiResponse {

    private String responseCode;
    private String responseMessage;
    private Object data;
}
