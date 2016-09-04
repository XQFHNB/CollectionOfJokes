package practice.software.xqf.collectionofjokes.net;

/**
 * Created by dell on 2016/9/4.
 */
public class Picture {
    private String ct;
    private String title;
    private String urlPicture_String;

    public Picture(String ct, String urlPicture_String, String title) {
        super();
        this.ct = ct;
        this.urlPicture_String = urlPicture_String;
        this.title = title;
    }

    public String getCt() {
        return ct;
    }

    public void setCt(String ct) {
        this.ct = ct;
    }

    public String getUrlPicture_String() {
        return urlPicture_String;
    }

    public void setUrlPicture_String(String urlPicture_String) {
        this.urlPicture_String = urlPicture_String;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
