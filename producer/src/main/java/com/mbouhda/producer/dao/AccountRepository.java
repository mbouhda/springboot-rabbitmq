package com.mbouhda.producer.dao;

import com.mbouhda.producer.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {

    Page<Account> findAll(Pageable pageable);
}
