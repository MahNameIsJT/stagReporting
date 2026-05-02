package pro1.apiDataModel;

import com.google.gson.annotations.SerializedName;

public class Action {

    @SerializedName("katedra")
    public String katedra; 

    @SerializedName("jednotkaZkr")
    public String zkratkaPredmetu;

    @SerializedName("typAkceZkr")
    public String typAkce;

    @SerializedName("denZkr")
    public String denZkr; 

    @SerializedName("hodinaOd")
    public int hodinaOd;

    @SerializedName("hodinaDo")
    public int hodinaDo;

    @SerializedName("pocetHodin")
    public int pocetHodin;

    @SerializedName("obsazeni")
    public int obsazeni;

    @SerializedName("ucitIdno")
    public Long ucitIdno;
}