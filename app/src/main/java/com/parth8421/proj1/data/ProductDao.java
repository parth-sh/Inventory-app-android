package com.parth8421.proj1.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductDao {
    @Insert
    void insert(Product product);

    @Query("DELETE FROM product")
    void deleteAll();

    @Query("SELECT * FROM product ORDER BY id DESC")
    LiveData<List<Product>> getProductListObserver();

//    @Query("SELECT SUM(cost) FROM ledger")
//    LiveData<Integer> getEntriesCostObserver();

//    @Query("SELECT * FROM ledger ORDER BY id DESC")
//    List<Entry> getInitialEntriesList();
}
