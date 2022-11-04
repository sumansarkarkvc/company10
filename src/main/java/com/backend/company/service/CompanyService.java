package com.backend.company.service;

import com.backend.company.VO.ResponseTemplateVO;
import com.backend.company.VO.Stock;
import com.backend.company.entity.Company;
import com.backend.company.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    public ResponseTemplateVO getCompanyWithStock(String companyCode) {
        ResponseTemplateVO vo = new ResponseTemplateVO();

        List<Stock> stock = (List<Stock>)restTemplate.getForObject("http://localhost:9090/api/v1.0/market/stock/info/" + companyCode, Object.class);


        Company company = companyRepository.findByCompanyCode(companyCode);
        vo.setCompany(company);
        vo.setStock(stock);

        return vo;
    }

    public List<ResponseTemplateVO> getAllCompaniesWithStock() {
        List<Company> companies = companyRepository.findAll();
        List<ResponseTemplateVO> vo = new ArrayList<>();
        companies.forEach(company -> {
            List<Stock> stock =
                    (List<Stock>)restTemplate.getForObject("http://localhost:9090/api/v1.0/market/stock/info/" + company.getCompanyCode(), Object.class);
            vo.add(new ResponseTemplateVO(company, stock));
        });
        return vo;
    }

    public void deleteCompany(String companyCode) {

        restTemplate.delete("http://localhost:9090/api/v1.0/market/stock/delete/" + companyCode, Stock.class);
        companyRepository.deleteByCompanyCode(companyCode);
    }
}
