package lv.jug.javaday.androidapp.presentation.feed;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;
import butterknife.InjectView;
import lv.jug.javaday.androidapp.R;
import lv.jug.javaday.androidapp.application.AssetsService;
import lv.jug.javaday.androidapp.application.NetworkService;
import lv.jug.javaday.androidapp.application.StringService;
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
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
            }
        });

        if (networkService.internetAvailable()){
            webView.loadDataWithBaseURL("https://twitter.com", data, "text/html", HTTP.UTF_8, null);
        } else {
            progressBar.setVisibility(View.VISIBLE);
            String message = stringService.loadString(R.string.no_internet);
            Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
        }
    }

    private String data =
            "<a class=\"twitter-timeline\" " +
                "href=\"https://twitter.com/search?q=%23jdayriga\" " +
                "data-widget-id=\"394922021928173568\">" +
                    "Tweets about \"#jdayriga\"" +
            "</a>" +
            "<script src=\"file:///android_asset/widgets.js\"></script>";
}
