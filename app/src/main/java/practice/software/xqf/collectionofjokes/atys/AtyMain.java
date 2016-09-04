package practice.software.xqf.collectionofjokes.atys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import practice.software.xqf.collectionofjokes.R;

public class AtyMain extends AppCompatActivity {
    private Button textButton, pictureButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aty_main);
        textButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startActivity(new Intent(AtyMain.this, AtyShowTexts.class));
            }

        });
        pictureButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                startActivity(new Intent(AtyMain.this,
                        AtyShowPictures.class));
            }

        });
    }
}
