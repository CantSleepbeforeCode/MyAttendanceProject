package com.example.fingerprint;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class BiometricDialogV23 extends BottomSheetDialog implements View.OnClickListener {
    private Context context;
    private Button btnCancel;
    private ImageView imgLogo;
    private TextView tvTitle, tvDescription, tvSubtitle, tvStatus;
    private BiometricCallback biometricCallback;

    public BiometricDialogV23(@NonNull Context context) {
        super(context, R.style.BottomSheetDialogTheme);
        this.context = context.getApplicationContext();
        setDialogView();
    }

    public BiometricDialogV23(@NonNull Context context, BiometricCallback biometricCallback) {
        super(context, R.style.BottomSheetDialogTheme);
        this.context = context.getApplicationContext();
        this.biometricCallback = biometricCallback;
        setDialogView();
    }

    public BiometricDialogV23(@NonNull Context context, int theme) {
        super(context, theme);
    }

    protected BiometricDialogV23(@NonNull Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    private void setDialogView() {
        View bottomSheetView = getLayoutInflater().inflate(R.layout.view_bottom_sheet, null);
        setContentView(bottomSheetView);

        btnCancel = findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(this);

        imgLogo = findViewById(R.id.img_logo);
        tvTitle = findViewById(R.id.tv_title);
        tvStatus = findViewById(R.id.tv_status);
        tvSubtitle = findViewById(R.id.tv_subtitle);
        tvDescription = findViewById(R.id.tv_description);
        updateLogo();
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void updateStatus(String status) {
        tvStatus.setText(status);
    }

    public void setSubtitle(String subtitle) {
        tvSubtitle.setText(subtitle);
    }

    public void setDescription(String description) {
        tvDescription.setText(description);
    }

    public void setButtonText(String negativeButtonText) {
        btnCancel.setText(negativeButtonText);
    }

    private void updateLogo() {
        try {
            Drawable drawable = getContext().getPackageManager().getApplicationIcon(context.getPackageName());
            imgLogo.setImageDrawable(drawable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        dismiss();
        biometricCallback.onAuthenticationCancelled();
    }


}
