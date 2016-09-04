package practice.software.xqf.collectionofjokes.net;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import practice.software.xqf.collectionofjokes.tools.Config;

/**
 * Created by dell on 2016/9/4.
 */
public class GetPictures {
    public GetPictures(String url, HttpMethod httpMethod,
                       final SuccessCallback successCallback,
                       final FailCallback failCallback) {

        new NetCollection(url, HttpMethod.GET,
                new NetCollection.SuccessCallback() {

                    @Override
                    public void OnSuccess(String result) {
                        // TODO Auto-generated method stub
                        ArrayList<Picture> pictureList = new ArrayList<Picture>();
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            JSONObject jsonObject2 = jsonObject
                                    .getJSONObject(Config.KEY_SHOW_RES_BODY);
                            String value_allNum = jsonObject2
                                    .getString(Config.KEY_ALLNUM);
                            System.out.println("The result :" + value_allNum);
                            JSONArray jsonArray = jsonObject2
                                    .getJSONArray(Config.KEY_CONTENTLIST);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                String value_ct = jsonArray.getJSONObject(i)
                                        .getString(Config.KEY_CT);
                                System.out.println("ct:" + value_ct);
                                String value_img = jsonArray.getJSONObject(i)
                                        .getString(Config.KEY_IMG);
                                System.out.println("url image:" + value_img);
                                String value_title = jsonArray.getJSONObject(i)
                                        .getString(Config.KEY_TITLE);

                                pictureList.add(new Picture(value_ct,
                                        value_img, value_title));
                                System.out.println("I=:" + i);
                            }

                            String value_currentPage = jsonObject2
                                    .getString(Config.KEY_CURRENTPAGE);
                            if (successCallback != null) {
                                successCallback.OnSuccess(pictureList);
                            }
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                            if (failCallback != null) {
                                failCallback.OnFail();
                            }
                        }
                    }

                }, new NetCollection.FailCallback() {

            @Override
            public void OnFail() {
                // TODO Auto-generated method stub
                if (failCallback != null) {
                    failCallback.OnFail();
                }
            }
        }, Config.KEY_PAGE, 1 + "");// 在设置刷新的时候肯定在这里要进行动态的改变
    }

    /**
     * 通过给定的图片URL获取图片，这里使用所谓的什么线程池，其中获取操作也是被封装。
     *
     * @param value_img
     * @return
     */

    public static interface SuccessCallback {
        void OnSuccess(ArrayList<Picture> list);
    }

    public static interface FailCallback {
        void OnFail();
    }
}
