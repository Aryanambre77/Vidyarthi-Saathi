package com.example.history_chatbot_application;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ChatApiService {

    @Headers("Content-Type: application/json")
    @POST("/ask")
    Call<AnswerResponseActivity> sendQuestion(@Body QuestionRequest question);
}