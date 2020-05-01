package com.omni.aurora.core.controllers;

import com.omni.aurora.core.dto.EntityStructureData;
import com.omni.aurora.core.facades.EntityFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/entity-structure")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EntityStructureController {

    private final EntityFacade entityFacade;

    @GetMapping
    public ResponseEntity<List<EntityStructureData>> getEntityStructure() {
        try {
            final List<EntityStructureData> entityStructures = this.entityFacade.getEntityStructures();
            return ResponseEntity.status(HttpStatus.OK).body(entityStructures);
        }
        catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}