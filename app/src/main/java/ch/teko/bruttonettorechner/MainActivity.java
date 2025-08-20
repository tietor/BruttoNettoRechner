package ch.teko.bruttonettorechner;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner percentageSpinner;
    EditText inputField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        this.inputField = findViewById(R.id.input_value);
        handleDropdown();
        RadioGroup group = findViewById(R.id.radio_group);
        group.check(R.id.radio_brutto);
        Button submitButton = findViewById(R.id.button);
        submitButton.setOnClickListener(view -> {
            Editable textOfInputField = this.inputField.getText();
            if (textOfInputField != null) {

                RadioButton selectedRadioButton = findViewById(group.getCheckedRadioButtonId());
                double input = Double.parseDouble(textOfInputField.toString());
                String selectedValueOfRadioButton = selectedRadioButton.getText().toString();
                int selectedPercentage = getSelectedPercentage();
                Intent intent = new Intent(this, ResultActivity.class);
                intent.putExtra("percentage", "Prozent: " + selectedPercentage);
                switch (selectedValueOfRadioButton) {
                    case "Brutto": {
                        double netto = (input / 100) * (100 - selectedPercentage);
                        intent.putExtra("input", "Brutto: " + input);
                        intent.putExtra("output", "Netto: " + netto);
                        break;
                    }
                    case "Netto": {
                        double brutto = (input / (100 - selectedPercentage)) * 100;
                        intent.putExtra("input", "Netto: " + input);
                        intent.putExtra("output", "Brutto: " + brutto);
                    }
                }
                startActivity(intent);
            }

        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private int getSelectedPercentage() {
        String valueOfSelectedItem = this.percentageSpinner.getSelectedItem().toString();
        int[] percentages = getResources().getIntArray(R.array.prozent_internal);
        for (int percentage : percentages) {
            if (valueOfSelectedItem.contains(String.valueOf(percentage))) {
                return percentage;
            }
        }
        return 0;
    }

    private void handleDropdown() {
        this.percentageSpinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.prozent_display, android.R.layout.simple_spinner_item);
        percentageSpinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}