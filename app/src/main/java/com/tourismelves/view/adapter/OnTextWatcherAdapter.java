package com.tourismelves.view.adapter;


import android.text.Editable;
import android.text.TextWatcher;

public abstract class OnTextWatcherAdapter implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public abstract void onTextChanged(CharSequence charSequence, int length, int i1, int i2);

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
