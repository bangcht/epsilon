package com.epsilon.screens.register;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.epsilon.R;
import com.epsilon.commons.GenericRetainedToolbarFragment;
import com.epsilon.screens.main.MainActivity;
import com.epsilon.utils.Utils;

import net.qiujuer.genius.widget.GeniusSeekBar;

import butterknife.Bind;
import butterknife.OnClick;
import utils.Injection;

/**
 * Created by Dandoh on 4/9/16.
 */
public class RegisterFragment extends GenericRetainedToolbarFragment implements RegisterContract.View, View.OnFocusChangeListener {


    @Bind(R.id.register_edt_password)
    EditText mPasswordEditText;
    @Bind(R.id.register_edt_username)
    EditText mUsernameEditText;

    @Bind(R.id.seekBar1)
    GeniusSeekBar mSeekBar1;
    @Bind(R.id.seekBar2)
    GeniusSeekBar mSeekBar2;
    @Bind(R.id.seekBar3)
    GeniusSeekBar mSeekBar3;
    @Bind(R.id.seekBar4)
    GeniusSeekBar mSeekBar4;
    @Bind(R.id.seekBar5)
    GeniusSeekBar mSeekBar5;
    @Bind(R.id.seekBar6)
    GeniusSeekBar mSeekBar6;
    @Bind(R.id.seekBar7)
    GeniusSeekBar mSeekBar7;
    @Bind(R.id.seekBar8)
    GeniusSeekBar mSeekBar8;

    @Bind(R.id.register_edt_repassword)
    EditText mRepasswordEditText;


    private int mCurrentScreenIndex = 1; // begin screen

    @Bind(R.id.wizard_container)
    ViewAnimator mWizardContainer;

    private RegisterContract.UserActionListener mUserActionListener;

    public static Fragment getInstance() {
        return new RegisterFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUserActionListener = new RegisterPresenter(this, Injection.provideUserRepository());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getView() != null) {
            getView().requestFocus();
            getView().setFocusableInTouchMode(true);
            getView().setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        if (mCurrentScreenIndex == 2) {
                            wizardBack();
                            return true;
                        }
                    }

                    return false;
                }
            });
        }

//        mPasswordEditText.setOnFocusChangeListener(this);
//        mUsernameEditText.setOnFocusChangeListener(this);

        setUpSignUpWizardView();
        setUpSeekBars();
    }

    private void setUpSeekBars() {
        CustomSeekBarOnTouchListener listener = new CustomSeekBarOnTouchListener();
        mSeekBar1.setOnTouchListener(listener);
        mSeekBar2.setOnTouchListener(listener);
        mSeekBar3.setOnTouchListener(listener);
        mSeekBar4.setOnTouchListener(listener);
        mSeekBar5.setOnTouchListener(listener);
        mSeekBar6.setOnTouchListener(listener);
        mSeekBar7.setOnTouchListener(listener);
        mSeekBar8.setOnTouchListener(listener);
    }

    void setUpSignUpWizardView() {
        // Kinda tricky, set display child if configuration change happen
        mWizardContainer.setDisplayedChild(mCurrentScreenIndex - 1);
    }

    private void wizardBack() {
        mCurrentScreenIndex--;
        mWizardContainer.setInAnimation(getContext(), R.anim.pull_in_left);
        mWizardContainer.setOutAnimation(getContext(), R.anim.push_out_right);
        mWizardContainer.showPrevious();
    }

    private void wizardNext() {
        mCurrentScreenIndex++;
        mWizardContainer.setInAnimation(getContext(), R.anim.pull_in_right);
        mWizardContainer.setOutAnimation(getContext(), R.anim.push_out_left);
        mWizardContainer.showNext();
    }

    @OnClick(R.id.register_btn_next)
    void completeBasic() {
        mUserActionListener.completeBasic(mUsernameEditText.getText().toString(),
                mPasswordEditText.getText().toString(), mRepasswordEditText.getText().toString());
    }

    @OnClick(R.id.register_btn_complete)
    void completeRegister() {
        Utils.log(TAG, "Calling complete register");

        int[] favorite = new int[]{
                mSeekBar1.getProgress(),
                mSeekBar2.getProgress(),
                mSeekBar3.getProgress(),
                mSeekBar4.getProgress(),
                mSeekBar5.getProgress(),
                mSeekBar6.getProgress(),
                mSeekBar7.getProgress(),
                mSeekBar8.getProgress(),
        };

        mUserActionListener.register(mUsernameEditText.getText().toString(),
                mPasswordEditText.getText().toString(),
                favorite);

    }

    @Override
    public void goToRegisterAddCategory() {
        hideKeyboard(getView());
        if (mCurrentScreenIndex == 1) {
            wizardNext();
        }
    }

    private void hideKeyboard(View view) {
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            if (mCurrentScreenIndex == 2) {
                wizardBack();
            } else {
                getActivity().onBackPressed();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void displayErrorUsername() {
        Toast.makeText(getActivity(), "Username is invalid", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayErrorPassword() {
        Toast.makeText(getActivity(), "Password is invalid", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToMainScreen() {
        Utils.log(TAG, "register succeed");

        Intent intent = MainActivity.makeIntent(getActivity(), MainActivity.RECOMMEND_TAB_POSITION);
        getActivity().startActivity(intent);
    }

    @Override
    public void displayRegisterSucceed() {
        Toast.makeText(getActivity(), "Register succeed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayRegisterError(String errorMessage) {
        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayPasswordNotMatchError() {
        Toast.makeText(getActivity(), "Password not matched", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        Utils.log(TAG, "Calling this " + hasFocus);
        if (!hasFocus) {
            hideKeyboard(v);
        }
    }

    public static class CustomSeekBarOnTouchListener implements View.OnTouchListener {

        private final float mNumOfLevel = 5.0f;
        private String TAG = "CustomSeekBarOnTouchListener";


        @Override
        public boolean onTouch(View v, MotionEvent event) {

            Utils.log(TAG, "calling this");

            int levelToGo = getLevelToGo(v.getWidth(), event.getX());
            Utils.log(TAG, levelToGo + " ");
            if (levelToGo > 0) {
                ((GeniusSeekBar) v).setProgress(levelToGo);
            }
            return false;
        }

        private int getLevelToGo(float maxWidth, float x) {

            Utils.log(TAG, "maxWidthd = " + maxWidth);
            Utils.log(TAG, "x = " + x);

            for (int i = 1; i <= mNumOfLevel; i++) {
                float xi = (maxWidth / (mNumOfLevel - 1)) * (i - 1);
                Utils.log(TAG, "x" + i + " " + xi);

                if (x >= xi - 1 / (2 * mNumOfLevel) * maxWidth
                        && x <= xi + 1 / (2 * mNumOfLevel) * maxWidth) {
                    return i;
                }
            }

            return -1;
        }


    }
}
