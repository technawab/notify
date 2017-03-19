package com.technawabs.notify.network.exceptions;

import com.technawabs.notify.constants.AppConstant;

public class AccessDeniedException extends HttpException {
    public AccessDeniedException() {
        super(AppConstant.EXCEPTION.ACCESS_DENIED);
    }
}
