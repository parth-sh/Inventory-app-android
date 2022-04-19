package com.parth8421.proj1.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ProductRepository {
    private ProductDao productDao;

    private LiveData<List<Product>> productListObserver;

    public ProductRepository(Application application) {
        LedgerRoomDatabase db = LedgerRoomDatabase.getDatabase(application);
        productDao = db.productDao();
        productListObserver = productDao.getProductListObserver();
    }

    public LiveData<List<Product>> getProductListObserver() {
        return productListObserver;
    }

    public void insert(Product product) {
        LedgerRoomDatabase.databaseWriteExecutor.execute(() -> {
            productDao.insert(product);
        });
    }

    public void deleteAll() {
        LedgerRoomDatabase.databaseWriteExecutor.execute(() -> {
            productDao.deleteAll();
        });
    }
}
