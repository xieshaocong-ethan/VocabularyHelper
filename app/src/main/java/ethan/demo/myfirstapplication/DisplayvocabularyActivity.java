package ethan.demo.myfirstapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplayvocabularyActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private TextView textView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayvocabulary);
        Intent intent = getIntent();
        ArrayList<String> array =intent.getStringArrayListExtra("list");
        String head =intent.getStringExtra("head");
        mRecyclerView = (RecyclerView) findViewById(R.id.rv1);
        textView1 = findViewById(R.id.textView1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        EthanRecycleAdapter ethanRecycleAdapter = new EthanRecycleAdapter(array);
        textView1.setText(head);
        mRecyclerView.setAdapter(ethanRecycleAdapter);
    }
}
