package net.hackdevicz.s0bek.healthmanager;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

//mapping contexte-vue
import net.hackdevicz.s0bek.healthmanager.classes.User;

public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    //les variables de vues peuvent être définies ici pour récupérer tout le contenu dans n'importe quelle méthode
    EditText size = null;
    EditText weight = null;
    EditText age = null;
    TextView IMC = null;
    TextView IMG = null;
    TextView imc = null;
    TextView img = null;

    double user_size;
    double user_weight;
    int user_age;
    String user_IMC;
    String user_IMG;
    String sexe = "";

    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        size = (EditText) findViewById(R.id.size_value);
        size.setPadding(10,5,5,5);

        weight = (EditText) findViewById(R.id.weight_value);
        weight.setPadding(10,5,5,5);

        age = (EditText) findViewById(R.id.age_value);
        age.setPadding(10,5,5,5);

        IMC = (TextView) findViewById(R.id.imc_value);
        IMG = (TextView) findViewById(R.id.img_value);

        imc = (TextView) findViewById(R.id.imc_display);
        img = (TextView) findViewById(R.id.img_display);

        imc.setText("");
        img.setText("");

        //on récupère les données une fois que toutes les données ont bien été saisies
        Button result = (Button) findViewById(R.id.Result);
        result.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        if (id == R.id.Result) {//clic sur le bouton de calcul

            //récupération des données de la vue une fois seulement que l'utilisateur a cliqué sur le bouton de calcul

            user_IMC = IMC.getText().toString();
            user_IMG = IMG.getText().toString();

            String size_content = size.getText().toString();
            String weight_content = weight.getText().toString();
            String age_content = age.getText().toString();

            //on récupère les données uniquement si elles sont remplies, sinon ca plante!
            if (!size_content.isEmpty() && !weight_content.isEmpty() && !age_content.isEmpty() && !(sexe.equals(""))) {

                user_size = Double.parseDouble(size_content);
                user_weight = Double.parseDouble(weight_content);
                user_age = Integer.parseInt(age_content);

                user.setSize(user_size);
                user.setAge(user_age);
                user.setSexe(sexe);
                user.setWeight(user_weight);

                user.imc_calculate();
                user.img_calculate();
                double imc_result = user.getImc();
                double img_result = user.getImg();

                Log.e("AGE:", "" + user.getAge());
                Log.e("SIZE:", "" + user.getSize());
                Log.e("SEXE:", "" + user.getSexe());
                Log.e("WEIGHT:", "" + user.getWeight());

                /*IMC.setText(user_IMC + " " + imc_result);
                IMG.setText(user_IMG + " " + img_result);*/
                imc.setText(String.format("%s", imc_result));
                img.setText(String.format("%s", img_result));

                Toast.makeText(this, String.format("Votre IMC: %s", imc_result), Toast.LENGTH_LONG).show();
                Toast.makeText(this, String.format("Votre IMG: %s", img_result), Toast.LENGTH_LONG).show();

            }

        }

    }

    //méthode pour tester les radiobutton qui ont été cochés dans la vue associée, à nous de la définir nous même en l'appelant dans le XML associé à la vue
    public void onRadioButtonClicked(View v) {

        //on cast la vue "v" en RadioButton pour ensuite pouvoir appliquer la méthode isChecked
        boolean checked = ((RadioButton)v).isChecked();

        switch (v.getId()) {
            case R.id.male_radiobutton:
                if (checked) {
                    sexe = "Homme";
                }
                break;

            case R.id.female_radiobutton:
                if (checked) {
                    sexe = "Femme";
                }
            break;
        }
    }

}
