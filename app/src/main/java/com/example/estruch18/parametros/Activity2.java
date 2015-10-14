package com.example.estruch18.parametros;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


public class Activity2 extends Activity {

    //Atributos de la clase
    private EditText carnet, valoracion, puntuacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        //Declaración de atributos
        carnet = (EditText)findViewById(R.id.e_carnet);
        valoracion = (EditText)findViewById(R.id.e_Valoracion);
        puntuacion = (EditText)findViewById(R.id.e_Puntuacion);

        //Recogida de datos pasados por parametro desde objeto "Bundle"
        Bundle bundle = getIntent().getExtras();

        //Mostrado de los datos recibidos por parámetro en sus respectivos campos.
        //PD: (Observación de recogida de datos desde objeto bundle: bundle.get(TipoObjeto))
        carnet.setText(bundle.getString("carnetConducir"));
        valoracion.setText(bundle.getFloat("numEstrellas")+" ESTRELLAS.");
        puntuacion.setText(bundle.getInt("numProgreso")+" %");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity2, menu);
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
}
