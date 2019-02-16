package ethan.demo.myfirstapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class individialdisplay extends AppCompatActivity {
    private TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individialdisplay);
        textView3 = findViewById(R.id.textView3);
        Intent intent = getIntent();
        String display = intent.getStringExtra("individual");
        textView3.setText(display);
    }
}
