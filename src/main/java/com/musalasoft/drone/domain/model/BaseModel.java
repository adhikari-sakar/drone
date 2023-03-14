package com.musalasoft.drone.domain.model;

import java.util.Objects;

public abstract class BaseModel<ID> {

    private  ID id;

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseModel)) return false;
        if (getClass() != o.getClass()) return false;
        BaseModel<?> model = (BaseModel<?>) o;
        return Objects.equals(id, model.id);
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
