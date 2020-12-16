package br.com.example.meuprimeiroexemplo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.example.meuprimeiroexemplo.R;
import br.com.example.meuprimeiroexemplo.debug.DebugActivity;

public class PessoaActivity extends DebugActivity {

    EditText textNome, textSobreNome, textEmail, textPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa);
    }

    public void exibir(View view) {
        textNome = findViewById(R.id.textNome);
        textSobreNome = findViewById(R.id.textSobreNome);
        textEmail = findViewById(R.id.textEmail);
        textPhone = findViewById(R.id.textPhone);
        String nome, sobrenome, email, telefone;
        nome = textNome.getText().toString();
        sobrenome = textSobreNome.getText().toString();
        email = textEmail.getText().toString();
        telefone = textPhone.getText().toString();
        String dados;
        dados = String.format("Os valores foram: \n%s\n%s\n%s\n%s", nome, sobrenome, email, telefone);


        Toast.makeText(getApplication(), dados, Toast.LENGTH_LONG).show();
    }
}