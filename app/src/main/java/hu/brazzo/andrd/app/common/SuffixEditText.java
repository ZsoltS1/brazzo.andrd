package hu.brazzo.andrd.app.common;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.text.TextPaint;
import android.util.AttributeSet;

import hu.brazzo.andrd.R;


public class SuffixEditText extends android.support.v7.widget.AppCompatEditText {
    private TextPaint mTextPaint;
    private String mSuffix;
    private float mSuffixWidth;
    private Drawable mSuffixDrawable;

    private void initialize(Context context) {
        Resources resources = getResources();

        mTextPaint = new TextPaint();
        mTextPaint.setTextSize(getTextSize());
        // Using the same foreground color could
        // be confusing.
        mTextPaint.setColor(getCurrentHintTextColor());
        mTextPaint.setTextAlign(Paint.Align.RIGHT);

        mSuffixDrawable = new Drawable() {
            @Override
            public void draw(@NonNull Canvas canvas) {
                if (mSuffix == null)
                    return;
                canvas.drawText(mSuffix, 0, getPaddingTop(), mTextPaint);
            }

            @Override
            public void setAlpha(int alpha) {
            }

            @Override
            public void setColorFilter(ColorFilter colorFilter) {
            }

            @Override
            public int getOpacity() {
                return PixelFormat.OPAQUE;
            }
        };

        Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), getContext().getResources().getString(R.string.font_helvetica_regular));
        setTypeface(myTypeface);
    }

    public SuffixEditText(Context context) {
        super(context);
        initialize(context);
    }

    public SuffixEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public SuffixEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    public void setSuffix(String suffix) {
        mSuffix = suffix;
        setCompoundDrawables(null, null, mSuffixDrawable, null);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mSuffix != null)
            mSuffixWidth = mTextPaint.measureText(mSuffix + " ") + 20;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public int getCompoundPaddingRight() {
        return super.getCompoundPaddingRight() + (int) Math.ceil(mSuffixWidth);
    }
}
