import lombok.Getter;

import java.util.Arrays;

@Getter
public class Number {
    String[] number;

    public Number(String[] number){
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Number number1 = (Number) o;
        return Arrays.equals(number, number1.number);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(number);
    }
}
