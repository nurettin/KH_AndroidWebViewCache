package in.nurett.dev.cachedeneme2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends Activity {
    Button btnClearCache;
    Button btnRefreshMenu;
    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setupUI();
        enableCache();
        loadMenu();
    }

    private void setupUI() {
        btnClearCache= (Button) findViewById(R.id.clear_cache);
        btnRefreshMenu= (Button)findViewById(R.id.refresh_menu);
        web= (WebView)findViewById(R.id.web);

        btnClearCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearCache();
                Toast.makeText(view.getContext(), "Cache Cleared!", Toast.LENGTH_SHORT).show();
            }
        });
        btnRefreshMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadMenu();
                Toast.makeText(view.getContext(), "Menu Loaded!", Toast.LENGTH_SHORT).show();
            }
        });
        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    private void loadMenu() {
        web.loadUrl("http://nurett.in:8080/");
    }

    private void enableCache() {
        WebSettings settings = web.getSettings();
        settings.setDomStorageEnabled(true);
        settings.setAppCachePath("/data/data/" + getPackageName() + "/cache");
        settings.setAllowFileAccess(true);
        settings.setAppCacheEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    }

    private void clearCache() {
        web.clearCache(true);
    }
}
