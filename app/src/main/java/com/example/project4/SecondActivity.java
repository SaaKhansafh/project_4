package com.example.project4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    private EditText editTextReply;
    private TextView textViewReceived;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_second);

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

        textViewReceived = findViewById(R.id.textViewReceived);
        editTextReply = findViewById(R.id.editTextReply);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(MainActivity.EXTRA_MESSAGE)) {
            String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
            if (message != null && !message.trim().isEmpty()) {
                textViewReceived.setText(message);
            } else {
                textViewReceived.setText("(Pesan kosong)");
            }
        }
    }

    public void returnReply(View view) {
        String reply = editTextReply.getText().toString();

        Intent replyIntent = new Intent();
        replyIntent.putExtra(MainActivity.EXTRA_REPLY, reply);
        setResult(RESULT_OK, replyIntent);

        finish();
    }
}
