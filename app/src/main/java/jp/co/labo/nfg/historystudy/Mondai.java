package jp.co.labo.nfg.historystudy;

import java.util.*;


public class Mondai {

    //TODO【入力欄】
    private String[] mondai_list = {"大阪城はどこにある？","坂本龍馬は何藩？","卑弥呼はどこの女王？","西南戦争の指導者はだれ？"};
    private String[] seitou_list = {"大阪","土佐藩","邪馬台国","西郷隆盛"};
    private String[] damy1_list = {"神戸","２班","ヨーロッパ","織田信長"};
    private String[] damy2_list = {"ヨーロッパ","長州藩","沖縄","リンカーン大統領"};
    private String[] kaisetu_list =
            {"大阪城は、大阪にあります。","坂本龍馬土佐藩出身です。","卑弥呼は邪馬台国を治めたと言われています。","西郷隆盛像は西南戦争を指揮しました。リンカーンが参加したのは南北戦争です。"};
    private int mondaisu = 4;
    //TODO【入力欄】


    List<Integer> answered_array = new ArrayList<Integer>();
    private int randnum;

    List<String> mondai_array = new ArrayList<String>(Arrays.asList(mondai_list));
    List<String> seitou_array = new ArrayList<String>(Arrays.asList(seitou_list));
    List<String> damy1_array = new ArrayList<String>(Arrays.asList(damy1_list));
    List<String> damy2_array = new ArrayList<String>(Arrays.asList(damy2_list));
    List<String> kaisetu_array = new ArrayList<String>(Arrays.asList(kaisetu_list));


    //ゲッターでmondaiとかにセットする
    public String getMondai() {
        //randnumに値をセット
        //mondaisuを-1
        this.randnum = (int)(Math.random() * (--mondaisu));

        //answeredArrayにといた問題番号を格納
        //TODO 正解不正解両方配列作る必要あるかも
        answered_array.add(randnum);

        //消す前に変数に代入しとく
        String mondai = mondai_array.get(randnum);

        //問題被りを除去
        mondai_array.remove(randnum);

        return mondai;
    }

    public String getSeitou() {
        String seitou = seitou_array.get(randnum);
        seitou_array.remove(randnum);
        return seitou;
    }

    public String getDamy1() {
        String damy1 = damy1_array.get(randnum);
        damy1_array.remove(randnum);
        return damy1;
    }

    public String getDamy2() {
        String damy2 = damy2_array.get(randnum);
        damy2_array.remove(randnum);
        return damy2;
    }

    //todo 解説リストどうしようかね、、
    //todo インスタンス新しく作ったほうがいいかも、、

}