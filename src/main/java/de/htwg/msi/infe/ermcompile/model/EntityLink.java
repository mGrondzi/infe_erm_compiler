package de.htwg.msi.infe.ermcompile.model;

/**
 * representation of links between enititys
 * see xml in ressources
 */
public class EntityLink {

    private String name;
    private String funcionality;

    // min and max der kardinalitäten
    // als String da wir * ebenfalls nehmen müssen
    private String min;
    private String max;

    public EntityLink(String name, String funcionality, String min, String max) {
        this.name = name;
        this.funcionality = funcionality;
        this.min = min;
        this.max = max;
    }

    public String getName() {
        return name;
    }

    public String getFuncionality() {
        return funcionality;
    }

    public String getMin() {
        return min;
    }

    public String getMax() {
        return max;
    }
}
