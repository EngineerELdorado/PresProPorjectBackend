package com.example.PresProProject.services;

import com.example.PresProProject.UserLocation;
import com.example.PresProProject.entities.Provider;
import org.springframework.data.domain.Page;

import java.util.Collection;

public interface IProviderService {

    Provider save(Provider provider);
    Provider findById(Provider provider);
    Collection<Provider> findClosest(UserLocation userLocation);
    Page<Provider> findAll(int page, int size);
}
