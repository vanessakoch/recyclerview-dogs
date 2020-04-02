package com.example.reciclerviewdogs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CachorroAdapter adapter;
    ImageButton editButton;
    MenuItem addButton;
    private List<Cachorro> dogsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editButton = (ImageButton) findViewById(R.id.iconEdit);
        addButton = (MenuItem) findViewById(R.id.addButton);

        dogsList = new ArrayList<Cachorro>();
        Cachorro dog = new Cachorro();
        dog.initializeData(dogsList);
        adapter = new CachorroAdapter(this, dogsList);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        ItemTouchHelper.Callback callback = new HelperTouchCallback(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return true;
    }

    public void onClickAdd(MenuItem item) {
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_add, null);
        final EditText apelido = (EditText) view.findViewById(R.id.editApelido);
        final EditText tamanho = (EditText) view.findViewById(R.id.editTamanho);
        final EditText localidade = (EditText) view.findViewById(R.id.editLocalidade);
        final EditText sexDog = (EditText) view.findViewById(R.id.editSexDog);

        mBuilder.setView(view);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();

        Button btnAdd = (Button) view.findViewById(R.id.btnAdicionar);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.insertItem(new Cachorro(R.drawable.semimagem, apelido.getText().toString(), tamanho.getText().toString(),
                        sexDog.getText().toString(), localidade.getText().toString()));
                Toast.makeText(view.getContext(),"Adicionado com sucesso! ",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

}
