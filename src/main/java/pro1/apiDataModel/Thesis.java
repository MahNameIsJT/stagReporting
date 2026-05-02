package pro1.apiDataModel;

import com.google.gson.annotations.SerializedName;

public class Thesis {
    @SerializedName("datumZadani")
    public StagDate datumZadani;

    @SerializedName("datumOdevzdani")
    public StagDate datumOdevzdani;
}