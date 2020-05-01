package com.omni.aurora.core.converters;

import com.omni.aurora.core.dto.EntityStructureEntryData;
import com.omni.aurora.core.populator.EntityStructureEntryPopulator;
import com.omni.aurora.core.populator.Populator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.metamodel.Attribute;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EntityStructureEntryConverter extends AbstractConverter<Attribute<?, ?>, EntityStructureEntryData> {

    private final EntityStructureEntryPopulator entityStructureEntryPopulator;

    @Override
    protected List<Populator<Attribute<?, ?>, EntityStructureEntryData>> getPopulators() {
        final List<Populator<Attribute<?, ?>, EntityStructureEntryData>> list = new ArrayList<>();
        list.add(this.entityStructureEntryPopulator);
        return list;
    }

    @Override
    protected EntityStructureEntryData getData() {
        return new EntityStructureEntryData();
    }
}