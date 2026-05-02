package pro1.apiDataModel;

import com.google.gson.annotations.SerializedName;

public class Exam {
    @SerializedName("mistnost")
    public String mistnost;

    @SerializedName(value = "pocetPrihlasenych", alternate = {"obsazeni", "prihlaseno", "studenti"})
    public Integer pocetPrihlasenych;
}