package com.example.estruch18.parametros;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;


public class Activity2 extends Activity {

    //Atributos de la clase
    private EditText carnet, valoracion, puntuacion;
    private ArrayList<String> resultados;
    private Button continuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        //Declaración de atributos
        carnet = (EditText)findViewById(R.id.e_carnet);
        valoracion = (EditText)findViewById(R.id.e_Valoracion);
        puntuacion = (EditText)findViewById(R.id.e_Puntuacion);
        continuar = (Button)findViewById(R.id.b_Continuar);
        resultados = new ArrayList<String>();

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

    public void almacenarResultado(){
        resultados.add(carnet.getText().toString());
        resultados.add(valoracion.getText().toString());
        resultados.add(puntuacion.getText().toString());
    }

    public void accionBotonContinuar(View v){
        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Ejecución de "almacenarResultado()" para guardar resultados
                almacenarResultado();
                // Recogemos el intent que ha llamado a esta actividad.
                Intent i = getIntent();
                // Le metemos el resultado que queremos mandar a la
                // actividad principal.
                i.putExtra("resultado", resultados);
                // Establecemos el resultado, y volvemos a la actividad
                // principal. La variable que introducimos en primer lugar
                // "RESULT_OK" es de la propia actividad, no tenemos que
                // declararla nosotros.
                setResult(RESULT_OK, i);

                // Finalizamos la Activity para volver a la anterior
                finish();
            }
        });
    }
}
