package br.com.example.meuprimeiroexemplo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.example.meuprimeiroexemplo.R;
import br.com.example.meuprimeiroexemplo.model.Comments;

public class CommentsAdapter extends BaseAdapter {

    private final Context context;
    private final List<Comments> comments;

    public CommentsAdapter(Context context, List<Comments> comments) {
        this.context = context;
        this.comments = comments;
    }

    @Override
    public int getCount() {
        return this.comments.size();
    }

    @Override
    public Comments getItem(int i) {
        return this.comments.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.comments_item_post, viewGroup, false);
        }

        Comments comments = getItem(posicao);

        TextView txtItemPostId, txtItemId, txtItemNome, txtItemEmail,
                txtItemBody;

        txtItemPostId = view.findViewById(R.id.txtItemPostId);
        txtItemId = view.findViewById(R.id.txtItemId);
        txtItemNome = view.findViewById(R.id.txtItemNome);
        txtItemEmail = view.findViewById(R.id.txtItemEmail);
        txtItemBody = view.findViewById(R.id.txtItemBody);

        txtItemPostId.setText(String.valueOf(comments.getPostId()));
        txtItemId.setText(String.valueOf(comments.getId()));
        txtItemNome.setText(comments.getName());
        txtItemEmail.setText(comments.getEmail());
        txtItemBody.setText(comments.getBody());

        return view;
    }
}
