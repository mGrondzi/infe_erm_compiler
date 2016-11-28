package de.htwg.msi.infe.ermcompile.model;


import lombok.Getter;

/**
 * Created by Nexoc on 17.11.2016.
 */

@Getter
public class Cardinality {
    private String min;
    private String max;

    public Cardinality(String min, String max) {this.min=min; this.max=max;}

}
