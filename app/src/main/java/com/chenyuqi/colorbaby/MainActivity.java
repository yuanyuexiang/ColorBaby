package com.chenyuqi.colorbaby;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CheckBox check_box_1 = (CheckBox)this.findViewById(R.id.checkBox_1);
        CheckBox check_box_2 = (CheckBox)this.findViewById(R.id.checkBox_2);
        CheckBox check_box_3 = (CheckBox)this.findViewById(R.id.checkBox_3);
        CheckBox check_box_4 = (CheckBox)this.findViewById(R.id.checkBox_4);
        CheckBox check_box_5 = (CheckBox)this.findViewById(R.id.checkBox_5);
        CheckBox check_box_6 = (CheckBox)this.findViewById(R.id.checkBox_6);
        CheckBox check_box_7 = (CheckBox)this.findViewById(R.id.checkBox_7);
        final TextView tv_add = (TextView)this.findViewById(R.id.tv_add);

        ColorDrawable OldColorDrawable = (ColorDrawable)tv_add.getBackground();
        int color_old = OldColorDrawable.getColor();
        tv_add.setBackgroundColor(color_old);
        tv_add.setText("颜色:"+Integer.toHexString(color_old));

        final Queue<CompoundButton> queue = new LinkedList<CompoundButton>();
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {

                if (checked) {
                    queue.add(compoundButton);
                    if (queue.size() > 2) {
                        CompoundButton cb = queue.poll();
                        cb.setChecked(false);
                    }
                } else {
                    queue.remove(compoundButton);
                }

                if(queue.size() == 0){
                    tv_add.setBackgroundColor(0xff000000);
                    tv_add.setText("颜色:" + Integer.toHexString(0xff000000));
                }
                if(queue.size() == 1){
                    CompoundButton cb = queue.element();
                    ColorDrawable OldColorDrawable = (ColorDrawable) cb.getBackground();
                    int color_old = OldColorDrawable.getColor();
                    tv_add.setBackgroundColor(color_old);
                    tv_add.setText("颜色:" + Integer.toHexString(color_old));
                }
                if (queue.size() == 2) {
                    List<CompoundButton> list = new ArrayList<CompoundButton>();
                    for(CompoundButton cb:queue){
                        list.add(cb);
                    }
                    ColorDrawable OldColorDrawable = (ColorDrawable) list.get(0).getBackground();
                    int color_old = OldColorDrawable.getColor();
                    ColorDrawable NewColorDrawable = (ColorDrawable) list.get(1).getBackground();
                    int color_new = NewColorDrawable.getColor();

                    int color = 0;
                    int or = Color.red(color_old);
                    int og = Color.green(color_old);
                    int ob = Color.blue(color_old);

                    int nr = Color.red(color_new);
                    int ng = Color.green(color_new);
                    int nb = Color.blue(color_new);

                    int r = (or + nr) % 2 == 0 ? (or + nr) / 2 : (or + nr + 1) / 2;
                    int g = (og + ng) % 2 == 0 ? (og + ng) / 2 : (og + ng + 1) / 2;
                    int b = (ob + nb) % 2 == 0 ? (ob + nb) / 2 : (ob + nb + 1) / 2;

                    color = Color.argb(255, r, g, b);
                    Log.i("TOMX", r + ":" + g + ":" + b);
                    tv_add.setBackgroundColor(color);
                    tv_add.setText("颜色:" + Integer.toHexString(color));
                }
                //废弃此方法
                if (queue.size() == 3) {

                    ColorDrawable OldColorDrawable = (ColorDrawable) tv_add.getBackground();
                    int color_old = OldColorDrawable.getColor();
                    ColorDrawable NewColorDrawable = (ColorDrawable) compoundButton.getBackground();
                    int color_new = NewColorDrawable.getColor();
                    int alpha = NewColorDrawable.getAlpha();
                    int color = 0;
                    if (color_old == 0xff000000) {
                        tv_add.setBackgroundColor(color_new);
                        tv_add.setText("颜色:" + Integer.toHexString(color_new));
                        return;
                    }

                    if (checked) {
                        //color = (color_old+color_new)/2;
                        //color = creatANewColor(color_old,color_new,127);
                        //color = blendColor(color_old,color_new);
                        int or = Color.red(color_old);
                        int og = Color.green(color_old);
                        int ob = Color.blue(color_old);

                        int nr = Color.red(color_new);
                        int ng = Color.green(color_new);
                        int nb = Color.blue(color_new);

                        int r = (or + nr) % 2 == 0 ? (or + nr) / 2 : (or + nr + 1) / 2;
                        int g = (og + ng) % 2 == 0 ? (og + ng) / 2 : (og + ng + 1) / 2;
                        int b = (ob + nb) % 2 == 0 ? (ob + nb) / 2 : (ob + nb + 1) / 2;

                        color = Color.argb(255, r, g, b);
                        Log.i("TOMX", r + ":" + g + ":" + b);
                    } else {
                        //color = color_old-color_new;
                        //color = creatANewColor(color_old,-color_new,alpha);
                        int or = Color.red(color_old);
                        int og = Color.green(color_old);
                        int ob = Color.blue(color_old);

                        int nr = Color.red(color_new);
                        int ng = Color.green(color_new);
                        int nb = Color.blue(color_new);

                        int r = or * 2 > 255 ? or * 2 - nr - 1 : or * 2 - nr;
                        int g = og * 2 > 255 ? og * 2 - ng - 1 : og * 2 - ng;
                        int b = ob * 2 > 255 ? ob * 2 - nb - 1 : ob * 2 - nb;
                        color = Color.argb(255, r, g, b);
                        Log.i("TOMX", r + ":-" + g + ":-" + b);

                    }
                    tv_add.setBackgroundColor(color);
                    tv_add.setText("颜色:" + Integer.toHexString(color));
                }
            }
        };
        check_box_1.setOnCheckedChangeListener(onCheckedChangeListener);
        check_box_2.setOnCheckedChangeListener(onCheckedChangeListener);
        check_box_3.setOnCheckedChangeListener(onCheckedChangeListener);
        check_box_4.setOnCheckedChangeListener(onCheckedChangeListener);
        check_box_5.setOnCheckedChangeListener(onCheckedChangeListener);
        check_box_6.setOnCheckedChangeListener(onCheckedChangeListener);
        check_box_7.setOnCheckedChangeListener(onCheckedChangeListener);
        //tv_add.setBackgroundColor(color);
    }
    /**lmjssjj:add*/
    public static int creatANewColor(int color, int overlayColor, int alpha) {

        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);

        int ovR = Color.red(overlayColor);
        int ovG = Color.green(overlayColor);
        int ovB = Color.blue(overlayColor);

        int newR = createColor(r, ovR, alpha);
        int newG = createColor(g, ovG, alpha);
        int newB = createColor(b, ovB, alpha);

        return Color.rgb(newR, newG, newB);
    }

    public static int createColor(int color, int overlay, int alpha) {
        int newcolor = (int) (color * (1 - alpha) + overlay * alpha);
        return newcolor;
    }

    public int blendColor(int fg, int bg) {
        int scr = Color.red(fg);
        int scg = Color.green(fg);
        int scb = Color.blue(fg);
        int sa = fg >>> 24;
        int dcr = Color.red(bg);
        int dcg = Color.green(bg);
        int dcb = Color.blue(bg);
        int color_r = dcr * (0xff - sa) / 0xff + scr * sa / 0xff;
        int color_g = dcg * (0xff - sa) / 0xff + scg * sa / 0xff;
        int color_b = dcb * (0xff - sa) / 0xff + scb * sa / 0xff;
        return ((color_r << 16) + (color_g << 8) + color_b) | (0xff000000);
    }
}
