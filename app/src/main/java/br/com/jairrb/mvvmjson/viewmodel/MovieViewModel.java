package br.com.jairrb.mvvmjson.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.com.jairrb.mvvmjson.model.Movie;
import br.com.jairrb.mvvmjson.repository.MovieRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MovieViewModel extends AndroidViewModel {

    private MutableLiveData<List<Movie>> moviesLiveData = new MutableLiveData<>();
    private MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> loadingLiveData = new MutableLiveData<>();

    private CompositeDisposable disposable = new CompositeDisposable();
    private MovieRepository repository = new MovieRepository();


    public MovieViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Movie>> getMovieLiveData() {
        return moviesLiveData;
    }

    public LiveData<Boolean> getLoadingLiveData() {
        return loadingLiveData;
    }

    public LiveData<Throwable> getErrorLiveData() {
        return errorLiveData;
    }

    public void getMovies() {
        disposable.add(
                repository.getListMovies(getApplication().getApplicationContext())
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable1 -> loadingLiveData.setValue(true))
                        .doAfterTerminate(() -> loadingLiveData.setValue(false))
                        .subscribe(moviesResponse -> moviesLiveData.setValue(moviesResponse.getMovies())
                                , throwable -> errorLiveData.setValue(throwable))
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
