package com.example.project4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.project4.extra.MESSAGE";
    public static final String EXTRA_REPLY = "com.example.project4.extra.REPLY";

    private EditText editTextMessage;
    private TextView textHeaderReply;
    private TextView textViewReply;

    private final ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    String reply = result.getData().getStringExtra(EXTRA_REPLY);
                    if (reply != null && !reply.trim().isEmpty()) {
                        textHeaderReply.setText(R.string.text_header_reply);
                        textHeaderReply.setVisibility(View.VISIBLE);
                        textViewReply.setText(reply);
                        textViewReply.setVisibility(View.VISIBLE);
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            int padding16dp = (int) (16 * getResources().getDisplayMetrics().density);
            v.setPadding(
                systemBars.left + padding16dp,
                systemBars.top + padding16dp,
                systemBars.right + padding16dp,
                systemBars.bottom + padding16dp
            );
            return insets;
        });

        editTextMessage = findViewById(R.id.editTextMessage);
        textHeaderReply = findViewById(R.id.text_header_reply);
        textViewReply = findViewById(R.id.textViewReply);
    }

    public void openSecondActivity(View view) {
        String message = editTextMessage.getText().toString();

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(EXTRA_MESSAGE, message);

        mActivityResultLauncher.launch(intent);
    }
}
