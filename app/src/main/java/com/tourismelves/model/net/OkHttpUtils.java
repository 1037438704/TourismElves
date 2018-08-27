package com.tourismelves.model.net;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;
import com.tourismelves.utils.log.LogUtil;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Description : OkHttp网络连接封装工具类  解析用的是Gson 记得添加Gson依赖 或者jar包
 */
public class OkHttpUtils {
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    private static final MediaType MEDIA_TYPE_TEXT = MediaType.parse("application/text; charset=utf-8");

    private static String getMimeType(String filename) {
        FileNameMap filenameMap = URLConnection.getFileNameMap();
        String contentType = filenameMap.getContentTypeFor(filename);
        if (contentType == null) {
            contentType = "application/octet-stream"; //* exe,所有的可执行程序
        }
        return contentType;
    }


    public static final int BUSINESS_ERROR = 403;
    public static final int DEAL_WITH_FAILURE = 410;
    public static final int NO_NET = 404;   //无网络
    public static final int PARSING_ERROR = 501;
    public static final int SERVICE_ERROR = 500;//服务器开小差了
    public static final int TOKEN_FAILURE = 400; //登录失效
    public static final int TOKEN_CANCEL = 410;//取消请求

    private static OkHttpUtils mInstance;
    private OkHttpClient mOkHttpClient;
    private Handler mDelivery;
    private Gson mGson;

    private OkHttpUtils() {
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        mGson = new Gson();
        mDelivery = new Handler(Looper.getMainLooper());
    }

    private synchronized static OkHttpUtils getmInstance() {
        if (mInstance == null) {
            mInstance = new OkHttpUtils();
        }
        return mInstance;
    }

    /**
     * 处理结果
     *
     * @param callback
     * @param request
     */
    private Call deliveryResult(final ResultCallback callback, final Request request) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendFailCallback(callback, new Exception("网络开小差"));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    int code = response.code();
                    String body = response.body().string();
                    log(call, body);

                    if (body.contains("!DOCTYPE HTML")) {
                        sendFailCallback(callback, new Exception("服务器出错啦！"));
                        return;
                    }
                    if (callback.mType == String.class) {
                        /**
                         * 返回字符串
                         */
                        sendSuccessCallBack(callback, body);
                    } else {
                        /**
                         * 这里处理解析返回对象
                         */
                        Object object = mGson.fromJson(body, callback.mType);
                        sendSuccessCallBack(callback, object);

                    }
                } catch (final Exception e) {
                    log(call, e.getMessage());
                    sendFailCallback(callback, new Exception("数据异常"));
                }
            }
        });
        return call;
    }

    private void log(Call call, String body) {
        Request request = call.request();
        LogUtil.i("请求完毕：\n网址：" + request.url() + "\n内容：" + body);
    }

    private void sendFailCallback(final ResultCallback callback, final Exception e) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onFailure(e);
                }
            }
        });
    }

    private void sendSuccessCallBack(final ResultCallback callback, final Object obj) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    try {
                        callback.onSuccess(obj);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * get请求
     */
    private Call getRequest(String url, final ResultCallback callback) {
        final Request request = new Request.Builder()
                .url(url).build();
        return deliveryResult(callback, request);
    }

    /**
     * post请求
     * 表单形式
     */
    private void postRequest(String url, final ResultCallback callback, List<ParamString> params) {
        Request request = buildPostRequest(url, params);
        deliveryResult(callback, request);
    }

    private Request buildPostRequest(String url, List<ParamString> params) {
        FormBody.Builder builder = new FormBody.Builder();
        for (ParamString param : params) {
            builder.add(param.key, param.value);
        }
        RequestBody requestBody = builder.build();
        return new Request.Builder().url(url).post(requestBody).build();
    }

    /**
     * post请求
     * 请求参数以json形式 拼到body里
     */
    private void postRequestObject(String url, final ResultCallback callback, List<ParamObject> params) {
        Request request = buildPostRequestObject(url, params);
        deliveryResult(callback, request);
    }

    private Request buildPostRequestObject(String url, List<ParamObject> params) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < params.size(); i++) {
            ParamObject paramObject = params.get(i);
            String key = paramObject.key;
            Object value = paramObject.value;
            if (i == 0) {
                sb.append("{");
            }

            sb.append("\"").append(key).append("\":");
            if (value instanceof String) {
                if (((String) value).endsWith("}") && ((String) value).startsWith("{")) {
                    sb.append(value);
                } else if (((String) value).endsWith("]") && ((String) value).startsWith("[")) {
                    sb.append(value);
                } else {
                    sb.append("\"").append(value).append("\"");
                }
            } else {
                sb.append(value);
            }

            if (i == params.size() - 1) {
                sb.append("}");
            } else {
                sb.append(",");
            }
        }
        RequestBody requestBody = RequestBody.create(MEDIA_TYPE_TEXT, sb.toString());
        return new Request
                .Builder()
                .url(url)
                .post(requestBody)
                .build();
    }


    /* *********************对外接口************************/

    /**
     * get请求
     */
    public static Call get(String url, ResultCallback callback) {
        return getmInstance().getRequest(url, callback);
    }

    /**
     * post请求
     */
    public static void post(String url, final ResultCallback callback, List<ParamString> params) {
        getmInstance().postRequest(url, callback, params);
    }

    public static void postJson(String url, List<ParamObject> params, final ResultCallback callback) {
        getmInstance().postRequestObject(url, callback, params);
    }

    /**
     * http请求回调类,回调方法在UI线程中执行
     *
     * @param <T>
     */
    public static abstract class ResultCallback<T> {

        Type mType;

        public ResultCallback() {
            mType = getSuperclassTypeParameter(getClass());
        }

        static Type getSuperclassTypeParameter(Class<?> subclass) {
            Type superclass = subclass.getGenericSuperclass();
            if (superclass instanceof Class) {
                throw new RuntimeException("Missing type parameter.");
            }
            ParameterizedType parameterized = (ParameterizedType) superclass;
            return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
        }

        /**
         * 请求成功回调
         */
        public abstract void onSuccess(T response);

        /**
         * 请求失败回调
         */
        public abstract void onFailure(Exception e);
    }
}
