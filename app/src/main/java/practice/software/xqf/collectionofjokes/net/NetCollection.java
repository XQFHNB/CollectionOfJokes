package practice.software.xqf.collectionofjokes.net;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import practice.software.xqf.collectionofjokes.tools.Config;

/**
 * Created by dell on 2016/9/4.
 */
public class NetCollection {
    public NetCollection(final String url, final HttpMethod httpmethod,
                         final SuccessCallback successCallback,
                         final FailCallback failCallback, final String... kvs) {
        // TODO Auto-generated constructor stub
        new AsyncTask<Void, Void, String>() {

            private HttpURLConnection connection;

            @Override
            protected String doInBackground(Void... params) {
                // TODO Auto-generated method stub
                URL urlString;
                StringBuffer paramsStringBuffer = new StringBuffer();
                for (int i = 0; i < kvs.length; i += 2) {
                    paramsStringBuffer.append(kvs[i]).append("=")
                            .append(kvs[i + 1]).append("&");
                }
                try {

                    switch (httpmethod) {
                        case GET:
                            urlString = new URL(url + "?"
                                    + paramsStringBuffer.toString());
                            connection = (HttpURLConnection) urlString
                                    .openConnection();
                            connection.setRequestProperty(Config.KEY_APIKEY,
                                    Config.VALUE_APIKEY);
                            connection.connect();
                            break;

                        default:
                            urlString = new URL(url);
                            HttpURLConnection connection = (HttpURLConnection) urlString
                                    .openConnection();

                            BufferedWriter writer = new BufferedWriter(
                                    new OutputStreamWriter(
                                            connection.getOutputStream(),
                                            Config.CHARSET));
                            writer.write(paramsStringBuffer.toString());
                            writer.flush();
                            writer.close();
                            break;
                    }
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(connection.getInputStream(),
                                    Config.CHARSET));
                    String resultString = new String();
                    StringBuffer resultBuffer = new StringBuffer();
                    while ((resultString = reader.readLine()) != null) {
                        resultBuffer.append(resultString);
                    }
                    return resultBuffer.toString();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
//这里是不用判断的。因为终究会运行最后一句
                }

                return null;
            }

            protected void onPostExecute(String result) {
                if (result != null) {
                    if (successCallback != null) {
                        successCallback.OnSuccess(result);
                    }

                } else {
                    if (failCallback != null) {
                        failCallback.OnFail();
                    }
                }
            }

            ;

        }.execute();
    }

    public static interface SuccessCallback {
        void OnSuccess(String result);
    }

    public static interface FailCallback {
        void OnFail();
    }
}
