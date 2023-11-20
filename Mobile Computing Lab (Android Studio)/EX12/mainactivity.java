
package com.example.webpage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

	private WebView webView;
	private EditText url;
	private Button getButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		webView = findViewById(R.id.webView);
		url = findViewById(R.id.url);
		getButton = findViewById(R.id.load);
		webView.getSettings().setJavaScriptEnabled(true);
		String staticHtml = "<html>\n" +
				"<body>\n" +
				"\n" +
				"<h1 style=\"color:red; font-family:sans-serif\">This is a HTML" +
				"Site</h1>\n" +
				"\n" +
				"<p style=\"color:red;\">A red paragraph.</p>\n" +
				"\n" +
				"<h2>An Unordered HTML List</h2>\n" +
				"\n" +
				"<ul>\n" +
				" <li>MAD lab</li>\n" +
				" <li>GML lab</li>\n" +
				" <li>SNA lab</li>\n" +
				"</ul>\n" +
				"\n" +
				"</body>\n" +
				"</html>";
		webView.loadData(staticHtml, "text/html", "UTF-8");
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// view.loadUrl(url);
				System.out.println("hello");
				return false;
			}
		});
		getButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				webView.loadUrl("https://" + url.getText().toString());
			}
		});
	}

}
