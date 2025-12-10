package com.example.history_chatbot_application;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class AboutFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        WebView webView = view.findViewById(R.id.youtubeWebView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadData("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/VIDEO_ID\" frameborder=\"0\" allowfullscreen></iframe>",
                "text/html", "utf-8");

        return view;
    }
}
