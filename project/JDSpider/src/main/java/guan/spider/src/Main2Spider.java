package guan.spider.src;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;

public class Main2Spider {


    /**
     *       封装成一个get请求
             使用httpClient发起请求
             得到相应结果
             解析html
     */

    private final static ArrayBlockingQueue<String> blockQureue = new ArrayBlockingQueue<String>(1000);


    private final static ProductDao proDao = new ProductDao();

    public static void main(String[] args) throws Exception {


        new Thread(new Runnable() {
            public void run() {
                while (true){
                    System.out.println("当前还有" + blockQureue.size());;
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


//消费线程

        for (int i = 0 ;i < 50 ;i++){
            new Thread(new Runnable() {
                public void run() {
                    while (true){
                        try {
                            String pid = blockQureue.take();

                            parseProductDetail(pid);
//                            Thread.sleep(1000);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }

        //获取首页数据
        getIndexHtml();

        //获取分页数据
        doPageHtml();
    }

//获取首页数据
    public static void getIndexHtml() throws Exception {


//        请求路径
        String url = "https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&enc=utf-8&wq=%E6%89%8B%E6%9C%BA&pvid=d1441fa9c78e43b8bd7895de46291203";

        String htmlString = getHtml(url);

        getPageProduct(htmlString);

        Thread.sleep(2000);


    }




//    获取分页数据

    private  static  void  doPageHtml() throws Exception {

        int page = 1;

        while (page < 100 ){

            String url = "https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&wq=%E6%89%8B%E6%9C%BA&cid2=653&cid3=655&page="+(2*page -1);

            String pageHtml = getHtml(url);

            getPageProduct(pageHtml);

            page ++;
        }




    }

    private static void getPageProduct(String html) throws Exception{
        Document doc = Jsoup.parse(html);
        Elements lists = doc.select("#J_goodsList li[data-pid]");
        for (Element ele : lists) {
            String pid = ele.attr("data-pid");
//            System.out.println(ele.attr("data-pid"));

            blockQureue.put(pid);

//            获取详情页面数据
//            parseProductDetail(pid);

        }

    }


//    解析详情页生成product对象
    private static void parseProductDetail(String pid) throws Exception {

        String itemUrl = "https://item.jd.com/" + pid + ".html";

        String htmlString = getHtml(itemUrl);


        Document document = Jsoup.parse(htmlString);

        String docText = document.select("[class=parameter2 p-parameter-list] li").get(0).text();
        String colorText = document.select("[class=parameter2 p-parameter-list] li").get(1).text();
        String pinpai = document.select("[class=parameter2 p-parameter-list] li").get(2).text();

        String pipTitle = document.select("[id=parameter-brand] li").text();

        String title = document.select(".sku-name").get(0).text();



        // 获取价格
        String priceURL = "https://p.3.cn/prices/mgets?skuIds=J_" + pid;

        CloseableHttpClient client = HttpClients.createDefault();

        HttpGet hget2 = new HttpGet(priceURL);

        CloseableHttpResponse response2 = client.execute(hget2);


        Product product = new Product();
        product.setName(docText);
        product.setId(pid);
        product.setUrl(itemUrl);
        product.setTitle(title);


        if (response2.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity2 = response2.getEntity();

            String priceString = EntityUtils.toString(entity2, "utf-8");
            System.out.println(priceString);
            Gson jsonItem = new Gson();
            ArrayList list = jsonItem.fromJson(priceString, ArrayList.class);

            Map<String, String> map = (Map<String, String>) list.get(0);
            String priceTitle = map.get("p");
            product.setPrice(priceTitle);
            product.setMaidian(colorText);
            product.setPinpai(pinpai);
            product.setXinhao(pipTitle);
        }

        System.out.println(product);

        proDao.saveProduct(product);
    }


    private static String getHtml(String url) throws Exception {
        //初始化一个httpclient
        CloseableHttpClient httpClient = HttpClients.createDefault();
//        指定请求方式
        HttpGet hget = new HttpGet(url);
//        执行请求
        CloseableHttpResponse response = httpClient.execute(hget);
        if (200 == response.getStatusLine().getStatusCode()) {
            // 获取请求响应
            HttpEntity entity = response.getEntity();
            String htmlString = EntityUtils.toString(entity, Charset.forName("utf-8"));
            return htmlString;
        } else {
            return null;
        }
    }
}
