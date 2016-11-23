package de.htwg.msi.infe.ermcompile.model.Table;

import de.htwg.msi.infe.ermcompile.model.Attribute.Attribute;
import de.htwg.msi.infe.ermcompile.model.Attribute.Key;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;


@Getter
public abstract class Table {

    @NonNull
    private String name;
    private List<Attribute> attributes;
    private List<Key> keys;

    public Table(String name, List<Attribute> attributes) {
        this.name = name;
        this.attributes = attributes;
    }

    public void addAttribute(Attribute attribute){
        this.attributes.add(attribute);
    }

    public void removeAttribute(int position){
        this.attributes.remove(position);
    }

    public void addKey(Key key){
        this.keys.add(key);
    }

    public void removeKey(int position){
        this.keys.remove(position);
    }
}
