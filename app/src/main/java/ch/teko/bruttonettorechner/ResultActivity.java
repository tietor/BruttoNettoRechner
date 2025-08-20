package ch.teko.bruttonettorechner;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        Bundle values = getIntent().getExtras();

        String input = values.getString("input");
        TextView inputNumber = findViewById(R.id.inputValue);
        inputNumber.setText(input);

        String percentageValue = values.getString("percentage");
        TextView percentage = findViewById(R.id.percentage);
        percentage.setText(percentageValue);

        String result = values.getString("output");
        TextView resultNumber = findViewById(R.id.result);
        resultNumber.setText(result);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}