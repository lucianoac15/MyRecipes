package br.com.lucianoac.recipes.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.Objects;

public final class ApiResponse<V> {

    @Nullable
    private final V data;

    @Nullable
    private final Throwable error;


    public ApiResponse(@NonNull final V Data) {
        Objects.requireNonNull(Data);

        this.data = Data;
        this.error = null;
    }

    public ApiResponse(@NonNull final Throwable error) {
        Objects.requireNonNull(error);

        this.error = error;
        this.data = null;
    }

    public boolean isSuccessful() {
        return data != null && error == null;
    }

    @NonNull
    public V getData() {
        if (data == null) {
            throw new IllegalStateException("Data is null; Call ApiResponse#isSuccessful() first.");
        }
        return data;
    }

    @NonNull
    public Throwable getError() {
        if (error == null) {
            throw new IllegalStateException("error is null; Call ApiResponse#isSuccessful() first.");
        }
        return error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApiResponse)) return false;

        ApiResponse<?> that = (ApiResponse<?>) o;

        return (data != null ? getData().equals(that.getData()) : that.data == null) &&
                (error != null ? getError().equals(that.getError()) : that.error == null);
    }

    @Override
    public int hashCode() {
        int result = data != null ? getData().hashCode() : 0;
        result = 31 * result + (error != null ? getError().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "Data=" + data + "\n" +
                ", error=" + error + "\n" +
                '}';
    }
}
