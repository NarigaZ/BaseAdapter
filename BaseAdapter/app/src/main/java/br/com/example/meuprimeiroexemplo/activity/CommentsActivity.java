package br.com.example.meuprimeiroexemplo.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.example.meuprimeiroexemplo.R;
import br.com.example.meuprimeiroexemplo.adapter.CommentsAdapter;
import br.com.example.meuprimeiroexemplo.bootstrap.APIClient;
import br.com.example.meuprimeiroexemplo.debug.DebugActivity;
import br.com.example.meuprimeiroexemplo.model.Comments;
import br.com.example.meuprimeiroexemplo.resource.CommentsResource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CommentsActivity extends DebugActivity {

    EditText txtPostId, txtNome, txtEmail, txtBody;
    ListView listViewPost;
    final List<HashMap<String, String>> lista = new ArrayList<>();
    final List<Comments> comments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
    }

    public void adicionarPost(View view) {
        Retrofit retrofit = APIClient.getClient();
        CommentsResource commentsResource = retrofit.create(CommentsResource.class);

        Call<List<Comments>> chamada = commentsResource.get();

        chamada.enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(Call<List<Comments>> call,
                                   Response<List<Comments>> response) {
                List<Comments> postagens = response.body();
                for (Comments c : postagens) {
                    baseAdapter(c.getPostId(), c.getId(), c.getName(),
                            c.getEmail(),
                            c.getBody());
                }
            }

            @Override
            public void onFailure(Call<List<Comments>> call, Throwable t) {
                Log.i("Falha na comunicação\n", t.getMessage());
                Toast.makeText(getApplicationContext(), "Erro" + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

        Button b = findViewById(R.id.btnAddPost);
        b.setClickable(false);

        Toast.makeText(getApplicationContext(), "No Click!",
                Toast.LENGTH_LONG).show();
    }

    private void baseAdapter(Integer postId, Integer id, String nome,
                             String email,
                             String body) {

        preencherObjetoLista(postId, id, nome, email, body);

        listViewPost = findViewById(R.id.listViewComments);

        CommentsAdapter commentsAdapter = new CommentsAdapter(getApplicationContext(), comments);

        listViewPost.setAdapter(commentsAdapter);
    }

    private void preencherObjetoLista(Integer postId, Integer id, String name,
                                      String email,
                                      String body) {
        try {
            Comments comment =
                    Comments.builder().postId(postId).id(id).name(name).email(email).body(body).build();

            comments.add(comment);
        } catch (Exception e) {
            Log.i("prencherObjetoLista\n\n\"", e.getMessage());
            Toast.makeText(getApplicationContext(),
                    "Erro" + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
