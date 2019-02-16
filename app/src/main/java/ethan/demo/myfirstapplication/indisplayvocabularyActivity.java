package ethan.demo.myfirstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class indisplayvocabularyActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, CompoundButton.OnCheckedChangeListener {
    private ListView listView2;
    private TextView textView2;
    private Switch aSwitch;
    ArrayList<String> array;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indisplayvocabulary);
        Intent intent = getIntent();
        array =intent.getStringArrayListExtra("array");
        ArrayList<String> ssay = new ArrayList<>();
        for(String o:array){
            ssay.add(o.charAt(0)+"_______");
        }
        String head =intent.getStringExtra("head");
        listView2 = findViewById(R.id.listView2);
        textView2 = findViewById(R.id.textView2);
        textView2.setText(head);
       ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,ssay);
        listView2.setAdapter(adapter);
        listView2.setOnItemClickListener(this);
        aSwitch = findViewById(R.id.switch1);
        aSwitch.setOnCheckedChangeListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    Intent intent = new Intent(this,individialdisplay.class);
    intent.putExtra("individual",array.get(position));
    startActivity(intent);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            if (!buttonView.isPressed()) {
                return;
            }else {
                aSwitch.setChecked(false);
                Intent intent = new Intent(this, DisplayvocabularyActivity.class);
                intent.putStringArrayListExtra("list", array);
                startActivity(intent);
            }
        }
    }
}
