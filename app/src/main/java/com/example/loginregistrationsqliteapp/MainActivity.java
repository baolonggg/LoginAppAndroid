package com.example.loginregistrationsqliteapp;

import android.os.Bundle;
import android.content.Intent;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void logout() {
        // Xử lý logic đăng xuất ở đây (ví dụ: xóa thông tin đăng nhập, đặt isLoggedIn = false)

        // Chuyển người dùng đến LoginActivity sau khi logout
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish(); // Kết thúc MainActivity để người dùng không thể quay lại bằng nút back
    }

}