package lv.jug.javaday.androidapp.presentation.feed;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;
import butterknife.InjectView;
import lv.jug.javaday.androidapp.R;
import lv.jug.javaday.androidapp.common.AssetsService;
import lv.jug.javaday.androidapp.common.NetworkService;
import lv.jug.javaday.androidapp.common.StringService;
import lv.jug.javaday.androidapp.common.WindowService;
import lv.jug.javaday.androidapp.presentation.BaseFragment;
import org.apache.http.protocol.HTTP;

import javax.inject.Inject;

public class TwitterFeedFragment extends BaseFragment {

    @Inject
    StringService stringService;

    @Inject
    AssetsService assetsService;

    @Inject
    NetworkService networkService;

    @Inject
    WindowService windowService;

    @InjectView(R.id.progressBar)
    ProgressBar progressBar;

    @InjectView(R.id.webView)
    WebView webView;

    @Override
    protected int contentViewId() {
        return R.layout.twitter_feed;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        WebSettings settings = webView.getSettings();
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
            }
        });

        if (networkService.internetAvailable()){
            int width = windowService.getDisplayWidth();
            String html = String.format(data, width);
            webView.loadDataWithBaseURL("https://twitter.com", html, "text/html", HTTP.UTF_8, null);
        } else {
            webView.loadData(data, "text/html", HTTP.UTF_8);
        }
    }

    private String data =
            "<head>" +
                "<style>" +
                    "* {" +
                        "overflow-x: hidden;" +
                        "max-width:100%%;" +
                    "}" +
                "</style>" +
            "</head>" +
            "<body>" +
                "<div>" +
                    "<a class=\"twitter-timeline\"" +
                        "href=\"https://twitter.com/search?q=%%23jdayriga\" " +
                        "data-widget-id=\"394922021928173568\"" +
                        "data-chrome=\"noheader nofooter\"" +
                        "data-dnt=\"true\"  >" +
                    "</a>" +
                    "<script src=\"file:///android_asset/widgets.js\"></script>"+
                "</div>" +
            "</body>";

    private String noInternet = "";
}
