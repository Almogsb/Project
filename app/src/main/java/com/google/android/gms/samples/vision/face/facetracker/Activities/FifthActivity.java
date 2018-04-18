package com.google.android.gms.samples.vision.face.facetracker.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.samples.vision.face.facetracker.Extras.Globals;
import com.google.android.gms.samples.vision.face.facetracker.FaceTrackerActivity;
import com.google.android.gms.samples.vision.face.facetracker.Functions.Functions;
import com.google.android.gms.samples.vision.face.facetracker.R;

public class FifthActivity extends AppCompatActivity {

    private Button b ;
    private TextView t;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);

        iv = (ImageView)findViewById(R.id.imageView2);
        t = (TextView)findViewById(R.id.txt1);
        t.setText(" המרחק הוא" + String.format("%.3f", Globals.distance));
        b = (Button)findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
     /*   ViewGroup.LayoutParams params = iv.getLayoutParams();
        params.height = getActivity().getResources().getDimensionPixelSize(R.dimen.item_height);
        params.width = getActivity().getResources().getDimensionPixelSize(R.dimen.item_width);*/
     }
}
