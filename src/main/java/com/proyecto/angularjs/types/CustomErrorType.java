package com.proyecto.angularjs.types;

import java.io.Serializable;

public class CustomErrorType implements Serializable {

    private static final long serialVersionUID = 117891093206251416L;
    
	private String errorMessage;

    public CustomErrorType(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
