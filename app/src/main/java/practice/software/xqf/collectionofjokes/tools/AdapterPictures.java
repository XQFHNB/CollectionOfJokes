package practice.software.xqf.collectionofjokes.tools;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;

import practice.software.xqf.collectionofjokes.R;
import practice.software.xqf.collectionofjokes.net.Loader_AsyncImage;

/**
 * Created by dell on 2016/9/4.
 */
public class AdapterPictures extends BaseAdapter {


    private Loader_AsyncImage asyncImageLoader;
    List<String> data;
    Context context;
    ImageView iv;
    private ListView listView;
    private ViewHolder viewholder;

    public AdapterPictures(Context context, List<String> list,ListView listView) {
        this.context = context;
        data = list;
        asyncImageLoader = new Loader_AsyncImage();
        this.listView = listView;
        // TODO Auto-generated constructor stub
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.list_view_cell, null);
            iv = (ImageView) convertView.findViewById(R.id.img);
            viewholder = new ViewHolder();
            viewholder.imageview = iv;
            convertView.setTag(viewholder);
        }

        String url = data.get(position).toString();
        iv.setTag(url);

        Drawable cachedImage = asyncImageLoader.load_From_Url(url,
                new Loader_AsyncImage.OnSucessCallback() {
                    @Override
                    public void OnSuccess(String url, Drawable drawable) {
                        ImageView imageViewByTag = (ImageView) listView
                                .findViewWithTag(url);
                        if (imageViewByTag != null) {
                            imageViewByTag.setImageDrawable(drawable);
                        }
                    }


                });

        if (cachedImage == null) {
            iv.setImageResource(R.mipmap.ic_launcher);
        } else {
            iv.setImageDrawable(cachedImage);
        }
        return convertView;
    }

    class ViewHolder {
        ImageView imageview;
    }
}
