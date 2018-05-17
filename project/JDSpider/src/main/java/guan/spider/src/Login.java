package guan.spider.src;

import org.apache.http.HttpConnection;
import org.apache.http.HttpEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class Login {


    public static void main(String[] args) throws Exception {

String tmp = "https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=false&word=%E7%BE%8E%E5%A5%B3&step_word=&hs=0&pn=1&spn=0&di=89721706800&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=2019270811%2C1269730008&os=336779565%2C265836185&simid=4137292997%2C664325661&adpicid=0&lpn=0&ln=3940&fr=&fmq=1521644982328_R&fm=&ic=0&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=girl&bdtype=0&oriquery=&objurl=http%3A%2F%2Fpic.yesky.com%2FuploadImages%2F2015%2F215%2F45%2F04L5VRR21C5W.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Frtv_z%26e3Byjfhy_z%26e3Bv54AzdH3FkkfAzdH3Fpi6jw1-dda8ad-8-8l_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0";

        downloadPicture(tmp);
    }

    @Test
    public void hGetTest() throws Exception {


//        请求路径
        String url = "https://www.mi.com/?client_id=180100041086&masid=17489.0001&kwd=%E5%B0%8F%E7%B1%B3";

        //初始化一个httpclient
        CloseableHttpClient httpClient = HttpClients.createDefault();

//        指定请求方式
        HttpGet hget = new HttpGet(url);

//        执行请求
        CloseableHttpResponse response = httpClient.execute(hget);

// 获取请求响应
        HttpEntity entity = response.getEntity();

//        解析请求
        String enStr = EntityUtils.toString(entity, Charset.forName("utf-8"));

        System.out.println(enStr);
    }

    @Test
    public void jSoupTest() throws Exception {

        Document doc = Jsoup.parse(new URL("http://www.itheima.com/"), 2000);

//        System.out.println(doc);

        Elements nav_txt = doc.getElementsByClass("nav_txt");


        Elements aList = doc.getElementsByTag("a");


        for (Element ex :aList) {

//            System.out.println(ex.text());
//            System.out.println("====");
//            System.out.println(ex.attr("href"));

        }

        for (Element el :nav_txt){


            Elements aXlist = el.getElementsByTag("a");


            for (Element exl :aXlist){

                System.out.println(exl.text() + " :"+  exl.attr("href") );

            }
            System.out.println();
//            System.out.println("href");
//            System.out.println(el.attr("href"));
//
//            System.out.println("------a[href]-------");
//            System.out.println(el.select("a[href]"));

        }

    }

    @Test
    public void jSoup2Test() throws Exception {

        Document doc = Jsoup.parse(new URL("http://www.163.com/"), 2000);

        Elements els = doc.getElementsByClass("cm_area ns_area_top");




        for (Element exl :els){

//            System.out.println(exl.text() + " :"+  exl.attr("href") );


            System.out.println(exl.text() +" => ");
            Elements alist = exl.select("a");

            for (Element el :alist){

                System.out.print(el.attr("href"));
                System.out.println();;
            }

        }


    }

    @Test
    public static void downLoadFile(String imgUrl ,String localPath)throws  Exception{

        URL url = new URL(imgUrl);


        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        InputStream inputStream = connection.getInputStream();

        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

        FileOutputStream fileOutputStream = new FileOutputStream(localPath +  "/aa.jpg");

        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

        byte[] bytes = new byte[10240];

        int flag = 0;

        while ((flag = bufferedInputStream.read(bytes))!=-1){

            bufferedOutputStream.write(bytes,0,flag);
        }

        bufferedInputStream.close();

        bufferedOutputStream.close();




    }

    private static void downloadPicture(String urlList) {
        URL url = null;
        int imageNumber = 0;

        try {
            url = new URL(urlList);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());

            String imageName =  "/Users/alfred/Desktop/react_Demo/test.jpg";

            FileOutputStream fileOutputStream = new FileOutputStream(new File(imageName));
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[2024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            byte[] context=output.toByteArray();
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
























