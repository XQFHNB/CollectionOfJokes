package practice.software.xqf.collectionofjokes.atys;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import practice.software.xqf.collectionofjokes.R;
import practice.software.xqf.collectionofjokes.net.GetPictures;
import practice.software.xqf.collectionofjokes.net.HttpMethod;
import practice.software.xqf.collectionofjokes.net.Picture;
import practice.software.xqf.collectionofjokes.tools.AdapterPictures;
import practice.software.xqf.collectionofjokes.tools.Config;

/**
 * Created by dell on 2016/9/4.
 */
public class AtyShowPictures extends Activity {

    private ListView listView;
    private AdapterPictures adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_show_pictures);
        listView = (ListView) findViewById(R.id.picture_listview);
        new GetPictures(Config.URL_SEVER, HttpMethod.GET, new GetPictures.SuccessCallback() {
            @Override
            public void OnSuccess(ArrayList<Picture> list) {
                ArrayList<String> listString = new ArrayList<String>();
                for (Picture picture : list) {
                    listString.add(picture.getUrlPicture_String());
                    adapter = new AdapterPictures(AtyShowPictures.this, listString, listView);
                }
            }
        }, new GetPictures.FailCallback() {
            @Override
            public void OnFail() {

            }
        });
    }


}

