package com.parth8421.proj1.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.parth8421.proj1.data.Product;
import com.parth8421.proj1.data.ProductRepository;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {

    private ProductRepository productRepository;
    private final LiveData<List<Product>> productListObserver;


    public ProductViewModel(@NonNull Application application) {
        super(application);
        productRepository = new ProductRepository(application);
        productListObserver = productRepository.getProductListObserver();
    }

    public LiveData<List<Product>> getProductListObserver() {
        return productListObserver;
    }

    public void insert(Product product) {
        productRepository.insert(product);
    }

    public void deleteAll() {
        productRepository.deleteAll();
    }
}
