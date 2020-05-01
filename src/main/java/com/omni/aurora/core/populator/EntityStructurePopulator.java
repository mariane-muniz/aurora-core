package com.omni.aurora.core.populator;

import com.omni.aurora.core.converters.EntityStructureEntryConverter;
import com.omni.aurora.core.dto.EntityStructureData;
import com.omni.aurora.core.dto.EntityStructureEntryData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import java.io.ObjectStreamClass;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EntityStructurePopulator implements Populator<EntityType<?>, EntityStructureData> {

    private final EntityStructureEntryConverter entityStructureEntryConverter;

    @Override
    public EntityStructureData populate(final EntityType<?> source, final EntityStructureData target) {
        target.setName(source.getName());
        target.setCode(source.getName().toLowerCase());
        Set<Attribute<?, ?>> attributes = (Set<Attribute<?, ?>>) source.getAttributes();
        List<Attribute<?, ?>> attrList = new LinkedList<>(attributes);
        final List<EntityStructureEntryData> entries =
                this.entityStructureEntryConverter.convertAll(attrList);
        target.setEntries(entries);
        this.setVersion(source, target);
        return target;
    }

    private void setVersion(EntityType<?> source, EntityStructureData target) {
        try {
            final String type = source.getJavaType().getName();
            Class<?> clazz = Class.forName(type);
            long serialVersionUID = ObjectStreamClass.lookup(clazz).getSerialVersionUID();
            target.setVersion(serialVersionUID);
        }
        catch (ClassNotFoundException e) {
            log.error(e.getMessage());
        }
    }
}