package enums;

import exception.ItemNotFound;

public enum ExpenseType {
    EQUAL("EQUAL"),
    EXACT("EXACT"),
    PERCENTAGE("PERCENTAGE");;

    private final String type;

    ExpenseType(String type) {
        this.type = type;
    }

    public static ExpenseType fromString(String text) {
        for (ExpenseType b : ExpenseType.values()) {
            if (b.type.equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new ItemNotFound("No constant with text " + text + " found");
    }

    public String getType() {
        return type;
    }
}
