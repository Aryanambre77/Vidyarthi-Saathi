package com.example.history_chatbot_application;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatbotActivity extends AppCompatActivity {

    EditText input;
    Button sendButton;
    TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ✅ FIRST set the layout
        setContentView(R.layout.activity_main);

        // ✅ THEN find views
        input = findViewById(R.id.editTextQuestion);
        sendButton = findViewById(R.id.buttonSend);
        responseText = findViewById(R.id.textViewAnswer);

        // ✅ THEN set EdgeToEdge and WindowInsets
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // ✅ Set up button click after views are initialized
        sendButton.setOnClickListener(v -> {
            String question = input.getText().toString().trim();
            if (question.isEmpty()) {
                responseText.setText("Please enter a question.");
                return;
            }

            QuestionRequest request = new QuestionRequest(question);
            ChatApiService apiService = ApiClient.getClient().create(ChatApiService.class);
            Call<AnswerResponseActivity> call = apiService.sendQuestion(request);

            call.enqueue(new Callback<AnswerResponseActivity>() {
                @Override
                public void onResponse(Call<AnswerResponseActivity> call, Response<AnswerResponseActivity> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        responseText.setText(response.body().getAnswer());
                    } else {
                        responseText.setText("Error: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<AnswerResponseActivity> call, Throwable t) {
                    responseText.setText("Failed: " + t.getMessage());
                }
            });
        });
    }
}
