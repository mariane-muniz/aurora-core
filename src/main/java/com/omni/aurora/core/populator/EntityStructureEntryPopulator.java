package com.omni.aurora.core.populator;

import com.omni.aurora.core.dto.EntityStructureEntryData;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.metamodel.model.domain.internal.SetAttributeImpl;
import org.hibernate.metamodel.model.domain.internal.SingularAttributeImpl;
import org.springframework.stereotype.Service;

import javax.persistence.metamodel.Attribute;

@Slf4j
@Service
public class EntityStructureEntryPopulator implements Populator<Attribute<?, ?>, EntityStructureEntryData> {

    @Override
    public EntityStructureEntryData populate(Attribute<?, ?> source, EntityStructureEntryData target) {
        target.setName(source.getName());
        if (source instanceof SingularAttributeImpl) {
            target.setOptional(((SingularAttributeImpl) source).isOptional());
            String type = ((SingularAttributeImpl) source).getType().getJavaType().getName();
            if (type.contains(".")) {
                String[] split = type.split("\\.");
                type = split[split.length-1];
            }
            type = type.toLowerCase();
            target.setType(type);
        }
        else if (source instanceof SetAttributeImpl) {
            target.setOptional(true);
            target.setType("list");
        }
        return target;
    }
}