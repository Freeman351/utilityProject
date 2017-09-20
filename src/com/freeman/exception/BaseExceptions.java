package com.freeman.exception;

import java.util.ArrayList;
import java.util.List;

public class BaseExceptions extends BaseException {

    private List<BaseException> exceptions;

    public BaseExceptions(BaseException ex ) {
        if (ex != null) {
            exceptions = new ArrayList<BaseException>();
            exceptions.add(ex);
        }
    }
    
    public BaseExceptions(List<BaseException> exceptions) {
        this.exceptions = exceptions;
    }

    public void addException(BaseException ex) {
        if (exceptions == null)
            exceptions = new ArrayList<BaseException>();
        exceptions.add(ex);
    }
    
    public List<BaseException> getExceptions() {
        return exceptions;
    }
}
