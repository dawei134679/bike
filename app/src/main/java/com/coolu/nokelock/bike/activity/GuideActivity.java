package com.coolu.nokelock.bike.activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.coolu.nokelock.bike.R;
import com.coolu.nokelock.bike.base.BaseActivity;
import com.coolu.nokelock.bike.fragment.PlayItemFragment;
import com.coolu.nokelock.bike.util.SPUtils;
import com.fitsleep.sunshinelibrary.utils.IntentUtils;
import com.fitsleep.sunshinelibrary.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends BaseActivity{
    private int image[]={R.mipmap.guide_tip,R.mipmap.guide_one,R.mipmap.guide_two,R.mipmap.guide_three};
    private ViewPager viewpager;
    //图片放置
    private List<ImageView> iamgeList;
    private Button guide_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 放在setContentView()之前运行  
        ScreenUtils.hideStatusBar(this);
        setContentView(R.layout.activity_guide);



        initView();
    }

    private void initView() {
        guide_button = (Button)findViewById(R.id.id_guide_button);
        guide_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startActivityAndFinish(GuideActivity.this, MainActivity.class);
                SPUtils.set(GuideActivity.this,"isEnter",true);
            }
        });
        viewpager = (ViewPager)findViewById(R.id.viewpager);
        iamgeList = new ArrayList<ImageView>();

        for (int i=0;i<image.length;i++){
            ImageView imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            iamgeList.add(imageView);
        }

        //绑定适配器
        viewpager.setAdapter(new GuideAdapter());
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //让滑倒最后一页显示按钮
                if(position==image.length-1){
                    guide_button.setVisibility(View.VISIBLE);
                }else {
                    guide_button.setVisibility(View.GONE);

                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    class GuideAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return image.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = iamgeList.get(position);
            imageView.setImageResource(image[position]);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // TODO Auto-generated method stub
            container.removeView((View) object);
        }
    }
}
