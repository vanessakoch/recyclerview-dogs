package com.example.reciclerviewdogs;

import java.util.List;

public class Cachorro {
    private int imagem;
    private String apelido;
    private String tamanho;
    private String sexo;
    private String localização;

    public Cachorro(int imagem, String apelido, String tamanho, String sexo, String localização) {
        this.imagem = imagem;
        this.apelido = apelido;
        this.tamanho = tamanho;
        this.sexo = sexo;
        this.localização = localização;
    }

    public Cachorro(){}

    public void initializeData(List<Cachorro> dogsList){
        dogsList.add(new Cachorro(R.drawable.anjinha, "Anjinha", "P", "Femea", "Canoinhas/SC"));
        dogsList.add(new Cachorro(R.drawable.princesa, "Princesa", "M", "Femea", "Canoinhas/SC"));
        dogsList.add(new Cachorro(R.drawable.bart, "Bart", "M", "Macho", "Florianopolis/SC"));
        dogsList.add(new Cachorro(R.drawable.benji, "Benji", "M", "Macho", "Florianopolis/SC"));
        dogsList.add(new Cachorro(R.drawable.bob, "Bob", "G", "Macho", "Brusque/SC"));
        dogsList.add(new Cachorro(R.drawable.fred, "Fred", "P", "Macho", "Itajai/SC"));
        dogsList.add(new Cachorro(R.drawable.mana, "Mana", "M", "Femea", "Florianopolis/SC"));
        dogsList.add(new Cachorro(R.drawable.frederico, "Frederico", "P", "Macho", "Chapeco/SC"));
        dogsList.add(new Cachorro(R.drawable.puppy, "Puppy", "M", "Femea", "Joaçaba/SC"));
        dogsList.add(new Cachorro(R.drawable.koda, "Koda", "M", "Macho", "Urubici/SC"));
        dogsList.add(new Cachorro(R.drawable.pandora, "Pandora", "P", "Femea", "São Jose/SC"));
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getLocalização() {
        return localização;
    }

    public void setLocalização(String localização) {
        this.localização = localização;
    }

}
