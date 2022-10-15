package com.nbd.repository.api;

import com.nbd.model.Rent;

import java.util.List;
import java.util.Optional;

public interface RentRepository {
    List<Rent> fetchRents();

    Optional<Rent> findByRentId(int id);

    boolean addRent(Rent rent);

    boolean addRents(List<Rent> rents);

    boolean dropRent(Rent rent);

    boolean updateRent(Rent rent);
}
