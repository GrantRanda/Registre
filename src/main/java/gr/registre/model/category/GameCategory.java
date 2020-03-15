package gr.registre.model.category;

/**
 * Enum for game categories.
 */
public enum GameCategory implements Category {

    OTHER("Other"),
    ADVENTURE("Adventure"),
    FIGHTING("Fighting"),
    MMO("MMO"),
    MMORPG("MMORPG"),
    METROIDVANIA("Metroidvania"),
    PLATFORM("Platform"),
    PUZZLE("Puzzle"),
    RACING("Racing"),
    RTS("Real-time strategy"),
    RTT("Real-time tactics"),
    ROGUELIKE("Roguelike"),
    RPG("RPG"),
    SANDBOX("Sandbox"),
    SHOOTER("Shooter"),
    SIMULATION("Simulation"),
    SPORTS("Sports"),
    SURVIVAL("Survival"),
    SURVIVAL_HORROR("Survival horror"),
    TEXT("Text"),
    TOWER_DEFENSE("Tower defense"),
    TBS("Turn-based strategy"),
    TBT("Turn-based tactics"),
    VISUAL_NOVEL("Visual Novel"),
    WARGAME("Wargame");

    private final String name;

    GameCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
