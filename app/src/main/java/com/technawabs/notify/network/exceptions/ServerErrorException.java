package com.technawabs.notify.network.exceptions;

import com.technawabs.notify.constants.AppConstant;

public class ServerErrorException extends HttpException {

    public ServerErrorException() {
        super(AppConstant.MESSAGE_SERVER_ERROR);
    }
}
