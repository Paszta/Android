package com.example.project.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract DAOMovie daoMovie();
    
    // stworzenie instancji
    private static volatile AppDatabase INSTANCE;
    // nadanie bazie nazwy
    private static String DB_NAME = "MoviesDB";
    // okreslenie ilosci watkow na ktorych bedzie pracowan repozytorium
    private static int NUM_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUM_OF_THREADS);


     static AppDatabase getDB(final Context context){
        //sprawdzenie stanu bazy
        if(INSTANCE == null){ // jesli baza danych nie istnieje - inicjalizacja
            synchronized (AppDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME)
                            .addCallback(RoomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .build();
                }

            }

        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback RoomDatabaseCallback = new RoomDatabase.Callback(){
         @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
             super.onCreate(db);

             databaseWriteExecutor.execute(() -> {
                 DAOMovie dao = INSTANCE.daoMovie();
                 Movie[] movies = {new Movie("Test", "www.test.pl")};
                 for (Movie m : movies) dao.insert(m);
             });
         }
    };
}


