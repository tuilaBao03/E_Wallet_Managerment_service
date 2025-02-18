package com.backend.smartwalletapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.smartwalletapp.model.CardHolder;


@Repository
public interface CardHolderRepository  extends JpaRepository<CardHolder, String>{
    @SuppressWarnings("null")
    boolean existsById( String id);
    CardHolder findByCardHolderId(String cardHolderId);


}
    
