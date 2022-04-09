package com.pyrojewel.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.time.nlp.TimeNormalizer;
import com.time.nlp.TimeUnit;
import com.time.nlp.stringPreHandlingModule;
import com.time.util.DateUtil;

import org.junit.Test;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    Button btn;
    EditText txInput,txOutput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=(Button)findViewById(R.id.button);
        txInput=(EditText) findViewById(R.id.txtinput);
        txOutput=(EditText) findViewById(R.id.txtoutput);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input=txInput.getText().toString();
                System.out.println(input);
                if(input!=""){
                txOutput.setText(deal(input));
            }else {
                    Toast t = Toast.makeText(MainActivity.this, "没有输入内容", Toast.LENGTH_LONG);
                    t.show();
                }
            }
        });

    }
    public String deal(String str){
        File file=new File("libs/TimeExp.m");
        TimeNormalizer normalizer = new TimeNormalizer(file.toURI().toString());
        normalizer.parse(str);// 抽取时间
        TimeUnit[] unit = normalizer.getTimeUnit();
        String a=str;
        if(unit.length!=0) {
            a = stringPreHandlingModule.numberTranslator(a).replace(unit[0].Time_Expression, "");
            return DateUtil.formatDateDefault(unit[0].getTime())+a;
        }else{
            Toast t = Toast.makeText(MainActivity.this, "没有识别到时间关键词", Toast.LENGTH_LONG);
            t.show();
            return "";
        }
    }



}
