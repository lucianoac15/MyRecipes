package br.com.lucianoac.recipes.common.retrofit;


import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;
import br.com.lucianoac.recipes.api.ApiResponse;

public final class LiveDataCallAdapter<R> implements CallAdapter<R, LiveData<ApiResponse<R>>> {
    private final Type responseType;

    LiveDataCallAdapter(@NonNull final Type responseType) {
        this.responseType = responseType;
    }

    @Override
    public Type responseType() {
        return responseType;
    }

    @Override
    public LiveData<ApiResponse<R>> adapt(@NonNull final Call<R> call) {
        return new LiveData<ApiResponse<R>>() {
            AtomicBoolean started = new AtomicBoolean(false);

            @Override
            protected void onActive() {
                super.onActive();
                if (started.compareAndSet(false, true)) {
                    call.enqueue(new Callback<R>() {

                        @Override
                        public void onFailure(@NonNull Call<R> call, @NonNull Throwable throwable) {
                            postValue(new ApiResponse<>(throwable));
                        }

                        @Override
                        public void onResponse(@NonNull Call<R> call, @NonNull Response<R> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                postValue(new ApiResponse<>(response.body()));
                            }
                        }
                    });
                }
            }
        };
    }
}
