package game;

import rules.MovementRule;
import rules.RestrictionRule;
import rules.SpecialRule;

public class Piece {
    private int id;
    private PieceName name;
    private String nameAbbreviation; // used for displaying
    private Color color;
    private MovementRule[] movementRules;
    private RestrictionRule[] restrictionRules;
    private SpecialRule[] specialRules;


    public Piece(int id, PieceName name, String nameAbbreviation, Color color, MovementRule[] movementRules, RestrictionRule[] restrictionRules){
        this.id = id;
        this.nameAbbreviation = nameAbbreviation;
        this.color = color;
        this.name = name;
        this.movementRules = movementRules;
        this.restrictionRules = restrictionRules;
        this.specialRules = new SpecialRule[]{};
    }

    public Piece(int id, PieceName name, String nameAbbreviation, Color color, MovementRule[] movementRules, RestrictionRule[] restrictionRules, SpecialRule[] specialRules){
        this.id = id;
        this.nameAbbreviation = nameAbbreviation;
        this.color = color;
        this.name = name;
        this.movementRules = movementRules;
        this.restrictionRules = restrictionRules;
        this.specialRules = specialRules;
    }
    public Color getColor() {
        return color;
    }

    public MovementRule[] getMovementRules() {
        return movementRules;
    }

    public RestrictionRule[] getRestrictionRules() {
        return restrictionRules;
    }

    public String getNameAbbreviation() {
        return nameAbbreviation;
    }
    public SpecialRule[] getSpecialRules(){
        return specialRules;
    }

    /*Hago la class movement, agarro la primer movement rule y meto una iteración
    * Agarro las restrictions y gameRules y veo si puedo seguir y si es valido ese move*/
}
