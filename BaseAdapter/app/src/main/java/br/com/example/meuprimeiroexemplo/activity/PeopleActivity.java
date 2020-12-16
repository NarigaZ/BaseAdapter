package br.com.example.meuprimeiroexemplo.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.example.meuprimeiroexemplo.R;
import br.com.example.meuprimeiroexemplo.adapter.PeopleAdapter;
import br.com.example.meuprimeiroexemplo.bootstrap.PeopleAPI;
import br.com.example.meuprimeiroexemplo.debug.DebugActivity;
import br.com.example.meuprimeiroexemplo.model.DefaultModel;
import br.com.example.meuprimeiroexemplo.model.People;
import br.com.example.meuprimeiroexemplo.resource.PeopleResource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PeopleActivity extends DebugActivity {

    ListView listViewPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
    }

    public void listarPosts(View view) {

        Retrofit retrofit = PeopleAPI.getClient();

        PeopleResource peopleResource = retrofit.create(PeopleResource.class);

        Call<DefaultModel> lista = peopleResource.get();
        try {
            lista.enqueue(new Callback<DefaultModel>() {
                @Override
                public void onResponse(Call<DefaultModel> call, Response<DefaultModel> response) {
                    try {
                        DefaultModel resposta = response.body();

                        List<People> pessoas = resposta.getResults();

                        PeopleAdapter p =
                                new PeopleAdapter(getApplicationContext(), pessoas);

                        listViewPeople = findViewById(R.id.peopleList);
                        listViewPeople.setAdapter(p);

                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "ERRO  " +
                                        "no processamento.\n\n" + e.getMessage(),
                                Toast.LENGTH_LONG).show();
                        Log.e("lista -- Erro", "\n\n" + e.getMessage() + "\n" +
                                "\n");
                    }
                }

                @Override
                public void onFailure(Call<DefaultModel> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "ERRO" +
                            "no serviço.\n" + t.getMessage(), Toast.LENGTH_LONG).show();
                    Log.e("app-people", "\n\n" + t.getMessage() + "\n\n");
                }
            });
        } catch (Exception e) {
            Log.i("ERRO", e.getMessage());
        }
    }
}