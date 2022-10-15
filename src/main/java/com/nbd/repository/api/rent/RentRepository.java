package com.nbd.repository.api.rent;

import java.util.List;
import java.util.Optional;

public interface RentRepository {
    List<RentModel> fetchRents();

    Optional<RentModel> findByRentId(int id);

    boolean addRent(RentModel rent);

    boolean addRents(List<RentModel> rents);

    boolean dropRent(RentModel rent);

    boolean updateRent(RentModel rent);
}
