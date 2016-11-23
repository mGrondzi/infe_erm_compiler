package de.htwg.msi.infe.ermcompile.model.Key;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Getter;


public abstract class Key extends Attribute{

    public Key(String name, Boolean notNull){
        super(name, notNull);
    }
}
