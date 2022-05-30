package com.example.demo.service.impls;

import com.example.demo.entity.Cookbook;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CookbookRepository;
import com.example.demo.service.CookbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CookbookServiceImpl implements CookbookService {

    @Autowired
    private CookbookRepository cookbookRepository;

    @Override
    public Cookbook createCookbook(Cookbook cookbook) {
        return cookbookRepository.save(cookbook);
    }

    @Override
    public Cookbook getCookbookByName(String name) {
        return cookbookRepository.findCookbookByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Cookbook", "Name", name));
    }

    @Override
    public List<Cookbook> findAll() throws Exception {
        return cookbookRepository.findAll();
    }

    @Override
    public Cookbook findById(Long aLong) throws Exception {
        return cookbookRepository.findById(aLong)
                .orElseThrow(() -> new ResourceNotFoundException("Cookbook", "Id", aLong));
    }

    @Override
    public Cookbook update(Cookbook entity, Long aLong) throws Exception {
        Cookbook cookbook = cookbookRepository.findById(aLong)
                .orElseThrow(() -> new ResourceNotFoundException("Cookbook", "Id", aLong));
        cookbook.setName(entity.getName());
        return cookbookRepository.save(cookbook);
    }

    @Override
    public void deleteById(Long aLong) throws Exception {
        Cookbook cookbook = cookbookRepository.findById(aLong)
                .orElseThrow(() -> new ResourceNotFoundException("Cookbook", "Id", aLong));
        cookbookRepository.deleteById(aLong);
    }
}
