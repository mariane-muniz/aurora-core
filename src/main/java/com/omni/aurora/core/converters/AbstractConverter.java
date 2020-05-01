package com.omni.aurora.core.converters;

import com.omni.aurora.core.populator.Populator;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public abstract class AbstractConverter<M, D> {

    abstract protected List<Populator<M, D>> getPopulators();
    protected abstract D getData();

    public D convert(final M model) {
        final D dto = this.getData();
        this.getPopulators().forEach(populator -> populator.populate(model, dto));
        return dto;
    }

    public List<D> convertAll(final List<M> models) {
        final List<D> dataList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(models)) {
            models.forEach(model -> dataList.add(this.convert(model)));
        }
        return dataList;
    }
}