package com.screenlake.ktormodule

import com.screenlake.ktormodule.KtorConstants.EMPTY
import com.screenlake.ktormodule.KtorConstants.ERROR_400
import com.screenlake.ktormodule.KtorConstants.ERROR_OCCURRED
import com.screenlake.ktormodule.KtorConstants.SESSION_EXPIRED_CODE

class AppException : Exception {

    private var errorMsg: String
    private var errCode: Int = 0
    private var errorLog: String?=EMPTY
    var isSessionExpired:Boolean?=false

    constructor(errCode: Int?=null, error: String?, errorLog: String? = EMPTY) : super(error) {
        this.errorMsg = error ?: ERROR_OCCURRED
        this.errCode = errCode ?: ERROR_400
        this.errorLog = errorLog?:this.errorMsg
        this.isSessionExpired = errCode == SESSION_EXPIRED_CODE
    }

    constructor(errCode: Int?=null, error: String?) : super(error) {
        this.errorMsg = error ?: ERROR_OCCURRED
        this.errCode = errCode ?: ERROR_400
        this.isSessionExpired = errCode == SESSION_EXPIRED_CODE
    }

    constructor(error: Error, e: Throwable?) {
        errCode = error.getKey()
        errorMsg = error.getValue()
        errorLog = e?.message
        isSessionExpired = error.getKey()== SESSION_EXPIRED_CODE
    }
}