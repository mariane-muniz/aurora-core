package com.omni.aurora.core.converters;

import com.omni.aurora.core.dto.EntityStructureData;
import com.omni.aurora.core.populator.EntityStructurePopulator;
import com.omni.aurora.core.populator.Populator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EntityStructureConverter extends AbstractConverter<EntityType<?>, EntityStructureData> {

    private final EntityStructurePopulator entityStructurePopulator;

    @Override
    protected List<Populator<EntityType<?>, EntityStructureData>> getPopulators() {
        final List<Populator<EntityType<?>, EntityStructureData>> list = new ArrayList<>();
        list.add(this.entityStructurePopulator);
        return list;
    }

    @Override
    protected EntityStructureData getData() {
        return new EntityStructureData();
    }
}