package com.example.PresProProject.services;

import com.example.PresProProject.GeoUtils;
import com.example.PresProProject.UserLocation;
import com.example.PresProProject.entities.Provider;
import com.example.PresProProject.repositories.CategoryRepository;
import com.example.PresProProject.repositories.ProviderRepository;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

@Service
public class ProviderServiceImpl implements IProviderService {

    @Autowired
    ProviderRepository providerRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Provider save(Provider provider) {
        Geometry geometry = null;
        try {
            geometry = GeoUtils.wktToGeometry(String.format("POINT (%s %s)",String.valueOf( provider.getLatitude()), String.valueOf(provider.getLongitude())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        provider.setLocation(geometry);
        provider.setCreationTime(System.currentTimeMillis());
        provider.setCreationDate(new Date());
        provider.setCategory(categoryRepository.getOne(provider.getCategoryId()));
        return providerRepository.save(provider);
    }

    @Override
    public Provider findById(Provider provider) {
        return null;
    }

    @Override
    public Collection<Provider> findClosest(UserLocation userLocation) {
        return providerRepository.findClosest(userLocation.getLongitude(), userLocation.getLatitude());
    }

    @Override
    public Page<Provider> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return providerRepository.findAll(pageable);
    }
}
