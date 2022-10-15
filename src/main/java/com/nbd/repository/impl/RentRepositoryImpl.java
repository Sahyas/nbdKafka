package com.nbd.repository.impl;

import com.nbd.model.Rent;
import com.nbd.repository.api.RentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RentRepositoryImpl implements RentRepository {
    private final List<Rent> rents;

    public RentRepositoryImpl() {
        rents = new ArrayList<>();
    }

    @Override
    public List<Rent> fetchRents() {
        return rents;
    }

    @Override
    public Optional<Rent> findByRentId(int id) {
        return rents.stream()
                .filter(rent -> rent.getId() == id)
                .findFirst();
    }

    @Override
    public boolean addRent(Rent rent) {
        return rents.add(rent);
    }

    @Override
    public boolean addRents(List<Rent> rents) {
        return rents.addAll(rents);
    }

    @Override
    public boolean dropRent(Rent rent) {
        return rents.remove(rent);
    }

    @Override
    public boolean updateRent(Rent rent) {
        Optional<Rent> foundRent = findByRentId(rent.getId());
        if(!foundRent.isPresent()) {
            return false;
        }
        dropRent(foundRent.get());
        return addRent(rent);
    }
}
