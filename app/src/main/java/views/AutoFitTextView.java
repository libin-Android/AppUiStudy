package views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

/**
 *
 * Created by Administrator on 2017/5/8.
 * 自定义文字大小自动缩放的TextView
 */
public class AutoFitTextView extends TextView {
    public AutoFitTextView(Context context) {
        this(context,null);
    }
    public AutoFitTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    public AutoFitTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
//        //测量宽度
//        int width;
//        if (widthMode==MeasureSpec.EXACTLY){//一般是设置了明确的值或者是MATCH_PARENT
//            width=widthSize;
//        }else if (widthMode==MeasureSpec.UNSPECIFIED){//表示子布局想要多大就多大，很少使用
//            width=1000;
//        }else {//AT_MOST：表示子布局限制在一个最大值内，一般为WARP_CONTENT
//            int desiredWidth = (int) (getPaddingLeft() + s.length() + getPaddingRight());
//            width=desiredWidth;
//        }
//        //测量高度
//        int height;
//        if (heightMode==MeasureSpec.EXACTLY){//一般是设置了明确的值或者是MATCH_PARENT
//            height=heightSize;
//        }else{//表示子布局限制在一个最大值内，一般为WARP_CONTENT
//            height= 2000;
//        }
//        setMeasuredDimension(width,height);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    public void setContent(String str){
        setText(str);
        setTextSize(5);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void upDateView(float mode,String str){
        setText(str);
        setTextSize(mode);
        invalidate();
    }
}
