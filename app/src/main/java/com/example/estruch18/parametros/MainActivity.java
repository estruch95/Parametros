package com.example.estruch18.parametros;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class MainActivity extends Activity {

    //Atributos de la clase
    private Switch carnetConducir;
    private RatingBar valoracionPractica;
    private SeekBar puntuacion;
    private Button siguiente;
    private EditText puntuacionActual;

    private EditText e_carnet, e_valoracion, e_puntuacion;

    //Variables de datos donde volcaremos las selecciones del usuario
    private String carnet;
    private Float valoracionSeleccionada;
    private int progreso;

    private static final int ACTIVITY2=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Declaración de atributos (id)
        carnetConducir = (Switch)findViewById(R.id.s_Conduccion);
        valoracionPractica = (RatingBar)findViewById(R.id.r_Valoracion);
        puntuacion = (SeekBar)findViewById(R.id.s_Puntuacion);
        siguiente = (Button)findViewById(R.id.b_Siguiente);
        puntuacionActual = (EditText)findViewById(R.id.e_infoPuntuacion);

        e_carnet = (EditText)findViewById(R.id.e_carnet);
        e_valoracion = (EditText)findViewById(R.id.e_Valoracion);
        e_puntuacion = (EditText)findViewById(R.id.e_Puntuacion);

        //Ejecución de métodos
        actualizarSeekBar();
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

    //Mis Métodos

    //Método que recoge la selección del componente switch
    public void seleccionSwitch(){
        if(carnetConducir.isChecked()){
            //Si está checkeado seteamos la variable "carnet"
            carnet = "Si";
        }
        else{
            carnet = "No";
        }
    }

    //Método que recoge la selección del componente ratingBar
    public void seleccionValoracion(){
        valoracionSeleccionada = valoracionPractica.getRating();
        //COMPROBACIÓN
        //Toast.makeText(getApplicationContext(),String.valueOf(valoracionSeleccionada), Toast.LENGTH_SHORT).show();
    }

    public void seleccionPuntuacion(){
        progreso = puntuacion.getProgress();
        //COMPROBACIÓN
        //Toast.makeText(getApplicationContext(),String.valueOf(progreso), Toast.LENGTH_SHORT).show();
    }

    public void mostrarPuntuacion(){
        String punt = String.valueOf(puntuacion.getProgress());
        puntuacionActual.setText(punt + " %");
    }

    public void actualizarSeekBar(){
        String punt = String.valueOf(puntuacion.getProgress());
        puntuacionActual.setText(punt + " %");

        puntuacion.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mostrarPuntuacion();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mostrarPuntuacion();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mostrarPuntuacion();
            }
        });
    }

    public void accionBotonSiguiente(View v){
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Ejecución de métodos para recoger datos o valores introducidos por el usuario
                seleccionSwitch();
                seleccionValoracion();
                seleccionPuntuacion();
                mostrarPuntuacion();

                //Declaración de Intent para cambio a Activity2
                Intent act2 = new Intent(getApplicationContext(), Activity2.class);

                //Paso de datos necesarios para subactivity2
                act2.putExtra("carnetConducir", carnet);
                act2.putExtra("numEstrellas", valoracionSeleccionada);
                act2.putExtra("numProgreso", progreso);

                //Iniciamos activity2
                startActivityForResult(act2, ACTIVITY2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case ACTIVITY2:
                gestionActivity2(resultCode, data);
                break;
        }

    }

    public void gestionActivity2(int resultCode, Intent data){
        if(resultCode == RESULT_OK){
            ArrayList<String> resultado = data.getExtras().getStringArrayList("resultado");
            Toast.makeText(getApplicationContext(),"resultados obtenidos OK", Toast.LENGTH_SHORT).show();

            e_carnet.setText(resultado.get(0));
            e_valoracion.setText(resultado.get(1));
            e_puntuacion.setText(resultado.get(2));

        }
        else{
            Toast.makeText(getApplicationContext(),"Error en el subActivity 2.", Toast.LENGTH_SHORT).show();
        }
    }
}
