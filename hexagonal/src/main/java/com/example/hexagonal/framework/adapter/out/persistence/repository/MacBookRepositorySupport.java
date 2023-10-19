package com.example.hexagonal.framework.adapter.out.persistence.repository;

import com.example.hexagonal.framework.adapter.out.persistence.data.MacBookFragment;

import java.util.List;

public interface MacBookRepositorySupport {

    List<MacBookFragment> findAllMacBook();
}
