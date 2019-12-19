package com.example.a04_listviewapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class TipsAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);


        ListView listView = (ListView) findViewById(R.id.listview);

        ArrayList<String> aryList = new ArrayList<String>();
        aryList.add(" 반려동물에게 있어, 당신이 얼마나 소중한 존재인지 잊지 마세요.");
        aryList.add(" 하룻밤 집을 비워야 한다면, 친구나 친척에게 맡기세요. ");
        aryList.add(" 특별한 이유가 아니라면 곤히 자는 동물을 깨우지 마세요.");
        aryList.add(" 동물을 사랑스러운 눈빛으로 바라보세요. 미심쩍은 시선은 금물.");
        aryList.add(" 명령은 간결하게 한 가지 문장/단어를 택해 쓰세요.");
        aryList.add(" 동물에게 사람 먹는 패스트푸드나 가공식품을 먹이지 마세요.");
        aryList.add(" 최소 하루에 한 번은 동물과 시간을 보내세요. ");
        aryList.add(" 잘못된 행동은 발견 즉시 교정해야 합니다. ");
        aryList.add(" 동물이 좋아하는 장소에 전용 화장실을 마련하세요.");
        aryList.add(" 동물은 먹을 것에 약하다는 걸 알아두세요.");
        aryList.add(" 적절한 사료 종류와 양을 배식하세요.");
        aryList.add(" 반려동물을 들이기 전, 일단 몇 주 정도 시간을 두고 현실적으로 고민해보세요.");
        aryList.add(" 위로의 말을 건네기보단, 평소와 다름없이 침착하고 자연스럽게 행동하세요.");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.items,
                R.id.name,
                aryList
        );

        listView.setAdapter(adapter);


    }
}
