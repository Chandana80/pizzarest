package com.ojass.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davulc01 on 2/25/2016.
 */
public class BusinessException extends RuntimeException {
    private List<String> businessMessages ;

    public BusinessException(String s) {
        super(s);
        this.businessMessages = new ArrayList<String>();
        this.businessMessages.add(s);
    }
    public BusinessException(String s,List<String> messages) {
        super(s);
        this.businessMessages = messages;
    }

    public List<String> getBusinessMessages() {
        return businessMessages;
    }
}
