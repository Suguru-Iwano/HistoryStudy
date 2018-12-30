package jp.co.labo.nfg.historystudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class SecondActivity extends AppCompatActivity {
    int seikai_count;
    int rep_num_kotei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView resultView = this.findViewById(R.id.resultView);
        TextView kaisetuView = this.findViewById(R.id.kaisetuView);

        Intent intent = getIntent();
        seikai_count = intent.getIntExtra("seikai_count", 0);
        rep_num_kotei = intent.getIntExtra("rep_num_kotei", 0);

        if(seikai_count == 0){
            resultView.setText
                    ("お疲れ様でした。\n" + "１つも正解しませんでした、、、");
        } else if (seikai_count == rep_num_kotei){
            resultView.setText
                    ("お疲れ様でした。\n" + "全問正解です、やりますねぇ！");
        } else {
            resultView.setText
                    ("お疲れ様でした。\n" + rep_num_kotei + "問中" + seikai_count + "問正解しました。");
        }

        kaisetuView.setText
                ("大阪城は、大阪にあります。\n坂本龍馬土佐藩出身です。\n卑弥呼は邪馬台国を治めたと言われています。\n西郷隆盛像は西南戦争を指揮しました。リンカーンが参加したのは南北戦争です。");
    }
}