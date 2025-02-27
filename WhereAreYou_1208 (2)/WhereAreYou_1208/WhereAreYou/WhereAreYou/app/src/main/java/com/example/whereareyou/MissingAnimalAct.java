package com.example.whereareyou;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MissingAnimalAct extends AppCompatActivity {
    String key = "Mmd3eUQllVHsDt943TR9yC6aDJ2%2BiG1NII6tH6y6Cyha%2BTuiwgalo7aFVS8i5JoNLnPTAkZV4TDWj6PqD4Ysjg%3D%3D";
    GridView gridView;
    MissingAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missing_animal);

        gridView = (GridView)findViewById(R.id.gridView);
        mAdapter = new MissingAdapter();

        /*bgnde = (TextView)findViewById(R.id.bgnde);
        endde = (TextView)findViewById(R.id.endde);

        // 오늘 날짜
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String start = sdf.format(new Date());
        endde.setText(start);

        // 3개월 전
        String end = sdf.format(addMonth(new Date(), -3));
        bgnde.setText(end);*/

        //text = (TextView)findViewById(R.id.result);


        // API Parsing
        StrictMode.enableDefaults();

        boolean inKindCd = false, inSexCd = false;
        boolean inPopFile = false, inProcStat = false, inColorCd = false;
        boolean inNoticeNo = false, inHappenDt = false, inHappenPlace = false;

        String kindCd = null, sexCd = null, procStat = null, colorCd = null;
        String popFile = null, noticeNo = null, happenDt = null, happenPlace = null;

        String urlStr = "http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic?"
                + "bgnde=" + "20191001" + "&endde=" + "20191220" + "&state=notice&pageNo=1&numOfRows=50&ServiceKey=" + key;

        try{
            URL url = new URL(urlStr); //검색 URL부분
            InputStream is = url.openStream();

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput( new InputStreamReader(is, "UTF-8") );

            int parserEvent = parser.getEventType();
            System.out.println("파싱시작합니다.");

            while (parserEvent != XmlPullParser.END_DOCUMENT){
                switch(parserEvent){
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                        if(parser.getName().equals("kindCd")){
                            inKindCd = true;
                        }
                        if(parser.getName().equals("sexCd")){
                            inSexCd = true;
                        }
                        if(parser.getName().equals("popfile")){
                            inPopFile = true;
                        }
                        if(parser.getName().equals("processState")){
                            inProcStat = true;
                        }
                        if(parser.getName().equals("colorCd")){
                            inColorCd = true;
                        }
                        if(parser.getName().equals("noticeNo")){
                            inNoticeNo = true;
                        }
                        if(parser.getName().equals("happenDt")){
                            inHappenDt = true;
                        }
                        if(parser.getName().equals("happenPlace")){
                            inHappenPlace = true;
                        }
                        break;
                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
                        if(inKindCd){
                            kindCd = parser.getText();
                            inKindCd = false;
                        }
                        if(inSexCd){
                            sexCd = parser.getText();
                            inSexCd = false;
                        }
                        if(inPopFile){
                            popFile = parser.getText();
                            inPopFile = false;
                        }
                        if(inProcStat){
                            procStat = parser.getText();
                            inProcStat = false;
                        }
                        if(inColorCd){
                            colorCd = parser.getText();
                            inColorCd = false;
                        }
                        if(inNoticeNo){
                            noticeNo = parser.getText();
                            inNoticeNo = false;
                        }
                        if(inHappenDt){
                            happenDt = parser.getText();
                            inHappenDt = false;
                        }
                        if(inHappenPlace){
                            happenPlace = parser.getText();
                            inHappenPlace = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("item")){
                            String str = "공고번호 " + noticeNo + "\n접수일 " + happenDt + "\n품종 " + kindCd
                                    + "\n발견장소 " + happenPlace + "\n성별 " + sexCd + "\n색상 " + colorCd
                                    + "\n상태 " + procStat;
                            mAdapter.addItem(new MissingItem(popFile, str));
                        }
                        break;
                }
                parserEvent = parser.next();
            }
        } catch (Exception e) {
            //status1.setText(e.toString());
        }
        gridView.setAdapter(mAdapter);
    }

    public static Date addMonth(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);

        return cal.getTime();
    }

    /*public void onClickSearch(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                Button btn = (Button)findViewById(R.id.bgnde);
                String start = btn.getText().toString();
                btn = (Button)findViewById(R.id.endde);
                String end = btn.getText().toString();

                Spinner spinner = (Spinner)findViewById(R.id.kind);
                String kind = spinner.getSelectedItem().toString();
                spinner = (Spinner)findViewById(R.id.upr);
                String upr = spinner.getSelectedItem().toString();

                System.out.println(start + " " + end + " " + kind + " " + upr);
                data = parsing(start, end);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        text.setText(data);
                    }
                });
            }
        }).start();
    }*/

/*    public ArrayList<MissingItem> parsing(String start, String end, String upr, String kind) {
        ArrayList<MissingItem> arrayList = new ArrayList<>();
        // API Parsing
        StrictMode.enableDefaults();

        boolean inKindCd = false, inSexCd = false;
        boolean inPopFile = false, inProcStat = false, inColorCd = false;
        boolean inNoticeNo = false, inHappenDt = false, inHappenPlace = false;

        String kindCd = null, sexCd = null, procStat = null, colorCd = null;
        String popFile = null, noticeNo = null, happenDt = null, happenPlace = null;

        String urlStr = "http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic?"
                + "bgnde=" + start + "&endde=" + end;

        if (kind != null) {
            if (kind.equals("개")) {
                urlStr = urlStr + "&kind=417000";
            } else if (kind.equals("고양이")) {
                urlStr = urlStr + "&kind=422400";
            } else if (kind.equals("기타")) {
                urlStr = urlStr + "&kind=429900";
            }
        }

        if (upr != null) {
            if (upr.equals("서울")) {
                urlStr = urlStr + "&upr_cd=6110000";
            } else if (upr.equals("부산")) {
                urlStr = urlStr + "&upr_cd=6260000";
            } else if (upr.equals("대구")) {
                urlStr = urlStr + "&upr_cd=6270000";
            }
        }

        urlStr = urlStr + "&state=notice&pageNo=1&numOfRows=50&ServiceKey=" + key;

        try{
            URL url = new URL(urlStr); //검색 URL부분

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();
            System.out.println("파싱시작합니다.");

            while (parserEvent != XmlPullParser.END_DOCUMENT){
                switch(parserEvent){
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                        if(parser.getName().equals("kindCd")){
                            inKindCd = true;
                        }
                        if(parser.getName().equals("sexCd")){
                            inSexCd = true;
                        }
                        if(parser.getName().equals("popfile")){
                            inPopFile = true;
                        }
                        if(parser.getName().equals("processState")){
                            inProcStat = true;
                        }
                        if(parser.getName().equals("colorCd")){
                            inColorCd = true;
                        }
                        if(parser.getName().equals("noticeNo")){
                            inNoticeNo = true;
                        }
                        if(parser.getName().equals("happenDt")){
                            inHappenDt = true;
                        }
                        if(parser.getName().equals("happenPlace")){
                            inHappenPlace = true;
                        }
                        break;
                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
                        if(inKindCd){
                            kindCd = parser.getText();
                            inKindCd = false;
                        }
                        if(inSexCd){
                            sexCd = parser.getText();
                            inSexCd = false;
                        }
                        if(inPopFile){
                            popFile = parser.getText();
                            inPopFile = false;
                        }
                        if(inProcStat){
                            procStat = parser.getText();
                            inProcStat = false;
                        }
                        if(inColorCd){
                            colorCd = parser.getText();
                            inColorCd = false;
                        }
                        if(inNoticeNo){
                            noticeNo = parser.getText();
                            inNoticeNo = false;
                        }
                        if(inHappenDt){
                            happenDt = parser.getText();
                            inHappenDt = false;
                        }
                        if(inHappenPlace){
                            happenPlace = parser.getText();
                            inHappenPlace = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("item")){
                            String str = "공고번호 " + noticeNo + "\n접수일 " + happenDt + "\n품종 " + kindCd
                                    + "\n발견장소 " + happenPlace + "\n성별 " + sexCd + "\n색상 " + colorCd
                                    + "\n상태 " + procStat;
                            arrayList.add(new MissingItem(popFile, str));
                            //status1.setText(status1.getText() + str);
                        }
                        break;
                }
                parserEvent = parser.next();
            }

            //recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(), items, R.layout.activity_missing_animal));
            //recyclerView.setLayoutManager(layoutManager);
        } catch (Exception e) {
            //status1.setText(e.toString());
        }

        return arrayList;
    }

    /*public void setDate(View view) {

    }*/

    class MissingAdapter extends BaseAdapter {
        ArrayList<MissingItem> items = new ArrayList<MissingItem>();
        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(MissingItem mItem){
            items.add(mItem);
        }

        @Override
        public MissingItem getItem(int i) {
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            MissingViewer missingViewer = new MissingViewer(getApplicationContext());
            missingViewer.setItem(items.get(i));
            return missingViewer;
        }
    }
}
