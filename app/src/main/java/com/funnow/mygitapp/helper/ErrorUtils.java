package com.funnow.mygitapp.helper;

import com.funnow.mygitapp.MainActivity;
import com.funnow.mygitapp.models.ApiError;

import java.io.IOException;
import java.lang.annotation.Annotation;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ErrorUtils {

    private Retrofit retrofit;

    @Inject
    public ErrorUtils(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public ApiError parseError(Response<?> response) {
        Converter<ResponseBody, ApiError> converter =
                retrofit.responseBodyConverter(ApiError.class, new Annotation[0]);

        ApiError apiError;

        try {
            apiError = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new ApiError();
        }

        return apiError;
    }

}
