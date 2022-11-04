package com.backend.company.controller;

import com.backend.company.VO.ResponseTemplateVO;
import com.backend.company.entity.Company;
import com.backend.company.repository.CompanyRepository;
import com.backend.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Transactional
@RestController
@RequestMapping("/api/v1.0/market/company/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class    CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyRepository companyRepository;

    @PostMapping("/register")
    public Company saveCompany(@Valid @RequestBody Company company) {
        return companyService.saveCompany(company);
    }

    @GetMapping("/info/{companyCode}")
    public ResponseTemplateVO getCompanyWithStock(@PathVariable("companyCode") String companyCode) {
        return companyService.getCompanyWithStock(companyCode);
    }

    @GetMapping("/details/{companyCode}")
    public Company findCompanyByCode(@PathVariable("companyCode") String companyCode) {
        return companyRepository.findByCompanyCode(companyCode);
    }

    @GetMapping("/getall")
    public List<ResponseTemplateVO> getAllCompaniesWithStock() {
        return companyService.getAllCompaniesWithStock();
    }

    @GetMapping("/getcompany")
    public List<Company> getAll(Company company) {
        return companyRepository.findAll();
    }

    @PutMapping("/update/{companyCode}")
    public ResponseEntity<Company> updateCompany(@PathVariable("companyCode") String companyCode, @RequestBody Company companyDetails) {
        Company company = companyRepository.findByCompanyCode(companyCode);

        company.setCompanyCode(companyDetails.getCompanyCode());
        company.setCompanyName(companyDetails.getCompanyName());
        company.setCompanyCeo(companyDetails.getCompanyCeo());
        company.setCompanyTurnover(companyDetails.getCompanyTurnover());
        company.setCompanyWebsite(companyDetails.getCompanyWebsite());
        company.setCompanyStockExchange(companyDetails.getCompanyStockExchange());

        Company updateCompany = companyRepository.save(company);
        return ResponseEntity.ok(updateCompany);
    }

    @DeleteMapping("/delete/{companyCode}")
    public String deleteCompany(@PathVariable("companyCode") String companyCode) {
        companyService.deleteCompany(companyCode);
        return "Company details with stock price is deleted";
    }
}
