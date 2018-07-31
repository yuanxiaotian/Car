package com.xuliucar.xuli.xuliucar.ui.notice;

import android.content.Intent;
import android.os.Bundle;

import com.xuliucar.xuli.xuliucar.base.BaseActivity;
import com.xuliucar.xuli.xuliucar.utils.ToastUtil;
import com.xuliucar.xuli.xuliucar.widget.ProgressWebView;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.w3c.dom.Document;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import cn.sharesdk.framework.ShareSDK;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class World extends BaseActivity {
    private String url;
    private String docName ;//文档的名字
    private String title;
    private ProgressWebView mWebView;
    private String con;
    private String mSavePath;


    @Override
    protected void preInitView() {
        super.preInitView();
        getData();
        getdownload();
        initViews();
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(mWebView);
    }


    private void getData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        url = bundle.getString("url");
        title = bundle.getString("title");
        con = bundle.getString("con");
        docName = url.substring(url.length() - 5, url.length());
    }
    private void initViews() {
        mWebView = new ProgressWebView(this);
        mWebView.setShare(title,url,con);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ShareSDK.stopSDK(this);
    }

    private void getdownload() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShortToast(getApplicationContext(),"网络连接失败！");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String name = docName.substring(0,docName.indexOf("."));
                mSavePath = "/mnt/sdcard/documents/";
                File file = new File(mSavePath);
                File file1 = new File(mSavePath+name);
                if(!file.exists()){
                    file.mkdir();
                }

                InputStream inputStream = response.body().byteStream();
                FileOutputStream fileOutputStream ;
                try{
                    fileOutputStream = new FileOutputStream(file+"/"+docName);
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = inputStream.read(buffer)) != -1){
                        fileOutputStream.write  (buffer,0,len);
                    }
                    fileOutputStream.flush();
                }catch (IOException e){
                    e.printStackTrace();
                }
                try {
                    if(!file1.exists()){
                        file1.mkdir();
                    }
                    convert2Html(mSavePath+docName, mSavePath+name+".html");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mWebView.loadUrl("file:/"+mSavePath+name+".html");
                    }
                });

            }
        });
    }

    /**
     * word文档转成html格式
     * */
    public void convert2Html(String fileName, String outPutFile)
            throws TransformerException, IOException,
            ParserConfigurationException {
        HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(fileName));
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
                DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());

        //设置图片路径
        wordToHtmlConverter.setPicturesManager(new PicturesManager()
        {
            public String savePicture(byte[] content,
                                      PictureType pictureType, String suggestedName,
                                      float widthInches, float heightInches )
            {
                String name = docName.substring(0,docName.indexOf("."));
                return name+"/"+suggestedName;
            }
        } );

        //保存图片
        List<Picture> pics=wordDocument.getPicturesTable().getAllPictures();
        if(pics!=null){
            for(int i=0;i<pics.size();i++){
                Picture pic = pics.get(i);
                System.out.println( pic.suggestFullFileName());
                try {
                    String name = docName.substring(0,docName.indexOf("."));
                    pic.writeImageContent(new FileOutputStream(mSavePath+ name + "/"
                            + pic.suggestFullFileName()));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        wordToHtmlConverter.processDocument(wordDocument);
        Document htmlDocument = wordToHtmlConverter.getDocument();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(out);

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        serializer.transform(domSource, streamResult);
        out.close();
        //保存html文件
        writeFile(new String(out.toByteArray()), outPutFile);
    }

    /**
     * 将html文件保存到sd卡
     * */
    public void writeFile(String content, String path) {
        FileOutputStream fos = null;
        BufferedWriter bw = null;
        try {
            File file = new File(path);
            if(!file.exists()){
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            bw = new BufferedWriter(new OutputStreamWriter(fos,"utf-8"));
            bw.write(content);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fos != null)
                    fos.close();
            } catch (IOException ie) {
            }
        }
    }



}
