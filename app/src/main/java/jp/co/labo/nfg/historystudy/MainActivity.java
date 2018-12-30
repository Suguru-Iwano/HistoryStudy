package jp.co.labo.nfg.historystudy;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //todo 繰り返し回数
    private int rep_num = 4;
    private int rep_num_kotei = 4;
    //todo 繰り返し回数

    //正解数を数えます
    //todo いるかね？？
    private int seikai_count = 0;
    private int count = 1;

    private TextView textViewQ;
    private TextView textViewCount;
    private Mondai mondai;
    private Button button1;
    private Button button2;
    private Button button3;

    private Button seitou;
    private Button damy1;
    private Button damy2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //インスタンスの生成
        mondai = new Mondai();
        setTextMain();
    }

    private void setTextMain() {
        setContentView(R.layout.activity_main);

        textViewQ = findViewById((R.id.textQ));
        textViewCount = findViewById((R.id.textViewCount));
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        //問題を表示
        //ここで問題が決定
        textViewQ.setText(mondai.getMondai());

        textViewCount.setText(this.count + "/" + rep_num_kotei);

        //選択肢の位置をランダムにするメソッド
        buttonRandom();

        seitou.setText(mondai.getSeitou());
        damy1.setText(mondai.getDamy1());
        damy2.setText(mondai.getDamy2());


        // Buttonを押した時の処理
        // 正解
        seitou.setOnClickListener(new View.OnClickListener() {
            // クリック時に呼ばれるメソッド
            @Override
            public void onClick(View view) {
                //クリック時にボタンの色を変える
                Button btn_click = seitou;
                Drawable btn_color = ResourcesCompat.getDrawable(getResources(), R.drawable.color_change_click, null);
                btn_click.setBackground(btn_color);

                clickEvent(true);
            }
        });

        //不正解
        damy1.setOnClickListener(new View.OnClickListener() {
            // クリック時に呼ばれるメソッド
            @Override
            public void onClick(View view) {

                Button btn_click = damy1;
                Drawable btn_color = ResourcesCompat.getDrawable(getResources(), R.drawable.color_change_click, null);
                btn_click.setBackground(btn_color);

                clickEvent(false);
            }
        });
        damy2.setOnClickListener(new View.OnClickListener() {
            // クリック時に呼ばれるメソッド
            @Override
            public void onClick(View view) {

                Button btn_click = damy2;
                Drawable btn_color = ResourcesCompat.getDrawable(getResources(), R.drawable.color_change_click, null);
                btn_click.setBackground(btn_color);

                clickEvent(false);
            }
        });
    }


    //クリック処理
    //引数：正解ならtrue、不正解ならfalse
    private void clickEvent(boolean boo) {

        //トースト表示
        if(boo) {
            this.seikai_count++;
            toastMakeMaru("", getApplicationContext());
        } else {
            toastMakeBatu("", getApplicationContext());

            //ボタンの色を変える
            Button btn = seitou;
            Drawable btn_color = ResourcesCompat.getDrawable(getResources(), R.drawable.color_change, null);
            btn.setBackground(btn_color);


        }
        this.count++;
        this.rep_num--;

        if(this.rep_num > 0) {
            // 2秒後に処理を実行する
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //再帰呼び出し
                    setTextMain();
                }
            }, 1900);

        } else {
            //todo ここ！インテンド！！！
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(getApplication(), SecondActivity.class);
                    intent.putExtra("seikai_count", seikai_count);
                    intent.putExtra("rep_num_kotei", rep_num_kotei);
                    startActivity(intent);
                }
            }, 1900);
        }
    }


    //トースト表示
    //private void toastMake(String message, int x, int y){
        //Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        // 位置調整
        //toast.setGravity(Gravity.CENTER, x, y);

        //toast.show();

    //}

    //todo トーストをまとめる
    //丸トースト
    public void toastMakeMaru( String str , Context cont) {
        Toast toast = new Toast( cont );

        LayoutInflater inflater = getLayoutInflater();

        // relative_layout:custom_toast.xmlのRelativeLayoutに付けたID
        ViewGroup viewGroup = findViewById(R.id.relative_layout);

        // inflateする
        View view = inflater.inflate( R.layout.custom_toast, viewGroup);

        TextView textView = view.findViewById( R.id.message );
        textView.setText( str );

        toast.setView( view );
        // 表示時間
        toast.setDuration( Toast.LENGTH_SHORT );
        // 位置調整
        toast.setGravity(Gravity.CENTER, 50, -290);

        toast.show();
    }

    //ばつトースト
    public void toastMakeBatu( String str , Context cont) {
        Toast toast = new Toast( cont );

        LayoutInflater inflater = getLayoutInflater();

        // relative_layout:custom_toast.xmlのRelativeLayoutに付けたID
        ViewGroup viewGroup = findViewById(R.id.relative_layout);

        // inflateする
        View view = inflater.inflate( R.layout.custom_toast_batu, viewGroup);

        TextView textView = view.findViewById( R.id.message );
        textView.setText( str );

        toast.setView( view );
        // 表示時間
        toast.setDuration( Toast.LENGTH_SHORT );
        // 位置調整
        toast.setGravity(Gravity.CENTER, 50, -290);

        toast.show();
    }



    //ボタンの配置をランダムにする
    private void buttonRandom(){
        int randnum = (int) (Math.random() * 3);
        if (randnum == 0) {
            this.seitou = this.button1;
            this.damy1 = this.button2;
            this.damy2 = this.button3;
        } else if (randnum == 1) {
            this.seitou = this.button3;
            this.damy1 = this.button1;
            this.damy2 = this.button2;
        } else {
            this.seitou = this.button2;
            this.damy1 = this.button3;
            this.damy2 = this.button1;
        }
    }


    @Override
    public void onClick(View v) {

    }
}