package com.technawabs.notify.network.exceptions;

import com.technawabs.notify.constants.AppConstant;

public class UnsupportedFileTypeException extends HttpException {

    public UnsupportedFileTypeException() {
        super(AppConstant.EXCEPTION.MESSAGE_UNSUPPORTED_FILE_TYPE);
    }

}
