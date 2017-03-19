package com.technawabs.notify.network.exceptions;

import com.technawabs.notify.constants.AppConstant;

public class UnauthorizedAccessException extends HttpException {

    public UnauthorizedAccessException() {
        super(AppConstant.EXCEPTION.UNAUTHORIZED_ACCESS);
    }
}
