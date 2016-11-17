package de.htwg.msi.infe.ermcompile.model;


import lombok.Getter;

/**
 * Created by Nexoc on 17.11.2016.
 */

@Getter
public class Cardinality {
    private int min;
    private int max;

    public Cardinality(int min, int max) {this.min=min; this.max=max;}

}
