package com.hjs.ex_1204;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView value;
    Button id_up, id_down, id_reset;

    int n = 0;
    SharedPreferences pref = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // n 값을 저장하기 위한 SharedPreference 객체 준비
        pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

        value = findViewById(R.id.value);

        id_up = findViewById(R.id.id_up);
        id_down = findViewById(R.id.id_down);
        id_reset = findViewById(R.id.id_reset);

        // 앱이 최초로 실행될 때 저장되어 있던 값을 로드
        n = pref.getInt("save", 0);

        //.setText()에는 정수값만 넣으면 오류가 난다.
        // 문자열 형태로 변경하여 집어넣어줘야 한다.
        // String.valueOf(n) --> "" + n 과 동일
        value.setText(String.valueOf(n));

        id_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                value.setText(String.valueOf(++n));
            }
        });

        id_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                value.setText(String.valueOf(--n));
            }
        });

        id_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                n = 0;
                value.setText(String.valueOf(n));
            }
        });
    }

    //앱이 일시정지 되었을 경우 n값을 저장

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences.Editor edit = pref.edit();
        edit.putInt("save", n);
        edit.commit(); // 커밋을 하지 않으면 값이 물리적으로 저장되지 않는다.

    }
}
