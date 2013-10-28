package lv.jug.javaday.androidapp.presentation.feed;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import butterknife.InjectView;
import lv.jug.javaday.androidapp.R;
import lv.jug.javaday.androidapp.presentation.BaseFragment;
import org.apache.http.protocol.HTTP;

public class TwitterFeedFragment extends BaseFragment {

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
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
            }
        });
        webView.loadDataWithBaseURL("https://twitter.com", data, "text/html", HTTP.UTF_8, null);
    }

    private String data =
            "<a class=\"twitter-timeline\" " +
                "href=\"https://twitter.com/search?q=%23jdayriga\" " +
                "data-widget-id=\"394922021928173568\">" +
                    "Tweets about \"#jdayriga\"" +
            "</a>" +
            "<script>!function(d,s,id){" +
                        "var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';" +
                        "if(!d.getElementById(id)){" +
                            "js=d.createElement(s);" +
                            "js.id=id;" +
                            "js.src=p+\"://platform.twitter.com/widgets.js\";" +
                            "fjs.parentNode.insertBefore(js,fjs);" +
                        "}" +
                    "}" +
                    "(document,\"script\",\"twitter-wjs\");" +
            "</script>";
}
