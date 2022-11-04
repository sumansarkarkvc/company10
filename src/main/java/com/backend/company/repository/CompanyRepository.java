package com.backend.company.repository;

import com.backend.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findByCompanyCode(String companyCode);

    void deleteByCompanyCode(String companyCode);
}
