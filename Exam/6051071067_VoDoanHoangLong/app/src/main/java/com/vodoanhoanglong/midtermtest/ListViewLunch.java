package com.vodoanhoanglong.midtermtest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.vodoanhoanglong.midtermtest.Model.Lunch;
import com.vodoanhoanglong.midtermtest.Model.LunchAdapter;

import java.util.ArrayList;

public class ListViewLunch extends AppCompatActivity {
    ListView lvLunch;
    LunchAdapter lunchAdapter;
    ArrayList<Lunch> lunches;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_lunch);

        linkViews();
        loadData();
        addEvents();
    }

    private void addEvents() {
        lvLunch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Lunch lunch = (Lunch)lunchAdapter.getItem(i);
                Dialog dialog = new CustomDialog(ListViewLunch.this, lunch.getPhoto(), lunch.getPlaceName(), lunch.getRatingValue(), lunch.getAddress());
                dialog.show();

            }
        });
    }

    private void loadData() {
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        lunches = new ArrayList<Lunch>();

        if(type.equals("price"))
        {
            lunches.add(new Lunch(R.drawable.lunch_com_tam_dai_dong, "Cơm Tấm Đại Đồng","Cơm Sườn",
                    4.1, "100+", "38 Đường Số 17, P. Linh Trung, Q. Thủ Đức, Tp.HCM"));
            lunches.add(new Lunch(R.drawable.lunch_bula_bento, "Bula Bento - Cơm Trưa","Cơm Chiên Cá Hồi",
                    4.0, "499+", "22 Đoàn Kết, P. Bình Thọ, Q. Thủ Đức, Tp.HCM"));
            lunches.add(new Lunch(R.drawable.lunch_com_bd_quang_ngan, "Cơm Bình Dân Quang Ngân","Cơm Gà Kho Xả Ớt",
                    4.0, "100+", "130 Linh Trung, P. Linh Trung, Q. Thủ Đức, Tp.HCM"));
            lunches.add(new Lunch(R.drawable.lunch_com_ga_to_vinh_dien, "Cơm Gà - Tô Vĩnh Diện","Cơm Đùi Gà Chiên Nước Mắm",
                    4.0, "999+", "15 Tô Vĩnh Diện, P. Linh Chiểu, Q. Thủ Đức, Tp.HCM"));
            lunches.add(new Lunch(R.drawable.lunch_com_nieu_phuong_bac, "Cơm Niêu Phương Bắc","Cơm Cá Cơm Rim Mặn",
                    4.1, "100+", "87 Hoàng Diệu 2, P. Linh Trung, Q. Thủ Đức, Tp.HCM"));
        }else
        {
            lunches.add(new Lunch(R.drawable.noodle_bun_dau_mam_tom, "Bún Đậu Mẹt Tre - Hoàng Diệu 2","Bún Đậu Tá Lả",
                    4.0, "499+", "177 Hoàng Diệu 2, P. Linh Trung, Q. Thủ Đức, Tp.HCM"));
            lunches.add(new Lunch(R.drawable.noodle_bun_dau_a_chanh, "Bún Đậu A Chảnh - Tô Vĩnh Diện","Bún Đậu Thập Cẩm",
                    4.5, "500+", "26 Tô Vĩnh Diện, P. Linh Chiểu, Q. Thủ Đức, Tp.HCM"));
            lunches.add(new Lunch(R.drawable.noodle_bun_dau_tieu_muoi, "Bún Đậu Mắm Tôm Tiểu Muội","Bún Đậu Thập Cẩm",
                    4.0, "400+", "39 Hoàng Diệu 2, P. Linh Trung, Q. Thủ Đức, Tp.HCM"));
            lunches.add(new Lunch(R.drawable.noodle_bun_di_7, "Bún Thịt Nướng Dì 7","Bún Thịt Nướng Đặc Biệt",
                    4.0, "300+", "15 Tô Vĩnh Diện, P. Linh Chiểu, Q. Thủ Đức, Tp.HCM"));
            lunches.add(new Lunch(R.drawable.noodle_bun_quay_phu_quoc, "Bún Quậy Phú Quốc - Hoàng Diệu 2","Bún Quậy Chả Tôm",
                    4.6, "200+", "204A Hoàng Diệu 2, P. Linh Chiểu, Q. Thủ Đức, Tp.HCM"));
        }



        lunchAdapter = new LunchAdapter(ListViewLunch.this, R.layout.active_item, lunches);
        lvLunch.setAdapter(lunchAdapter);
    }

    private void linkViews() {
        lvLunch = findViewById(R.id.lvLunch);
    }
}