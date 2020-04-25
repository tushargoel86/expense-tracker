package com.tushar.expenses.periods;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Money implements Serializable, Comparable<Money> {

    private final int amount;

    /**
     * Creates a new {@code Money} object.
     *
     * @param currency the currency.
     * @param amount   fixed-point integer where the last two digits represent decimals.
     */
    public Money(int amount) {
        this.amount = amount;
    }

    /**
     * Creates a new {@code Money} object.
     *
     * @param currency the currency.
     * @param amount   the amount as a double.
     */
    public Money(double amount) {
        this((int) (amount * 100));
    }

    /**
     * Creates a new {@code Money} object if both of the parameters are non-{@code null}.
     *
     * @param currency the currency.
     * @param value    fixed-point integer where the last two digits represent decimals.
     * @return a new {@code Money} object or {@code null} if any of the parameters are {@code null}.
     */
    public static Money valueOf(Integer value) {
            return new Money(value);
    }

    /**
     * Returns a new {@code Money} object whose amount is the sum of this amount and {@code augend}'s amount.
     *
     * @param augend the {@code Money} object to add to this object.
     * @return {@code this} + {@code augend}
     * @throws IllegalArgumentException if this object and {@code augend} have different currencies.
     */
    @NonNull
    public Money add(@NonNull Money augend) {
        Objects.requireNonNull(augend, "augend must not be null");
        System.out.println("amount: " + amount + " augend: " + augend);
        return new Money(amount + augend.amount);
    }

    /**
     * Returns a new {@code Money} object whose amount is the difference between this amount and {@code subtrahend}'s amount.
     *
     * @param subtrahend the {@code Money} object to remove from this object.
     * @return {@code this} - {@code augend}
     * @throws IllegalArgumentException if this object and {@code subtrahend} have different currencies.
     */
    @NonNull
    public Money subtract(@NonNull Money subtrahend) {
        Objects.requireNonNull(subtrahend, "subtrahend must not be null");
        return new Money(amount - subtrahend.amount);
    }

    /**
     * Returns a new {@code Money} object whose amount is this amount multiplied by {@code multiplicand}.
     *
     * @param multiplicand the value to multiply the amount by.
     * @return {@code this} * {@code multiplicand}
     */
    @NonNull
    public Money multiply(int multiplicand) {
        return new Money(amount * multiplicand);
    }


    /**
     * Returns the amount as a fixed-point integer where the last two digits represent decimals.
     */
    public int fixedPointAmount() {
        return amount;
    }

    /**
     * Returns the amount as a double.
     */
    public double doubleAmount() {
        return amount / 100d;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
    	System.out.println("amount: " + amount);
    	String amountString;
        if (amount == 0) {
            amountString = "000";
        } else {
            amountString = Integer.toString(amount);
        }
        System.out.println("amountString "+ amountString);
        return amountString;
    }

	@Override
	public int compareTo(Money money) {
		return Integer.compare(amount, money.amount);
	}

}
