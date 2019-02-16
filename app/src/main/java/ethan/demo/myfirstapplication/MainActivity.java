package ethan.demo.myfirstapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener {
    public static final String EXTRA_MESSAGE = "ethan.demo.myfirstapplication.MESSAGE";
    private DBhelper dBhelper;
    private ListView mylistView;
    private Spinner myspinner;
    private EditText editText;
    private static String DATABASES_DIR;
    private final static String DATABASE_NAME = "englishstudy.db";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        copyDatabaseFile(this,true);
        dBhelper = new DBhelper(this);
        mylistView = findViewById(R.id.listView1);
        myspinner = findViewById(R.id.spinner1);
        editText = findViewById(R.id.editText1);
        changespinner();
        mylistView.setOnItemLongClickListener(this);
    }

    public static void copyDatabaseFile(Context context, boolean isfored) {
        DATABASES_DIR = context.getApplicationInfo().dataDir+"/databases/";
        File dir = new File(DATABASES_DIR);
        if (!dir.exists() || isfored) {
            try {
                dir.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        File dest = new File(dir, DATABASE_NAME);
        if(dest.exists() && !isfored){
            return ;
        }

        try {
            if(dest.exists()){
                dest.delete();
            }
            dest.createNewFile();
            InputStream in = context.getResources().openRawResource(R.raw.englishstudy);
            int size = in.available();
            byte buf[] = new byte[size];
            in.read(buf);
            in.close();
            FileOutputStream out = new FileOutputStream(dest);
            out.write(buf);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void changespinner(){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,dBhelper.getdata());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspinner.setAdapter(adapter);
        myspinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] sarray = dBhelper.getdata(position+1);
                ArrayAdapter<String> adapter1 = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_expandable_list_item_1,sarray);
                mylistView.setAdapter(adapter1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(MainActivity.this, indisplayvocabularyActivity.class);
        intent.putExtra("head",String.valueOf(parent.getItemAtPosition(position)));
        ArrayList<String> array =  dBhelper.getdata(myspinner.getSelectedItemPosition()+1,position+1);
        intent.putStringArrayListExtra("array",array);
        startActivity(intent);
      return false;
    }
}
