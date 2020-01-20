package com.shegi.surveyour.APi;

import com.shegi.surveyour.model.spaceCrafts;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("singel_data.php")
    Call<List<spaceCrafts>> getSingelData(@Query("id")String id);

}
