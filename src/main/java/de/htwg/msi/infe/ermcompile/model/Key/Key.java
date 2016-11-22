package de.htwg.msi.infe.ermcompile.model.Key;

import lombok.Getter;


public abstract class Key {
    @Getter
    private String name;

    public Key(String name){
        this.name=name;
    }
}
