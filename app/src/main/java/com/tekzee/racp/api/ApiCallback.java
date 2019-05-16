package com.tekzee.racp.api;

import com.tekzee.racp.ui.base.model.CommonResult;

import org.json.JSONException;

import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;


public abstract class ApiCallback<M> extends DisposableObserver<M> {

    public abstract void onSuccess(M successResult) throws JSONException;

    public abstract void onFailure(CommonResult commonResult);

    public abstract void onFinish();


    @Override
    public void onComplete() {
        onFinish();
    }


    @Override
    public void onError(Throwable e) {
        CommonResult result = new CommonResult();
        result.setSuccess(false);

        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int code = httpException.code();
            switch (code) {
                case 400:
                    result.setMessage("Error code 400-Bad Request!");
                    break;
                case 401:
                    result.setMessage("Error code 401-Unautorised!");
                    break;
                case 404:
                    result.setMessage("Error code 404-Not found!");
                    break;
                case 405:
                    result.setMessage("Error code 405-Requested method not allowed!");
                    break;
                case 500:
                    result.setMessage("Error code 500-Internal Server error!");
                    break;
                default:
                    if(httpException.getMessage().length()!=0)
                    {
                        result.setMessage(httpException.getMessage());
                    }else {
                        result.setMessage("Something went wrong!");
                    }

                    break;
            }

            onFailure(result);
        } else {
            result.setMessage(e.getLocalizedMessage());
            onFailure(result);
        }
        onFinish();
    }

    @Override
    public void onNext(M m) {
        try {
            onSuccess(m);
        } catch (Exception e) {
            CommonResult result = new CommonResult();
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            onFailure(result);
        }

    }
}
