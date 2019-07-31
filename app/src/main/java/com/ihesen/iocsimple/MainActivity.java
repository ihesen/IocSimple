package com.ihesen.iocsimple;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ihesen.ioclibrary.annotation.ContentView;
import com.ihesen.ioclibrary.annotation.InjectView;
import com.ihesen.ioclibrary.annotation.OnClick;
import com.ihesen.ioclibrary.annotation.OnLongClick;

@ContentView(R.layout.activity_main) // setContentView
public class MainActivity extends BaseActivity {

    @InjectView(R.id.btn)
    private Button btn;
    @InjectView(R.id.textview)
    TextView textView;

//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }


    @Override
    protected void onResume() {
        super.onResume();

//        btn = findViewById(R.id.btn);
//        Toast.makeText(this, btn.getText().toString(), Toast.LENGTH_SHORT).show();
    }


    //监听的方法名 setOnClickListener
    //监听的对象 View.OnClickListener
    //回调方法 onClick(View v)
    @OnClick({R.id.btn, R.id.textview})
    public void onClick(View view) {
        Toast.makeText(this, "OnClick: " + btn.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    @OnLongClick({R.id.btn, R.id.textview})
    public boolean onLongClick() {
        Toast.makeText(this, "onLongClick: " + textView.getText().toString(), Toast.LENGTH_SHORT).show();
        return false;
    }
    //ceshi

}
