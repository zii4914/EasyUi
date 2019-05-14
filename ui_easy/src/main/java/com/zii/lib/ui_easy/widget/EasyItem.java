package com.zii.lib.ui_easy.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.zii.lib.ui_easy.R;

/**
 * Created by zii on 2019/5/14.
 */
public class EasyItem extends ConstraintLayout {

  private ImageView mIvLeft;
  private TextView mTvLeft;
  private EditText mEdtRight;
  private ImageView mIvRight;
  private View mLineTop;
  private View mLineBottom;
  //------参数-----
  //left iv
  private int mLeftIvResId;
  private int mLeftIvSize;
  private int mLeftIvMarginLeft;
  //left tv
  private String mLeftTvStr;
  private int mLeftTvTextColor;
  private int mLeftTvTextSize;
  private int mLeftTvTextStyle;
  private int mLeftTvMarginIv;
  private int mLeftTvMarginParent;
  //right edt
  private String mRightEdtHint;
  private int mRightEdtHintColor;
  private String mRightEdtStr;
  private int mRightEdtTextSize;
  private int mRightEdtTextStyle;
  private int mRightEdtTextColor;
  private int mRightEdtWidth;
  private int mRightEdtMarginRightIv;
  private int mRightEdtMarginRightParent;
  private boolean mRightEdtEnable;
  private boolean mRightEdtSingleLine; //单行,ellipsize为end
  private int mRightEdtMaxLines;
  //right iv
  private int mRightIvSize;
  private int mRightIvResId;
  private int mRightIvMarginRight;
  //line top
  private boolean mLineTopVisible;
  private int mLineTopHeight;
  private int mLineTopLeftMargin;
  private int mLineTopRightMargin;
  private int mLineTopColor;
  private int mLineTopDashGap;
  private int mLineTopDashWidth;
  //line bottom
  private boolean mLineBottomVisible;
  private int mLineBottomHeight;
  private int mLineBottomLeftMargin;
  private int mLineBottomRightMargin;
  private int mLineBottomColor;
  private int mLineBottomDashGap;
  private int mLineBottomDashWidth;
  //other
  private int mPressedColor;

  public EasyItem(Context context) {
    super(context);
    init(null);
  }

  public EasyItem(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(attrs);
  }

  public EasyItem(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(attrs);
  }

  private void init(AttributeSet attrs) {
    View inflate = LayoutInflater.from(getContext()).inflate(R.layout.easy_item, this);
    findView(inflate);
    initAttrs(attrs);
    initViews();
  }

  private void findView(View view) {
    mIvLeft = view.findViewById(R.id.iv_left);
    mTvLeft = view.findViewById(R.id.tv_left);
    mEdtRight = view.findViewById(R.id.edt_right);
    mIvRight = view.findViewById(R.id.iv_right);
    mLineTop = view.findViewById(R.id.line_top);
    mLineBottom = view.findViewById(R.id.line_bottom);
  }

  private void initAttrs(AttributeSet attrs) {
    initDefaultAttr();

    int[] styleable = R.styleable.EasyItem;
    if (attrs != null && styleable != null && styleable.length > 0) {
      TypedArray typedArray = getContext().obtainStyledAttributes(attrs, styleable);
      for (int i = 0; i < typedArray.getIndexCount(); i++) {
        int index = typedArray.getIndex(i);
        setStyleAttr(typedArray, index);
      }
      typedArray.recycle();
    }
  }

  private void initDefaultAttr() {
    mPressedColor = Color.parseColor("#DFDFDF");

    mLeftIvMarginLeft = dp2px(15);
    mRightIvMarginRight = dp2px(15);

    mLeftTvTextColor = Color.parseColor("#333333");
    mLeftTvTextSize = dp2px(15);
    mLeftTvMarginIv = dp2px(4);
    mLeftTvMarginParent = mLeftIvMarginLeft;

    mRightEdtHintColor = Color.parseColor("#cccccc");
    mRightEdtTextColor = Color.parseColor("#333333");
    mRightEdtTextSize = dp2px(15);
    mRightEdtMarginRightIv = dp2px(4);
    mRightEdtMarginRightParent = mRightIvMarginRight;

    mLineTopHeight = dp2px(0.7f);
    mLineTopColor = Color.parseColor("#eeeeee");
    mLineBottomHeight = dp2px(0.7f);
    mLineBottomColor = Color.parseColor("#eeeeee");
  }

  private void setStyleAttr(TypedArray typedArray, int index) {
    //lift iv
    if (index == R.styleable.EasyItem_easy_item_left_iv_margin_left) {
      mLeftIvMarginLeft = typedArray.getDimensionPixelSize(index, 0);
    } else if (index == R.styleable.EasyItem_easy_item_left_iv_src) {
      mLeftIvResId = typedArray.getResourceId(index, 0);
    } else if (index == R.styleable.EasyItem_easy_item_left_iv_size) {
      mLeftIvSize = typedArray.getDimensionPixelSize(index, 0);
    }
    //left tv
    else if (index == R.styleable.EasyItem_easy_item_left_tv_str) {
      mLeftTvStr = typedArray.getString(index);
    } else if (index == R.styleable.EasyItem_easy_item_left_tv_text_color) {
      mLeftTvTextColor = typedArray.getColor(index, Color.BLACK);
    } else if (index == R.styleable.EasyItem_easy_item_left_tv_text_size) {
      mLeftTvTextSize = typedArray.getDimensionPixelSize(index, 0);
    } else if (index == R.styleable.EasyItem_easy_item_left_tv_text_style) {
      mLeftTvTextStyle = typedArray.getInt(index, 0);
    } else if (index == R.styleable.EasyItem_easy_item_left_tv_margin_iv) {
      mLeftTvMarginIv = typedArray.getDimensionPixelSize(index, 0);
    } else if (index == R.styleable.EasyItem_easy_item_left_tv_margin_parent) {
      mLeftTvMarginParent = typedArray.getDimensionPixelSize(index, 0);
    }
    //right edt
    else if (index == R.styleable.EasyItem_easy_item_right_edt_hint) {
      mRightEdtHint = typedArray.getString(index);
    } else if (index == R.styleable.EasyItem_easy_item_right_edt_hint_color) {
      mRightEdtHintColor = typedArray.getColor(index, Color.BLACK);
    } else if (index == R.styleable.EasyItem_easy_item_right_edt_str) {
      mRightEdtStr = typedArray.getString(index);
    } else if (index == R.styleable.EasyItem_easy_item_right_edt_text_size) {
      mRightEdtTextSize = typedArray.getDimensionPixelSize(index, 0);
    } else if (index == R.styleable.EasyItem_easy_item_right_edt_text_style) {
      mRightEdtTextStyle = typedArray.getInt(index, 0);
    } else if (index == R.styleable.EasyItem_easy_item_right_edt_text_color) {
      mRightEdtTextColor = typedArray.getColor(index, Color.BLACK);
    } else if (index == R.styleable.EasyItem_easy_item_right_edt_margin_right_iv) {
      mRightEdtMarginRightIv = typedArray.getDimensionPixelSize(index, 0);
    } else if (index == R.styleable.EasyItem_easy_item_right_edt_margin_right_parent) {
      mRightEdtMarginRightParent = typedArray.getDimensionPixelSize(index, 0);
    } else if (index == R.styleable.EasyItem_easy_item_right_edt_enable) {
      mRightEdtEnable = typedArray.getBoolean(index, false);
    } else if (index == R.styleable.EasyItem_easy_item_right_edt_width) {
      mRightEdtWidth = typedArray.getDimensionPixelSize(index, 0);
    } else if (index == R.styleable.EasyItem_easy_item_right_edt_single_line) {
      mRightEdtSingleLine = typedArray.getBoolean(index, false);
    } else if (index == R.styleable.EasyItem_easy_item_right_edt_max_lines) {
      mRightEdtMaxLines = typedArray.getInt(index, 1);
    }
    //right iv
    else if (index == R.styleable.EasyItem_easy_item_right_iv_size) {
      mRightIvSize = typedArray.getDimensionPixelSize(index, 0);
    } else if (index == R.styleable.EasyItem_easy_item_right_iv_src) {
      mRightIvResId = typedArray.getResourceId(index, 0);
    } else if (index == R.styleable.EasyItem_easy_item_right_iv_margin_right) {
      mRightIvMarginRight = typedArray.getDimensionPixelSize(index, 0);
    }
    //line top
    else if (index == R.styleable.EasyItem_easy_item_line_top_visible) {
      mLineTopVisible = typedArray.getBoolean(index, false);
    } else if (index == R.styleable.EasyItem_easy_item_line_top_height) {
      mLineTopHeight = typedArray.getDimensionPixelSize(index, 0);
    } else if (index == R.styleable.EasyItem_easy_item_line_top_margin_left) {
      mLineTopLeftMargin = typedArray.getDimensionPixelSize(index, 0);
    } else if (index == R.styleable.EasyItem_easy_item_line_top_margin_right) {
      mLineTopRightMargin = typedArray.getDimensionPixelSize(index, 0);
    } else if (index == R.styleable.EasyItem_easy_item_line_top_color) {
      mLineTopColor = typedArray.getColor(index, Color.TRANSPARENT);
    } else if (index == R.styleable.EasyItem_easy_item_line_top_dash_gap) {
      mLineTopDashGap = typedArray.getDimensionPixelSize(index, 0);
    } else if (index == R.styleable.EasyItem_easy_item_line_top_dash_width) {
      mLineTopDashWidth = typedArray.getDimensionPixelSize(index, 0);
    }
    //line bottom
    else if (index == R.styleable.EasyItem_easy_item_line_bottom_visible) {
      mLineBottomVisible = typedArray.getBoolean(index, false);
    } else if (index == R.styleable.EasyItem_easy_item_line_bottom_height) {
      mLineBottomHeight = typedArray.getDimensionPixelSize(index, 0);
    } else if (index == R.styleable.EasyItem_easy_item_line_bottom_margin_left) {
      mLineBottomLeftMargin = typedArray.getDimensionPixelSize(index, 0);
    } else if (index == R.styleable.EasyItem_easy_item_line_bottom_margin_right) {
      mLineBottomRightMargin = typedArray.getDimensionPixelSize(index, 0);
    } else if (index == R.styleable.EasyItem_easy_item_line_bottom_color) {
      mLineBottomColor = typedArray.getColor(index, Color.TRANSPARENT);
    } else if (index == R.styleable.EasyItem_easy_item_line_bottom_dash_gap) {
      mLineBottomDashGap = typedArray.getDimensionPixelSize(index, 0);
    } else if (index == R.styleable.EasyItem_easy_item_line_bottom_dash_width) {
      mLineBottomDashWidth = typedArray.getDimensionPixelSize(index, 0);
    }
    //other
    else if (index == R.styleable.EasyItem_easy_item_pressed_color) {
      mPressedColor = typedArray.getColor(index, Color.GRAY);
    }
  }

  private void initViews() {
    //设置点击效果
    setBackground(getPressedSelector());
    //控件属性设置
    setLeftIv();
    setLeftTv();
    setRightEdt();
    setRightIv();
    setLineTop();
    setLineBottom();
  }

  /** 设置 下边分割线 **/
  private void setLineBottom() {
    ConstraintLayout.LayoutParams lp = (ConstraintLayout.LayoutParams) mLineBottom.getLayoutParams();
    lp.height = mLineBottomHeight;
    lp.setMargins(mLineBottomLeftMargin, 0, mLineBottomRightMargin, 0);
    mLineBottom.setBackgroundColor(mLineBottomColor);
    //上边 虚线 形式
    if (mLineBottomDashWidth > 0) {
      GradientDrawable background = new GradientDrawable();
      background.setShape(GradientDrawable.LINE);
      background.setStroke(mLineBottomHeight, mLineBottomColor, mLineBottomDashWidth, mLineBottomDashGap);
      lp.height = mLineBottomHeight + 1;//高度要比stroke高度要高
      mLineBottom.setLayoutParams(lp);
      mLineBottom.setBackground(background);
    }
    //显示与否
    mLineBottom.setVisibility(mLineBottomVisible ? View.VISIBLE : View.GONE);
  }

  /** 设置 上边分割线 **/
  private void setLineTop() {
    ConstraintLayout.LayoutParams lp = (ConstraintLayout.LayoutParams) mLineTop.getLayoutParams();
    lp.height = mLineTopHeight;
    lp.setMargins(mLineTopLeftMargin, 0, mLineTopRightMargin, 0);
    mLineTop.setBackgroundColor(mLineTopColor);
    //上边 虚线 形式
    if (mLineTopDashWidth > 0) {
      GradientDrawable background = new GradientDrawable();
      background.setShape(GradientDrawable.LINE);
      background.setStroke(mLineTopHeight, mLineTopColor, mLineTopDashWidth, mLineTopDashGap);
      lp.height = mLineTopHeight + 1;//高度要比stroke高度要高
      mLineTop.setLayoutParams(lp);
      mLineTop.setBackground(background);
    }
    //显示与否
    mLineTop.setVisibility(mLineTopVisible ? View.VISIBLE : View.GONE);
  }

  /** 设置 右侧图片 **/
  private void setRightIv() {
    ConstraintLayout.LayoutParams lp = (ConstraintLayout.LayoutParams) mIvRight.getLayoutParams();
    if (mRightIvSize > 0) {
      lp.width = mRightIvSize;
      lp.height = mRightIvSize;
    }
    lp.rightMargin = mRightIvMarginRight;
    mIvRight.setVisibility(mRightIvResId != 0 ? View.VISIBLE : View.GONE);
    mIvRight.setImageResource(mRightIvResId);
  }

  /** 设置 右侧edt **/
  private void setRightEdt() {
    //hint
    mEdtRight.setHint(mRightEdtHint);
    mEdtRight.setHintTextColor(mRightEdtHintColor);
    //text
    mEdtRight.setText(mRightEdtStr);
    mEdtRight.setTextSize(TypedValue.COMPLEX_UNIT_PX, mRightEdtTextSize);
    mEdtRight.setTextColor(mRightEdtTextColor);
    switch (mRightEdtTextStyle) {
      case 0:
        mEdtRight.setTypeface(null, Typeface.NORMAL);
        break;
      case 1:
        mEdtRight.setTypeface(null, Typeface.BOLD);
        break;
    }
    //layout
    ConstraintLayout.LayoutParams lp = (ConstraintLayout.LayoutParams) mEdtRight.getLayoutParams();
    if (mRightEdtWidth > 0) {
      lp.width = mRightEdtWidth;
      lp.leftToRight = 0;
    }
    lp.goneRightMargin = mRightEdtMarginRightParent;
    lp.rightMargin = mRightEdtMarginRightIv;
    mEdtRight.setLayoutParams(lp);
    mEdtRight.setEnabled(mRightEdtEnable);
    //lines
    if (mRightEdtSingleLine) {
      mEdtRight.setSingleLine();
      mEdtRight.setMaxLines(1);
      if (!mRightEdtEnable) { //如果为文字模式，则设置ellipsize为end
        mEdtRight.setEllipsize(TextUtils.TruncateAt.END);
        mEdtRight.setKeyListener(null);
      }
    } else if (mRightEdtMaxLines > 0) {
      mEdtRight.setSingleLine(false);
      mEdtRight.setMaxLines(mRightEdtMaxLines);
    }
  }

  /** 设置左侧 tv **/
  private void setLeftTv() {
    mTvLeft.setText(mLeftTvStr);
    mTvLeft.setTextColor(mLeftTvTextColor);
    switch (mLeftTvTextStyle) {
      case 0:
        mTvLeft.setTypeface(null, Typeface.NORMAL);
        break;
      case 1:
        mTvLeft.setTypeface(null, Typeface.BOLD);
        break;
    }
    mTvLeft.setTextSize(TypedValue.COMPLEX_UNIT_PX, mLeftTvTextSize);
    LayoutParams lp = (LayoutParams) mTvLeft.getLayoutParams();
    lp.leftMargin = mLeftTvMarginIv;
    lp.goneStartMargin = mLeftTvMarginParent;
  }

  /** 设置 左侧Iv **/
  private void setLeftIv() {
    ConstraintLayout.LayoutParams lp = (ConstraintLayout.LayoutParams) mIvLeft.getLayoutParams();
    if (mLeftIvSize > 0) {
      lp.width = mLeftIvSize;
      lp.height = mLeftIvSize;
    }
    lp.leftMargin = mLeftIvMarginLeft;
    mIvLeft.setVisibility(mLeftIvResId != 0 ? View.VISIBLE : View.GONE);
    mIvLeft.setImageResource(mLeftIvResId);
  }

  public StateListDrawable getPressedSelector() {
    GradientDrawable drawablePressed = new GradientDrawable();
    drawablePressed.setColor(mPressedColor);

    GradientDrawable drawableUnPressed = new GradientDrawable();
    drawableUnPressed.setColor(Color.WHITE);

    StateListDrawable stateListDrawable = new StateListDrawable();
    stateListDrawable.addState(new int[] { android.R.attr.state_pressed }, drawablePressed);
    stateListDrawable.addState(new int[] { -android.R.attr.state_pressed }, drawableUnPressed);
    return stateListDrawable;
  }

  private int dp2px(float dp) {
    final float scale = getResources().getDisplayMetrics().density;
    return (int) (dp * scale + 0.5f);
  }

}
