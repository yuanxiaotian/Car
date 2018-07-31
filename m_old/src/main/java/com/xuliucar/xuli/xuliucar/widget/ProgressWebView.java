package com.xuliucar.xuli.xuliucar.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.xuliucar.xuli.xuliucar.R;

import java.lang.reflect.InvocationTargetException;

import cn.sharesdk.onekeyshare.OnekeyShare;


public class ProgressWebView extends LinearLayout {

	private WebView mWebView;
	private ProgressBar mProgressBar;
	private Toolbar webToobar;
	private ImageView share;

	private final Context mContext;
	
	private String url;

	private String title;
	private String con;
//	private String errorHtml = "<html><head><meta charset='UTF-8'></head><body><br><br><br><br><br><br><br><div align='center' style='font-size: smaller'  onclick='window.android.refresh()' ><a href='http://www.baidu.com' style='text-decoration: none'>暂无数据 <br/> 点击调用android方法 </a></div></body></html>";

//	@JavascriptInterface
//	public void refresh() {
//		Toast.makeText(mContext, "js 调用方法", Toast.LENGTH_SHORT).show();
//	}


	public ProgressWebView(Context context) {
		this(context, null);
	}


	public ProgressWebView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ProgressWebView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.mContext = context;
		initView(context);
	}

	private void initView(final Context context) {

		View.inflate(context, R.layout.view_web_progress, this);

		mWebView = (WebView) findViewById(R.id.web_view);
		mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
		webToobar = (Toolbar) findViewById(R.id.webToobar);
		share = (ImageView) findViewById(R.id.share);

		webToobar.setNavigationIcon(R.drawable.back);
		webToobar.setNavigationOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {

				((Activity)mContext).finish();

			}
		});

		share.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				showShare();
			}
		});
	}

	private void showShare(){
		OnekeyShare oks = new OnekeyShare();
		//关闭sso授权
		oks.disableSSOWhenAuthorize();

		// title标题：微信、QQ（新浪微博不需要标题）
		oks.setTitle(title);  //最多30个字符

		// text是分享文本：所有平台都需要这个字段
		oks.setText(con);  //最多40个字符

		// imagePath是图片的本地路径：除Linked-In以外的平台都支持此参数
		//oks.setImagePath(Environment.getExternalStorageDirectory() + "/meinv.jpg");//确保SDcard下面存在此张图片

		//网络图片的url：所有平台
		oks.setImageUrl("http://d.hiphotos.baidu.com/image/pic/item/f9dcd100baa1cd1199229564b112c8fcc2ce2dda.jpg");//网络图片rul

		// url：仅在微信（包括好友和朋友圈）中使用
		oks.setUrl(url);   //网友点进链接后，可以看到分享的详情

		// Url：仅在QQ空间使用
		oks.setTitleUrl(url);  //网友点进链接后，可以看到分享的详情

		// 启动分享GUI
		oks.show(mContext);
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public void loadUrl(String url) {
		if(url == null) {
			url = "";
		}
		initWebview(url);
	}

	public void setShare(String title,String url,String con){
		this.title = title;
		this.url = url;
		this.con = con;
	}
	
	@SuppressLint("JavascriptInterface")
	private void initWebview(String url) {

		mWebView.addJavascriptInterface(this, "android");

		WebSettings webSettings = mWebView.getSettings();

		webSettings.setJavaScriptEnabled(true);
		// 设置可以访问文件
		webSettings.setAllowFileAccess(true);
		// 设置可以支持缩放
		webSettings.setSupportZoom(true);
		// 设置默认缩放方式尺寸是far
//		webSettings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
		// 设置出现缩放工具
		webSettings.setBuiltInZoomControls(true);
		//不显示webview缩放按钮
		webSettings.setDisplayZoomControls(false);
		//扩大比例的缩放
		webSettings.setUseWideViewPort(true);
		//自适应屏幕
		webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
		webSettings.setLoadWithOverviewMode(true);
		
		mWebView.loadUrl(url);
		
		// 设置WebViewClient
		mWebView.setWebViewClient(new WebViewClient() {
			// url拦截
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// 使用自己的WebView组件来响应Url加载事件，而不是使用默认浏览器器加载页面
				view.loadUrl(url);
				// 相应完成返回true
				return true;
				// return super.shouldOverrideUrlLoading(view, url);
			}

			// 页面开始加载
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				mProgressBar.setVisibility(View.VISIBLE);
				super.onPageStarted(view, url, favicon);
			}

			// 页面加载完成
			@Override
			public void onPageFinished(WebView view, String url) {
				mProgressBar.setVisibility(View.GONE);
				super.onPageFinished(view, url);
			}

			// WebView加载的所有资源url
			@Override
			public void onLoadResource(WebView view, String url) {
				super.onLoadResource(view, url);
			}

			@Override
			public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
//				view.loadData(errorHtml, "text/html; charset=UTF-8", null);
				super.onReceivedError(view, errorCode, description, failingUrl);
			}

		});


		
		// 设置WebChromeClient
		mWebView.setWebChromeClient(new WebChromeClient() {
			@Override
			// 处理javascript中的alert
			public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
				return super.onJsAlert(view, url, message, result);
			}

			@Override
			// 处理javascript中的confirm
			public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
				return super.onJsConfirm(view, url, message, result);
			}

			@Override
			// 处理javascript中的prompt
			public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, final JsPromptResult result) {
				return super.onJsPrompt(view, url, message, defaultValue, result);
			}

			// 设置网页加载的进度条
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				mProgressBar.setProgress(newProgress);
				super.onProgressChanged(view, newProgress);
			}

			// 设置程序的Title
			@Override
			public void onReceivedTitle(WebView view, String title) {
				super.onReceivedTitle(view, title);
			}
		});
		mWebView.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (event.getAction() == KeyEvent.ACTION_DOWN) {
					if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) { // 表示按返回键
						try {
							mWebView.getClass().getMethod("onPause").invoke(mWebView,(Object[])null);
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						} catch (NoSuchMethodException e) {
							e.printStackTrace();
						}
//						Intent intent = new Intent(mContext,MainActivity.class);
//						intent.putExtra("num","1");
//						mContext.startActivity(intent);
						((Activity)mContext).finish();
						// webview.goForward();//前进
						return true; // 已处理
					}
				}
				return false;
			}
		});
	}

	public boolean canBack() {
		if (mWebView.canGoBack()) {
			mWebView.goBack();
			return false;
		}
		return true;
	}


}
