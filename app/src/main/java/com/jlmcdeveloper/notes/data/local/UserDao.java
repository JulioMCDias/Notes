package com.jlmcdeveloper.notes.data.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.jlmcdeveloper.notes.data.model.User;


import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
public interface UserDao {

    @Query(value = "SELECT * from users WHERE name=:name AND password=:password")
    Single<User> getUser(String name, String password);

    @Query(value = "SELECT * from users")
    Maybe<List<User>> getAllUser();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertUser(User user);

    @Delete
    Single<Integer> deleteUser(User user);
}
