package pro1.apiDataModel;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ExamsList {
    @SerializedName(value = "terminZkousky", alternate = {"terminyZkousek", "terminy", "items"})
    public List<Exam> terminZkousky;
}