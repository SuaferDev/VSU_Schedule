package com.suafer.vsucs;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.suafer.vsucs.classes.Info;
import com.suafer.vsucs.classes.Setting;
import com.suafer.vsucs.enumpack.ScheduleType;
import com.suafer.vsucs.enumpack.VersionStatus;
import com.suafer.vsucs.tool.AppStore;
import com.suafer.vsucs.tool.CustomBackgroundAdapter;
import com.suafer.vsucs.tool.CustomFragmentAdapter;
import com.suafer.vsucs.tool.CustomToast;
import com.suafer.vsucs.tool.SlidePagerAdapter;

public class MainActivity extends AppCompatActivity {
    private PagerAdapter pagerAdapter;
    public DatabaseReference versionDataBase;
    private VersionStatus status;
    private SharedPreferences SaveSetting;
    private LinearLayout main_linear;
    public static Setting setting;
    private CustomFragmentAdapter customFragmentAdapter;
    private ViewPager viewpager;
    private static final int PICK_IMAGE = 100;
    private static final int PERMISSION_REQUEST_CODE = 100;
    private boolean permision = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SaveSetting = getSharedPreferences("setting", MODE_PRIVATE);
        String json = SaveSetting.getString("setting", "");
        Gson gsonSetting = new Gson();
        Type type = new TypeToken<Setting>() {}.getType();
        setting = gsonSetting.fromJson(json, type);

        if(setting==null){setting=AppStore.createDefoult();}

        viewpager = findViewById(R.id.viewpager);
        setPage();
        main_linear = findViewById(R.id.main_linear);


        ImageView image_menu = findViewById(R.id.image_menu);
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            if(setting.getPathBackground()!=null){
                try{
                    Drawable drawable = Drawable.createFromPath(setting.getPathBackground());
                    if(drawable!=null){main_linear.setBackground(drawable);}
                }catch (Exception e){
                    CustomToast.showCustomToast(MainActivity.this, 0, "Не удалось установить изображение");
                    main_linear.setBackgroundResource(AppStore.getBackground(setting.getBackground()));
                }
            }else{
                main_linear.setBackgroundResource(AppStore.getBackground(setting.getBackground()));
            }
        } else {
            main_linear.setBackgroundResource(AppStore.getBackground(setting.getBackground()));
        }


        versionDataBase = FirebaseDatabase.getInstance().getReference("Info");
        getDataFromDB();

        if(!isInternetAvailable()){status = VersionStatus.CannotDetermined;}

        image_menu.setOnClickListener(view -> {createVersionDialog();});
    }

    public void getDataFromDB(){
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot postSnapshot) {
                Info versionInfo = postSnapshot.getValue(Info.class);
                assert versionInfo != null;
                if(!Objects.equals(getAppVersion(), versionInfo.getVersion())){
                    CustomToast.showCustomToast(MainActivity.this,Toast.LENGTH_SHORT, getResources().getString(R.string.toast_ver));
                    status = VersionStatus.Outdated;
                }else{
                    status = VersionStatus.New;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("TAG", error.getMessage());
            }
        };
        versionDataBase.addValueEventListener(vListener);
    }
    private boolean isInternetAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
    private void createVersionDialog(){
        Dialog dialogSetting = new Dialog(MainActivity.this);
        dialogSetting.setContentView(R.layout.custom_dialog_setting);
        dialogSetting.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialogSetting.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogSetting.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationTop;

        dialogSetting.setCancelable(true);
        TextView text_version = dialogSetting.findViewById(R.id.text_version);
        TextView text_version_now = dialogSetting.findViewById(R.id.text_version_now);
        ImageView image_version = dialogSetting.findViewById(R.id.image_version);
        ListView list_page = dialogSetting.findViewById(R.id.list_page);
        TextView schedule1 = dialogSetting.findViewById(R.id.schedule1);
        TextView schedule2 = dialogSetting.findViewById(R.id.schedule2);

        if(setting.getSchedule().getScheduleType()== ScheduleType.First){
            schedule1.setBackgroundResource(R.drawable.background1234);
        }else{
            schedule2.setBackgroundResource(R.drawable.background1234);
        }

        schedule1.setOnClickListener(view ->{
            schedule2.setBackgroundResource(0x00000000);
            schedule1.setBackgroundResource(R.drawable.background1234);
            setting.setSchedule(AppStore.createSchedule1());
        });
        schedule2.setOnClickListener(view ->{
            schedule1.setBackgroundResource(0x00000000);
            schedule2.setBackgroundResource(R.drawable.background1234);
            setting.setSchedule(AppStore.createSchedule2());
        });

        TextView text_back = dialogSetting.findViewById(R.id.text_back);
        ImageView image_back = dialogSetting.findViewById(R.id.image_back);
        LinearLayout linear_add_back = dialogSetting.findViewById(R.id.linear_add_back);

        customFragmentAdapter = new CustomFragmentAdapter(setting.getPages(),this);
        list_page.setAdapter(customFragmentAdapter);

        RecyclerView recyclerView = dialogSetting.findViewById(R.id.recyclerView);

        text_version.setText(getResources().getString(AppStore.getVersionString(status)));
        image_version.setImageResource(AppStore.getVersionImage(status));
        text_version_now.setText(getAppVersion());

        list_page.setOnItemClickListener((parent, view, position, id) -> {
            if(setting.getPages().get(position).getStatus()){MainActivity.setting.deletePage(setting.getPages().get(position).getPageType());
            }else{MainActivity.setting.addPage(setting.getPages().get(position).getPageType());}
            customFragmentAdapter = new CustomFragmentAdapter(setting.getPages(),this);
            list_page.setAdapter(customFragmentAdapter);
            setPage();
        });

        if(setting.getPathBackground()==null){
            text_back.setText("Добавить изображение");
            image_back.setImageResource(R.drawable.icon_add);
        }else{
            text_back.setText("Установлено Ваше изображение");
            image_back.setImageResource(R.drawable.icon_ok_green);
        }

        linear_add_back.setOnClickListener(v -> {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                openImageChooser();
                if(setting.getPathBackground()==null){
                    text_back.setText("Добавить изображение");
                    image_back.setImageResource(R.drawable.icon_add);
                }else{
                    text_back.setText("Установлено Ваше изображение");
                    image_back.setImageResource(R.drawable.icon_ok_green);
                }
            } else {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
            }
        });

        CustomBackgroundAdapter customBackgroundAdapter = new CustomBackgroundAdapter(AppStore.backgrounds, MainActivity.this);
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 1, GridLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(customBackgroundAdapter);

        dialogSetting.show();
    }


    private void openImageChooser() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            Uri imageUri = data.getData();
            try {
                setting.setPathBackground(getImagePath(imageUri));
                Drawable drawable = Drawable.createFromPath(getImagePath(imageUri));
                main_linear.setBackground(drawable);
            } catch (Exception e) {e.printStackTrace();}
        }
    }


    private String getImagePath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String imagePath = cursor.getString(column_index);
            cursor.close();
            return imagePath;
        }
        return uri.getPath();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                permision = true;
            } else {
                showPermissionDeniedDialog();
            }
        }
    }

    private void showPermissionDeniedDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Доступ к медиафайлам")
                .setMessage("Для того чтобы получить доступ к медиафайлам, пожалуйста, разрешите доступ в настройках приложения.")
                .setPositiveButton("Настройки", (dialog, which) -> {
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                    intent.setData(uri);
                    startActivity(intent);
                }).setNegativeButton("Отмена", (dialog, which) -> dialog.dismiss()).create().show();
    }



    private void setPage(){
        List<Fragment> fragmentList = AppStore.createFragmentList(setting.getPages());
        pagerAdapter = new SlidePagerAdapter(getSupportFragmentManager(), fragmentList);
        viewpager.setAdapter(pagerAdapter);
    }

    private String getAppVersion(){
        String appVersion = "";
        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            appVersion = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appVersion;
    }

    private long backPressedTime;
    @Override
    public void onBackPressed(){
        if(backPressedTime+2000>System.currentTimeMillis()){
            super.onBackPressed();
            return;
        }else{
            CustomToast.showCustomToast(this, Toast.LENGTH_LONG, getResources().getString(R.string.toast_back));
        }
        backPressedTime = System.currentTimeMillis();
    }

    private void save(){
        SharedPreferences.Editor editorSetting = SaveSetting.edit();
        Gson gsonSetting = new Gson();
        String json = gsonSetting.toJson(setting);
        editorSetting.putString("setting", json);
        editorSetting.apply();
    }

    @Override
    public void onStop() {
        super.onStop();
        save();
    }
}