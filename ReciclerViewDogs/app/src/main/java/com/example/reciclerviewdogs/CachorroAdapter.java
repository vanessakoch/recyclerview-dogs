package com.example.reciclerviewdogs;


import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.Collections;
import java.util.List;

public class CachorroAdapter extends RecyclerView.Adapter<CachorroAdapter.DogViewHolder> {

    List<Cachorro> cachorrosList;
    MainActivity mainActivity;
    private int posicaoRemovida;
    private Cachorro cachorroRemovido;

    CachorroAdapter(MainActivity mainActivity, List<Cachorro> cachorrosList){
        this.mainActivity = mainActivity;
        this.cachorrosList = cachorrosList;
    }

    @NonNull
    @Override
    public DogViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem, viewGroup, false);
        DogViewHolder viewHolder = new DogViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final DogViewHolder dogViewHolder, int i) {
        dogViewHolder.dogPhoto.setImageResource(cachorrosList.get(i).getImagem());
        dogViewHolder.dogNickname.setText(cachorrosList.get(i).getApelido());
        dogViewHolder.dogSize.setText(cachorrosList.get(i).getTamanho());
        dogViewHolder.dogSex.setText(cachorrosList.get(i).getSexo());
        dogViewHolder.dogLocation.setText(cachorrosList.get(i).getLocalização());

        dogViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),cachorrosList.get(dogViewHolder.getAdapterPosition()).getApelido(),Toast.LENGTH_SHORT).show();
            }
        });

        dogViewHolder.iconEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            final AlertDialog.Builder mBuilder = new AlertDialog.Builder(mainActivity);
            LayoutInflater inflater = LayoutInflater.from(mainActivity);
            View v = inflater.inflate(R.layout.dialog_edit,null);
            ImageView imagemDogEdit = (ImageView)v.findViewById(R.id.imgDog);

            final EditText apelido = (EditText) v.findViewById(R.id.editApelido);
            final EditText tamanho = (EditText) v.findViewById(R.id.editTamanho);
            final EditText localidade = (EditText) v.findViewById(R.id.editLocalidade);

            Button btnAtualiza = (Button) v.findViewById(R.id.btnEditar);

            imagemDogEdit.setImageResource(cachorrosList.get(dogViewHolder.getAdapterPosition()).getImagem());
            apelido.setText(cachorrosList.get(dogViewHolder.getAdapterPosition()).getApelido());
            tamanho.setText(cachorrosList.get(dogViewHolder.getAdapterPosition()).getTamanho());
            localidade.setText(cachorrosList.get(dogViewHolder.getAdapterPosition()).getLocalização());

            mBuilder.setView(v);
            final AlertDialog dialog = mBuilder.create();
            dialog.show();

            btnAtualiza.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                cachorrosList.get(dogViewHolder.getAdapterPosition()).setApelido(apelido.getText().toString());
                cachorrosList.get(dogViewHolder.getAdapterPosition()).setTamanho(tamanho.getText().toString());
                cachorrosList.get(dogViewHolder.getAdapterPosition()).setLocalização(localidade.getText().toString());

                updateItem(dogViewHolder.getAdapterPosition());

                Toast.makeText(view.getContext(),"Dados atualizados com sucesso! ",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                }
            });
            }
        });
    }


    @Override
    public int getItemCount() {
        return cachorrosList.size();
    }

    public static class DogViewHolder extends RecyclerView.ViewHolder {
        TextView dogNickname;
        TextView dogSize;
        TextView dogSex;
        TextView dogLocation;
        ImageView dogPhoto;
        ImageButton iconEdit;

        DogViewHolder(View itemView) {
            super(itemView);
            itemView.setTag(this);
            iconEdit = (ImageButton)itemView.findViewById(R.id.iconEdit);
            dogPhoto = (ImageView)itemView.findViewById(R.id.dog_photo);
            dogNickname = (TextView)itemView.findViewById(R.id.dog_nickname);
            dogSize = (TextView)itemView.findViewById(R.id.dog_size);
            dogSex = (TextView)itemView.findViewById(R.id.dog_sex);
            dogLocation = (TextView)itemView.findViewById(R.id.dog_local);
        }
    }

    public void insertItem(Cachorro dog) {
        cachorrosList.add(dog);
        notifyItemInserted(getItemCount());
    }

    public void updateItem(int position) {
        cachorrosList.get(position);
        notifyItemChanged(position);
    }

    public void removerItem(int position) {
        posicaoRemovida = position;
        cachorroRemovido = cachorrosList.get(position);

        cachorrosList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, this.getItemCount());

        Snackbar snackbar = Snackbar.make(mainActivity.findViewById(R.id.mainLayout),"Este cachorro foi adotado(a) e saiu da lista :)", Snackbar.LENGTH_LONG);
        snackbar.setAction("Desfazer?", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cachorrosList.add(posicaoRemovida, cachorroRemovido);
                notifyItemInserted(posicaoRemovida);
            }
        });
        snackbar.show();
    }

    public boolean moverItem(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(cachorrosList, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(cachorrosList, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }
}
