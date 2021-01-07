package com.bt.mp3.entity.exception

enum class CleanExceptionType(val code: Int) {

    DATA_NULL(0),
    DATA_EMPTY(0),
    DATA_NULL_OR_EMPTY(0),

    INPUT_NULL(0),
    INPUT_EMPTY(0),
    INPUT_NULL_OR_EMPTY(0),
    INPUT_SHORT(0),
    INPUT_LONG(0),

    PARAM_NULL(0),
    PARAM_EMPTY(0),
    PARAM_NULL_OR_EMPTY(0),

    NETWORK_NO_CONNECTION(0),
    NETWORK_TIMEOUT(0),
    NETWORK_BAD_REQUEST(0),

    APP_FORCE_UPDATE(0),
    SERVER_MAINTENANCE(0),
    UNAUTHORIZED(0),

    UNKNOWN(0)
}
