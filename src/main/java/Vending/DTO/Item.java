package Vending.DTO;

import java.math.BigDecimal;
import java.util.Objects;

public class Item {
    public String name;
    BigDecimal cost;
    public int numberOf;
    public Item(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public int getNumberOf() {
        return numberOf;
    }
    //Only setter for the number of because this will be updated.
    public void setNumberOf(int numberOf) {
        this.numberOf = numberOf;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.cost);
        hash = 89 * hash + Objects.hashCode(this.numberOf);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.cost, other.cost)) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "Item{" + "name=" + name + ", Cost=" + cost + ", number of left=" + numberOf +'}';
    }
}
