package com.nbd.repository.impl.rent;

import com.nbd.repository.api.rent.RentModel;
import com.nbd.repository.api.rent.RentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RentRepositoryImpl implements RentRepository {
    private final List<RentModel> rents;

    public RentRepositoryImpl() {
        rents = new ArrayList<>();
    }

    @Override
    public List<RentModel> fetchRents() {
        return rents;
    }

    @Override
    public Optional<RentModel> findByRentId(int id) {
        return rents.stream()
                .filter(rent -> rent.getId() == id)
                .findFirst();
    }

    @Override
    public boolean addRent(RentModel rent) {
        return rents.add(rent);
    }

    @Override
    public boolean addRents(List<RentModel> rents) {
        return rents.addAll(rents);
    }

    @Override
    public boolean dropRent(RentModel rent) {
        return rents.remove(rent);
    }

    @Override
    public boolean updateRent(RentModel rent) {
        Optional<RentModel> foundRent = findByRentId(rent.getId());
        if(!foundRent.isPresent()) {
            return false;
        }
        dropRent(foundRent.get());
        return addRent(rent);
    }
}
