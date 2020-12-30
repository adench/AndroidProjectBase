package com.xqkj.baselibrary.net;

/**
 * Created by SCYDB on 2018/6/27.
 */
public class HttpErrorHelper {
    public static String validateError(Object error) {

        if(isSSLProblem(error)){
            return "SSL证书错误";
        }
        else if (isTimeOutProblem(error)) {
            return "连接超时，请重新尝试";
        }
        else if (isNetworkProblem(error)) {
            return "网络连接异常";
        }else if(isServerProblem(error)){
            return "服务器开小差了，请稍后再试";
        }

        return "网络连接异常";
    }

    /**
     * 确定是否与网络相关的错误
     * @param error
     * @return
     */
    private static boolean isNetworkProblem(Object error) {
        return String.valueOf(error).contains("NetworkError") ||
                String.valueOf(error).contains("NoConnectionError");
    }
    //ssL 证书无效
    private static boolean isSSLProblem(Object error){
        return  String.valueOf(error).contains("SSLPeerUnverifiedException") ;
    }
    /**
     * 确定是否与服务器相关的错误
     * @param error
     * @return
     */
    private static boolean isServerProblem(Object error) {
        return String.valueOf(error).contains("ServerError")
                || String.valueOf(error).contains("AuthFailureError");
    }

    private static boolean isTimeOutProblem(Object error){
        return String.valueOf(error).contains("Timeout");
    }

}
