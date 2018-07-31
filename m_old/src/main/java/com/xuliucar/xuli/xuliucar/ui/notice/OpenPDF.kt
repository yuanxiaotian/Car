package com.xuliucar.xuli.xuliucar.ui.notice

import android.graphics.Canvas
import android.os.Environment
import cn.sharesdk.onekeyshare.OnekeyShare
import com.cangmaomao.lib.base.BaseActivity
import com.cangmaomao.lib.base.BasePresenter
import com.cangmaomao.lib.utils.StatusBarUtil
import com.joanzapata.pdfview.listener.OnDrawListener
import com.joanzapata.pdfview.listener.OnLoadCompleteListener
import com.joanzapata.pdfview.listener.OnPageChangeListener
import com.xuliucar.xuli.xuliucar.R
import kotlinx.android.synthetic.main.activity_open_file.*
import java.io.File
import java.io.FileOutputStream
import java.net.URL

class OpenPDF : BaseActivity<BasePresenter>(), OnPageChangeListener, OnLoadCompleteListener, OnDrawListener {

    override fun layViewId(): Int = R.layout.activity_open_file

    override fun addViewId(): Int = 0

    private var url: String? = null

    private var pdfName: String? = null
    private var title: String? = null
    private var con: String? = null


    override fun initView() {

        StatusBarUtil.transparencyBar(this)

        val intent = intent
        val bundle = intent.extras
        url = bundle!!.getString("url")
        title = bundle.getString("title")
        con = bundle.getString("con")
        pdfName = url!!.substring(url!!.length - 8, url!!.length)

        getdownload()

        initToolbar(title, null)


        //        ImageView pdfView_share = (ImageView) findViewById(R.id.pdfView_share);
        //下载进度(可选参数，不需要可不传)


        //        pdfView_share.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View view) {
        //                showShare();
        //            }
        //        });
    }


    private fun showShare() {
        val oks = OnekeyShare()
        //关闭sso授权
        oks.disableSSOWhenAuthorize()

        // title标题：微信、QQ（新浪微博不需要标题）
        oks.setTitle(title)  //最多30个字符

        // text是分享文本：所有平台都需要这个字段
        oks.text = con  //最多40个字符

        // imagePath是图片的本地路径：除Linked-In以外的平台都支持此参数
        //        oks.setImagePath(Environment.getExternalStorageDirectory() + "/meinv.jpg");//确保SDcard下面存在此张图片


        //网络图片的url：所有平台
        oks.setImageUrl("http://d.hiphotos.baidu.com/image/pic/item/f9dcd100baa1cd1199229564b112c8fcc2ce2dda.jpg")//网络图片rul

        // url：仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(url)   //网友点进链接后，可以看到分享的详情

        // Url：仅在QQ空间使用
        oks.setTitleUrl(url)  //网友点进链接后，可以看到分享的详情

        // 启动分享GUI
        oks.show(this)
    }


    private fun getdownload() {
        Thread {
            run {
                val path = Environment.getExternalStorageDirectory().path + "/" + pdfName
                try {
                    val bing = URL(url)
                    val bytes = bing.readBytes()
                    val fileInputStream = FileOutputStream(path)
                    fileInputStream.write(bytes)
                    fileInputStream.flush()
                    fileInputStream.close()
                    runOnUiThread { displayFromFile(File(path)) }
                } catch (e: Exception) {
                    com.cangmaomao.lib.utils.e("读取PDF文件出错!")
                }
            }
        }.start()


    }


    private fun displayFromFile(file: File) {
        pdfView.fromFile(file)   //设置pdf文件地址
                .defaultPage(1)         //设置默认显示第1页
                .onPageChange(this)     //设置翻页监听
                .onLoad(this)           //设置加载监听
                .onDraw(this)            //绘图监听
                .showMinimap(false)     //pdf放大的时候，是否在屏幕的右上角生成小地图
                .swipeVertical(true)  //pdf文档翻页是否是垂直翻页，默认是左右滑动翻页
                .enableSwipe(true)   //是否允许翻页，默认是允许翻
                // .pages( 2 , 3 , 4 , 5  )  //把2 , 3 , 4 , 5 过滤掉
                .load()
    }

    /**
     * 翻页回调
     */
    override fun onPageChanged(page: Int, pageCount: Int) {
        //        Toast.makeText( MainActivity.this , "page= " + page +
        //                " pageCount= " + pageCount , Toast.LENGTH_SHORT).show();
    }

    /**
     * 加载完成回调
     *
     * @param nbPages 总共的页数
     */
    override fun loadComplete(nbPages: Int) {

    }

    override fun onLayerDrawn(canvas: Canvas, pageWidth: Float, pageHeight: Float, displayedPage: Int) {
        // Toast.makeText( MainActivity.this ,  "pageWidth= " + pageWidth + "
        // pageHeight= " + pageHeight + " displayedPage="  + displayedPage , Toast.LENGTH_SHORT).show();
    }
}
