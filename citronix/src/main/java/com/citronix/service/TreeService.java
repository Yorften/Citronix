package com.citronix.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.citronix.dto.TreeDTO;
import com.citronix.mapper.TreeMapper;
import com.citronix.model.Tree;
import com.citronix.repository.TreeRepository;

import lombok.extern.log4j.Log4j2;

/**
 * Service interface for Tree entity.
 * Defines methods for CRUD operations and additional business logic.
 */
@Component
@Log4j2
public class TreeService {
    private final List<String> VALID_INCLUDES = Arrays.asList("trainer", "program", "students");

    @Autowired
    private TreeRepository treeRepository;

    @Autowired
    private TreeMapper treeMapper;

    public TreeDTO getTreeById(long id, String... with) {
        return null;
    }

    public List<TreeDTO> getAllTrees(String... with) {
        return null;
    }

    public TreeDTO addTree(Tree tree) {
        return null;
    }

    public TreeDTO updateTree(long treeId, Tree tree) {
        return null;
    }

    public TreeDTO deleteTreeById(long treeId) {
        return null;
    }
}
